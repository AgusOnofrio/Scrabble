package Modelo;
import java.util.ArrayList;

public class Atril {
    //Unatril puede solo contener 7 fichas
    private ArrayList<Ficha> fichas;
    private static final Integer cantidadDeFichas=7;

    public Atril(){
        fichas = new ArrayList<Ficha>(cantidadDeFichas);
    }

    public void llenarAtril(BolsaFichas bolsa){
        while(fichas.size()< cantidadDeFichas){
            fichas.add(bolsa.sacarFicha());
        }
    }

    public boolean eslleno() {
        return fichas.size() >= Atril.cantidadDeFichas;
    }

    public void agarrarFicha(BolsaFichas bolsa) {
        if(fichas.size()< cantidadDeFichas){
            fichas.add(bolsa.sacarFicha());
        }
    }


}
