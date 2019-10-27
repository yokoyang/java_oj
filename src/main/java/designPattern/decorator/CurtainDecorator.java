package designPattern.decorator;


/**
 * 窗帘装饰类
 * @author admin
 *
 */
public class CurtainDecorator extends BaseDecorator{

    public CurtainDecorator(IDecorator decorator) {
        super(decorator);
    }

    /**
     * 窗帘具体装饰方法
     */
    @Override
    public void decorate() {
        super.decorate();
        System.out.println("窗帘装饰。。。");
    }

}
