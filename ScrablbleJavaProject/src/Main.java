import Modelo.BolsaFichas;
import Modelo.Casillero;
import Modelo.Diccionario;
import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Tablero;
import Vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        //inicializo
        Tablero tablero= new Tablero();
        VistaConsola vistaConsola = new VistaConsola();
        BolsaFichas bolsa=new BolsaFichas();
        Jugador jugador1 = new Jugador("Agustin",bolsa);
        Jugador jugador2 = new Jugador("Luna",bolsa);



        // //Muestro tablero por primera vez
        // vistaConsola.mostrarTablero(tablero);
        // vistaConsola.mostrarCasillerosDisponibles(tablero);
        // vistaConsola.mostrarAtrilJugador(jugador1);

        // //pongo una ficha
        // Ficha ficha= bolsa.sacarFicha();
        // System.out.println(ficha.getLabel());
        // tablero.getCasilleros()[0][1].ponerFicha(ficha);

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
        vistaConsola.turnoJugador(tablero, jugador1);

        vistaConsola.turnoJugador(tablero, jugador2);



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
