package Modelo;
import java.util.ArrayList;

public class Atril {
    //Un atril puede solo contener 7 fichas
    private ArrayList<Ficha> fichas;
    private Integer cantidadDeFichas=7;
    private BolsaFichas bolsa;

    public Atril(){
        fichas = new ArrayList<Ficha>(cantidadDeFichas);
    }

    public void llenarAtril(){
        while(fichas.size()< cantidadDeFichas){
            fichas.add(bolsa.sacarFicha());
        }
    }

    public ArrayList<Ficha> getFichasAtril(){
        return this.fichas;
    }

    public String getFichasAtrilString(){
        StringBuilder stringAtril= new StringBuilder();
        for (Ficha ficha : fichas) {
            stringAtril.append(ficha.getLabel().toString()+",");
        }
        return stringAtril.toString();
    }

    
    // public Ficha sacarFichaDeAtril(int posicion){
    //     Ficha ficha=null;
    //     if(posicion>=0 && posicion<fichas.size()) {
    //         ficha= fichas.get(posicion);
    //     }
    //     return ficha;
    // }
    //El atril debe intercambiar sus letras


}
