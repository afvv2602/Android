package mi.app.pruebacrono;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hallianinc.opensource.timecounter.StopWatch;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    static StopWatch stopwatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.crono) ;
        Button empeza = findViewById(R.id.empezar);
        Button parar = findViewById(R.id.parar);
        Button reaundar = findViewById(R.id.reanudar);
        Button enviar = findViewById(R.id.recoger);
        stopwatch = new StopWatch(tv); // pass the textView you want to use as stop-watch, stop-watch you would update the textView to reflect elapsed time.

        empeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.start();
            }
        });
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.pause();
            }
        });
        reaundar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.resume();
            }
        });
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: tiempo"+stopwatch.getTime());
            }
        });


    }
}