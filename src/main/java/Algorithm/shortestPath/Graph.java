package Algorithm.shortestPath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
    //顶点个数
    private int v;
    private LinkedList<Edge> adj[];

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }

    }

    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    public class Edge {
        public int sid;
        public int tid;
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    private class Vertex implements Comparable<Vertex> {
        private int id;
        private int dist;

        @Override
        public int compareTo(Vertex v) {
            if (v.dist > this.dist) {
                return -1;
            }
            return 1;
        }

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    public void dijkstra(int s, int t) {
        int[] predecessor = new int[v];
        Vertex[] vertices = new Vertex[v];
        for (int i = 0; i < v; i++) {
            vertices[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        //小顶堆
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        //是否进入过队列
        boolean[] inqueue = new boolean[v];
        priorityQueue.add(new Vertex(s, 0));
        inqueue[s] = true;
        while (!priorityQueue.isEmpty()) {
            Vertex minVertex = priorityQueue.poll();
            if (minVertex.id == t) {
                break;
            }
            for (int i = 0; i < adj[minVertex.id].size(); i++) {
                Edge edge = adj[minVertex.id].get(i);
                Vertex nextVertex = vertices[edge.tid];
                if (minVertex.dist + edge.w < nextVertex.dist) {
                    nextVertex.dist = minVertex.dist + edge.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if (!inqueue[nextVertex.id]) {
                        priorityQueue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
            }
        }

        System.out.println("最后输出");
        System.out.print(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) {
            return;
        }
        print(s, predecessor[t], predecessor);
        System.out.print("-->" + t);
    }
}
