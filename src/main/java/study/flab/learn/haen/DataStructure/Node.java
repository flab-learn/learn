package study.flab.learn.haen.DataStructure;

public class Node<E> {

    public Node(E data, Node<E> next, Node<E> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public E data;
    public Node<E> next;
    public Node<E> prev;
}