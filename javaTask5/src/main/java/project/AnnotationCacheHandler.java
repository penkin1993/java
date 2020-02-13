package project;

import project.cache_annotations.Cache;

import java.lang.reflect.Method;
import java.util.*;

class AnnotationCacheHandler {
    private final Map<List<Object>, Object> cache = new HashMap<>();

    static void validateStringLength(Method method) throws Exception {

        if (method.isAnnotationPresent(Cache.class)) {
            Cache an = method.getAnnotation(Cache.class);

            System.out.println(an.cacheType());
            System.out.println(an.listSize());
            System.out.println(an.fileNamePrefix());
        }

    }


}



























