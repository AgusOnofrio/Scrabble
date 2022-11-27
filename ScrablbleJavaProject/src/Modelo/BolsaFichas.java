package Modelo;
import java.util.ArrayList;
import java.util.Collections;

import Modelo.Interfaces.IBolsaFichas;
import Modelo.Interfaces.IFicha;

public class BolsaFichas implements IBolsaFichas{
    //Hacer que la bolsa de fichas sea un singleton
    
    private  ArrayList<IFicha> fichas;
    
    public BolsaFichas(){
        fichas= new ArrayList<IFicha>();
        for (Letra letra : Letra.values()) {
            for (int i = 0; i < letra.quantity; i++) {
                Ficha fichaPorAgregar= new Ficha(letra.label,letra.value);
                fichas.add(fichaPorAgregar);
            }
        }
    }
    

    public ArrayList<IFicha> getFichas(){
        return fichas;
    }

    public IFicha sacarFicha(){
        IFicha ficha;

        if(fichas.isEmpty()) return null;

        this.revolver();

        ficha = fichas.get(0);
        fichas.remove(0);

        return ficha;
    }

    public IFicha cambiarFicha(IFicha fichaAnterior) {

        fichas.add(fichaAnterior);
        IFicha fichaNueva = this.sacarFicha();

        return fichaNueva;
    }

    public void revolver(){
        Collections.shuffle(this.fichas);
    }

    public boolean esVacia(){
        return fichas.isEmpty();
    }

}
