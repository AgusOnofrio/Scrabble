package Modelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Controlador.Eventos;
import Modelo.Interfaces.IBolsaFichas;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import pruebaObserverSimple.Observado;

public class Partida implements Observado{
    private ArrayList<pruebaObserverSimple.Observer> observadores= new ArrayList<pruebaObserverSimple.Observer>();;
    private ArrayList<Ijugador> jugadores;
    private Itablero tablero;
    private IBolsaFichas bolsaConLetras;
    private boolean continuarPartida;
    private ArrayList<ICasillero> casillerosJugadosEnElTurno=new ArrayList<ICasillero>();
    private Diccionario diccionario;
    private static Integer turno=0;
    private ICasillero casilleroElegido=null;
    private IFicha fichaElegida=null;


    public Partida()  throws IOException{
        //inicializo bolsa de letras
        this.bolsaConLetras= new BolsaFichas();
        //Inicializo jugadores
        this.jugadores= new ArrayList<Ijugador>();
        //inicializo Tablero
        this.tablero = new Tablero();
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

    public void agregarCasilleroJugado(ICasillero casillero){ 
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
        this.notificarObservers(this,Eventos.POSICIONO_FICHA);
        this.getJugador().sumarPuntos(puntaje);
        return puntaje;
    }


    public boolean estaEnArrayPalabra(ArrayList<IPalabra> array, IPalabra palabra){ //TODO controlador
        boolean esta=false;
        for (IPalabra p : array) {
            if(p.equals(palabra)) esta=true;
        }
        return esta;
    }

    public Itablero getTablero() {
        return this.tablero;
    }

    public Ijugador getJugador() {
        return this.jugadores.get(turno);
    }

    public IFicha elegirfichaJugador(int indice){   // TODO Desacoplar vista de controlador

        IFicha ficha = this.jugadores.get(turno).getAtril().sacarFichaDeAtril(indice);

        return ficha;
    }


    public void siguienteTurno(){
        turno=(turno+1)%this.jugadores.size();
    }

    public int getPuntaje() {
        return this.getJugador().getPuntaje();
    }

    public void elegirCasillero(ICasillero casillero){
        this.casilleroElegido=casillero;
        if(this.casilleroElegido !=null && this.fichaElegida!=null)
        {
            this.casilleroElegido.ponerFicha(fichaElegida);
            this.casilleroElegido=null;
            this.getJugador().getAtril().sacarFichaDeAtril(fichaElegida);
            this.fichaElegida=null;
            this.notificarObservers(this, null);
        }
    }

    public void elegirFichaAtril(IFicha ficha){
        this.fichaElegida=ficha;
        if(this.casilleroElegido !=null && this.fichaElegida!=null)
        {
            this.casilleroElegido.ponerFicha(fichaElegida);
            this.casilleroElegido=null;
            this.getJugador().getAtril().sacarFichaDeAtril(fichaElegida);
            this.fichaElegida=null;
            this.notificarObservers(this, null);
        }
    }





    //Observable

    @Override
    public void notificarObservers(Object data,Eventos evento) {
        for(pruebaObserverSimple.Observer o : observadores){
            o.update(data,evento); 
         }
        
    }

    @Override
    public void agregarObservador(Object t) {
        this.observadores.add((pruebaObserverSimple.Observer)t);
        
    }

    @Override
    public void quitarObservador(Object t) {
        // TODO Auto-generated method stub
        
    }



}
