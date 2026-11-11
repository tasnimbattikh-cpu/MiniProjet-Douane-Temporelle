package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private TextView txtNom, txtAge, txtDestination, txtMotif;
    private Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        txtNom = findViewById(R.id.txt_nom_tb);
        txtAge = findViewById(R.id.txt_age_tb);
        txtDestination = findViewById(R.id.txt_destination_tb);
        txtMotif = findViewById(R.id.txt_motif_tb);
        btnApprouver = findViewById(R.id.btn_approuver_tb);
        btnRefuser = findViewById(R.id.btn_refuser_tb);

        Intent intent = getIntent();
        String matricule = MainActivity.MATRICULE;

        String n = intent.getStringExtra(
                MainActivity.creerCle(matricule, "NOM_VOYAGEUR"));
        int anneeNaissance = intent.getIntExtra(
                MainActivity.creerCle(matricule, "NAISSANCE"), 0);
        int anneeDestination = intent.getIntExtra(
                MainActivity.creerCle(matricule, "DESTINATION"), 0);
        String m = intent.getStringExtra(
                MainActivity.creerCle(matricule, "MOTIF"));

        int ageTemporel = anneeDestination - anneeNaissance;

        txtNom.setText("Nom : " + n);
        txtAge.setText("Âge temporel : " + ageTemporel + " ans");
        txtDestination.setText("Année de destination : " + anneeDestination);
        txtMotif.setText("Motif : " + m);

        boolean differencePremiere = estPremier(ageTemporel);

        if ("Modification du passé".equals(m) && differencePremiere) {
            btnApprouver.setEnabled(false);
            btnApprouver.setText("Approbation impossible");
        }

        btnApprouver.setOnClickListener(v -> ouvrirResultat(true, n));

        btnRefuser.setOnClickListener(v -> ouvrirResultat(false, n));
    }

    private boolean estPremier(int nombre) {
        if (nombre < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(nombre); i++) {
            if (nombre % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void ouvrirResultat(boolean verdict, String nomVoyageur) {
        Intent intent = new Intent(VerificationActivity.this,
                ResultActivity.class);

        String matricule = MainActivity.MATRICULE;
        intent.putExtra(MainActivity.creerCle(matricule, "VERDICT"), verdict);
        intent.putExtra(MainActivity.creerCle(matricule, "NOM_VOYAGEUR"), nomVoyageur);

        startActivity(intent);
    }
}
