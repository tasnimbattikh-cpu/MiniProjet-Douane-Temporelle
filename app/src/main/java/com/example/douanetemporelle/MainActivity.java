package com.example.douanetemporelle;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nom, naissance, destination;
    private Spinner motif;
    private Button btnInspecter;

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
            String n = nom.getText().toString().trim();
            String a = naissance.getText().toString().trim();
            String d = destination.getText().toString().trim();

            if (n.isEmpty() || a.isEmpty() || d.isEmpty()) {
                Toast.makeText(this, "يرجى تعمير جميع الخانات",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int anneeNaissance = Integer.parseInt(a);
                int anneeDestination = Integer.parseInt(d);

                if (anneeNaissance <= 0 || anneeDestination <= 0) {
                    Toast.makeText(this, "السنة يجب أن تكون أكبر من صفر",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String m = motif.getSelectedItem().toString();

                Intent intent = new Intent(MainActivity.this,
                        VerificationActivity.class);

                intent.putExtra(creerCle(MATRICULE, "NOM_VOYAGEUR"), n);
                intent.putExtra(creerCle(MATRICULE, "NAISSANCE"), anneeNaissance);
                intent.putExtra(creerCle(MATRICULE, "DESTINATION"), anneeDestination);
                intent.putExtra(creerCle(MATRICULE, "MOTIF"), m);

                startActivity(intent);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "أدخل السنوات بالأرقام فقط",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
