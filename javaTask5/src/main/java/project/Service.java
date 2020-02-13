package project;

import java.util.Date;
import java.util.List;

interface Service {
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", identityBy = {String.class, double.class})
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = CacheType.FILE, listSize = 100, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    List<String> work(String item);
}





