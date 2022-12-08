package Modelo;
import java.io.Serializable;
import java.util.ArrayList;

import Modelo.Interfaces.IAtril;
import Modelo.Interfaces.IBolsaFichas;
import Modelo.Interfaces.IFicha;

public class Atril implements IAtril,Serializable {
    //Un atril puede solo contener 7 fichas
    private ArrayList<IFicha> fichas;
    private Integer cantidadDeFichas=7;
    private IBolsaFichas bolsa;

    public Atril(IBolsaFichas bolsaConLetras){
        this.fichas = new ArrayList<IFicha>(cantidadDeFichas);
        this.bolsa=bolsaConLetras;
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

    public IFicha sacarFichaDeAtril(IFicha fichaElegida){
        IFicha ficha=null;
        int posicion=-1;
        for (int i = 0; i < this.fichas.size(); i++) {
            System.out.printf("Ficha %s - %s",fichas.get(i).getLabel(),fichaElegida.getLabel());
            
            if(fichas.get(i).getLabel().equals(fichaElegida.getLabel())) posicion=i;
        }

        if(posicion>=0 && posicion<fichas.size()){
            System.out.println(("La ficha esta en el atril"));
            ficha= fichas.get(posicion);
            this.fichas.remove(posicion);
        }
        return ficha;
    }

    //El atril debe intercambiar sus fichas
    @Override
    public void intercambiarFichas(ArrayList<IFicha> listaDeFichas) {
        for (int i = 0; i < fichas.size(); i++) {
            for (IFicha iFicha : listaDeFichas) {
                if(fichas.get(i).getLabel().equals(iFicha.getLabel())){
                    fichas.set(i, bolsa.cambiarFicha(fichas.get(i)));
                    
                }
            }
        }
    }

    @Override
    public void ponerFicha(IFicha ficha) {
        if(this.fichas.size()<cantidadDeFichas){
            this.fichas.add(ficha);
        }
        
    }

    @Override
    public boolean estaVacio() {
        
        return this.fichas.isEmpty();
    }


}
