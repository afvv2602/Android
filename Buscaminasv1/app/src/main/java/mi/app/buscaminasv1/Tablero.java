package mi.app.buscaminasv1;

import android.view.View;

public class Tablero {

    public void crearTableroPrinpiante(View view){
        int tablero [][];
        int minas = (int)(Math.random() * (10 - 5) + 5);
        for (int i = 0 ; i<8;i++){
            for(int j = 0;j<8;i++){

            }
        }
    }
    public void crearTableroMedio(View view){
        int tablero [][];
        int minas = (int)(Math.random() * (40 - 20) + 20);
        for (int i = 0 ; i<16;i++){
            for(int j = 0;j<16;i++){

            }
        }
    }
    public void crearTableroExperto(View view){
        int tablero [][] ;
        int minas = (int)(Math.random() * (99 - 50) + 50);
        for (int i = 0 ; i<16;i++){
            for(int j = 0;j<30;i++){

            }
        }
    }
}
