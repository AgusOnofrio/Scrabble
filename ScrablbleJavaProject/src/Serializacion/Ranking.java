package Serializacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Modelo.Interfaces.Ijugador;

public class Ranking implements Serializable {
    private ArrayList<Ijugador> jugadores = new ArrayList<Ijugador>();
    private static Ranking instancia;

    public static Ranking getInstance(){
        if(instancia==null){
            instancia=new Ranking();
        }
        return instancia;
    }

    public void agregarJugador(Ijugador jugador ){
        this.jugadores.add(jugador);
        Collections.sort(this.jugadores, (Comparator.<Ijugador>
        comparingInt(jugador1 -> -jugador1.getPuntaje())
            .thenComparingInt(jugador2 -> jugador2.getPuntaje())));
    }

    public ArrayList<Ijugador> getJugadores(){
        return this.jugadores;
    }



}
