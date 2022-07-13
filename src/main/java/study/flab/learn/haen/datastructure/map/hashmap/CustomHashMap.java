package study.flab.learn.haen.datastructure.map.hashmap;

import study.flab.learn.haen.datastructure.map.Node;

import java.util.*;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOADFACTOR = 0.75f;
    LinkedList<Node>[] bucket;
    float loadFactor;
    int capacity;
    int bucketSize;
    int entrySize;

    public CustomHashMap() {
        this.loadFactor = DEFAULT_LOADFACTOR;
        this.capacity = DEFAULT_CAPACITY;
        bucket = new LinkedList[capacity];
        bucketSize = 0;
        entrySize = 0;
    }

    public CustomHashMap(float loadFactor) {
        if(loadFactor <= 0) {
            loadFactor = DEFAULT_LOADFACTOR;
        }
        this.loadFactor = loadFactor;
        this.capacity = DEFAULT_CAPACITY;
        bucket = new LinkedList[capacity];
        bucketSize = 0;
        entrySize = 0;
    }

    // Best: O(1), Worst: O(N)
    V get(K key) {
        int hashKey = hashFunction(key);
        if(bucket[hashKey] == null) {
            return null;
        }

        Node fNode = findNode(bucket[hashKey], key);
        if(fNode != null) {
            return (V) fNode.getValue();
        }
        return null;
    }

    // Best: O(1), Worst: O(N), 배열 재할당시: O(N^2)
    void put(K key, V value) {
        if(loadFactor * capacity <= entrySize) {
            reAllocCapacity();
        }

        Node node = new Node(key, value);
        int hashKey = hashFunction(key);

        if(bucket[hashKey] == null) {
            bucket[hashKey] = new LinkedList<>();
            bucketSize++;
        }

        Node fNode = findNode(bucket[hashKey], key);
        if(fNode != null) {
            fNode.setValue(value);
        } else {
            bucket[hashKey].add(node);
            entrySize++;
        }
    }

    // Best: O(1), Worst: O(N)
    V remove(K key) {
        int hashKey = hashFunction(key);
        if(bucket[hashKey] == null) {
            return null;
        }

        Node fNode = findNode(bucket[hashKey], key);
        if(fNode != null) {
            bucket[hashKey].remove(fNode);
            if(bucket[hashKey].isEmpty()) {
                bucket[hashKey] = null;
                bucketSize--;
            }

            entrySize--;
            return (V) fNode.getValue();
        }
        return null;
    }

    int size() {
        return entrySize;
    }

    boolean isEmpty() {
        return bucketSize <= 0;
    }

    Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Node> nodes : bucket) {
            if (nodes != null) {
                for (Node node : nodes) {
                    set.add(node);
                }
            }
        }
        return set;
    }

    // 추가 구현 - hashkey
    int hashFunction(K key) {
        return key.hashCode() % capacity;
    }

    // 추가 구현 - LinkedList에서 node 찾기
    Node findNode(LinkedList<Node> list, K key) {
        if(list == null) {
            return null;
        }

        for(Node eNode : list) {
            if(eNode.getKey().equals(key)) {
                return eNode;
            }
        }
        return null;
    }

    // 추가 구현 - 배열 크기 재할당
    // capacity가 늘어나면서 다시 hashkey를 구하고 bucket에 넣기
    private void reAllocCapacity() {
        capacity *= 2;
        LinkedList<Node>[] newBucket = new LinkedList[capacity];
        for(int i = 0; i < bucket.length; i++) {
            if(bucket[i] == null) {
                continue;
            }
            for(Node node : bucket[i]) {
                int hashkey = hashFunction((K) node.getKey());
                if(newBucket[hashkey] == null) {
                    newBucket[hashkey] = new LinkedList<>();
                }
                newBucket[hashkey].add(node);
            }
        }
        bucket = newBucket;
    }
}