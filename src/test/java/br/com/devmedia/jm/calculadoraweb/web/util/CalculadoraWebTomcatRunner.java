package br.com.devmedia.jm.calculadoraweb.web.util;

import br.com.devmedia.jm.calculadoraweb.web.util.TomcatEmbedded.Builder;

/**
 * Executa a Calculadora Web no Tomcat Embedded
 */
public class CalculadoraWebTomcatRunner {

    private static final String DEFAULT_CONTEXT = "calculadora-web";

    private static final String APP_BASE = "target/calculadora-web";

    private TomcatEmbedded tomcat;

    private int port;

    private String context;

    private String urlAplicacao;

    public CalculadoraWebTomcatRunner() {
        this(TomcatEmbedded.DEFAULT_PORT);
    }

    public CalculadoraWebTomcatRunner(final int port) {
        this(port, DEFAULT_CONTEXT);
    }

    public CalculadoraWebTomcatRunner(final int port, final String contextPath) {
        this.port = port;
        this.context = contextPath;
        this.urlAplicacao = String.format("http://localhost:%d/%s", this.port,
                this.context);
    }

    /**
     * @return porta em que o Tomcat esta sendo executado
     */
    public int getPort() {
        return port;
    }

    /**
     * @return context da aplicação
     */
    public String getContext() {
        return context;
    }

    public String getContextPath() {
        return "/" + context;
    }

    /**
     * @return URL da aplicação
     */
    public String getUrlAplicacao() {
        return urlAplicacao;
    }

    /**
     * Roda a aplicação
     */
    public void run() {
        Builder builder = new Builder();
        tomcat = builder.port(port).appBase(APP_BASE)
                .contextPath(getContextPath())
                .reloadable(true).build();
        tomcat.start();
    }

    public void stop() {
        tomcat.stop();
    }

    /**
     * Roda a aplicação
     * 
     * @param args
     *            argumentos para execução
     */
    public static void main(String[] args) {
        new CalculadoraWebTomcatRunner().run();
    }

}
