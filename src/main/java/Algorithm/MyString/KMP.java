package Algorithm.MyString;

public class KMP {
    private static int KmpSearch(char[] s, char[] p) {
        int i = 0;
        int j = 0;
        int sLen = s.length;
        int pLen = p.length;
        int[] next = new int[p.length];
        getNext(p, next);
        while (i < sLen && j < pLen) {
            //①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else {
                //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }
        if (j == pLen)
            return i - j;
        else
            return -1;
    }

    private static void getNext(char[] p, int[] next) {
        int pLen = p.length;
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < pLen - 1) {
            //p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p[j] == p[k]) {
                ++k;
                ++j;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
    }

    public static void main(String[] args) {
//        char[] p = new char[]{'D', 'A', 'B', 'C', 'D', 'A', 'B', 'D', 'E'};
        String pS = "ababacd";
        String sS = "this is name ababacd";
        int r = KmpSearch(sS.toCharArray(), pS.toCharArray());
//        getNext(p, next);
//        for (int i = 0; i != next.length; i++) {
//            System.out.println(next[i]);
//        }
        System.out.println(r);
    }
}
