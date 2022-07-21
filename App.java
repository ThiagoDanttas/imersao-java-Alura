import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws Exception{

        // fazer conexão HTTP e buscar os tops 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        HttpClient client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam(titulo, imagem, classificacao)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for ( Map<String, String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            String nomeArquivo = titulo + ".png";
            InputStream inputStream = new URL(urlImagem).openStream();

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }

    }

}
