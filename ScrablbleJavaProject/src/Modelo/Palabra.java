package Modelo;

import java.util.ArrayList;

public class Palabra {
    private  ArrayList<Casillero> posiciones;
    private  ArrayList<Ficha> letras;
    private final String palabra;

    public Palabra(ArrayList<Casillero> casilleros){  //¿Deberia poder inicializar una palabra sin saber que sea valida primero?
        this.posiciones= casilleros;
        //Agrego las letras que estan cada casillero
        for (Casillero casillero : casilleros) {
            this.letras.add(casillero.getFicha());
        }
        
        // seteo la propiedad palabra
        this.palabra= this.convertirString();
        
    }

    private String convertirString(){
        String palabra="";
        for (Ficha ficha : letras) {
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
        return Diccionario.validarPalabra(this.palabra);
    }




}
