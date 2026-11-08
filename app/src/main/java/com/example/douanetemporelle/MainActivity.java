
package com.example.douanetemporelle;

import android.os.Bundle;
import android.widget.*;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nom, naissance, destination;
    Spinner motif;
    Button btnInspecter;


    public static final String MATRICULE = "6236";

    public static String creerCle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nom = findViewById(R.id.et_nom_tb);
        naissance = findViewById(R.id.et_naissance_tb);
        destination = findViewById(R.id.et_destination_tb);
        motif = findViewById(R.id.sp_motif_tb);
        btnInspecter = findViewById(R.id.btn_inspecter_tb);


        String[] choix = {
                "Tourisme",
                "Modification du passé",
                "Fuite"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                choix
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        motif.setAdapter(adapter);


        btnInspecter.setOnClickListener(v -> {

            String n = nom.getText().toString();
            String a = naissance.getText().toString();
            String d = destination.getText().toString();
            String m = motif.getSelectedItem().toString();


            if (n.isEmpty() || a.isEmpty() || d.isEmpty()) {
                Toast.makeText(this, "Remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
            } else {


                Intent intent = new Intent(MainActivity.this,
                        VerificationActivity.class);


                intent.putExtra(creerCle(MATRICULE, "NOM_VOYAGEUR"), n);
                intent.putExtra(creerCle(MATRICULE, "NAISSANCE"), a);
                intent.putExtra(creerCle(MATRICULE, "DESTINATION"), d);
                intent.putExtra(creerCle(MATRICULE, "MOTIF"), m);

                startActivity(intent);
            }
        });
    }
}
