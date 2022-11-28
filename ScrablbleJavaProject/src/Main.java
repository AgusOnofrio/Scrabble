import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Controlador.ScrabbleController;
import Modelo.BolsaFichas;
import Modelo.Casillero;
import Modelo.Diccionario;
import Modelo.Ficha;
import Modelo.IPartida;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Tablero;
import Vista.IVista;
import Vista.VistaConsola;
import Vista.vistaGrafica;

public class Main {
    public static void main(String[] args) throws IOException {
        //inicializo
        
        IPartida modelo = new Partida();
		ScrabbleController controlador1 = new ScrabbleController(modelo);
        controlador1.agregarJugador("Agustin");
		IVista vista1 = new vistaGrafica();
        vista1.setControlador(controlador1);
		vista1.iniciar();
		
		// Controlador controlador2 = new Controlador(modelo);
		// IVista vista2 = new VistaGrafica(controlador2);
		// vista2.iniciar();

      
        
        // //Muestro tablero por primera vez
        // vistaConsola.mostrarTablero(tablero);
        // vistaConsola.mostrarCasillerosDisponibles(tablero);
        // vistaConsola.mostrarAtrilJugador(jugador1);

        // //pongo una ficha
        // Ficha ficha= (Ficha) bolsa.sacarFicha();
        // System.out.println(ficha.getLabel());
        // controlador1.getTablero().getCasilleros()[14][1].ponerFicha(ficha);
        // controlador1.getTablero().getCasilleros()[14][14].ponerFicha(ficha);
        // controlador1.getTablero().getCasilleros()[13][14].ponerFicha(ficha);

        

        // //muestro despues de poner una ficha
        // vistaConsola.mostrarTablero(tablero);
        // vistaConsola.mostrarCasillerosDisponibles(tablero);
        // vistaConsola.mostrarAtrilJugador(jugador1);


        // //Elegir una ficha y un casillero
        // vistaConsola.elegirFichaYCasillero(tablero,jugador1);
        

        // //muestro despues de poner una ficha
        // vistaConsola.mostrarTablero(tablero);
        // vistaConsola.mostrarCasillerosDisponibles(tablero);
        // vistaConsola.mostrarAtrilJugador(jugador1);



        // Pruebo el diccionario
        // String palabra= "saldREmos";
        // System.out.println(dic.validarPalabra(palabra));

        // palabra= "e";
        // System.out.println(dic.validarPalabra(palabra));
        



        //Probar jugar  dos turnos

        // vistaConsola.turnoJugador();
        // vistaConsola.turnoJugador();



        //PRobar la carga de fichas -------------------------------------
        // BolsaFichas bolsaDeFichas = new BolsaFichas();
        // for (Ficha ficha : bolsaDeFichas.getFichas()) {
        //     System.out.println(ficha.getLabel()+"-"+ficha.getValue().toString());
        // }

        // bolsaDeFichas.revolver();
        // int i=0;
        //     while(!(bolsaDeFichas.getFichas().isEmpty())) {
        //         i++;
        //         System.out.println();
        //         System.out.println("Saque la Ficha numero "+i);
        //         Ficha fichaSacada = bolsaDeFichas.sacarFicha();
        //         System.out.println();
        //         System.out.println(fichaSacada.getLabel()+"-"+fichaSacada.getValue().toString());
        //     }
            //-----------------------------------------------------

        
        }

}
