package br.com.devmedia.jm.calculadoraweb.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.devmedia.jm.calculadoraweb.core.Calculadora;

/**
 * Servlet que oferece as operações básicas da matemática
 * via interface Web.
 *
 */
@WebServlet(
        name = "CalculadoraServlet", urlPatterns = { "/calcular" })
public class CalculadoraServlet extends HttpServlet {
    /**
     *Constante do parâmetro operação.
     */
    private static final String PARAMETRO_OPERACAO = "operacao";
    /**
     *Constante para modo de operação cálculo.
     */
    private static final String PARAMETRO_MODO_CALCULO = "modo";
    /**
     *Constante do parâmetro número 1.
     */
    private static final String PARAMETRO_NUM_1 = "num1";
    /**
     *Constante do parâmetro número 2.
     */
    private static final String PARAMETRO_NUM_2 = "num2";
    /**
     *Constante para parâmetro de operação da tela, que 
     *indica se a tela está sendo inicializada ou execução do cálculo.
     */
    private static final String MODO_CALCULAR = "CALCULAR";

    private static final long serialVersionUID = 1L;

    @Override
    public final void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        processar(req, resp);
    }

    @Override
    public final void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        processar(req, resp);
    }

    private void processar(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> erros = new HashMap<String, String>();
        if (MODO_CALCULAR.equals(req.getParameter(PARAMETRO_MODO_CALCULO))) {
            Double num1 = getParametroDouble(req, PARAMETRO_NUM_1, "Número 1",
                    erros);
            Double num2 = getParametroDouble(req, PARAMETRO_NUM_2, "Número 2",
                    erros);
            Operacao operacao = getParametroOperacao(req, erros);
            if (erros.isEmpty()) {
                double resultado = operacao.calcular(num1, num2);
                req.setAttribute("resultado", resultado);
            } else {
                req.setAttribute("erros", erros);
            }
            req.setAttribute(PARAMETRO_NUM_1, num1);
            req.setAttribute(PARAMETRO_NUM_2, num2);
            req.setAttribute("operacaoSelecionada", operacao);
        }
        req.setAttribute("operacoes", Operacao.values());
        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/calculadora.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Recupera o parâmetro e converte para um double.
     *
     * @param req
     *            requisição para extração do parâmetro
     * @param nome
     *            nome do parâmetro
     * @param campo
     *            nome do campo associado com o parâmetro
     * @param erros
     *            contexto para armazenamento da mensagem de erro, caso ocorra
     *            algum
     * @return parâmetro convertido em double, ou null caso o parametro seja
     *         nulo, vazio ou não possa ser convertido para double.
     */
    private Double getParametroDouble(final HttpServletRequest req,
            final String nome,
            final String campo, final Map<String, String> erros) {
        String numStr = req.getParameter(nome);
        if (numStr == null || numStr.isEmpty()) {
            erros.put(campo, "Número requerido!");
            return null;
        }
        Double num = null;
        try {
            num = Double.parseDouble(numStr);
        } catch (NumberFormatException nfe) {
            erros.put(campo, "Número inválido!");
        }
        return num;
    }

    /**
     *Recupera o parâmetro operação.
     *
     * @param req
     *            requisição para extração do parâmetro
     * @param erros
     *            contexto para armazenamento da mensagem de erro, caso ocorra
     *            algum
     * @return parâmetro convertido em {@link Operacao} ou null caso o parâmetro
     *         não possa ser convertido em {@link Operacao}
     */
    private Operacao getParametroOperacao(final HttpServletRequest req,
            final Map<String, String> erros) {
        Operacao operacao = null;
        try {
            operacao = Operacao.valueOf(req.getParameter(PARAMETRO_OPERACAO));
        } catch (IllegalArgumentException | NullPointerException ex) {
            erros.put("Operação", "Operação inválida");
        }
        return operacao;
    }

    /**
     *Enumeração encapsula e simplifica a utilização 
     *das operações matemáticas.
     **/
    public enum Operacao {

        /**
         * Efetua a adição.
         */
        ADICIONAR("Adicionar") {
            @Override
            double calcular(final double num1, final double num2) {
                return Calculadora.adicionar(num1, num2);
            }
        },
        /**
         * Efetua a subtração.
         */
        SUBTRAIR("Subtrair") {
            @Override
            double calcular(final double num1, final double num2) {
                return Calculadora.subtrair(num1, num2);
            }
        },
        /**
         * Efetua a multiplicação.
         */
        MULTIPLICAR("Multiplicar") {
            @Override
            double calcular(final double num1, final double num2) {
                return Calculadora.multiplicar(num1, num2);
            }
        },
        /**
         * Efetua a divisão.
         */
        DIVIDIR("Dividir") {
            @Override
            double calcular(final double num1, final double num2) {
                return Calculadora.dividir(num1, num2);
            }
        };
        /**
         * Label da operação.
         */
        private String label;

        /**
         *Constói uma nova operação.
         * @param label Label da operação
         */
        private Operacao(final String label) {
            this.label = label;
        }

        /**
         *@return Label da operação
         */
        public String getLabel() {
            return label;
        }

        /**
         * Método de cálculo, que é oferecido por cada operação constante da
         * Enum.
         *
         * @param num1
         *            número utilizado no cálculo da operação
         * @param num2
         *            número utilizado no cálculo da operação
         * @return resultado do cálculo da operação
         */
        abstract double calcular(double num1, double num2);

    }

}
