package study.flab.learn.cyh.DataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

import static org.assertj.core.api.Assertions.*;


class CustomStackUsingArrayListTest {

    private CustomStackUsingArrayList cStack;
    private Stack rStack;

    private static final int PARAMS_ARRAY_SIZE = 10;
    private CustomArrayListToCSUA cArrayList;
    private ArrayList rArrayList;

    @BeforeEach
    void setUp() {
        cStack = new CustomStackUsingArrayList();
        rStack = new Stack();
        cArrayList = new CustomArrayListToCSUA();
        rArrayList = new ArrayList();
    }

    @Test
    void add() {
        cArrayList.add("1번");
        cArrayList.add("2번");
        cArrayList.add("3번");
        String sMsg = "1번, 2번, 3번";
        assertThat(cArrayList.toString()).contains(sMsg);

        sMsg = "1번, 2번, 3번, 4번";
        cArrayList.add("4번");
        assertThat(cArrayList.toString()).contains(sMsg);
    }

    @Test
    void addWithIndex() {
        cArrayList.add("1번");
        cArrayList.add("2번");
        cArrayList.add("3번");
        String sMsg = "1번, 2번, 3번";
        assertThat(cArrayList.toString()).contains(sMsg);

        cArrayList.add(0,"10번");
        sMsg = "10번, 1번, 2번, 3번";
        assertThat(cArrayList.toString()).contains(sMsg);

        cArrayList.add(4,"11번");
        sMsg = "10번, 1번, 2번, 3번, 11번";
        assertThat(cArrayList.toString()).contains(sMsg);
    }

    @Test
    void indexOf() {
        assertThat(cArrayList.indexOf(1)).isEqualTo(-1);

        cArrayList = new CustomArrayListToCSUA();
        cArrayList.add(null);
        assertThat(cArrayList.indexOf(null)).isEqualTo(0);
    }

    @Test
    void remove() {
        rArrayList.add("1번");
        rArrayList.add("2번");
        rArrayList.add("3번");
        rArrayList.remove(0);
        String sMsg = "2번, 3번";
        assertThat(rArrayList.toString()).contains(sMsg);
        assertThat(rArrayList.size()).isEqualTo(2);

        cArrayList.add("1번");
        cArrayList.add("2번");
        cArrayList.add("3번");
        sMsg = "2번, 3번";
        cArrayList.remove(0);
        assertThat(cArrayList.toString()).contains(sMsg);
        assertThat(cArrayList.size()).isEqualTo(2);
    }


    //=============================================
    @Test
    void isEmpty() {
        rStack.isEmpty();
        assertThat(rStack.isEmpty()).isTrue();

        cStack.isEmpty();
        assertThat(cStack.isEmpty()).isTrue();
    }

    @Test
    void push() {
        String pMsg = "10번";
        rStack.push(pMsg);
        assertThat(rStack.isEmpty()).isFalse();

        String sMsg = "10번";
        assertThat(rStack.toString()).contains(sMsg);
        assertThat(rStack.size()).isEqualTo(1);


        pMsg = "10번";
        cStack.push(pMsg);
        assertThat(cStack.isEmpty()).isFalse();

        sMsg = "10번";
        assertThat(cStack.toString()).contains(sMsg);
        assertThat(cStack.size()).isEqualTo(1);
    }

    @Test
    void pop() {
        rStack.add("1번");
        rStack.add("2번");
        rStack.add("3번");
        String sMsg = "3번";
        assertThat(Objects.equals(rStack.pop(), sMsg)).isTrue();
        assertThat(rStack.size()).isEqualTo(2);

        cStack.arrayStack.add("1번");
        cStack.arrayStack.add("2번");
        cStack.arrayStack.add("3번");
        assertThat(Objects.equals(cStack.pop(), sMsg)).isTrue();
        assertThat(cStack.size()).isEqualTo(2);

    }

    @Test
    void peek() {
        rStack.add("1번");
        rStack.add("2번");
        rStack.add("3번");
        String sMsg = "3번";
        assertThat(Objects.equals(rStack.peek(), sMsg)).isTrue();

        cStack.arrayStack.add("1번");
        cStack.arrayStack.add("2번");
        cStack.arrayStack.add("3번");
        assertThat(Objects.equals(cStack.peek(), sMsg)).isTrue();
    }
}