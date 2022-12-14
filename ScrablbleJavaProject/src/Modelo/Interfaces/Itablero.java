package Modelo.Interfaces;

import java.awt.Component;
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
    public ArrayList<ICasillero> getUltimosCasilleroJugados();
    public ArrayList<ICasillero> getCasillerosJugadosEnElTurno();
    public void clearCasillerosJugadosEnElTurno();
    public void agregarCasilleroJugado(ICasillero casilleroElegido);
    public ICasillero getCasilleroElegido();
    public void clearCasilleroElegido();
    public void setCasilleroElegido(ICasillero casillero);
    
    
    //devolder todas las posiciones validas
}