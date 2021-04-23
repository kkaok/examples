package eblo.example.function.itfc;

public class Calculator implements MathInterface{

    public int multiple(int x, int y) {
        return x * y;
    }

    public int divid(int x, int y) {
        return x / y;
    }
}
