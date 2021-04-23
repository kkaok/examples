package eblo.example.function.itfc;

/**
 * @author hyunkim
 * 상속 및 override 
 */
public interface MathInterface extends MathAddInterface, MathMinusInterface{

    @Override
    default int add(int x, int y) {
        return x+y+100;
    }

}
