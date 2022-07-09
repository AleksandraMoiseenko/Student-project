package ru.stm.student.example.datacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class CacheStore<T> {
    private Cache<Integer, T> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(120, TimeUnit.SECONDS)
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            .build();

    //Method to fetch previously stored record using record key
    public T get(Integer key) {
        return cache.getIfPresent(key);
    }

    //Method to put a new record in Cache Store with record key
    public void add(Integer key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
            System.out.println("Record stored in "
                + value.getClass().getSimpleName()
                + " Cache with Key = " + key);
        }
    }
}
