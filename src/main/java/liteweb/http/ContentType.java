package liteweb.http;

import java.util.Map;
import java.util.Objects;

/**
 * ContentType enum uses the file extension to loosely map the available content type based on common media types:
 * <a href="http://en.wikipedia.org/wiki/Internet_media_type">Internet_media_type</a>
 */
final class ContentType {
    private static final Map<String, String> EXTENSION_MAP = Map.of(
            "CSS", "Content-Type: text/css",
            "GIF", "Content-Type: image/gif",
            "HTM", "Content-Type: text/html",
            "HTML", "Content-Type: text/html",
            "ICO", "Content-Type: image/x-icon",
            "JPG", "Content-Type: image/jpeg",
            "JPEG", "Content-Type: image/jpeg",
            "PNG", "Content-Type: image/png",
            "XML", "Content-type: text/xml");

    private ContentType() {
    }

    static String of(String extension) {
        String defined = EXTENSION_MAP.get(extension.toUpperCase());
        return Objects.requireNonNullElse(defined, "Content-Type: text/html");
    }
}
