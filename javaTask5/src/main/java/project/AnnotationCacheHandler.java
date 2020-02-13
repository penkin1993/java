package project;

import java.lang.reflect.Method;
import java.util.*;

//public class AnnotationCacheHandler{
//    public String name = "Grig";
//}



public class AnnotationCacheHandler {
    private final Map<List<Object>, Object> cache = new HashMap<>();


    public void validateStringLenght(Object o) throws Exception{
        Method[] declaredMethods = o.getClass().getDeclaredMethods();
        for (Method m: declaredMethods){
            if (m.isAnnotationPresent(Cache.class)){
                Cache an = m.getAnnotation(Cache.class);

                // TODO: 1. Через an получить параметры метода
                // TODO: 2. Получить имя метода и список аргументов
                // TODO: 3. Логика кэширования. (Должна совпадать с CacheHandler)

            }

        }

    }


    // TODO: Нужен паттерн декоратор. При запуске логировать с параметрами антоации основной метод



    private List<Object> toKey(Method method, Object[] args) { // TODO: Ключ для сохранения !!!
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }

}



// 1. Реализовать без аннотаций




























