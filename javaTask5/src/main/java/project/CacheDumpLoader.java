package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class CacheDumpLoader {
    // Словарь для поиска результата в памяти / на диске
    HashMap<List<Object>, HashMap<String, Object>> resultPath = new HashMap<>();

    // Массив результатов. (Если все храниться в памяти) ????
    List<Object> inMemoryResults = new ArrayList<>();

    // метод проверки наличия ключа
    boolean containsKey(List<Object> key){
        return resultPath.containsKey(key);
    }

    // метод для сохранения в словарь результатов расчета
    void dump(List<Object> key, HashMap<String, Object> cacheParams, Object result){
        // "rootFolder"
        // "cacheType"
        // "listSize"
        // "fileNamePrefix"
        // "zip"
        // "identityBy"
        // TODO: Расположить в нужном порядке










    }
    // метод для извлечения рассчетов
    Object load(List<Object> key){












        return null;
    }
}







// TODO: Какой патерн лучше использовать ????
