
import java.io.IOException;
import java.net.URI;
import java.net.http.*;


public class ClienteHttp {

    public String buscaDados(String url) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            URI endereco = URI.create(url);
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return body;
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }


    }
}