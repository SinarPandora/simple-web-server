package liteweb.config;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigTest {
    @Test
    void shouldReadIntValue_WhenGetConfig() {
        Config config = new Config("/config.properties");
        assertEquals(2, config.getInt("number"));
    }

    @Test
    void shouldReadBooleanValue_WhenGetConfig() {
        Config config = new Config("/config.properties");
        assertEquals(true, config.getBool("boolean"));
    }

    @Test
    void shouldReadStringValue_WhenGetConfig() {
        Config config = new Config("/config.properties");
        assertEquals("foo", config.getStr("string"));
    }

    @Test
    void shouldThrowException_WhenConfigNotExist() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> new Config("not_exist"));
        assertEquals("Can not read config.properties", e.getMessage());
        assertEquals(FileNotFoundException.class, e.getCause().getClass());
    }
}
