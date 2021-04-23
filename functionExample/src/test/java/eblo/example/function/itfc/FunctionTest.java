package eblo.example.function.itfc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

/**
 * 
 * 람다 표현식 
 *  ( parameters ) -> expression body       // 인자가 여러개 이고 하나의 문장으로 구성
 *  ( parameters ) -> { expression body }   // 인자가 여러개 이고 여러 문장으로 구성
 *  () -> { expression body }               // 인자가 없고 여러 문장으로 구성
 *  () -> expression body                   // 인자가 없고 하나의 문장으로 구성
 * 출처 : https://dinfree.com/lecture/language/112_java_9.html#m2
 */
public class FunctionTest extends BaseSysout{

    /**
     * 인자와 리턴값이 있는 경우 사용. 
     * apply
     */
    @Test
    void func() {
        Function<String, Integer> f = str -> Integer.parseInt(str);
        Integer result = f.apply("100");
        assertEquals(result, 100);
    }

    /**
     * 인자는 있고 리턴값은 없다. 
     * accept
     */
    @Test
    void consumer() {
        String name = "까오기";
        Consumer<String> c = nm -> print(nm+"님 안녕하세요.");
        c.accept(name);
        assertEquals("까오기님 안녕하세요.", outputStreamCaptor.toString().trim());
    }

    /**
     * 인자는 없고 반환만 있다. 
     */
    @Test
    void supplier() {
        Supplier<String> s = () -> "고객 센터는 1234-1234 입니다.";
        String result = s.get();

        assertEquals(result, "고객 센터는 1234-1234 입니다.");
    }

    /**
     * 인자와 리턴값이 있는 경우 사용. 
     */
    @Test
    void operator() {
        UnaryOperator<String> u = str -> str + "님 안녕하세요.";
        String result = u.apply("까오기");
        assertEquals("까오기님 안녕하세요.", result);

        BinaryOperator<String> b = (str1, str2) -> str1 + "님, " + str2 + "님 안녕하세요.";
        String resultBi = b.apply("까오기", "까시기");
        assertEquals("까오기님, 까시기님 안녕하세요.", resultBi);
    }

    /**
     * 인자를 받아서 boolean 반환. 
     */
    @Test
    void predicate() {
        Predicate<String> p = str -> str.contains("까오기");
        boolean resultTrue = p.test("까오기님 안녕하세요."); // false
        assertTrue(resultTrue);
        boolean resultFalse = p.test("까시기님 안녕하세요."); // false
        assertFalse(resultFalse);
    }
}
