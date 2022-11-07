import javax.swing.SwingUtilities;

public class PruebaMVC {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                
                PruebaModelo modelo= new PruebaModelo();
                PruebaControlador controlador = new PruebaControlador(modelo);
                
                PruebaVista vista1 = new PruebaVista(controlador);
                PruebaVista vista2 = new PruebaVista(controlador);

                modelo.addObservador(vista1);
                modelo.addObservador(vista2);
            }
        });
        



    }
}
