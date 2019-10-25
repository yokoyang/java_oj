package designPattern.decorator;

public class MyDecorator {

    public static void main( String[] args )
    {
        IDecorator decorator = new Decorator();
        IDecorator curtainDecorator = new CurtainDecorator(decorator);
        curtainDecorator.decorate();

        IDecorator tvDecorator = new TVDecorator(curtainDecorator);
        tvDecorator.decorate();
    }
}
