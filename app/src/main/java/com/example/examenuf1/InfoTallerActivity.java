package com.example.examenuf1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class InfoTallerActivity extends AppCompatActivity {
    RecyclerView infoTallerRecycler;
    List<InfoTaller> infoTallerList;
    FirebaseDatabase db;
    InfoTallerAdapter infoTallerAdapter;

    Button nuevoComentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_taller);

        nuevoComentario = findViewById(R.id.nuevoComentario);

        nuevoComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InfoTallerActivity.this, NewComentario.class);
                startActivity(intent);
            }
        });

        // creamos array
        infoTallerList = new ArrayList<>();
        // Instanciamos BBDD
        db = FirebaseDatabase.getInstance();
        // Buscamos Nuestro recyclerview
        infoTallerRecycler = findViewById(R.id.infoTallerRecycler);

        // Mostrar Recycler
        infoTallerRecycler.setLayoutManager(new LinearLayoutManager(this));
        infoTallerAdapter = new InfoTallerAdapter();
        infoTallerRecycler.setAdapter(infoTallerAdapter);
        db.getReference().child("Taller").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                /*Este Metodo cada vez que encuentra un nodo saltara este metodo y
                retornara toodo lo que este en ese nodo
                Datasnapshot compacta la info y eres tu quien debe saber lo que va venir
                Se ha de mapear*/

                // Guardo en la arraylist el datasnapshot de tipo reserva
                infoTallerList.add(dataSnapshot.getValue(InfoTaller.class));

                // Aviso al Recycler que hay un nuevo elemento que hay que mostrar
                infoTallerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }







    public class InfoTallerAdapter extends RecyclerView.Adapter<InfoTallerAdapter.InfoTallerHolder> {

       // ArrayList<InfoTaller> listaReservas;


        public InfoTallerAdapter() {

        }

        @Override
        // Dibuja en pantalla los viewholder (elementos de la lista)
        public InfoTallerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = getLayoutInflater();

            return new InfoTallerHolder(layoutInflater, viewGroup);
        }

        @Override
        // Pones los datos en cada viewholder
        public void onBindViewHolder(@NonNull InfoTallerHolder infoTallerHolder, int i) {


            InfoTaller res = infoTallerList.get(i);
            infoTallerHolder.valoracion.setText(res.getValoracion());
            infoTallerHolder.comentario.setText(res.getComentario());
        }

        // Metodo que devuelve la cantidad de objectos de la array.
        // Y sabremos cuantas veces recorrera el bucle que pinta los datos
        @Override
        public int getItemCount() {
            return infoTallerList.size();
        }


        /* MusicaHolder es el bloque donde pinto la info de cada objeto de cojo de la array */
        class InfoTallerHolder extends RecyclerView.ViewHolder {
            TextView valoracion, comentario;

            public InfoTallerHolder(LayoutInflater layoutInflater, ViewGroup parent) { // Busca el itemview
                super(layoutInflater.inflate(R.layout.infotallerholder, parent, false));

                // instanciasmo viewa del layout
                valoracion = itemView.findViewById(R.id.valoracionText);
                comentario = itemView.findViewById(R.id.comentarioText);

            }
        }
    }
}
