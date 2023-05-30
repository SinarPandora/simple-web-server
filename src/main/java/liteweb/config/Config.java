package liteweb.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("unused")
public final class Config {
    private final Properties PROPERTIES = new Properties();
    private final Map<String, Object> CACHE = new HashMap<>();

    public Config(String path) {
        try(InputStream inputStream = Config.class.getResourceAsStream(path)) {
            if (inputStream == null) throw new FileNotFoundException("File not exist");
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Can not read config.properties", e);
        }
    }

    public String getStr(String key) {
        return CACHE.computeIfAbsent(key, PROPERTIES::getProperty).toString();
    }

    public Integer getInt(String key) {
        return ((Integer) CACHE.computeIfAbsent(key, k -> Integer.parseInt(PROPERTIES.getProperty(k))));
    }

    public Boolean getBool(String key) {
        return ((Boolean) CACHE.computeIfAbsent(key, k -> Boolean.parseBoolean(PROPERTIES.getProperty(k))));
    }
}
