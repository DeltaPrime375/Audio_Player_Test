package com.example.audio_player_test;

import android.media.MediaPlayer;

public class MiReproductor {
    static MediaPlayer Instancia;

    public static MediaPlayer getInstance(){
        if(Instancia == null){
            Instancia = new MediaPlayer();
        }
        return Instancia;
    }

    public static int IndiceActual = -1;
}
