package eblo.example.logback;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

public class LombokTestControllerTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testLoadedCustomLogbackConfig() throws Exception {
        LombokTestController controller = new LombokTestController();
        controller.test();
        this.outputCapture.expect(containsString("LombokTestController"));
    }

}
