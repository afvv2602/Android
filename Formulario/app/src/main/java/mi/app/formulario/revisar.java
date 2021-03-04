package mi.app.formulario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class revisar extends AppCompatActivity {
    TextView nb;
    TextView fc;
    TextView sx;
    TextView myD;
    Button er;
    Button co;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revisar);
        String nombre = getIntent().getStringExtra("nombre");
        String fecha = getIntent().getStringExtra("fecha");
        String sexo = getIntent().getStringExtra("sexo");
        String mayorEdad = getIntent().getStringExtra("mayorEdad");
        nb = findViewById(R.id.nombre);
        fc = findViewById(R.id.fecha);
        sx = findViewById(R.id.sexo);
        myD = findViewById(R.id.mayorEdad);
        nb.setText("Tu nombre es: "+nombre);
        fc.setText("Tu fecha de nacimiento es: "+fecha);
        sx.setText("Tu sexo es: "+sexo);
        myD.setText(mayorEdad);
        er = findViewById(R.id.error);
        co = findViewById(R.id.correcto);
        er.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),MainActivity.class);
                setResult(RESULT_CANCELED,comunicacion);
                finish();
            }
        });
        co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),MainActivity.class);
                setResult(RESULT_OK, comunicacion);
                finish();
            }
        });
    }

}
