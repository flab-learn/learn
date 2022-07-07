package study.flab.learn.cyh.DataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.*;

class CustomQueueUsingLinkedListTest {

    private CustomQueueUsingLinkedList cQueue;
    private Queue sQueue;
    private String sMsg;

    @BeforeEach
    void setUp() {
        cQueue = new CustomQueueUsingLinkedList();
        sQueue = new LinkedList();
    }

    @Test
    void isEmpty() {
        assertThat(sQueue.isEmpty()).isTrue();
        sQueue.add("1번");
        assertThat(sQueue.isEmpty()).isFalse();

        assertThat(cQueue.isEmpty()).isTrue();
        cQueue.enqueue("1번");
        assertThat(cQueue.isEmpty()).isFalse();
    }

    @Test
    void enqueue() {
        sQueue.add("1번");
        sQueue.add("2번");
        sMsg = "[1번,2번]";
        assertThat(sQueue.toString().replaceAll(" ", "")).isEqualTo(sMsg);

        cQueue.enqueue("1번");
        cQueue.enqueue("2번");
        sMsg = "[1번,2번]";
        assertThat(cQueue.toString()).isEqualTo(sMsg);
    }

    @Test
    void dequeue() {
        sQueue.add("1번");
        sQueue.add("2번");
        sMsg = "[2번]";
        sQueue.remove("1번");
        assertThat(sQueue.toString().replaceAll(" ", "")).isEqualTo(sMsg);

        cQueue.enqueue("1번");
        cQueue.enqueue("2번");
        sMsg = "[2번]";
        cQueue.dequeue();
        assertThat(cQueue.toString()).isEqualTo(sMsg);
    }

    @Test
    void peek() {
        sQueue.add("1번");
        sQueue.add("2번");
        sMsg = "1번";
        assertThat(sQueue.peek().toString().replaceAll(" ", "")).isEqualTo(sMsg);

        cQueue.enqueue("1번");
        cQueue.enqueue("2번");
        sMsg = "1번";
        assertThat(cQueue.peek().toString()).isEqualTo(sMsg);
    }
}