package com.example.dylanrodbar.tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Puntajes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);
        int puntaje = getIntent().getExtras().getInt("puntaje");
        TextView tV = findViewById(R.id.textViewValorPuntaje);
        tV.setText(String.valueOf(puntaje));
    }
}
