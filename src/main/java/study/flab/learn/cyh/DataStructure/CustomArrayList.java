package study.flab.learn.cyh.DataStructure;

import java.util.*;

public class CustomArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int lastIdx;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int capacity) {
        if (capacity > 0) {
            this.elements = new Object[capacity];
        } else if (capacity == 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
        }
    }

    public int size() {
        return lastIdx;
    }

    public boolean isLastIdx(int capacity) {
        return (capacity <= lastIdx) ? true : false;
    }

    //O(1)
    public boolean isEmpty() {
        return lastIdx == 0;
    }

    //O(n)
    public int indexOf(Object element) {
        int i;
        if (element == null) {
            for (i = 0; i < lastIdx; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (i = 0; i < lastIdx; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    //O(n)
    private Object[] resizing(Object[] elements, int capacity) {
        Object[] elementsTemp = new Object[capacity];
        for (int i = 0; i < lastIdx; i++) {
            elementsTemp[i] = elements[i];
        }
        return elementsTemp;
    }

    //amortised O(1)
    public boolean add(E element) {
        int capacity = this.elements.length;
        if (isLastIdx(capacity)) {
            this.elements = resizing(this.elements, capacity * 2);
        }
        this.elements[lastIdx++] = element;
        return true;
    }

    //O(n)
    public void add(int idx, E element) {
        int capacity = this.elements.length;
        if (isLastIdx(capacity)) {
            this.elements = resizing(this.elements, capacity * 2);
        }

        for (int i = lastIdx; i > idx; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[idx] = element;
        lastIdx++;
    }

    //O(n^2)
    public boolean addAll(Collection c) {
        Object[] elementsTemp = c.toArray();
        int capacity = elementsTemp.length;
        for (Object o : elementsTemp) {
            add((E) o);
        }
        return capacity != 0;
    }

    //O(n^2)
    public boolean addAll(int idx, Collection c) {
        Object[] elementsTemp = c.toArray();
        int capacityTemp = elementsTemp.length;
        int capacity = this.elements.length;

        int capacityInput = capacityTemp + lastIdx;
        if (capacity < capacityInput) {
            this.elements = resizing(this.elements, capacityInput * 2);
        }

        for (int i = capacityInput - 1; i >= idx; i--) {
            this.elements[i] = this.elements[i - capacityTemp];
        }

        for (int i = idx, j = 0; i < idx+capacityTemp; i++, j++) {
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
        Object[] elementsTemp = c.toArray();
        for (Object o : elementsTemp) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    //O(1)
    public E get(int paramInt) {
        return (E) this.elements[paramInt];
    }

    //O(1)
    public E set(int paramInt, E paramE) {
        return (E) (this.elements[paramInt] = paramE);
    }

    //O(n)
    public E remove(int idx) {
        for (int i = idx; i < lastIdx - 1; i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.elements[--lastIdx] = null;
        return (E) this.elements;
    }

    //O(n^2)
    public boolean remove(Object o) {
        return remove(indexOf(o)) != null;
    }

    //O(n^2)
    public boolean removeAll(Collection c) {
        Object[] elementsTemp = c.toArray();
        for (Object o : elementsTemp) {
            if (!remove(o)) {
                return false;
            }
        }
        return true;
    }

    //O(n^2)
    public CustomArrayList<E> subList(int startIndex, int endIndex) {
        CustomArrayList<E> rtnList = new CustomArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            rtnList.add((E) this.elements[i]);
        }
        return rtnList;
    }

    public String toString() {
        return "CustomArrayList [elements=" + Arrays.toString(this.elements) + ", size=" + lastIdx + "]";
    }


    public Object[] toArray() {
        return Arrays.copyOf(this.elements, lastIdx);
    }
}