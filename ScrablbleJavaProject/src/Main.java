
import java.io.IOException;
import javax.swing.JOptionPane;
import Cliente.ClienteScrabble;
import Servidor.ServidorScrabble;


public class Main {
    public static void main(String[] args) throws IOException {
        //inicializo juego local
        ServidorScrabble servidor = new ServidorScrabble();

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
            ClienteScrabble cliente = new ClienteScrabble();
        }







        }

}
