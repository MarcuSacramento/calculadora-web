package br.com.devmedia.jm.calculadoraweb.core;

/**
 * Implementação das operações básicas da matemática.
 */
public final class Calculadora {

    private Calculadora() {
        throw new UnsupportedOperationException("Instanciação não permitida!");
    }

    /**
     * Retorna a soma da parcela1 e parcela2.
     * 
     * @param parcela1
     *            parcela1 da adição
     * @param parcela2
     *            parcela2 da adição
     * @return soma da parcela1 e parcela2
     */
    public static double adicionar(final double parcela1,
            final double parcela2) {
        return parcela1 + parcela2;
    }

    /**
     * Retorna a diferença da subtração do minuendo e subtraendo.
     * 
     * @param minuendo
     *            minuendo para subtração
     * @param subtraendo
     *            subtraendo para subtração
     * @return diferença da subtração de minuendo e subtraendo
     */
    public static double subtrair(final double minuendo,
            final double subtraendo) {
        return minuendo - subtraendo;
    }

    /**
     * Retorna o resultado da divisão do dividendo pelo divisor.
     * 
     * @param dividendo
     *            dividendo da operação
     * @param divisor
     *            divisor da operação
     * @return resultado da divisão do dividendo pelo divisor
     */
    public static double dividir(final double dividendo, final double divisor) {
        return dividendo / divisor;
    }

    /**
     * Retorna o produto da multiplicação do multiplicando pelo multiplicador.
     * 
     * @param multiplicando
     *            multiplicando da operação
     * @param multiplicador
     *            multiplicador da operação
     * @return o produto da multiplicação do multiplicando pelo multiplicador
     */
    public static double multiplicar(final double multiplicando,
            final double multiplicador) {
        return multiplicando * multiplicador;
    }

}
