package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Controlador.Partida;
import Modelo.Casillero;
import Modelo.Diccionario;
import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Palabra;
import Modelo.Tablero;
import Modelo.TipoEspecial;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
public class VistaConsola {
    


    private Partida partida;

    public VistaConsola(Partida partida){
        this.partida=partida;
    }



    public int menuPrincipal(){
        int opcion;
        do {
            System.out.println("----------------------------SCRABBLE----------------------------");
            System.out.println("1-Comenzar Partida");
            System.out.println("0-Salir");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
        } while (opcion > 1 || opcion <0);
        return opcion;
    }

    

    public int menuJugadores(){
        int opcion;
        do {
            System.out.printf("%20","Elegi el numero de jugadores: ");
            System.out.println("1- 2 jugadores");
            System.out.println("2- 3 jugadores");
            System.out.println("3- 4 jugadores");
            System.out.println("0- Salir");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
        } while (opcion<0 || opcion > 3);
        return opcion;
    }


    public void mostrarCasillero(ICasillero casillero){
        IFicha ficha = casillero.getFicha();
        if(ficha!=null){
            System.out.printf("%3s",ficha.getLabel());
        }else{
            switch (casillero.getTipoEspecial()) {
                case SIMPLE:
                    System.out.printf("%3s",".");
                    break;
                case DOBLE_LETRA:
                    System.out.printf("%3s","2L");
                break;
                case TRIPLE_LETRA:
                System.out.printf("%3s","3L");
                break;
                case TRIPLE_PALABRA:
                System.out.printf("%3s","3P");
                break;
                case DOBLE_PALABRA:
                System.out.printf("%3s","2P");
                break;
                default:
                System.out.printf("%3s",".");
                    break;
            }
        }
       
        System.out.printf("");
    }

    public void mostrarTablero(Tablero tablero){
        Casillero[][] casilleros = tablero.getCasilleros();
        System.out.printf("      0   1   2   3   4   5   6   7   8   9  10  11  12  13  14");
        System.out.println();
        for (int i = 0; i < Tablero.TAMANIO; i++) {
            System.out.printf("%-3s ",i);
            for (int j = 0; j < Tablero.TAMANIO; j++) {
                this.mostrarCasillero(casilleros[i][j]);
                System.out.printf(" ");
            }
            System.out.println();
        }
    }

    public Casillero elegirCasilleroDisponible(Itablero tablero){ // TODO Desacoplar vista de controlador
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println("Estos son los casilleros donde podes ubicar una ficha");
        ArrayList<Casillero> casillerosDisponibles = tablero.casillerosDisponibles();
        do {
            
            String indices = " ";
            Integer i =0;
            for (Casillero c : casillerosDisponibles) {
                System.out.printf("%d-%d | ",c.getFila(),c.getColumna());
                indices+= ""+i.toString()+"  |  ";
                i++;
            } 
            System.out.println();
            System.out.println(indices);

            System.out.println("Elegi un casillero:");
            opcion = sc.nextInt();


        } while (opcion<0 || opcion> tablero.casillerosDisponibles().size());

        Casillero casillero = casillerosDisponibles.get(opcion);

        return casillero;
    }


    public void mostrarCasillerosDisponibles(Tablero tablero){ 

        System.out.println("Estos son los casilleros donde podes ubicar una ficha");
        ArrayList<Casillero> casillerosDisponibles = tablero.casillerosDisponibles();

          
        String indices = " ";
        Integer i =0;
        for (Casillero c : casillerosDisponibles) {
            System.out.printf("%d-%d | ",c.getFila(),c.getColumna());
            indices+= ""+i.toString()+"  |  ";
            i++;
        } 
        System.out.println();
        System.out.println(indices);

    }




    public IFicha elegirfichaJugador(Ijugador jugador){   // TODO Desacoplar vista de controlador
        Scanner sc = new Scanner(System.in);
        int opcion;
        ArrayList<IFicha> fichas = jugador.getAtril().getFichasAtril();
        do {
            String indices = "";
            Integer i =0;
            System.out.println("Estas son tu fichas: ");
            for (IFicha ficha : fichas) {
                System.out.printf("%s | ",ficha.getLabel());
                indices+= i.toString()+" | ";
                i++;
            }
            System.out.println();
            System.out.println(indices);

            System.out.println("Elegi una Ficha:");
            opcion = sc.nextInt();
            
        } while (opcion<0 || opcion> fichas.size());

        IFicha ficha = jugador.getAtril().sacarFichaDeAtril(opcion);

        return ficha;
    }


    public void mostrarAtrilJugador(Ijugador jugador){

        ArrayList<IFicha> fichas = jugador.getAtril().getFichasAtril();
        String indices = "";
        Integer i =0;
        System.out.println("Estas son tu fichas: ");
        for (IFicha ficha : fichas) {
            System.out.printf("%s | ",ficha.getLabel());
            indices+= i.toString()+" | ";
            i++;
        }
        System.out.println();
        System.out.println(indices);
    }




    public void elegirFichaYCasillero(Itablero tablero, Ijugador jugador) { // TODO Desacoplar vista de controlador
        ICasillero casillero;
        IFicha ficha;

        
        casillero = this.elegirCasilleroDisponible(tablero);
        ficha= this.elegirfichaJugador(jugador);

        casillero.ponerFicha(ficha);
        partida.agregarCasilleroJugado(casillero);
    }



    // el turno finaliza cuando:
    // -El jugador finaliza el turno 
    // -El jugador se queda sin fichas
    // -El jugador intercambia fichas
    public void turnoJugador() throws IOException{ // TODO Desacoplar vista de controlador
        Scanner sc = new Scanner(System.in);
        int opcion=1;
        partida.clearCasillerosJugadosEnElTurno();
        System.out.println("Turno "+partida.getJugador().getNombre());
        mostrarTablero(this.partida.getTablero());
        mostrarCasillerosDisponibles(this.partida.getTablero());
        mostrarAtrilJugador(this.partida.getJugador());
        System.out.println("Elegir: 1-jugar 0-Finalizar turno");
        opcion = sc.nextInt();

        while (this.partida.getJugador().getAtril().getFichasAtril().size()>0 && opcion!=0){
            mostrarTablero(this.partida.getTablero());
            mostrarCasillerosDisponibles(this.partida.getTablero());
            mostrarAtrilJugador(this.partida.getJugador());
            elegirFichaYCasillero(this.partida.getTablero(), this.partida.getJugador());
            System.out.println("Elegir: 1-jugar 0-Finalizar turno");
            opcion = sc.nextInt();
        }

        //chequear las palabras formadas
       int puntajeTurno= partida.calcularPuntajeTurno();

       System.out.println("El puntaje del turno fue: "+puntajeTurno);

    }


}
