package project.examples;

import project.cache_annotations.Cache;
import project.cache_annotations.CacheType;

public interface Calculator {
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data")
    int run(String arg);
}
