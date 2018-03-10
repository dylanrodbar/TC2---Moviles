package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 5/3/2018.
 */

public class PiezaT extends Pieza {

    PiezaT(){
        super();
        bloque1 = new Bloque("Rosado");
        bloque2 = new Bloque("Rosado");
        bloque3 = new Bloque("Rosado");
        bloque4 = new Bloque("Rosado");
        tipoPieza = 6;
    }
    @Override
    public void asignarValoresBloques() {
        tipoPiezas[0].annadirPosiciones(0, 3, 0);
        tipoPiezas[0].annadirPosiciones(0, 4, 1);
        tipoPiezas[0].annadirPosiciones(0, 5, 2);
        tipoPiezas[0].annadirPosiciones(1, 4, 3);

        tipoPiezas[1].annadirPosiciones(0, 4, 0);
        tipoPiezas[1].annadirPosiciones(1, 4, 1);
        tipoPiezas[1].annadirPosiciones(2, 4, 2);
        tipoPiezas[1].annadirPosiciones(1, 3, 3);

        tipoPiezas[2].annadirPosiciones(1, 5, 0);
        tipoPiezas[2].annadirPosiciones(1, 4, 1);
        tipoPiezas[2].annadirPosiciones(1, 3, 2);
        tipoPiezas[2].annadirPosiciones(0, 3, 3);

        tipoPiezas[3].annadirPosiciones(2, 4, 0);
        tipoPiezas[3].annadirPosiciones(1, 4, 1);
        tipoPiezas[3].annadirPosiciones(0, 4, 2);
        tipoPiezas[3].annadirPosiciones(1, 5, 3);
    }


}
