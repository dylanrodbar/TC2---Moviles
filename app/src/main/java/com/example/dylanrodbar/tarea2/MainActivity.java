package com.example.dylanrodbar.tarea2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.dylanrodbar.tetris.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void buttonMenu1Clicked(View view){
        Intent intent = new Intent(this, TetrisJuego.class);
        intent.putExtra(EXTRA_MESSAGE, "hi");
        startActivity(intent);
    }

    public void buttonMenu2Clicked(View view){
        Intent intent = new Intent(this, Puntajes.class);
        intent.putExtra(EXTRA_MESSAGE, "");
        startActivity(intent);
    }
}
