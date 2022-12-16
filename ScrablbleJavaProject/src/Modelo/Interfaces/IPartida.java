package Modelo.Interfaces;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IPartida extends IObservableRemoto{

	Ijugador agregarJugador(String nombre)throws RemoteException;

	void clearCasillerosJugadosEnElTurno()throws RemoteException;

	void agregarCasilleroJugado(ICasillero casillero)throws RemoteException;

	int calcularPuntajeTurno() throws IOException;

	void finalizarTurno() throws IOException;

	void cambiarFichas(ArrayList<IFicha> fichasACambiar)throws RemoteException;

	boolean estaEnArrayPalabra(ArrayList<IPalabra> array, IPalabra palabra)throws RemoteException;

	Itablero getTablero()throws RemoteException;

	Ijugador getJugador()throws RemoteException;

	IFicha elegirfichaJugador(int indice)throws RemoteException;

	void siguienteTurno()throws RemoteException;

	int getPuntaje()throws RemoteException;

	void elegirCasillero(ICasillero casillero)throws RemoteException;

	void elegirFichaAtril(IFicha ficha)throws RemoteException;

	ArrayList<IPalabra> getPalabrasValidasDelTurno()throws RemoteException;

	// Observable Remoto
	void notificarObservadores(Object obj) throws RemoteException;

    ArrayList<Ijugador> getJugadores() throws RemoteException;

    void sacarFichaDeCasillero(ICasillero casillero) throws RemoteException;

	void guardarPartida() throws RemoteException;

    void borrarPartida() throws RemoteException;

	void guardarPuntajes() throws RemoteException;

	

    

  

    

}