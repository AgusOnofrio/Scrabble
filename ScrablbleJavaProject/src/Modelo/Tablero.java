package Modelo;
import java.io.IOException;
import java.util.ArrayList;

import Modelo.Interfaces.Itablero;

public class Tablero implements Itablero{
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


    public Diccionario diccionario;

    //private boolean isFirstMove;
    public Tablero() throws IOException {
        casilleros = new Casillero[TAMANIO][TAMANIO];
  
        //setFirstMove(true);
        // Como el tablero es simetrico, y a fines de que no sea tan tedioso cargar los datos de cada casillero, 
        // va a recorrer el array de los casilleros primero de adelante para atras y luego de atras para adelante
        String[] fila;
        for (int i = 0; i < TAMANIO; i++) {
            fila = (i <= 7) ? diseñoTablero[i].split(",") : diseñoTablero[TAMANIO - i - 1].split(",");
            for (int j = 0; j < TAMANIO; j++) {
                switch (fila[j]) {
                    case "SIMPLE":
                    casilleros[i][j] = new Casillero(i,j,TipoEspecial.SIMPLE);
                        break;
                    case "DOBLE_LETRA":
                    casilleros[i][j] = new Casillero(i,j,TipoEspecial.DOBLE_LETRA);
                        break;
                    case "TRIPLE_LETRA":
                    casilleros[i][j] = new Casillero(i,j,TipoEspecial.TRIPLE_LETRA);
                        break;
                    case "DOBLE_PALABRA":
                    casilleros[i][j] = new Casillero(i,j,TipoEspecial.DOBLE_PALABRA);
                        break;
                    case "TRIPLE_PALABRA":
                    casilleros[i][j] = new Casillero(i,j,TipoEspecial.TRIPLE_PALABRA);
                        break;
                    default:
                    casilleros[i][j] = new Casillero(i,j,TipoEspecial.SIMPLE);
                        break;
                }
            }
        }

        this.diccionario= new Diccionario();


    }


    public Casillero[][] getCasilleros(){
        return casilleros;
    }

    public Casillero getCasillero(int fila,int columna){
        Casillero casillero = null;
        if(fila>0 && fila<Tablero.TAMANIO && columna>0 && columna<TAMANIO){
            casillero=this.casilleros[fila][columna];
        }
        return casillero;
    }


    public  boolean posicionValida(int fila, int columna) {
        boolean esValida=true;

        //Es valido si
        //Esta dentro del tablero
        if(columna < 0 || columna > Tablero.TAMANIO || fila < 0 || fila > Tablero.TAMANIO) esValida=false;

        //Si no esta ocupado
        if(this.casilleros[fila][columna].estaOcupado()) esValida=false;

        //Si tiene algun casillero al costado que este ocupado o es el del medio
        if(!existeFichaAdyacente(fila,columna)) esValida=false;

        if(esValida) this.casilleros[fila][columna].setDisponible(true);

        return esValida;
    }

    public boolean existeFichaAdyacente(int fila, int columna) {
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


    @Override
    public boolean posicionValida() {
        // TODO Auto-generated method stub
        return false;
    }



    public ArrayList<Casillero> casillerosDisponibles(){
        ArrayList<Casillero> casillerosDisponibles = new ArrayList<Casillero>();
        
        for (int i = 0; i < Tablero.TAMANIO; i++) {
            for (int j = 0; j < Tablero.TAMANIO; j++) {
                if(this.posicionValida(i, j) || (i==7 && j==7 && tableroVacio())) casillerosDisponibles.add(this.casilleros[i][j]);
            }
        }

        return casillerosDisponibles;
    }

    
  






    
};
