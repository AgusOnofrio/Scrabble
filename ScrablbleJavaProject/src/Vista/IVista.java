package Vista;

import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;

public interface IVista {
    public void mostrarCasillero(ICasillero casillero);

    public void mostrarTablero();

    public void mostrarAtrilJugador();

    public void iniciar();

    public void actualizarVista();

    public void setCambiarFichas(boolean b);

    public void mostrarFinDeturno();

    public void setControlador(IControladorRemoto controlador);

    public void mostrarResultadoFinal();

    public void actualizarJugadores();

    public void comenzarTurno();



}
