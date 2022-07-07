package study.flab.learn.cyh.DataStructure;

public class CustomQueueUsingArrayList<E> {

    public CustomArrayListToCSUA<E> arrayQueue = new CustomArrayListToCSUA<>();

    //O(1)
    public boolean isEmpty() {
        return arrayQueue.size() == 0;
    }
    //O(1)
    public void enqueue(E e) {
        arrayQueue.add(e);
    }
    //O(n)
    public E dequeue() {
        E strRtn = arrayQueue.get(0);
        arrayQueue.remove(0);
        return strRtn;
    }
    //O(1)
    public E peek() {
        return arrayQueue.get(0);
    }

    @Override
    public String toString() {
        return "[" + arrayQueue + "]";
    }
}
