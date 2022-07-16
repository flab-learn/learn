package study.flab.learn.cyh.DataStructure;

import java.util.Arrays;
import java.util.Objects;


class CustomArrayListToCQUA<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    public int size;
    public int head;
    public int tail;
    public int capacity;

    protected CustomArrayListToCQUA() {
        this.capacity = DEFAULT_CAPACITY;
        this.elements = new Object[this.capacity];
        this.head = -1;
        this.tail = -1;
    }

    protected CustomArrayListToCQUA(int capacity) {
        if(capacity < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.capacity = DEFAULT_CAPACITY;
        if (capacity > 0) {
            this.capacity = capacity;
        }
        this.elements = new Object[this.capacity];
        this.head = -1;
        this.tail = -1;
    }

    //O(1)
    protected int size() {
        return this.size;
    }

    //O(n)
    private E[] incDoublyCapacity() {
        this.capacity *= 2;
        Object[] elementsTemp = new Object[this.capacity];
        for (int i = 0; i < this.size; i++) {
            elementsTemp[i] = elements[i];
        }
        return (E[]) elementsTemp;
    }

    //amortised O(1)
    protected boolean add(E element) {
        if (this.capacity <= this.size) {
            this.elements = incDoublyCapacity();
        }
        if (this.head == -1) {
            this.head = 0;
        }

        this.tail = (this.tail + 1) % this.capacity;
        this.elements[tail] = element;
        this.size++;
        return true;
    }

    //O(1)
    protected E removeFirst() {
        if (this.size == 0) {
            return null;
        }

        int prevHead = this.head;
        Object strRtn = elements[prevHead];
        if (this.size == 1) {
            this.head = -1;
            this.tail = -1;
            this.size--;
            return (E) strRtn;
        }
        this.head = (this.head + 1) % this.capacity;

        elements[prevHead] = null;
        this.size--;
        return (E) strRtn;
    }

    //O(1)
    protected E getHead() {
        return (E) this.elements[head];
    }

    @Override
    public String toString() {
        return Arrays.toString(this.elements);
    }
}