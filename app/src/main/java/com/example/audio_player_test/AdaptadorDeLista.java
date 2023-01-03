package com.example.audio_player_test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDeLista extends RecyclerView.Adapter<AdaptadorDeLista.viewHolder>{
    ArrayList<ModeloAudio> ListaCanciones;
    Context contexto;

    public AdaptadorDeLista(ArrayList<ModeloAudio> listaCanciones, Context contexto) {
        ListaCanciones = listaCanciones;
        this.contexto = contexto;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(contexto).inflate(R.layout.objeto_reciclador,parent,false);
        return new AdaptadorDeLista.viewHolder(vista);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        ModeloAudio datosCanciones = ListaCanciones.get(position);
        holder.VistaTitulo.setText(datosCanciones.getTitulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiReproductor.getInstance().reset();
                MiReproductor.IndiceActual= position;
                Intent Intencion = new Intent(contexto, ActividadReproductor.class);
                Intencion.putExtra("LISTA",ListaCanciones);
                Intencion.setFlags(Intencion.FLAG_ACTIVITY_NEW_TASK);
                contexto.startActivity(Intencion);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListaCanciones.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView VistaTitulo;
        ImageView VistaIcono;
        public viewHolder(View itemView) {
            super(itemView);
            VistaTitulo=itemView.findViewById(R.id.titulo_cancion);
            VistaIcono=itemView.findViewById(R.id.vista_icono);
        }
    }
}
