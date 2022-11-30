package Modelo.Interfaces;

import java.util.ArrayList;
public interface IAtril {
    public void llenarAtril();
    public ArrayList<IFicha> getFichasAtril();
    public String getFichasAtrilString();    
    public IFicha sacarFichaDeAtril(int posicion);
    public IFicha sacarFichaDeAtril(IFicha fichaElegida);
    public void intercambiarFichas();
    public void ponerFicha(IFicha ficha);
}
