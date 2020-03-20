package alibaba;

import java.util.*;

public class Main {
    /*
1 1 1 2 2 2 2 2 1 1
   * */
    private static int result = Integer.MAX_VALUE;

    static int cards(int n, int[] cards) {
        int remainCards = 0;
        for (int v : cards) {
            remainCards += v;
        }
        bc(n, cards, remainCards, 0);
        return result;
    }

    static void bc(int n, int[] cards, int remainCards, int tmpResult) {
        if (remainCards == 0) {
            result = Math.min(result, tmpResult);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (canSix(cards, i)) {
                cards[i] -= 2;
                cards[i + 1] -= 2;
                cards[i + 2] -= 2;
                bc(n, cards, remainCards - 6, tmpResult + 1);
                cards[i] += 2;
                cards[i + 1] += 2;
                cards[i + 2] += 2;
            } else if (canFive(cards, i)) {
                for (int t = i; t <= i + 4; t++) {
                    cards[t] -= 1;
                }
                bc(n, cards, remainCards - 5, tmpResult + 1);
                for (int t = i; t <= i + 4; t++) {
                    cards[t] += 1;
                }
            } else if (canTwo(cards, i)) {
                cards[i] -= 2;
                bc(n, cards, remainCards - 2, tmpResult + 1);
                cards[i] += 2;

            } else if (cards[i] >= 1) {
                cards[i] -= 1;
                bc(n, cards, remainCards - 1, tmpResult + 1);
                cards[i] += 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = sc.nextInt();
        }
        cards(10, nums);
        System.out.println(result);
        sc.close();
    }

    private static boolean canSix(int[] cards, int start) {
        return start + 2 < cards.length && cards[start] >= 2 && cards[start + 1] >= 2 && cards[start + 2] >= 2;
    }

    static boolean canTwo(int[] cards, int start) {
        return start < cards.length && cards[start] >= 2;
    }

    static boolean canFive(int[] cards, int start) {
        return start + 4 < cards.length && cards[start] >= 1 && cards[start + 1] >= 1 && cards[start + 2] >= 1 && cards[start + 3] >= 1 && cards[start + 4] >= 1;
    }

}
