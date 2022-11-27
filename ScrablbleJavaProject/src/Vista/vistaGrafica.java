package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.Caret;
import javax.swing.text.FlowView;

import Controlador.Eventos;
import Controlador.ScrabbleController;
import Modelo.Casillero;
import Modelo.IPartida;
import Modelo.Jugador;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import pruebaObserverSimple.Observer;

public class vistaGrafica implements IControladorRemoto,IVista,Observer{
    private JFrame frame;
    private JPanel tablero;
    private JPanel atril;
    private JPanel panelPrincipal;
    private JPanel panelLateral;
    private JButton botonCambiarFichas;
    private final Color VERDE = new Color(25,135, 73);
    private final Color NARANJA = new Color(244,184,74,200);
    private final Color ROJO = new Color(233,33,23);
    private IObservableRemoto modeloRemoto;
    private ScrabbleController controlador;
    
    public vistaGrafica(ScrabbleController controlador){
        this.controlador= controlador;
    }

    public void iniciar(){
        frame = new JFrame("Titulo de la ventana");
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mostrarJuego();
        frame.setVisible(true);
    }

    private void mostrarJuego(){
        
        panelPrincipal= (JPanel) frame.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());


        //Muestro panel con valores
        JTextArea valoresLetras= new JTextArea(utils.valoreLetrasAString());
        valoresLetras.setEditable(false);
        valoresLetras.setAlignmentX(Font.CENTER_BASELINE);
        valoresLetras.setAlignmentY(Font.CENTER_BASELINE);
        valoresLetras.setFont(new Font(Font.SANS_SERIF, 0, 15)); ;
        panelPrincipal.add(valoresLetras,BorderLayout.EAST);


        //inicio tablero
        GridLayout glTablero = new GridLayout(15, 15);
        this.tablero= new JPanel();
        this.tablero.setLayout(glTablero);
        this.tablero.setSize(600,600);
        panelPrincipal.add(this.tablero, BorderLayout.CENTER);
        
        
        //inicio atril
        GridLayout glAtril = new GridLayout(1, 7);
        this.atril= new JPanel();
        this.atril.setLayout(new FlowLayout());    
        panelPrincipal.add(this.atril, BorderLayout.SOUTH);
        

        //Boton para finalizar turno
        JButton botonFinalizarTurno=new JButton("Finalizar turno");
        botonFinalizarTurno.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    controlador.finalizarTurno();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});

        //Boton para cambiar fichas
        botonCambiarFichas=new JButton("Cambiar fichas");
        botonCambiarFichas.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                    controlador.cambiarFichas();
            }});
        
        
        
        
        atril.add(botonFinalizarTurno);
        atril.add(botonCambiarFichas);


        //Etiqueta jugador 
        JLabel nombreJugador= new JLabel("Jugador: "+this.controlador.getJugador().getNombre()+" |");
        JLabel puntaje= new JLabel("Puntaje: "+this.controlador.getJugador().getPuntaje()+" puntos");
        atril.add(nombreJugador);
        atril.add(puntaje);

        mostrarTablero(controlador.getTablero());
        mostrarAtrilJugador(controlador.getJugador());
        panelPrincipal.setVisible(true);
        
    }



    @Override
    public void mostrarCasillero(ICasillero casillero) {}
      

    @Override
    public void mostrarTablero(Itablero tablero) {
        Casillero[][] casilleros = tablero.getCasilleros();
        ArrayList<Casillero> casillerosDisponibles =tablero.casillerosDisponibles();
        for (Casillero[] fila : casilleros) {
            for (Casillero casillero : fila) {
                casillero.setDisponible(casillerosDisponibles.contains(casillero));
                
                //muestro casilleros

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
                botonCasillero.setEnabled(casillero.getDisponible());
                botonCasillero.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        controlador.elegirCasillero(casillero);
                    }});
        
                this.tablero.add(botonCasillero);
                
            }


            }
        }
    

    @Override
    public void mostrarAtrilJugador(Ijugador jugador) {
        ArrayList<IFicha> fichas = jugador.getAtril().getFichasAtril();

        for (IFicha ficha : fichas) {
            JButton botonFicha = new JButton(ficha.getLabel());
            botonFicha.setSize(250,250);
            botonFicha.addActionListener(new ActionListener() {
                @Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.elegirFichaAtril(ficha);
				System.out.println("Ingresa aca??");
			}
            });;
            atril.add(botonFicha);
        }

        
    }

    @Override
    public void update(Object data, Eventos evento)  {
        switch (evento) {
            case POSICIONO_FICHA:
                panelPrincipal.removeAll();
                this.mostrarJuego();
                this.botonCambiarFichas.setEnabled(false);
                System.out.println("LLego al update de la vista");   
                break;
            case FINALIZO_TURNO:
                ArrayList<String> palabrasValidasDelTurno = controlador.getPalabrasValidasDelTurno();
                String mensajePalabras="";
                for (String palabra : palabrasValidasDelTurno) {
                    mensajePalabras+= palabra+"\t";
                }
                try {
                    JOptionPane.showMessageDialog(frame, mensajePalabras+"\n"+"Puntos de "+controlador.getJugador().getNombre()+": "+ controlador.calcularPuntajeTurno());
                } catch (HeadlessException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case COMIENZA_TURNO:
                panelPrincipal.removeAll();
                this.mostrarJuego();
                this.botonCambiarFichas.setEnabled(true);
                break;
            case FINALIZAR_PARTIDA:
                this.mostrarJuego();
                JOptionPane.showMessageDialog(frame, controlador.getJugador().getPuntaje());
                this.botonCambiarFichas.setEnabled(true);
                break;
            default:
                break;
        }
        
    }

    @Override
    public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
        switch ((Eventos)arg1) {
            case POSICIONO_FICHA:
                panelPrincipal.removeAll();
                this.mostrarJuego();
                this.botonCambiarFichas.setEnabled(false);
                System.out.println("LLego al update de la vista");   
                break;
            case FINALIZO_TURNO:
                ArrayList<String> palabrasValidasDelTurno = controlador.getPalabrasValidasDelTurno();
                String mensajePalabras="";
                for (String palabra : palabrasValidasDelTurno) {
                    mensajePalabras+= palabra+"\t";
                }
                try {
                    JOptionPane.showMessageDialog(frame, mensajePalabras+"\n"+"Puntos de "+controlador.getJugador().getNombre()+": "+ controlador.calcularPuntajeTurno());
                } catch (HeadlessException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case COMIENZA_TURNO:
                panelPrincipal.removeAll();
                this.mostrarJuego();
                this.botonCambiarFichas.setEnabled(true);
                break;
            case FINALIZAR_PARTIDA:
                this.mostrarJuego();
                JOptionPane.showMessageDialog(frame, controlador.getJugador().getPuntaje());
                this.botonCambiarFichas.setEnabled(true);
                break;
            default:
                break;
        }
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modeloRemoto= (IPartida) modeloRemoto;
        
    }








}

