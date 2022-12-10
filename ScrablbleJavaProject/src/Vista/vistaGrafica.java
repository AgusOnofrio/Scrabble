package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import Controlador.ScrabbleController;
import Modelo.Jugador;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;

public class vistaGrafica implements IVista{
    private JFrame frame;
    private JPanel tablero;
    private JPanel atril;
    private JPanel panelPrincipal;
    private JButton botonCambiarFichas;
    private final Color VERDE = new Color(25,135, 73);
    private final Color NARANJA = new Color(244,184,74,200);
    private final Color ROJO = new Color(233,33,23);
    
    private ScrabbleController controlador;

    public vistaGrafica(){}
    public void setControlador(IControladorRemoto controlador){
        this.controlador=(ScrabbleController)controlador;
    }


    public void iniciar(){
        frame = new JFrame("Titulo de la ventana");
        frame.setSize(1000,900);
        frame.addWindowListener( new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i = guardarPartida();
                if(i==0) System.exit(0);
            }
        });
        panelPrincipal= (JPanel) frame.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());
    	// this.agregarJugador();
        this.mostrarJuego();
        frame.setVisible(true);
    }

    
    private int guardarPartida() {
        
        String [] opciones = {"Guardar partida","Salir"};
        String opcionElegida= (String) JOptionPane.showInputDialog(
            null, 
            "Desea guardar antes de salir? ","Guardar partida",  
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            null);
        
        if(opcionElegida=="Guardar partida"){
            this.controlador.guardarPartida();
        }else{
            this.controlador.borrarPartida();
        }
        
        return 0;
    }
    public void agregarJugador(){
        String nombreJugador= (String) JOptionPane.showInputDialog(
            null, 
            "Ingrese su nombre", "Nombre del jugador "+(this.controlador.getNumeroJugadorAAgregar()+1), 
            JOptionPane.QUESTION_MESSAGE,
            null,
            null,
            null);
        this.controlador.agregarJugador(nombreJugador);
    }


    private void mostrarJuego(){
        
        boolean esLaVistaDelJugador=this.controlador.getJugadorVista().getNombre().equals(this.controlador.getJugadorActual().getNombre());

        //Muestro panel con valores
        JTextArea valoresLetras= new JTextArea(utils.valoreLetrasAString());
        valoresLetras.setEditable(false);
        valoresLetras.setAlignmentX(Font.CENTER_BASELINE);
        valoresLetras.setAlignmentY(Font.CENTER_BASELINE);
        valoresLetras.setFont(new Font(Font.SANS_SERIF, 0, 15)); ;
        panelPrincipal.add(valoresLetras,BorderLayout.EAST);


        //Muestro panel con marcador
        String s="";
        try {
            for (Ijugador j : this.controlador.getJugadores() ) {
                s+=j.getNombre()+" : "+j.getPuntaje()+"\n";
            }
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        JTextArea marcador= new JTextArea(s);
        marcador.setEditable(false);
        marcador.setAlignmentX(Font.CENTER_BASELINE);
        marcador.setAlignmentY(Font.CENTER_BASELINE);
        marcador.setFont(new Font(Font.SANS_SERIF, 0, 15)); ;
        panelPrincipal.add(marcador,BorderLayout.WEST);



        //inicio tablero
        GridLayout glTablero = new GridLayout(15, 15);
        this.tablero= new JPanel();
        this.tablero.setLayout(glTablero);
        this.tablero.setSize(600,600);
        panelPrincipal.add(this.tablero, BorderLayout.CENTER);
        
        
        //inicio atril
        // GridLayout glAtril = new GridLayout(1, 7);
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
                    opcionesParaCambiarFichas();
            }});
        
        
        
        
        atril.add(botonFinalizarTurno);
        atril.add(botonCambiarFichas);


        //Etiqueta jugador 
        JLabel nombreJugador= new JLabel("Turno: "+this.controlador.getJugadorActual().getNombre()+" |");
        JLabel puntaje= new JLabel("Puntaje: "+this.controlador.getJugadorActual().getPuntaje()+" puntos");
        atril.add(nombreJugador);
        atril.add(puntaje);

        mostrarTablero();
        mostrarAtrilJugador();
        panelPrincipal.setVisible(true);
        
        
        
        for (Component component : panelPrincipal.getComponents()) {
                component.setEnabled(esLaVistaDelJugador); 
        };

        botonCambiarFichas.setEnabled(esLaVistaDelJugador);
        botonFinalizarTurno.setEnabled(esLaVistaDelJugador);
        
        panelPrincipal.updateUI();
    }

    public void opcionesParaCambiarFichas(){
        Ijugador jugador = this.controlador.getJugadorVista();
        JPanel panelCambio = new JPanel();
        ArrayList<IFicha> fichasElegidas = new ArrayList<IFicha>();

        ArrayList<IFicha> fichas = jugador.getAtril().getFichasAtril();

        for (IFicha ficha : fichas) {
            JButton botonFicha = new JButton(ficha.getLabel());
            botonFicha.setSize(250,250);
            botonFicha.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent arg0) {
                if(botonFicha.isEnabled()){
                    fichasElegidas.add(ficha);
                    botonFicha.setEnabled(false);
                }else{
                    fichasElegidas.remove(ficha);
                    botonFicha.setEnabled(true);
                }
            }
            });
            panelCambio.add(botonFicha);
        }
        JButton botonCambio = new JButton("Cambiar");
        botonCambio.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent arg0) {
            controlador.cambiarFichas(fichasElegidas);

        }
        });
        panelCambio.add(botonCambio);
        panelCambio.setVisible(true);
        this.panelPrincipal.removeAll();
        this.panelPrincipal.add(panelCambio);
        this.panelPrincipal.updateUI();
        
    }

    @Override
    public void mostrarCasillero(ICasillero casillero) {}
      

    @Override
    public void mostrarTablero() {
        boolean esElturno=this.controlador.getJugadorVista().getNombre().equals(this.controlador.getJugadorActual().getNombre());
        Itablero tablero = this.controlador.getTablero();
        ICasillero[][] casilleros = tablero.getCasilleros();
        ArrayList<ICasillero> casillerosDisponibles =tablero.casillerosDisponibles();
        int numeroFila=0;
        int numeroColumna=0;
        for (ICasillero[] fila : casilleros) {
            for (ICasillero casillero : fila) {
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
                botonCasillero.setEnabled(casillero.getDisponible() && esElturno);
                botonCasillero.putClientProperty("fila", numeroFila);
                botonCasillero.putClientProperty("columna", numeroColumna);
                botonCasillero.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        if(casillero.getFicha()!=null){
                            controlador.sacarFichaDeCasillero(casillero);
                        }else{
                            controlador.elegirCasillero(casillero);
                        }
                }});
        
                this.tablero.add(botonCasillero);
                numeroColumna++;
            }
            numeroFila++;

        }
    }
    

    @Override
    public void mostrarAtrilJugador() {
        boolean esElturno=this.controlador.getJugadorVista().getNombre().equals(this.controlador.getJugadorActual().getNombre());
        Ijugador jugador = this.controlador.getJugadorVista();
        
        ArrayList<IFicha> fichas = jugador.getAtril().getFichasAtril();

        for (IFicha ficha : fichas) {
            JButton botonFicha = new JButton(ficha.getLabel());
            botonFicha.setSize(250,250);
            botonFicha.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent arg0) {
                controlador.elegirFichaAtril(ficha);
            }
            });;
            botonFicha.setEnabled(esElturno);
            atril.add(botonFicha);
        }
        

        
    }



    @Override
    public void actualizarVista() {

        this.panelPrincipal.removeAll();
        this.mostrarJuego();
        frame.setVisible(true);
        
    }

    @Override
    public void setCambiarFichas(boolean condicion) {
        this.botonCambiarFichas.setEnabled(condicion);
        
    }

    @Override
    public void mostrarFinDeturno() {
        JPanel panelResultado= new JPanel();
        panelResultado.setLayout(new BoxLayout(panelResultado,BoxLayout.Y_AXIS));
        Integer puntajeTurno=0;

        String jugador= this.controlador.getJugadorActual().getNombre();
        panelResultado.add(new JLabel("Jugador: "+jugador)) ;//+"\n"+this.controlador.getPalabrasValidasDelTurno().toString()+"\n"+"Puntaje:"+puntaje;
        for (IPalabra palabra : this.controlador.getPalabrasValidasDelTurno()) {
            panelResultado.add(new JLabel(palabra.convertirString()+" - "+palabra.obtenerPuntaje()));
            puntajeTurno+=palabra.obtenerPuntaje();
        }
        panelResultado.add(new JLabel("Puntaje turno: "+puntajeTurno));


        JButton botonContinuar= new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                actualizarVista();
        }});

        
        

        panelResultado.add(botonContinuar);
        panelResultado.setVisible(true);

        
        for (Component c : panelPrincipal.getComponents()) {
            c.setVisible(false);
        }
        panelPrincipal.add(panelResultado,BorderLayout.CENTER);
        panelPrincipal.updateUI();
        
    }
    @Override
    public void mostrarResultadoFinal() {

        
        String resultados="";
        Integer ganador=0;
        ArrayList<Ijugador> jugadores;
        try {
            jugadores = this.controlador.getJugadores();

            Collections.sort(jugadores, (Comparator.<Ijugador>
                        comparingInt(jugador1 -> -jugador1.getPuntaje())
            .thenComparingInt(jugador2 -> jugador2.getPuntaje())));

            JPanel panelResultado= new JPanel();
            panelResultado.setLayout(new BoxLayout(panelResultado, BoxLayout.PAGE_AXIS));

            for (Ijugador j :  jugadores ) {
                panelResultado.add( new JLabel(j.getNombre()+"      "+"Puntaje:"+j.getPuntaje().toString()));
                for (IPalabra palabra : j.obtenerPalabrasDePartida()) {
                    panelResultado.add(new JLabel(palabra.convertirString()+" - "+palabra.obtenerPuntaje()));
                }
                panelResultado.add(new JLabel());

            }

            
            JButton botonFinalizarPartida= new JButton("Finalizar");
            botonFinalizarPartida.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    // guardarPartida();
                    guardarPuntajes();
                    System.exit(0);
                    //SAlir del juego;
            }});
    
            
            JLabel textoMensaje = new JLabel(resultados);
            textoMensaje.setSize(300,300);
            
            
            
            panelResultado.add(botonFinalizarPartida);
            panelResultado.setVisible(true);
  
            for (Component c : panelPrincipal.getComponents()) {
                c.setVisible(false);
            }
            panelPrincipal.add(panelResultado,BorderLayout.CENTER);
            panelPrincipal.updateUI();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       

        
        
    }
    
    
    @Override
    public void actualizarJugadores() {
        // TODO Auto-generated method stub
        
    }


    public void guardarPuntajes(){
        this.controlador.guardarPuntajes();
    }









}

