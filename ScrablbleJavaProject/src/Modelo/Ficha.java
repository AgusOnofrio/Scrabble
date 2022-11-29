package Modelo;

import java.io.Serializable;

import Modelo.Interfaces.IFicha;

public class Ficha implements IFicha,Serializable {
    private final String label;
    private final Integer value;

    public Ficha(String label,Integer value){
        this.label=label;
        this.value=value;
    }

    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }


}
