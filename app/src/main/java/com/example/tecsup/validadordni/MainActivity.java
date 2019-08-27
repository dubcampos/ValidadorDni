package com.example.tecsup.validadordni;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

private Button btn_consultar;
private TextView txt_respuesta;
private String resultado;
private EditText txt_dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_consultar = findViewById(R.id.btn_consultar);
        txt_respuesta = (TextView)findViewById(R.id.txt_respuesta);
        txt_dni = (EditText)findViewById(R.id.txt_dni);

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_dni.getText().toString().length()==8){
                    ValidarDni(txt_dni.getText().toString());
                    String splitResultado = Split(resultado);
                    if(splitResultado.equals("  ")){
                        txt_respuesta.setText("-DNI NO ENCONTRADO-");
                    }else{
                        txt_respuesta.setText(splitResultado);
                    }
                }else{
                    Toast.makeText(MainActivity.this,"DNI debe tener 8 digitos",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public String ValidarDni(String dni){
        try{
            URL url = new URL("http://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Afiliado/GetNombresCiudadano?DNI="+dni);
            URLConnection con = null;
            con = url.openConnection();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(),"ISO-8859-1"));
            StringBuilder sb = new StringBuilder();
            try{
                String s;
                while ((s = r.readLine())!=null){
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                r.close();
            }
            resultado = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;

    }

    public String Split(String nombre){
        String var = nombre;
        String[] array = var.split("\\|");
        nombre = array[2] + " " + array[0] + " " +array[1];
        return nombre;

    }






}
