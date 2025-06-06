package app.service;

import java.util.concurrent.ConcurrentHashMap;

public class PerClassCounter {
    private static final ConcurrentHashMap<Class<?>, Integer> counters = new ConcurrentHashMap<>();

    public static synchronized int getNextNumero(Class<?> clazz) {
        int next = counters.getOrDefault(clazz, 0) + 1;
        counters.put(clazz, next);
        return next;
    }
}
