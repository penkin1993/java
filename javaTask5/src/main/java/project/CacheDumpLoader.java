package project;

import project.cache_annotations.CacheType;
import project.save_load_handlers.ListSizeHandler;
import project.save_load_handlers.ZipDumpHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

class CacheDumpLoader {

    // Массив результатов. (Если все храниться в памяти)
    private HashMap<List<Object>, Object> inMemoryResults = new HashMap<>();

    // Словарь для поиска результата  на диске
    private HashMap<List<Object>, ZipDumpHandler> onDiskResults = new HashMap<>(); // может можно упростить ???

    // метод проверки наличия ключа
    boolean containsKey(List<Object> key) {
        return onDiskResults.containsKey(key) | inMemoryResults.containsKey(key);
    }

    // метод для сохранения в словарь результатов расчета
    void dump(List<Object> key, HashMap<String, Object> cacheParams, Object result) {
        // ограничиваем длину, если объект кастуется в массив
        ListSizeHandler listSizeHandler = new ListSizeHandler((int) cacheParams.get("listSize"));
        Object listResult = listSizeHandler.cut(result);

        // сохранение на диск или в оперативную память
        if (cacheParams.get("cacheType") == CacheType.IN_MEMORY) {
            inMemoryResults.put(key, listResult);

        } else { // сохранение на диск
            String cachePrefix  = (String) cacheParams.get("fileNamePrefix");
            if (cachePrefix.equals("__None__")) {
                cachePrefix = ((Method) key.get(0)).getName();
            }

            ZipDumpHandler zipDumpHandler = new ZipDumpHandler((boolean) cacheParams.get("zip"), cachePrefix,
                    (String) cacheParams.get("rootFolder"));
            zipDumpHandler.dump(result);
            onDiskResults.put(key, zipDumpHandler);
        }
    }
    // метод для извлечения рассчетов
    Object load(List<Object> key) {
        if (inMemoryResults.containsKey(key)) {
            return inMemoryResults.get(key);

        } else {
            ZipDumpHandler zipDumpHandler = onDiskResults.get(key);
            return  zipDumpHandler.load();
        }
    }
}



