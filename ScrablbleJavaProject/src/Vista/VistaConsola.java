package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Casillero;
import Modelo.Diccionario;
import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Palabra;
import Modelo.Tablero;
import Modelo.TipoEspecial;

public class VistaConsola {
    


    private ArrayList<Casillero> casillerosJugadosEnElTurno=new ArrayList<Casillero>();



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


    public void mostrarCasillero(Casillero casillero){
        Ficha ficha = casillero.getFicha();
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

    public Casillero elegirCasilleroDisponible(Tablero tablero){
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




    public Ficha elegirfichaJugador(Jugador jugador){
        Scanner sc = new Scanner(System.in);
        int opcion;
        ArrayList<Ficha> fichas = jugador.getAtril().getFichasAtril();
        do {
            String indices = "";
            Integer i =0;
            System.out.println("Estas son tu fichas: ");
            for (Ficha ficha : fichas) {
                System.out.printf("%s | ",ficha.getLabel());
                indices+= i.toString()+" | ";
                i++;
            }
            System.out.println();
            System.out.println(indices);

            System.out.println("Elegi una Ficha:");
            opcion = sc.nextInt();
            
        } while (opcion<0 || opcion> fichas.size());

        Ficha ficha = jugador.getAtril().sacarFichaDeAtril(opcion);

        return ficha;
    }


    public void mostrarAtrilJugador(Jugador jugador){

        ArrayList<Ficha> fichas = jugador.getAtril().getFichasAtril();
        String indices = "";
        Integer i =0;
        System.out.println("Estas son tu fichas: ");
        for (Ficha ficha : fichas) {
            System.out.printf("%s | ",ficha.getLabel());
            indices+= i.toString()+" | ";
            i++;
        }
        System.out.println();
        System.out.println(indices);
    }




    public void elegirFichaYCasillero(Tablero tablero, Jugador jugador) {
        Casillero casillero;
        Ficha ficha;

        
        casillero = this.elegirCasilleroDisponible(tablero);
        ficha= this.elegirfichaJugador(jugador);

        casillero.ponerFicha(ficha);
        casillerosJugadosEnElTurno.add(casillero);
    }



    // el turno finaliza cuando:
    // -El jugador finaliza el turno 
    // -El jugador se queda sin fichas
    // -El jugador intercambia fichas
    public void turnoJugador(Tablero tablero, Jugador jugador) throws IOException{
        Scanner sc = new Scanner(System.in);
        int opcion=1;
        casillerosJugadosEnElTurno.clear();
        mostrarTablero(tablero);
        mostrarCasillerosDisponibles(tablero);
        mostrarAtrilJugador(jugador);
        System.out.println("Elegir: 1-jugar 0-Finalizar turno");
        opcion = sc.nextInt();

        while (jugador.getAtril().getFichasAtril().size()>0 && opcion!=0){
            mostrarTablero(tablero);
            mostrarCasillerosDisponibles(tablero);
            mostrarAtrilJugador(jugador);
            elegirFichaYCasillero(tablero, jugador);
            System.out.println("Elegir: 1-jugar 0-Finalizar turno");
            opcion = sc.nextInt();
        }

        //chequear las palabras formadas
       int puntajeTurno= calcularPuntajeTurno(tablero);

       System.out.println("El puntaje del turno fue: "+puntajeTurno);

    }

    public ArrayList<Casillero> palabraHorizontal(Tablero tablero,Casillero casillero){
        //miro hacia la izquierda
        Casillero casilleroActual= casillero;
        boolean casilleroValido=true;
        while(casilleroValido){
            System.out.printf("Probando el casillero %d-%d: %s\n",casilleroActual.getFila(),casilleroActual.getColumna(),casilleroActual.getFicha().getLabel());
            if(tablero.getCasillero(casilleroActual.getFila(), casilleroActual.getColumna()-1).getFicha()!=null){
                casilleroActual= tablero.getCasillero(casilleroActual.getFila(), casilleroActual.getColumna()-1);
            }else{
                casilleroValido=false;
            }
        }
    
        ArrayList<Casillero> palabra= new ArrayList<Casillero>();
        
        do {
            palabra.add(casilleroActual);
            casilleroActual= tablero.getCasillero(casilleroActual.getFila(), casilleroActual.getColumna()+1);
        } while (casilleroActual.estaOcupado()); 


        return palabra;
    }


    public ArrayList<Casillero> palabraVertical(Tablero tablero,Casillero casillero){
        //miro hacia la izquierda
        Casillero casilleroActual= casillero;
        boolean casilleroValido=true;
        while(casilleroValido){
            if(tablero.getCasillero(casilleroActual.getFila()-1, casilleroActual.getColumna()).getFicha()!=null){
                casilleroActual= tablero.getCasillero(casilleroActual.getFila()-1, casilleroActual.getColumna());
            }else{
                casilleroValido=false;
            }
        }
    
        ArrayList<Casillero> palabra= new ArrayList<Casillero>();
        
        do {
            palabra.add(casilleroActual);
            casilleroActual= tablero.getCasillero(casilleroActual.getFila()+1, casilleroActual.getColumna());
        } while (casilleroActual.estaOcupado()); 


        return palabra;
    }



    public ArrayList<Palabra> chequearSiLaFichaFormaPalabra(Tablero tablero,Casillero casillero){
        
        Palabra palabraHorizontal;
        Palabra palabraVertical;
        ArrayList<Palabra> palabras = new ArrayList<Palabra>();
        palabraHorizontal= new Palabra(palabraHorizontal(tablero, casillero));
        palabraVertical= new Palabra(palabraVertical(tablero, casillero));
        palabras.add(palabraHorizontal);
        palabras.add(palabraVertical);
        
        return palabras;
    }


    public int calcularPuntajeTurno(Tablero tablero) throws IOException{
        ArrayList<Palabra> palabrasFormadas = new ArrayList<Palabra>(); 
        for (Casillero casillero : casillerosJugadosEnElTurno) {
            for (Palabra palabra : chequearSiLaFichaFormaPalabra(tablero, casillero)) {
                System.out.println("Validando si ya se encontro palabra: "+palabra.convertirString());
                if(!estaEnArrayPalabra(palabrasFormadas, palabra)){
                    palabrasFormadas.add(palabra);
                } 
            } 
        }

        int puntaje=0;
        for (Palabra palabra : palabrasFormadas) {
            System.out.printf("Validando %s ...\n",palabra.convertirString());
            if(palabra.esValida(tablero.diccionario)){
                System.out.printf("La palabra %s es valida: %d\n",palabra.convertirString(),palabra.obtenerPuntaje());
                puntaje+=palabra.obtenerPuntaje();
            }else{
                System.out.printf("La palabra %s no es valida\n",palabra.convertirString());

            }
        }

        return puntaje;
    }


    public boolean estaEnArrayPalabra(ArrayList<Palabra> array, Palabra palabra){
        boolean esta=false;
        for (Palabra p : array) {
            if(p.equals(palabra)) esta=true;
        }
        return esta;
    }


}
