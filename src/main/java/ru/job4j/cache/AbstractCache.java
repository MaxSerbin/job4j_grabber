package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> softRef = new SoftReference<>(value);
        cache.put(key, softRef);
    }

    public V get(K key) {
        V rsl = null;
        if (cache.containsKey(key)) {
            rsl = cache.get(key).get();
        }
        if (rsl == null) {
            return load(key);
        } else {
             return rsl;
        }
    }

    protected abstract V load(K key);
}
