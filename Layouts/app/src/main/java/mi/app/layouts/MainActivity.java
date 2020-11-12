package mi.app.layouts;

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
        final Button contraint = (Button) findViewById(R.id.constraint);
        final Button frame = (Button) findViewById(R.id.frame);
        final Button linear = (Button) findViewById(R.id.linear);
        final Button relative = (Button) findViewById(R.id.relative);
        final Button table = (Button) findViewById(R.id.table);

        contraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), constraint.class);
                startActivity(comunicacion);
            }
        });
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), frame.class);
                startActivity(comunicacion);
            }
        });
        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), relative.class);
                startActivity(comunicacion);
            }
        });
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), table.class);
                startActivity(comunicacion);
            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), linear.class);
                startActivity(comunicacion);
            }
        });
    }
}
