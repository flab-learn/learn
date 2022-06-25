package study.flab.learn.haen.DataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class CustomLinkedList<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    // O(1)
    boolean add(E e) {
        addLast(e);
        return true;
    }

    // 처음, 끝: O(1) / 중간: O(n)
    void add(int index, E e) {
        if(index < 0) {
            return;
        }

        if(index == 0) {
            addFirst(e);
        } else if(index == size) {
            addLast(e);
        } else {
            Node<E> fNode = findNode(index);
            Node<E> node = new Node<>(e, fNode.next, fNode);
            fNode.next = node;

            size++;
        }
    }

    // O(n)
    boolean addAll(Collection<E> c) {
        for(E e : c) {
            addLast(e);
        }
        return true;
    }

    // O(n)
    boolean addAll(int index, Collection<E> c) {
        Node<E> fNode = findNode(index);
        if(fNode == null) {
            return false;
        }

        Node<E> fNext = fNode.next;
        Node<E> iter = fNode;
        for(E e : c) {
            Node<E> node = new Node<>(e, null, iter.next);
            iter.next = node;
            iter = node;
        }
        iter.next = fNext;

        return true;
    }

    // O(1)
    void addFirst(E e) {
        Node<E> node = new Node<>(e, head, null);

        if(head != null) {
            head.prev = node;
        } else {
            tail = node;
        }

        head = node;
        size++;
    }

    // O(1)
    void addLast(E e) {
        if(size <= 0) {
            addFirst(e);
            return;
        }

        Node<E> node = new Node<E>(e, null, tail);
        tail.prev = node;
        tail = node;
        size++;
    }

    // O(1)
    boolean offer(E e) {
        addLast(e);
        return true;
    }

    // O(1)
    boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    // O(1)
    boolean offerLast(E e) {
        addLast(e);
        return  true;
    }

    // O(n)
    boolean contains(Object o) {
        int index = findIndex(o);
        if(index == size)
            return false;
        return true;
    }

    // O(n^2)
    boolean containsAll(Collection<E> c) {
        for(E e : c) {
            if(!contains(e)) {
                return false;
            }
        }
        return true;
    }

    // O(n)
    int indexOf(Object o) {
        int index = findIndex(o);
        if(index == size)
            return -1;
        return index;
    }

    boolean isEmpty() {
        return size <= 0;
    }

    // O(n)
    E get(int index) {
        Node<E> fNode = findNode(index);
        if(fNode != null) {
            return fNode.data;
        }
        return null;
    }

    // O(1)
    E getFirst() {
        if(head != null) {
            return head.data;
        }
        throw new NoSuchElementException();
    }

    // O(1)
    E getLast() {
        if(tail != null) {
            return tail.data;
        }
        throw new NoSuchElementException();
    }

    // O(1)
    E peek() {
        if(head != null) {
            return head.data;
        }
        return null;
    }

    // O(1)
    E peekFirst() {
        if(size <= 0) {
            return null;
        }
        return getFirst();
    }

    // O(1)
    E peekLast() {
        if(size <= 0) {
            return null;
        }
        return getLast();
    }

    // O(1)
    E poll() {
        if(size <= 0) {
            return null;
        }

        Node<E> node = head.next;
        if(node != null) {
            node.prev = null;
        }

        E data = head.data;
        head = node;
        size--;

        return data;

    }

    // O(1)
    E pollFirst() {
        return poll();
    }

    // O(1)
    E pollLast() {
        if(size <= 0) {
            return null;
        }

        Node<E> node = tail.prev;
        if(node != null) {
            node.next = null;
        }

        E data = tail.data;
        tail = node;
        size--;
        return data;
    }

    E pop() {
        return removeFirst();
    }

    void push(E e) {
        addFirst(e);
    }

    // O(n)
    E remove(int index) {
        Node<E> fNode = findNode(index);
        if(fNode != null) {
            Node<E> fPrev = fNode.prev;
            Node<E> fNext = fNode.next;

            fPrev.next = fNext;
            fNext.prev = fPrev;

            E data = fNode.data;
            fNode = null;
            size--;
            return data;
        }
        throw new NoSuchElementException();
    }

    // O(n)
    boolean remove(Object o) {
        int index = indexOf(o);
        E e = remove(index);
        if(e != null) {
            return true;
        }
        return false;
    }

    // O(1)
    E removeFirst() {
        if(size <= 0) {
            throw new NoSuchElementException();
        }
        return poll();
    }

    // O(1)
    E removeLast() {
        if(size <= 0) {
            throw new NoSuchElementException();
        }
        return pollLast();
    }

    // O(n)
    boolean removeAll(Collection<E> c) {
        for(E e : c) {
            if(!remove(e)) {
                return false;
            }
        }
        return true;
    }

    // O(n)
    E set(int index, E e) {
        Node<E> fNode = findNode(index);
        fNode.data = e;
        return fNode.data;
    }

    // O(n)
    ArrayList<E> subList(int from, int to) {
        ArrayList<E> list = new ArrayList<>();
        int index = from;
        Node<E> node = findNode(index);
        while(index < to) {
            if(node != null) {
                list.add(node.data);
                node = node.next;
            }
            index++;
        }
        return list;
    }

    // 추가 구현 - 노드 찾기
    Node<E> findNode(int index) {
        Node<E> node = head;
        int checkIndex = 0;
        while(node != null) {
            if(index == checkIndex) {
                return node;
            }
            node = node.next;
            checkIndex++;
        }
        return null;
    }

    // 추가 구현 - sentinel
    void linkedSentinel(Object data) {
        tail.next = new Node<>((E)data, null, tail.next);
    }

    void unlinkedSentinel() {
        tail.next = null;
    }

    int findIndex(Object o) {
        // sentinel
        linkedSentinel(o);

        Node<E> node = head;
        int checkIndex = 0;
        while(true) {
            if(node.data.equals(o)) {
                break;
            }
            node = node.next;
            checkIndex++;
        }

        unlinkedSentinel();

        return checkIndex;
    }
}

class Node<E> {

    public Node(E data, Node<E> next, Node<E> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public E data;
    public Node<E> next;
    public Node<E> prev;
}