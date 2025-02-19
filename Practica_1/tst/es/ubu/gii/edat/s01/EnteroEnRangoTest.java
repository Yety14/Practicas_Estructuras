package es.ubu.gii.edat.s01;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnteroEnRangoTest {

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorInvalido() {
        EnteroEnRango rango = new EnteroEnRango(5, 10, 5);
    }
	
    @Test
    public void testSumar() {
        EnteroEnRango entero1 = new EnteroEnRango(5, 0, 10);
        EnteroEnRango entero2 = new EnteroEnRango(3, 0, 8);

        entero1.sumar(entero2);

        assertEquals(8, entero1.getValor());
        assertEquals(0, entero1.getValorMinimo());
        assertEquals(10, entero1.getValorMaximo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumarFueraDeRango() {
        EnteroEnRango entero1 = new EnteroEnRango(8, 0, 10);
        EnteroEnRango entero2 = new EnteroEnRango(4, 0, 8);

        entero1.sumar(entero2);
    }

    @Test
    public void testRestar() {
        EnteroEnRango entero1 = new EnteroEnRango(8, 5, 10);
        EnteroEnRango entero2 = new EnteroEnRango(3, 1, 6);

        entero1.restar(entero2);

        assertEquals(5, entero1.getValor());
        assertEquals(5, entero1.getValorMinimo());
        assertEquals(6, entero1.getValorMaximo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRestarFueraDeRango() {
        EnteroEnRango entero1 = new EnteroEnRango(5, 0, 10);
        EnteroEnRango entero2 = new EnteroEnRango(8, 0, 5);

        entero1.restar(entero2);
    }

    @Test
    public void testIterador() {
        EnteroEnRango rango = new EnteroEnRango(5, 1, 10);
        int[] valoresEsperados = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = 0;

        for (int valor : rango) {
            assertEquals(valoresEsperados[index], valor);
            index++;
        }

        assertEquals(valoresEsperados.length, index);
    }

    @Test
    public void testIteradorRangoUnico() {
        EnteroEnRango rango = new EnteroEnRango(5, 5, 5);
        int[] valoresEsperados = {5};
        int index = 0;

        for (int valor : rango) {
            assertEquals(valoresEsperados[index], valor);
            index++;
        }

        assertEquals(valoresEsperados.length, index);
    }

}
