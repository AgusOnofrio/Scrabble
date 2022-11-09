package Modelo;

public class Jugador {
    private Atril atril;
    private Integer puntaje;
    private String nombre;
    static Integer numeroDeJugador;

    public Jugador(String nombre,Atril atril){
        this.nombre=nombre;
        this.puntaje=0;
        this.atril=atril;  // TODO ¿Hace falta que sea un atril?   
        numeroDeJugador++;
    }

    public Jugador(){
        this.nombre= "jugador "+ numeroDeJugador;
        this.puntaje=0;
        this.atril=new Atril();
        numeroDeJugador++;
    }

    public boolean agarrarFicha(){
        if(atril.eslleno()) return false;
        
        atril.agarrarFicha();
    }


}
