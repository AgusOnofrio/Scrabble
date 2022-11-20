package Modelo;

public class Casillero {
    private final TipoEspecial tipo;
    private Ficha ficha=null;
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
    
    public Ficha getFicha(){
        return this.ficha;
    }

    

    public void ponerFicha(Ficha ficha){
        if (this.ficha ==null) this.ficha=ficha;
    }

    public TipoEspecial getTipoEspecial(){
        return this.tipo;
    }


    //Un casillero esta disponible cuando el casillero central o cuando tiene letras adyacentes
    public void setDisponible(boolean condicion){
        this.disponible=condicion;
    }


}
