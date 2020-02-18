package project.examples;

import project.cache_annotations.Cache;
import project.cache_annotations.CacheType;

import java.util.Date;
import java.util.List;

public interface Service {
    @Cache(cacheType = CacheType.FILE, listSize = 100, fileNamePrefix = "data", zip = false, identityBy = {double.class})
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = CacheType.FILE, zip = false, identityBy = {String.class})
    List<String> work(String item);
}






