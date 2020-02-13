package project;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class CacheHandler implements InvocationHandler {
    private CacheDumpLoader cacheDumpLoader = new CacheDumpLoader();
    private Object delegate;
    String rootFolder;

    public CacheHandler(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    private CacheHandler(CacheHandler cacheHandler, Object delegate) {
        this.rootFolder = cacheHandler.rootFolder;
        this.delegate = delegate;
        this.cacheDumpLoader = new CacheDumpLoader();
    }

    public <T> T cache(T service) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                service.getClass().getInterfaces(),
                new CacheHandler(this, service)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> key = toKey(method, args);
        if (cacheDumpLoader.containsKey(key)) {
            return cacheDumpLoader.load(key); // вернуть результат из кэша

        } else {
            // парарметры кэширования
            HashMap<String, Object> cacheParams = AnnotationCacheHandler.validateStringLength(this, method);
            Object result = method.invoke(delegate, args);
            cacheDumpLoader.dump(key, cacheParams, result);
            return result;
        }

    }

    private List<Object> toKey(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }
}


/*
public class CacheHandler implements InvocationHandler {
    private final Map<List<Object>, Object> cache = new HashMap<>();
    private Object delegate;

    public CacheHandler(Object delegate) {
        this.delegate = delegate;
    }

    static public <T> T cache(T service) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
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

 */
