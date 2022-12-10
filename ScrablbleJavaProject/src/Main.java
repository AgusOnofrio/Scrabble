
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cliente.ClienteScrabble;
import Modelo.Interfaces.IPartida;
import Modelo.Interfaces.Ijugador;
import Serializacion.Ranking;
import Serializacion.Serializador;
import Servidor.ServidorScrabble;


public class Main {
    public static void main(String[] args) throws IOException {

        // JFrame frame = new JFrame(); //JFrame Creation       
        // frame.setTitle("Scrabble"); //Add the title to frame
        // frame.setLayout(null); //Terminates default flow layout
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        // frame.setSize(1000,900);
        
        // // BufferedImage myPicture = ImageIO.read(new File("../A.png"));
        
        // JLabel picLabel = new JLabel(new ImageIcon("A.png"));
        // frame.add(picLabel);
        // frame.setVisible(true);
        
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
