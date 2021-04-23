package eblo.example.function.itfc;

public interface MathAddInterface {

    /**
     * default method 선언 
     * @param x
     * @param y
     * @return
     */
    default int add(int x, int y) {
        return x+y;
    }

}
