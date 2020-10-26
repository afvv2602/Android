package mi.app.apiminimo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class suma extends AppCompatActivity {

    int numero1 = 0;
    int numero2 = 0;
    int resultado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_suma);


        final Button btnSum = (Button) findViewById(R.id.btnSuma);
        btnSum.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText txt1 = (EditText) findViewById(R.id.sum1);
                EditText txt2 = (EditText) findViewById(R.id.sum2);
                numero1 = Integer.parseInt(txt1.getText().toString());
                numero2 = Integer.parseInt(txt2.getText().toString());
                resultado = numero1 + numero2;
                TextView res =findViewById(R.id.txt3);
                res.setText("El resultado es : "+resultado);


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
    }
}