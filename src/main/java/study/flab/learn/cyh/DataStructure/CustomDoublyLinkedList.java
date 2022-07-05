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

        //Sentinel
        this.head = new CustomDoublyNode<>();
        this.tail = new CustomDoublyNode<>();
        this.head.nextNode = tail;
        this.tail.prevNode = head;
    }

    //O(1)
    public boolean add(T data) {
        addLast(data);
        return true;
    }

    //O(n)
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
        CustomDoublyNode<T> currNode = prevNode.nextNode;  // 추가하려는 위치의 노드
        CustomDoublyNode<T> newNode = new CustomDoublyNode<>(data); // 추가하려는 노드

        //Sentinel
        newNode.prevNode = prevNode;
        prevNode.nextNode = newNode;
        newNode.nextNode = currNode;
        currNode.prevNode = newNode;
        size++;
    }

    //O(1)
    public boolean addAll(Collection<T> c) {
        for (T t : c) {
            addLast(t);
        }
        return true;
    }

    //O(n)
    public boolean addAll(int index, Collection<T> c) {
        for (T t : c) {
            add(index - 1, t);
            index++;
        }
        return true;
    }

    //O(1)
    public void addFirst(T data) {
        CustomDoublyNode<T> newNode = new CustomDoublyNode<>(data);
        //Sentinel
        CustomDoublyNode<T> nextNode = head.nextNode;
        nextNode.prevNode = newNode;
        head.nextNode = newNode;
        newNode.prevNode = head;
        newNode.nextNode = nextNode;
        size++;
    }

    //O(1)
    public void addLast(T data) {
        if (size <= 0) {
            addFirst(data);
            return;
        }
        CustomDoublyNode<T> newNode = new CustomDoublyNode<>(data);
        //Sentinel
        CustomDoublyNode<T> prevNode = tail.prevNode;
        prevNode.nextNode = newNode;
        newNode.nextNode = tail;
        tail.prevNode = newNode;
        newNode.prevNode = prevNode;
        size++;
    }

    //O(n)
    private CustomDoublyNode<T> access(int index) {
        //범위 밖인 경우 처리
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        CustomDoublyNode<T> currNode;
        // 뒤에서부터 접근하는 경우
        if (index > size/2) {
            //Sentinel
            currNode = tail.prevNode;
            for (int i = size - 1; i > index; i--) {
                currNode = currNode.prevNode;
            }
            return currNode;
        }

        //Sentinel
        currNode = head.nextNode;
        for (int i = 0; i < index; i++) {
            currNode = currNode.nextNode;
        }
        return currNode;
    }

    //O(n)
    public String toString() {
        StringBuilder sum = new StringBuilder();
        //Sentinel
        CustomDoublyNode<T> currNode = head.nextNode;
        for (int i = 0; i < size; i++) {
            sum.append(currNode.data);
            if (i < size - 1) {
                sum.append(",");
            }
            //Sentinel
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
    public boolean offer(T t) {
        return add(t);
    }

    //O(1)
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    //O(1)
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

    //O(n)
    public int indexOf(Object o) {
        int index = 0;
        if (Objects.isNull(o)) {
            for (CustomDoublyNode<T> node = head.nextNode; node != null; node = node.nextNode) {
                if (Objects.isNull(node.data))
                    return index;
                index++;
            }
        } else {
            for (CustomDoublyNode<T> node = head.nextNode; node != null; node = node.nextNode) {
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
        //Sentinel
        return head.nextNode == tail && tail.prevNode == head;
    }

    //O(n)
    public T get(int index) {
        CustomDoublyNode<T> node = access(index);
        return node.data;
    }

    //O(1)
    public T getFirst() {
//        return get(0);
        //Sentinel
        return (head.nextNode == null) ? null : head.nextNode.data;
    }

    //O(1)
    public T getLast() {
//        return get(size - 1);
        //Sentinel
        return (tail.prevNode == null) ? null : tail.prevNode.data;
    }

    //O(1)
    public T peek() {
//        return (head.nextNode == null) ? null : getFirst();
        return getFirst();
    }

    //O(1)
    public T peekFirst() {
        return peek();
    }

    //O(1)
    public T peekLast() {
//        return (tail.prevNode == null) ? null : tail.prevNode.data;
        return getLast();
    }

    //O(n)
    public T poll() {
        return (head.nextNode == null) ? null : remove(0);
    }

    //O(1)
    public T pollFirst() {
        return poll();
    }

    //O(1)
    public T pollLast() {
        return (tail.prevNode == null) ? null : remove(size - 1);
    }

    //O(1)
    public T pop() {
        CustomDoublyNode<T> currNode = head.nextNode;
        removeFirst();
        return currNode.data;
    }

    //O(1)
    public void push(T t) {
        addFirst(t);
    }

    /**
     * 2022.06.27
     * currNode가 doubly linked에서는 앞 뒤 노드 정보를 들고 있기 때문에
     * 새로 index로 참조하는 것을 피할 수 있습니다.
     * index로 접근하는 형태는 linked list에서 비효율적이거든요
     */
    //O(n)
    public T remove(int index) {
        if (index <= 0) {
            String strRtn = (head.nextNode == null ? null : String.valueOf(head.nextNode.data));
            removeFirst();
            return (T) strRtn;
        }
        if (index == size) {
            String strRtn = (tail.prevNode == null ? null : String.valueOf(tail.prevNode.data));
            removeLast();
            return (T) strRtn;
        }

        //doubly
        CustomDoublyNode<T> prevNode = access(index - 1);  // 삭제하려는 위치의 이전 노드
//        CustomDoublyNode<T> currNode = prevNode.next;   // 삭제하려는 위치의 노드(현재노드)
        String strRtn = String.valueOf(prevNode.nextNode.data); // 삭제하려는 위치의 노드의 data(현재노드의 data)

        // 삭제할 노드(현재노드)의 전후 노드를 연결
        prevNode.nextNode = prevNode.nextNode.nextNode; // 이전 노드의 다음 노드를, 현재 노드에서 다음 노드로 변경
        //Sentinel
        prevNode.nextNode.prevNode = prevNode;  //prevNode.next.prev == currNode.prev
        size--;
        return (T) strRtn;
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
        //Sentinel
        if (size == 1) {
            head.nextNode = tail;
            tail.prevNode = head;
            size--;
            return true;
        }
        //Sentinel
        CustomDoublyNode<T> nextNode = head.nextNode.nextNode;
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
        //Sentinel
        if (size == 1) {
            head.nextNode = tail;
            tail.prevNode = head;
            size--;
            return true;
        }
        //Sentinel
        CustomDoublyNode<T> prevNode = tail.prevNode.prevNode;
        prevNode.prevNode = tail.prevNode.prevNode.prevNode;
        prevNode.nextNode = tail;
        tail.prevNode = prevNode;
        size--;
        return true;
    }

    //O(n)
    public boolean removeAll(Collection<T> c) {
        for (T t : c) {
            if (!remove(t)) {
                return false;
            }
        }
        return true;
    }

    //O(n)
    public T set(int index, T t) {
        CustomDoublyNode<T> currNode = access(index);
        CustomDoublyNode<T> currNodeTemp = currNode;
        currNode.data = t;
        return currNodeTemp.data;
    }

    // 이건 없습니다만 ArrayList랑 비슷하게 구현해본다면?
    //O(nm)
    public LinkedList<T> subList(int from, int to) {
        LinkedList<T> rtnList = new LinkedList<>();
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

    //Sentinel
    public CustomDoublyNode() {
        this.data = null;
        this.nextNode = null;
        this.prevNode = null;
    }
}