package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CacheDumpLoader {
    // Словарь для поиска результата в памяти / на диске
    HashMap<List<Object>, HashMap<String, Object>> resultPath = new HashMap<>();

    // Массив результатов. (Если все храниться в памяти) ????
    List<Object> inMemoryResults = new ArrayList<>();


    // метод для сохранения в словарь результатов расчета
    public void dump(List<Object> key, HashMap<String, Object> cacheParams){





        
    }

    // метод проверки наличия ключа
    public boolean containsKey(List<Object> key){







        return true;
    }





    // метод для извлечения рассчетов
    public Object load(List<Object> key){








        return null;
    }



}







// TODO: Какой патерн лучше использовать ????
