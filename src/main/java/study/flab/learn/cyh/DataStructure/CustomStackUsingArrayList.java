package study.flab.learn.cyh.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * CustomStackUsingArrayList : CSUA
 */
public class CustomStackUsingArrayList<E> {

    private CustomArrayListToCSUA<E> arrayStack = new CustomArrayListToCSUA<>();
    private ArrayList<String> arrayList = new ArrayList<String>();

    public boolean isEmpty() {
        return arrayStack.isEmpty();
    }

    public void push(E e) {
        arrayStack.add(e);
    }

    public E pop() {
        return null;
    }

    public E peek() {
        return null;
    }

    @Override
    public String toString() {
        return "arrayStack=" + arrayStack;
    }
}




class CustomArrayListToCSUA<E> {
    public static final int DEFAULT_CAPACITY = 10;
    public Object[] elements;
    public int lastIndex;

    public CustomArrayListToCSUA() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayListToCSUA(int capacity) {
        //음수인 경우 에러처리
        if(capacity < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (capacity > 0) {
            this.elements = new Object[capacity];
            return;
        }
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    //O(1)
    public int size() {
        return lastIndex;
    }

    //O(1)
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    //O(n)
    public int indexOf(Object element) {
        for (int i = 0; i < lastIndex; i++) {
            if (Objects.equals(this.elements[i], element)) {
                return i;
            }
        }
        return -1;
    }

    //O(n)
    private E[] incDoublyCapacity(int capacity) {
        Object[] elementsTemp = new Object[capacity * 2];
        for (int i = 0; i < lastIndex; i++) {
            elementsTemp[i] = elements[i];
        }
        return (E[]) elementsTemp;
    }

    //amortised O(1)
    public boolean add(E element) {
        if (this.elements.length <= lastIndex) {
            this.elements = incDoublyCapacity(this.elements.length);
        }
        this.elements[lastIndex++] = element;
        return true;
    }

    //O(n)
    public void add(int index, E element) {
        //음수인 경우 에러처리
        if(index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (this.elements.length <= lastIndex) {
            this.elements = incDoublyCapacity(this.elements.length);
        }

        for (int i = lastIndex; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[index] = element;
        lastIndex++;
    }


    @Override
    public String toString() {
        return "size=" + lastIndex
                + ", elements=" + Arrays.toString(this.elements);
    }
}