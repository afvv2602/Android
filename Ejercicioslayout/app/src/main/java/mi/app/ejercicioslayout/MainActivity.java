package mi.app.ejercicioslayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultado = (TextView) findViewById(R.id.res);

        final Button btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText numI = findViewById(R.id.num);
                int num = Integer.parseInt(numI.getText().toString());
                int numR = (int) Math.random() * 100 + 1;
                if (num == numR) {
                    resultado.setText("Has acertado");
                    resultado.setVisibility(View.VISIBLE);
                } else if (num > numR) {
                    resultado.setText("El numero introducido es  mas grande");
                    resultado.setVisibility(View.VISIBLE);
                } else if (num < numR) {
                    resultado.setText("El numero introducido es  mas pequeño");
                    resultado.setVisibility(View.VISIBLE);

                }
            }
    });
}
}