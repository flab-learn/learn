package study.flab.learn.cyh.DataStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    private static final int PARAMS_ARRAY_SIZE = 10;
    private final String[] params = new String[PARAMS_ARRAY_SIZE];
    private List<String> javaList;
    private CustomArrayList<String> customList;
    List<String> javaListAddAllTest1;
    List<String> customListAddAllTest1;

    @BeforeEach
    void setUp() {
        int idx = PARAMS_ARRAY_SIZE;
        while (idx-- > 0) {
            params[idx] = idx + "번";
        }
        javaList = new ArrayList<>();
        javaListAddAllTest1 = new ArrayList<>();
        javaListAddAllTest1.add(params[1]);
        javaListAddAllTest1.add(params[2]);

        customList = new CustomArrayList<>();
        customListAddAllTest1 = new ArrayList<>();
        customListAddAllTest1.add(params[1]);
        customListAddAllTest1.add(params[2]);
    }

    @Test
    @DisplayName("생성자를 확인합니다.")
    void constructor() {
        assertNotNull(javaList);
        assertNotNull(customList);
    }

    @Test
    @DisplayName("생성자를 확인합니다. 사이즈를 명시해줍니다. 배열의 크기를 확인합니다.")
    void constructorWithArgs() {
        javaList = new ArrayList<>(1);
        javaList.add(params[1]); javaList.add(params[2]);
        assertEquals(2, javaList.size());

        customList = new CustomArrayList<>(1);
        customList.add(params[1]); customList.add(params[2]);
        assertEquals(2, customList.size());
    }

    @Test
    @DisplayName("생성자를 확인합니다. 사이즈를 명시해줍니다. 배열이 비어있는지 확인합니다.")
    void isEmpty() {
        javaList = new ArrayList<>(1);
        javaList.add(params[1]);
        assertFalse(javaList.isEmpty());

        customList = new CustomArrayList<>(1);
        customList.add(params[1]);
        assertFalse(customList.isEmpty());
    }

    @Test
    @DisplayName("요소를 추가합니다.")
    void add() {
        javaList.add(params[1]); javaList.add(params[2]); javaList.add(params[3]);
        assertThat(javaList.size()).isEqualTo(3);

        customList.add(params[1]); customList.add(params[2]); customList.add(params[3]);
        assertThat(customList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("요소를 추가합니다. 요소가 추가될 인덱스를 명시해줍니다.")
    void addWithIndex() {
        javaList.add(params[1]); javaList.add(params[2]);
        assertTrue(javaList.toString().contains("1번, 2번"));
        javaList.add(0, params[3]);
        javaList.add(1, params[4]);
        assertTrue(javaList.toString().contains("3번, 4번, 1번, 2번"));
        javaList.add(4, params[5]);
        assertTrue(javaList.toString().contains("3번, 4번, 1번, 2번, 5번"));

        customList.add(params[1]); customList.add(params[2]);
        assertTrue(customList.toString().contains("1번, 2번"));
        customList.add(0, params[3]);
        assertTrue(customList.toString().contains("3번, 1번, 2번"));
        customList.add(1, params[4]);
        assertTrue(customList.toString().contains("3번, 4번, 1번, 2번"));
        customList.add(4, params[5]);
        assertTrue(customList.toString().contains("3번, 4번, 1번, 2번, 5번"));
    }

    @Test
    @DisplayName("요소를 다건 추가합니다. Collection 타입을 입력받습니다.")
    void addAll() {
        javaList.addAll(javaListAddAllTest1);
        assertTrue(javaList.toString().contains("1번, 2번"));

        customList.addAll(customListAddAllTest1);
        assertTrue(customList.toString().contains("1번, 2번"));
    }

    @Test
    @DisplayName("요소를 다건 추가합니다. Collection 타입을 입력받습니다. 요소가 추가될 인덱스를 명시해줍니다.")
    void addAllWithIndex() {
        javaList.add(params[3]);
        javaList.add(params[3]);
        javaList.add(params[3]);
        javaList.add(params[4]);
        javaList.add(params[5]);
        javaList.add(params[6]);
        javaList.add(params[7]);
        javaList.add(params[8]);
        javaList.add(params[9]);
        javaList.addAll(5, javaListAddAllTest1);
//        System.out.println(javaList.toString());
        assertTrue(javaList.toString().contains("3번, 3번, 3번, 4번, 5번, 1번, 2번, 6번, 7번, 8번, 9번"));

        customList.add(params[3]);
        customList.add(params[3]);
        customList.add(params[4]);
        customList.add(params[4]);
        customList.add(params[5]);
        customList.add(params[5]);
        customList.add(params[6]);
        customList.add(params[6]);
        customList.add(params[7]);
        customList.add(params[8]);
        customList.add(params[9]);
        customList.addAll(5, customListAddAllTest1);
        System.out.println(customList.toString());
//        assertTrue(customList.toString().contains("3번, 3번, 3번, 4번, 5번, 1번, 2번, 6번, 7번, 8번, 9번"));
    }

    @Test
    @DisplayName("지정한 객체가 있는 첫 번째 요소의 위치를 반환합니다. 만일 없을 경우 -1을 반환합니다")
    void indexOf() {
        javaList.add(null);
        javaList.add(params[1]);
        assertThat(javaList.indexOf(null)).isEqualTo(0);
        assertThat(javaList.indexOf("1번")).isEqualTo(1);
        assertThat(javaList.indexOf("99번")).isEqualTo(-1);

        customList.add(null);
        customList.add(params[1]);
        assertThat(customList.indexOf(null)).isEqualTo(0);
        assertThat(customList.indexOf("1번")).isEqualTo(1);
        assertThat(customList.indexOf("99번")).isEqualTo(-1);
    }

    @Test
    @DisplayName("지정한 객체가 컬렉션에 있는지 확인합니다.")
    void contains() {
        javaList.add(null);
        javaList.add(params[1]);
        assertTrue(javaList.contains(null));
        assertTrue(javaList.contains("1번"));
        assertFalse(javaList.contains("99번"));

        customList.add(null);
        customList.add(params[1]);
        assertTrue(customList.contains(null));
        assertTrue(customList.contains("1번"));
        assertFalse(customList.contains("99번"));
    }

    @Test
    @DisplayName("지정한 모든 객체가 컬렉션에 있는지 확인합니다.")
    void containsAll() {
        javaList.add(params[1]);
        javaList.add(params[2]);
        javaList.add(params[3]);
        assertTrue(javaList.containsAll(javaListAddAllTest1));

        customList.add(params[1]);
        customList.add(params[2]);
        customList.add(params[3]);
        assertTrue(customList.containsAll(customListAddAllTest1));
    }

    @Test
    @DisplayName("지정된 위치에 저장되어 있는 요소를 반환합니다.")
    void get() {
        javaList.add(params[1]);
        javaList.add(params[2]);
        assertThat(javaList.get(0)).isEqualTo("1번");
        assertThat(javaList.get(1)).isEqualTo("2번");

        customList.add(params[1]);
        customList.add(params[2]);
        assertThat(customList.get(0)).isEqualTo("1번");
        assertThat(customList.get(1)).isEqualTo("2번");
    }

    @Test
    @DisplayName("지정된 위치에 있는 요소를 지정된 요소로 바꿉니다.")
    void set() {
        javaList.add(params[1]);
        javaList.add(params[2]);
        assertTrue(javaList.toString().contains("1번, 2번"));
        javaList.set(0, params[3]);
        javaList.set(1, params[4]);
        assertTrue(javaList.toString().contains("3번, 4번"));

        customList.add(params[1]);
        customList.add(params[2]);
        assertTrue(customList.toString().contains("1번, 2번"));
        customList.set(0, params[3]);
        customList.set(1, params[4]);
        assertTrue(customList.toString().contains("3번, 4번"));
    }

    @Test
    @DisplayName("지정한 객체와 동일한 첫 번째 객체를 삭제합니다.")
    void removeWithArgs() {
        javaList.add(params[1]);
        javaList.add(params[2]);
        javaList.add(params[3]);
        assertTrue(javaList.toString().contains("1번, 2번, 3번"));
        assertThat(javaList.size()).isEqualTo(3);
        javaList.remove("1번");
        assertTrue(javaList.toString().contains("2번, 3번"));
        assertThat(javaList.size()).isEqualTo(2);
        javaList.remove("3번");
        assertTrue(javaList.toString().contains("2번"));
        assertThat(javaList.size()).isEqualTo(1);

        customList.add(params[1]);
        customList.add(params[2]);
        customList.add(params[3]);
        assertTrue(customList.toString().contains("1번, 2번, 3번"));
        assertThat(customList.size()).isEqualTo(3);
        customList.remove("1번");
        assertTrue(customList.toString().contains("2번, 3번"));
        assertThat(customList.size()).isEqualTo(2);
        customList.remove("3번");
        assertTrue(customList.toString().contains("2번"));
        assertThat(customList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("지정한 객체와 동일한 n 번째 객체를 삭제합니다.")
    void removeWithIndex() {
        javaList.add(params[1]);
        javaList.add(params[2]);
        javaList.add(params[3]);
        assertTrue(javaList.toString().contains("1번, 2번, 3번"));
        assertThat(javaList.size()).isEqualTo(3);
        javaList.remove(1);
        assertTrue(javaList.toString().contains("1번, 3번"));
        assertThat(javaList.size()).isEqualTo(2);
        javaList.remove(1);
        assertTrue(javaList.toString().contains("1번"));
        assertThat(javaList.size()).isEqualTo(1);

        customList.add(params[1]);
        customList.add(params[2]);
        customList.add(params[3]);
        assertTrue(customList.toString().contains("1번, 2번, 3번"));
        assertThat(customList.size()).isEqualTo(3);
        customList.remove(1);
        assertTrue(customList.toString().contains("1번, 3번"));
        assertThat(customList.size()).isEqualTo(2);
        customList.remove(0);
        assertTrue(customList.toString().contains("3번"));
        assertThat(customList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("컬렉션 내의 모든 객체 제거")
    void removeAll() {
        javaList.add(params[1]);
        javaList.add(params[2]);
        javaList.add(params[3]);
        assertTrue(javaList.toString().contains("1번, 2번, 3번"));
        assertThat(javaList.size()).isEqualTo(3);
        javaList.removeAll(javaListAddAllTest1);
        assertTrue(javaList.toString().contains("3번"));

        customList.add(params[1]);
        customList.add(params[2]);
        customList.add(params[3]);
        assertTrue(customList.toString().contains("1번, 2번, 3번"));
        assertThat(customList.size()).isEqualTo(3);
        customList.removeAll(customListAddAllTest1);
        assertTrue(customList.toString().contains("3번"));
    }

    @Test
    @DisplayName("입력받은 index 범위 내의 요소를 컬렉션으로 반환")
    void subList() {
        javaList.add(params[1]);
        javaList.add(params[2]);
        javaList.add(params[3]);
        assertTrue(javaList.toString().contains("1번, 2번, 3번"));
        assertThat(javaList.size()).isEqualTo(3);
        List<String> javaListAddAllTest1 = javaList.subList(1, 3); // 1 <= x < 3
        assertTrue(javaListAddAllTest1.toString().contains("2번, 3번"));

        customList.add(params[1]);
        customList.add(params[2]);
        customList.add(params[3]);
        assertTrue(customList.toString().contains("1번, 2번, 3번"));
        assertThat(customList.size()).isEqualTo(3);
        CustomArrayList<String> customListAddAllTest2 = customList.subList(1, 3); // 1 <= x < 3
        assertTrue(customListAddAllTest2.toString().contains("2번, 3번"));
    }

}
