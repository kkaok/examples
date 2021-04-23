package eblo.example.function.itfc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    
    private void print(String output) {
        System.out.println(output);
    }
    
    @Test
    @DisplayName("default method test")
    void calculator() {
        Calculator calculator = new Calculator();
        assertThat(calculator.add(1, 2)).isEqualTo(103);
        assertThat(calculator.minus(1, 2)).isEqualTo(-1);
        assertThat(calculator.multiple(1, 2)).isEqualTo(2);
        assertThat(calculator.divid(1, 2)).isEqualTo(0);
    }
    
    /**
     * 인자와 리턴값이 있는 경우 사용. 
     * apply
     */
    @Test
    void func() {
        Function<String, Integer> f = str -> Integer.parseInt(str);
        Integer result = f.apply("100");
        System.out.println(result);
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
}
