package project;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Cache {

    CacheType cacheType() default CacheType.IN_MEMORY;

    int listSize() default 100_000;

    String fileNamePrefix() default "data_0";

    boolean zip() default false;

    Class[] identityBy() default {String.class};

}
