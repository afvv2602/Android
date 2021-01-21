package mi.app.buscaminasv1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;

public class Tablero extends AppCompatActivity {

    private static int contadorBanderas;
    private static Button[][] buttons;
    private static int[][] tablero;
    private static boolean[][] pulsado;
    private static int[][] banderas;
    private static int minas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablero);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.pintar);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);
        tablero = new int[8][8];
        buttons = new Button[8][8];
        pulsado = new boolean[8][8];
        banderas = new int[8][8];
        final View marco = (View) findViewById(R.id.Tablero);
        Button volver = (Button) findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(), Jugar.class);
                startActivity(comunicacion);
            }
        });
        Button reiniciar = (Button) findViewById(R.id.salir);

        marco.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                marco.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (marco.getHeight() != 0) {
                    controladorJuego();
                    mostrarArray();
                }
            }
        });
    }

    private static void mostrarArray() {
        String tableroV = "      Buscaminas \n";
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                int valorA = tablero[i][j];
                if (j == 7) {
                    tableroV += "[" + valorA + "] \n";
                } else {
                    tableroV += "[" + valorA + "]";
                }
            }
        }
        Log.d(TAG, "mostrarArray: " + tableroV);
    }

    private void controladorJuego() {
        generarMinas();
        generarJuego();
        rellenarBooleans();
        generarBotones();
    }

    private void generarMinas() {
        View view = (View) findViewById(R.id.Tablero);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.pintar);
        minas = 8;
        contadorBanderas = minas;
        int ancho = view.getWidth();
        int alto = view.getHeight();
        for (int i = 0; i < minas; i++) {
            int fila = (int) (Math.random() * 8);
            int columna = (int) (Math.random() * 8);
            int revisar = tablero[fila][columna];
            if (revisar == 0) {
                final ImageView imagen = new ImageView(this);
                imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                imagen.setImageResource(R.drawable.bomba);
                layout1.addView(imagen);
                tablero[fila][columna] = 9;
            } else {
                i--;
            }
        }
    }

    private void generarJuego() {
        /*
         * [izqA][midA][dchaA] [izqM][fila,columna][dchaM] [izqB][midB][dchaB]
         */
        View view = (View) findViewById(R.id.Tablero);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.pintar);
        int ancho = view.getWidth();
        int alto = view.getHeight();
        ImageView imagen;
        int contadorBombas = 0;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero.length; columna++) {
                contadorBombas = 0;
                //Esquina izquierda
                if (fila == 0 && columna == 0 && tablero[fila][columna] == 0) {
                    contadorBombas += revisardchaM(fila, columna);
                    contadorBombas += revisardchaB(fila, columna);
                    contadorBombas += revisarmidB(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                    }
                }
                // Lateral izquierdo
                else if (fila > 0 && fila < tablero.length - 1 && columna == 0 && tablero[fila][columna] == 0) {
                    contadorBombas += revisarmidA(fila, columna);
                    contadorBombas += revisarmidB(fila, columna);
                    contadorBombas += revisardchaA(fila, columna);
                    contadorBombas += revisardchaM(fila, columna);
                    contadorBombas += revisardchaB(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                        case 4:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cuatro);
                            layout1.addView(imagen);
                            break;
                        case 5:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cinco);
                            layout1.addView(imagen);
                            break;
                    }
                }
                // Esquina izquierda abajo
                else if (fila == tablero.length - 1 && columna == 0 && tablero[fila][columna] == 0) {
                    contadorBombas += revisarmidA(fila, columna);
                    contadorBombas += revisardchaA(fila, columna);
                    contadorBombas += revisardchaM(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;

                    }
                }
                // Parte de abajo
                else if (fila == tablero.length - 1 && columna > 0 && columna < tablero.length - 1 && tablero[fila][columna] == 0) {
                    contadorBombas += revisarizqA(fila, columna);
                    contadorBombas += revisarizqM(fila, columna);
                    contadorBombas += revisarmidA(fila, columna);
                    contadorBombas += revisardchaA(fila, columna);
                    contadorBombas += revisardchaM(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                        case 4:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cuatro);
                            layout1.addView(imagen);
                            break;
                        case 5:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cinco);
                            layout1.addView(imagen);
                            break;
                    }
                }
                // Esquina derecha Abajo
                else if (fila == tablero.length - 1 && columna == tablero.length - 1 && tablero[fila][columna] == 0) {
                    contadorBombas += revisarizqA(fila, columna);
                    contadorBombas += revisarizqM(fila, columna);
                    contadorBombas += revisarmidA(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                    }
                }
                // Lateral derecho
                else if (fila < tablero.length - 1 && fila > 0 && columna == tablero.length - 1 && tablero[fila][columna] == 0) {
                    contadorBombas += revisarizqA(fila, columna);
                    contadorBombas += revisarizqM(fila, columna);
                    contadorBombas += revisarizqB(fila, columna);
                    contadorBombas += revisarmidA(fila, columna);
                    contadorBombas += revisarmidB(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                        case 4:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cuatro);
                            layout1.addView(imagen);
                            break;
                        case 5:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cinco);
                            layout1.addView(imagen);
                            break;
                    }
                }
                // Esquina Derecha Arriba
                else if (fila == 0 && columna == tablero.length - 1 && tablero[fila][columna] == 0) {
                    contadorBombas += revisarizqM(fila, columna);
                    contadorBombas += revisarizqB(fila, columna);
                    contadorBombas += revisarmidB(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                    }
                }
                // Parte de arriba
                else if (fila == 0 && columna < tablero.length - 1 && columna > 0 && tablero[fila][columna] == 0) {
                    contadorBombas += revisarizqM(fila, columna);
                    contadorBombas += revisarizqB(fila, columna);
                    contadorBombas += revisarmidB(fila, columna);
                    contadorBombas += revisardchaM(fila, columna);
                    contadorBombas += revisardchaB(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                        case 4:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cuatro);
                            layout1.addView(imagen);
                            break;
                        case 5:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cinco);
                            layout1.addView(imagen);
                            break;
                    }
                    // Resto
                }
                //Revisar que no pinta sobre una bomba
                else if (tablero[fila][columna] == 9) {
                } else {
                    contadorBombas += revisarizqA(fila, columna);
                    contadorBombas += revisarizqM(fila, columna);
                    contadorBombas += revisarizqB(fila, columna);
                    contadorBombas += revisarmidA(fila, columna);
                    contadorBombas += revisarmidB(fila, columna);
                    contadorBombas += revisardchaA(fila, columna);
                    contadorBombas += revisardchaM(fila, columna);
                    contadorBombas += revisardchaB(fila, columna);
                    tablero[fila][columna] = contadorBombas;
                    switch (contadorBombas) {
                        case 0:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            int random = (int) (Math.random() * 4 + 1);
                            switch (random) {
                                case 0:
                                    imagen.setImageResource(R.drawable.vacio1);
                                    break;
                                case 1:
                                    imagen.setImageResource(R.drawable.vacio2);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.vacio3);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.vacio4);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.vacio5);
                                    break;
                            }
                            layout1.addView(imagen);
                            break;
                        case 1:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.uno);
                            layout1.addView(imagen);
                            break;
                        case 2:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.dos);
                            layout1.addView(imagen);
                            break;
                        case 3:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.tres);
                            layout1.addView(imagen);
                            break;
                        case 4:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cuatro);
                            layout1.addView(imagen);
                            break;
                        case 5:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.cinco);
                            layout1.addView(imagen);
                            break;
                        case 6:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.seis);
                            layout1.addView(imagen);
                            break;
                        case 7:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.siete);
                            layout1.addView(imagen);
                            break;
                        case 8:
                            imagen = new ImageView(this);
                            imagen.animate().x(alto / 8 * columna).y(ancho / 8 * fila).setDuration(0).start();
                            imagen.setLayoutParams(new RelativeLayout.LayoutParams((ancho / 8), (ancho / 8)));
                            imagen.setImageResource(R.drawable.ocho);
                            layout1.addView(imagen);
                            break;
                    }
                }
            }
        }
    }

    private void rellenarBooleans() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                pulsado[i][j] = false;
            }
        }
    }

    private void generarBotones() {
        View view = (View) findViewById(R.id.Tablero);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.pintar);
        int ancho = view.getWidth();
        int alto = view.getHeight();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final Button boton = new Button(this);
                boton.animate().x(alto / 8 * j).y(ancho / 8 * i).setDuration(0).start();
                boton.setLayoutParams(new RelativeLayout.LayoutParams(ancho / 8, alto / 8));
                boton.setText("");
                int random = (int) (Math.random() * 4 + 1);
                switch (random) {
                    case 0:
                        boton.setBackgroundResource(R.drawable.boton_rosa_ig);
                        break;
                    case 1:
                        boton.setBackgroundResource(R.drawable.boton_azul_ig);
                        break;
                    case 2:
                        boton.setBackgroundResource(R.drawable.boton_rojo_ig);
                        break;
                    case 3:
                        boton.setBackgroundResource(R.drawable.boton_verde_ig);
                        break;
                    case 4:
                        boton.setBackgroundResource(R.drawable.boton_morado_ig);
                        break;
                }
                boton.setTag(i + "#" + j + "#" + tablero[i][j] + "#");
                boton.setOnClickListener(comprobarClick);
                boton.setOnLongClickListener(colocarBandera);
                buttons[i][j] = boton;
                layout1.addView(boton);
            }

        }
    }

    //colocar banderas
    private View.OnLongClickListener colocarBandera = new View.OnLongClickListener() {
        public boolean onLongClick(View v) {
            Button boton = (Button) v;
            Object ObjBTN = boton.getTag();
            String pos = ObjBTN.toString();
            String[] partes = pos.split("#");
            String fil = partes[0];
            String col = partes[1];
            int fila = Integer.parseInt(fil);
            int columna = Integer.parseInt(col);
            if(banderas[fila][columna] !=10 && contadorBanderas!=0){
                banderas[fila][columna] = 10;
                boton.setBackgroundResource(R.drawable.bandera);
                contadorBanderas--;
                revisarGanador();
                return true;
            }if(banderas[fila][columna] == 10){
                banderas[fila][columna] = 0 ;
                contadorBanderas++;
                int random = (int) (Math.random() * 4 + 1);
                switch (random) {
                    case 0:
                        boton.setBackgroundResource(R.drawable.boton_rosa_ig);
                        break;
                    case 1:
                        boton.setBackgroundResource(R.drawable.boton_azul_ig);
                        break;
                    case 2:
                        boton.setBackgroundResource(R.drawable.boton_rojo_ig);
                        break;
                    case 3:
                        boton.setBackgroundResource(R.drawable.boton_verde_ig);
                        break;
                    case 4:
                        boton.setBackgroundResource(R.drawable.boton_morado_ig);
                        break;
                }
                return false;
            }
            return false;
        }
    };

    private void revisarGanador(){
        int ganador=0;
        for (int i = 0;i<tablero.length;i++){
            for(int j = 0;j<tablero.length;j++){
                if(tablero[i][j]==9 && banderas[i][j]==10){
                    ganador++;
                    if(ganador == minas){
                        Toast toast = Toast.makeText(getApplicationContext(), "Ganaste", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        }
    }

    //Revisar botones
    private View.OnClickListener comprobarClick = new View.OnClickListener() {
        public void onClick(View v) {
            Button boton = (Button) v;
            Object ObjBTN = boton.getTag();
            String pos = ObjBTN.toString();
            String[] partes = pos.split("#");
            String fil = partes[0];
            String col = partes[1];
            String cas = partes[2];
            int fila = Integer.parseInt(fil);
            int columna = Integer.parseInt(col);
            int casilla = Integer.parseInt(cas);
            pulsado[fila][columna]=true;
            if(casilla == 9){
                Toast toast = Toast.makeText(getApplicationContext(), "Perdiste", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
            revisar();
            expandir(fila, columna);
        }
    };

    private void revisar(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (pulsado[i][j]) {
                    expandir(i,j);
                    Button boton = buttons[i][j];
                    boton.setEnabled(false);
                    boton.setVisibility(View.INVISIBLE);
                }
            }
        }
        for(int i = 0; i< tablero.length;i++){
            for(int j = 0; j<tablero.length;j++){
                if (buttons[i][j].isEnabled() && pulsado[i][j]){
                    revisar();
                }
            }
        }
    }

    private void expandir(int fila, int columna) {
        if (tablero[fila][columna] == 0 && pulsado[fila][columna] == true) {
            try {
                pulsado[fila-1][columna-1] = true;
            } catch (Exception e) {
            }
            try {
                pulsado[fila][columna-1] = true;
            } catch (Exception e) {
            }
            try {
                pulsado[fila+1][columna-1] = true;
            } catch (Exception e) {
            }
            try {
                pulsado[fila-1][columna] = true;
            } catch (Exception e) {
            }
            try {
                pulsado[fila+1][columna] = true;
            } catch (Exception e) {
            }
            try {
                pulsado[fila-1][columna+1] = true;
            } catch (Exception e) {
            }
            try {
                pulsado[fila][columna+1] = true;
            } catch (Exception e) {
            }
            try {
                pulsado[fila+1][columna+1] = true;
            } catch (Exception e) {
            }
        }
    }

    //Revisar al crear el tablero

    private static int revisarizqA(int fila, int columna) {
        if (tablero[fila - 1][columna - 1] == 9) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int revisarizqM(int fila, int columna) {
        if (tablero[fila][columna - 1] == 9) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int revisarizqB(int fila, int columna) {
        if (tablero[fila + 1][columna - 1] == 9) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int revisarmidA(int fila, int columna) {
        if (tablero[fila - 1][columna] == 9) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int revisarmidB(int fila, int columna) {
        if (tablero[fila + 1][columna] == 9) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int revisardchaA(int fila, int columna) {
        if (tablero[fila - 1][columna + 1] == 9) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int revisardchaM(int fila, int columna) {
        if (tablero[fila][columna + 1] == 9) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int revisardchaB(int fila, int columna) {
        if (tablero[fila + 1][columna + 1] == 9) {
            return 1;
        } else {
            return 0;
        }
    }
}

class Lienzo extends View {
    public Lienzo(Context context) {
        super(context);
    }
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(0,0 ,0 );
        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();
        Log.d(TAG, "ancho: "+ancho+" alto "+alto);
        Paint bordeEx = new Paint();
        int random = (int) (Math.random() * 3 + 1);
        bordeEx.setStrokeWidth(30);
        switch (random) {
            case 0:
                bordeEx.setColor(Color.rgb(255, 20, 147));
                break;
            case 1:
                bordeEx.setColor(Color.rgb(254, 76, 64));
                break;
            case 2:
                bordeEx.setColor(Color.rgb(76, 64, 254));
                break;
            case 3:
                bordeEx.setColor(Color.rgb(191, 255, 0));
                break;
            default:
                bordeEx.setColor(Color.rgb(0, 0, 0));
                break;
        }
        canvas.drawLine(0, 0, ancho, 0, bordeEx);
        canvas.drawLine(0, 0, 0, alto, bordeEx);
        canvas.drawLine(ancho, 0, ancho, alto, bordeEx);
        canvas.drawLine(0, alto, ancho, alto, bordeEx);
        Paint tablero = new Paint();
        switch (random) {
            case 0:
                tablero.setColor(Color.rgb(255, 20, 147));
                break;
            case 1:
                tablero.setColor(Color.rgb(254, 76, 64));
                break;
            case 2:
                tablero.setColor(Color.rgb(76, 64, 254));
                break;
            case 3:
                tablero.setColor(Color.rgb(191, 255, 0));
                break;
            default:
                tablero.setColor(Color.rgb(0, 0, 0));
                break;
        }
        //pintamos las celdas
        tablero.setStrokeWidth(15);
        for (int i = 0; i < 8; i++) { //verticales
            canvas.drawLine(ancho / 8 * i, 0, ancho / 8 * i, alto, tablero); //linea
        }

        for (int i = 0; i < 8; i++) { //horizontales
            canvas.drawLine(0, ancho / 8 * i, ancho, ancho / 8 * i, tablero); //linea
        }
    }
}
