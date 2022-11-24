package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.FlowView;

import Controlador.ScrabbleController;
import Modelo.Casillero;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;

public class vistaGrafica implements IVista{
    private JFrame frame;
    private JPanel tablero;
    private JPanel atril;
    private final Color VERDE = new Color(25,135, 73);
    private final Color NARANJA = new Color(244,184,74,200);
    private final Color ROJO = new Color(233,33,23);

    private ScrabbleController controlador;
    
    public vistaGrafica(ScrabbleController controlador){
        this.controlador= controlador;
        iniciarVistaGrafica();
    }

    public void iniciarVistaGrafica(){
        frame = new JFrame("Titulo de la ventana");
        frame.setSize(400,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelPrincipal= (JPanel) frame.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());
        
        
        
        
        //inicio tablero
        GridLayout glTablero = new GridLayout(15, 15);
        this.tablero= new JPanel();
        this.tablero.setLayout(glTablero);
        frame.add(tablero);
        panelPrincipal.add(this.tablero, BorderLayout.CENTER);
        
        
        //inicio atril
        // GridLayout glAtril = new GridLayout(1, 7);
        // this.atril= new JPanel();
        // this.atril.setLayout(glAtril);
        // frame.add(this.atril);
        // panelPrincipal.add(this.atril, BorderLayout.SOUTH);
        
        
        mostrarTablero(controlador.getTablero());
        
        frame.setVisible(true);
    }

    @Override
    public void mostrarCasillero(ICasillero casillero) {
        JButton botonCasillero;
        IFicha ficha = casillero.getFicha();
        if(ficha!=null){
            botonCasillero= new JButton(ficha.getLabel());
        }else{
            switch (casillero.getTipoEspecial()) {
                case SIMPLE:
                    botonCasillero= new JButton();
                    botonCasillero.setBackground(VERDE);
                    break;
                case DOBLE_LETRA:
                    botonCasillero= new JButton("2L");
                    botonCasillero.setBackground(Color.CYAN);
                    break;
                case TRIPLE_LETRA:
                    botonCasillero= new JButton("3L");
                    botonCasillero.setBackground(Color.BLUE);
                    break;
                case TRIPLE_PALABRA:
                    botonCasillero= new JButton("3P");
                    botonCasillero.setBackground(ROJO);
                    break;
                case DOBLE_PALABRA:
                    botonCasillero= new JButton("2P");
                    botonCasillero.setBackground(NARANJA);
                    break;
                default:
                    botonCasillero= new JButton();
                    botonCasillero.setBackground(VERDE);
                    break;
            }
        }
        tablero.add(botonCasillero);
        
    }

    @Override
    public void mostrarTablero(Itablero tablero) {
        Casillero[][] casilleros = tablero.getCasilleros();
        
        for (Casillero[] fila : casilleros) {
            for (Casillero casillero : fila) {
                this.mostrarCasillero(casillero);
            }
        }
        
    }

    @Override
    public void mostrarAtrilJugador(Ijugador jugador) {
        ArrayList<IFicha> fichas = jugador.getAtril().getFichasAtril();

        for (IFicha ficha : fichas) {
            atril.add(new JButton(ficha.getLabel()));
        }

        
    }

}

