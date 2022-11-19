package Modelo;
public class Ficha {
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
