package pruebaObserverSimple;

// `p` is a reference to the object that implements the interface.
public interface Observado <T>{
    public void notificarObservers(int valor);
	public void agregarObservador(T t);
    public void quitarObservador(T t);
}
