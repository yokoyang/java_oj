package wy2019;

import java.util.Scanner;

public class WY_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String process = scanner.next();
        String dir = "NESW";
        int ans = 0;
        for (int i = 0; i != n; i++) {
            ans = (ans + (process.charAt(i) == 'L' ? -1 : 1) + 4) % 4;
        }
        System.out.println(dir.charAt(ans));

    }
}
