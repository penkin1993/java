package project.examples;

import project.cache_annotations.Cache;
import project.cache_annotations.CacheType;

public interface Calculator {
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", identityBy = {String.class, double.class})
    int run(String arg);
}
