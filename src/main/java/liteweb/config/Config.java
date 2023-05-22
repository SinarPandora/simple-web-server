package liteweb.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("unused")
public final class Config {
    private final Properties PROPERTIES = new Properties();
    private final Map<String, Object> CACHE = new HashMap<>();

    public Config(String path) {
        try {
            PROPERTIES.load(Config.class.getResourceAsStream(path));
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
}
