package kickStart.G2020;

import java.util.Scanner;

import java.util.*;

public class Solution {
    private static int solve(Scanner scanner) {
        int N = scanner.nextInt();
        int dollars = scanner.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(scanner.nextInt());
        }
        int counter = 0;
        while (dollars >= 0 && !pq.isEmpty()) {
            int each = pq.poll();
            dollars -= each;
            if (dollars >= 0) {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(scanner)));
        }
    }
}
