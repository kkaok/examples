package eblo.example.function.itfc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SysoutTest extends BaseSysout{

    @Test
    void sysout() {
        print("까오기님 안녕하세요.");
        assertEquals("까오기님 안녕하세요.", outputStreamCaptor.toString().trim());
    }

}
