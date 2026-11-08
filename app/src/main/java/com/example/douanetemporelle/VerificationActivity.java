package com.example.douanetemporelle;


import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    public static final String MATRICULE = "6236";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verification);

        Intent intent = getIntent();

        String n = intent.getStringExtra(
                MainActivity.creerCle(MATRICULE, "NOM_VOYAGEUR"));

        String a = intent.getStringExtra(
                MainActivity.creerCle(MATRICULE, "NAISSANCE"));

        String d = intent.getStringExtra(
                MainActivity.creerCle(MATRICULE, "DESTINATION"));

        String m = intent.getStringExtra(
                MainActivity.creerCle(MATRICULE, "MOTIF"));
    }
}
