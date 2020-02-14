package project;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class CacheProxy implements InvocationHandler {
    private CacheDumpLoader cacheDumpLoader = new CacheDumpLoader();
    private Object delegate;
    String rootFolder;

    public CacheProxy(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    private CacheProxy(CacheProxy cacheProxy, Object delegate) {
        this.rootFolder = cacheProxy.rootFolder;
        this.delegate = delegate;
        this.cacheDumpLoader = new CacheDumpLoader();
    }

    public <T> T cache(T service) {
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                service.getClass().getInterfaces(),
                new CacheProxy(this, service)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HashMap<String, Object> cacheParams = AnnotationCacheHandler.validateStringLength(this, method);

        //if cacheParams.containsKey("identityBy"){ // TODO: add
        List<Class> identityBy = Arrays.asList((Class[]) cacheParams.remove("identityBy"));
        Object[] keyArgs = Arrays.stream(args).filter(i -> identityBy.contains(i.getClass())).toArray();
        List<Object> key = toKey(method, keyArgs);

        if (cacheDumpLoader.containsKey(key)) {
            return cacheDumpLoader.load(key); // вернуть результат из кэша

        } else {
            // парарметры кэширования
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
