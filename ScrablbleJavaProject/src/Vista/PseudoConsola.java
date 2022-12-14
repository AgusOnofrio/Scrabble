package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ScrabbleController;
import Modelo.Tablero;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSlider;

public class PseudoConsola extends JFrame implements IVista{

	private ScrabbleController controlador;

	private JPanel contentPane;
	private JPanel panelTablero;
	
	private JTextField campoFicha;
	private JTextField campoCasillero;

	private JTextArea atrilArea;
	private JTextArea casillerosDisponiblesArea;
	private JTextArea resultadosArea;

	private JButton botonCasillero;
	private JButton botonFicha;
	private JButton botonFinalizar;
	private JButton CambiarFichas;

	private String stringTablero="";
	private String stringResultado="";
	private String stringAtril="";
	private String stringCasillerosDisponibles="";
	private String fichaSeleccionada;
	private String filaSeleccionada;
	private String columnaSeleccionada;

	/**
	 * Create the frame.
	 */
	public PseudoConsola() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.resultadosArea = new JTextArea();
		resultadosArea.setEnabled(false);
		resultadosArea.setEditable(false);
		resultadosArea.setBounds(525, 11, 217, 330);
		contentPane.add(resultadosArea);
		
		campoFicha = new JTextField();
		campoFicha.setBounds(566, 377, 86, 20);
		contentPane.add(campoFicha);
		campoFicha.setColumns(10);
		
