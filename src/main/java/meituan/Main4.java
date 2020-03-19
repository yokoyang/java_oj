package meituan;

import java.lang.reflect.Array;
import java.util.*;

public class Main4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int S = sc.nextInt();
        int[][] graph = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u][v] = w;
            graph[v][u] = w;
        }
        int K = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            graph[i][i] = 0;
        }
        for (int k = 1; k <= N; k++) {
            for (int u = 1; u <= N; u++) {
                for (int v = 1; v <= N; v++) {
                    graph[u][v] = Math.min(graph[u][k] + graph[k][v], graph[u][v]);
                }
            }
        }
        HashSet<Integer> visit = new HashSet<>();
        visit.add(S);
        Queue<Integer> p = new ArrayDeque<>();
        int[] left = new int[N + 1];
        Arrays.fill(left, -1);
        left[S] = K;
        p.offer(S);
        int res = 0;

        while (!p.isEmpty()) {
            int size = p.size();
            while (size-- > 0) {
                int now = p.poll();

                for (int i = 1; i <= N; i++) {
                    if (now == i) {
                        continue;
                    }
                    if (left[now] - graph[now][i] >= 0) {
                        if (left[now] - graph[now][i] == 0) {
                            res++;
                        } else {
                            p.offer(i);
                            visit.add(i);
                            left[i] = left[now] - graph[now][i];
                        }

                    } else if (left[now] > 0) {
                        res++;
                    }
                }
            }
        }

        System.out.println(res);
    }
}
