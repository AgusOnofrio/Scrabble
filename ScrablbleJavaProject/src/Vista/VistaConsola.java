package Vista;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import Controlador.ScrabbleController;
import Modelo.Tablero;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
public class VistaConsola implements IVista {
    private ScrabbleController controlador;
    private String jugadorActual;

    public VistaConsola(){}

    public void setControlador(ScrabbleController controlador){
        this.controlador=controlador;
    }

    public void iniciar(){
        this.mostrarTurno();
    }




    private void mostrarTurno() {
        this.mostrarTablero();
        this.mostrarCasillerosDisponibles(controlador.getTablero());
        this.mostrarAtrilJugador();

        
        this.jugadorActual=this.controlador.getJugadorActual().getNombre();
        System.out.println("Es mi turno?");
        if(this.jugadorActual.equals(this.controlador.getJugadorVista().getNombre())){
            System.out.println("1-Posicionar ficha");
            System.out.println("2-Cambiar fichas");
            System.out.println("0-Finalizar turno");
            Scanner sc= new Scanner(System.in);
            Integer op = sc.nextInt();
            sc.nextLine();
            // sc.close();
            switch (op) {
                case 1:
                    this.controlador.elegirFichaAtril(this.elegirfichaJugador()); 
                    this.controlador.elegirCasillero(this.elegirCasilleroDisponible(this.controlador.getTablero())); 
                    break;
                case 2:
                    this.controlador.cambiarFichas(this.controlador.getJugadorVista().getAtril().getFichasAtril());
                break;
                case 0:
                    try {
                        this.controlador.finalizarTurno();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
                default:
                this.mostrarTurno();
                    break;
            }
        }
    
    }

    public void mostrarCasillero(ICasillero casillero){
        IFicha ficha = casillero.getFicha();
        if(ficha!=null){
            if(ficha.getLabel().equals(".")){
				System.out.printf("%3s","#");
			}else{
				System.out.printf("%3s",ficha.getLabel());
			}
            
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

    public void mostrarTablero(){
        Itablero tablero= this.controlador.getTablero();
        ICasillero[][] casilleros = tablero.getCasilleros();
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

    public ICasillero elegirCasilleroDisponible(Itablero tablero){ 
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println("Estos son los casilleros donde podes ubicar una ficha");
        ArrayList<ICasillero> casillerosDisponibles = tablero.casillerosDisponibles();
        do {
            
            String indices = " ";
            Integer i =0;
            for (ICasillero c : casillerosDisponibles) {
                System.out.printf("%d-%d | ",c.getFila(),c.getColumna());
                indices+= ""+i.toString()+"  |  ";
                i++;
            } 
            System.out.println();
            System.out.println(indices);

            System.out.println("Elegi un casillero:");
            opcion = sc.nextInt();
            sc.nextLine();


        } while (opcion<0 || opcion> tablero.casillerosDisponibles().size());
        // sc.close();
        ICasillero casillero = casillerosDisponibles.get(opcion);

        return casillero;
    }


    public void mostrarCasillerosDisponibles(Itablero itablero){ 

        System.out.println("Estos son los casilleros donde podes ubicar una ficha");
        ArrayList<ICasillero> casillerosDisponibles = itablero.casillerosDisponibles();

          
        String indices = " ";
        Integer i =0;
        for (ICasillero c : casillerosDisponibles) {
            System.out.printf("%d-%d | ",c.getFila(),c.getColumna());
            indices+= ""+i.toString()+"  |  ";
            i++;
        } 
        System.out.println();
        System.out.println(indices);

    }




    public IFicha elegirfichaJugador(){   
        ArrayList<IFicha> fichas = this.controlador.getJugadorVista().getAtril().getFichasAtril();
        int opcion;
        do {
            Scanner sc = new Scanner(System.in);
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
            sc.nextLine();
            
        } while (opcion<0 || opcion> fichas.size());
        // sc.close();
        IFicha ficha = this.controlador.getJugadorVista().getAtril().sacarFichaDeAtril(opcion);

        return ficha;
    }


    public void mostrarAtrilJugador(){
        Ijugador jugador= this.controlador.getJugadorVista();
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





    public void mostrarPuntos(){
       System.out.println("Jugador "+this.controlador.getJugadorActual().getNombre()+" : "+this.controlador.mostraPuntaje()+" puntos");
    }

    @Override
    public void actualizarVista() {

        this.mostrarTurno();
        
    }

    @Override
    public void setCambiarFichas(boolean b) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mostrarFinDeturno() {
        
        System.out.println();
        System.out.println("Jugador: "+this.jugadorActual);
        Integer puntajeTurno=0;
        for (IPalabra palabra : this.controlador.getPalabrasValidasDelTurno()) {
            System.out.println(palabra.convertirString()+" - "+palabra.obtenerPuntaje());
            puntajeTurno+=palabra.obtenerPuntaje();
        }
        System.out.println("Puntaje turno: "+puntajeTurno);

        Scanner sc= new Scanner(System.in);
        sc.nextLine();
        // sc.close();
        
        
        
    }

    @Override
    public void setControlador(IControladorRemoto controlador) {
        this.controlador=(ScrabbleController)controlador;
        
    }

    @Override
    public void mostrarResultadoFinal() {
        System.out.println("FIN DEL JUEGO");
        System.out.println();
        String resultados="";
        Integer ganador=0;
        ArrayList<Ijugador> jugadores;
       
            try {
                jugadores = this.controlador.getJugadores();
                Collections.sort(jugadores, (Comparator.<Ijugador>
                            comparingInt(jugador1 -> -jugador1.getPuntaje())
                .thenComparingInt(jugador2 -> jugador2.getPuntaje())));
    
                
                
    
    
                for (Ijugador j :  jugadores ) {
                    System.out.println(j.getNombre()+"      "+"Puntaje:"+j.getPuntaje().toString());
                    for (IPalabra palabra : j.obtenerPalabrasDePartida()) {
                        System.out.println(palabra.convertirString()+" - "+palabra.obtenerPuntaje());
                    }
                    System.out.println();
    
                }

                controlador.guardarPuntajes();
                System.exit(0);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        
    }

    @Override
    public void actualizarJugadores() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void comenzarTurno() {
        this.mostrarTurno();
        
    }







}
