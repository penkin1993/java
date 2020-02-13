package project;

import project.cache_annotations.CacheType;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;


public class CacheHandler implements InvocationHandler {
    private  Map<List<Object>, Object> cache;
    private String defaultRootFolder;
    private CacheType defaultCacheType;
    private int defaultListSize;
    private String defaultFileNamePrefix;
    private boolean defaultZip;
    private Class[] defaultIdentityBy;

    private Object delegate;

    public CacheHandler(String defaultRootFolder, CacheType defaultCacheType, int defaultListSize,
                        String defaultFileNamePrefix, boolean defaultZip, Class[] defaultIdentityBy){

        this.defaultRootFolder = defaultRootFolder;
        this.defaultCacheType = defaultCacheType;
        this.defaultListSize = defaultListSize;
        this.defaultFileNamePrefix = defaultFileNamePrefix;
        this.defaultZip = defaultZip;
        this.defaultIdentityBy = defaultIdentityBy;
    }

    private CacheHandler(CacheHandler cacheHandler, Object delegate) {
        this.defaultRootFolder = cacheHandler.defaultRootFolder;
        this.defaultCacheType = cacheHandler.defaultCacheType;
        this.defaultListSize = cacheHandler.defaultListSize;
        this.defaultFileNamePrefix = cacheHandler.defaultFileNamePrefix;
        this.defaultZip = cacheHandler.defaultZip;
        this.defaultIdentityBy = cacheHandler.defaultIdentityBy;

        this.cache = new HashMap<>();
        this.delegate = delegate;
    }


    public <T> T cache(T service){
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                service.getClass().getInterfaces(),
                new CacheHandler(this, service)
        );
    }


    // TODO: Как работает newProxyInstance ???

    // TODO: Само кэширование поручить отдельному классу

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // TODO: 1. Добавить в словарь еще и имя класса

        // TODO 3. Снимать аннотации и кэшировать согласно ним (Как правильно это написать с точки зрения архитектуры ???)
        // TODO: Создать отдельный класс со статик методом. Он и будет сохранять. Реализовать это после предыдущих шагов


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

    /*
    public static void main(String[] args) {
        Class[] defaultIdentityBy = {Object.class};
        CacheHandler cacheHandler = new CacheHandler("./", CacheType.FILE, 100,
                "BLABLA", true, defaultIdentityBy);

        String newBla = cacheHandler.cache("dfsdfd");
    }
     */
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
