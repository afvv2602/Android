package mi.app.apiminimo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class clicks extends AppCompatActivity {
    int clicks = 0;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_clicks);
        final Button btnclicks = (Button) findViewById(R.id.clicks);
        btnclicks.setText("No has pulsado el boton");
        btnclicks.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clicks++;
                btnclicks.setText("Has pulsado el boton "+clicks+" veces");
            }
        });


        Button btnvolver = (Button) findViewById(R.id.btnVolver);
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal = new Intent(v.getContext(),MainActivity.class);
                startActivity(principal);
            }
        });

        /*
        *
        * Pregunta numero 4
        *
        * 1.-Se borra el contado de clicks
        * 2.-No puedo testearlo
        * 3.-Se mantienen
        * 4.-
        *
        * */
    }
}
