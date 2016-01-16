package br.com.devmedia.jm.calculadoraweb.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.powermock.reflect.Whitebox;

import br.com.devmedia.jm.calculadoraweb.FastTests;
import br.com.devmedia.jm.calculadoraweb.core.Calculadora;

/**
 * Testes para {@link Calculadora}
 **/
@Category(FastTests.class)
public class CalculadoraTest {
    
    @Test(expected = UnsupportedOperationException.class)
    public void testConstrutorPrivado() throws Exception {
        Whitebox.invokeConstructor(Calculadora.class);
    }

    @Test
    public void testAdicionar() {
        double esperado = 2;
        Assert.assertEquals(esperado, Calculadora.adicionar(1, 1), 0);
    }

    @Test
    public void testSubtrair() {
        double esperado = 1;
        Assert.assertEquals(esperado, Calculadora.subtrair(2, 1), 0);
    }

    @Test
    public void testDividir() {
        double esperado = 1.5;
        Assert.assertEquals(esperado, Calculadora.dividir(3, 2), 0);
    }

    @Test
    public void testDividirPositivoPorZero() {
        double esperado = Double.POSITIVE_INFINITY;
        Assert.assertEquals(esperado, Calculadora.dividir(3, 0), 0);
    }

    @Test
    public void testDividirNegativoPorZero() {
        double esperado = Double.NEGATIVE_INFINITY;
        Assert.assertEquals(esperado, Calculadora.dividir(-3, 0), 0);
    }

    @Test
    public void testMultiplicar() {
        double esperado = 3;
        Assert.assertEquals(esperado, Calculadora.multiplicar(1.5, 2), 0);
    }

}
