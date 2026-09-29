import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.*;
import javax.net.ssl.*;

public class ServidorHTTPS {

    public static void main(String[] args) {
        try {
            // Criar servidor HTTPS na porta 8443
            HttpsServer servidor = HttpsServer.create(
                new InetSocketAddress(8443), 0
            );

            // Configurar SSL (seguranca)
            servidor.setHttpsConfigurator(
                new HttpsConfigurator(criarSSL())
            );

            // Quando alguem acessar "/", mostrar a pagina
            servidor.createContext("/", exchange -> {
                try {
                    // Ler o arquivo HTML
                    String html = lerArquivo("index.html");

                    // Definir tipo de conteudo
                    exchange.getResponseHeaders().set(
                        "Content-Type",
                        "text/html;charset=UTF-8"
                    );

                    // Enviar para o navegador
                    byte[] resposta = html.getBytes(StandardCharsets.UTF_8);
                    exchange.sendResponseHeaders(200, resposta.length);
                    OutputStream saida = exchange.getResponseBody();
                    saida.write(resposta);
                    saida.close();

                } catch (Exception e) {
                    String erro = "Erro ao carregar pagina: " + e.getMessage();
                    exchange.sendResponseHeaders(500, erro.length());
                    exchange.getResponseBody().write(erro.getBytes());
                    exchange.getResponseBody().close();
                }
            });

            // Iniciar o servidor
            servidor.start();
            System.out.println("===================================");
            System.out.println("Servidor HTTPS rodando!");
            System.out.println("Acesse: https://localhost:8443");
            System.out.println("===================================");
            System.out.println("Pressione Ctrl+C para parar");

        } catch (Exception e) {
            System.err.println("Erro ao iniciar servidor:");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Funcao que configura o SSL
    static SSLContext criarSSL() throws Exception {
        // Carregar o certificado
        KeyStore ks = KeyStore.getInstance("JKS");
        FileInputStream arquivo = new FileInputStream("keystore.jks");
        ks.load(arquivo, "senha123".toCharArray());
        arquivo.close();

        // Configurar gerenciador de chaves
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm()
        );
        kmf.init(ks, "senha123".toCharArray());

        // Criar contexto SSL
         SSLContext ssl = SSLContext.getInstance("TLS");
        ssl.init(kmf.getKeyManagers(), null, null);
        return ssl;
    }

    // Funcao para ler arquivo
    static String lerArquivo(String nome) throws Exception {
        return new String(
            Files.readAllBytes(Paths.get(nome)),
            StandardCharsets.UTF_8
        );
    }
}