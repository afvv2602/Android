package mi.app.practica2radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup rg = (RadioGroup) findViewById((R.id.RG));
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int radioButtonID) {
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        switch (radioButtonID){
                            case R.id.rbM:
                                Toast.makeText(context, "Grande neo", duration).show();
                                break;
                            case R.id.rbI:
                                Toast.makeText(context, "All in", duration).show();
                                break;
                            case R.id.rbE:
                                Toast.makeText(context, "Un crack Matt", duration).show();
                                break;
                            case R.id.rbB:
                                Toast.makeText(context, "Pobre Harrison", duration).show();
                                break;
                        }
                    }
                });

            }
        });
    }
}