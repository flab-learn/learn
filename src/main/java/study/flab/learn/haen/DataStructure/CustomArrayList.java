package study.flab.learn.haen.DataStructure;

import java.util.ArrayList;
import java.util.Collection;

public class CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int capacity;
    private int lastIndex;

    public CustomArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        array = new Object[capacity];
        lastIndex= 0;
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
        lastIndex= 0;
    }

    // O(1)
    public boolean add(E data) {
        if(capacity <= lastIndex) {
            ReAllocCapacity();
        }
        array[lastIndex++] = data;
        return true;
    }

    // O(n);
    public void add(int index, E data) {
        if(capacity <= lastIndex) {
            ReAllocCapacity();
        }
        for(int i = lastIndex; i > index; i--) {
            array[i] = array[i - 1];
        }
        lastIndex++;
        array[index] = data;
    }

    // O(n^2)
    public boolean addAll(Collection c) {
        for(Object o : c) {
            add((E)o);
        }
        return true;
    }

    // O(n^2)
    public boolean addAll(int index, Collection c) {
        int size = c.size();
        for(int i = lastIndex - 1; i >= index; i--) {
            add(i + size, (E)array[i]);
        }
        for(Object o : c) {
            array[index++] = o;
        }
        return true;
    }

    // O(n)
    public boolean contains(Object o) {
        for(int i = 0; i < lastIndex; i++) {
            if(array[i].equals(o))
                return true;
        }
        return false;
    }

    // O(n^2)
    public boolean containsAll(Collection c) {
        for(Object o : c) {
            if(!contains(o))
                return false;
        }
        return true;
    }

    // O(n)
    public int indexOf(Object o) {
        for(int i = 0; i < lastIndex; i++) {
            if(array[i].equals(o))
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return lastIndex <= 0;
    }

    // O(1)
    public E get(int index) {
        return (E)array[index];
    }

    // O(n)
    public E remove(int index) {
        E data = (E)array[index];
        for(int i = index; i < lastIndex - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--lastIndex] = null;
        return data;
    }

    // O(n^2)
    public boolean remove(Object o) {
        for(int i = 0; i < lastIndex; i++) {
            if(array[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // O(n^2)
    public boolean removeAll(Collection c) {
        for(Object o : c) {
            if(!remove(o))
                return false;
        }
        return true;
    }

    // O(1)
    public E set(int index, E data) {
        array[index] = data;
        return (E)array[index];
    }

    // O(n^2)
    public ArrayList<E> subList(int from, int to) {
        ArrayList<E> arrayList = new ArrayList<>();
        for(int i = from; i < to; i++) {
            E data = get(i);
            arrayList.add(data);
        }
        return arrayList;
    }

    // 추가 구현 - 배열 크기 재할당
    private void ReAllocCapacity() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        for(int i = 0; i < lastIndex; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
