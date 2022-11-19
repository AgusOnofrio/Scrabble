package Modelo;

import java.util.ArrayList;

public class Jugador {
    private Atril atril;
    private Integer puntaje;
    private String nombre;
    private static Integer numeroDeJugador=0;

    public Jugador(String nombre){
        this.nombre=nombre;
        numeroDeJugador++;
        this.atril= new Atril();
        puntaje=0;
    }

    public Jugador(){
        this.nombre="Jugador "+numeroDeJugador.toString();
        numeroDeJugador++;
        this.atril= new Atril();
        puntaje=0;
    }



    //Sabers si la palabra esta en s atril
    public boolean palabraEstaEnAtril(String palabra){
        String fichasAtril = this.atril.getFichasAtrilString();
        boolean valida=true;
        String[] ArrayPalabra = palabra.split(",");

        for (String s : ArrayPalabra) {
            if(fichasAtril.contains(s)){
                fichasAtril.replaceFirst(s,"");
            }else{
                valida=false;
            }
        }
        return valida;
    }

    //Un jugador puede cambiar las fichas de su atril


}
