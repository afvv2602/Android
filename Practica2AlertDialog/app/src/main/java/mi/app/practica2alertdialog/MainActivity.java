package mi.app.practica2alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Para poder crearlo en el on create hay que coger MainActivity.this en vez de getAplicationContext
        /*AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Prueba");
        alert.setTitle("gfgf");
        Toast.makeText(getApplicationContext(), "texto de ejemplo", Toast.LENGTH_SHORT).show();
        alert.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
*/

    }

    public void crearDialogo(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Prueba");
        alert.setTitle("gfgf");
        Toast.makeText(getApplicationContext(), "texto de ejemplo", Toast.LENGTH_SHORT).show();
        alert.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
    