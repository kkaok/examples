package eblo.example.function.itfc;

public interface MathMinusInterface {
    
    /**
     * default method 선언 
     * @param x
     * @param y
     * @return
     */
    default int minus(int x, int y) {
        return x-y;
    }
}
