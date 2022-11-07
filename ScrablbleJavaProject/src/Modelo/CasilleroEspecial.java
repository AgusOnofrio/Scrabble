package Modelo;
public class CasilleroEspecial extends Casillero {
        private TipoEspecial tipo;
        private Ficha ficha;
        private boolean ocupado;

    public CasilleroEspecial(TipoEspecial tipoEspecial) {
        this.tipo=tipoEspecial;
    }

    @Override
    public int calcularPuntajeLetra(){
        return 0;
    }
}
