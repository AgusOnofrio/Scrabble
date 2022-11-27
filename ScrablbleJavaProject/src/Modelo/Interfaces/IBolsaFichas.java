package Modelo.Interfaces;

import java.util.ArrayList;

public interface IBolsaFichas {
    public ArrayList<IFicha> getFichas();
    public IFicha sacarFicha();
    public IFicha cambiarFicha(IFicha fichaAnterior);
    public void revolver();
    public boolean esVacia();

}
