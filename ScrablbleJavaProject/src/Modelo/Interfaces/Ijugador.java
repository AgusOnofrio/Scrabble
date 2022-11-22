package Modelo.Interfaces;

import Modelo.Atril;

public interface Ijugador {
    //Sabers si la palabra esta en s atril
    public String getNombre();
    
    public void setNombre(String nombre);

    //Sabers si la palabra esta en s atril
    public boolean palabraEstaEnAtril(String palabra);

    public Atril getAtril() ;

}
