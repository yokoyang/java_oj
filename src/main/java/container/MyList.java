package container;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ReadThread implements Runnable {
    private List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (Integer i : this.list) {
            System.out.println(i);
        }
    }
}

class WriteThread implements Runnable {
    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        this.list.add(9);
    }
}

class TestCopyOnWriteArrayList {

    private void test() {
        List<Integer> tempList = Arrays.asList(1, 2);
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));

        System.out.println("copyList size:"+copyList.size());

    }
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("K1", "V1");
        map.put("K2", "V2");
        map.put("K3", "V3");
        new TestCopyOnWriteArrayList().test();
    }

}

public class MyList {
}
