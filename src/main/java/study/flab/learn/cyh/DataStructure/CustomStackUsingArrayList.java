package study.flab.learn.cyh.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 * CustomStackUsingArrayList : CSUA
 */
public class CustomStackUsingArrayList<E> {

    public CustomArrayListToCSUA<E> arrayStack = new CustomArrayListToCSUA<>();
//    private ArrayList<String> arrayList = new ArrayList<String>();

    public int size() {
        return arrayStack.size();
    }

    //O(1)
    public boolean isEmpty() {
        return arrayStack.isEmpty();
    }

    //O(1)
    public void push(E e) {
        arrayStack.add(e);
    }

    //O(1)
    //Stack의 가장 위에 있는 element를 제거하고 return
    public E pop() {
        if (arrayStack.size() == 0) {
            throw new EmptyStackException();
        }
        E e = peek();
        arrayStack.remove(arrayStack.lastIndex - 1);
        return e;
    }

    //O(1)
    //Stack의 가장 위에 있는 element를 조회
    public E peek() {
        if (arrayStack.size() == 0) {
            throw new EmptyStackException();
        }
        return arrayStack.get(arrayStack.lastIndex - 1);
    }

    @Override
    public String toString() {
        return "arrayStack=" + arrayStack;
    }
}




class CustomArrayListToCSUA<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    protected int lastIndex;

    protected CustomArrayListToCSUA() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    protected CustomArrayListToCSUA(int capacity) {
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
    protected int size() {
        return lastIndex;
    }

    //O(1)
    protected boolean isEmpty() {
        return lastIndex == 0;
    }

    //O(n)
    protected int indexOf(Object element) {
        for (int i = 0; i < lastIndex; i++) {
            if (Objects.equals(this.elements[i], element)) {
                return i;
            }
        }
        return -1;
    }

    //O(n)
    private E[] incDoublyCapacity() {
        int capacity = this.elements.length;
        Object[] elementsTemp = new Object[capacity * 2];
        for (int i = 0; i < lastIndex; i++) {
            elementsTemp[i] = elements[i];
        }
        return (E[]) elementsTemp;
    }

    //amortised O(1)
    protected boolean add(E element) {
        if (this.elements.length <= lastIndex) {
            this.elements = incDoublyCapacity();
        }
        this.elements[lastIndex++] = element;
        return true;
    }

    //O(n)
    protected void add(int index, E element) {
        //음수인 경우 에러처리
        if(index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (this.elements.length <= lastIndex) {
            this.elements = incDoublyCapacity();
        }

        for (int i = lastIndex; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[index] = element;
        lastIndex++;
    }

    //O(1)
    protected E get(int index) {
        return (E) this.elements[index];
    }

    //O(1)
    protected E set(int index, E e) {
        return (E) (this.elements[index]=e);
    }

    //O(n)
    protected E remove(int index) {
        for (int i = index; i < lastIndex - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.elements[--lastIndex] = null;
        return (E) this.elements;
    }


    @Override
    public String toString() {
        return "size=" + lastIndex
                + ", elements=" + Arrays.toString(this.elements);
    }
}