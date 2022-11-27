package Vista;

import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;

public interface IVista {
    public void mostrarCasillero(ICasillero casillero);

    public void mostrarTablero(Itablero tablero);

    public void mostrarAtrilJugador(Ijugador jugador);

    public void iniciar();

}
