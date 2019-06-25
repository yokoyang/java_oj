package Algorithm.shortestPath;

import java.lang.reflect.AccessibleObject;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
