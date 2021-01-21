package mi.app.ciclodevidasaveinstance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.util.Log.i(TAG,
                "onCreate(" + savedInstanceState + ")");
    }
    protected void onSaveInstanceState(Bundle outInstance) {
        super.onSaveInstanceState(outInstance); android.util.Log.i(TAG,
                "onSaveInstanceState");
    }

}