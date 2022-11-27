package pruebaObserverSimple;

import Controlador.Eventos;

// `p` is a reference to the object that implements the interface.
public interface Observado <T>{
    public void notificarObservers(Object data,Eventos evento);
	public void agregarObservador(T t);
    public void quitarObservador(T t);
}
