package hashmap;

import java.util.*;

public interface SimpleMap <K, V> {
    void put(K key, V value); // сохранить по ключу

    V get(K key); // получить по ключу

    V remove(K key); // удалить по ключу

    boolean contains(K key); // проверить есть ли такой ключ

    int size(); // получить размер

    Set<K> keySet(); // получить все ключи

    Collection<V> values(); // получить все значения
}


