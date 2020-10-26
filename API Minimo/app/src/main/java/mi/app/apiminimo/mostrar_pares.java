package mi.app.apiminimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class mostrar_pares extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_pares);
        Button btnP = (Button) findViewById(R.id.btnPares);
        btnP.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText texto = (EditText) findViewById(R.id.nombre);
                int numero = Integer.parseInt(texto.getText().toString());
                String cadenaF = "";
                int j = 0;
                for (int i = 0; i <= numero; i++) {
                    if (i % 2 == 0) {
                        cadenaF += i + " ";
                    }

                }
                TextView mostrarPares =findViewById(R.id.mostrarPares);
                mostrarPares.setText(cadenaF);


            }
        });



        Button btn = (Button) findViewById(R.id.btnVolver);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent principal = new Intent(v.getContext(),MainActivity.class);
                startActivity(principal);
            }
        });
    }
}
