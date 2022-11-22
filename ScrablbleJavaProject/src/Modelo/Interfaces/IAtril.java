package Modelo.Interfaces;

import java.util.ArrayList;

import Modelo.Ficha;

public interface IAtril {
    public void llenarAtril();
    public ArrayList<IFicha> getFichasAtril();
    public String getFichasAtrilString();    
    public IFicha sacarFichaDeAtril(int posicion);
    public void intercambiarFichas();
}
