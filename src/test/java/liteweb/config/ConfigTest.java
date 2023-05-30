package liteweb.config;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigTest {
    @Test
    public void shouldReadIntValue_WhenGetConfig() {
        Config config = new Config("/config.properties");
        assertEquals(2, config.getInt("number"));
    }

    @Test
    public void shouldReadBooleanValue_WhenGetConfig() {
        Config config = new Config("/config.properties");
        assertEquals(true, config.getBool("boolean"));
    }

    @Test
    public void shouldReadStringValue_WhenGetConfig() {
        Config config = new Config("/config.properties");
        assertEquals("foo", config.getStr("string"));
    }

    @Test
    public void shouldThrowException_WhenConfigNotExist() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> new Config("not_exist"));
        assertEquals("Can not read config.properties", e.getMessage());
        assertEquals(FileNotFoundException.class, e.getCause().getClass());
    }
}
