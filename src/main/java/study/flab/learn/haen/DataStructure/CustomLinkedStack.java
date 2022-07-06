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

    void push(E e) {
        Node<E> prev = tail.prev;
        Node<E> node = new Node<>(e, tail, prev);

        tail.prev = node;
        prev.next = node;
        size++;
    }

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

    E peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        Node<E> node = tail.prev;
        return node.data;
    }
}