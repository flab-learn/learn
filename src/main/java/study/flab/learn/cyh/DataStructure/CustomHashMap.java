package study.flab.learn.cyh.DataStructure;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

public class CustomHashMap<K, V> implements Serializable {

    private static final int INITIAL_CAPACITY = 1 << 4; // 16
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public int capacity;
    public float loadFactor; //해시테이블의 버킷이 얼마나 가득 찼는지 보여주는 수치
    public CustomEntryToCHM<K, V>[] table;
    public int tableSize;

    public CustomHashMap() {
        this(INITIAL_CAPACITY);
    }
    public CustomHashMap(int capacity) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.capacity = capacity;
        this.table = new CustomEntryToCHM[this.capacity];
    }
    public CustomHashMap(float loadFactor) {
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.table = new CustomEntryToCHM[this.capacity];
    }

    //O(1)
    public V get(K key) {
        int tableIndex = key.hashCode() % this.capacity;
        CustomEntryToCHM<K,V> currEntry = this.table[tableIndex];
        return currEntry == null ? null : currEntry.value;
    }

    //O(n)
    private CustomEntryToCHM[] incCapacitySize() {
        this.capacity = this.capacity * 2;
        CustomEntryToCHM[] newTable = new CustomEntryToCHM[this.capacity];
        for (int i = 0; i < tableSize; i++) {
            newTable[i] = this.table[i];
        }
        return newTable;
    }

    //amortised O(1)
    public void put(K key, V value) {
        if (loadFactor * this.capacity <= this.tableSize) {
            this.table = incCapacitySize();
        }

        CustomEntryToCHM<K, V> newEntry = new CustomEntryToCHM<>(key, value);

        int tableIndex = key.hashCode() % this.capacity;
        this.table[tableIndex] = newEntry;
        tableSize++;
    }

    //O(1)
    public int getEmptyEntry() {
        int iRtn = this.capacity - this.tableSize;
        return iRtn;
    }
    //O(1)
    public int size() {
        return tableSize;
    }
    //O(1)
    public boolean isEmpty() {
        return tableSize == 0;
    }

    @Override
    public String toString() {
        return "CustomHashMap{"
                + Arrays.toString(table) +
                '}';
    }
}

class CustomEntryToCHM<K, V> implements Map.Entry<K, V> {

    final K key;
    V value;
//    CustomEntryToCHUL<K,V> nextEntry;
//    CustomEntryToCHUL<K,V> prevEntry;

    CustomEntryToCHM() {
        this.key = null;
        this.value = null;
    }

    CustomEntryToCHM(K key, V value) {
        this.key = key;
        this.value = value;
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