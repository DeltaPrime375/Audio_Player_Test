package com.example.audio_player_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView sin_canciones;
    ArrayList<ModeloAudio> ListaCanciones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.lista_de_canciones);
        sin_canciones=findViewById(R.id.sin_canciones);

        if(revisarPermisos()==false){
            solicitarPermisos();
            return;
        }

        String [] proyeccion={
            MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.DURATION
        };
        String seleccion=MediaStore.Audio.Media.IS_MUSIC+"!=0";

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proyeccion, seleccion, null, null);
        while(cursor.moveToNext()){
            ModeloAudio DatosCanciones = new ModeloAudio(cursor.getString(1), cursor.getString(0), cursor.getString(2));
            if(new File(DatosCanciones.getDireccion()).exists())
                ListaCanciones.add(DatosCanciones);
        }

        if(ListaCanciones.size()==0){
            sin_canciones.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new AdaptadorDeLista(ListaCanciones, getApplicationContext()));
        }
    }

    boolean revisarPermisos(){
        int result= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }
    void solicitarPermisos() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))  {
            Toast.makeText(MainActivity.this, "No se tienen permisos para leer el almacenamiento", Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }
}