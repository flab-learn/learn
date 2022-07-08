package study.flab.learn.haen.DataStructure;

import java.util.EmptyStackException;

public class CustomLinkedStack<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    public CustomLinkedStack() {
        head = new Node<>();
        tail = new Node<>();

        head.next = tail;
        tail.prev = head;
    }

    boolean isEmpty() {
        return head.next == tail;
    }

    // O(1)
    void push(E e) {
        Node<E> prev = tail.prev;
        Node<E> node = new Node<>(e, tail, prev);

        tail.prev = node;
        prev.next = node;
        size++;
    }

    // O(1)
    E pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        Node<E> node = tail.prev;
        Node<E> prev = node.prev;
        E data = node.data;

        prev.next = tail;
        tail.prev = prev;
        size--;

        return data;
    }

    // O(1)
    E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        Node<E> node = tail.prev;
        return node.data;
    }
}