package Algorithm.shortestPath;

import java.lang.reflect.AccessibleObject;
import java.util.*;

class Solution {
    class Graph {
        int v;
        LinkedList<Edge> ajd[];

        Graph(int v) {
            this.v = v;
            this.ajd = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                this.ajd[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t, int w) {
            this.ajd[s].add(new Edge(s, t, w));
        }
    }

    class Edge {
        int sid;
        int tid;
        int w;

        Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) {
                prices.put(f[0], new HashMap<>());
            }
            prices.get(f[0]).put(f[1], f[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[]{0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.offer(new int[]{price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }

    class Vertex implements Comparable<Vertex> {
        int id;
        int dist;

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex v) {
            if (this.dist - v.dist < 0) {
                return -1;
            }
            return 1;
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Graph g = new Graph(N);
        for (int[] edge : times) {
            g.addEdge(edge[0] - 1, edge[1] - 1, edge[2]);
        }
        int startId = K - 1;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Vertex[] vs = new Vertex[N];
        for (int i = 0; i < N; i++) {
            vs[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        vs[startId].dist = 0;
        pq.offer(vs[startId]);
        Vertex minVertex;
        while (!pq.isEmpty()) {
            minVertex = pq.poll();
            if (vs[minVertex.id].dist < minVertex.dist) {
                continue;
            }
            for (int i = 0; i < g.ajd[minVertex.id].size(); i++) {
                Edge e = g.ajd[minVertex.id].get(i);
                Vertex nextV = vs[e.tid];
                if (e.w + minVertex.dist < nextV.dist) {
                    nextV.dist = e.w + minVertex.dist;
                    pq.offer(nextV);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < vs.length; i++) {
            if (vs[i].dist == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, vs[i].dist);
        }
        return result;
    }
}
