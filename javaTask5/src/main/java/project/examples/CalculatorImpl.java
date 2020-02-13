package project.examples;

import project.cache_annotations.Cache;
import project.cache_annotations.CacheType;

public class CalculatorImpl implements Calculator {
    @Override
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", identityBy = {String.class, double.class})
    public int run(String arg) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {

        }
        return arg.hashCode();
    }

}
