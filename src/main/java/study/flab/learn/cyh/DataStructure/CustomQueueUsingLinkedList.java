package study.flab.learn.cyh.DataStructure;

public class CustomQueueUsingLinkedList<E> {

    public CustomLinkedListToCSUL<E> listQueue = new CustomLinkedListToCSUL<>();

    //O(1)
    public boolean isEmpty() {
        return listQueue.size() == 0;
    }
    //O(1)
    public void enqueue(E e) {
        listQueue.addLast(e);
    }
    //O(1)
    public E dequeue() {
        return listQueue.remove(0);
    }
    //O(1)
    public E peek() {
        return listQueue.peekFirst();
    }

    @Override
    public String toString() {
        return "[" + listQueue + "]";
    }
}
