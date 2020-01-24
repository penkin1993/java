package project;

import java.lang.annotation.*;

enum CashType {
    FILE,
    IN_MEMORY
}

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@interface Cache {

    CashType cacheType(); // TODO ???

    int listList();

    String fileNamePrefix();

    boolean zip();

    Class[] identityBy();

} // TODO: Параметризацию вбухать сюда

// TODO: Нужны будут рефлекшены !!! Но это уже в имплементации !!!