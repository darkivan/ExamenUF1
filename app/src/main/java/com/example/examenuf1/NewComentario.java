package com.example.examenuf1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class NewComentario extends AppCompatActivity {

    EditText valoracionEdit, comentariosEdit;
    Button sendButton;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comentario);

        db = FirebaseDatabase.getInstance();

        valoracionEdit = findViewById(R.id.valoracionEdit);
        comentariosEdit = findViewById(R.id.comentariosEdit);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Generar objeto de tipo reserva y subirlo a Firebase
                InfoTaller infoTaller;
                infoTaller = new InfoTaller(valoracionEdit.getText().toString(), comentariosEdit.getText().toString());
                 /*Esto sirve para guardar en el firebase
                .child() es para guardarlo en la subcarpeta de la raiz
                .push() para que al leer no de ningun problema
                */

                db.getReference().child("Taller").push().setValue(infoTaller);
            }

        });


    }
}
