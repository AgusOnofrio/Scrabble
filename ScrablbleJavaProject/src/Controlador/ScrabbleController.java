package Controlador;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import Vista.IVista;

public class ScrabbleController implements Observer{
    
    private Ijugador jugador;
    private Partida modelo;
    private IVista vista;

    public ScrabbleController(Partida modelo){
        this.modelo=modelo;
    }

    public void setModelo(Partida modelo) {
        this.modelo = modelo;
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }


    public Itablero getTablero() {
        return this.modelo.getTablero();
    }

    @Override
    public void update(Observable o, Object arg) {
        	if (arg instanceof Eventos) {
			switch ((Eventos) arg) {
			case ELEGIR_FICHA_ATRIL:
				this.vista.mostrarAtrilJugador(this.modelo.getJugador());
				break;
			case ELEGIR_CASILLERO:
				this.vista.mostrarTablero(this.modelo.getTablero());
				break;
            case POSICIONO_FICHA:
                this.vista.mostrarTablero(this.modelo.getTablero());
                this.vista.mostrarAtrilJugador(this.modelo.getJugador());
		    	break;
			default:
				break;
			
			}
		}
        
    }

    public void agregarCasilleroJugado(ICasillero casillero) {
        this.modelo.agregarCasilleroJugado(casillero);
    }

    public void clearCasillerosJugadosEnElTurno() {
        this.modelo.clearCasillerosJugadosEnElTurno();
    }

    public Ijugador getJugador() {
        return this.modelo.getJugador();
    }

    public int calcularPuntajeTurno() throws IOException {

        return this.modelo.calcularPuntajeTurno();
    }

    public void agregarJugador(String nombre) {
        this.modelo.agregarJugador(nombre);
    }

    public void siguienteTurno() {
        this.modelo.siguienteTurno();
    }

    public int mostraPuntaje() {
        return this.modelo.getPuntaje();
    }






}
