package Algorithm;

import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    static class TimerTaskDemo extends TimerTask{
        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("这是java.util.TimeTask在执行任务,本次执行时间是："+sdf.format(new Date()));
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //创建Timer对象，并调用schedule方法（有四个重载方法）
        Timer t = new Timer();
        //传递参数。第一个参数是TimerTask对象（指定要执行的任务）,第二个参数是延迟时间，第三个参数是时间间隔（毫秒）
        t.schedule(new TimerTaskDemo(),0,5000);
        Thread.sleep(6000);
        System.out.println("a");
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }

    private static int solve(Scanner scanner) {
        int N = scanner.nextInt();
        int Q = scanner.nextInt();
        String have = scanner.next();
        int sum = 0;
        List<HashMap<Character, Integer>> recordList = new ArrayList<>();
        char[] allCharList = have.toCharArray();
        for (int i = 0; i < allCharList.length; i++) {
            HashMap<Character, Integer> record = new HashMap<>();
            if (i != 0) {
                record.putAll(recordList.get(recordList.size() - 1));
            }
            int t = record.getOrDefault(allCharList[i], 0);
            record.put(allCharList[i], ++t);
            recordList.add(record);
        }
        for (int i = 1; i <= Q; i++) {
            int counter = 0;
            int Li = scanner.nextInt();
            int Ri = scanner.nextInt();
            Map<Character, Integer> record = recordList.get(Ri - 1);
            for (Map.Entry entry : record.entrySet()) {
                Character letter = (Character) entry.getKey();
                int frequence = (int) entry.getValue();
                int before = 0;
                if (Li > 1) {
                    before = recordList.get(Li - 2).getOrDefault(letter, 0);

                }
                if ((frequence - before) % 2 != 0) {
                    //odd
                    counter++;
                }
                if (counter > 1) {
                    break;
                }
            }
            if (counter <= 1) {
                sum++;
            }

        }
        return sum;
    }

}
