package br.com.devmedia.jm.calculadoraweb.web.util;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Tomcat embedded para execução dos testes
 * funcionais
 *
 */
public class TomcatEmbedded {

    private final Tomcat tomcat;

    public static final Integer DEFAULT_PORT = 8080;

    private static final String DEFAULT_BASE_DIR = "target/tomcat";

    private TomcatEmbedded(Builder builder) {
        tomcat = new Tomcat();

        if (builder.port > 0) {
            tomcat.setPort(builder.port);
        } else {
            tomcat.setPort(DEFAULT_PORT);
        }
        if (builder.baseDir == null) {
            tomcat.setBaseDir(new File(DEFAULT_BASE_DIR).getAbsolutePath());
        } else {
            tomcat.setBaseDir(builder.baseDir);
        }
        try {
            Context ctx = tomcat.addWebapp(builder.contextPath, new File(
                    builder.appBase).getAbsolutePath());
            ctx.setReloadable(builder.reloadable);
        } catch (Exception e) {
            throw new RuntimeException("Erro criando "
                    + TomcatEmbedded.class.getName(), e);
        }
    }

    /**
     * Inicia a execução do tomcat
     */
    public void start() {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException("Erro iniciando "
                    + TomcatEmbedded.class.getName(), e);
        }
    }

    public void stop() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            throw new RuntimeException("Erro parando "
                    + TomcatEmbedded.class.getName(), e);
        }
    }

    /**
     * Builder para criação de instâncias do Tomcat embedded
     */
    public static class Builder {

        private int port;

        private String baseDir;

        private String contextPath;

        private String appBase;

        private boolean reloadable;

        /**
         * Porta em que o Tomcat será executado
         * 
         * @param port
         *            número da porta
         * @return builder
         */
        public Builder port(int port) {
            this.port = port;
            return this;
        }

        /**
         * Diretório base para o Tomcat
         * 
         * @param baseDir
         *            diretório base
         * @return builder
         */
        public Builder baseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        /**
         * Contexto da aplicação no Tomcat
         * 
         * @param contextPath
         *            contexto da aplicação
         * @return builder
         */
        public Builder contextPath(String contextPath) {
            this.contextPath = contextPath;
            return this;
        }

        /**
         * Base da aplicação no Tomcat
         * 
         * @param appBase
         *            diretório base da aplicaçào
         * @return builder
         */
        public Builder appBase(String appBase) {
            this.appBase = appBase;
            return this;
        }

        /**
         * Flag indicando se o Tomcat deve recarregar o contexto
         * 
         * @param reloadable
         *            flag indicando se a aplicação deve ser recarregada em
         *            alterações
         * @return builder
         */
        public Builder reloadable(boolean reloadable) {
            this.reloadable = reloadable;
            return this;
        }

        /**
         * Constrói um nova instância do Tomcat embedded
         * 
         * @return instância criada Tomcat embedded
         */
        public TomcatEmbedded build() {
            return new TomcatEmbedded(this);
        }

    }

}