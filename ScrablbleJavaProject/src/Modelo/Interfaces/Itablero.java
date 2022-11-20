package Modelo.Interfaces;

import Modelo.Casillero;

public interface Itablero{

    public boolean tableroVacio();
    public boolean existeFichaAdyacente(int fila, int columna);
    public boolean posicionValida();
    public Casillero[][] getCasilleros();
    
    
    //devolder todas las posiciones validas
}