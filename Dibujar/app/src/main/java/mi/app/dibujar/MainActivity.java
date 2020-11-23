package mi.app.dibujar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        Pintado pintar = new Pintado(MainActivity.this);
        layout.addView(pintar);



    }

    class Pintado extends View {
        public Pintado(Context context) {
            super(context);
            pintado.setOnTouchListener((view, motionEvent) ->
                    {
                        corx = (int) motionEvent.getX();
                        cory = (int) motionEvent.getY();
                        pintado.invalidate();return true;}
            );

        }

        protected void onDraw(Canvas canvas) {
//          canvas.drawRGB(53,123,62);
  //        pintarLineas(canvas);
//          pintarCirculos(canvas);
 //         pintarOvalos(canvas);
 //         pintarPantalla(canvas);
 //         pintarTexto(canvas);
            pintarImagen(canvas);
        }

        private void pintarImagen(Canvas canvas) {
            canvas.drawRGB(0, 255, 0);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sasuke);
            canvas.drawBitmap(bmp, (0 - 250) / 2, (0 - 200) / 2, null);

        }

        private void pintarTexto(Canvas canvas) {
            Paint pincel = new Paint();
            canvas.drawRGB(0, 255, 0);
            pincel.setARGB(255, 0, 0, 0);
            pincel.setTextSize(100);
            Typeface tf = Typeface.create(Typeface.SERIF,Typeface.ITALIC);
            pincel.setTypeface(tf);
            canvas.drawText("Ejemplo pintado texto)", 0, 70,
                    pincel);

        }

        private void pintarPantalla(Canvas canvas) {
            Paint pincel = new Paint();
            canvas.drawRGB(0,255,0);
            pincel.setARGB(255,255,0,0);
            pincel.setStrokeWidth(4);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(corx, cory, 20, pincel);
        }


        private void pintarOvalos(Canvas canvas) {
            Paint pincel = new Paint();
            canvas.drawRGB(255, 255, 255);
            pincel.setARGB(255, 0, 0, 0);
            pincel.setStrokeWidth(5);
            pincel.setStyle(Paint.Style.STROKE);
            //El óvalo se pinta dentro del rectángulo, por lo tanto,pintaremos un óvalo dentro de todo el layout
            RectF rectangulo1 = new RectF(0, 0, 100, 200);
            canvas.drawOval(rectangulo1, pincel);

        }

        private void pintarCirculos(Canvas canvas) {
            Paint pincel = new Paint();
            canvas.drawRGB(200,0,150);
            pincel.setARGB(255,255,255,255);
            pincel.setStrokeWidth(4);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(200, 200, 20, pincel);

        }
        private void pintarLineas(Canvas canvas) {
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();
            Paint pincel = new Paint();
            canvas.drawRGB(0, 255, 0);
            pincel.setARGB(255, 0, 0, 0);
            canvas.drawLine(0, 30, ancho, 30, pincel);
            pincel.setStrokeWidth(4);//Ancho de la traza
            canvas.drawLine(0, 200, ancho, 200, pincel);
            canvas.drawLine(150, 100, ancho-150, 100, pincel);

        }

    }


}