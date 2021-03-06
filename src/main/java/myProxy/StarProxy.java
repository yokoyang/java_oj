package myProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StarProxy implements InvocationHandler {
    // 目标类，也就是被代理对象
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里可以做增强
        System.out.println("收钱");
        Object result = method.invoke(target, args);
        System.out.println("演出结束");
        return result;
    }

    // 生成代理类
    public Object CreatProxiedObj() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{Star.class}, this);
    }

    public static void main(String[] args) {
        Star ldh = new LiuDeHua();
        StarProxy proxy = new StarProxy();
        proxy.setTarget(ldh);
        Star star = (Star) proxy.CreatProxiedObj();
        star.dance("a");
        star.sing("b");
    }
}