		this.botonFicha = new JButton("Ficha");
		botonFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fichaSeleccionada=campoFicha.getText();
				elegirFichaAtril(fichaSeleccionada);
			}


		});
		botonFicha.setBounds(662, 376, 89, 23);
		contentPane.add(botonFicha);
		
		campoCasillero = new JTextField();
		campoCasillero.setBounds(566, 408, 86, 20);
		contentPane.add(campoCasillero);
		campoCasillero.setColumns(10);
		
		this.botonCasillero = new JButton("Casillero");
		botonCasillero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elegirCasillero( campoCasillero.getText());
			}
		});
		botonCasillero.setBounds(662, 407, 89, 23);
		contentPane.add(botonCasillero);
		
		this.casillerosDisponiblesArea = new JTextArea();
		casillerosDisponiblesArea.setText("7-7");
		casillerosDisponiblesArea.setEnabled(false);
		casillerosDisponiblesArea.setEditable(false);
		casillerosDisponiblesArea.setBounds(56, 406, 459, 51);
		contentPane.add(casillerosDisponiblesArea);
		
		this.atrilArea = new JTextArea();
		atrilArea.setEnabled(false);
		atrilArea.setEditable(false);
		atrilArea.setBounds(56, 343, 459, 51);
		contentPane.add(atrilArea);
		
		this.panelTablero = new JPanel();
		panelTablero.setBounds(96, 11, 373, 321);
		contentPane.add(panelTablero);
		panelTablero.setLayout(new GridLayout(16, 16, 0, 0));
		
		this.botonFinalizar = new JButton("Finalizar");
		botonFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    controlador.finalizarTurno();
                } catch (IOException error) {
                    error.printStackTrace();
                }
			}
		});
		botonFinalizar.setBounds(531, 445, 89, 23);
		contentPane.add(botonFinalizar);
		
		this.CambiarFichas = new JButton("Cambiar fichas");
		CambiarFichas.setBounds(630, 445, 112, 23);
		contentPane.add(CambiarFichas);
		this.setVisible(true);
		
	}
	
	
	
	
	

	@Override
	public void mostrarCasillero(ICasillero casillero) {
		IFicha ficha = casillero.getFicha();
        if(ficha!=null){
			if(ficha.getLabel()=="."){
				this.panelTablero.add(new JLabel("*"));
			}else{
				this.panelTablero.add(new JLabel(ficha.getLabel()));
			}
        }else{
            switch (casillero.getTipoEspecial()) {
                case SIMPLE:
				// this.stringTablero +=" . ";
				this.panelTablero.add(new JLabel("."));
                    break;
                case DOBLE_LETRA:
				// this.stringTablero+=(" 2L ");
				this.panelTablero.add(new JLabel("2L"));
                break;
                case TRIPLE_LETRA:
                // this.stringTablero+=(" 3L ");
				this.panelTablero.add(new JLabel("3L"));
                break;
                case TRIPLE_PALABRA:
                // this.stringTablero+=(" 3P ");
				this.panelTablero.add(new JLabel("3P"));
                break;
                case DOBLE_PALABRA:
                // this.stringTablero+=(" 2P ");
				this.panelTablero.add(new JLabel("2P"));
				
                break;
                default:
                this.stringTablero+=(" . ");
				
                    break;
            }
        }
		
       
		
	}

	@Override
	public void mostrarTablero() {
		this.panelTablero.removeAll();;
        Itablero tablero= this.controlador.getTablero();
        ICasillero[][] casilleros = tablero.getCasilleros();
		this.panelTablero.add(new JLabel(" "));
		for (Integer i = 0; i < 15; i++) {
			this.panelTablero.add(new JLabel(i.toString()));
		}
        
		
        
        for (Integer i = 0; i < Tablero.TAMANIO; i++) {
            this.panelTablero.add(new JLabel( new Character((char) (i+65)).toString()));
            for (int j = 0; j < Tablero.TAMANIO; j++) {
                this.mostrarCasillero(casilleros[i][j]);
                
            }
            this.stringTablero+="\n";
        }
		// this.tableroArea.setText(stringTablero);
		this.panelTablero.updateUI();
	}

	@Override
	public void mostrarAtrilJugador() {
		this.stringAtril="";
		Ijugador jugador= this.controlador.getJugadorVista();
        ArrayList<IFicha> fichas = jugador.getAtril().getFichasAtril();
        String indices = "";
        for (IFicha ficha : fichas) {
            stringAtril+= ficha.getLabel()+" | ";
        }
		this.atrilArea.setText(stringAtril);
		
	}


	public void mostrarCasillerosDisponibles(){ 
        ArrayList<ICasillero> casillerosDisponibles = this.controlador.getTablero().casillerosDisponibles();
		this.stringCasillerosDisponibles="";
		String indices = "  ";
        Integer i =0;
        for (ICasillero c : casillerosDisponibles) {
            this.stringCasillerosDisponibles+=new Character((char) (c.getFila()+65)).toString()+c.getColumna()+" | ";
			indices+= i.toString()+"  |  ";
            i++;
        } 
		this.stringCasillerosDisponibles+="\n"+indices;
		this.casillerosDisponiblesArea.setText(stringCasillerosDisponibles);
    }




	@Override
	public void iniciar() {
		this.mostrarTablero();
		this.mostrarAtrilJugador();
		this.mostrarCasillerosDisponibles();
		botonCasillero.setEnabled(this.esTuTurno());
		botonFicha.setEnabled(this.esTuTurno());
		botonFinalizar.setEnabled(this.esTuTurno());
		CambiarFichas.setEnabled(this.esTuTurno());
	}


	public void elegirFichaAtril(String ficha) {
		ArrayList<IFicha> fichasAtril= this.controlador.getJugadorVista().getAtril().getFichasAtril();
		IFicha fichaSeleccionada=null;
		for (IFicha f : fichasAtril) {
			if(f.getLabel().equals(ficha.toUpperCase().trim())) fichaSeleccionada=f;
		}
		
		if(fichaSeleccionada!=null){
			this.controlador.elegirFichaAtril(fichaSeleccionada);
			resultadosArea.setText("Ficha seleccionada: "+fichaSeleccionada.getLabel());
		}else{
			resultadosArea.setText("Ficha invalida");
		}
	}

	public void elegirCasillero(String indice){
		ArrayList<ICasillero> casillerosDisponibles = this.controlador.getTablero().casillerosDisponibles();
		if(utils.esNumerico(indice) && Integer.parseInt(indice)>=0 && Integer.parseInt(indice)< casillerosDisponibles.size()){
			this.controlador.elegirCasillero(casillerosDisponibles.get(Integer.parseInt(indice)));
			resultadosArea.setText("Casillero elegido: "+casillerosDisponibles.get(Integer.parseInt(indice)).getFila()+
			"-"+casillerosDisponibles.get(Integer.parseInt(indice)).getColumna());
		}else{
			resultadosArea.setText("Casillero Invalido");
		}
	}

	@Override
	public void actualizarVista() {
		this.mostrarTablero();
		this.mostrarAtrilJugador();
		this.mostrarCasillerosDisponibles();
		botonCasillero.setEnabled(this.esTuTurno());
		botonFicha.setEnabled(this.esTuTurno());
		botonFinalizar.setEnabled(this.esTuTurno());
		CambiarFichas.setEnabled(this.esTuTurno());
	}

	private boolean esTuTurno() {
		Ijugador jugadorVista= this.controlador.getJugadorVista();
		Ijugador jugadorActual= this.controlador.getJugadorActual();
		return jugadorActual.getNombre().equals(jugadorVista.getNombre());
	}

	@Override
	public void setCambiarFichas(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarFinDeturno() {
        Integer puntajeTurno=0;
		String textoResultado="";
        String jugador= this.controlador.getJugadorActual().getNombre();
        textoResultado+="Jugador: "+jugador+"\n" ;
        for (IPalabra palabra : this.controlador.getPalabrasValidasDelTurno()) {
            textoResultado+=palabra.convertirString()+" - "+palabra.obtenerPuntaje()+"\n";
            puntajeTurno+=palabra.obtenerPuntaje();
        }
        textoResultado+="Puntaje turno: "+puntajeTurno;
		resultadosArea.setText(textoResultado);
		this.actualizarVista();
		
	}

	@Override
	public void setControlador(IControladorRemoto controlador) {
		this.controlador=(ScrabbleController) controlador;
		
	}

	@Override
	public void mostrarResultadoFinal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarJugadores() {
		// TODO Auto-generated method stub
		
	}
}
