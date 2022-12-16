package Modelo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Controlador.Eventos;
import Modelo.Interfaces.IBolsaFichas;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;
import Modelo.Interfaces.IPartida;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import Serializacion.Ranking;
import Serializacion.Serializador;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;


public class Partida extends ObservableRemoto implements IPartida,Serializable{
    private ArrayList<IObservadorRemoto> observadoresRemotos= new ArrayList<IObservadorRemoto>();
    private ArrayList<Ijugador> jugadores;
    private Itablero tablero;
    private IBolsaFichas bolsaConLetras;
    // private ArrayList<ICasillero> casillerosJugadosEnElTurno=new ArrayList<ICasillero>();
    // private ArrayList<IPalabra> palabrasFormadasEnElTurno;
    
    private static Integer turno=0;
    // private ICasillero casilleroElegido=null;
    // private IFicha fichaElegida=null;
    private Integer paso=0;
   
    private final Integer bonus=50;

    public Partida()  throws IOException,RemoteException{
        //inicializo bolsa de letras
        this.bolsaConLetras= BolsaFichas.getInstance();
        //Inicializo jugadores
        this.jugadores= new ArrayList<Ijugador>();
        //inicializo Tablero
        this.tablero = Tablero.getInstance();
    }

    @Override
	public Ijugador agregarJugador(String nombre)throws RemoteException{
        boolean valido=true;
        for (Ijugador j : this.jugadores) {
            if(j.getNombre().equals(nombre)) valido=false;
        }
        Ijugador jugador=null;

        if(valido){
            jugador = new Jugador(nombre); 
            jugadores.add(jugador);
        }
        
        return  jugador ;
    }

    @Override
	public void clearCasillerosJugadosEnElTurno()throws RemoteException{
        this.tablero.clearCasillerosJugadosEnElTurno();
    }

    @Override
	public void agregarCasilleroJugado(ICasillero casillero)throws RemoteException{ 
        this.tablero.agregarCasilleroJugado(casillero);
    }


    
	public ArrayList<ICasillero> palabraHorizontal(ICasillero casillero){ 
        //miro hacia la izquierda
        ICasillero casilleroActual= casillero;
        boolean casilleroValido=true;
        while(casilleroValido){
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
        } while (casilleroActual!=null && casilleroActual.estaOcupado()); 


        return palabra;
    }


    
	public ArrayList<ICasillero> palabraVertical(ICasillero casillero){ 
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
        } while (casilleroActual!=null && casilleroActual.estaOcupado()); 


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


    @Override
	public int calcularPuntajeTurno() throws IOException, RemoteException{ //TODO controlador
        ArrayList<IPalabra> palabrasTurno = new ArrayList<IPalabra>(); 
        this.getJugador().clearPalabrasFormadasEnElturno();
        ArrayList<ICasillero> casillerosConFichaQueNoFormanNada= new ArrayList<ICasillero>();
        int puntaje=0;
        boolean formaAlgo;
        for (ICasillero casillero : this.tablero.getCasillerosJugadosEnElTurno()) {
            formaAlgo=false;
            for (IPalabra palabra : chequearSiLaFichaFormaPalabra(casillero)) {
                System.out.println("Validando si ya se encontro palabra: "+palabra.convertirString());
                
                if(!(Diccionario.estaEnAbecedario(palabra.convertirString())) && palabra.esValida()){
                    if(!estaEnArrayPalabra(palabrasTurno, palabra))  palabrasTurno.add(palabra);
                    formaAlgo=true;
                }
                
                // if(!palabra.esValida() && !(Diccionario.estaEnAbecedario(palabra.convertirString())) ){
                //     //DevolverFichas
                //     casillerosConFichaQueNoFormanNada.add(casillero);
                // }
            } 

            if(!formaAlgo) casillerosConFichaQueNoFormanNada.add(casillero);
        }

        boolean todosSusCasillerosSonValidos;

        for (IPalabra palabra : palabrasTurno) {
            todosSusCasillerosSonValidos= true;
            System.out.printf("Validando %s ...\n",palabra.convertirString());
            for (ICasillero c : casillerosConFichaQueNoFormanNada) {
                System.out.println("Casillero: "+c.getFicha().getLabel()+" No forma NADa");
                for (ICasillero casiPalabra : palabra.getPosiciones()) {
                    if((casiPalabra.getColumna() == c.getColumna())&&(casiPalabra.getFila()==c.getFila())) todosSusCasillerosSonValidos=false;
                }
            }


            if(palabra.esValida() && todosSusCasillerosSonValidos){
                System.out.printf("La palabra %s es valida: %d\n",palabra.convertirString(),palabra.obtenerPuntaje());
                puntaje+=palabra.obtenerPuntaje();
                this.getJugador().agregarPalabraCreadaPorJugador(palabra);
                this.getJugador().agregarPalabraFormadaEnElturno(palabra);
            }else{
                System.out.printf("La palabra %s no es valida\n",palabra.convertirString());
                

            }
        }

        for (ICasillero casillero : casillerosConFichaQueNoFormanNada) {
            IFicha ficha= this.tablero.quitarFicha(casillero.getFila(), casillero.getColumna());
            if(ficha!=null) this.tablero.getCasillerosJugadosEnElTurno().remove(casillero);
            this.getJugador().getAtril().ponerFicha(ficha);
        }
        
        
        return puntaje;
    }


