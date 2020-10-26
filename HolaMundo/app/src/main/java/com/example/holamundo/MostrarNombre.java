package com.example.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.file.Files;

public class MostrarNombre extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.mostrar_saludo);
        //Recogemos los valores del MainActivity
        String nombre = getIntent().getStringExtra("NOMBRE");
        TextView mostrarNombre = (TextView) findViewById(R.id.mostrarNombre);
        mostrarNombre.setText(nombre);

        //Pulsar el boton para volver a MainActivity
        Button btn = (Button) findViewById(R.id.BtnVolver);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal = new Intent(v.getContext(),MainActivity.class);
                startActivity(principal);
            }
        });
    }
}
