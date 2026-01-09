package example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloResourceTest {

    @LocalServerPort
    private int port;

    @LocalManagementPort
    private int managementPort;

    private HttpClient httpClient;

    @BeforeEach
    void setUp() {
        httpClient = HttpClient.newHttpClient();
    }

    @Test
    void should_return_hello_message_from_jersey_endpoint() throws Exception {
        // Given
        var url = "http://localhost:%d/hello".formatted(port);
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isEqualTo("Hello from Jersey!");
    }

    @Test
    void should_return_hello_with_name_from_jersey_endpoint() throws Exception {
        // Given
        var name = "John";
        var url = "http://localhost:%d/hello/%s".formatted(port, name);
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isEqualTo("Hello, %s!".formatted(name));
    }

    @Test
    void should_return_ready_status_from_readyz_endpoint() throws Exception {
        // Given
        var url = "http://localhost:%d/readyz".formatted(port);
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).contains("UP");
    }

    @Test
    void should_return_ready_status_from_actuator_health_readiness() throws Exception {
        // Given
        var url = "http://localhost:%d/actuator/health/readiness".formatted(managementPort);
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // When
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).contains("UP");
    }
}
