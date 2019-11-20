package designPattern.bridgePattern;

public class GreenCircle implements DrawAPI {
//    ConcreteImplementor：具体实现类
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
