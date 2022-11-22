package Modelo.Interfaces;

import java.util.ArrayList;

import Modelo.Casillero;

public interface Itablero{

    public boolean tableroVacio();
    public boolean existeFichaAdyacente(int fila, int columna);
    public boolean posicionValida();
    public Casillero[][] getCasilleros();
    public Casillero getCasillero(int fila, int columna);
    public ArrayList<Casillero> casillerosDisponibles();
    
    
    //devolder todas las posiciones validas
}