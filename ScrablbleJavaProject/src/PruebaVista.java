import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.security.auth.callback.TextInputCallback;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.FlowView.FlowStrategy;

import org.w3c.dom.Text;

public class PruebaVista {
    private PruebaControlador controlador;
    private JLabel texto;
    
    private JButton botonSuma,botonResta;
    private JFrame frame;



    public PruebaVista(PruebaControlador controlador){
      
        iniciarVentana();
        // this.controlador=controlador;

        // botonSuma= new JButton();
        // botonSuma.setText("Suma");
        // botonSuma.addMouseListener(new MouseInputAdapter() {

        //         @Override
        //         public void mouseClicked(MouseEvent e) {
        //             controlador.sumar();
        //         }
        // });

        // botonResta= new JButton();
        // botonResta.setText("Resta");

        // botonResta.addMouseListener(new MouseInputAdapter() {

        //         @Override
        //         public void mouseClicked(MouseEvent e) {
        //             controlador.restar();
        //         }
        // });


        // texto= new JLabel();
        // texto.setText(Integer.toString(0));







    }



    private void iniciarVentana() {
        frame = new JFrame("titulo de la ventana");
        frame.setSize(400,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel etiqueta1 = new JLabel("Hola mundo, desde Swing");
        

        botonSuma= new JButton();
        botonSuma.setText("Suma");
        botonSuma.addMouseListener(new MouseInputAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.sumar();
                }
        });

        botonResta= new JButton();
        botonResta.setText("Resta");

        botonResta.addMouseListener(new MouseInputAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.restar();
                }
        });


        texto= new JLabel();
        texto.setText(Integer.toString(0));


        




        JPanel panelPrincipal = (JPanel)frame.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelMenu= new JPanel();
        panelMenu.setLayout(new GridLayout());

        panelMenu.add(etiqueta1);
        panelMenu.add(botonSuma);
        panelMenu.add(botonResta);
       
        panelPrincipal.add(panelMenu,BorderLayout.WEST);


    }




}
