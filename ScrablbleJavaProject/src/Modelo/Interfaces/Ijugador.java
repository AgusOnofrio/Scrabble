package Modelo.Interfaces;

import java.util.ArrayList;

public interface Ijugador {
    //Sabers si la palabra esta en s atril
    public String getNombre();
    
    public void setNombre(String nombre);

    //Sabers si la palabra esta en s atril
    public boolean palabraEstaEnAtril(String palabra);

    public IAtril getAtril() ;

    public void sumarPuntos(int puntaje);

    public Integer getPuntaje();

    public void agregarPalabraCreadaPorJugador(IPalabra palabra);

    public ArrayList<IPalabra> obtenerPalabrasDePartida();

    public void clearPalabrasFormadasEnElturno();

    public void agregarPalabraFormadaEnElturno(IPalabra palabra);

    public ArrayList<IPalabra> getPalabrasValidasDelTurno();

    public IFicha getFichaElegida();

    public void clearFichaElegida();

    public void setFichaElegida(IFicha ficha);
}
