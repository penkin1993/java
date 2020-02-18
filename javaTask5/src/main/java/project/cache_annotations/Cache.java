package project.cache_annotations;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    CacheType cacheType() default CacheType.IN_MEMORY;

    int listSize() default 100_000;

    String fileNamePrefix() default "__None__";

    boolean zip() default true;

    Class[] identityBy() default {String.class, double.class};

}
