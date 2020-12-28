package com.example.dibujar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;

//AppCompatActivity
public class MainActivity extends AppCompatActivity
{
    private int corx, cory;
    private Lienzo fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        corx = 100;
        cory = 100;
        RelativeLayout layout1 = findViewById(R.id.layout);
        Lienzo pintado = new Lienzo(this);
        pintado.setOnTouchListener((view, motionEvent) ->
        {
            corx = (int) motionEvent.getX();
            cory = (int) motionEvent.getY();
            pintado.invalidate();return true;}
        );
        //fondo.setOnTouchListener(this);
        layout1.addView(pintado);
        //getSupportActionBar().hide();
    }
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        corx = (int) event.getX();
//        cory = (int) event.getY();
//        fondo.invalidate();
//        return true;
//    }
    class Lienzo extends View
    {
        int ancho;
        int alto;
        Paint pincel;

        public Lienzo(Context context)
        {
            super(context);
        }

        protected void onDraw(Canvas canvas)
        {
            Log.d("DEPURACIÓN", "ha pintado");
            ancho = canvas.getWidth();
            alto = canvas.getHeight();
            pincel = new Paint();
            //Pintar pixel
            //canvas.drawPoint(ancho / 2, alto / 2, pincel1);

            //pintarLinea(canvas);
            //pintarLineas(canvas);
            //pincarRectangulo(canvas);
            //pintarRectangulos(canvas);
            //pintarCirculo(canvas);
            //pintarCirculos(canvas);
            //pintarOvalo(canvas);
            //pintarLetras(canvas);
            //pintarImagen(canvas);
            //pintarCirculo(canvas);
            //pintarEspiral(canvas);
            //pintarOvalos(canvas);
            Log.d("DEPURACIÓN", "ha salido");
        }
        protected void pintarEspiral(Canvas canvas)
        {
            canvas.drawRGB(255, 255, 255);
            pincel.setARGB(255, 0, 0, 0);
            pincel.setStyle(Paint.Style.STROKE);//Esto indica solamente pintar el perímetro
            int i,x,y;
            int origenX, origenY;
            double angle;
            origenX =ancho/2;
            origenY=alto/2;
/*            for (i=0; i< 5000; i=i+10)
            {
                angle = 0.1 * i;
                x=(int)((1+angle)*Math.cos(angle));
                y=(int)((1+angle)*Math.sin(angle));
                canvas.drawLine(origenX,origenY,origenX+x, origenY+y,pincel);
                origenX+=x;
                origenY+=y;
            }*/
            pincel.setStrokeWidth(4);
            for (i=0; i< 720; i=i+1)
            {
                angle = 0.1 * i;
                x=(int)((1+angle)*Math.cos(angle));
                y=(int)((1+angle)*Math.sin(angle));
                canvas.drawLine(origenX,origenY,origenX+x, origenY+y,pincel);
                origenX+=x;
                origenY+=y;
            }
        }

        protected void pintarLinea(Canvas canvas)
        {
            canvas.drawRGB(0, 255, 0);
            pincel.setARGB(255, 0, 0, 0);
            canvas.drawLine(0, 30, ancho, 30, pincel);
            pincel.setStrokeWidth(4);
            canvas.drawLine(0, 200, ancho, 200, pincel);
            canvas.drawLine(150, 100, ancho-150, 100, pincel);
        }

        protected void pintarLineas(Canvas canvas)
        {
            canvas.drawRGB(255, 255, 255);
            pincel.setARGB(255, 255, 0, 0);
            //canvas.drawLine(startX, startY, stopX, stopY, paint);
            //canvas.drawLine(70, 0, 70, alto, pincel);
            canvas.drawLine(0, 70, ancho, 70, pincel);
            pincel.setStrokeWidth(4);
            canvas.drawLine(0, 200, ancho,200, pincel);
            pincel.setStrokeWidth(5);
            canvas.drawLine(0, 500, ancho,500, pincel);
            pincel.setStrokeWidth(6);
            canvas.drawLine(0, 1200, ancho,1200, pincel);
            pincel.setARGB(255, 0, 0, 0);

            int cantLineas = alto / 30 - 2;
            pincel.setStrokeWidth(4);
            for (int fila = 0; fila < cantLineas; fila++)
            {
                //canvas.drawLine(0, fila * 30 + 60, ancho, fila * 30 + 60,pincel);
                canvas.drawLine(fila * 30 + 60, 0, fila * 30 + 60, alto,pincel);
            }
        }

        protected void pincarRectangulo(Canvas canvas)
        {
            canvas.drawRGB(255,255,255);// cambia el color de fondo
            pincel.setARGB(255,255,0,0);
            //columna y fila 10 todo el ancho y fila 40
            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(10,10,ancho-10,40, pincel);
            pincel.setStyle(Paint.Style.STROKE);//Esto indica solamente pintar el perímetro
            canvas.drawRect(10,60,ancho-10,90, pincel);

            pincel.setStrokeWidth(3);//Seguimos con el mismo esetilo de pintado, pero distinto grosor
            canvas.drawRect(10,110,ancho-10,140, pincel);
        }

        protected void pintarRectangulos(Canvas canvas)
        {
            canvas.drawRect(100,200,ancho-100,alto -200,pincel);
            pincel.setARGB(255,255,255,255);
            canvas.drawRect(200,300,ancho-200,alto -300,pincel);
            pincel.setARGB(255,0,0,0);
            pincel.setStyle(Paint.Style.STROKE);
            pincel.setStrokeWidth(3);
            canvas.drawRect(300,400,ancho-300,alto -400,pincel);
            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect((ancho/2)-200,(alto/2)-400,(ancho/2)+200,(alto/2)+400,pincel);
        }
        protected void pintarCirculo(Canvas canvas)
        {
            canvas.drawRGB(0,255,0);
            pincel.setARGB(255,255,0,0);
            pincel.setStrokeWidth(4);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(corx, cory, 20, pincel);
        }
        protected void pintarCirculos(Canvas canvas)
        {
            canvas.drawRGB(255, 255, 255);
            pincel.setARGB(255, 255, 0, 0);
            pincel.setStyle(Paint.Style.STROKE);
            pincel.setStrokeWidth(5);
            for (int f = 0; f < 10; f++)
            {
                canvas.drawCircle(ancho / 2, alto / 2, f * 50, pincel);
            }
        }

        protected void pintarOvalo(Canvas canvas)
        {
            canvas.drawRGB(255, 255, 255);

            pincel.setARGB(255, 0, 0, 0);
            pincel.setStrokeWidth(5);
            pincel.setStyle(Paint.Style.STROKE);
            //El óvalo se pinta dentro del rectángulo, por lo tanto,
            //pintaremos un óvalo dentro de todo el layout
            RectF rectangulo1 = new RectF(0, 0, ancho, alto);
            canvas.drawOval(rectangulo1, pincel);

            int menor = (ancho < alto)
                                    ? ancho
                                    : alto;

            pincel.setStyle(Paint.Style.FILL);
            pincel.setARGB(255, 255, 255, 0);
            canvas.drawCircle(ancho / 2, alto / 2, menor / 2, pincel);
        }

        protected void pintarOvalos(Canvas canvas)
        {
            canvas.drawRGB(255, 255, 255);

            pincel.setARGB(255, 0, 0, 0);
            pincel.setStrokeWidth(5);
            pincel.setStyle(Paint.Style.STROKE);
            //El óvalo se pinta dentro del rectángulo, por lo tanto,
            //pintaremos un óvalo dentro de todo el layout

            RectF rectangulo;

/*            rectangulo = new RectF(0, 0, ancho, alto);
            canvas.drawOval(rectangulo, pincel);
            pincel.setARGB(255,255,255,0);
            canvas.drawRect(ancho*0.25f,alto*0.25f,ancho*0.75f,alto*0.75f,pincel);
            pincel.setARGB(255,255,0,0);
            canvas.drawRect(ancho*0.152f,alto*0.140f,ancho*0.850f,alto*0.85f,pincel);*/
            Random rnd = new Random();
            float anchoL, anchoR, altoL, altoR;
            anchoL = ancho *0.152f;
            anchoR = ancho *0.850f;
            altoL = alto *0.140f;
            altoR = alto *0.855f;

            pincel.setARGB(255, 255,255,0);
            rectangulo = new RectF(0,0,ancho,alto);
            canvas.drawRect(rectangulo,pincel);
            pincel.setARGB(255,0,0,0);
            canvas.drawOval(rectangulo,pincel);
            pincel.setARGB(255, 255,0,0);
            rectangulo = new RectF(anchoL, altoL, anchoR, altoR);
            canvas.drawRect(rectangulo,pincel);
            pincel.setARGB(255,0,0,0);
            canvas.drawOval(rectangulo,pincel);
            pincel.setARGB(255, 255,0,0);
            rectangulo = new RectF(anchoL, altoL, anchoR, altoR);
            canvas.drawRect(rectangulo,pincel);
            pincel.setARGB(255,0,0,0);
            canvas.drawOval(rectangulo,pincel);
            anchoL = ancho *0.152f+ancho *0.075f;
            anchoR = ancho *0.850f-ancho *0.075f;
            altoL = alto *(0.140f*2);
            altoR = alto *0.85f-alto *0.135f;
            pincel.setARGB(255, 0,0,255);
            rectangulo = new RectF(anchoL, altoL, anchoR, altoR);
            canvas.drawRect(rectangulo,pincel);
            pincel.setARGB(255,0,0,0);
            canvas.drawOval(rectangulo,pincel);
            /*for (int i = 0; i < 4; ++i)
            {
                pincel.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                if (i == 0)
                    rectangulo = new RectF(0,0,ancho,alto);
                else
                {
                    rectangulo = new RectF(anchoL, altoL, anchoR, altoR);
                    anchoL = ancho *(0.152f*i);
                    anchoR = ancho *(1-(0.150f*i));
                    altoL = alto *(0.140f*i);
                    altoR = alto *(1-(0.150f*i));
                }
                canvas.drawRect(rectangulo,pincel);
                pincel.setARGB(255,0,0,0);
                canvas.drawOval(rectangulo,pincel);
            }*/
        }

        protected void pintarLetras(Canvas canvas)
        {
            canvas.drawRGB(0, 255, 0);
            Paint pincel1 = new Paint();
            pincel1.setARGB(255, 255, 0, 0);
            pincel1.setTextSize(30);
            pincel1.setTypeface(Typeface.SERIF);
            canvas.drawText("Hola Mundo (SERIF)", 0, 70, pincel1);
            pincel1.setTypeface(Typeface.SANS_SERIF);
            canvas.drawText("Hola Mundo (SANS SERIF)", 0, 100, pincel1);
            pincel1.setTypeface(Typeface.MONOSPACE);
            canvas.drawText("Hola Mundo (MONOSPACE)", 0, 140, pincel1);
            Typeface tf = Typeface.create(Typeface.SERIF, Typeface.ITALIC);
            pincel1.setTypeface(tf);
            canvas.drawText("Hola Mundo (SERIF ITALIC)", 0, 180, pincel1);
            tf = Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC);
            pincel1.setTypeface(tf);
            canvas.drawText("Hola Mundo (SERIF ITALIC BOLD)", 0, 220, pincel1);
        }

        protected void pintarImagen(Canvas canvas)
        {
            canvas.drawRGB(0, 0, 255);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_prueba_imagen);
            canvas.drawBitmap(bmp, (ancho - 250) / 2, (alto - 200) / 2, null);
        }
    }
}