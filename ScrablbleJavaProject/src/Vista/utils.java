package Vista;

import Modelo.Letra;

public class utils {
    public static String valoreLetrasAString(){
        String texto="Letra - Valor\n";
        for (Letra l : Letra.values()) {
            if(l.getLabel().length()>1){
                texto+=""+l.getLabel()+"   :   "+l.getValue()+"\n";    
            }else{
                texto+="   "+l.getLabel()+"   :   "+l.getValue()+"\n";
            }
        }
        return texto;
    }
    public static boolean esNumerico(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