    @Override
	public void finalizarTurno() throws IOException, RemoteException{
        int puntosTurno=this.calcularPuntajeTurno();
        
        //si al finalizar le turno el jugador tiene el atril vacio Gana 50 puntos extra
        if(this.getJugador().getAtril().estaVacio()){
            this.getJugador().sumarPuntos(this.bonus);
        }


        this.getJugador().getAtril().llenarAtril();
        
        this.getJugador().sumarPuntos(puntosTurno);
        
        
        
        if(this.tablero.getCasillerosJugadosEnElTurno().size() <1){
            paso++;
        }else{
            paso=0;
        }
        
        if(paso>(this.jugadores.size()*2-1) || bolsaConLetras.esVacia()){
            System.out.println("Paso 6");
            //finalizar partida???
            this.// Calling the method notifica() on the object obj.
            notificarObservadores(Eventos.FINALIZAR_PARTIDA);
            return;
        }
        else{
            System.out.println("Paso 7");
            this.tablero.clearCasillerosJugadosEnElTurno();
            this.getJugador().getAtril().llenarAtril();
            this.notificarObservadores(Eventos.FINALIZO_TURNO);
            siguienteTurno();
            this.notificarObservadores(Eventos.COMIENZA_TURNO);
        }
        

    }

    public void guardarPartida(){
        Serializador serializador = new Serializador("partida.dat");
        serializador.writeOneObject(this);
    }



    @Override
	public void cambiarFichas(ArrayList<IFicha> fichasACambiar)throws RemoteException{
        this.getJugador().getAtril().intercambiarFichas(fichasACambiar);
        try {
            this.finalizarTurno();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
	public boolean estaEnArrayPalabra(ArrayList<IPalabra> array, IPalabra palabra)throws RemoteException{ //TODO controlador
        boolean esta=false;
        for (IPalabra p : array) {
            if(p.equals(palabra)) esta=true;
        }
        return esta;
    }

    @Override
	public Itablero getTablero()throws RemoteException {
        return this.tablero;
    }

    @Override
	public Ijugador getJugador()throws RemoteException {
        return this.jugadores.get(turno);
    }


    @Override
	public IFicha elegirfichaJugador(int indice)throws RemoteException{   // TODO Desacoplar vista de controlador

        IFicha ficha = this.jugadores.get(turno).getAtril().sacarFichaDeAtril(indice);

        return ficha;
    }


    @Override
	public void siguienteTurno()throws RemoteException{
        turno=(turno+1)%this.jugadores.size();
        this.tablero.reiniciarCasillerosJugados();
    }

    @Override
	public int getPuntaje()throws RemoteException {
        return this.getJugador().getPuntaje();
    }

    @Override
	public void elegirCasillero(ICasillero casillero) throws RemoteException{
        this.tablero.setCasilleroElegido(casillero);
        ICasillero casilleroElegido= this.tablero.getCasilleroElegido();
        IFicha fichaElegida= this.getJugador().getFichaElegida();
        if(casilleroElegido !=null && fichaElegida!=null)
        {
            casilleroElegido.ponerFicha(fichaElegida);
            this.tablero.ponerFicha(casilleroElegido.getFila(), casilleroElegido.getColumna(), fichaElegida);
            

            this.agregarCasilleroJugado(casilleroElegido);
           
            this.tablero.clearCasilleroElegido();
            
            IFicha fichaSacada =this.getJugador().getAtril().sacarFichaDeAtril(fichaElegida);
            System.out.println(fichaSacada.getLabel());
            this.getJugador().clearFichaElegida();
            
            this.notificarObservadores(Eventos.POSICIONO_FICHA);
        }
    }

    @Override
	public void elegirFichaAtril(IFicha ficha)throws RemoteException{
        this.getJugador().setFichaElegida(ficha);
        IFicha fichaElegida= this.getJugador().getFichaElegida();
        ICasillero casilleroElegido= this.tablero.getCasilleroElegido();
        if(casilleroElegido !=null && fichaElegida!=null)
        {
            casilleroElegido.ponerFicha(fichaElegida);
            this.tablero.ponerFicha(casilleroElegido.getFila(), casilleroElegido.getColumna(), fichaElegida);
            

            this.agregarCasilleroJugado(casilleroElegido);
            
            this.tablero.clearCasilleroElegido();
            
            IFicha fichaSacada =this.getJugador().getAtril().sacarFichaDeAtril(fichaElegida);
            System.out.println(fichaSacada.getLabel());
            this.getJugador().clearFichaElegida();
            
            this.notificarObservadores(Eventos.POSICIONO_FICHA);
        }
    }

    @Override
	public ArrayList<IPalabra> getPalabrasValidasDelTurno()throws RemoteException {

        return this.getJugador().getPalabrasValidasDelTurno();
    }

    @Override
    public ArrayList<Ijugador> getJugadores() {
        
        return  this.jugadores;
    }

    @Override
    public void sacarFichaDeCasillero(ICasillero casillero) throws RemoteException {
        
        IFicha ficha= this.tablero.quitarFicha(casillero.getFila(), casillero.getColumna());
        if(ficha!=null) this.tablero.getCasillerosJugadosEnElTurno().remove(this.tablero.getCasillerosJugadosEnElTurno().size()-1);
        
 
        this.getJugador().getAtril().ponerFicha(ficha);

        this.notificarObservadores(Eventos.QUITO_FICHA_CASILLERO);
       
        
    }

    @Override
    public void borrarPartida() {
        File archivo = new File("partida.dat");
        archivo.delete();

    }

    @Override
    public void guardarPuntajes() throws RemoteException {
        Serializador serializador = new Serializador("historial.dat");
        Object r = serializador.readFirstObject();
        if(r==null) r=Ranking.getInstance();
        Ranking ranking = (Ranking) r;
        for (Ijugador j : this.jugadores) { 
            ranking.agregarJugador(j);
        }
        serializador.writeOneObject(ranking);

    }














    



}
