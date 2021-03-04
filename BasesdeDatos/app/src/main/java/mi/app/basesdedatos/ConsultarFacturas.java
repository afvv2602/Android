package mi.app.basesdedatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultarFacturas extends AppCompatActivity {

    CrearFacturas facturas;
    SQLiteDatabase dbFacturas;
    Spinner spNum;
    String dni, concepto;
    double valor;
    int num;
    Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_facturas);

        facturas = new CrearFacturas(this, "DBFacturas", null, 1);
        dbFacturas = facturas.getWritableDatabase();

        c = dbFacturas.rawQuery("SELECT num FROM Facturas",null);
        int numReg = c.getCount();
        int cont = 0;
        String [] valoresNum = new String[numReg];



        while (c.moveToNext()){
            valoresNum[cont] = c.getString(0);
            cont++;

        }
        spNum = (Spinner) findViewById(R.id.spinnerNum);
        spNum.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, valoresNum));

        spNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String numS = spNum.getSelectedItem().toString();

                Cursor dni = dbFacturas.rawQuery("SELECT dni FROM Facturas WHERE num = "+numS+"", null);
                Cursor concepto = dbFacturas.rawQuery("SELECT concepto FROM Facturas WHERE num = "+numS+"", null);
                Cursor valor = dbFacturas.rawQuery("SELECT valor FROM Facturas WHERE num = "+numS+"", null);

                TextView dniV = (TextView) findViewById(R.id.dniView);
                while (dni.moveToNext()){
                    dniV.setText(dni.getString(0));
                }


                TextView concV = (TextView) findViewById(R.id.concView);
                while (concepto.moveToNext()){
                    concV.setText(concepto.getString(0));
                }


                TextView valV = (TextView) findViewById(R.id.valView);
                while(valor.moveToNext()){
                    valV.setText(valor.getString(0));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void volverClick(View view){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }
}
