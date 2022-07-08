package study.flab.learn.cyh.DataStructure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListToCQUATest {

    private List sList;
    private CustomArrayListToCQUA cList;

    @BeforeEach
    void setUp() {
        sList = new ArrayList<>();
        cList = new CustomArrayListToCQUA();
    }

    @Test
    void removeFirst() {
        cList.add("1번");
        cList.add("2번");
        cList.add("3번");
        String sMsg = "[1번,2번,3번]";
        assertThat(cList.toString().replaceAll(", null","")
                .replaceAll("null,","")
                .replaceAll(" ","")).isEqualTo(sMsg);

        sMsg = "1번";
        assertThat(cList.removeFirst().toString()).isEqualTo(sMsg);
        sMsg = "[2번,3번]";
        assertThat(cList.toString().replaceAll(", null","")
                .replaceAll("null,","")
                .replaceAll(" ","")).isEqualTo(sMsg);
    }

}