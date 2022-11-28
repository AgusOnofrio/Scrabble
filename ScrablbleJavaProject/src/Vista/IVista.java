package Vista;

import Controlador.ScrabbleController;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;

public interface IVista {
    public void mostrarCasillero(ICasillero casillero);

    public void mostrarTablero(Itablero tablero);

    public void mostrarAtrilJugador(Ijugador jugador);

    public void iniciar();

    public void actualizarVista();

    public void setCambiarFichas(boolean b);

    public void mostrarFinDeturno(String string);

    public void setControlador(IControladorRemoto controlador);

}
