package com.example.aguacoop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Menu_Trabajador extends AppCompatActivity {
    private TextView msgInicio;
private Button btnCerrarSesion,btntareasTrabajador;
private AsyncHttpClient cliente;
private String logUsu;
private String idUsuarioString;
RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_trabajador);
        btnCerrarSesion = findViewById(R.id.cerrarSesionTrabajador);
        msgInicio = findViewById(R.id.MensajeInicioMenuTrabajador);
        btntareasTrabajador = findViewById(R.id.tareasTrabajador);
        Intent recibir = getIntent();
        Bundle extra = recibir.getExtras();
       String nombreUsuario = extra.getString("LogUsuEnviado");
       String logUsu = nombreUsuario;
        buscarProducto("https://aguaproyect.com/login/getUsuarios.php?txtLogUsu="+logUsu);


        //------------------------BOTONES-------------------------------
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent i = new Intent(Menu_Trabajador.this, InicioSesion.class);
                startActivity(i);
                finish();
            }
        });

        btntareasTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu_Trabajador.this, Tareas_Trabajador.class);
                i.putExtra("idUsuarioTarea",idUsuarioString);
                startActivity(i);
            }
        });

    }
        //-----------------------------------------------------------


    private void buscarProducto(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        msgInicio.setText("Bienvenido: "+jsonObject.getString("nombreUsuario"));
                         int idUsuarioObtenido =jsonObject.getInt("idUsuario");
                        final String prueba= Integer.toString(idUsuarioObtenido);
                         idUsuarioString=prueba;
                        ;

                    } catch (Exception e) {
                        Toast.makeText(Menu_Trabajador.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
