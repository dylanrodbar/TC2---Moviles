package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 7/3/2018.
 */

public class TipoPieza {
    private Posicion[] posiciones = new Posicion[4];

    TipoPieza() {

    }

    public void annadirPosiciones(int x, int y, int pos) {

        posiciones[pos] = new Posicion(x, y);
    }

    public Posicion[] getPosiciones() {
        return posiciones;
    }

}
