package mi.app.apiminimo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class principal extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String nombre = getIntent().getStringExtra("nb");
        TextView mostrarBienvenida = (TextView) findViewById(R.id.TV1);
        mostrarBienvenida.setText("Bienvenido "+ nombre);

        final Button pares = (Button) findViewById(R.id.btnPares);
        final Button click = (Button) findViewById(R.id.btnClicks);
        final Button sum = (Button) findViewById(R.id.btnSuma);

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),suma.class);
                startActivity(comunicacion);
            }
        });

        pares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),mostrar_pares.class);
                startActivity(comunicacion);
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent comunicacion = new Intent(v.getContext(),clicks.class);
                startActivity(comunicacion);
            }
        });


    }
}


