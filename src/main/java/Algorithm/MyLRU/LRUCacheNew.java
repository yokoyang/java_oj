package Algorithm.MyLRU;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheNew {
    private int capacity;
    private Map<Integer, Integer> cache;

    public LRUCacheNew(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
             protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int getCache(int key) {
        return cache.getOrDefault(key, -1);
    }

    public int setCache(int key, int v) {
        return cache.put(key, v);
    }
}
