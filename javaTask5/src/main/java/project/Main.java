package project;

import project.CacheProxy;
import project.Loader;

public class Main {
    public static void main(String[] args) {

        CacheProxy cacheProxy = new CacheProxy(...); 
        Service service = cacheProxy.cache(new ServiceImpl());
         Loader loader = cacheProxy.cache(new LoaderImpl());

    }
}


// CacheProxy -- кэширующий прокси (этот паттерн и испольщовать)
// с методом кэш. Т.е. он декорирует объект и возвращает его обернутую версию (паттерн Декоратор ?).
// Использовать дженерики !!!

// Реализовывать еще и метод аннотации cashe ???