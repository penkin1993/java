package hashmap;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashMap<K, V> implements SimpleMap<K, V>{
    private ArrayList<Optional<Node<K, V>>> hashList = new ArrayList<>(); // список с элементами
    private int capacity = 0; // вместимость массива
    private int size = 0;

    // TODO реализовать увеличение
    //  и уменьшение (автоматически ???) словаря. Прослдеить за ключами !!!
    // по дефолту заполнять null новый массив



    @Override
    public void put(K key, V value){
        if (contains(key)){
            Node<K, V> node = getNode(key);
            node.setValue(value);
        }
        else{
            // TODO: Проверка на расширение !!!
            // Расширили


            int index = getIndex(key);  // TODO: Извлечь последний элемент данного ключа !!!
            Node node = hashList.get(index).orElse(null);
            if (node == null){
                hashList.set(index, Optional.of(new Node<>(key, value)));
            }
            else {
                while (true) {
                    if (node.getNextNode() == null) {
                        node.setNextNode(new Node<>(key, value));
                        break;
                    }
                    else {
                        node = node.getNextNode();
                    }
                }
            }
            this.size += 1;
        }
    }

    private int getIndex(K key){
        int hash = key.hashCode();
        return Math.abs(hash) % capacity;
    }

    private Node getNode(K key){
        int index = getIndex(key);
        Node node = hashList.get(index).orElse(null);
        if (node == null){
            throw new NoSuchElementException("The remove element does not exist");
        }
        while (true){
            if (node.getKey().equals(key)){
                return node;
            }
            else if (node.getNextNode() == null){
                throw new NoSuchElementException("The key does not exist");
            }
            else {
                node = node.getNextNode();
            }
        }
    }

    @Override
    public V get(K key){
        try {
            Node<K, V> node = getNode(key);
            return node.getValue();
        } catch (NoSuchElementException e){
            throw new NoSuchElementException("The key does not exist");
        }
    }

    @Override
    public boolean contains(K key){
        try {
            getNode(key);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }


    @Override
    public V remove(K key){
        int index = getIndex(key);
        Node<K, V> node = hashList.get(index).orElse(null);
        if (node == null){
            throw new NoSuchElementException("The remove element does not exist");
        }
        Node<K, V> nextNode;
        if (node.getKey().equals(key)){
            nextNode = node.getNextNode();
            hashList.set(index, Optional.of(node.getNextNode()));

            // TODO: Сжимать, если указывает на null ???
            // Проверка на сжатие

            this.size -= 1;
            return nextNode.getValue();
        }
        else while (true){
            nextNode = node.getNextNode();
            if (nextNode == null){
                throw new NoSuchElementException("The remove element does not exist");
            }
            else if (nextNode.getKey().equals(key)){
                node.setNextNode(nextNode.getNextNode());

                // TODO: Сжимать, если указывает на null ???
                // Проверка на сжатие

                this.size -= 1;
                return nextNode.getValue();
            }
            else{
                node = node.getNextNode();
            }
        }
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }


    @Override
    public int size(){
        return this.size;
    }


    private static class Node<K, V>{
        private final K key;
        private V value;
        private Node<K, V> next = null;

        Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return this.key;
        }

        V getValue() {
            return this.value;
        }

        void setValue(V value){
            this.value = value;
        }

        void setNextNode(Node<K, V> node){
            this.next = node;
        }

        Node getNextNode(){
            return this.next;
        }

    }

}
