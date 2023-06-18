package liteweb.cache;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: sinar
 * 2023/6/18 23:17
 */
class LRUCacheTest {
    @Test
    public void verifyIsEmpty() {
        var cache = new LRUCache<String, Integer>(10);
        assertTrue(cache.isEmpty());
    }

    @Test
    public void verifySize() {
        var cache = new LRUCache<String, Integer>(10);
        cache.put("foo", 1);
        cache.put("bar", 2);
        cache.put("baz", 3);

        assertFalse(cache.isEmpty());
        assertEquals(3, cache.size());
    }

    @Test
    public void verifyPutOverLimit() {
        var cache = new LRUCache<String, Integer>(10);
        IntStream.rangeClosed(1, 11).forEach(i -> cache.put(String.valueOf(i), i));

        assertEquals(10, cache.size());
        assertEquals(11, cache.getLatestValue().getValue());
        assertEquals(2, cache.getOldestValue().getValue());
    }

    @Test
    public void verifyRenewPut() {
        var cache = new LRUCache<String, Integer>(10);
        cache.put("foo", 1);
        cache.put("bar", 2);
        cache.put("baz", 3);

        cache.put("foo", 4);

        assertEquals(3, cache.size());
        //noinspection OptionalGetWithoutIsPresent
        assertEquals(4, cache.get("foo").get());
        assertEquals("foo", cache.getLatestValue().getKey());
        assertEquals("bar", cache.getOldestValue().getKey());
    }

    @Test
    public void verifyGetNonExist() {
        var cache = new LRUCache<String, Integer>(10);
        cache.put("foo", 1);
        cache.put("bar", 2);
        cache.put("baz", 3);

        assertTrue(cache.get("qux").isEmpty());
    }

    @Test
    public void verifyEvictIfExist() {
        var cache = new LRUCache<String, Integer>(10);
        cache.put("foo", 1);

        var result = cache.evictIfExist("foo");

        assertTrue(result.isPresent());
        assertEquals(1, result.get());
        assertTrue(cache.isEmpty());
    }

    @Test
    public void verifyEvictIfExistOnNonExist() {
        var cache = new LRUCache<String, Integer>(10);
        cache.put("foo", 1);

        assertTrue(cache.evictIfExist("bar").isEmpty());
    }

    @Test
    public void verifyCleanAll() {
        var cache = new LRUCache<String, Integer>(10);
        cache.put("foo", 1);
        cache.put("bar", 2);
        cache.put("baz", 3);

        cache.cleanAll();

        assertTrue(cache.isEmpty());
        assertEquals(0, cache.size());
    }
}
