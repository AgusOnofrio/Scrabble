package pruebaObserverSimple;
public class PruebaControlador {
    private PruebaModelo modelo;

    public PruebaControlador(PruebaModelo modelo) {
        this.modelo = modelo;
    }

    public void sumar() {
        modelo.sumar();
    }

    public void restar() {
        modelo.restar();
    }
    
}
