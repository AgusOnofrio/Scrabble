package Vista;

import Modelo.Letra;

public class utils {
    public static String valoreLetrasAString(){
        String texto="Letra - Valor\n";
        for (Letra l : Letra.values()) {
            if(l.label.length()>1){
                texto+=""+l.label+"   :   "+l.value+"\n";    
            }else{
                texto+="   "+l.label+"   :   "+l.value+"\n";
            }
        }
        return texto;
    }
}
