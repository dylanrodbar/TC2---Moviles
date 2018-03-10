package com.example.dylanrodbar.tarea2;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class TetrisJuego extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat gestureDetector;
    TableLayout tableLa;
    private final int largoMatriz = 22;
    private final int anchoMatriz = 10;
    private boolean perdido;
    private Espacio matrizBloques[][];
    private Pieza piezaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetris_juego);

        gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        annadirElementosGridLayout();

        this.perdido = false;
        this.matrizBloques = new Espacio[largoMatriz][anchoMatriz];

        iniciarMatrizJuego();

        this.piezaActual = new PiezaJ();
        this.piezaActual.asignarValoresBloques();
        this.piezaActual.cambiarTipoBloque();
        //generarPieza();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                Thread tr = Thread.currentThread();

                while (true) {

                    piezaBajada();

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dibujarMatrizLogica();
                            dibujarPieza();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                }

            }
        };


        Thread t = new Thread(r, "T1");
        t.start();

    }

    public void piezaBajada() {
        if (!hayColisionConFondoTablero()) {
            if (!hayColisionVertical()) {
                bajarPieza();
            } else {
                asignarPiezaAMatriz();
                hayLinea();
                generarPieza();
            }

        } else {
            asignarPiezaAMatriz();
            hayLinea();
            generarPieza();
        }
    }

    public void bajarPiezas(int linea) {
        for(int i = linea; i > 0; i--) {
            if(hayVacio(i + 1)) bajarPiezasAux(i);
            else break;
        }
    }

    public void bajarPiezasAux(int linea) {
        for(int i = 0; i < anchoMatriz; i++) {
            Bloque b;
            if(matrizBloques[linea][i].getOcupado()) {
                b = matrizBloques[linea][i].getBloque();
                matrizBloques[linea + 1][i].asignarBloqueAEspacio(b);
                matrizBloques[linea][i].desasignarBloqueAEspacio();
            }
        }
    }
    public void hayLinea() {
        for(int i = 0; i < largoMatriz; i++) {
            if(hayLineaAux(i)) {
                desasignarLinea(i);
                bajarPiezas(i-1);
                hayLinea();
            }
        }
    }

    public boolean hayLineaAux(int linea) {
        for(int i = 0; i < anchoMatriz; i++) {
            if(!matrizBloques[linea][i].getOcupado()) return false;
        }
        return true;
    }

    public boolean hayVacio(int linea) {
        for(int i = 0; i < anchoMatriz; i++) {
            if(matrizBloques[linea][i].getOcupado()) return false;
        }
        return true;
    }

    public void desasignarLinea(int linea) {
        for(int i = 0; i < anchoMatriz; i++) {
            matrizBloques[linea][i].desasignarBloqueAEspacio();
        }
    }

    public void annadirElementosGridLayout() {
        //gridLa = findViewById(R.id.gridLayoutTetris);
        tableLa = findViewById(R.id.tableLayoutTetris);

        for (int i = 0; i < 24; i++) {
            TableRow tRow = new TableRow(this);
            for (int j = 0; j < 12; j++) {
                ImageView imageV = new ImageView(this);
                //imageV.setLayoutParams(params);

                TableRow.LayoutParams params = new TableRow.LayoutParams();
                //GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height = 60;
                params.width = 60;
                //params.rowSpec = GridLayout.spec(i);
                //params.columnSpec = GridLayout.spec(j);
                imageV.setLayoutParams(params);
                //imageV.setImageResource(R.drawable.b);
                if (i == 0 || i == 23 || j == 0 || j == 11) {
                    imageV.setImageResource(R.drawable.b);
                } else {
                    imageV.setImageResource(R.drawable.n);
                }

                tRow.addView(imageV);
                //gridLa.addView(imageV);


            }
            tableLa.addView(tRow);

        }

        //TableRow r = (TableRow) table.getChildAt(2);
        //ImageView i = (ImageView) r.getChildAt(2);
        //i.setImageResource(R.drawable.b);


    }

    /*Métodos de la lógica del juego*/
    //Con este método, se inicializan los espacios de la matriz del juego
    public void iniciarMatrizJuego() {
        for (int i = 0; i < largoMatriz; i++) {
            for (int j = 0; j < anchoMatriz; j++) {
                matrizBloques[i][j] = new Espacio();
            }
        }
    }

    /*Con este método, se creará una nueva pieza para el juego*/
    public void generarPieza() {

        Random rand = new Random();

        int randomNum = rand.nextInt((7 - 1) + 1) + 1;

        /*Se genera una pieza de acuerdo a un número aleatorio*/
        if (randomNum == 1) piezaActual = new PiezaI();
        else if (randomNum == 2) piezaActual = new PiezaJ();
        else if (randomNum == 3) piezaActual = new PiezaL();
        else if (randomNum == 4) piezaActual = new PiezaO();
        else if (randomNum == 5) piezaActual = new PiezaS();
        else if (randomNum == 6) piezaActual = new PiezaT();
        else if (randomNum == 7) piezaActual = new PiezaZ();

        /*Se inicializan los valores para el tipo de pieza*/
        piezaActual.asignarValoresBloques();
        piezaActual.cambiarTipoBloque();

    }

    /*Este método se encargará de cambiar la fila actual de la pieza, sumándola en 1*/
    public void bajarPieza() {
        this.piezaActual.actualizarBloqueBajada();
        this.piezaActual.cambiarTipoBloque();

    }

    /*Este método se encargará de mover una pieza hacia la derecha*/
    public void moverPiezaDerecha() {
        if(!hayColisionHorizontalDerecha()) {
            this.piezaActual.actualizarBloqueDerecha(anchoMatriz - 1);
            this.piezaActual.cambiarTipoBloque();
        }

    }

    /*Este  método se encargará de mover una pieza hacia la izquierda*/
    public void moverPiezaIzquierda() {
        if(!hayColisionHorizontalIzquierda()) {
            this.piezaActual.actualizarBloqueIzquierda(0);
            this.piezaActual.cambiarTipoBloque();
        }

    }

    /*Este método se encargará de rotar hacia la derecha la  pieza*/
    public void cambiarRotacionDerechaPieza() {
        this.piezaActual.actualizarBloqueCambioTipoDerecha();
        this.piezaActual.cambiarTipoBloque();
    }

    /*Este método se encargará de rotar hacia la izquiera la pieza*/
    public void cambiarRotacionIzquierdaPieza() {
        this.piezaActual.actualizarBloqueCambioTipoIzquierda();
        this.piezaActual.cambiarTipoBloque();
    }

    /*Este método se encargará de evaluar si hay una colisión entre bloques hacia abajo*/
    public boolean hayColisionVertical() {
        int filaBloque1 = piezaActual.getXBloque1();
        int filaBloque2 = piezaActual.getXBloque2();
        int filaBloque3 = piezaActual.getXBloque3();
        int filaBloque4 = piezaActual.getXBloque4();

        int columnaBloque1 = piezaActual.getYBloque1();
        int columnaBloque2 = piezaActual.getYBloque2();
        int columnaBloque3 = piezaActual.getYBloque3();
        int columnaBloque4 = piezaActual.getYBloque4();

        if (matrizBloques[filaBloque1 + 1][columnaBloque1].getOcupado()) return true;
        else if (matrizBloques[filaBloque2 + 1][columnaBloque2].getOcupado()) return true;
        else if (matrizBloques[filaBloque3 + 1][columnaBloque3].getOcupado()) return true;
        else if (matrizBloques[filaBloque4 + 1][columnaBloque4].getOcupado()) return true;

        return false;
    }

    public boolean estaEnLimiteDerecha() {
        int columnaBloque1 = piezaActual.getYBloque1();
        int columnaBloque2 = piezaActual.getYBloque2();
        int columnaBloque3 = piezaActual.getYBloque3();
        int columnaBloque4 = piezaActual.getYBloque4();

        if (columnaBloque1 == anchoMatriz - 1 || columnaBloque2 == anchoMatriz - 1 || columnaBloque3 == anchoMatriz - 1 || columnaBloque4 == anchoMatriz - 1)
            return true;
        else return false;
    }

    public boolean estaEnLimiteIzquierda() {
        int columnaBloque1 = piezaActual.getYBloque1();
        int columnaBloque2 = piezaActual.getYBloque2();
        int columnaBloque3 = piezaActual.getYBloque3();
        int columnaBloque4 = piezaActual.getYBloque4();

        if(columnaBloque1 == 0 || columnaBloque2 == 0 || columnaBloque3 == 0 || columnaBloque4 == 0) return true;
        else return false;
    }


    /*Este método se encargará de evaluar si hay una colisión de bloques hacia derecha*/
    public boolean hayColisionHorizontalDerecha() {
        int filaBloque1 = piezaActual.getXBloque1();
        int filaBloque2 = piezaActual.getXBloque2();
        int filaBloque3 = piezaActual.getXBloque3();
        int filaBloque4 = piezaActual.getXBloque4();

        int columnaBloque1 = piezaActual.getYBloque1();
        int columnaBloque2 = piezaActual.getYBloque2();
        int columnaBloque3 = piezaActual.getYBloque3();
        int columnaBloque4 = piezaActual.getYBloque4();

        if(matrizBloques[filaBloque1][columnaBloque1 + 1].getOcupado()) return true;
        if(matrizBloques[filaBloque2][columnaBloque2 + 1].getOcupado()) return true;
        if(matrizBloques[filaBloque3][columnaBloque3 + 1].getOcupado()) return true;
        if(matrizBloques[filaBloque4][columnaBloque4 + 1].getOcupado()) return true;

        return false;
    }

    /*Este método se encargará de evaluar si hay una colisión de bloques hacia izquierda*/
    public boolean hayColisionHorizontalIzquierda() {
        int filaBloque1 = piezaActual.getXBloque1();
        int filaBloque2 = piezaActual.getXBloque2();
        int filaBloque3 = piezaActual.getXBloque3();
        int filaBloque4 = piezaActual.getXBloque4();

        int columnaBloque1 = piezaActual.getYBloque1();
        int columnaBloque2 = piezaActual.getYBloque2();
        int columnaBloque3 = piezaActual.getYBloque3();
        int columnaBloque4 = piezaActual.getYBloque4();

        if(matrizBloques[filaBloque1][columnaBloque1 - 1].getOcupado()) return true;
        if(matrizBloques[filaBloque2][columnaBloque2 - 1].getOcupado()) return true;
        if(matrizBloques[filaBloque3][columnaBloque3 - 1].getOcupado()) return true;
        if(matrizBloques[filaBloque4][columnaBloque4 - 1].getOcupado()) return true;

        return false;
    }

    /*Este método se encargará de evaluar si la pieza llegó al final del tablero*/
    public boolean hayColisionConFondoTablero() {
        int filaBloque1 = piezaActual.getXBloque1();
        int filaBloque2 = piezaActual.getXBloque2();
        int filaBloque3 = piezaActual.getXBloque3();
        int filaBloque4 = piezaActual.getXBloque4();

        if(filaBloque1 == largoMatriz -1 || filaBloque2 == largoMatriz - 1 || filaBloque3 == largoMatriz-1 || filaBloque4 == largoMatriz - 1) {
            return true;
        }
        return false;
    }

    /*Este método se encargará de asignar los bloques de una pieza a la matriz, una vez que haya colisionado*/
    public void asignarPiezaAMatriz() {
        int filaBloque1 = piezaActual.getXBloque1();
        int filaBloque2 = piezaActual.getXBloque2();
        int filaBloque3 = piezaActual.getXBloque3();
        int filaBloque4 = piezaActual.getXBloque4();

        int columnaBloque1 = piezaActual.getYBloque1();
        int columnaBloque2 = piezaActual.getYBloque2();
        int columnaBloque3 = piezaActual.getYBloque3();
        int columnaBloque4 = piezaActual.getYBloque4();

        matrizBloques[filaBloque1][columnaBloque1].asignarBloqueAEspacio(this.piezaActual.getBloque1());
        matrizBloques[filaBloque2][columnaBloque2].asignarBloqueAEspacio(this.piezaActual.getBloque2());
        matrizBloques[filaBloque3][columnaBloque3].asignarBloqueAEspacio(this.piezaActual.getBloque3());
        matrizBloques[filaBloque4][columnaBloque4].asignarBloqueAEspacio(this.piezaActual.getBloque4());
    }



    /*Métodos de cambio de interfaz del juego*/

    public void dibujarPieza() {
        int filaBloque1 = piezaActual.getXBloque1();
        int filaBloque2 = piezaActual.getXBloque2();
        int filaBloque3 = piezaActual.getXBloque3();
        int filaBloque4 = piezaActual.getXBloque4();

        int columnaBloque1 = piezaActual.getYBloque1();
        int columnaBloque2 = piezaActual.getYBloque2();
        int columnaBloque3 = piezaActual.getYBloque3();
        int columnaBloque4 = piezaActual.getYBloque4();

        TableRow r;
        ImageView im1;
        ImageView im2;
        ImageView im3;
        ImageView im4;

        r = (TableRow) tableLa.getChildAt(filaBloque1 + 1);
        im1 = (ImageView) r.getChildAt(columnaBloque1 + 1);


        r = (TableRow) tableLa.getChildAt(filaBloque2 + 1);
        im2 = (ImageView) r.getChildAt(columnaBloque2 + 1);

        r = (TableRow) tableLa.getChildAt(filaBloque3 + 1);
        im3 = (ImageView) r.getChildAt(columnaBloque3 + 1);

        r = (TableRow) tableLa.getChildAt(filaBloque4 + 1);
        im4 = (ImageView) r.getChildAt(columnaBloque4 + 1);

        int tipo = piezaActual.getTipoPieza();
        if(tipo == 1) {
            im1.setImageResource(R.drawable.piezai);
            im2.setImageResource(R.drawable.piezai);
            im3.setImageResource(R.drawable.piezai);
            im4.setImageResource(R.drawable.piezai);
        }
        else if(tipo == 2) {
            im1.setImageResource(R.drawable.piezaj);
            im2.setImageResource(R.drawable.piezaj);
            im3.setImageResource(R.drawable.piezaj);
            im4.setImageResource(R.drawable.piezaj);
        }
        else if(tipo == 3) {
            im1.setImageResource(R.drawable.piezal);
            im2.setImageResource(R.drawable.piezal);
            im3.setImageResource(R.drawable.piezal);
            im4.setImageResource(R.drawable.piezal);
        }
        else if(tipo == 4) {
            im1.setImageResource(R.drawable.piezao);
            im2.setImageResource(R.drawable.piezao);
            im3.setImageResource(R.drawable.piezao);
            im4.setImageResource(R.drawable.piezao);
        }
        else if(tipo == 5) {
            im1.setImageResource(R.drawable.piezas);
            im2.setImageResource(R.drawable.piezas);
            im3.setImageResource(R.drawable.piezas);
            im4.setImageResource(R.drawable.piezas);
        }
        else if(tipo == 6) {
            im1.setImageResource(R.drawable.piezat);
            im2.setImageResource(R.drawable.piezat);
            im3.setImageResource(R.drawable.piezat);
            im4.setImageResource(R.drawable.piezat);
        }
        else if(tipo == 7) {
            im1.setImageResource(R.drawable.piezaz);
            im2.setImageResource(R.drawable.piezaz);
            im3.setImageResource(R.drawable.piezaz);
            im4.setImageResource(R.drawable.piezaz);
        }
    }

    public void dibujarMatrizLogica() {
        for(int i = 0; i < largoMatriz; i++){
            for(int j = 0; j < anchoMatriz; j++){
                if(matrizBloques[i][j].getOcupado()) {
                    TableRow r = (TableRow) tableLa.getChildAt(i+1);
                    ImageView im = (ImageView) r.getChildAt(j+1);
                    int tipo = matrizBloques[i][j].getBloque().getTipo();
                    if(tipo == 1) im.setImageResource(R.drawable.piezai);
                    if(tipo == 2) im.setImageResource(R.drawable.piezaj);
                    if(tipo == 3) im.setImageResource(R.drawable.piezal);
                    if(tipo == 4) im.setImageResource(R.drawable.piezao);
                    if(tipo == 5) im.setImageResource(R.drawable.piezas);
                    if(tipo == 6) im.setImageResource(R.drawable.piezat);
                    if(tipo == 7) im.setImageResource(R.drawable.piezaz);

                }
                else{
                    TableRow r = (TableRow) tableLa.getChildAt(i+1);
                    ImageView im = (ImageView) r.getChildAt(j+1);
                    im.setImageResource(R.drawable.n);
                }
            }
        }
    }


    /*Manejo de gestos*/

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        float v1 = e.getRawX();
        float v2 = e.getRawY();

        if(v1 > 500){
            cambiarRotacionIzquierdaPieza();
        }
        else{
            cambiarRotacionDerechaPieza();
        }

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        float v1 = e.getRawX();
        float v2 = e.getRawY();

        if(v1 > 500){
            cambiarRotacionIzquierdaPieza();
        }
        else{
            cambiarRotacionDerechaPieza();
        }

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        float v1 = e.getRawX();
        float v2 = e.getRawY();

        if(v1 > 500){
            cambiarRotacionIzquierdaPieza();
        }
        else{
            cambiarRotacionDerechaPieza();
        }

        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {


    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float angle = (float) Math.toDegrees(Math.atan2(e1.getY() - e2.getY(), e2.getX() - e1.getX()));

        //Scroll right
        if (angle > -45 && angle <= 45) {
            if(!estaEnLimiteDerecha()) {
                moverPiezaDerecha();
            }
            dibujarMatrizLogica();
            dibujarPieza();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //Scroll left
        if (angle >= 135 && angle < 180 || angle < -135 && angle > -180) {
            if(!estaEnLimiteIzquierda()) {
                moverPiezaIzquierda();
            }
            dibujarMatrizLogica();
            dibujarPieza();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //Scroll down
        if (angle < -45 && angle >= -135) {

            return true;
        }

        //Scroll up
        if (angle > 45 && angle <= 135) {
            return true;
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {


    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
