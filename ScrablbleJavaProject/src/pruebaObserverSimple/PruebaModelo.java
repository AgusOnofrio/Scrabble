package pruebaObserverSimple;
import java.util.ArrayList;

import Controlador.Eventos;

public class PruebaModelo implements Observado<Observer> {
    private ArrayList<Observer> observadores;
    private int valor;

    public PruebaModelo(){
        valor=0;
        observadores=new ArrayList<Observer>();
    }




    public void sumar() {
        valor++;
        // notificarObservers(valor);
    }

    public void restar() {
        valor--;
        // notificarObservers(valor);
    }

    @Override
    public void notificarObservers (Object data,Eventos evento) {
        for(Observer o : observadores){
        //    o.update(valor); 
        }

        
    }

    @Override
    public void agregarObservador(Observer o) {
        observadores.add(o);
        // notificarObservers(valor);        
    }

    @Override
    public void quitarObservador(Observer o) {
        observadores.remove(o);
        
    }
    
}
