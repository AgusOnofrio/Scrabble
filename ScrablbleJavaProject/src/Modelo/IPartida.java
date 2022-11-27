package Modelo;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Controlador.Eventos;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IPartida extends IObservableRemoto{

	void agregarJugador(String nombre)throws RemoteException;

	void clearCasillerosJugadosEnElTurno()throws RemoteException;

	void agregarCasilleroJugado(ICasillero casillero)throws RemoteException;

	int calcularPuntajeTurno() throws IOException;

	void finalizarTurno() throws IOException;

	void cambiarFichas()throws RemoteException;

	boolean estaEnArrayPalabra(ArrayList<IPalabra> array, IPalabra palabra)throws RemoteException;

	Itablero getTablero()throws RemoteException;

	Ijugador getJugador()throws RemoteException;

	IFicha elegirfichaJugador(int indice)throws RemoteException;

	void siguienteTurno()throws RemoteException;

	int getPuntaje()throws RemoteException;

	void elegirCasillero(ICasillero casillero)throws RemoteException;

	void elegirFichaAtril(IFicha ficha)throws RemoteException;

	ArrayList<String> getPalabrasValidasDelTurno()throws RemoteException;

	void notificarObservers(Object data, Eventos evento)throws RemoteException;

	void agregarObservador(Object t)throws RemoteException;

	void quitarObservador(Object t)throws RemoteException;

	// Observable Remoto
	void notificarObservadores(Object obj) throws RemoteException;

}