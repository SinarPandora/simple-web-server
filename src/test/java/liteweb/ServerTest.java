package liteweb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ServerTest {

    @ParameterizedTest
    @CsvSource({", 8080", "1234, 1234", "8080, 8080"})
    void shouldReturnTrue_whenValid(String value, int port) {
        String[] args =  value == null ? new String[]{} : new String[]{value};
        assertEquals(port, Server.getValidPortParam(args));
    }

    @ParameterizedTest
    @CsvSource({"asda", "0", "65535"})
    void wrongParamThrowException(String value) {
        String[] args = {value};
        assertThrows(NumberFormatException.class, () -> Server.getValidPortParam(args));
    }

    @Test
    void shouldHandleSocket() throws IOException, InterruptedException {
        ServerSocket serverSocket = Mockito.mock(ServerSocket.class);
        Socket clientSocket = Mockito.mock(Socket.class);
        given(serverSocket.accept()).willReturn(clientSocket);
        given(clientSocket.getInputStream()).willReturn(new ByteArrayInputStream("HEAD / HTTP/1.1\n\n".getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        given(clientSocket.getOutputStream()).willReturn(output);
        ExecutorService exec = Executors.newSingleThreadExecutor();

        Server.handle(serverSocket, exec);

        exec.shutdown();
        assertTrue(exec.awaitTermination(10, TimeUnit.SECONDS));
        assertEquals("""
                HTTP/1.0 200 OK
                Connection: close
                Server: simple-web-server


                """, output.toString(StandardCharsets.UTF_8).replaceAll("\r\n", "\n"));
    }
}
