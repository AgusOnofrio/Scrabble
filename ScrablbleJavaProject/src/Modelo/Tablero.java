package Modelo;
import java.util.ArrayList;

public class Tablero {
    public static final Integer TAMANIO = 15;

    public static final String[] diseñoTablero = {
        "TRIPLE_PALABRA,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,SIMPLE,TRIPLE_PALABRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,TRIPLE_PALABRA",
        "SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE,SIMPLE,TRIPLE_LETRA,SIMPLE,SIMPLE,SIMPLE,TRIPLE_LETRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE",
        "SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE",
        "DOBLE_LETRA,SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE,DOBLE_LETRA",
        "SIMPLE,SIMPLE,SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE,SIMPLE,SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE,SIMPLE,SIMPLE",
        "SIMPLE,TRIPLE_LETRA,SIMPLE,SIMPLE,SIMPLE,TRIPLE_LETRA,SIMPLE,SIMPLE,SIMPLE,TRIPLE_LETRA,SIMPLE,SIMPLE,SIMPLE,TRIPLE_LETRA,SIMPLE",
        "SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE",
        "TRIPLE_PALABRA,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_PALABRA,SIMPLE,SIMPLE,SIMPLE,DOBLE_LETRA,SIMPLE,SIMPLE,TRIPLE_PALABRA",
    };


    private final Casillero[][] casilleros;

    //private boolean isFirstMove;
    public Tablero() {
        casilleros = new Casillero[TAMANIO][TAMANIO];
        //setFirstMove(true);
        // Como el tablero es simetrico, y a fines de que no sea tan tedioso cargar los datos de cada casillero, 
        // va a recorrer el array de los casilleros primero de adelante para atras y luego de atras para adelante
        String[] fila;
        for (int i = 0; i < TAMANIO; i++) {
            fila = (i <= 7) ? diseñoTablero[i].split(",") : diseñoTablero[TAMANIO - i - 1].split(",");
           System.out.println();
            for (int j = 0; j < TAMANIO; j++) {
                System.out.printf(fila[j]+"\t");
                switch (fila[j]) {
                    case "SIMPLE":
                    casilleros[i][j] = new Casillero(TipoEspecial.SIMPLE);
                        break;
                    case "DOBLE_LETRA":
                    casilleros[i][j] = new Casillero(TipoEspecial.DOBLE_LETRA);
                        break;
                    case "TRIPLE_LETRA":
                    casilleros[i][j] = new Casillero(TipoEspecial.TRIPLE_LETRA);
                        break;
                    case "DOBLE_PALABRA":
                    casilleros[i][j] = new Casillero(TipoEspecial.DOBLE_PALABRA);
                        break;
                    case "TRIPLE_PALABRA":
                    casilleros[i][j] = new Casillero(TipoEspecial.TRIPLE_PALABRA);
                        break;
                    default:
                    casilleros[i][j] = new Casillero(TipoEspecial.SIMPLE);
                        break;
                }
            }
        }
    }


    public Casillero[][] getCasilleros(){
        return casilleros;
    }

    public  boolean posicionValida(int columna, int fila) {
        boolean esValida=true;

        //Es valido si
        //Esta dentro del tablero
        if(columna < 0 || columna > Tablero.TAMANIO || fila < 0 || fila > Tablero.TAMANIO) esValida=false;

        //Si no esta ocupado
        if(this.casilleros[fila][columna].estaOcupado()) esValida=false;

        //Si tiene algun casillero al costado que este ocupado
        if(!existeFichaAdyacente(fila,columna)) esValida=false;

        if(esValida) this.casilleros[fila][columna].setDisponible(true);

        return esValida;
    }

    private boolean existeFichaAdyacente(int fila, int columna) {
        boolean hayAlgunaFicha=false;

        //me fijo si hay una arriba
        if((fila-1 >= 0) && this.casilleros[fila-1][columna].estaOcupado()) hayAlgunaFicha=true;

        //me fijo si hay una a la izquierda
        if((columna-1 >= 0) && this.casilleros[fila][columna-1].estaOcupado()) hayAlgunaFicha=true;
        
        //me fijo si hay una a la derecha
        if((columna+1 < TAMANIO) && this.casilleros[fila][columna+1].estaOcupado()) hayAlgunaFicha=true;
        
        //me fijo si hay una a la izquierda
        if((fila+1 < TAMANIO) && this.casilleros[fila+1][columna].estaOcupado()) hayAlgunaFicha=true;


        return hayAlgunaFicha;
    }


    public boolean tableroVacio() {
        boolean vacio=true;
        for (int i = 0; i < TAMANIO; i++) 
            for (int j = 0; j < TAMANIO; j++) 
                if (casilleros[i][j].estaOcupado())  vacio= false;
        
        return vacio;
    }
    
  






    
};
