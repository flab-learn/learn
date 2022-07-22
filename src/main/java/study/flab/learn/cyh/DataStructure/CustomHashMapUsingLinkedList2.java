package study.flab.learn.cyh.DataStructure;

import java.io.Serializable;
import java.util.*;

public class CustomHashMapUsingLinkedList2<K, V> implements Serializable {

    private static final int INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public int capacity;
    public float loadFactor;
    public LinkedList<CustomEntryToCHMUL2<K, V>>[] table;
    public int tableSize;

    public CustomHashMapUsingLinkedList2() {
        this(INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    public CustomHashMapUsingLinkedList2(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }
    public CustomHashMapUsingLinkedList2(float loadFactor) {
        this(INITIAL_CAPACITY, loadFactor);
    }
    public CustomHashMapUsingLinkedList2(int capacity, float loadFactor) {
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = capacity;
        this.table = new LinkedList[this.capacity];
    }

    //O(n)
    public V get(K key) {
        int tableIndex = key.hashCode() % this.capacity;

        if (this.table[tableIndex] == null) {
            return null;
        }

        CustomEntryToCHMUL2<K,V> entry = this.table[tableIndex].getFirst();
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.nextEntry;
        }
        return null;
    }

    //O(n)
    private LinkedList<CustomEntryToCHMUL2<K, V>>[] incCapacitySize() {
        int prevCapacity = this.capacity;
        this.capacity *= 2;
        LinkedList<CustomEntryToCHMUL2<K, V>>[] newTable = new LinkedList[this.capacity];
        for (int i = 0; i < prevCapacity; i++) {
            if (this.table[i] != null) {
                newTable[i] = this.table[i];
            }
        }
        return newTable;
    }

    //O(n)
    public void put(K key, V value) {
        if (loadFactor * this.capacity <= this.tableSize) {
            this.table = incCapacitySize();
        }

        CustomEntryToCHMUL2<K, V> newEntry = new CustomEntryToCHMUL2<>(key, value);

        int tableIndex = key.hashCode() % this.capacity;
        LinkedList<CustomEntryToCHMUL2<K, V>> bucket = this.table[tableIndex];
        if (bucket == null) {
            this.table[tableIndex] = new LinkedList<>();
            this.table[tableIndex].add(newEntry);
            tableSize++;
            return;
        }

        CustomEntryToCHMUL2<K, V> entry = this.table[tableIndex].getFirst();
        while (entry.nextEntry != null) {
            //중복KEY 처리
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.nextEntry;
        }

        //중복KEY 처리
        if (entry.key.equals(key)) {
            entry.value = value;
            return;
        }
        entry.nextEntry = newEntry;
        tableSize++;
    }

    //O(1)
    public int getEmptyEntry() {
        return this.capacity - this.tableSize;
    }
    //O(1)
    public int size() {
        return tableSize;
    }
    //O(1)
    public boolean isEmpty() {
        return tableSize == 0;
    }
    //O(n)
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (int i = 0; i < this.capacity; i++) {
            if (this.table[i] != null) {
                for (int j = 0; j < this.table[i].size(); j++) {
                    set.add(this.table[i].get(j));
                }
            }
        }
        return set;
    }

    @Override
    public String toString() {
        return "CustomHashMapUsingLinkedList2{" +
                "\ncapacity=" + capacity +
                "\n, loadFactor=" + loadFactor +
                "\n, table=" + Arrays.toString(table) +
                "\n, tableSize=" + tableSize +
                "\n}";
    }
}

class CustomEntryToCHMUL2<K, V> implements Map.Entry<K, V> {

    final K key;
    V value;
    CustomEntryToCHMUL2<K,V> nextEntry;
    CustomEntryToCHMUL2<K,V> prevEntry;

    public CustomEntryToCHMUL2() {
        this.key = null;
        this.value = null;
        this.nextEntry = null;
        this.prevEntry = null;
    }

    CustomEntryToCHMUL2(K key, V value) {
        this.key = key;
        this.value = value;
        this.nextEntry = null;
        this.prevEntry = null;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        V prevValue = this.value;
        this.value = value;
        return prevValue;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}