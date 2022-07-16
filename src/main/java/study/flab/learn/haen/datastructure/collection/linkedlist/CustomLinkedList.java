package study.flab.learn.haen.datastructure.collection.linkedlist;

import study.flab.learn.haen.datastructure.collection.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class CustomLinkedList<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    public CustomLinkedList() {
        head = new Node<>();
        tail = new Node<>();

        head.next = tail;
        tail.prev = head;
    }

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

        Node<E> fNode = findNode(index);
        Node<E> node = new Node<>(e, fNode.next, fNode);
        fNode.next = node;

        size++;
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
        Node<E> next = head.next;
        Node<E> node = new Node<>(e, next, head);

        next.prev = node;
        head.next = node;

        size++;
    }

    // O(1)
    void addLast(E e) {
        Node<E> prev = tail.prev;
        Node<E> node = new Node<>(e, tail, prev);

        prev.next = node;
        tail.prev = node;

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
        if(index == -1)
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
        if(index == -1)
            return -1;
        return index;
    }

    boolean isEmpty() {
        return head.next == tail;
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
        if(index <= 0 || index >= size) {
            return null;
        }

        Node<E> fNode = findNode(index);
        E data = fNode.data;
        unlinkedNode(fNode);

        if(data != null) {
            return data;
        } else {
            throw new NoSuchElementException();
        }
    }

    // O(n)
    boolean remove(Object o) {
        Node<E> node = head.next;
        while(node != tail) {
            if(node.equals(o)) {
                unlinkedNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    void unlinkedNode(Node<E> node) {
        Node<E> fPrev = node.prev;
        Node<E> fNext = node.next;

        fPrev.next = fNext;
        fNext.prev = fPrev;

        node = null;
        size--;
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
        Node<E> node = head.next;
        int checkIndex = 0;
        while(node != tail) {
            if(index == checkIndex) {
                return node;
            }
            node = node.next;
            checkIndex++;
        }
        return null;
    }

    int findIndex(Object o) {
        Node<E> node = head.next;
        int checkIndex = 0;
        while(node != tail) {
            if(node.data.equals(o)) {
                break;
            }
            node = node.next;
            checkIndex++;
        }

        if(checkIndex == size) {
            return -1;
        }

        return checkIndex;
    }
}