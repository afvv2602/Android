package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Definimos una constante para hacer pruebas
    private final static String etiqueta = "ejemplo";
    Intent intentImplicito;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Accedemos al botón
        final Button btn = (Button) findViewById(R.id.BtnVolver);
        //Log.i(TAG, "Mostrando informacion el log);
    }
    protected void onPause(){
        super.onPause();
        Log.i("Etiqueta", "onPause");

    }
        public void MostrarTexto(View view) {

            intentImplicito = new Intent();
            //Nos va a permitir seleccionar una aplicacion que nos permita enviar un mensaje
            intentImplicito.setAction(Intent.ACTION_SEND);
            //Añadimos el tipo de cifrado o codificacion
            intentImplicito.putExtra(Intent.EXTRA_TEXT,"Hola soy tal");
            //Indicamos el tipo de cifrado o codificacion
            intentImplicito.setType("text/plain");
            //Esto es opcional, va a comprar que la actividad que queremos realizar no "crashea" al ejecutarla
            if (intentImplicito.resolveActivity(getPackageManager())!=null){
                startActivity(intentImplicito);
            }

        }

        public void openMaps (View view){

        // Hay que añadir permisos para acceder a internet
            intent = new Intent (Intent.ACTION_VIEW, Uri.parse("geo: 35.721344, 139.776840"));
            if (intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
            }
        }

        public void mandarSMS(View view){
            //Hay que aplicar permisos desde el manifest
            //Se puede hacer en una sola linea o en dos

            intent = new Intent (Intent.ACTION_SEND);//Uri.parse("smsto: "+"644367798"));
            intent.setData(Uri.parse("smsto: "+"644367798"));
            intent.putExtra("sms_body", "Ejemplo envio mensaje");

            //Con las flags le indicas que quieres hacer una nueva tarea
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivity(intent);
            }
        }
        public void setAlarma(View view){
            intent = new Intent (AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE,"Pa clase");
            intent.putExtra(AlarmClock.EXTRA_HOUR,6);
            intent.putExtra(AlarmClock.EXTRA_MINUTES,15);
            if (intent.resolveActivity(getPackageManager())!= null){
                startActivity(intent);
            }

        }

    public void mandarEmail(View view) {
        intentImplicito = new Intent();
        //Nos va a permitir seleccionar una aplicacion que nos permita enviar un mensaje
        intentImplicito.setAction(Intent.ACTION_SEND);
        //Añadimos el tipo de cifrado o codificacion
        intentImplicito.putExtra(Intent.EXTRA_TEXT,"Gracias por contactar con ");
        //Indicamos el tipo de cifrado o codificacion
        intentImplicito.setType("text/plain");
        //Al estar enviando un email, tienes que poner un asunto y el email al cual enviar el mensaje
        intentImplicito.putExtra(Intent.EXTRA_SUBJECT,"Test envio email");
        intentImplicito.putExtra(Intent.EXTRA_EMAIL,new String[]{"fjcanofp@gmail.com"});
        //Esto es opcional, va a comprobar que la actividad que queremos realizar no "crashea" al ejecutarla
        if (intentImplicito.resolveActivity(getPackageManager())!=null){
            startActivity(intentImplicito);
        }
    }


        /*
        EditText introducido = (EditText) findViewById(R.id.editTextTextPersonName);
        //Lo que hacemos es mostrar la cadena introducida en la vista
        String textoIntroducido = introducido.getText().toString();
        TextView textoMostrar;

        //Creamos una variable tipo Toast, que nos va a permitir mostrar un mensaje temporar en pantalla
            //                                  Donde lo mostramos ,Que mostramos ,Cuanto tiempo lo mostramos
        Toast mostrarNombre = Toast.makeText(getApplicationContext(),textoIntroducido, Toast.LENGTH_SHORT);

        //Podemos colocarlo en pantalla, en caso de no hacerlo saldra abajo en el medio
        mostrarNombre.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
        //Por ultimo lo mostramos en el layout
        mostrarNombre.show();

         */

    }

