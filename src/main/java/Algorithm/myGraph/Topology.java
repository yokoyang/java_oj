package Algorithm.myGraph;

import java.util.LinkedList;

public class Topology {
    public static class Graph {
        private int v;
        private LinkedList<Integer>[] adj;

        public Graph(int v) {
            this.v = v;
            this.adj = new LinkedList[v];
            // 初始化领接表
            for (int i = 0; i < v; i++) {
                this.adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t) {
            this.adj[s].add(t);
        }

        public void topSortbyKahn() {
//            统计每个顶点的入度
            int[] inDegree = new int[v];
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int w = adj[i].get(j);
                    inDegree[w]++;
                }
            }
            LinkedList<Integer> queue = new LinkedList<>();
            for (int i = 0; i < inDegree.length; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
            while (!queue.isEmpty()) {
                Integer w = queue.remove();
                System.out.print("->" + w);
                LinkedList<Integer> outPoint = adj[w];
                for (int j = 0; j < outPoint.size(); j++) {
                    int k = outPoint.get(j);
                    inDegree[k]--;
                    if (inDegree[k] <= 0) {
                        queue.add(k);
                    }
                }
            }
        }
        public void topoSortByDFS() {
            // 先构建逆邻接表，边 s->t 表示，s 依赖于 t，t 先于 s
            LinkedList<Integer> inverseAdj[] = new LinkedList[v];
            for (int i = 0; i < v; ++i) { // 申请空间
                inverseAdj[i] = new LinkedList<>();
            }
            for (int i = 0; i < v; ++i) { // 通过邻接表生成逆邻接表
                for (int j = 0; j < adj[i].size(); ++j) {
                    int w = adj[i].get(j); // i->w
                    inverseAdj[w].add(i); // w->i
                }
            }
            boolean[] visited = new boolean[v];
            for (int i = 0; i < v; ++i) { // 深度优先遍历图
                if (visited[i] == false) {
                    visited[i] = true;
                    dfs(i, inverseAdj, visited);
                }
            }
        }

        private void dfs(
                int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
            for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
                int w = inverseAdj[vertex].get(i);
                if (visited[w] == true) continue;
                visited[w] = true;
                dfs(w, inverseAdj, visited);
            } // 先把 vertex 这个顶点可达的所有顶点都打印出来之后，再打印它自己
            System.out.print("->" + vertex);
        }

    }

}
