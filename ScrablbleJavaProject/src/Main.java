
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cliente.ClienteScrabble;
import Servidor.ServidorScrabble;


public class Main {
    public static void main(String[] args) throws IOException {

        // JFrame frame = new JFrame(); //JFrame Creation       
        // frame.setTitle("Scrabble"); //Add the title to frame
        // frame.setLayout(null); //Terminates default flow layout
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        // frame.setSize(1000,900);
        
        // // BufferedImage myPicture = ImageIO.read(new File("../A.png"));
        // Image img = Toolkit.getDefaultToolkit().createImage("../A.png");
        // JLabel picLabel = new JLabel(new ImageIcon(img));
        // frame.add(picLabel);
        // frame.setVisible(true);
        
        
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
