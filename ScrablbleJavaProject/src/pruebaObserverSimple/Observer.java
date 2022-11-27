package pruebaObserverSimple;

import Controlador.Eventos;

public interface Observer {
    void update(Object data,Eventos evento);
}
