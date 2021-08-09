package com.example.aguacoop;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class InicioSesion extends AppCompatActivity {
    RequestQueue rq;
    JsonRequest jrq;
    private EditText txtusu, txtcon;
    private Button btnentrar;
    private String usuario, password;
    private ProgressDialog dialog;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);
        txtusu = (EditText) findViewById(R.id.txtusu);
        txtcon = (EditText) findViewById(R.id.txtpas);
        btnentrar = (Button) findViewById(R.id.btnentrar);
        rq = Volley.newRequestQueue(this);

        recuperarPreferencias();

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = txtusu.getText().toString();
                password = txtcon.getText().toString();

                if (!usuario.isEmpty() && !password.isEmpty()) {

                    validarUsuario("https://aguaproyect.com/login/validar_usuario.php");


                } else {

                    Toast.makeText(InicioSesion.this, "No deben haber campos vacios", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    private void validarUsuario(String URL) {
        dialog = new ProgressDialog(InicioSesion.this);
        dialog.setTitle("Iniciando Sesion");
        dialog.setMessage("Cargando");
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {


                    guardarPreferencias();
                    dialog.hide();
                    Intent i = new Intent(InicioSesion.this, Menu_Trabajador.class);
                    i.putExtra("LogUsuEnviado",usuario);

                    startActivity(i);
                    finish();
                } else {
                    dialog.hide();
                    Toast.makeText(InicioSesion.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InicioSesion.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", usuario);
                parametros.put("password", password);
                return parametros;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void guardarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", usuario);
        editor.putString("password", password);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void recuperarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        txtusu.setText(preferences.getString("usuario", "MiUsuario"));
        txtcon.setText(preferences.getString("password", "12345"));
    }


}

