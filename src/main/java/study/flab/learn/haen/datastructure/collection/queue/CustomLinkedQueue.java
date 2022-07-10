package study.flab.learn.haen.datastructure.collection.queue;

import study.flab.learn.haen.datastructure.collection.Node;

public class CustomLinkedQueue<E> {
    Node<E> front;
    Node<E> rear;
    int size;

    public CustomLinkedQueue() {
        front = new Node<>();
        rear = new Node<>();

        front.next = rear;
        rear.prev = front;
    }

    boolean isEmpty() {
        return size <= 0;
    }

    void enqueue(E e) {
        Node<E> prev = rear.prev;
        Node<E> node = new Node<>(e, rear, prev);

        rear.prev = node;
        prev.next = node;
        size++;
    }

    E dequeue() {
        if(isEmpty()) {
            return null;
        }

        Node<E> node = front.next;
        Node<E> next = node.next;
        E data = node.data;

        front.next = next;
        next.prev = front;
        size--;

        return data;
    }

    E peek() {
        if(isEmpty()) {
            return null;
        }

        Node<E> node = front.next;
        return node.data;
    }
}
