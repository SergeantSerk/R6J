package test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.com.github.joecourtneyw.Auth;
import main.com.github.joecourtneyw.R6J;
import main.com.github.joecourtneyw.R6Player;
import main.com.github.joecourtneyw.declarations.Platform;
import org.junit.jupiter.api.Test;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;


public class ApiTest {


    @Test()
    public void testApi() throws IOException {

        InputStream testCredentialsStream = ApiTest.class.getResourceAsStream("/testCredentials.json");

        JsonNode credentials = new ObjectMapper().readTree(testCredentialsStream);

        Auth auth = new Auth(credentials.get("testLogin").asText(), credentials.get("testPassword").asText());

        R6J api = new R6J(auth);

        // The test player they have given should exist on the ubisoft servers
        assertTrue(api.playerExists(credentials.get("testPlayerName").asText(), Platform.UPLAY));

        R6Player player = api.getPlayerByName(credentials.get("testPlayerName").asText(), Platform.UPLAY);

        // The player we grabbed should have the name we requested
        assertEquals(credentials.get("testPlayerName").asText(), player.getName());
    }


}
