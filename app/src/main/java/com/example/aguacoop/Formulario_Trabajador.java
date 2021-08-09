package com.example.aguacoop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Method;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


public class Formulario_Trabajador extends AppCompatActivity{

    private EditText idMedidorFormulario,observacionesTrabajador,resultadoQR,trabajoRealizado;
    private AsyncHttpClient cliente;
    private Button btnguardarTrabajo,btnvolverTrabajo,btnQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_trabajador);
        idMedidorFormulario = (EditText)findViewById(R.id.idMedidorFormulario);
        observacionesTrabajador = (EditText)findViewById(R.id.observacionesTrabajador);
        resultadoQR = (EditText)findViewById(R.id.resultadoQR);
        trabajoRealizado = (EditText) findViewById(R.id.trabajoRealizado);
        btnguardarTrabajo = (Button)findViewById(R.id.guardarTrabajo);
        btnvolverTrabajo = (Button)findViewById(R.id.volverTrabajo);
        btnQR = (Button)findViewById(R.id.btnQR);
        cliente = new AsyncHttpClient();


        // ----------------RECIBIENDO DATOS A TRAVES DE INT EXTRA----------
        Intent recibo = getIntent();
        int recibo2= recibo.getIntExtra("enviado2",0);
        String recibofinal=  String.valueOf(recibo2);
        idMedidorFormulario.setText(recibofinal.toString());
        int idRecibida = recibo.getIntExtra("idTareaEnviada",0);

        final String idTareaRecibida = String.valueOf(idRecibida);
        final String tareaRecibida = recibo.getStringExtra("tareaEnviada");

        //----------------------------------------------------------------
            trabajoRealizado.setText(tareaRecibida);


        //----------------------------------------------------------------


        btnguardarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (observacionesTrabajador.getText().length()==0 || resultadoQR.getText().length()==0 || trabajoRealizado.getText().toString().isEmpty()){
                   AlertDialog.Builder msg = new AlertDialog.Builder(Formulario_Trabajador.this);
                   msg.setTitle("Error");
                   msg.setCancelable(true);
                   msg.setMessage("No deben haber campos vacios");
                   msg.show();
               }else{
                   String id = idMedidorFormulario.getText().toString();
                   String tra = trabajoRealizado.getText().toString();
                   String QR = resultadoQR.getText().toString();
                   QR.replaceAll("https://portal.sidiv.registrocivil.cl/docstatus?","");
                   String OBser = observacionesTrabajador.getText().toString();

                   //-------------REEMPLAZAR ESPACIOS----------------------------------





                   String urlPrueba = "https://aguaproyect.com/login/putData.php?idMedidorFormulario="+id+"&trabajoRealizado="+tra+"&resultadoQR="+QR+"&observacionesTrabajador="+OBser+"&idTar="+idTareaRecibida;
                   urlPrueba = urlPrueba.replaceAll(" ","%20");
                   String url = urlPrueba;
                   //--------------------------------------------------------------------------


                   cliente.post(url, new AsyncHttpResponseHandler() {
                       @Override
                       public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                           if (statusCode==200){

                               idMedidorFormulario.setText("");
                               resultadoQR.setText("");
                               observacionesTrabajador.setText("");

                               AlertDialog msg = new AlertDialog.Builder(Formulario_Trabajador.this).create();
                               msg.setTitle("Registro");
                               msg.setMessage("Se registro con exito!");
                               msg.show();
                               msg.setCancelable(true);
                               msg.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                   @Override
                                   public void onCancel(DialogInterface dialog) {
                                       finish();
                                   }
                               });
                           }

                       }

                       @Override
                       public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


                       }
                   });
               }
            }
        });



        btnvolverTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });




        btnQR.setOnClickListener(mOnCLickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result !=null)
            if (result.getContents()!=null){
                String resultadoPrueba = result.getContents();
                resultadoPrueba = resultadoPrueba.replace("https://portal.sidiv.registrocivil.cl/docstatus?","");
                resultadoQR.setText(resultadoPrueba);

            }else{
                resultadoQR.setText("Error al escanear");
            }
    }

    private View.OnClickListener mOnCLickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnQR:
                    new IntentIntegrator(Formulario_Trabajador.this).initiateScan();
                    break;
            }
        }
    };


}
