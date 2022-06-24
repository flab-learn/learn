package study.flab.learn.cyh.DataStructure;

import java.util.*;

public class CustomLinkedList<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public CustomLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(T data) {
        if (size <= 0) {
            addFirst(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void add(int index, T data) {
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node<T> prevNode = access(index - 1);  // 추가하려는 위치의 이전 노드
        Node<T> currNode = prevNode.next;  // 추가하려는 위치의 노드
        Node<T> newNode = new Node<>(data); // 추가하려는 노드

        prevNode.next = newNode; // 이전 노드의 다음 노드를 새 노드로 변경
        newNode.next = currNode; // 새 노드의 다음 노드를 현재 노드로 변경
        size++;
    }

    public boolean add(T data) {
        addLast(data);
        return true;
    }

    public boolean addAll(Collection<T> c) {
        for (T t : c) {
            addLast(t);
        }
        return true;
    }

    public boolean addAll(int index, Collection<T> c) {
        for (T t : c) {
            add(index - 1, t);
            index++;
        }
        return true;
    }

    private Node<T> access(int index) {
        Node<T> currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }


    public String toString() {
        StringBuilder sum = new StringBuilder();
        Node<T> currNode = head;
        for (int i = 0; i < size; i++) {
            sum.append(currNode.data);
            if (i < size - 1) {
                sum.append(",");
            }
            if (currNode.next != null) {
                currNode = currNode.next;
            }
        }
        return sum.toString();
    }

    public boolean offer(T t) {
        return add(t);
    }
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<T> node = head; node != null; node = node.next) {
                if (node.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> node = head; node != null; node = node.next) {
                if (o.equals(node.data))
                    return index;
                index++;
            }
        }
        return -1;
    }
}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

}