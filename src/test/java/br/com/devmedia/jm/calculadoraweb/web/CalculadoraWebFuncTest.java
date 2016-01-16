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
import br.com.devmedia.jm.calculadoraweb.web.util.CalculadoraWebTomcatRunner;

/**
 *  Testes funcionais Calculadora Web 
 */
@Category(SlowTests.class)
public class CalculadoraWebFuncTest {

    private static CalculadoraWebTomcatRunner app;

    private static WebDriver driver;

    private static final String MSG_NUM1_REQUERIDO = "Campo: Número 1 - Número requerido!";
    private static final String MSG_NUM2_REQUERIDO = "Campo: Número 2 - Número requerido!";
    
    private static final String MSG_NUM1_INVALIDO = "Campo: Número 1 - Número inválido!";
    private static final String MSG_NUM2_INVALIDO = "Campo: Número 2 - Número inválido!";

    @BeforeClass
    public static void before() {
        app = new CalculadoraWebTomcatRunner(8090);
        app.run();
        driver = new FirefoxDriver();
    }

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

    private void acessar() {
        driver.get(app.getUrlAplicacao());
    }

    private void digitarNumero1(String numero) {
        driver.findElement(By.id("num1")).sendKeys(numero);
    }

    private void digitarNumero2(String numero) {
        driver.findElement(By.id("num2")).sendKeys(numero);
    }

    private void selecionarOperacao(Operacao operacao) {
        Select select = new Select(driver.findElement(By.id("operacao")));
        select.selectByValue(operacao.name());
    }

    private void calcular() {
        driver.findElement(By.id("btnCalcular")).click();
    }

    private void verificarResultado(String resultado) {
        Assert.assertEquals(resultado, driver.findElement(By.id("resultado"))
                .getText());
    }

    private void verificarErros(String... erros) {
        WebElement divErros = driver.findElement(By.id("divErros"));
        for (String erro : erros) {
            Assert.assertTrue(divErros.getText().contains(erro));
        }
    }

    @AfterClass
    public static void after() {
        driver.close();
        app.stop();
    }

}
