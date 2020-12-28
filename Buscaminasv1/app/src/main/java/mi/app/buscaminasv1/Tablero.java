package mi.app.buscaminasv1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;



public class Tablero extends AppCompatActivity {
    private Lienzo fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablero);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.Tablero);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);
        int minas = (int) (Math.random() * (10 - 5) + 5);
        int contador = 0;
        Button volver = (Button) findViewById(R.id.volver);
        Button reiniciar = (Button) findViewById(R.id.reiniciar);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),Jugar.class);
                startActivity(comunicacion);
            }
        });
        //Creacion de los botones y asignarlos a su posicion
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //Imagen de detras
                final ImageView imagen = new ImageView(this);
                imagen.animate().x(i * 115).y(j * 137+3).setDuration(0).start();
                imagen.setLayoutParams(new RelativeLayout.LayoutParams(132, 129));
                imagen.setId(contador);
                imagen.setImageResource(R.drawable.pink_bomb);
                layout1.addView(imagen);
                //Boton encima
//                final Button boton = new Button(this);
//                boton.animate().x(i * 115 ).y(j * 137).setDuration(0).start();
//                boton.setLayoutParams(new RelativeLayout.LayoutParams(115, 137));
//                boton.setText("");
//                int random =(int) (Math.random()*4+1);
//                switch(random){
//                    case 0: boton.setBackground(getResources().getDrawable(R.drawable.boton_rosa_ig));break;
//                    case 1: boton.setBackground(getResources().getDrawable(R.drawable.boton_azul_ig));break;
//                    case 2: boton.setBackground(getResources().getDrawable(R.drawable.boton_rojo_ig));break;
//                    case 3: boton.setBackground(getResources().getDrawable(R.drawable.boton_verde_ig));break;
//                    case 4: boton.setBackground(getResources().getDrawable(R.drawable.boton_morado_ig));break;
//                }
//                boton.setId(contador);
//                boton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boton.setVisibility(View.INVISIBLE);
//                        boton.setEnabled(false);
//                    }
//                });
//                layout1.addView(boton);
                contador++;
            }
        }
    }
}
class Lienzo extends View {

    public Lienzo(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        Paint bordeEx = new Paint();
        int random =(int) (Math.random()*3+1);
        bordeEx.setStrokeWidth(30);
        switch(random){
            case 0: bordeEx.setColor(Color.rgb(255, 20, 147));break;
            case 1: bordeEx.setColor(Color.rgb(254, 76, 64));break;
            case 2: bordeEx.setColor(Color.rgb(76, 64, 254));break;
            case 3: bordeEx.setColor(Color.rgb(191, 255, 0));break;
            default: bordeEx.setColor(Color.rgb(0,0,0));break;
        }
        canvas.drawLine(0, 0, ancho, 0, bordeEx);
        canvas.drawLine(0, 0, 0, alto, bordeEx);
        canvas.drawLine(ancho, 0, ancho, alto, bordeEx);
        canvas.drawLine(0, alto, ancho, alto, bordeEx);
        Paint tablero = new Paint();
        switch(random){
            case 0: tablero.setColor(Color.rgb(255, 20, 147));break;
            case 1: tablero.setColor(Color.rgb(254, 76, 64));break;
            case 2: tablero.setColor(Color.rgb(76, 64, 254));break;
            case 3: tablero.setColor(Color.rgb(191, 255, 0));break;
            default: tablero.setColor(Color.rgb(0,0,0));break;
        }
        tablero.setStrokeWidth(10);
        int cantLineas = 8;
        for (int fila = 0; fila < cantLineas; fila++) {
            canvas.drawLine(0, fila * 137, ancho, fila * 137,
                    tablero);
            canvas.drawLine(fila * 115, 0, fila * 115, alto, tablero);
        }
    }
}