package study.flab.learn.cyh.DataStructure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


class CustomStackUsingArrayListTest {

    private CustomStackUsingArrayList csua;

    private static final int PARAMS_ARRAY_SIZE = 10;
    private CustomArrayListToCSUA arrayListToCSUA;

    @BeforeEach
    void setUp() {
        csua = new CustomStackUsingArrayList();
        arrayListToCSUA = new CustomArrayListToCSUA();

        for (int i = 1; i <= 9; i++) {
            arrayListToCSUA.add(i + "번");
        }
    }

    @Test
    void add() {
        String sMsg = "1번, 2번, 3번, 4번, 5번, 6번, 7번, 8번, 9번";
        assertThat(arrayListToCSUA.toString()).contains(sMsg);

        sMsg = "1번, 2번, 3번, 4번, 5번, 6번, 7번, 8번, 9번, 10번";
        arrayListToCSUA.add("10번");
        assertThat(arrayListToCSUA.toString()).contains(sMsg);
    }

    @Test
    void addWithIndex() {
        String sMsg = "1번, 2번, 3번, 4번, 5번, 6번, 7번, 8번, 9번";
        assertThat(arrayListToCSUA.toString()).contains(sMsg);

        arrayListToCSUA.add(8,"10번");
        sMsg = "1번, 2번, 3번, 4번, 5번, 6번, 7번, 8번, 10번, 9번";
        assertThat(arrayListToCSUA.toString()).contains(sMsg);

        arrayListToCSUA.add(0,"11번");
        sMsg = "11번, 1번, 2번, 3번, 4번, 5번, 6번, 7번, 8번, 10번, 9번";
        assertThat(arrayListToCSUA.toString()).contains(sMsg);
    }

    @Test
    void indexOf() {
        assertThat(arrayListToCSUA.indexOf(1)).isEqualTo(-1);

        arrayListToCSUA = new CustomArrayListToCSUA();
        arrayListToCSUA.add(null);
        assertThat(arrayListToCSUA.indexOf(null)).isEqualTo(0);
    }

    @Test
    void isEmpty() {
        csua.isEmpty();
        assertThat(csua.isEmpty()).isTrue();
    }

    @Test
    void push() {
        String pMsg = "10번";
        csua.push(pMsg);
        assertThat(csua.isEmpty()).isFalse();

        String sMsg = "10번";
        assertThat(csua.toString()).contains(sMsg);
    }

    @Test
    void pop() {
    }

    @Test
    void peek() {
    }
}