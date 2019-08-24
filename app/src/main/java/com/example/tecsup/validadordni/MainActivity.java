package com.example.tecsup.validadordni;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

Button btn;
TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn_consultar);
        resultado = findViewById(R.id.txt_respuesta);
    }

    public String FuncionDni(String dni){

            try{
                URL url = new URL("http://aplicaciones007.jne.gon.pe/srop_publico/Consulta/Afiliado/GetNombresCiudadano?DNI=2912");
                URLConnection con =null;
                con = url.openConnection();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(), "ISO-8859-1"));
                StringBuilder sb = new StringBuilder();
                try{
                    String s;
                    while ((s = r.readLine()) != null){
                        sb.append(s);
                        sb.append("\n");
                    }
                } finally {
                    r.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
         resultado = sb.toString();
            return resultado;
         catch (IOException ex){
        }
    }


}
