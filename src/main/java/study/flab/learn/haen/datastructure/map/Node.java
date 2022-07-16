package study.flab.learn.haen.datastructure.map;

import java.util.Map;

public class Node<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public Node(K key) {
        this.key = key;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V data = this.value;
        this.value = value;
        return data;
    }
}
