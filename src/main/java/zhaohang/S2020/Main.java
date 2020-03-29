package zhaohang.S2020;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static class Mu implements Comparable<Mu> {
        int l;
        int w;
        boolean done = false;

        Mu() {
        }

        public void setDone() {
            this.done = true;
        }

        @Override
        public int compareTo(Mu o) {
            if (l == o.l) {
                return Integer.compare(w, o.w);
            }
            return Integer.compare(l, o.l);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            Mu[] infos = new Mu[N];
            for (int i = 0; i < N; i++) {
                infos[i] = new Mu();
                infos[i].l = sc.nextInt();
            }
            for (int i = 0; i < N; i++) {
                infos[i].w = sc.nextInt();
            }
            Arrays.sort(infos);
            int cost = 0;
            int nw;
            for (int k = 0; k < N; k++) {
                if (infos[k].done) {
                    continue;
                }
                nw = infos[k].w;
                cost++;
                for (int j = k + 1; j < N; j++) {
                    if (infos[j].w >= nw && !infos[j].done) {
                        nw = infos[j].w;
                        infos[j].setDone();
                    }
                }
            }
            System.out.println(cost);
        }

    }
}
