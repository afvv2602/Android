package mi.app.formulario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.BlockingDeque;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText nombre;
    EditText fecha;
    RadioGroup sexo;
    CheckBox mayorEdad;
    Button enviar;
    private static final String TAG = "MyActivity";

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Ha habido un error con los datos", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Los datos son correctos", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.ano);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        sexo = findViewById(R.id.sexo);
        mayorEdad = findViewById(R.id.mayorEdad);
        mayorEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revisarClick(v);
            }
        });
        enviar = findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nb = (String) nombre.getText().toString();
                String fc = (String) fecha.getText().toString();
                String sx;
                switch (sexo.getCheckedRadioButtonId()) {
                    case R.id.mujer:
                        sx = "Mujer";
                        break;
                    case R.id.hombre:
                        sx = "Hombre";
                        break;
                    default:
                        sx = "Helicoptero de combate X200";
                        break;
                }
                if(nb.equals("") || fc.equals("") || sx.equals("") ){
                    crearDialogo3(v);
                }else{
                    String myD = revisarCheckBox(v);
                    Intent comunicacion = new Intent(v.getContext(), revisar.class);
                    comunicacion.putExtra("nombre", nb);
                    comunicacion.putExtra("fecha", fc);
                    comunicacion.putExtra("sexo", sx);
                    comunicacion.putExtra("mayorEdad", myD);
                    startActivityForResult(comunicacion,0);
                }
            }
        });

    }

    private void crearDialogo3(View v) {
        androidx.appcompat.app.AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Revisa que no haya ningun valor sin escribir");
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private boolean revisarClick(View v) {
        if (fecha.getText().toString().equals("")) {
            crearDialogo1(v);
            mayorEdad.setChecked(false);
            return false;
        } else {
            String fc = (String) fecha.getText().toString();
            String[] parts = fc.split("-");
            String part1 = parts[0]; // year
            int ano = Integer.parseInt(part1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = new Date();
            String fcA = dateFormat.format(date); // fecha actual
            String[] parts2 = fcA.split("-");
            String par2 = parts2[0];
            int anoA = Integer.parseInt(par2);
            int edad = anoA - ano;
            if (edad < 18) {
                crearDialogo2(v);
                mayorEdad.setChecked(false);
                return false;
            }else{
                return true;
            }
        }
    }

    private boolean revisarClick2(View v) {
        String fc = (String) fecha.getText().toString();
        String[] parts = fc.split("-");
        String part1 = parts[0]; // year
        int ano = Integer.parseInt(part1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fcA = dateFormat.format(date); // fecha actual
        String[] parts2 = fcA.split("-");
        String par2 = parts2[0];
        int anoA = Integer.parseInt(par2);
        int edad = anoA - ano;
        if (edad < 18) {
            return false;
        } else {
            return true;
        }
    }

    private String revisarCheckBox(View v) {
        if (revisarClick2(v)) {
            return "mayor de edad";
        } else {
            return "menor de edad";
        }
    }

    public void crearDialogo1(View view) {
        androidx.appcompat.app.AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Por favor introduce una fecha primero");
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    public void crearDialogo2(View view) {
        androidx.appcompat.app.AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Por favor no pulses este boton si eres menor de edad");
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private void showDatePicker() {
        Calendar calendar = new GregorianCalendar(Locale.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        fecha.setText(year + "-" + month + "-" + day);
    }
}
