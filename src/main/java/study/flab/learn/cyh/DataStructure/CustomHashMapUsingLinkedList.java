package study.flab.learn.cyh.DataStructure;

import java.io.Serializable;
import java.util.*;

public class CustomHashMapUsingLinkedList<K, V> implements Serializable {

    private static final int INITIAL_CAPACITY = 1 << 4; // 16
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    public int capacity;
    public float loadFactor;
//    public LinkedList<CustomEntryToCHMUL<K, V>>[] table;
    public CustomEntryToCHMUL<K, V>[] table;
    public int tableSize;

    public CustomHashMapUsingLinkedList() {
        this(INITIAL_CAPACITY);
    }
    public CustomHashMapUsingLinkedList(int capacity) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.capacity = capacity;
        this.table = new CustomEntryToCHMUL[this.capacity];
    }
    public CustomHashMapUsingLinkedList(float loadFactor) {
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.table = new CustomEntryToCHMUL[this.capacity];
    }

    //O(1)
    public V get(K key) {
        int tableIndex = key.hashCode() % this.capacity;
        CustomEntryToCHMUL<K,V> currEntry = this.table[tableIndex];

        while (currEntry != null) {
            if (currEntry.key.equals(key)) {
                return currEntry == null ? null : currEntry.value;
            }
            currEntry = currEntry.nextEntry;
        }
        return null;
    }

    //O(n)
    private CustomEntryToCHMUL<K, V>[] incCapacitySize() {
        int prevCapacity = this.capacity;
        this.capacity *= 2;
        CustomEntryToCHMUL<K, V>[] newTable = new CustomEntryToCHMUL[this.capacity];
        for (int i = 0; i < prevCapacity; i++) {
            if (this.table[i] != null) {
                newTable[i] = this.table[i];
            }
        }
        return newTable;
    }

    //amortised O(1)
    public void put(K key, V value) {
        if (loadFactor * this.capacity <= this.tableSize) {
            this.table = incCapacitySize();
        }

        CustomEntryToCHMUL<K, V> newEntry = new CustomEntryToCHMUL<>(key, value, null);

        int tableIndex = key.hashCode() % this.capacity;
        System.out.println("### tableIndex=" + tableIndex);

        CustomEntryToCHMUL<K, V> currEntry = this.table[tableIndex];


//        if (this.table[tableIndex] == null) {
//            System.out.println("### this.table[tableIndex]");
//            this.table[tableIndex] = new LinkedList<>();
//            this.table[tableIndex].add(newEntry);
//            tableSize++;
//        }

        if (currEntry == null) {
            System.out.println("### newEntry == null");
            this.table[tableIndex] = newEntry;
            tableSize++;
        } else {
            System.out.println("### newEntry != null");
            while (currEntry.nextEntry != null) {
                System.out.println("### newEntry.nextEntry != null");
                if (currEntry.key.equals(key)) {
                    currEntry.value = value;   //덮어쓴다
                    return;
                }
                currEntry = currEntry.nextEntry;
            }

            if (currEntry.key.equals(key)) {
                System.out.println("### newEntry.key.equals(key)");
                currEntry.value = value;   //덮어쓴다
            } else {
                System.out.println("### newEntry.nextEntry = newEntry");
                //신규추가
                currEntry.nextEntry = newEntry;
                tableSize++;
            }
        }
        System.out.println("======= put 2 start ================");
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + this.table[i]);
        }
        System.out.println("======= put 2 end ================");
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
                set.add(this.table[i]);
            }
        }
        return set;
    }

    @Override
    public String toString() {
        return "CustomHashMapUsingLinkedList{"
                + Arrays.toString(table) +
                '}';
    }

}

class CustomEntryToCHMUL<K, V> implements Map.Entry<K, V> {

    final K key;
    V value;
    CustomEntryToCHMUL<K,V> nextEntry;

//    CustomEntryToCHMUL() {
//        this.key = null;
//        this.value = null;
//        this.nextEntry = null;
//    }

    CustomEntryToCHMUL(K key, V value, CustomEntryToCHMUL<K,V> nextEntry) {
        this.key = key;
        this.value = value;
        this.nextEntry = nextEntry;
    }

//    CustomEntryToCHMUL(K key, V value) {
//        this.key = key;
//        this.value = value;
//    }

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