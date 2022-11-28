package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Controlador.Eventos;
import Controlador.ScrabbleController;
import Modelo.Casillero;
import Modelo.Tablero;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import pruebaObserverSimple.Observer;
public class VistaConsola implements IVista,Observer {
    


    private ScrabbleController controlador;

    public VistaConsola(ScrabbleController controlador){
        this.controlador=controlador;
    }

    public void iniciar(){
        // this.mostrarTablero(controlador.getTablero());
        // this.mostrarCasillerosDisponibles(controlador.getTablero());
        // this.mostrarAtrilJugador(controlador.getJugador());
        // this.elegirFichaYCasillero(controlador);
        int opcion=menuPrincipal();
        if(opcion ==1){ 
            opcion=menuJugadores();
            if(opcion!=0){
                this.inicializarJugadores(opcion);
                try {
                    this.turnoJugador();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    private void inicializarJugadores(int cantidad) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        for (int i = 0; i < cantidad; i++) {
            System.out.printf("Nombre del jugador %d: ",i+1);
            nombre = sc.nextLine();
            this.controlador.agregarJugador(nombre);
        }
        sc.close();
    }

    public int menuPrincipal(){
        int opcion;
        do {
            System.out.println("----------------------------SCRABBLE----------------------------");
            System.out.println("1-Comenzar Partida");
            System.out.println("0-Salir");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
            sc.close();
        } while (opcion > 1 || opcion <0);
        

        return opcion;
    }

    

    public int menuJugadores(){
        int opcion;
        do {
            System.out.println("Elegi el numero de jugadores: ");
            System.out.println(" 2 jugadores");
            System.out.println(" 3 jugadores");
            System.out.println(" 4 jugadores");
            System.out.println("0- Salir");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
            sc.close();
        } while (opcion<0 || opcion > 4 || opcion==1);
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

    public void mostrarTablero(Itablero tablero){
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
        sc.close();
        Casillero casillero = casillerosDisponibles.get(opcion);

        return casillero;
    }


    public void mostrarCasillerosDisponibles(Itablero itablero){ 

        System.out.println("Estos son los casilleros donde podes ubicar una ficha");
        ArrayList<Casillero> casillerosDisponibles = itablero.casillerosDisponibles();

          
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
        sc.close();
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




    public void elegirFichaYCasillero(ScrabbleController controlador) { // TODO Desacoplar vista de controlador
        ICasillero casillero;
        IFicha ficha;

        
        casillero = this.elegirCasilleroDisponible(controlador.getTablero());
        ficha= this.elegirfichaJugador(controlador.getJugador());

        casillero.ponerFicha(ficha);
        controlador.agregarCasilleroJugado(casillero);
    }



    // el turno finaliza cuando:
    // -El jugador finaliza el turno 
    // -El jugador se queda sin fichas
    // -El jugador intercambia fichas
    public void turnoJugador() throws IOException{ // TODO Desacoplar vista de controlador
        Scanner sc = new Scanner(System.in);
        int opcion=1;
        controlador.clearCasillerosJugadosEnElTurno();
        System.out.println("Turno "+controlador.getJugador().getNombre());
        mostrarTablero(this.controlador.getTablero());
        mostrarCasillerosDisponibles(this.controlador.getTablero());
        mostrarAtrilJugador(this.controlador.getJugador());
        System.out.println("Elegir: 1-jugar 0-Finalizar turno");
        opcion = sc.nextInt();

        while (this.controlador.getJugador().getAtril().getFichasAtril().size()>0 && opcion!=0){
            mostrarTablero(this.controlador.getTablero());
            mostrarCasillerosDisponibles(this.controlador.getTablero());
            mostrarAtrilJugador(this.controlador.getJugador());
            elegirFichaYCasillero(this.controlador);
            System.out.println("Elegir: 1-jugar 0-Finalizar turno");
            opcion = sc.nextInt();
        }
        sc.close();

        //chequear las palabras formadas
       int puntajeTurno= controlador.calcularPuntajeTurno();
        
       System.out.println("El puntaje del turno fue: "+puntajeTurno);
       this.mostrarPuntos();
       this.controlador.siguienteTurno();
       this.turnoJugador();

    }


    public void mostrarPuntos(){
       System.out.println("Jugador "+this.controlador.getJugador().getNombre()+" : "+this.controlador.mostraPuntaje()+" puntos");
    }






    @Override
    public void update(Object data,Eventos evento) {
        
        
    }

    @Override
    public void actualizarVista() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCambiarFichas(boolean b) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mostrarFinDeturno(String string) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setControlador(IControladorRemoto controlador) {
        // TODO Auto-generated method stub
        
    }


}
