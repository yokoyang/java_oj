package myThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TwoThreadPrint {

    static class VowelPrinter extends Thread {
        private BlockingQueue<Character> bq;

        public VowelPrinter(BlockingQueue<Character> bq) {
            super();

            this.bq = bq;
        }

        @Override
        public void run() {
            super.run();
            String s = "aeiou";
            for (Character c : s.toCharArray()) {
                try {
                    bq.put(c);
                    System.out.print(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class ConsonantPrinter extends Thread {
        private BlockingQueue<Character> bq;

        public ConsonantPrinter(BlockingQueue<Character> bq) {
            super();
            this.bq = bq;
        }

        @Override
        public void run() {
            super.run();
            String s = "aeiou";
            char ch = 'a';
            List<Character> toPrint = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                char now = (char) (ch + i);
                if (s.indexOf(now) == -1) {
                    toPrint.add(now);
                }
            }
            Character[] toPrintList = (Character[]) toPrint.toArray();
            for (int i = 0; i < toPrintList.length; i++) {
                try {
                    bq.take();
                    bq.put(toPrintList[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class NumberPrinter extends Thread {
        private BlockingQueue<Object> bp;

        public NumberPrinter(BlockingQueue<Object> bp) {
            super();
            this.bp = bp;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 1; i < 53; i++) {
                try {
                    System.out.print(i);
                    bp.put(i);
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class LetterPrinter extends Thread {
        private BlockingQueue<Object> bp;

        public LetterPrinter(BlockingQueue<Object> bp) {
            super();
            this.bp = bp;
        }

        @Override
        public void run() {
            super.run();
            char ch = 'A';
            for (int i = 0; i < 26; i++) {
                try {
                    bp.take();
                    bp.take();
                    System.out.print((char) (ch + i));
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

//    private final Lock lock = new ReentrantLock();
//    private final Condition con = lock.newCondition();

    public static void main(String[] args) {
//        BlockingQueue<Object> bp = new ArrayBlockingQueue<>(2);
//
//        LetterPrinter t1 = new LetterPrinter(bp);
//        NumberPrinter t2 = new NumberPrinter(bp);
//
//        t1.start();
//        t2.start();
        BlockingQueue<Character> bp = new ArrayBlockingQueue<>(1);

        VowelPrinter t1 = new VowelPrinter(bp);
        ConsonantPrinter t2 = new ConsonantPrinter(bp);

        t1.start();
        t2.start();
    }

}
