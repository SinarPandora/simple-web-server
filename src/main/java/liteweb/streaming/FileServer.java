package liteweb.streaming;

import liteweb.Server;
import liteweb.cache.LRUCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

/**
 * File Server
 * Author: sinar
 * 2023/6/18 18:13
 */
public class FileServer {
    private FileServer() {
    }

    private static final LRUCache<String, FileInfo> CACHE = new LRUCache<>(Server.CONFIG.getInt("app.cache_size"));
    private static final int FILE_SIZE_LIMIT = Server.CONFIG.getInt("app.cache_file_size_in_mb");
    private static final int BYTE_TO_MB = 1024 * 1024;

    public static byte[] serve(File file) throws IOException {
        byte[] result;
        String path = file.getAbsolutePath();
        if (canBeCached(file)) {
            long lastModified = file.lastModified();
            Optional<FileInfo> cache = CACHE.get(path);
            if (cache.isPresent() && cache.get().lastModified() == lastModified) {
                result = cache.get().bytes();
            } else {
                result = getBytes(file);
                CACHE.put(path, FileInfo.of(result, lastModified));
            }
        } else {
            CACHE.evictIfExist(path);
            result = getBytes(file);
        }
        return result;
    }

    public static boolean canBeCached(File file) {
        return 1.0 * file.length() / BYTE_TO_MB <= FILE_SIZE_LIMIT;
    }

    private static byte[] getBytes(File file) throws IOException {
        int length = (int) file.length();
        byte[] array = new byte[length];
        try (InputStream in = Files.newInputStream(file.toPath())) {
            int offset = 0;
            while (offset < length) {
                int count = in.read(array, offset, (length - offset));
                offset += count;
            }
        }
        return array;
    }
}
