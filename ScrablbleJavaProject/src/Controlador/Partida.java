package Controlador;
import java.io.IOException;
import java.util.ArrayList;

import Modelo.BolsaFichas;
import Modelo.Jugador;
import Modelo.Tablero;
import Modelo.Casillero;
import Modelo.Diccionario;
import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Palabra;
import Modelo.Tablero;
import Modelo.TipoEspecial;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IPalabra;

public class Partida {

    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private BolsaFichas bolsaConLetras;
    private boolean continuarPartida;
    private ArrayList<ICasillero> casillerosJugadosEnElTurno=new ArrayList<ICasillero>();
    private Diccionario diccionario;
    private static Integer turno=0;


    public Partida() throws IOException{
        //Inicializo jugadores
        this.jugadores=new ArrayList<Jugador>();
        //inicializo Tablero
        this.tablero = new Tablero();
        //inicializo bolsa de letras
        this.bolsaConLetras= new BolsaFichas();
        //inicializo diccionario
        this.diccionario = new Diccionario();

    }

    public void agregarJugador(String nombre){
        Jugador jugador= new Jugador(nombre, bolsaConLetras); // TODO ¿Hace falta la bolas de fichas?¿No se podria manejar todo desde las variables del controlador?
        jugadores.add(jugador);
    }

    public void clearCasillerosJugadosEnElTurno(){
        this.casillerosJugadosEnElTurno.clear();
    }

    public void agregarCasilleroJugado(ICasillero casillero){ //TODO si esto lo ejecuta la vista , la vista necesitaria conocer al casillero y eso no es correcto. Cambiar
        this.casillerosJugadosEnElTurno.add(casillero);
    }


    public ArrayList<ICasillero> palabraHorizontal(ICasillero casillero){ //TODO ¿Son necesario los parametros?¿Se podria hacer con this?
        //miro hacia la izquierda
        ICasillero casilleroActual= casillero;
        boolean casilleroValido=true;
        while(casilleroValido){
            System.out.printf("Probando el casillero %d-%d: %s\n",casilleroActual.getFila(),casilleroActual.getColumna(),casilleroActual.getFicha().getLabel());
            if(this.tablero.getCasillero(casilleroActual.getFila(), casilleroActual.getColumna()-1).getFicha()!=null){
                casilleroActual= this.tablero.getCasillero(casilleroActual.getFila(), casilleroActual.getColumna()-1);
            }else{
                casilleroValido=false;
            }
        }
    
        ArrayList<ICasillero> palabra= new ArrayList<ICasillero>();
        
        do {
            palabra.add(casilleroActual);
            casilleroActual= this.tablero.getCasillero(casilleroActual.getFila(), casilleroActual.getColumna()+1);
        } while (casilleroActual.estaOcupado()); 


        return palabra;
    }


    public ArrayList<ICasillero> palabraVertical(ICasillero casillero){ //TODO ¿Son necesario los parametros?¿Se podria hacer con this?
        //miro hacia la izquierda
        ICasillero casilleroActual= casillero;
        boolean casilleroValido=true;
        while(casilleroValido){
            if(this.tablero.getCasillero(casilleroActual.getFila()-1, casilleroActual.getColumna()).getFicha()!=null){
                casilleroActual= this.tablero.getCasillero(casilleroActual.getFila()-1, casilleroActual.getColumna());
            }else{
                casilleroValido=false;
            }
        }
    
        ArrayList<ICasillero> palabra= new ArrayList<ICasillero>();
        
        do {
            palabra.add(casilleroActual);
            casilleroActual= this.tablero.getCasillero(casilleroActual.getFila()+1, casilleroActual.getColumna());
        } while (casilleroActual.estaOcupado()); 


        return palabra;
    }



    public ArrayList<IPalabra> chequearSiLaFichaFormaPalabra(ICasillero casillero){ //TODO controlador
        
        Palabra palabraHorizontal;
        Palabra palabraVertical;
        ArrayList<IPalabra> palabras = new ArrayList<IPalabra>();
        palabraHorizontal= new Palabra(palabraHorizontal(casillero));
        palabraVertical= new Palabra(palabraVertical(casillero));
        palabras.add(palabraHorizontal);
        palabras.add(palabraVertical);
        
        return palabras;
    }


    public int calcularPuntajeTurno() throws IOException{ //TODO controlador
        ArrayList<IPalabra> palabrasFormadas = new ArrayList<IPalabra>();  
        for (ICasillero casillero : casillerosJugadosEnElTurno) {
            for (IPalabra palabra : chequearSiLaFichaFormaPalabra(casillero)) {
                System.out.println("Validando si ya se encontro palabra: "+palabra.convertirString());
                if(!estaEnArrayPalabra(palabrasFormadas, palabra)){
                    palabrasFormadas.add(palabra);
                } 
            } 
        }

        int puntaje=0;
        for (IPalabra palabra : palabrasFormadas) {
            System.out.printf("Validando %s ...\n",palabra.convertirString());
            if(palabra.esValida()){
                System.out.printf("La palabra %s es valida: %d\n",palabra.convertirString(),palabra.obtenerPuntaje());
                puntaje+=palabra.obtenerPuntaje();
            }else{
                System.out.printf("La palabra %s no es valida\n",palabra.convertirString());

            }
        }

        return puntaje;
    }


    public boolean estaEnArrayPalabra(ArrayList<IPalabra> array, IPalabra palabra){ //TODO controlador
        boolean esta=false;
        for (IPalabra p : array) {
            if(p.equals(palabra)) esta=true;
        }
        return esta;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public Jugador getJugador() {
        return this.jugadores.get(turno);
    }











}
