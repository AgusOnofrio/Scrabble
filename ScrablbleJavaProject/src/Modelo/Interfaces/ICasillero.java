package Modelo.Interfaces;

import Modelo.TipoEspecial;

public interface ICasillero {
    public int getFila();

    public int getColumna();

    public boolean estaOcupado();
    
    public IFicha getFicha();

    public void ponerFicha(IFicha ficha);

    public TipoEspecial getTipoEspecial();

    //Un casillero esta disponible cuando el casillero central o cuando tiene letras adyacentes
    public void setDisponible(boolean condicion);
}
