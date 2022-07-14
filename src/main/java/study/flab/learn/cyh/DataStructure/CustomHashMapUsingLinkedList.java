package study.flab.learn.cyh.DataStructure;

import java.io.Serializable;
import java.util.*;

public class CustomHashMapUsingLinkedList<K, V> implements Serializable {

    private static final int INITIAL_CAPACITY = 1 << 4; // 16
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public int capacity;
    public float loadFactor;
    public LinkedList<CustomEntryToCHMUL<K, V>>[] table;
    public int tableSize;

    public CustomHashMapUsingLinkedList() {
        this(INITIAL_CAPACITY);
    }
    public CustomHashMapUsingLinkedList(int capacity) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.capacity = capacity;
        this.table = new LinkedList[this.capacity];
    }
    public CustomHashMapUsingLinkedList(float loadFactor) {
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.table = new LinkedList[this.capacity];
    }

    //O(n)
    private CustomEntryToCHMUL<K,V> getEntry(LinkedList<CustomEntryToCHMUL<K,V>> list) {
        if (list == null) {
            return null;
        }

        CustomEntryToCHMUL<K,V> entryTemp = null;
        for (CustomEntryToCHMUL e : list) {
            if (e.nextEntry != null) {
                entryTemp = e.nextEntry;
            }
        }

        if (entryTemp != null) {
            K key = entryTemp.getKey();
            V value = entryTemp.getValue();
        }
        return entryTemp == null ? list.getFirst() : entryTemp;
    }

    //O(1)~O(n)
    public V get(K key) {
        int tableIndex = key.hashCode() % this.capacity;
        CustomEntryToCHMUL<K,V> currEntry = getEntry(this.table[tableIndex]);
        while (currEntry != null) {
            if (currEntry.key.equals(key)) {
                return currEntry.value;
            }
            currEntry = currEntry.nextEntry;
        }
        return null;
    }

    //O(1)~O(n)
    public void put(K key, V value) {
        CustomEntryToCHMUL<K, V> newEntry = new CustomEntryToCHMUL<>(key, value, null);

        int tableIndex = key.hashCode() % this.capacity;
        CustomEntryToCHMUL<K, V> currEntry = getEntry(this.table[tableIndex]);

        if (currEntry == null) {
            this.table[tableIndex] = new LinkedList<>();
            this.table[tableIndex].add(newEntry);
            tableSize++;
        } else {
            while (currEntry.nextEntry != null) {
                if (currEntry.key.equals(key)) {
                    currEntry.value = value;   //덮어쓴다
                    return;
                }
                currEntry = currEntry.nextEntry;
            }

            if (currEntry.key.equals(key)) {
                currEntry.value = value;   //덮어쓴다
            } else {
                //신규추가
                currEntry.nextEntry = newEntry;
                tableSize++;
            }
        }
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
                set.add(getEntry(this.table[i]));
            }
        }
        return set;
    }

    @Override
    public String toString() {
        String sRtn = "CustomHashMapUsingLinkedList{";
        for (int i = 0; i < this.table.length; i++) {
            sRtn += getEntry(this.table[i]);
            if (i != this.table.length - 1) {
                sRtn += " ";
            }
        }
        sRtn += '}';
        return sRtn;
    }

}

class CustomEntryToCHMUL<K, V> implements Map.Entry<K, V> {

    final K key;
    V value;
    CustomEntryToCHMUL<K,V> nextEntry;

    CustomEntryToCHMUL(K key, V value, CustomEntryToCHMUL<K,V> nextEntry) {
        this.key = key;
        this.value = value;
        this.nextEntry = nextEntry;
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