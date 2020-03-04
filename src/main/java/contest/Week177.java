package contest;

import java.text.SimpleDateFormat;
import java.util.*;

public class Week177 {
    public static void main(String[] args) {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));
//        Week177 week177 = new Week177();
//        System.out.println(week177.largestMultipleOfThree(new int[]{8, 6, 7, 1, 0, 1, 2}));
//        int[] res = week177.closestDivisors(123);
//        for (int n : res) {
//            System.out.println(n);
//        }
    }

    public int[] closestDivisors(int num) {
        int n2 = num + 1, n3 = num + 2;
        int end = (int) Math.ceil(Math.pow(num, 0.5));
        int o;
        int m = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (int i = 1; i <= end; i++) {
            if (n2 % i == 0) {
                o = n2 / i;
                if ((o - i) < m) {
                    m = o;
                    res = new int[]{i, o};
                }
            } else if (n3 % i == 0) {
                o = n3 / i;
                if ((o - i) < m) {
                    m = o;
                    res = new int[]{i, o};
                }
            }
        }
        return res;
    }

    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        ArrayList<Integer> mod1List = new ArrayList<>();
        ArrayList<Integer> mod2List = new ArrayList<>();
        int sum = 0;

        for (int n : digits) {
            if (mod1List.size() < 2 && n % 3 == 1) {
                mod1List.add(n);
            } else if (mod2List.size() < 2 && n % 3 == 2) {
                mod2List.add(n);
            }
            sum += n;
        }
        int remain = sum % 3;
        if (remain == 0) {
            return getResult(digits);
        }
        if (remain == 1) {
            if (mod1List.size() >= 1) {
                return getResult(digits, mod1List.get(0));
            }
            if (mod2List.size() >= 2) {
                return getResult(digits, mod2List.get(0), mod2List.get(1));
            }
        }
        if (remain == 2) {
            if (mod2List.size() >= 1) {
                return getResult(digits, mod2List.get(0));
            }
            if (mod1List.size() >= 2) {
                return getResult(digits, mod1List.get(0), mod1List.get(1));
            }
        }
        return "";
    }

    private String getResult2(int[] digits, Integer... banned) {
        HashSet<Integer> bannedSet = new HashSet<>(Arrays.asList(banned));
        StringBuilder builder = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (bannedSet.contains(i)) continue;
            builder.append(digits[i]);
        }
        String result = builder.toString();
        if (result.length() > 0 && result.charAt(0) == '0') return "0"; // Remove leading 0 case [0,..., 0]
        return result;
    }

    String getResult(int[] nums, Integer... banned) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(banned));
        for (int i = nums.length - 1; i >= 0; i--) {
            int pos = b.indexOf(nums[i]);
            if (pos >= 0) {
                b.remove(pos);
                continue;
            }
            sb.append(nums[i]);
        }
        String res = sb.toString();
        if (res.length() > 0 && res.charAt(0) == '0') {
            return "0";
        }
        return res;
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean res = true;
        Queue<Integer> pq = new ArrayDeque<>();
        pq.add(0);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!pq.isEmpty()) {
            int size = pq.size();
            while (size-- > 0) {
                int now = pq.poll();
                int l = leftChild[now], r = rightChild[now];
                if (l >= 0) {
                    if (visited.contains(l)) {
                        return false;
                    }
                    pq.offer(l);
                    visited.add(l);
                }
                if (r >= 0) {
                    if (visited.contains(r)) {
                        return false;
                    }
                    pq.offer(r);
                    visited.add(r);

                }
            }
        }
        if (visited.size() < n) {
            return false;
        }
        return true;
    }

}
