package Modelo;
import java.util.ArrayList;
import java.util.Collections;

public class BolsaFichas {
    //Hacer que la bolsa de fichas sea un singleton
    
    private  static ArrayList<Ficha> fichas;
    
    public static void inicializar(){
        for (Letra letra : Letra.values()) {
            for (int i = 0; i < letra.quantity; i++) {
                Ficha fichaPorAgregar= new Ficha(letra.label,letra.value);
                fichas.add(fichaPorAgregar);
            }
        }
    }
    

    public ArrayList<Ficha> getFichas(){
        return fichas;
    }

    public Ficha sacarFicha(){
        Ficha ficha;

        if(fichas.isEmpty()) return null;

        this.revolver();

        ficha = fichas.get(0);
        fichas.remove(0);

        return ficha;
    }

    public Ficha cambiarFicha(Ficha fichaAnterior) {

        fichas.add(fichaAnterior);
        Ficha fichaNueva = this.sacarFicha();

        return fichaNueva;
    }

    public void revolver(){
        Collections.shuffle(this.fichas);
    }


}
