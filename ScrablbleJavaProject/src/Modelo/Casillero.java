package Modelo;

import java.io.Serializable;

import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;

public class // A class that represents a square in a board game.
Casillero implements ICasillero,Serializable{
    private final TipoEspecial tipo;
    private IFicha ficha=null;
    private boolean disponible;
    private final int fila;
    private final int columna;
//TODO ver si necesito guardar la fila y la columna
  


    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Casillero(int fila,int columna){
        this.tipo=TipoEspecial.SIMPLE;
        this.ficha=null;
        this.disponible=false;
        this.fila=fila;
        this.columna=columna;

    }

    public Casillero(int fila, int columna, TipoEspecial tipo){
        this.tipo=tipo;
        this.ficha=null;
        this.fila=fila;
        this.columna=columna;

    }


    // public int calcularPuntajeLetra(){
    //     return 0;
    // }

    // public int multiplicaPalabra(){
    //     return 0;
    // }

    public boolean estaOcupado(){
        return this.ficha != null;
    }
    
    public IFicha getFicha(){
        return this.ficha;
    }

    

    public void ponerFicha(IFicha ficha){
        if (this.ficha ==null) this.ficha=ficha;
    }

    public TipoEspecial getTipoEspecial(){
        return this.tipo;
    }


    //Un casillero esta disponible cuando el casillero central o cuando tiene letras adyacentes
    public void setDisponible(boolean condicion){
        this.disponible=condicion;
    }

    public boolean getDisponible(){
        return this.disponible;
    }

    @Override
    public IFicha quitarFicha() {
        IFicha fichaQuitada= this.ficha;
        this.ficha=null;
        return fichaQuitada;
    }

}
