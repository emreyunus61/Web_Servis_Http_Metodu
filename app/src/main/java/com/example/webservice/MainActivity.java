package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b=(Button) findViewById(R.id.button);
      tv1=(TextView)findViewById(R.id.tv1);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("Sunucunun Cevabı");
                 new arkaPlan().execute("http://192.168.1.68:8080/api/Persons/getName");

//Exucutenin amacı doınbackground sınıfını çağırmak

            }
        });

    }

    class  arkaPlan extends AsyncTask<String,String,String> {

        protected String doInBackground(String... params) {
            // İlk elamanı sunucu adresi URL
            HttpURLConnection connection = null;
            BufferedReader br = null;

            try {
                URL url = new URL(params[0]); //http://127.0.0.1:8000/Listele
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();//Sunucuya bağlanmayı sağlar
                InputStream is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is)); //url 'i hhtpconnectiona bağlıoruz =>inputstream bağlanıyor=>

                String satir;
                String dosya="";
                //inputstreamRader bağlanıyor=>Bufferradear bağlanarak satır ssatır okunma sağlanıyor
                while ((satir = br.readLine()) != null) {

                    Log.d("satir:", satir);
                    dosya += satir;

                }
                return dosya;


            } catch (Exception e) {

                e.printStackTrace();
            }

            return "Hata";
        }


         protected void onPostExecute(String s){

            Log.d("postExceutetangelen",s);



            tv1.setText(s);
         }

        }
}
