package project.examples;

import project.cache_annotations.Cache;
import project.cache_annotations.CacheType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {

    @Override
    @Cache(cacheType = CacheType.FILE, listSize = 100, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    public List<String> run(String item, double value, Date date) {
        return null;
    }

    @Override
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", identityBy = {String.class, double.class})
    public List<String> work(String item) {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {

        }
        ArrayList<String> list = new ArrayList<>();
        list.add("ad");
        return list; // TODO: Не должен возврашать null ?????
    }
}
