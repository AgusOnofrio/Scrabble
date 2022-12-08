package Modelo;

public class FichaEspecial extends Ficha {
    private String label;
    public FichaEspecial(String label, Integer value) {
        super(label, value);
        //TODO Auto-generated constructor stub
    }
    
    public void setLabel(String letra){
        this.label=letra;
    }
}

