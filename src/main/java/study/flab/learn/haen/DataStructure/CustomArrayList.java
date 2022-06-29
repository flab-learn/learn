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
        if(capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }

        this.capacity = capacity;
        array = new Object[capacity];
        lastIndex= 0;
    }

    // O(1)
    public boolean add(E data) {
        if(capacity <= lastIndex) {
            reAllocCapacity();
        }
        array[lastIndex++] = data;
        return true;
    }

    // O(n);
    public void add(int index, E data) {
        if(capacity <= lastIndex) {
            reAllocCapacity();
        }
        for(int i = lastIndex; i > index; i--) {
            array[i] = array[i - 1];
        }
        lastIndex++;
        array[index] = data;
    }

    // O(n^2)
    public boolean addAll(Collection<E> c) {
        for(E e : c) {
            add(e);
        }
        return true;
    }

    // O(1)
    // 기존 배열에 add배열을 더한다.
    public boolean addAll(int index, Collection<E> c) {
        Object[] addArray = c.toArray();
        int size = c.size();
        if(capacity <= lastIndex + size) {
            reAllocCapacity();
        }

        System.arraycopy(addArray, 0, array, lastIndex, size);
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
    public boolean containsAll(Collection<E> c) {
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

    // O(n)
    // 기존 배열을 o를 제외한 배열로 이어붙인다.
    public boolean remove(Object o) {
        for(int i = 0; i < lastIndex; i++) {
            if(array[i].equals(o)) {
                int size = lastIndex;
                System.arraycopy(array, i + 1, array, i, size - i);
                lastIndex = size - 1;
                array[lastIndex] = null;
                return true;
            }
        }
        return false;
    }

    // O(n^2)
    public boolean removeAll(Collection<E> c) {
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
    private void reAllocCapacity() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        for(int i = 0; i < lastIndex; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}