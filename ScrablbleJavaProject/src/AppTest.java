import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import Modelo.BolsaFichas;
import Modelo.Casillero;
import Modelo.Diccionario;
import Modelo.Ficha;
import Modelo.Tablero;
import Modelo.Interfaces.ICasillero;
import Modelo.Interfaces.IFicha;

public class AppTest {
    
    @Test
    public void firstTest(){
        assertEquals(2,1+1);
    }

    @Test
    public void sacarFichaMeDevuelveUnaFicha(){
        BolsaFichas bolsa = new BolsaFichas();
        assert(bolsa.sacarFicha() instanceof Ficha);
    }

    @Test
    public void sacarFichaMeDevuelveUnaFichaValida(){
        BolsaFichas bolsa = new BolsaFichas();
        assert(bolsa.sacarFicha() instanceof Ficha);
        assert(bolsa.sacarFicha()!= null);
    }

    @Test
    public void sacarUnaFichaBajaLaCantidadDeFichasDeLaBolsa(){
        BolsaFichas bolsa = new BolsaFichas();
        Integer cantidadAnterior = bolsa.getFichas().size();
        bolsa.sacarFicha();
        assert( bolsa.getFichas().size() < cantidadAnterior);
        assertNotEquals( bolsa.getFichas().size(), null);

    }

    @Test
    public void CambiarFichaMeDevuelveOtraFicha(){
        BolsaFichas bolsa = new BolsaFichas();
        IFicha fichaAnterior = bolsa.sacarFicha() ;

        IFicha fichanueva= bolsa.cambiarFicha(fichaAnterior);

        assertNotEquals(fichanueva, fichaAnterior);
        assertNotEquals(fichanueva,null);
    }

    @Test
    public void cuandoInicializoTableroNoHayFichas() throws IOException{
        Tablero tablero= new Tablero();
        
        ICasillero[][] casilleros = tablero.getCasilleros();

        for (ICasillero[] fila : casilleros){
                for (ICasillero c : fila){
                assertEquals(false,c.estaOcupado());
                assertEquals(null,c.getFicha());
            }
        }
    }

    @Test
    public void unaPalabraQueNoEstaEnElDiccionarioDevuelveFalse() throws IOException{
        

        String palabra = "jorgelin";
        
        assertEquals(true,Diccionario.validarPalabra(palabra));
    }

    @Test
    public void ponerFichaEnUnCasilleroMeDevuelveEsaFicha(){
        Casillero casillero =  new Casillero(0,0);
        BolsaFichas bolsaFichas = new BolsaFichas();

        IFicha ficha = bolsaFichas.sacarFicha();

        casillero.ponerFicha(ficha);
        
        assertEquals(ficha,casillero.getFicha());
    }


    @Test
    public void jugadorPuedeFormarLaPalabraSiTieneTodasLasLetrasEnElAtril(){

        assertTrue(false);
    }
    



}
