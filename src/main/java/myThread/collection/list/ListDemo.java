package myThread.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDemo {
    public static void main(String[] args) {
//        使用这种方式，会抛出异常checkForComodification
//        List<String> list = new ArrayList<>();
//        for(int i=0;i<3;i++){
//            new Thread(() -> {
//                list.add(ListDemo.class.getSimpleName().substring(0,8));
//                System.out.println(list);
//            }).start();
//        }

//        第二种方案:使用Collections.synchronizedList
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        for(int i=0;i<3;i++){
//            new Thread(() -> {
//                list.add(ListDemo.class.getSimpleName().substring(0,8));
//                System.out.println(list);
//            }).start();
//        }
        //第三种方案。使用copyOnWriteArrayList
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(ListDemo.class.getSimpleName().substring(0, 8));
                System.out.println(list);
            }).start();
        }

    }
}
