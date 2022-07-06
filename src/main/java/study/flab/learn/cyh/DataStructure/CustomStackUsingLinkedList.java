package study.flab.learn.cyh.DataStructure;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Objects;

public class CustomStackUsingLinkedList<E> {

    public CustomLinkedListToCSUL<E> listStack = new CustomLinkedListToCSUL<>();
//    public CustomDoublyLinkedList<E> listStack = new CustomDoublyLinkedList<>();

    //O(1)
    public boolean isEmpty() {
        return listStack.size() == 0;
    }

    //O(1)
    public void push(E e) {
        listStack.add(e);
    }

    //O(1)
    //Stack의 가장 위에 있는 element를 제거하고 return
    public E pop() {
        if (listStack.size() == 0) {
            throw new EmptyStackException();
        }
        E e = peek();
        listStack.removeLast();
        return e;
    }

    //O(1)
    //Stack의 가장 위에 있는 element를 조회
    public E peek() {
        if (listStack.size() == 0) {
            throw new EmptyStackException();
        }
        return listStack.getLast();
    }

    @Override
    public String toString() {
        return "[" + listStack + "]";
    }
}




class CustomLinkedListToCSUL<E> {

    private int size;
    private CustomNodeToCSUL<E> head;
    private CustomNodeToCSUL<E> tail;

    public CustomLinkedListToCSUL() {
        this.size = 0;
        head = new CustomNodeToCSUL<>();
        tail = new CustomNodeToCSUL<>();
        head.nextNode = tail;
        tail.prevNode = head;
    }

    public boolean add(E data) {
        addLast(data);
        return true;
    }

    public void add(int index, E data) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        CustomNodeToCSUL<E> prevNode = access(index - 1);
        CustomNodeToCSUL<E> currNode = prevNode.nextNode;
        CustomNodeToCSUL<E> newNode = new CustomNodeToCSUL<>(data);

        prevNode.nextNode = newNode;
        newNode.nextNode = currNode;
        currNode.prevNode = newNode;
        newNode.prevNode = prevNode;
        size++;
    }

    public boolean addAll(Collection<E> c) {
        for (E e : c) {
            addLast(e);
        }
        return true;
    }

    public boolean addAll(int index, Collection<E> c) {
        for (E e : c) {
            add(index - 1, e);
            index++;
        }
        return true;
    }

    private CustomNodeToCSUL<E> access(int index) {
        //범위 밖인 경우 처리
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        CustomNodeToCSUL<E> currNode;
        if (index > size / 2) {
            currNode = tail.prevNode;
            for (int i = size - 1; i > index; i--) {
                currNode = currNode.prevNode;
            }
            return currNode;
        }

        currNode = head.nextNode;
        for (int i = 0; i < index; i++) {
            currNode = currNode.nextNode;
        }
        return currNode;
    }
    
    public void addFirst(E data) {
        CustomNodeToCSUL<E> newNode = new CustomNodeToCSUL(data);
        CustomNodeToCSUL<E> nextNode = head.nextNode;
        newNode.nextNode = nextNode;
        nextNode.prevNode = newNode;
        newNode.prevNode = head;
        head.nextNode = newNode;
        size++;
    }
    
    public void addLast(E data) {
        if (size <= 0) {
            addFirst(data);
            return;
        }
        CustomNodeToCSUL<E> newNode = new CustomNodeToCSUL(data);
        CustomNodeToCSUL<E> prevNode = tail.prevNode;
        newNode.prevNode = prevNode;
        prevNode.nextNode = newNode;
        newNode.nextNode = tail;
        tail.prevNode = newNode;
        size++;
    }

    public boolean offer(E e) {
        return add(e);
    }
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    public boolean containsAll(Collection<E> c) {
        return true;
    }

    public int indexOf(Object o) {
        if (size == 0) {
            return -1;
        }

        CustomNodeToCSUL<E> currNode = head.nextNode;
        if (Objects.isNull(o)) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(currNode.data)) {
                    return i;
                }
                currNode = currNode.nextNode;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(currNode.data, o)) {
                    return i;
                }
                currNode = currNode.nextNode;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public E get(int index) {
        CustomNodeToCSUL<E> currNode = access(index);
        return currNode.data;
    }
    public E getFirst() {
        return size == 0 ? null : head.nextNode.data;
    }
    public E getLast() {
        return size == 0 ? null : tail.prevNode.data;
    }
    public E peek() {
        return getLast();
    }
    public E peekFirst() {
        return getFirst();
    }
    public E peekLast() {
        return getLast();
    }
    public E pop() {
        CustomNodeToCSUL<E> currNode = tail.prevNode;
        removeLast();
        return currNode.data;
    }
    public void push(E e) {
        addLast(e);
    }
    public E remove(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            E strRtn = size == 0 ? null : head.nextNode.data;
            removeFirst();
            return strRtn;
        }

        CustomNodeToCSUL<E> prevNode = access(index - 1);
        CustomNodeToCSUL<E> nextNode = prevNode.nextNode.nextNode;
        E strRtn = prevNode.nextNode.data;

        prevNode.nextNode = nextNode;
        nextNode.prevNode = prevNode;
        size--;
        return strRtn;
    }
    public boolean remove(Object o) {
        remove(indexOf(o));
        return true;
    }
    public boolean removeFirst() {
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            head.nextNode = tail;
            tail.prevNode = head;
            size--;
            return true;
        }
        CustomNodeToCSUL<E> nextNode = head.nextNode.nextNode;
        nextNode.prevNode = head;
        head.nextNode = nextNode;
        size--;
        return true;
    }
    //O(1)
    public boolean removeLast() {
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            head.nextNode = tail;
            tail.prevNode = head;
            size--;
            return true;
        }
        CustomNodeToCSUL<E> prevNode = tail.prevNode.prevNode;
        prevNode.nextNode = tail;
        tail.prevNode = prevNode;
        size--;
        return true;
    }
    public boolean removeAll(Collection<E> c) {
        for (E e : c) {
            if (!remove(e)) {
                return false;
            }
        }
        return true;
    }
    public E set(int index, E e) {
        CustomNodeToCSUL<E> currNode = access(index);
        CustomNodeToCSUL<E> currNodeTemp = currNode;
        currNode.data = e;
        return currNode.data;
    }
    public LinkedList<E> subList(int from, int to) {
        LinkedList<E> listRtn = new LinkedList<>();
        for (int i = from, j = 0; i < to; i++, j++) {
            listRtn.add(j, access(i).data);
        }
        return listRtn;
    }
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sum = new StringBuilder();
        CustomNodeToCSUL<E> currNode = head.nextNode;
        for (int i = 0; i < size; i++) {
            sum.append(currNode.data);
            if (i < size - 1) {
                sum.append(",");
            }
            if (currNode.nextNode == tail) {
                continue;
            }
            if (currNode.nextNode != null) {
                currNode = currNode.nextNode;
            }
        }
        return sum.toString();
    }
}


class CustomNodeToCSUL<T> {
    T data;
    CustomNodeToCSUL<T> nextNode;
    CustomNodeToCSUL<T> prevNode;

    protected CustomNodeToCSUL(T data) {
        this.data = data;
        this.nextNode = null;
        this.prevNode = null;
    }

    public CustomNodeToCSUL() {
        this.data = null;
        this.nextNode = null;
        this.prevNode = null;
    }
}