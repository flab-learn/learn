package study.flab.learn.cyh.DataStructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;

class CustomLinkedListTest {

    private String[] params = new String[10];
    private CustomLinkedList customLinkedList;
    private Collection javaLinkedList;

    @BeforeEach
    void setUp() {
        javaLinkedList = new LinkedList();
        customLinkedList = new CustomLinkedList();

        for (int i = 0; i < 10; i++) {
            params[i] = i + "번";
        }
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

}