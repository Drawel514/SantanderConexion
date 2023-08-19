package com.example.santanderdaniel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.mime.Header;

public class MainActivity extends AppCompatActivity {
//Atributos
RecyclerView contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializampos las Vistas
        contenedor = findViewById(R.id.contenedor);
        contenedor.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient httpCliente=new AsyncHttpClient();

        httpCliente.get("http://10.185.144.104/Tienda/wservicio/listar.php", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String respuesta=new String(responseBody);

                try {
                    cargarDatos(statusCode,respuesta);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cargarDatos(int statusCode, String respuesta) throws JSONException {
        if (statusCode==200){
            //Si hay conexion
            Log.e("datos",respuesta.toString());
            //convertir el string a formato json

            JSONArray miJsonArray=new JSONArray(respuesta);
            //colocar datos en el listView
            //crear el adaptador
            Adaptador miAdaptador = new Adaptador(this,miJsonArray);
            contenedor.setAdapter(miAdaptador);
        }else{
            Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
        }
    }
}
