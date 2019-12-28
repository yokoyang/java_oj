package myReflect.LoaderforName;

//import java.lang.reflect.InvocationTargetException;

public class ClassloaderAndForNameTest {
//    https://blog.csdn.net/qq_27093465/article/details/52262340

//    ClassLoader和Class.forName( )的比较
//
//ClassLoader是将类的.class文件加载到jvm中，不会执行static内容，只有在newInstance时才会执行static块。
//Class.forName( )不仅是将类的.class文件加载到jvm中，还会对类进行解释，执行static内容。
//java进行数据库连接时为什么要写Class.forName("xxx")
//
//JDBC是桥接模式的典型应用，Class.forName(String className)的作用有两个，
// 一个是将CLASSPATH下指定名字的.class文件加载到jvm中，第二个是初始化这个类。
//Class.forName("com.mysql.jdbc.Driver")的作用实际上就是调用DriverManager的registerDriver方法注册一个mysql的JDBC驱动（Driver）而已，
// Driver继承NonRegisteringDriver.java，NonRegisteringDriver.java实现了JDK提供的Driver接口，
//  这个Driver提供了若干数据库连接的方法，每个不同的数据库连接类都必须实现它，并重写和具体的数据库连接的算法。
//JDK不负责和数据库连接打交道，也没必要，只提供一个具体的接口Driver，告诉所有第三方，要连接数据库，就去实现这个接口，然后通过DriverManager注册一下，
// 到时候连接某个数据库的时候，你已经在我这里注册了，我会调用你注册进来的Driver里面的方法去对指定数据库进行连接的。
//我们仅仅需要初始化这个类，执行static块的内容就行了，不需要使用这个类内部的API，因此没必要对其进行实例化。

    public static void main(String[] args) throws Exception {
        String wholeNameLine = "myReflect.LoaderforName.Line";
        String wholeNamePoint = "myReflect.LoaderforName.Point";
        System.out.println("下面是测试Classloader的效果");
        testClassloader(wholeNameLine, wholeNamePoint);
        System.out.println("----------------------------------");
        System.out.println("下面是测试Class.forName的效果");
        testForName(wholeNameLine, wholeNamePoint);

    }

    /**
     * classloader
     */
    private static void testClassloader(String wholeNameLine, String wholeNamePoint) throws Exception {
        Class<?> line;
        Class<?> point;
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        line = loader.loadClass(wholeNameLine);
        point = loader.loadClass(wholeNamePoint);
//            line.getDeclaredConstructor().newInstance();
        //demo = ClassloaderAndForNameTest.class.getClassLoader().loadClass(wholeNamePoint);//这个也是可以的
        System.out.println("line   " + line.getName());
        System.out.println("point   " + point.getName());
    }

    /**
     * Class.forName
     */
    private static void testForName(String wholeNameLine, String wholeNamePoint) {

        try {
            Class line = Class.forName(wholeNameLine);
            Class point = Class.forName(wholeNamePoint);
            System.out.println("line   " + line.getName());
            System.out.println("point   " + point.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}