package designPattern.decorator;

public class TVDecorator extends BaseDecorator {
    public TVDecorator(IDecorator decorator) {
        super(decorator);
    }
    @Override
    public void decorate(){
        super.decorate();
        System.out.println("电视机装饰。。。");

    }
}
