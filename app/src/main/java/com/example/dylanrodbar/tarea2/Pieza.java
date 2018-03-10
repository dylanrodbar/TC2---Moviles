package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 5/3/2018.
 */

public abstract class Pieza {

    protected Bloque bloque1;
    protected Bloque bloque2;
    protected Bloque bloque3;
    protected Bloque bloque4;
    protected int tipoPieza;
    protected int tipo; /*El tipo será la posición en la que puede estar la pieza*/
    protected TipoPieza[] tipoPiezas = new TipoPieza[4];


    Pieza(Bloque bloque1, Bloque bloque2, Bloque bloque3, Bloque bloque4) {
        this.bloque1 = new Bloque(bloque1.getX(), bloque1.getY(), bloque1.getColor());
        this.bloque2 = new Bloque(bloque2.getX(), bloque2.getY(), bloque2.getColor());
        this.bloque3 = new Bloque(bloque3.getX(), bloque3.getY(), bloque3.getColor());
        this.bloque4 = new Bloque(bloque4.getX(), bloque4.getY(), bloque4.getColor());
    }
    public Pieza(){
        tipo = 0;
        for (int i = 0; i < 4; i++){
            tipoPiezas[i] = new TipoPieza();
        }
    }

    /*Método que actualiza el valor y de la pieza en +1*/
    public void actualizarBloqueBajada(){
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                tipoPiezas[i].getPosiciones()[j].setX(1);

            }
        }
    }

    /*Asigna a los bloques las nuevas posiciones*/
    public void cambiarTipoBloque() {
        bloque1.setX(tipoPiezas[tipo].getPosiciones()[0].getX());
        bloque1.setY(tipoPiezas[tipo].getPosiciones()[0].getY());

        bloque2.setX(tipoPiezas[tipo].getPosiciones()[1].getX());
        bloque2.setY(tipoPiezas[tipo].getPosiciones()[1].getY());

        bloque3.setX(tipoPiezas[tipo].getPosiciones()[2].getX());
        bloque3.setY(tipoPiezas[tipo].getPosiciones()[2].getY());

        bloque4.setX(tipoPiezas[tipo].getPosiciones()[3].getX());
        bloque4.setY(tipoPiezas[tipo].getPosiciones()[3].getY());
    }

    public Bloque getBloque1(){
        return bloque1;
    }

    public Bloque getBloque2(){
        return bloque2;
    }

    public Bloque getBloque3(){
        return bloque3;
    }

    public Bloque getBloque4(){
        return bloque4;
    }



    public int getXBloque1() {
        return bloque1.getX();
    }

    public int getXBloque2() {
        return bloque2.getX();
    }

    public int getXBloque3() {
        return bloque3.getX();
    }

    public int getXBloque4() {
        return bloque4.getX();
    }

    public int getYBloque1() {
        return bloque1.getY();
    }

    public int getYBloque2() {
        return bloque2.getY();
    }

    public int getYBloque3() {
        return bloque3.getY();
    }

    public int getYBloque4() {
        return bloque4.getY();
    }

    public int getTipoPieza(){return tipoPieza;}



    /*Método que actualiza el valor x de la pieza en +1*/
    public void actualizarBloqueDerecha(int limite) {
        for(int i = 0; i < 4; i++) {
            if(!hayLimitePieza(limite, i)){
                for(int j = 0; j < 4; j++) {
                    tipoPiezas[i].getPosiciones()[j].setY(1);
                }

            }
        }
    }



    /*Si hay al menos una pieza en el límite, se retorna true*/
    public boolean hayLimitePieza(int limite, int fila){
        for(int i = 0; i < 4; i++) {
            if(tipoPiezas[fila].getPosiciones()[i].getY() == limite) return true;
        }
        return false;
    }


    /*Método que actualiza el valor y de la pieza en -1*/
    public void actualizarBloqueDerecha1(int limite) {
        for(int i = 0; i < 4; i++) {
            if(!hayLimitePieza(limite, i)){
                for(int j = 0; j < 4; j++) {
                    tipoPiezas[i].getPosiciones()[j].setY(1);
                }
            }

        }
    }

    /*Método que actualiza el valor y de la pieza en -1*/
    public void actualizarBloqueIzquierda(int limite) {
        for(int i = 0; i < 4; i++) {
            if(!hayLimitePieza(limite, i)){
                for(int j = 0; j < 4; j++) {
                    tipoPiezas[i].getPosiciones()[j].setY(-1);
                }
            }

        }
    }

    public void actualizarBloqueCambioTipoDerecha() {
        if(tipo == 3) tipo = 0;
        else tipo++;
    }

    public void actualizarBloqueCambioTipoIzquierda() {
        if(tipo == 0) tipo = 3;
        else tipo--;
    }


    public abstract void asignarValoresBloques();

}
