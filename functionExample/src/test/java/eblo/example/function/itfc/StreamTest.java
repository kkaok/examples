package eblo.example.function.itfc;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StreamTest {

    /*
     * 중간 연산 메서드
        스트림 필터링 : filter(), distinct()
        스트림 변환 : map(), flatMap()
        스트림 제한 : limit(), skip()
        스트림 정렬 : sorted()
        스트림 연산 결과 확인 : peek()
        타입변환: asDoubleStream(), asLongStream(), boxed()
     */
    
    /*
        최종 연산 메서드
        요소의 출력 : forEach()
        요소의 소모 : reduce()
        요소의 검색 : findFirst(), findAny()
        요소의 검사 : anyMatch(), allMatch(), noneMatch()
        요소의 통계 : count(), min(), max()
        요소의 연산 : sum(), average()
        요소의 수집 : collect()
     */    
    
    
    @Test
    void sort() {
        String[] strArr = { "d1", "d4", "d3" };
        List<String> strList = Arrays.asList(strArr);
        strList.stream().sorted().forEach(System.out::print);
    }
    

}
