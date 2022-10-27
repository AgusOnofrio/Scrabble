import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

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



}
