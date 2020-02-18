package project;

import project.cache_annotations.Cache;

import java.lang.reflect.Method;
import java.util.*;

class AnnotationCacheHandler {
    static HashMap<String, Object>  validateStringLength(CacheProxy cacheHandler, Method method) throws Exception {
        HashMap<String, Object> cacheParams = new HashMap<>();

        cacheParams.put("rootFolder", cacheHandler.rootFolder);

        if (method.isAnnotationPresent(Cache.class)) {
            Cache an = method.getAnnotation(Cache.class);

            cacheParams.put("cacheType", an.cacheType());
            cacheParams.put("listSize", an.listSize());
            cacheParams.put("fileNamePrefix", an.fileNamePrefix());
            cacheParams.put("zip", an.zip());
            cacheParams.put("identityBy", an.identityBy());
        }

        return cacheParams;
    }


}



























