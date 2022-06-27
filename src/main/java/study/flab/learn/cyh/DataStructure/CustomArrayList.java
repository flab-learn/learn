package study.flab.learn.cyh.DataStructure;

import java.util.*;

public class CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int lastIndex;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int capacity) {
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

    public int size() {
        return lastIndex;
    }

    private boolean isFull() {
        return (elements.length <= lastIndex) ? true : false;
    }

    //O(1)
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    //O(n)
    public int indexOf(Object element) {
        int i;
        if (element == null) {
            for (i = 0; i < lastIndex; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (i = 0; i < lastIndex; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    //O(n)
    private Object[] incDoublyCapacity(int capacity) {
        Object[] elementsTemp = new Object[capacity * 2];
        for (int i = 0; i < lastIndex; i++) {
            elementsTemp[i] = this.elements[i];
        }
        return elementsTemp;
    }

    //amortised O(1)
    public boolean add(E element) {
        if (isFull()) {
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

        if (isFull()) {
            this.elements = incDoublyCapacity(this.elements.length);
        }

        for (int i = lastIndex; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[index] = element;
        lastIndex++;
    }

    //O(n)
    public boolean addAll(Collection c) {
        int capacity = c.size();
        for (Object o : c) {
            add((E) o);
        }
        return capacity != 0;
    }

    //O(n^2)
    public boolean addAll(int index, Collection c) {
        Object[] elementsTemp = c.toArray();
        int capacityTemp = elementsTemp.length;
        int capacity = this.elements.length;

        int capacityInput = capacityTemp + lastIndex;
        if (capacity < capacityInput) {
            this.elements = incDoublyCapacity(capacityInput);
        }

        for (int i = capacityInput - 1; i >= index; i--) {
            this.elements[i] = this.elements[i - capacityTemp];
        }

        for (int i = index, j = 0; i < index+capacityTemp; i++, j++) {
            this.elements[i] = elementsTemp[j];
        }

        return capacityTemp != 0;
    }

    //O(n)
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //O(n^2)
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    //O(1)
    public E get(int index) {
        return (E) this.elements[index];
    }

    //O(1)
    public E set(int index, E e) {
        return (E) (this.elements[index] = e);
    }

    //O(n)
    public E remove(int index) {
        for (int i = index; i < lastIndex - 1; i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.elements[--lastIndex] = null;
        return (E) this.elements;
    }

    //O(n)
    public boolean remove(Object o) {
        return remove(indexOf(o)) != null;
    }

    //O(n^2)
    public boolean removeAll(Collection c) {
        for (Object o : c) {
            if (!remove(o)) {
                return false;
            }
        }
        return true;
    }

    //O(n)
    public CustomArrayList<E> subList(int startIndex, int endIndex) {
        CustomArrayList<E> rtnList = new CustomArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            rtnList.add((E) this.elements[i]);
        }
        return rtnList;
    }

    public String toString() {
        return "CustomArrayList [elements=" + Arrays.toString(this.elements) + ", size=" + lastIndex + "]";
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.elements, lastIndex);
    }
}