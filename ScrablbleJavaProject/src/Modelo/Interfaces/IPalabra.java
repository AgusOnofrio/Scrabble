package Modelo.Interfaces;

import java.util.ArrayList;

public interface IPalabra {
    public String convertirString();

    public int obtenerPuntaje();

    public boolean esValida();

    public boolean equals(IPalabra palabra);

    public ArrayList<ICasillero> getPosiciones();

}
