package Modelo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;


public class Partida extends ObservableRemoto implements IPartida,Serializable{
    private ArrayList<IObservadorRemoto> observadoresRemotos= new ArrayList<IObservadorRemoto>();
    private ArrayList<Ijugador> jugadores;
    private Itablero tablero;
    private IBolsaFichas bolsaConLetras;
    private ArrayList<ICasillero> casillerosJugadosEnElTurno=new ArrayList<ICasillero>();
    private ArrayList<IPalabra> palabrasFormadasEnElTurno;
    
    private static Integer turno=0;
    private ICasillero casilleroElegido=null;
    private IFicha fichaElegida=null;
    private Integer paso=0;
    private ICasillero ultimoCasilleroJugado=null;
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
            jugador = new Jugador(nombre); // TODO ¿Hace falta la bolas de fichas?¿No se podria manejar todo desde las variables del controlador?
            jugadores.add(jugador);
        }
        
        return  jugador ;
    }

    @Override
	public void clearCasillerosJugadosEnElTurno()throws RemoteException{
        this.casillerosJugadosEnElTurno.clear();
    }

    @Override
	public void agregarCasilleroJugado(ICasillero casillero)throws RemoteException{ 
        this.casillerosJugadosEnElTurno.add(casillero);
    }


    
	public ArrayList<ICasillero> palabraHorizontal(ICasillero casillero){ //TODO ¿Son necesario los parametros?¿Se podria hacer con this?
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
        this.palabrasFormadasEnElTurno =  new ArrayList<IPalabra>(); 
        ArrayList<ICasillero> casillerosConFichaQueNoFormanNada= new ArrayList<ICasillero>();
        int puntaje=0;
        boolean formaAlgo;
        for (ICasillero casillero : casillerosJugadosEnElTurno) {
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
                this.palabrasFormadasEnElTurno.add(palabra);
            }else{
                System.out.printf("La palabra %s no es valida\n",palabra.convertirString());
                

            }
        }

        for (ICasillero casillero : casillerosConFichaQueNoFormanNada) {
            IFicha ficha= this.tablero.quitarFicha(casillero.getFila(), casillero.getColumna());
            if(ficha!=null) this.casillerosJugadosEnElTurno.remove(casillero);
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
        
        this.notificarObservadores(Eventos.FINALIZO_TURNO);
        

        if(this.casillerosJugadosEnElTurno.size() <1){
            paso++;
        }else{
            paso=0;
        }
        
        if(paso>(this.jugadores.size()*2-1) || bolsaConLetras.esVacia()){
            System.out.println("Paso 6");
            //finalizar partida???
            this.notificarObservadores(Eventos.FINALIZAR_PARTIDA);
         }
        else{
            System.out.println("Paso 7");
            this.casillerosJugadosEnElTurno= new ArrayList<ICasillero>() ;
            this.getJugador().getAtril().llenarAtril();
            siguienteTurno();

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
        this.casilleroElegido=casillero;
        System.out.println("Entro a elegir casillero del modelo");
        if(this.casilleroElegido !=null && this.fichaElegida!=null)
        {
            this.casilleroElegido.ponerFicha(fichaElegida);
            this.tablero.ponerFicha(casilleroElegido.getFila(), casilleroElegido.getColumna(), fichaElegida);
            

            this.agregarCasilleroJugado(casilleroElegido);
            this.ultimoCasilleroJugado=casilleroElegido;
            this.casilleroElegido=null;
            
            IFicha fichaSacada =this.getJugador().getAtril().sacarFichaDeAtril(fichaElegida);
            System.out.println(fichaSacada.getLabel());
            this.fichaElegida=null;
            
            this.notificarObservadores(Eventos.POSICIONO_FICHA);
        }
    }

    @Override
	public void elegirFichaAtril(IFicha ficha)throws RemoteException{
        this.fichaElegida=ficha;
        if(this.casilleroElegido !=null && this.fichaElegida!=null)
        {
            if(fichaElegida instanceof FichaEspecial) this.notificarObservadores(Eventos.ELEGIR_LETRA);
            this.casilleroElegido.ponerFicha(fichaElegida);
            this.tablero.ponerFicha(casilleroElegido.getFila(),casilleroElegido.getColumna(),fichaElegida);

            this.agregarCasilleroJugado(casilleroElegido);
            this.ultimoCasilleroJugado=casilleroElegido;
            this.casilleroElegido=null;
            
            IFicha fichaSacada =this.getJugador().getAtril().sacarFichaDeAtril(fichaElegida);
            System.out.println(fichaSacada.getLabel());
            this.fichaElegida=null;
            
            this.notificarObservadores(Eventos.POSICIONO_FICHA);
        }
    }

    @Override
	public ArrayList<IPalabra> getPalabrasValidasDelTurno()throws RemoteException {

        return this.palabrasFormadasEnElTurno;
    }

    @Override
    public ArrayList<Ijugador> getJugadores() {
        
        return  this.jugadores;
    }

    @Override
    public void sacarFichaDeCasillero(ICasillero casillero) throws RemoteException {
        
        IFicha ficha= this.tablero.quitarFicha(casillero.getFila(), casillero.getColumna());
        if(ficha!=null) this.casillerosJugadosEnElTurno.remove(this.casillerosJugadosEnElTurno.size()-1);
        
 
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

        // try {
        //     ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("historial.dat"));
        //     SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        //     for (Ijugador j : this.jugadores) {
        //         Date fecha = new Date(System.currentTimeMillis());
        //         oos.writeChars(j.getNombre()+" - "+j.getPuntaje()+" "+formatter.format(fecha));
        //     }
        //     oos.close();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        Serializador serializador = new Serializador("historial.dat");
        Object r = serializador.readFirstObject();
        if(r==null) r=Ranking.getInstance();
        Ranking ranking = (Ranking) r;
        for (Ijugador j : this.jugadores) { 
            ranking.agregarJugador(j);
        }
        serializador.writeOneObject(ranking);


         
        // SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        // Date date = new Date(System.currentTimeMillis());
        // System.out.println(formatter.format(date));
        //     for (Ijugador j : this.jugadores) { 
        //         if(!tieneCabecera){
        //             JugadorHistorial jugadorHistorial = new JugadorHistorial(j.getNombre(), j.getPuntaje(),date );
        //             serializador.writeOneObject(jugadorHistorial);
        //             tieneCabecera=true;
        //         }else{
        //             JugadorHistorial jugadorHistorial = new JugadorHistorial(j.getNombre(), j.getPuntaje(),date );
        //             serializador.addOneObject(jugadorHistorial);
        //         }
		// 	}
		


    }



// // // Observable Remoto
//     @Override
//     public void notificarObservadores(Object obj ) throws RemoteException {
//         for (IObservadorRemoto o: this.observadoresRemotos) {
//             o.actualizar((IObservableRemoto) this, obj);
//             // new Thread(new Runnable() {
//             //     @Override
//             //     public void run() {
//             //         try {
//             //             o.actualizar((IObservableRemoto) this, obj);
//             //         } catch (RemoteException e) {
//             //             System.out.println("ERROR: notificando al observador.");
//             //             e.printStackTrace();
//             //         }
//             //     }
//             // }).start();
//         }
//     }








    



}
