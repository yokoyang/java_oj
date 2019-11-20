package designPattern.bridgePattern;

public class RedCircle implements DrawAPI {
//    ConcreteImplementor：具体实现类
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
