package mi.app.ochobotones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean encendido = false;
    TextView cronometro;
    Thread hilo;
    Button iniciar, reiniciar;
    int mili = 0, seg = 0, minutos = 0;
    Handler h = new Handler(); // sirve para modificar el textview
    Intent intentImplicito;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cronometro = (TextView) findViewById(R.id.cronometro);
        iniciar = (Button) findViewById(R.id.iniciar);
        reiniciar = (Button) findViewById(R.id.reiniciar);
        iniciar.setOnClickListener(this);
        reiniciar.setOnClickListener(this);
    }

    public void contactar(View view) { //email
        intentImplicito = new Intent();
        //Nos va a permitir seleccionar una aplicacion que nos permita enviar un mensaje
        intentImplicito.setAction(Intent.ACTION_SEND);
        intentImplicito.putExtra(Intent.EXTRA_TEXT, "Texto por defecto");
        //Indicamos el tipo de cifrado o codificacion
        intentImplicito.setType("text/plain");
        //Al estar enviando un email, tienes que poner un asunto y el email al cual enviar el mensaje
        intentImplicito.putExtra(Intent.EXTRA_SUBJECT, "Por defecto ");
        intentImplicito.putExtra(Intent.EXTRA_EMAIL, new String[]{"https://palomatica.info/"});
        //Esto es opcional, va a comprobar que la actividad que queremos realizar no "crashea" al ejecutarla
        if (intentImplicito.resolveActivity(getPackageManager()) != null) {
            startActivity(intentImplicito);
        }
    }

    public void agenda(View view) { // agenda
        intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void camara(View view) { // camara
        intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void ruta(View view) { // maps
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 40.459418, -3.717462"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void llamar(View view) { // llamar
        intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+34913980300"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void web(View view) { // pagina web
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.palomafp.org/"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void iniciarHilo() {
        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){ // while infinito
                    if(encendido){ // se activa la variable encendido si se presiona el boton iniciar
                        try {
                            Thread.sleep(1);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        mili++;
                        if(mili >= 999){
                            seg++;
                            mili=0;
                        }if(seg>=59){
                            minutos++;
                            seg=0;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                String m="",s="",mi="";
                                if(mili<10){ //Modificar la variacion de los 0
                                    m="00"+mili;
                                }else if (mili<=100){
                                    m="0"+mili;
                                }else{
                                    m=""+mili;
                                }
                                if (seg<=10){
                                    s="0"+seg;
                                }else{
                                    s=""+seg;
                                }
                                if(minutos<=10){
                                    mi="0"+minutos;
                                }else{
                                    mi=""+minutos;
                                }
                                cronometro.setText(mi+":"+s+":"+m);
                            }
                        });
                    }
                }
            }
        });
        hilo.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iniciar:
                iniciarHilo();
                if (encendido){
                    encendido=false;
                    iniciar.setText("Iniciar");
                    break;
                }else {
                    encendido=true;
                    iniciar.setText("Parar");
                    break;
                }
            case R.id.reiniciar:
                encendido = false;
                iniciar.setText("Iniciar");
                mili=0;
                seg=0;
                minutos=0;
                cronometro.setText("00:00:000");
                break;
        }
    }


}