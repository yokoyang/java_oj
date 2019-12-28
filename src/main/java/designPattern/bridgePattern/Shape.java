package designPattern.bridgePattern;

public abstract class Shape {
//    Abstraction：抽象类

    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}