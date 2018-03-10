package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 5/3/2018.
 */

public class Bloque {
    private int x;
    private int y;
    private String color;
    private String colorHex;

    public Bloque() {}

    public Bloque(String color) {
        this.color = color;
    }

    public Bloque(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public void setColor(String color) {
        this.color = color;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public String getColorHex() {
        return colorHex;
    }

}
