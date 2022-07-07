package study.flab.learn.cyh.DataStructure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomStackUsingLinkedListTest {

    private CustomStackUsingLinkedList cStack;
    private Stack sStack;

    @BeforeEach
    void setUp() {
        cStack = new CustomStackUsingLinkedList();
        sStack = new Stack();
    }

    @Test
    void isEmpty() {
        assertThat(sStack.isEmpty()).isTrue();
        sStack.push("1번");
        sStack.push("2번");
        String sMsg = "[1번,2번]";
        assertThat(sStack.isEmpty()).isFalse();
        assertThat(sStack.toString().replaceAll(" ", "")).isEqualTo(sMsg);


        assertThat(cStack.isEmpty()).isTrue();
        cStack.push("1번");
        assertThat(cStack.isEmpty()).isFalse();
    }

    @Test
    void push() {
        cStack.push("1번");
        cStack.push("2번");
        String sMsg = "[1번,2번]";
        assertThat(cStack.toString()).isEqualTo(sMsg);
    }

    @Test
    void pop() {
        cStack.push("1번");
        cStack.push("2번");
        cStack.push("3번");
        String sMsg = "[1번,2번]";
        cStack.pop();
        assertThat(cStack.toString()).isEqualTo(sMsg);
    }

    @Test
    void peek() {
        cStack.push("1번");
        cStack.push("2번");
        cStack.push("3번");
        String sMsg = "3번";
        assertThat(cStack.peek()).isEqualTo(sMsg);
    }
}