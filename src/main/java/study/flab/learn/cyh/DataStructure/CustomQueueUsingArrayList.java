package study.flab.learn.cyh.DataStructure;

import java.util.Arrays;

public class CustomQueueUsingArrayList<E> {

//    public CustomArrayListToCSUA<E> arrayQueue = new CustomArrayListToCSUA<>();
    public CustomArrayListToCQUA<E> arrayQueue = new CustomArrayListToCQUA<>();

    //O(1)
    public boolean isEmpty() {
        return arrayQueue.size() == 0;
    }
    //O(1)
    public void enqueue(E e) {
        arrayQueue.add(e);
    }
    //O(1)
    public E dequeue() {
        return arrayQueue.removeFirst();
    }
    //O(1)
    public E peek() {
        return arrayQueue.getHead();
    }

    @Override
    public String toString() {
        return arrayQueue.toString();
    }
}
