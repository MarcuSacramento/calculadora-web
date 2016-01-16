package br.com.devmedia.jm.calculadoraweb.web;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import br.com.devmedia.jm.calculadoraweb.SlowTests;
import br.com.devmedia.jm.calculadoraweb.web.CalculadoraServlet.Operacao;


/**
 *  Testes funcionais Calculadora Web 
 */
@Category(SlowTests.class)
public class CalculadoraWebFuncTest {


    private static final String MSG_NUM1_REQUERIDO = "Campo: Número 1 - Número requerido!";
    private static final String MSG_NUM2_REQUERIDO = "Campo: Número 2 - Número requerido!";
    
    private static final String MSG_NUM1_INVALIDO = "Campo: Número 1 - Número inválido!";
    private static final String MSG_NUM2_INVALIDO = "Campo: Número 2 - Número inválido!";

    @Test
    public void testAdicionar() {
        opercaoSucesso("2", "3", Operacao.ADICIONAR, "5.0");
    }

    @Test
    public void testSubtrair() {
        opercaoSucesso("15", "3", Operacao.SUBTRAIR, "12.0");
    }

    @Test
    public void testMultiplicar() {
        opercaoSucesso("10", "4", Operacao.MULTIPLICAR, "40.0");
    }

    @Test
    public void testDividir() {
        opercaoSucesso("100", "2", Operacao.DIVIDIR, "50.0");
    }

    @Test
    public void testAdicionarSemValores() {
        opercaoErros("", "", Operacao.ADICIONAR, MSG_NUM1_REQUERIDO, MSG_NUM2_REQUERIDO);
    }
    
    @Test
    public void testSubtrairSemValores() {
        opercaoErros("", "", Operacao.SUBTRAIR, MSG_NUM1_REQUERIDO, MSG_NUM2_REQUERIDO);
    }
    
    @Test
    public void testMultiplicarSemValores() {
        opercaoErros("", "", Operacao.MULTIPLICAR, MSG_NUM1_REQUERIDO, MSG_NUM2_REQUERIDO);
    }
    
    @Test
    public void testDividirSemValores() {
        opercaoErros("", "", Operacao.DIVIDIR, MSG_NUM1_REQUERIDO, MSG_NUM2_REQUERIDO);
    }

    @Test
    public void testAdicionarNumerosInvalidos() {
        opercaoErros("a", "b", Operacao.ADICIONAR, MSG_NUM1_INVALIDO, MSG_NUM2_INVALIDO);
    }
    
    @Test
    public void testSubtrairNumerosInvalidos() {
        opercaoErros("a", "b", Operacao.SUBTRAIR, MSG_NUM1_INVALIDO, MSG_NUM2_INVALIDO);
    }
    
    @Test
    public void testMultiplicarNumerosInvalidos() {
        opercaoErros("a", "b", Operacao.MULTIPLICAR, MSG_NUM1_INVALIDO, MSG_NUM2_INVALIDO);
    }
    
    @Test
    public void testDividirNumerosInvalidos() {
        opercaoErros("a", "b", Operacao.DIVIDIR, MSG_NUM1_INVALIDO, MSG_NUM2_INVALIDO);
    }
    
    private void opercaoSucesso(String numero1, String numero2, Operacao opercao, String resultado) {
        acessar();
        digitarNumero1(numero1);
        digitarNumero2(numero2);
        selecionarOperacao(opercao);
        calcular();
        verificarResultado(resultado);
    }
    
    private void opercaoErros(String numero1, String numero2, Operacao opercao, String... erros) {
        acessar();
        digitarNumero1(numero1);
        digitarNumero2(numero2);
        selecionarOperacao(opercao);
        calcular();
        verificarErros(erros);
    }

    
}
