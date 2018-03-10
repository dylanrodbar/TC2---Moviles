package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 5/3/2018.
 */

public class Bloque {
    private int x;
    private int y;
    private int tipo;

    public Bloque() {}

    public Bloque(int tipo) {

        this.tipo = tipo;
    }

    public Bloque(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void actualizarPosicion(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo){ this.tipo = tipo; }

}
