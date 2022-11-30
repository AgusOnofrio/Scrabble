package Modelo.Interfaces;

import java.util.ArrayList;


public interface Itablero{
    
    public boolean tableroVacio();
    public boolean existeFichaAdyacente(int fila, int columna);
    public boolean posicionValida();
    public ICasillero[][] getCasilleros();
    public ICasillero getCasillero(int fila, int columna);
    public ArrayList<ICasillero> casillerosDisponibles();
    public void ponerFicha(int fila, int columna, IFicha fichaElegida);
    public IFicha quitarFicha(int fila,int columna);
    public void reiniciarCasillerosJugados();
    
    
    //devolder todas las posiciones validas
}