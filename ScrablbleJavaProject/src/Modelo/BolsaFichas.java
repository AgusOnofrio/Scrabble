package Modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import Modelo.Interfaces.IBolsaFichas;
import Modelo.Interfaces.IFicha;

public class BolsaFichas implements IBolsaFichas,Serializable{
    //Hacer que la bolsa de fichas sea un singleton
    private static BolsaFichas instancia;
    private  ArrayList<IFicha> fichas;
    
    public static BolsaFichas getInstance(){
        if(instancia==null){
            instancia=new BolsaFichas();
        }
        return instancia;
    }

    private BolsaFichas(){
        fichas= new ArrayList<IFicha>();
        for (Letra letra : Letra.values()) {
            for (int i = 0; i < letra.getQuantity(); i++) {
                Ficha fichaPorAgregar= new Ficha(letra.getLabel(),letra.getValue());
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
