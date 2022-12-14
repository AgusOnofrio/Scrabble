package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

import Modelo.Interfaces.IAtril;
import Modelo.Interfaces.IFicha;
import Modelo.Interfaces.IPalabra;
import Modelo.Interfaces.Ijugador;

public class Jugador implements Ijugador,Serializable{
    private IAtril atril;
    private Integer puntaje;
    private String nombre;
    private ArrayList<IPalabra> palabrasDePartida= new ArrayList<IPalabra>();
    private ArrayList<IPalabra> palabrasFormadasEnElTurno=new ArrayList<IPalabra>();
    private static Integer numeroDeJugador=0;
    private IFicha fichaElegida=null;
    
    
    public Jugador(String nombre){
        numeroDeJugador=numeroDeJugador+1;
        if(nombre.trim()=="")nombre="Jugador "+numeroDeJugador.toString();
        this.nombre=nombre;
        this.atril= new Atril();
        this.palabrasDePartida= new ArrayList<IPalabra>();
        puntaje=0;
    }
    
    public Jugador(){
        this.nombre="Jugador "+(++numeroDeJugador).toString();
        numeroDeJugador++;
        this.atril= new Atril();
        this.palabrasDePartida= new ArrayList<IPalabra>();
        puntaje=0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public static Integer getNumeroDeJugador(){
        return numeroDeJugador;
    }

    public IAtril getAtril() {
        return this.atril;
    }

    @Override
    public void sumarPuntos(int puntaje) {
        this.puntaje+=puntaje;
        
    }

    @Override
    public Integer getPuntaje() {
        return this.puntaje;        
    }

    
    
    public void agregarPalabraCreadaPorJugador(IPalabra palabra){
        this.palabrasDePartida.add( palabra);
    }

    public ArrayList<IPalabra> obtenerPalabrasDePartida(){
        return this.palabrasDePartida;
    }

    public void agregarPalabraFormadaEnElturno(IPalabra palabra){
        this.palabrasFormadasEnElTurno.add(palabra);
    }

    public void clearPalabrasFormadasEnElturno(){
        this.palabrasFormadasEnElTurno.clear();
    }

    public ArrayList<IPalabra> getPalabrasValidasDelTurno(){
        return this.palabrasFormadasEnElTurno;
    }

    public IFicha getFichaElegida(){
        return this.fichaElegida;
    }

    @Override
    public void clearFichaElegida() {
        this.fichaElegida=null;
        
    }

    @Override
    public void setFichaElegida(IFicha ficha) {
        this.fichaElegida=ficha;
        
    }

    
    
}
