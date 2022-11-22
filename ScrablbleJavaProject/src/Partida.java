import java.io.IOException;
import java.util.ArrayList;

import Modelo.BolsaFichas;
import Modelo.Jugador;
import Modelo.Tablero;

public class Partida {

    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private BolsaFichas bolsaConLetras;
    private boolean continuarPartida;

    public Partida() throws IOException{
        //Inicializo jugadores
        jugadores=new ArrayList<Jugador>();
        //inicializo Tablero
        tablero = new Tablero();
        //inicializo bolsa de letras
        bolsaConLetras= new BolsaFichas();
    }

    public void agregarJugador(Jugador jugador){
        jugadores.add(jugador);
    }


}
