package liteweb.cache;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * LRU Cache
 * Author: sinar
 * 2023/6/18 17:22
 */
public class LRUCache<K, V> {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<K, Node<Map.Entry<K, V>>> valueMap = new HashMap<>();
    private final SortedByUsageLinkedList<Map.Entry<K, V>> valueStore = new SortedByUsageLinkedList<>();
    private final int limit;

    public LRUCache(int limit) {
        this.limit = limit;
    }

    public int size() {
        return valueStore.size();
    }

    public boolean isEmpty() {
        return this.valueStore.isEmpty();
    }

    public void put(@NotNull K key, @Nullable V value) {
        if (value == null) return;
        lock.writeLock().lock();
        try {
            Map.Entry<K, V> newValue = Map.entry(key, value);
            Node<Map.Entry<K, V>> newNode;
            if (valueMap.containsKey(key)) {
                newNode = valueStore.updateToFront(valueMap.get(key), newValue);
            } else {
                if (this.size() >= this.limit) {
                    evictOldest();
                }
                newNode = valueStore.addToFront(newValue);
            }
            valueMap.put(key, newNode);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Optional<V> get(@NotNull K key) {
        lock.readLock().lock();
        try {
            Node<Map.Entry<K, V>> cache = this.valueMap.get(key);
            if (cache != null && !cache.isEmpty()) {
                valueMap.put(key, valueStore.moveToFront(cache));
                return Optional.of(cache.getValue().getValue());
            } else return Optional.empty();
        } finally {
            lock.readLock().unlock();
        }
    }

    public Optional<V> evictIfExist(@NotNull K key) {
        if (!valueMap.containsKey(key)) return Optional.empty();
        lock.writeLock().lock();
        try {
            Node<Map.Entry<K, V>> cache = valueMap.remove(key);
            valueStore.detach(cache);
            return Optional.of(cache.getValue().getValue());
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void evictOldest() {
        lock.writeLock().lock();
        try {
            Node<Map.Entry<K, V>> oldest = valueStore.removeOldest();
            if (!oldest.isEmpty()) {
                valueMap.remove(oldest.getValue().getKey());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Map.Entry<K, V> getLatestValue() {
        lock.readLock().lock();
        try {
            return valueStore.getHead().getValue();
        } finally {
            lock.readLock().unlock();
        }
    }

    public Map.Entry<K, V> getOldestValue() {
        lock.readLock().lock();
        try {
            return valueStore.getTail().getValue();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void cleanAll() {
        lock.writeLock().lock();
        try {
            valueMap.clear();
            valueStore.clean();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
