package Modelo;
import java.util.ArrayList;

public class Tablero {
    private ArrayList<Casillero> casilleros[];

    public Tablero(){
        final Integer FILAS=15;
        final Integer COLUMNAS=15;

        for (int i = 0; i < FILAS; i++) {
            casilleros[0]= new ArrayList<Casillero>();
            for (int j = 0; j < COLUMNAS; j++) {
                if((i==0 || i==14 || i==7) && (j==0 || j==7 || j==14) && !(i==7 && j==7)){
                    casilleros[i].add(new Casillero(TipoEspecial.TRIPLE_PALABRA));
                } else if ((i==j)) {
                    
                }


            }
        }

        casilleros[1]= new ArrayList<Casillero>();
        casilleros[2]= new ArrayList<Casillero>();
        casilleros[3]= new ArrayList<Casillero>();
        casilleros[4]= new ArrayList<Casillero>();
        casilleros[5]= new ArrayList<Casillero>();
        casilleros[6]= new ArrayList<Casillero>();
        casilleros[7]= new ArrayList<Casillero>();
        casilleros[8]= new ArrayList<Casillero>();
        casilleros[9]= new ArrayList<Casillero>();
        casilleros[10]= new ArrayList<Casillero>();
        casilleros[11]= new ArrayList<Casillero>();
        casilleros[12]= new ArrayList<Casillero>();
        casilleros[13]= new ArrayList<Casillero>();
        casilleros[14]= new ArrayList<Casillero>();
    }
    
}
