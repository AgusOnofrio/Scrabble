package Vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Controlador.ScrabbleController;
import Modelo.Tablero;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.Ijugador;
import Modelo.Interfaces.Itablero;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
public class VistaConsola implements IVista {
    


    private ScrabbleController controlador;

    public VistaConsola(){}

    public void setControlador(ScrabbleController controlador){
        this.controlador=controlador;
    }

    public void iniciar(){
        this.mostrarJuego();
    }

    private void mostrarJuego() {
        //Muestro el tablero y el atril para jugar cada turno
        this.mostrarTablero();
        this.mostrarCasillerosDisponibles();
        this.mostrarAtrilJugador();
        
        if(this.controlador.getJugadorVista().getNombre().equals(this.controlador.getJugadorActual().getNombre())){
            Scanner sc = new Scanner(System.in);
            String ficha = sc.nextLine();
            ArrayList<IFicha> fichas = this.controlador.getJugadorVista().getAtril().getFichasAtril();
            IFicha fichaElegida=null;

            for (IFicha f : fichas) {
                if(f.getLabel().equals(ficha)){
                    fichaElegida=f;
                }
            }
                
            if(fichaElegida!=null){
                this.controlador.elegirFichaAtril(fichaElegida);
                
            }else{
                System.out.println("No existe esa ficha en tu atril");
            }
               
                
        }
        
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


    public void mostrarCasillerosDisponibles(){ 

        System.out.println("Estos son los casilleros donde podes ubicar una ficha");
        ArrayList<ICasillero> casillerosDisponibles = this.controlador.getTablero().casillerosDisponibles();

          
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


    public void mostrarAtrilJugador(){
        Ijugador jugador= this.controlador.getJugadorActual();
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCambiarFichas(boolean b) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mostrarFinDeturno() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setControlador(IControladorRemoto controlador) {
        this.controlador=(ScrabbleController)controlador;
        
    }

    @Override
    public void mostrarResultadoFinal() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actualizarJugadores() {
        // TODO Auto-generated method stub
        
    }







}
