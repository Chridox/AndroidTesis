package com.example.aguacoop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import java.lang.Object;

import cz.msebera.android.httpclient.Header;

public class Tareas_Trabajador extends AppCompatActivity {
    private ListView listaTareasTrabajador;
    private Button btnvolverListaMedidor;
    private AsyncHttpClient cliente;
    private String idUsuario;
    private int idUsuarioFinal;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_trabajador);
        listaTareasTrabajador = (ListView) findViewById(R.id.listaTareasTrabajador);
        btnvolverListaMedidor = (Button) findViewById(R.id.volverListaMedidor);
        cliente = new AsyncHttpClient();
        obtenerDatos();
        refrescar();
        //-------------------------SETEANDO FECHA------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date d = new Date();
        String fecha= dateFormat.format(d);
        //---------------------------------------------------------------------

        btnvolverListaMedidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
public void refrescar(){
    final Handler handler = new Handler();
    handler.postDelayed( new Runnable() {
        @Override
        public void run() {
            obtenerDatos();
            handler.postDelayed( this, 5000 );
        }
    }, 5000 );
}

 public void obtenerDatos(){
     Intent recibir = getIntent();
     Bundle extra = recibir.getExtras();
     String idUsuarioTarea = extra.getString("idUsuarioTarea");
            String url="https://aguaproyect.com/login/getData.php?txtIdUsu="+idUsuarioTarea;
                  cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                actualizarGrilla(new String(responseBody));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
 }


 public void actualizarGrilla(String datos){
        final ArrayList<TareasTrabajador> lisTareas = new ArrayList<TareasTrabajador>();
        try{
            JSONArray json = new JSONArray(datos);
            for (int i=0; i<json.length(); i++){
                int idTT = json.getJSONObject(i).getInt("idTarea");
               int idMedidorTT = json.getJSONObject(i).getInt("idMedidor2");
                String direccion = json.getJSONObject(i).getString("dirMedidor2");
                String lat = json.getJSONObject(i).getString("latMed2");
                String lon = json.getJSONObject(i).getString("lngMed2");
                String tareaTarea = json.getJSONObject(i).getString("tareaTarea");
                String nombreUsuarioTT = json.getJSONObject(i).getString("nombreUsuario");
                String fecha = json.getJSONObject(i).getString("fecha");
                TareasTrabajador TT = new TareasTrabajador(idTT,idMedidorTT,tareaTarea,nombreUsuarioTT,direccion,lat,lon);
                lisTareas.add(TT);
            }
            final ArrayAdapter<TareasTrabajador> adaptador = new ArrayAdapter<TareasTrabajador>(Tareas_Trabajador.this,android.R.layout.simple_list_item_1, lisTareas);
            listaTareasTrabajador.setAdapter(adaptador);


              listaTareasTrabajador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    TareasTrabajador TT = lisTareas.get(position);

                    String url="https://aguaproyect.com/login/getData.php";

                    cliente.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            TareasTrabajador TT = lisTareas.get(position);
                            Intent i = new Intent(Tareas_Trabajador.this,Mapa.class);
                            i.putExtra("latitud",TT.getLatDirTT());
                            i.putExtra("longitud",TT.getLonDirTT());
                            startActivity(i);

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                }
            });

            listaTareasTrabajador.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    TareasTrabajador TT = lisTareas.get(position);
                    int idEnviar = TT.getIdMedidorTT();
                    int idTarEnviar = TT.getIdTT();
                    String tarea = TT.getTareaAsignadaTT();
                    Integer.toString(idTarEnviar);
                    Integer.toString(idEnviar);
                    Intent i = new Intent(Tareas_Trabajador.this, Formulario_Trabajador.class);
                    i.putExtra("enviado2", idEnviar);
                    i.putExtra("idTareaEnviada",idTarEnviar);
                    i.putExtra("tareaEnviada",tarea);
                    startActivity(i);

                   return true;
                }
            });
        }catch (Exception e){
            Toast.makeText(Tareas_Trabajador.this,"Error en la carga de datos",Toast.LENGTH_LONG).show();
        }
 }



}

