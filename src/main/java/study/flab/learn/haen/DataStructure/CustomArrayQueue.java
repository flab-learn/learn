package study.flab.learn.haen.DataStructure;

public class CustomArrayQueue<E> {
    static final int DEFAULT_CAPACITY = 10;
    Object[] array;
    int front;
    int rear;
    int capacity;
    int size;

    public CustomArrayQueue() {
        this.capacity = DEFAULT_CAPACITY;
        array = new Object[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    public CustomArrayQueue(int capacity) {
        if(capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }

        this.capacity = capacity;
        array = new Object[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    boolean isEmpty() {
        return size <= 0;
    }

    // O(1) / worst: O(n)
    void enqueue(E e) {
        if(capacity <= size) {
            reAllocCapacity();
        }

        rear = (rear + 1) % capacity;
        array[rear] = e;
        size++;
    }

    // O(1)
    E dequeue() {
        if(isEmpty()) {
            return null;
        }

        front = (front + 1) % capacity;
        Object data = array[front];
        array[front] = null;
        size--;

        return (E) data;
    }

    // O(1)
    E peek() {
        if(isEmpty()) {
            return null;
        }

        return (E) array[front + 1];
    }

    // 추가 구현 - 배열 크기 재할당
    private void reAllocCapacity() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
