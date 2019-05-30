package com.example.examenuf1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TallerActivity extends AppCompatActivity {

    RecyclerView recycleTaller;
    List<Taller> tallerList = new ArrayList<>();
    TallerAdapter tallerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taller);

        Taller tall1 = new Taller("Talleres ROS","Renault");
        Taller tall2 = new Taller("Talleres Autolica","Mercedes");
        Taller tall3 = new Taller("Pepito","Seat");

        tallerList.add(tall1);
        tallerList.add(tall2);
        tallerList.add(tall3);

        recycleTaller = findViewById(R.id.recycleTaller);

        // Mostrar Recycler
        recycleTaller.setLayoutManager(new LinearLayoutManager(this));
        tallerAdapter = new TallerAdapter();
        recycleTaller.setAdapter(tallerAdapter);
    }
    public class TallerAdapter extends RecyclerView.Adapter<TallerAdapter.TallerHolder> {

        public TallerAdapter() {

        }

        @Override
        // Dibuja en pantalla los viewholder (elementos de la lista)
        public TallerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = getLayoutInflater();
            return new TallerHolder(layoutInflater, viewGroup);
        }

        @Override
        // Pones los datos en cada viewholder
        public void onBindViewHolder(@NonNull TallerHolder  tallerHolder, int i) {


            Taller res = tallerList.get(i);
            tallerHolder.nombreTaller.setText(res.getNombre());
            tallerHolder.marcaTaller.setText(res.getMarca());
        }

        // Metodo que devuelve la cantidad de objectos de la array.
        // Y sabremos cuantas veces recorrera el bucle que pinta los datos
        @Override
        public int getItemCount() {
            return tallerList.size();
        }


        /* MusicaHolder es el bloque donde pinto la info de cada objeto de cojo de la array */
        class TallerHolder extends RecyclerView.ViewHolder{
            TextView nombreTaller, marcaTaller;

            public TallerHolder(LayoutInflater layoutInflater, ViewGroup parent) { // Busca el itemview
                super(layoutInflater.inflate(R.layout.tallerholder, parent,false));

                nombreTaller = itemView.findViewById(R.id.nombreTaller);
                marcaTaller = itemView.findViewById(R.id.marcaTaller);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TallerActivity.this, InfoTallerActivity.class);
                        startActivity(intent);
                    }

                });


            }
        }

    }
}

