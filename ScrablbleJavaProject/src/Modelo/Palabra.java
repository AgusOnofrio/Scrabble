package Modelo;

import java.io.IOException;
import java.util.ArrayList;

import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;

public class Palabra implements IPalabra{
    private  ArrayList<ICasillero> posiciones;
    private  ArrayList<IFicha> letras;
    private final String palabra;

    public Palabra(ArrayList<ICasillero> casilleros){  //¿Deberia poder inicializar una palabra sin saber que sea valida primero?
        this.posiciones= casilleros;
        this.letras= new ArrayList<IFicha>();
        //Agrego las letras que estan cada casillero
        for (ICasillero casillero : casilleros) {
            this.letras.add(casillero.getFicha());
        }
        
        // seteo la propiedad palabra
        this.palabra= this.convertirString();
        
    }

    public String convertirString(){
        String palabra="";
        for (IFicha ficha : letras) {
            palabra+=ficha.getLabel();
        }
        return palabra;
    }


    public int obtenerPuntaje(){
        int puntaje=0;
        int puntajeLetra=0;

        ArrayList<Integer> multiplicadores= new ArrayList<Integer>();
        
        for (int i = 0; i < this.letras.size() ; i++) {

            TipoEspecial multiplicador = this.posiciones.get(i).getTipoEspecial();
            puntajeLetra=this.letras.get(i).getValue() ;

            switch (multiplicador) {
                case TRIPLE_LETRA:
                        puntajeLetra=puntajeLetra*3;
                    break;
                case DOBLE_LETRA:
                        puntajeLetra=puntajeLetra*2;
                    break;
                case TRIPLE_PALABRA:
                        multiplicadores.add(3);
                    break;
                case DOBLE_PALABRA:
                        multiplicadores.add(2);
                    break;
            
                default:
                    break;
            }

            puntaje+=puntajeLetra;

        }

        if(multiplicadores.size()>0){
            for (Integer multiplicador : multiplicadores) {
                puntaje= puntaje * multiplicador;
            }
        }

        return puntaje;

    }

    public boolean esValida(){
        boolean valida=false;
        try {
            if(Diccionario.validarPalabra(this.palabra)) valida=true;;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valida;
    }

    public boolean equals(IPalabra palabra){
        String p = palabra.convertirString();

        return p.equals(this.convertirString());
    }

    public ArrayList<ICasillero> getPosiciones(){
        return this.posiciones;
    }


}
