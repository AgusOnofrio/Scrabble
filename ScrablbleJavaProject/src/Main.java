import Modelo.BolsaFichas;
import Modelo.Ficha;

public class Main {
    public static void main(String[] args) {



        //PRobar la carga de fichas -------------------------------------
        BolsaFichas bolsaDeFichas = new BolsaFichas();
        for (Ficha ficha : bolsaDeFichas.getFichas()) {
            System.out.println(ficha.getLabel()+"-"+ficha.getValue().toString());
        }

        bolsaDeFichas.revolver();
        int i=0;
            while(!(bolsaDeFichas.getFichas().isEmpty())) {
                i++;
                System.out.println();
                System.out.println("Saque la Ficha numero "+i);
                Ficha fichaSacada = bolsaDeFichas.sacarFicha();
                System.out.println();
                System.out.println(fichaSacada.getLabel()+"-"+fichaSacada.getValue().toString());
            }
            //-----------------------------------------------------



            





    }





}
