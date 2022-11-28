package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Modelo.IPartida;
import Modelo.Jugador;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
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
    };

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

    // @Override
    // public void update(Observable o, Object arg) {
    //     	if (arg instanceof Eventos) {
	// 		switch ((Eventos) arg) {
	// 		case ELEGIR_FICHA_ATRIL:
	// 			this.vista.mostrarAtrilJugador(this.modelo.getJugador());
	// 			break;
	// 		case ELEGIR_CASILLERO:
	// 			this.vista.mostrarTablero(this.modelo.getTablero());
	// 			break;
    //         case POSICIONO_FICHA:
    //             this.vista.mostrarTablero(this.modelo.getTablero());
    //             this.vista.mostrarAtrilJugador(this.modelo.getJugador());
	// 	    	break;
	// 		default:
	// 			break;
			
	// 		}
	// 	}
        
    // }

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

    public Ijugador getJugador() {
        Ijugador jugador=null;
        try {
            jugador= this.modelo.getJugador();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            this.modelo.agregarJugador(nombre);
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
    public void actualizar(IObservableRemoto arg0, Object cambio) throws RemoteException {
        System.out.println("Entra al actualizar del controller");
        switch ((Eventos)cambio) {
            case POSICIONO_FICHA:
                this.vista.actualizarVista();
                this.vista.setCambiarFichas(false);
                
                System.out.println("LLego al update de la vista");   
                break;
            case FINALIZO_TURNO:
                ArrayList<String> palabrasValidasDelTurno = this.getPalabrasValidasDelTurno();
                String mensajePalabras="";
                for (String palabra : palabrasValidasDelTurno) {
                    mensajePalabras+= palabra+"\t";
                }

                try {
                    this.vista.mostrarFinDeturno(mensajePalabras+"\n"+"Puntos de "+this.getJugador().getNombre()+": "+ this.calcularPuntajeTurno());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case COMIENZA_TURNO:
                this.vista.actualizarVista();
                this.vista.setCambiarFichas(true);
                break;
            case FINALIZAR_PARTIDA:
                this.vista.actualizarVista();
                this.vista.mostrarFinDeturno(this.getJugador().getPuntaje().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IPartida) modeloRemoto;
        
    }


    


}
