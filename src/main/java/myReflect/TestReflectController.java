package myReflect;

import java.lang.reflect.*;

/**
 * @description:反射
 */

/**
 * 创建一个user类
 * 可不在与main方法同一个类中创建
 */
class User {

    //定一个成员变量:姓名
    private String name;

    //无参构造
    public User() {
    }

    //有参构造
    public User(String name) {
        this.name = name;
    }

    //get方法
    public String getName() {
        return name;
    }

    //set方法
    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 测试Class类的函数和反射库中的函数
 */
public class TestReflectController {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        //获得User类的Class对象
        Class<?> cc = User.class;

        //下面这个是通过new出来的对象，并通过set给成员变量赋值
        User user1 = new User();
        user1.setName("hello");
        //下面这个是通过构造器的方式

        //1.有参构造器
        //先得到有参构造器的信息，再根据构造器的信息，由newInstance()函数创建一个User对象
        Constructor<?> constructor = cc.getConstructor(String.class);
        //这个constructor.newInstance方法就是反射包下java.lang.reflect.Constructor类下的newInstance方法
        User user2 = (User) constructor.newInstance("world");
        System.out.println(user1.getName());//hello
        System.out.println(user2.getName());//world

        //2.无参构造器
        Constructor<?> constructor1 = cc.getConstructor();
        //无参构造不能够像1一样赋值
        //User user3 = (User) constructor1.newInstance("熊本");
        User user3 = (User) constructor1.newInstance();
        user3.setName("熊本");
        System.out.println(user3.getName());//熊本

        //由无参构造器创建对象时，可不必获得构造器，直接由Class对象调用newInstance()方法。
        Class<?> cc2 = User.class;
        User user4 = (User) cc2.getDeclaredConstructor().newInstance();
        user4.setName("同学");
        System.out.println(user4.getName());//同学

        //3.下面2个输出语句可看出cc保存类信息，输出的是“class + 类名”。cc.newInstance()是具体类的对象。
        System.out.println(cc);//class myReflect.User
        System.out.println(cc.getDeclaredConstructor().newInstance());//myReflect.User@b4c966a

        //4.AccessibleObject、Field类
        //首先得到有参构造函数的信息，然后根据构造函数实例化一个对象。
        //由getDeclaredField()函数得到类里面的私有成员变量，访问私有成员变量要用setAccessible()函数设置访问权限。
        //Field类对象得到成员变量后还可以设置该变量的值，使用set()方法。
        Constructor<?> constructor2 = cc.getConstructor(String.class);
        User user5 = (User) constructor2.newInstance("改变前：100");
        Field field = cc.getDeclaredField("name");
        field.setAccessible(true);
        field.set(user5, "改变后：50");
        System.out.println(user5.getName());//改变后：50

        //5.Method.invoke
        //首先根据获得的构造函数信息实例化一个对象
        //然后由函数名获得类中的公有函数，getMethod("函数名")
        //invoke()方法执行由getMethod()获得的函数，这里获得的函数是getter()
        //对于获得的有参函数，invoke(对象)里只添加对象名。
        Constructor<?> constructor3 = cc.getConstructor(String.class);
        User user6 = (User) constructor3.newInstance("你好世界");
        //getName是User实体类中的方法
        System.out.println(cc.getMethod("getName").invoke(user6));//你好世界

        //对于获得到的无参函数，在调用getMethod()函数时，要在getMethod()中指定被获得函数的"函数名"和"参数类型"
        //并且在执行该函数（即调用invoke()函数时），要指定对象和参数类型的具体实例。
        User user7 = (User) cc.getDeclaredConstructor().newInstance();
        Method method = cc.getMethod("setName", String.class);
        method.invoke(user7, "你好哇，这个世界");
        System.out.println(user7.getName());//你好哇，这个世界

        //6.Modifier类
        //Class类中getModifiers()函数返回一个用于描述类，构造器，方法和域的修饰符的整形数值。、
        //调用Modifier.toString()方法将整型数值转变成字符串，也是就我们熟悉的public，private，static，final等修饰符。
        System.out.println(Modifier.toString(cc.getModifiers()));//无
        System.out.println(Modifier.toString(constructor.getModifiers()));//public
        System.out.println(Modifier.toString(field.getModifiers()));//private
        System.out.println(Modifier.toString(method.getModifiers()));//public

        //同时，Modifier类还有一些判断修饰符是不是某一类型的方法。
        System.out.println(Modifier.isPublic(cc.getModifiers()));
        System.out.println(Modifier.isPublic(constructor.getModifiers()));

    }
}
