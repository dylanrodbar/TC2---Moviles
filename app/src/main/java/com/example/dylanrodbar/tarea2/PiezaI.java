package com.example.dylanrodbar.tarea2;

/**
 * Created by dylanrodbar on 5/3/2018.
 */

public class PiezaI extends Pieza {



    PiezaI(Bloque bloque1, Bloque bloque2, Bloque bloque3, Bloque bloque4){
        super();
    }
    PiezaI(){
        super();
        bloque1 = new Bloque("Amarillo");
        bloque2 = new Bloque("Amarillo");
        bloque3 = new Bloque("Amarillo");
        bloque4 = new Bloque("Amarillo");
        tipoPieza = 1;
    }
    @Override
    public void asignarValoresBloques() {
        tipoPiezas[0].annadirPosiciones(0, 4, 0);
        tipoPiezas[0].annadirPosiciones(1, 4, 1);
        tipoPiezas[0].annadirPosiciones(2, 4, 2);
        tipoPiezas[0].annadirPosiciones(3, 4, 3);

        tipoPiezas[1].annadirPosiciones(1, 6, 0);
        tipoPiezas[1].annadirPosiciones(1, 5, 1);
        tipoPiezas[1].annadirPosiciones(1, 4, 2);
        tipoPiezas[1].annadirPosiciones(1, 3, 3);

        tipoPiezas[2].annadirPosiciones(3, 4, 0);
        tipoPiezas[2].annadirPosiciones(2, 4, 1);
        tipoPiezas[2].annadirPosiciones(1, 4, 2);
        tipoPiezas[2].annadirPosiciones(0, 4, 3);

        tipoPiezas[3].annadirPosiciones(2, 3, 0);
        tipoPiezas[3].annadirPosiciones(2, 4, 1);
        tipoPiezas[3].annadirPosiciones(2, 5, 2);
        tipoPiezas[3].annadirPosiciones(2, 6, 3);
    }




}
