package com.example.examenuf1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText insertUser, insertPassword;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        insertUser = findViewById(R.id.insertUser);
        insertPassword = findViewById(R.id.insertPassword);

        MyBBDD_Helper sql = new MyBBDD_Helper(this);
        db = sql.getWritableDatabase();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();

                values.put(MyBBDD_Schema.EntradaBBDD.COLUMNA1, insertUser.getText().toString());
                values.put(MyBBDD_Schema.EntradaBBDD.COLUMNA2, insertPassword.getText().toString());
                db.insert(MyBBDD_Schema.EntradaBBDD.TABLE_NAME, null, values);


            }


        });

    }
}
