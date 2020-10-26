package mi.app.practica2verificacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast toast;
//
//        CheckBox lunes = (CheckBox) findViewById(R.id.lunes);
//        CheckBox martes = (CheckBox) findViewById(R.id.martes);
//         CheckBox miercoles = (CheckBox) findViewById(R.id.miercoles);
//        CheckBox jueves = (CheckBox) findViewById(R.id.jueves);
//        CheckBox viernes = (CheckBox) findViewById(R.id.viernes);
//        CheckBox sabado = (CheckBox) findViewById(R.id.sabado);
//        CheckBox domingo = (CheckBox) findViewById(R.id.domingo);
//
//        if (lunes.isChecked()){
//            toast = Toast.makeText(getApplicationContext(),"Lunes añadido",Toast.LENGTH_SHORT);
//            toast.show();
//        }if (martes.isChecked()){
//            toast = Toast.makeText(getApplicationContext(),"Martes añadido",Toast.LENGTH_SHORT);
//            toast.show();
//        }if (miercoles.isChecked()){
//            toast = Toast.makeText(getApplicationContext(),"Miercoles añadido",Toast.LENGTH_SHORT);
//            toast.show();
//        }if (jueves.isChecked()){
//            toast = Toast.makeText(getApplicationContext(),"Jueves añadido",Toast.LENGTH_SHORT);
//            toast.show();
//        }if(viernes.isChecked()){
//            toast = Toast.makeText(getApplicationContext(),"Viernes añadido",Toast.LENGTH_SHORT);
//            toast.show();
//        }if (sabado.isChecked()){
//            toast = Toast.makeText(getApplicationContext(),"Sabado añadido",Toast.LENGTH_SHORT);
//            toast.show();
//        }if (domingo.isChecked()){
//            toast = Toast.makeText(getApplicationContext(),"Domingo añadido",Toast.LENGTH_SHORT);
//            toast.show();
//            wtf.setVisibility(View.VISIBLE);
//        }else {
//            wtf.setVisibility(View.INVISIBLE);
//        }
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        TextView wtf = (TextView) findViewById(R.id.wtf);
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.lunes:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "Lunes añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
            else break;
            case R.id.martes:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "martes añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.miercoles:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "miercoles añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.jueves:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "jueves añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.viernes:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "viernes añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.sabado:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "sabado añadido", Toast.LENGTH_SHORT).show();
                    break;
                }
                else break;
            case R.id.domingo:
                if (checked) {
                    Toast.makeText(getApplicationContext(), "domingo añadido", Toast.LENGTH_SHORT).show();
                    wtf.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    wtf.setVisibility(View.INVISIBLE);
                    break;
                }
        }
    }
}