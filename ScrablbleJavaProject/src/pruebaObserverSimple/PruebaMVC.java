package pruebaObserverSimple;
import javax.swing.SwingUtilities;

public class PruebaMVC {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                
                PruebaModelo modelo= new PruebaModelo();
                PruebaControlador controlador = new PruebaControlador(modelo);
                
                PruebaVista vista1 = new PruebaVista(controlador);
                PruebaVista vista2 = new PruebaVista(controlador);

                modelo.agregarObservador(vista1);
                modelo.agregarObservador(vista2);
            }
        });
        



    }
}
