package study.flab.learn.cyh.DataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomDoublyLinkedListTest {

    private CustomDoublyLinkedList customLinkedList;
    private List javaLinkedList;


    @BeforeEach
    void setUp() {
        javaLinkedList = new LinkedList();
        customLinkedList = new CustomDoublyLinkedList();

    }

    @Test
    void addFirst() {
        customLinkedList.addFirst("1");
        assertThat(customLinkedList.toString()).isEqualTo("1");
    }

    @Test
    void addLast() {
        customLinkedList.addLast("2");
        assertThat(customLinkedList.toString()).isEqualTo("2");
    }

    @Test
    void add() {
        customLinkedList.add("3");
        assertThat(customLinkedList.toString()).isEqualTo("3");
    }

    @Test
    void addTotalTest() {
        customLinkedList.addLast("2");
        customLinkedList.addFirst("1");
        customLinkedList.add("3");
        assertThat(customLinkedList.toString()).isEqualTo("1,2,3");
    }

    @Test
    void addWithIndex() {
        customLinkedList.add(0,"2");
        customLinkedList.add(1,"3");
        customLinkedList.add(0,"1");
        assertThat(customLinkedList.toString()).isEqualTo("1,2,3");
    }

    @Test
    void addTotalTest2() {
        customLinkedList.addLast("2");
        customLinkedList.addFirst("1");
        customLinkedList.add("3");
        customLinkedList.add(0,"4");
        assertThat(customLinkedList.toString()).isEqualTo("4,1,2,3");
    }

    @Test
    void addAll() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        javaLinkedList.add("3");
        javaLinkedList.add("4");

        customLinkedList.addAll(javaLinkedList);

        assertThat(customLinkedList.toString()).isEqualTo("1,2,3,4");
    }

    @Test
    void addAllWithIndex() {
        customLinkedList.add("1");
        customLinkedList.add("4");
        javaLinkedList.add("2");
        javaLinkedList.add("3");

        customLinkedList.addAll(2, javaLinkedList);

        assertThat(customLinkedList.toString()).isEqualTo("1,2,3,4");
    }

    @Test
    void offer() {
        customLinkedList.add("1");
        customLinkedList.add("2");

        customLinkedList.offer("3");
        customLinkedList.offer("4");

        assertThat(customLinkedList.toString()).isEqualTo("1,2,3,4");
    }

    @Test
    void offerFirst() {
        customLinkedList.add("3");
        customLinkedList.add("4");

        customLinkedList.offerFirst("2");
        customLinkedList.offerFirst("1");

        assertThat(customLinkedList.toString()).isEqualTo("1,2,3,4");
    }

    @Test
    void offerLast() {
        customLinkedList.add("1");
        customLinkedList.add("2");

        customLinkedList.offerLast("3");
        customLinkedList.offerLast("4");

        assertThat(customLinkedList.toString()).isEqualTo("1,2,3,4");
    }

    @Test
    void contains() {
        customLinkedList.add("1");
        customLinkedList.add("2");

        assertThat(customLinkedList.contains("2")).isTrue();
        assertThat(customLinkedList.contains("3")).isFalse();
    }

    @Test
    void containsAll() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        javaLinkedList.add("1");
        javaLinkedList.add("2");

        assertThat(customLinkedList.containsAll(javaLinkedList)).isTrue();
    }

    @Test
    void isEmpty() {
        assertThat(customLinkedList.isEmpty()).isTrue();

        customLinkedList.add("1");
        assertThat(customLinkedList.isEmpty()).isFalse();
    }

    @Test
    void get() {
        customLinkedList.add("1");
        customLinkedList.add("2");

        assertThat(customLinkedList.get(0).toString()).isEqualTo("1");
        assertThat(customLinkedList.get(1).toString()).isEqualTo("2");
    }

    @Test
    void getTotalTest() {
        customLinkedList.add("1");
        customLinkedList.add("2");

        assertThat(customLinkedList.getFirst().toString()).isEqualTo("1");
        assertThat(customLinkedList.getLast().toString()).isEqualTo("2");
    }

    @Test
    void peekTotalTest() {
        customLinkedList.add("1");
        customLinkedList.add("2");

        assertThat(customLinkedList.peek()).isEqualTo("1");
        assertThat(customLinkedList.peekFirst()).isEqualTo("1");
        assertThat(customLinkedList.peekLast()).isEqualTo("2");
    }

    @Test
    void remove() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");
//        System.out.println(customLinkedList.remove(1));
        customLinkedList.remove(1);
        assertThat(customLinkedList.toString()).isEqualTo("1,3");
    }

    @Test
    void removeTotalTest() {
//        javaLinkedList.add("1");
//        javaLinkedList.add("2");
//        javaLinkedList.add("3");
//        javaLinkedList.add("4");
//        javaLinkedList.add("5");
//        System.out.println(javaLinkedList.remove(0));
//        System.out.println(javaLinkedList.toString());
//        assertThat(javaLinkedList.toString()).isEqualTo("[2, 3, 4, 5]");

//        System.out.println(customLinkedList.remove(0));
//        assertThat(customLinkedList.toString()).isEqualTo("2,3,4,5");
//        System.out.println(customLinkedList.removeFirst());
//        assertThat(customLinkedList.toString()).isEqualTo("3,4,5");
//        System.out.println(customLinkedList.removeLast());
//        assertThat(customLinkedList.toString()).isEqualTo("3,4");
//        System.out.println(customLinkedList.remove("4"));
//        assertThat(customLinkedList.toString()).isEqualTo("3");

        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");
        customLinkedList.add("4");
        customLinkedList.add("5");
        customLinkedList.remove(0);
        assertThat(customLinkedList.toString()).isEqualTo("2,3,4,5");
        customLinkedList.removeFirst();
//        System.out.println(customLinkedList.toString());
        assertThat(customLinkedList.toString()).isEqualTo("3,4,5");
        customLinkedList.removeLast();
//        System.out.println(customLinkedList.toString());
        assertThat(customLinkedList.toString()).isEqualTo("3,4");
        customLinkedList.remove("4");
        assertThat(customLinkedList.toString()).isEqualTo("3");
    }

    @Test
    void pop() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        assertThat(customLinkedList.pop()).isEqualTo("1");
        assertThat(customLinkedList.toString()).isEqualTo("2");
    }

    @Test
    void push() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.push("3");
        assertThat(customLinkedList.toString()).isEqualTo("3,1,2");
    }

    @Test
    void removeAll() {
        javaLinkedList.add("1");
        javaLinkedList.add("2");

        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");

        customLinkedList.removeAll(javaLinkedList);
        assertThat(customLinkedList.toString()).isEqualTo("3");
    }

    @Test
    void set() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");

        customLinkedList.set(0, "4");
        assertThat(customLinkedList.toString()).isEqualTo("4,2,3");
    }

    @Test
    void subList() {
        customLinkedList.add("1");
        customLinkedList.add("2");

        javaLinkedList = customLinkedList.subList(0, 2);
        Object[] sArr = javaLinkedList.toArray();
        for (int i = 0; i < sArr.length; i++) {
            assertThat(sArr[i].toString()).isEqualTo(customLinkedList.get(i).toString());
        }
    }

}