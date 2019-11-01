package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

    @Test
    public void varastonAlustaminenToimii() {
        Varasto varasto1 = new Varasto(7, 3);
        assertEquals(3, varasto1.getSaldo(), 0);
    }
    
    @Test
    public void varastonAlustaminenToimii2() {
        Varasto varasto1 = new Varasto(5, -1);
        assertEquals(0, varasto1.getSaldo(), 0);
    }
    
    @Test
    public void alustaTyhjaNegatiivinenVarasto() {
        Varasto varasto1 = new Varasto(-1, 3);
        assertEquals(0, varasto1.getTilavuus(), 0);
    }
    
    @Test
    public void alustaTyhjaNegatiivinenVarasto2() {
        Varasto varasto1 = new Varasto(-1);
        assertEquals(0, varasto1.getTilavuus(), 0);
    }
    
    @Test
    public void lisaaVarastoonVirheellinenMaara() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), 0);
    }
    
    @Test
    public void lisaaVarastoTayteen() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), 0);
    }
    
    @Test
    public void otaVarastostaNegatiivinenMaara() {
        varasto.otaVarastosta(-1);
        assertEquals(0, varasto.getSaldo(), 0);
    }
    
    @Test
    public void otaKaikkiMitaVoidaan() {
        Varasto varasto1 = new Varasto(10, 5);
        varasto1.otaVarastosta(6);
        assertEquals(1, varasto.getSaldo(), 0);
    }
}