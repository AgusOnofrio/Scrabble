
import java.io.IOException;
import javax.swing.JOptionPane;

import Cliente.ClienteScrabble;
import Modelo.Interfaces.IPartida;
import Modelo.Interfaces.Ijugador;
import Serializacion.Ranking;
import Serializacion.Serializador;
import Servidor.ServidorScrabble;


public class Main {
    public static void main(String[] args) throws IOException {

        //Leo historial
        Serializador historial = new Serializador("historial.dat");
        Object object = historial.readFirstObject();
        if(object!=null){
            Ranking ranking = (Ranking) object;
            for (Ijugador j : ranking.getJugadores()) {
                System.out.println(j.getNombre()+" - "+j.getPuntaje());
            }
        }



        // //Recupero si hay alguna partida
        Serializador serializador = new Serializador("partida.dat");
		Object partidaRecuperada = serializador.readFirstObject();
        

        
        //inicializo juego local
        ServidorScrabble servidor = new ServidorScrabble(partidaRecuperada);

        if(partidaRecuperada!=null){
            IPartida partida = (IPartida) partidaRecuperada;
            for (Ijugador jugador : partida.getJugadores()) {
                ClienteScrabble cliente = new ClienteScrabble(jugador);
            }
            System.out.println();
        }else{
            Integer[] jugadoresPosibles= {2,3,4};
            Integer jugadores = (Integer) JOptionPane.showInputDialog(
                null, 
                "Seleccione la cantidad de jugadores", "Jugadores", 
                JOptionPane.QUESTION_MESSAGE, 
                null,
                jugadoresPosibles,
                2
            );
    
            for (int i = 0; i < jugadores; i++) {
                ClienteScrabble cliente = new ClienteScrabble(null);
            }
        }




        }

}
