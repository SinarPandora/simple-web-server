package liteweb.streaming;

/**
 * File info
 * Author: sinar
 * 2023/6/18 18:16
 */
public record FileInfo(byte[] bytes, long lastModified) {
    public static FileInfo of(byte[] bytes, long lastUpdate) {
        return new FileInfo(bytes, lastUpdate);
    }
}
