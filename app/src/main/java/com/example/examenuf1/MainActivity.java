package com.example.examenuf1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    TextView tiempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiempo = findViewById(R.id.tiempo);
        MiHilo hilo = new MiHilo();
        hilo.execute("https://jdarestaurant.firebaseio.com/talleres.json");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //FragmentTransaction fmt = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.talleres:
                intent = new Intent(MainActivity.this, TallerActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    public class MiHilo extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection;
            URL url;
            connection = null;
            String result;
            result ="";

            try{

                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while(data != -1){
                    result += (char) data;
                    data = inputStream.read();
                    //tiempo.setText(data);


                }

            }catch (Exception e){

                e.printStackTrace();

            }

            Log.i("RESULT", result);

            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            tiempo.setText(data);

          try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray(data);

                for(int i=0; i<jsonArray.length(); i++){

                    JSONObject jsonitem = jsonArray.getJSONObject(i);
                    tiempo.setText(jsonitem.toString());

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
