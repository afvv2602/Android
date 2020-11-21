package mi.app.variasactividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class calculadora extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        final Button sumar = (Button) findViewById(R.id.suma);
        final Button restar = (Button) findViewById(R.id.resta);
        final Button multiplicar = (Button) findViewById(R.id.multiplicar);
        final Button dividir = (Button) findViewById(R.id.dividir);
        final Button volver = (Button) findViewById(R.id.volver);

        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num1 = (EditText)findViewById(R.id.primerNum);
                Double primerNum = Double.parseDouble(num1.getText().toString());
                EditText num2 = (EditText)findViewById(R.id.segundoNum);
                Double segunNum = Double.parseDouble(num2.getText().toString());
                TextView resultadoTextV = (TextView)findViewById(R.id.resultado);
                Double resultadoNum = primerNum + segunNum;
                String resultado = resultadoNum.toString();
                resultadoTextV.setText(resultado);
            }
        });
        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num1 = (EditText)findViewById(R.id.primerNum);
                Double primerNum = Double.parseDouble(num1.getText().toString());
                EditText num2 = (EditText)findViewById(R.id.segundoNum);
                Double segunNum = Double.parseDouble(num2.getText().toString());
                TextView resultadoTextV = (TextView)findViewById(R.id.resultado);
                Double resultadoNum = primerNum - segunNum;
                String resultado = resultadoNum.toString();
                resultadoTextV.setText(resultado);
            }
        });
        multiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num1 = (EditText)findViewById(R.id.primerNum);
                Double primerNum = Double.parseDouble(num1.getText().toString());
                EditText num2 = (EditText)findViewById(R.id.segundoNum);
                Double segunNum = Double.parseDouble(num2.getText().toString());
                TextView resultadoTextV = (TextView)findViewById(R.id.resultado);
                Double resultadoNum = primerNum * segunNum;
                String resultado = resultadoNum.toString();
                resultadoTextV.setText(resultado);

            }
        });
        dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num1 = (EditText)findViewById(R.id.primerNum);
                Double primerNum = Double.parseDouble(num1.getText().toString());
                EditText num2 = (EditText)findViewById(R.id.segundoNum);
                Double segunNum = Double.parseDouble(num2.getText().toString());
                TextView resultadoTextV = (TextView)findViewById(R.id.resultado);
                Double resultadoNum = primerNum / segunNum;
                String resultado = resultadoNum.toString();
                resultadoTextV.setText(resultado);
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), MainActivity.class);
                startActivity(comunicacion);
            }
        });
    }
}
