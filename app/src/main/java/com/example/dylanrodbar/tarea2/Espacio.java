package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 5/3/2018.
 */

public class Espacio {
    private boolean ocupado;
    private int x;
    private int y;
    private int tipoPieza;

    private Bloque bloque;
    //private int tipo; //Si es pieza actual o pieza acomodada

    public Espacio() {
        this.ocupado = false;
    }

    public void setOcupado() {
        ocupado = !ocupado;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public Bloque getBloque() {
        return bloque;
    }

    //public int getTipo(){
    //    return tipo;
    //}

    //public void asignarEspacioOcupado(int x, int y, int tipoPieza, int tipo){
    //    this.tipo = tipo;
    //    this.x = x;
    //    this.y = y;
    //    this.tipoPieza = tipoPieza;
    //}

    //public void asignarEspacio(int x, int y, int tipoPieza, int tipo){
    //    this.tipo = tipo;
    //    this.x = x;
    //    this.y = y;
    //    this.tipoPieza = tipoPieza;
    //    setOcupado();
    //}

    //public void desasignarEspacio(){
    //    this.tipo = 0;
    //    this.x = -1;
    //    this.y = -1;
    //    this.tipoPieza = 0;
    //    setOcupado();
    //}



    public void asignarBloqueAEspacio(Bloque bloque) {
        this.bloque = bloque;
        //this.tipo = tipo;
        setOcupado();
    }

    public void asignar() {
        setOcupado();
    }

    public void desasignarBloqueAEspacio() {
        //this.bloque = null;
        //this.tipo = 0;
        setOcupado();
    }
}
