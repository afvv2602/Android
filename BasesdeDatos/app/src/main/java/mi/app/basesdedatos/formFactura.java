package mi.app.basesdedatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class formFactura extends AppCompatActivity {

    CrearFacturas facturas;
    CrearClientes clientes;
    SQLiteDatabase dbFacturas, dbClientes;
    Spinner spNum;
    String dni, concepto;
    double valor;
    int num;
    Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_facturas);

        //Abrimos facturas
        facturas = new CrearFacturas(this, "DBFacturas", null, 1);
        dbFacturas = facturas.getWritableDatabase();

        clientes = new CrearClientes(this, "DBClientes", null, 1);
        dbClientes = clientes.getWritableDatabase();

        c = dbClientes.rawQuery("SELECT DNI FROM Clientes",null);
        int numReg = c.getCount();
        int cont = 0;
        String [] valoresDNI = new String[numReg];
        System.out.println( "NUMERO DE REGITROS:" + numReg);

        while (c.moveToNext()){
            valoresDNI[cont] = c.getString(0);
            cont++;
            System.out.println(c.getString(0));
        }


        spNum = (Spinner) findViewById(R.id.spinnerDni);
        spNum.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, valoresDNI));
    }

    public void crearClick(View view){
        Toast.makeText(this, crearFactura()? "Factura añadida con exito": "No se pudo añadir la factura", Toast.LENGTH_LONG);
    }

    public boolean crearFactura(){


        EditText numT = (EditText) findViewById(R.id.numTxt);
        num = Integer.parseInt(numT.getText().toString());

        spNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dni = spNum.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        EditText conc = (EditText) findViewById(R.id.conceptoTxt);
        concepto = conc.getText().toString();

        EditText val = (EditText) findViewById(R.id.valorTxt);
        valor = Double.parseDouble(val.getText().toString());

        if (dbFacturas != null){

            if(!c.moveToFirst()){//Comprobamos si existe el registro
                dbFacturas.execSQL("INSERT INTO Facturas (num, dni, concepto, valor)" + "VALUES ("+num+", '"+dni+"','"+concepto+"', "+valor+")");
                return true;
            }
            else{
                Toast.makeText(this, "Ya existe el registro", Toast.LENGTH_LONG).show();
                return false;
            }

        }

        return false;
    }

    public void volverClick(View view){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }
}
