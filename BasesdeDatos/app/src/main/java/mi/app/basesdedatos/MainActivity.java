package mi.app.basesdedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void newClienteClick(View view){
        Intent comunicacion = new Intent(this, formCliente.class);
        startActivity(comunicacion);

    }

    public void newFacturaClick(View view){
        Intent comunicacion = new Intent(this, formFactura.class);
        startActivity(comunicacion);

    }

    public void consFacturaClick(View view){
        Intent comunicacion = new Intent(this, ConsultarFacturas.class);
        startActivity(comunicacion);

    }

}