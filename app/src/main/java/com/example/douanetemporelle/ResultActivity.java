package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private LinearLayout layoutResultat;
    private TextView txtResultat;
    private Button btnNouveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        layoutResultat = findViewById(R.id.layout_resultat_tb);
        txtResultat = findViewById(R.id.txt_resultat_tb);
        btnNouveau = findViewById(R.id.btn_nouveau_tb);

        Intent intent = getIntent();
        String matricule = MainActivity.MATRICULE;

        boolean verdict = intent.getBooleanExtra(
                MainActivity.creerCle(matricule, "VERDICT"), false);
        String nom = intent.getStringExtra(
                MainActivity.creerCle(matricule, "NOM_VOYAGEUR"));

        if (nom == null) {
            nom = "Voyageur";
        }

        if (verdict) {
            layoutResultat.setBackgroundColor(Color.parseColor("#2E7D32"));
            txtResultat.setText("Bon voyage " + nom);
        } else {
            layoutResultat.setBackgroundColor(Color.parseColor("#B71C1C"));
            txtResultat.setText(nom + " est en état d'arrestation temporelle");
        }
<<<<<<< HEAD
//
=======

>>>>>>> origin/main
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(retour);
            finish();
        });
    }
}
