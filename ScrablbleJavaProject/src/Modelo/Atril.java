package Modelo;
import java.util.ArrayList;

import Modelo.Interfaces.IAtril;
import Modelo.Interfaces.IFicha;

public class Atril implements IAtril {
    //Un atril puede solo contener 7 fichas
    private ArrayList<IFicha> fichas;
    private Integer cantidadDeFichas=7;
    private BolsaFichas bolsa;

    public Atril(BolsaFichas bolsa){
        this.fichas = new ArrayList<IFicha>(cantidadDeFichas);
        this.bolsa=bolsa;
        this.llenarAtril();
    }

    public void llenarAtril(){
        while(fichas.size()< cantidadDeFichas){
            fichas.add(bolsa.sacarFicha());
        }
    }

    public ArrayList<IFicha> getFichasAtril(){
        return this.fichas;
    }

    public String getFichasAtrilString(){
        StringBuilder stringAtril= new StringBuilder();
        for (IFicha ficha : fichas) {
            stringAtril.append(ficha.getLabel().toString()+",");
        }
        return stringAtril.toString();
    }

    
    public IFicha sacarFichaDeAtril(int posicion){
        IFicha ficha=null;
        if(posicion>=0 && posicion<fichas.size()) {
            ficha= fichas.get(posicion);
            fichas.remove(posicion);
        }
        return ficha;
    }



    //El atril debe intercambiar sus fichas
    @Override
    public void intercambiarFichas() {
        // TODO Auto-generated method stub
        
    }


}