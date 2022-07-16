package study.flab.learn.haen.datastructure.collection.stack;

import java.util.EmptyStackException;

public class CustomArrayStack<E> {
    static final int DEFAULT_CAPACITY = 10;
    Object[] array;
    int top;
    int capacity;
    int size;

    public CustomArrayStack() {
        this.capacity = DEFAULT_CAPACITY;
        array = new Object[capacity];
        top = -1;
        size = 0;
    }

    public CustomArrayStack(int capacity) {
        if(capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }

        this.capacity = capacity;
        array = new Object[capacity];
        top = -1;
        size = 0;
    }

    boolean isEmpty() {
        return size <= 0;
    }

    // O(1) / worst: O(n)
    void push(E e) {
        if(capacity <= size) {
            reAllocCapacity();
        }

        array[++top] = e;
        size++;
    }

    // O(1)
    E pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        Object data = array[top];
        array[top--] = null;
        size--;

        return (E) data;
    }

    // O(1)
    E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        return (E) array[top];
    }

    // 추가 구현 - 배열 크기 재할당
    private void reAllocCapacity() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
