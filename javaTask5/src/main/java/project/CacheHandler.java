package project;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class CacheHandler implements InvocationHandler {
    private final Map<List<Object>, Object> cache = new HashMap<>();
    private final Object delegate;


    public CacheHandler(Object delegate) {
        this.delegate = delegate;
    }

    public static <T> T cache(T service){
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader().getParent(),
                service.getClass().getInterfaces(),
                new CacheHandler(service)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> key = toKey(method, args);
        return cache.computeIfAbsent(key, k -> {
            try {
                return method.invoke(delegate, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<Object> toKey(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }

}
