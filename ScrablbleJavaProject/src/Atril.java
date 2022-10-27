import java.util.ArrayList;

public class Atril {
    //Unatril puede solo contener 7 fichas
    private ArrayList<Ficha> fichas;
    private Integer cantidadDeFichas=7;

    public Atril(){
        fichas = new ArrayList<Ficha>(cantidadDeFichas);
    }

    public void llenarAtril(BolsaFichas bolsa){
        while(fichas.size()< cantidadDeFichas){
            fichas.add(bolsa.sacarFicha());
        }
    }


}
