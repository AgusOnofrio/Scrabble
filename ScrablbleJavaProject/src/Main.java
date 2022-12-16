
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    static JFrame frame;
    static JPanel panelPrincipal;
    public static void main(String[] args) throws IOException {
        menuPrincipal();
    }

    public static void menuPrincipal(){
        frame = new JFrame(); //JFrame Creation       
        frame.setTitle("Scrabble"); //Add the title to frame
        frame.setLayout(null); //Terminates default flow layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        frame.setSize(1000,900);
        
        panelPrincipal= new JPanel();
        panelPrincipal= (JPanel) frame.getContentPane();
        



        JButton botonVerRanking= new JButton("Ver Ranking");
        botonVerRanking.setSize(300,300);
        botonVerRanking.setEnabled(true);
        botonVerRanking.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                verRanking();
                
            }
        });

        JButton botonContinuarPartida= new JButton("Continuar partida");
        botonContinuarPartida.setSize(1000,1000);
        botonContinuarPartida.setEnabled(true);
        botonContinuarPartida.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    continuarPartida();
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
        }});


        JButton botonNuevaPartida= new JButton("Nueva partida");
        botonNuevaPartida.setSize(1000,1000);
        botonNuevaPartida.setEnabled(true);
        botonNuevaPartida.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    comenzarJuego(null);
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
        }});


        botonVerRanking.setBounds(450,600,150,50);
        botonContinuarPartida.setBounds(450,675,150,50);
        botonNuevaPartida.setBounds(450,750,150,50);


        
        
        JLabel picLabel = new JLabel(new ImageIcon("A.png"));
        picLabel.setSize(800,450);
        picLabel.setBounds(0,0,1000,563);



        panelPrincipal.add(picLabel);
        panelPrincipal.add(botonVerRanking);
        panelPrincipal.add(botonContinuarPartida);
        panelPrincipal.add(botonNuevaPartida);



        panelPrincipal.updateUI();
        frame.setVisible(true);
    }

    // //Leo historial
    public static void continuarPartida() throws RemoteException{
        // //Recupero si hay alguna partida
        Serializador serializador = new Serializador("partida.dat");
        Object partidaRecuperada = serializador.readFirstObject();
        
        //inicio juego
        comenzarJuego(partidaRecuperada);
    }


    public static void comenzarJuego(Object partidaRecuperada) throws RemoteException{
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


    public static void verRanking(){
        panelPrincipal.removeAll();

        JPanel panelRanking = new JPanel();
        panelRanking.setLayout(null );
        

        Serializador historial = new Serializador("historial.dat");
        Object object = historial.readFirstObject();
        int y=0;
        if(object!=null){
            Ranking ranking = (Ranking) object;
            
            for (Ijugador j : ranking.getJugadores()) {
                JLabel puntaje = new JLabel(j.getNombre()+" - "+j.getPuntaje());
                puntaje.setBounds(450,y,100,30);
                panelRanking.add(puntaje);
                y+=33;
            }
        }

        
        
        JButton botonVolverAlMenu= new JButton("Menu principal");
        botonVolverAlMenu.setBounds(750,750,150,50);
        botonVolverAlMenu.setEnabled(true);
        botonVolverAlMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                frame.dispose();
                menuPrincipal();
            }
        });
        panelRanking.setBounds(0, 0, 900, 800);
        panelRanking.add(botonVolverAlMenu);
        panelRanking.setVisible(true);
        panelPrincipal.add(panelRanking);
        panelPrincipal.updateUI();
    }














}
