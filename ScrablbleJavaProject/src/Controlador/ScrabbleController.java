package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPartida;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import Vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class ScrabbleController implements  ActionListener,IControladorRemoto{
    private Ijugador jugador;
    private IPartida modelo;
    private IVista vista;

    public ScrabbleController(IVista vista){
        this.vista=vista;
    }

    public <T extends IObservableRemoto> ScrabbleController(T modelo) {
		try {
			this.setModeloRemoto(modelo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



    public void setModelo(IPartida modelo) {
        this.modelo = modelo;
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }


    public Itablero getTablero() {
        Itablero tablero=null;
        try {
            tablero= this.modelo.getTablero();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tablero;
    }

    public void agregarCasilleroJugado(ICasillero casillero) {
        try {
            this.modelo.agregarCasilleroJugado(casillero);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clearCasillerosJugadosEnElTurno() {
        try {
            this.modelo.clearCasillerosJugadosEnElTurno();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Ijugador getJugadorActual() {
        Ijugador jugador=null;
        try {
            jugador= this.modelo.getJugador();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jugador;
    }

    public Ijugador getJugadorVista() {
        Ijugador jugador=null;
        jugador= this.jugador;
        return jugador;
    }

    public void finalizarTurno() throws IOException {
        this.modelo.finalizarTurno();
    }

    public int calcularPuntajeTurno() throws IOException{
        return modelo.calcularPuntajeTurno();
    }

    public void agregarJugador(String nombre) {
        try {
            
            this.jugador=this.modelo.agregarJugador(nombre);

            this.vista.actualizarVista();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void siguienteTurno() {
        try {
            this.modelo.siguienteTurno();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int mostraPuntaje() {
        int puntaje=0;
        try {
            puntaje= this.modelo.getPuntaje();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return puntaje;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void elegirCasillero(ICasillero casillero){
        try {
            this.modelo.elegirCasillero(casillero);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void elegirFichaAtril(IFicha ficha){
        try {
            modelo.elegirFichaAtril(ficha);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public ArrayList<String> getPalabrasValidasDelTurno() {
        ArrayList<String> palabras=null;
        try {
            palabras= modelo.getPalabrasValidasDelTurno();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return palabras;
    }

    public void cambiarFichas() {
        try {
            this.modelo.cambiarFichas();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T arg0) throws RemoteException {
        this.modelo = (IPartida) arg0;
        
    }

    @Override
    public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
        switch ((Eventos)arg1) {
            case AGREGAR_JUGADOR:
                this.vista.actualizarVista();
            break;
            case POSICIONO_FICHA:
                this.jugador=this.modelo.getJugador();
                this.vista.actualizarVista();
                this.vista.setCambiarFichas(false);  
                break;
            case FINALIZO_TURNO:
                this.vista.mostrarFinDeturno();
                break;
            case COMIENZA_TURNO:
                this.vista.actualizarVista();
                this.vista.setCambiarFichas(true);
                break;
            case FINALIZAR_PARTIDA:
                this.vista.mostrarFinDeturno();
                this.vista.mostrarResultadoFinal();
                break;
            default:
                break;
        }
        
    }

    public ArrayList<Ijugador> getJugadores() throws RemoteException {
        return this.modelo.getJugadores();
    }


    


}
