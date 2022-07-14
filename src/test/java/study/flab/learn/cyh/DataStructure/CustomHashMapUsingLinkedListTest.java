package study.flab.learn.cyh.DataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapUsingLinkedListTest {

    private CustomHashMapUsingLinkedList cMap;
    private Map jMap;

    @BeforeEach
    void setUp() {
        cMap = new CustomHashMapUsingLinkedList();
        jMap = new HashMap();
    }

//    @Test
//    void when_테스트상태_Expect_기대결과() {
//    }

    @Test
    void when_매개변수_capacity로_CustomHashMap의_생성자를_선언한다_Expect_멤버변수_loadFactor와_buckets의_크기가_초기화된다() {
        //given
        float default_cMap_loadFactor = 0.75f;
        int default_cMap_capacity = 16;
        int default_cMap_tableSize = 0;
        assertThat(cMap.loadFactor).isEqualTo(default_cMap_loadFactor);
        assertThat(cMap.capacity).isEqualTo(default_cMap_capacity);
        assertThat(cMap.tableSize).isEqualTo(default_cMap_tableSize);

        //when
        cMap = new CustomHashMapUsingLinkedList(20);

        //then
        assertThat(cMap.table.length).isEqualTo(20);
    }

    @Test
    void when_매개변수_loadFactor로_CustomHashMap의_생성자를_선언한다_Expect_멤버변수_loadFactor와_buckets의_크기가_초기화된다() {
        //given
        float default_cMap_loadFactor = 0.75f;
        int default_cMap_capacity = 16;
        int default_cMap_tableSize = 0;
        assertThat(cMap.loadFactor).isEqualTo(default_cMap_loadFactor);
        assertThat(cMap.capacity).isEqualTo(default_cMap_capacity);
        assertThat(cMap.tableSize).isEqualTo(default_cMap_tableSize);

        //when
        cMap = new CustomHashMapUsingLinkedList(0.85f);

        //then
        assertThat(cMap.loadFactor).isEqualTo(0.85f);
    }

    @Test
    void when_put_메서드를_사용하여_값을_넣는다_Expect_정상처리() {
        //given
        float default_cMap_loadFactor = 0.75f;
        int default_cMap_capacity = 16;
        int key = 1;
        String value = "1번";

        //when
        cMap.put(key, value);

        //then
        assertThat(cMap.loadFactor).isEqualTo(default_cMap_loadFactor);
        assertThat(cMap.capacity).isEqualTo(default_cMap_capacity);
        assertThat(cMap.tableSize).isEqualTo(1);
    }

    @Test
    void when_get_메서드를_사용하여_값을_가져온다_Expect_정상처리() {
        //given
        int key1 = 1, key2 = 2;
        String value1 = "1번", value2 = "2번";

        //when
        cMap.put(key1, value1);
        cMap.get(key1);
        cMap.put(key2, value2);
        jMap.put(key1, value1);
        jMap.put(key2, value2);

        //then
        assertThat(cMap.get(key1)).isEqualTo(value1);
        assertThat(cMap.get(key2)).isEqualTo(value2);
        assertThat(jMap.get(key1)).isEqualTo(value1);
        assertThat(jMap.get(key2)).isEqualTo(value2);
    }

    @Test
    void when_put_메서드를_사용하여_동일한_key값으로_넣은후_value를_가져온다_Expect_마지막에_넣은_value가_리턴() {
        //given
        int uniqueKey = 1;
        String value1 = "1번", value2 = "2번";

        //when
        cMap.put(uniqueKey, value1);
        cMap.put(uniqueKey, value2);
        jMap.put(uniqueKey, value1);
        jMap.put(uniqueKey, value2);

        //then
        assertThat(cMap.get(uniqueKey)).isEqualTo(value2);
        assertThat(jMap.get(uniqueKey)).isEqualTo(value2);
    }

    @Test
    void when_put_메서드를_사용하여_max_capacity를_초과하는_값을_넣는다_Expect_HashMap의_tableSize가_2배로_재정의되며_정상출력() {
        //given
        assertThat(cMap.isEmpty()).isTrue();
        int count = 16;
        int key = 17;
        String value = "17번";

        //when
        for (int i = 1; i <= count; i++) {
            cMap.put(i, i+"번");
        }
        cMap.put(key, value);

        //then
        assertThat(cMap.get(key)).isEqualTo(value);
        assertThat(cMap.size()).isEqualTo(17);
    }

    @Test
    void when_HashMap이_비어있지_않을_경우_남은_entry를_확인하기위해_getEmptyEntry메소드를_호출_Expect_몇개의_entry가_비어있는지_확인가능() {
        //given
        int count = 16;

        //when
        for (int i = 1; i <= count; i++) {
            cMap.put(i, i+"번");
        }
        int emptyEntry = cMap.capacity - count;

        //then
        assertThat(cMap.getEmptyEntry()).isEqualTo(emptyEntry);
    }

    @Test
    void when_HashTable에_넣지않은_값을_get함수를_통해_확인_Expect_리턴값으로_null_확인() {
        //given
        int key1 = 1, key2 = 2;
        String value1 = "1번";

        //when
        cMap.put(key1, value1);
        jMap.put(key1, value1);

        //then
        assertThat(cMap.get(key2)).isEqualTo(null);
        assertThat(jMap.get(key2)).isEqualTo(null);
    }

    @Test
    void when_테스트상태_Expect_기대결과() {
        //given
        int count = 16;

        //when
        for (int i = 1; i <= count; i++) {
            cMap.put(i, i+"번");
        }
        for (int i = 1; i <= count; i++) {
            jMap.put(i, i+"번");
        }

        //then
        assertThat(cMap.entrySet().size()).isEqualTo(count);
        assertThat(jMap.entrySet().size()).isEqualTo(count);
    }


//    @Test
//    void when_테스트상태_Expect_기대결과() {
//
//    }
}