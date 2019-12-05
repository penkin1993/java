package hashmap;

import java.util.*;


public class HashMap<K, V> implements SimpleMap<K, V> {
    private Node<K, V>[] newHashList;
    private int capacity = 16; // вместимость массива
    private int size = 0;
    private Node<K, V>[] hashList = NewHashListInit(16);

    private Node<K, V>[] NewHashListInit(int capacity) {
        newHashList = (Node<K, V>[]) new Node[capacity];

        for (int i = 0; i < capacity; i++) {
            newHashList[i] = null;
        }

        this.capacity = capacity;
        return newHashList;
    }

    private void expandList() {
        Node<K, V> node;

        if ((float) this.size >= 0.75 * capacity) {
            this.size = 0;
            Node<K, V>[] oldHashList = (Node<K, V>[]) new Node[capacity];
            this.hashList = NewHashListInit(2 * capacity); // capacity изменилось
            for (int i = 0; i < capacity / 2; i++) {
                NodeIterator nodeIterator = new NodeIterator(oldHashList, i);
                while (nodeIterator.hasNext()) {
                    node = nodeIterator.next();
                    put(node.getKey(), node.getValue());
                }
            }
        }
    }

    @Override
    public void put(K key, V value) {
        if (contains(key)) {
            Node<K, V> node = getNode(key);
            node.setValue(value);
        } else {
            expandList();
            int index = getIndex(key);
            try {
                Node node = hashList[index]; //
                while (true) {
                    if (node.getNextNode() == null) {
                        node.setNextNode(new Node<>(key, value));
                        break;
                    } else {
                        node = node.getNextNode();
                    }
                }
            } catch (NullPointerException e) {
                hashList[index] = new Node<>(key, value);
            }
            this.size += 1;
        }
    }

    private int getIndex(K key) {
        int hash = key.hashCode();
        return Math.abs(hash) % this.capacity;
    }

    private Node getNode(K key) {
        int index = getIndex(key);
        try {
            Node node = hashList[index];
            while (true) {
                if (node.getKey().equals(key)) {
                    return node;
                } else if (node.getNextNode() == null) {
                    throw new NoSuchElementException("The key does not exist");
                } else {
                    node = node.getNextNode();
                }
            }
        } catch (NullPointerException e) {
            throw new NoSuchElementException("The remove element does not exist");
        }
    }

    @Override
    public V get(K key) {
        try {
            Node<K, V> node = getNode(key);
            return node.getValue();
        } catch (NoSuchElementException e) {
            //return null;
            throw new NoSuchElementException("The key does not exist");
        }
    }

    @Override
    public boolean contains(K key) {
        try {
            getNode(key);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = hashList[index];
        if (node == null) {
            //throw new NoSuchElementException("The remove element does not exist");
            return null;
        }
        Node<K, V> nextNode;
        if (node.getKey().equals(key)) {
            hashList[index] = null;
            this.size -= 1;
            return node.getValue();
        } else while (true) {
            nextNode = node.getNextNode();
            if (nextNode == null) {
                //throw new NoSuchElementException("The remove element does not exist");
                return null;
            } else if (nextNode.getKey().equals(key)) {
                node.setNextNode(nextNode.getNextNode());
                this.size -= 1;
                return nextNode.getValue();
            } else {
                node = node.getNextNode();
            }
        }
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            NodeIterator nodeIterator = new NodeIterator(this.hashList, i);
            while (nodeIterator.hasNext()) {
                set.add(nodeIterator.next().getKey());
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> collection = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            NodeIterator nodeIterator = new NodeIterator(this.hashList, i);
            while (nodeIterator.hasNext()) {
                collection.add(nodeIterator.next().getValue());
            }
        }
        return collection;
    }

    @Override
    public int size() {
        return this.size;
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next = null;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return this.key;
        }

        V getValue() {
            return this.value;
        }

        void setValue(V value) {
            this.value = value;
        }

        void setNextNode(Node<K, V> node) {
            this.next = node;
        }

        Node getNextNode() {
            return this.next;
        }

    }

    class NodeIterator implements Iterator {
        Node<K, V> currentNode;
        Node<K, V>[] hashList;

        // initialize pointer to head of the list for iteration
        NodeIterator(Node<K, V>[] hashList, int index) {
            this.hashList = hashList;
            try {
                this.currentNode = hashList[index];
            } catch (NullPointerException e) {
                this.currentNode = null;
            }
        }

        // returns false if next element does not exist
        public boolean hasNext() {
            return currentNode != null;
        }

        // return current data and update pointer
        public Node<K, V> next() {
            Node<K, V> node = currentNode;
            currentNode = currentNode.getNextNode();
            return node;
        }
    }
}
