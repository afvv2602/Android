package mi.app.banderas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button espana = (Button) findViewById(R.id.espana);
        final Button extremadura = (Button) findViewById(R.id.extremadura);
        final Button colombia = (Button) findViewById(R.id.colombia);
        final Button polonia = (Button) findViewById(R.id.polonia);
        final Button portugal = (Button) findViewById(R.id.portugal);

        espana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), espana.class);
                startActivity(comunicacion);
            }
        });
        extremadura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), extremadura.class);
                startActivity(comunicacion);
            }
        });
        colombia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), colombia.class);
                startActivity(comunicacion);
            }
        });
        polonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), polonia.class);
                startActivity(comunicacion);
            }
        });
        portugal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), portugal.class);
                startActivity(comunicacion);
            }
        });
    }
}