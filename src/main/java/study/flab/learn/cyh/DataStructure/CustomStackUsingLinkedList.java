package study.flab.learn.cyh.DataStructure;

public class CustomStackUsingLinkedList<E> {

    public CustomLinkedListToCSUL<E> listStack = new CustomLinkedListToCSUL<>();

    public boolean isEmpty() {
        return false;
    }
    public void push(E e) {
        return;
    }

    public E pop() {
        return null;
    }

    public E peek() {
        return null;
    }

}




class CustomLinkedListToCSUL<E> {

    private int size;
    private CustomNodeToCSUL<E> head;
    private CustomNodeToCSUL<E> tail;
}


class CustomNodeToCSUL<T> {
    T data;
    CustomNodeToCSUL<T> next;
    CustomNodeToCSUL<T> prev;

    protected CustomNodeToCSUL(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}