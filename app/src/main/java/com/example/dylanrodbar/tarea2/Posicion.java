package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 7/3/2018.
 */

public class Posicion {
    private int x;
    private int y;


    Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {

        this.x+=x;
    }

    public void setY(int y) {
        this.y += y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
