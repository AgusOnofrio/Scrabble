package Modelo;

public class Casillero {
    private TipoEspecial tipo;
    private Ficha ficha;
    private boolean ocupado;

    public Casillero(){
        this.tipo=TipoEspecial.SIMPLE;
        this.ocupado=false;
        this.ficha=null;
    }

    public Casillero(TipoEspecial tipo){
        this.tipo=tipo;
        this.ocupado=false;
        this.ficha=null;
    }


    public int calcularPuntajeLetra(){
        return 0;
    }

    public int multiplicaPalabra(){
        return 0;
    }


}
