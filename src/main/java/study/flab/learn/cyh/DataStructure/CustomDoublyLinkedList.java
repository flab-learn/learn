package study.flab.learn.cyh.DataStructure;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class CustomDoublyLinkedList<E> {

    private int size;
    private CustomDoublyNode<E> head;
    private CustomDoublyNode<E> tail;

    public CustomDoublyLinkedList() {
        this.size = 0;
        this.head = new CustomDoublyNode<>();
        this.tail = new CustomDoublyNode<>();
        this.head.nextNode = tail;
        this.tail.prevNode = head;
    }

    //O(1)
    public boolean add(E data) {
        addLast(data);
        return true;
    }

    //O(n)
    public void add(int index, E data) {
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        CustomDoublyNode<E> prevNode = access(index - 1);  // 추가하려는 위치의 이전 노드
        CustomDoublyNode<E> currNode = prevNode.nextNode;  // 추가하려는 위치의 노드
        CustomDoublyNode<E> newNode = new CustomDoublyNode<>(data); // 추가하려는 노드
        newNode.prevNode = prevNode;
        prevNode.nextNode = newNode;
        newNode.nextNode = currNode;
        currNode.prevNode = newNode;
        size++;
    }

    //O(1)
    public boolean addAll(Collection<E> c) {
        for (E e : c) {
            addLast(e);
        }
        return true;
    }

    //O(n)
    public boolean addAll(int index, Collection<E> c) {
        for (E e : c) {
            add(index - 1, e);
            index++;
        }
        return true;
    }

    //O(1)
    public void addFirst(E data) {
        CustomDoublyNode<E> newNode = new CustomDoublyNode<>(data);
        CustomDoublyNode<E> nextNode = head.nextNode;
        nextNode.prevNode = newNode;
        head.nextNode = newNode;
        newNode.prevNode = head;
        newNode.nextNode = nextNode;
        size++;
    }

    //O(1)
    public void addLast(E data) {
        if (size <= 0) {
            addFirst(data);
            return;
        }
        CustomDoublyNode<E> newNode = new CustomDoublyNode<>(data);
        CustomDoublyNode<E> prevNode = tail.prevNode;
        prevNode.nextNode = newNode;
        newNode.nextNode = tail;
        tail.prevNode = newNode;
        newNode.prevNode = prevNode;
        size++;
    }

    //O(n)
    private CustomDoublyNode<E> access(int index) {
        //범위 밖인 경우 처리
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        CustomDoublyNode<E> currNode;
        // 뒤에서부터 접근하는 경우
        if (index > size/2) {
            currNode = tail.prevNode;
            for (int i = size - 1; i > index; i--) {
                currNode = currNode.prevNode;
            }
            return currNode;
        }

        currNode = head.nextNode;
        for (int i = 0; i < index; i++) {
            currNode = currNode.nextNode;
        }
        return currNode;
    }

    //O(n)
    public String toString() {
        StringBuilder sum = new StringBuilder();
        CustomDoublyNode<E> currNode = head.nextNode;
        for (int i = 0; i < size; i++) {
            sum.append(currNode.data);
            if (i < size - 1) {
                sum.append(",");
            }
            if (currNode.nextNode == tail) {
                continue;
            }
            if (currNode.nextNode != null) {
                currNode = currNode.nextNode;
            }
        }
        return sum.toString();
    }

    //O(1)
    public boolean offer(E e) {
        return add(e);
    }

    //O(1)
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    //O(1)
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    //O(n)
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //O(nm)
    public boolean containsAll(Collection<E> c) {
        for (E e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    //O(n)
    public int indexOf(Object o) {
        int index = 0;
        if (Objects.isNull(o)) {
            for (CustomDoublyNode<E> node = head.nextNode; node != null; node = node.nextNode) {
                if (Objects.isNull(node.data))
                    return index;
                index++;
            }
        } else {
            for (CustomDoublyNode<E> node = head.nextNode; node != null; node = node.nextNode) {
                if (o.equals(node.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    //O(1)
    public boolean isEmpty() {
//        return size == 0;
        return head.nextNode == tail && tail.prevNode == head;
    }

    //O(n)
    public E get(int index) {
        CustomDoublyNode<E> node = access(index);
        return node.data;
    }

    //O(1)
    public E getFirst() {
//        return get(0);
        return (head.nextNode == null) ? null : head.nextNode.data;
    }

    //O(1)
    public E getLast() {
//        return get(size - 1);
        return (tail.prevNode == null) ? null : tail.prevNode.data;
    }

    //O(1)
    public E peek() {
//        return (head.nextNode == null) ? null : getFirst();
        return getFirst();
    }

    //O(1)
    public E peekFirst() {
        return peek();
    }

    //O(1)
    public E peekLast() {
//        return (tail.prevNode == null) ? null : tail.prevNode.data;
        return getLast();
    }

    //O(n)
    public E poll() {
        return (head.nextNode == null) ? null : remove(0);
    }

    //O(1)
    public E pollFirst() {
        return poll();
    }

    //O(1)
    public E pollLast() {
        return (tail.prevNode == null) ? null : remove(size - 1);
    }

    //O(1)
    public E pop() {
        CustomDoublyNode<E> currNode = head.nextNode;
        removeFirst();
        return currNode.data;
    }

    //O(1)
    public void push(E e) {
        addFirst(e);
    }

    /**
     * 2022.06.27
     * currNode가 doubly linked에서는 앞 뒤 노드 정보를 들고 있기 때문에
     * 새로 index로 참조하는 것을 피할 수 있습니다.
     * index로 접근하는 형태는 linked list에서 비효율적이거든요
     */
    //O(n)
    public E remove(int index) {
        if (index <= 0) {
            String strRtn = (head.nextNode == null ? null : String.valueOf(head.nextNode.data));
            removeFirst();
            return (E) strRtn;
        }
        if (index == size) {
            String strRtn = (tail.prevNode == null ? null : String.valueOf(tail.prevNode.data));
            removeLast();
            return (E) strRtn;
        }

        CustomDoublyNode<E> prevNode = access(index - 1);  // 삭제하려는 위치의 이전 노드
//        CustomDoublyNode<T> currNode = prevNode.next;   // 삭제하려는 위치의 노드(현재노드)
        String strRtn = String.valueOf(prevNode.nextNode.data); // 삭제하려는 위치의 노드의 data(현재노드의 data)

        // 삭제할 노드(현재노드)의 전후 노드를 연결
        prevNode.nextNode = prevNode.nextNode.nextNode; // 이전 노드의 다음 노드를, 현재 노드에서 다음 노드로 변경
        prevNode.nextNode.prevNode = prevNode;
        size--;
        return (E) strRtn;
    }

    //O(n)
    public boolean remove(Object o) {
        int targetIndex = indexOf(o);
        if (targetIndex < 0) {
            return false;
        }
        return remove(targetIndex) != null;
    }

    //O(1)
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        if (size == 1) {
            head.nextNode = tail;
            tail.prevNode = head;
            size--;
            return true;
        }
        CustomDoublyNode<E> nextNode = head.nextNode.nextNode;
        nextNode.nextNode = head.nextNode.nextNode.nextNode;
        nextNode.prevNode = head;
        head.nextNode = nextNode;
        size--;
        return true;
    }

    //O(1)
    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        }
        if (size == 1) {
            head.nextNode = tail;
            tail.prevNode = head;
            size--;
            return true;
        }
        CustomDoublyNode<E> prevNode = tail.prevNode.prevNode;
        prevNode.prevNode = tail.prevNode.prevNode.prevNode;
        prevNode.nextNode = tail;
        tail.prevNode = prevNode;
        size--;
        return true;
    }

    //O(n)
    public boolean removeAll(Collection<E> c) {
        for (E e : c) {
            if (!remove(e)) {
                return false;
            }
        }
        return true;
    }

    //O(n)
    public E set(int index, E e) {
        CustomDoublyNode<E> currNode = access(index);
        CustomDoublyNode<E> currNodeTemp = currNode;
        currNode.data = e;
        return currNodeTemp.data;
    }

    // 이건 없습니다만 ArrayList랑 비슷하게 구현해본다면?
    //O(nm)
    public LinkedList<E> subList(int from, int to) {
        LinkedList<E> rtnList = new LinkedList<>();
        for (int i = from, j = 0; i < to; i++, j++) {
            rtnList.add(j, access(i).data);
        }
        return rtnList;
    }

    //O(1)
    public int size() {
        return size;
    }
}

class CustomDoublyNode<T> {
    T data;
    CustomDoublyNode<T> nextNode;
    CustomDoublyNode<T> prevNode;

    public CustomDoublyNode(T data) {
        this.data = data;
        this.nextNode = null;
        this.prevNode = null;
    }

    public CustomDoublyNode() {
        this.data = null;
        this.nextNode = null;
        this.prevNode = null;
    }
}