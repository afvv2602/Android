package mi.app.basesdedatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class formCliente extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_cliente);

    }


    public void crearClick(View view){

        Toast.makeText(this, crearCliente()? "Cliente añadido con exito": "No se pudo añadir el cliente", Toast.LENGTH_LONG).show();


    }

    public boolean crearCliente(){
        int dni;
        String nombre, direccion, tlf;

        EditText id = (EditText) findViewById(R.id.dniTxt);
        dni = Integer.parseInt(id.getText().toString());

        EditText name = (EditText) findViewById(R.id.nameTxt);
        nombre = name.getText().toString();

        EditText dir = (EditText) findViewById(R.id.dirTxt);
        direccion = dir.getText().toString();

        EditText tel = (EditText) findViewById(R.id.tlfTxt);
        tlf = tel.getText().toString();


        CrearClientes clientes = new CrearClientes(this, "DBClientes", null, 1);

        //Abrimos clientes
        SQLiteDatabase dbClientes = clientes.getWritableDatabase();

        Cursor c = dbClientes.rawQuery("SELECT * FROM Clientes WHERE DNI = "+dni+"", null);//Guardamos el resultado de la wuery en un cursor

        if(dbClientes != null)
        {
            if(!c.moveToFirst()){//No existe el registro
                dbClientes.execSQL("INSERT INTO Clientes (DNI, nombre, direccion, tlf) " + "VALUES ("+dni+", '"+nombre+"', '"+direccion+"', '"+tlf+"')");
                return true;
            }
            else{
                Toast.makeText(this, "Ya existe el registro", Toast.LENGTH_SHORT).show();
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
