package study.flab.learn.cyh.DataStructure;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class CustomDoublyLinkedList<T> {

    private int size;
    private CustomDoublyNode<T> head;
    private CustomDoublyNode<T> tail;

    public CustomDoublyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public boolean add(T data) {
        addLast(data);
        return true;
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
        CustomDoublyNode<T> prevNode = access(index - 1);  // 추가하려는 위치의 이전 노드
        CustomDoublyNode<T> currNode = prevNode.next;  // 추가하려는 위치의 노드
        CustomDoublyNode<T> newNode = new CustomDoublyNode<>(data); // 추가하려는 노드

        prevNode.next = newNode; // 이전 노드의 다음 노드를 새 노드로 변경
        newNode.next = currNode; // 새 노드의 다음 노드를 현재 노드로 변경
        size++;
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

    public void addFirst(T data) {
        CustomDoublyNode<T> newNode = new CustomDoublyNode<>(data);
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
        CustomDoublyNode<T> newNode = new CustomDoublyNode<>(data);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    private CustomDoublyNode<T> access(int index) {
        //범위 밖인 경우 처리
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        CustomDoublyNode<T> currNode;
        // 뒤에서부터 접근하는 경우
        if (index > size/2) {
            currNode = tail;
            for (int i = size-1; i > index; i--) {
                currNode = currNode.prev;
            }
            return currNode;
        }

        currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }


    public String toString() {
        StringBuilder sum = new StringBuilder();
        CustomDoublyNode<T> currNode = head;
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

    //O(n)
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //O(nm)
    public boolean containsAll(Collection<T> c) {
        for (T t : c) {
            if (!contains(t)) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (Objects.isNull(o)) {
            for (CustomDoublyNode<T> node = head; node != null; node = node.next) {
                if (Objects.isNull(node.data))
                    return index;
                index++;
            }
        } else {
            for (CustomDoublyNode<T> node = head; node != null; node = node.next) {
                if (o.equals(node.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        CustomDoublyNode<T> node = access(index);
        return node.data;
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size - 1);
    }

    public T peek() {
        return (head == null) ? null : head.data;
    }

    public T peekFirst() {
        return peek();
    }

    public T peekLast() {
        return (tail == null) ? null : tail.data;
    }

    public T poll() {
        return (head == null) ? null : remove(0);
    }

    public T pollFirst() {
        return poll();
    }

    public T pollLast() {
        return (head == null) ? null : remove(size - 1);
    }

    public T pop() {
        CustomDoublyNode<T> currNode = head;
        removeFirst();
        return currNode.data;
    }

    public void push(T t) {
        addFirst(t);
    }

    public T remove(int index) {
        CustomDoublyNode<T> currNode = access(index);
        if (index <= 0) {
            removeFirst();
            return currNode.data;
        }
        CustomDoublyNode<T> prevNode = access(index - 1);  // 삭제하려는 위치의 이전 노드
        prevNode.next = access(index + 1); // 이전 노드의 다음 노드를, 현재 노드에서 다음 노드로 변경
        size--;
        return currNode.data;
    }

    public boolean remove(Object o) {
        int targetIndex = indexOf(o);
        if (targetIndex < 0) {
            return false;
        }
        return remove(targetIndex) != null;
    }

    public boolean removeFirst() {
        if (Objects.isNull(head)) {
            return false;
        }
        head = access(1);
        size--;
        return true;
    }

    public boolean removeLast() {
        if (Objects.isNull(head)) {
            return false;
        }
        tail = access(size - 1);
        size--;
        return true;
    }

    public boolean removeAll(Collection<T> c) {
        for (T t : c) {
            if (!remove(t)) {
                return false;
            }
        }
        return true;
    }

    public T set(int index, T t) {
        CustomDoublyNode<T> currNode = access(index);
        CustomDoublyNode<T> currNodeTemp = currNode;
        currNode.data = t;
        return currNodeTemp.data;
    }

    // 이건 없습니다만 ArrayList랑 비슷하게 구현해본다면?
    public LinkedList<T> subList(int from, int to) {
        LinkedList<T> rtnList = new LinkedList<>();
        for (int i = from, j = 0; i < to; i++, j++) {
            rtnList.add(j, access(i).data);
        }
        return rtnList;
    }


    public int size() {
        return size;
    }
}

class CustomDoublyNode<T> {
    T data;
    CustomDoublyNode<T> next;
    CustomDoublyNode<T> prev;

    public CustomDoublyNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}