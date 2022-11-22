import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import Modelo.BolsaFichas;
import Modelo.Casillero;
import Modelo.Diccionario;
import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Tablero;

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
        Ficha fichaAnterior = bolsa.sacarFicha() ;

        Ficha fichanueva= bolsa.cambiarFicha(fichaAnterior);

        assertNotEquals(fichanueva, fichaAnterior);
        assertNotEquals(fichanueva,null);
    }

    @Test
    public void cuandoInicializoTableroNoHayFichas() throws IOException{
        Tablero tablero= new Tablero();
        
        Casillero[][] casilleros = tablero.getCasilleros();

        for (Casillero[] fila : casilleros){
                for (Casillero c : fila){
                assertEquals(false,c.estaOcupado());
                assertEquals(null,c.getFicha());
            }
        }
    }

    @Test
    public void unaPalabraQueNoEstaEnElDiccionarioDevuelveFalse() throws IOException{
        Diccionario diccionario = new Diccionario();

        String palabra = "jorgelin";
        
        assertEquals(true,diccionario.validarPalabra(palabra));
    }

    @Test
    public void ponerFichaEnUnCasilleroMeDevuelveEsaFicha(){
        Casillero casillero =  new Casillero(0,0);
        BolsaFichas bolsaFichas = new BolsaFichas();

        Ficha ficha = bolsaFichas.sacarFicha();

        casillero.ponerFicha(ficha);
        
        assertEquals(ficha,casillero.getFicha());
    }


    @Test
    public void jugadorPuedeFormarLaPalabraSiTieneTodasLasLetrasEnElAtril(){

        assertTrue(false);
    }
    



}
