package Algorithm.dfs;

import java.util.*;

public class MoveBox {
    public static void main(String[] args) {
        MoveBox moveBox = new MoveBox();
        System.out.println(moveBox.minPushBox(new char[][]{{'#', '#', '#', '#', '#', '#'}, {'#', 'T', '#', '#', '#', '#'}, {'#', '.', '.', 'B', '.', '#'}, {'#', '.', '#', '#', '.', '#'}, {'#', '.', '.', '.', 'S', '#'}, {'#', '#', '#', '#', '#', '#'}}));
    }

    class MapNode {
        int bx, by, px, py;

        //用来判断这个点是否访问过，因为如果判断的是引用的话，永远都是没有访问过
        int key() {
            return ((by * 20 + bx) << 16) | (py * 20 + px);
        }

        public MapNode() {
        }

        public MapNode(int bx, int by, int px, int py) {
            this.bx = bx;
            this.by = by;
            this.px = px;
            this.py = py;
        }
    }

    //1263 推箱子问题
    int m, n;
    HashSet<Integer> seen = new HashSet<>();
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public int minPushBox(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        if (n == 0) {
            return -1;
        }
        MapNode start = new MapNode(), end = new MapNode();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    start.px = i;
                    start.py = j;
                } else if (grid[i][j] == 'B') {
                    start.bx = i;
                    start.by = j;
                } else if (grid[i][j] == 'T') {
                    end.bx = i;
                    end.by = j;
                }
            }
        }
        LinkedList<MapNode> pq = new LinkedList<>();
        pq.offer(start);
        int step = 0;
        while (!pq.isEmpty()) {
            int size = pq.size();
            while (size > 0) {
                MapNode cur = pq.poll();
                for (int i = 0; i < 4; i++) {
                    MapNode next = canPush(grid, cur, dx[i], dy[i]);
                    if (next == null || seen.contains(next.key())) {
                        continue;
                    }
                    if (next.bx == end.bx && next.by == end.by) {
                        return step + 1;
                    }
                    seen.add(next.key());
                    pq.offer(next);
                }
                size--;
            }
            ++step;
        }
        return -1;
    }


    public MapNode canPush(char[][] arr, MapNode curnode, int dx, int dy) {
        int cbx = curnode.bx;
        int cby = curnode.by;
        int nbx = cbx + dx;
        int nby = cby + dy;

        // whether next box position  is valid
        if (nbx < 0 || nbx >= arr.length || nby < 0 || nby >= arr[0].length || arr[nbx][nby] == '#'|| arr[cbx][cby] == '#')
            return null;

        if (hasPath(arr, curnode, cbx - dx, cby - dy))
            return new MapNode(cbx, cby, cbx + dx, cby + dy);

        return null;
    }

    public static boolean hasPath(char[][] arr, MapNode curnode, int tx, int ty) {
        HashSet<Integer> hashSet = new HashSet<>();
        return canArrive(arr, hashSet, curnode.bx, curnode.by, curnode.px, curnode.py, tx, ty);
    }

    public static boolean canArrive(char[][] arr, HashSet<Integer> nodes, int bx, int by, int px, int py, int tx, int ty) {

        if (px == tx && py == ty)
            return true;

        if (tx < 0 || tx >= arr.length || ty < 0 || ty >= arr[0].length || arr[tx][ty] == '#')
            return false;

        if (px < 0 || px >= arr.length || py < 0 || py >= arr[0].length || arr[px][py] == '#')
            return false;

        // meet the box
        if (px == bx && py == by)
            return false;

        if (nodes.contains(px * 20 + py))
            return false;
        else
            nodes.add(px * 20 + py);
        return canArrive(arr, nodes, bx, by, px + 1, py, tx, ty) || canArrive(arr, nodes, bx, by, px - 1, py, tx, ty)
                || canArrive(arr, nodes, bx, by, px, py + 1, tx, ty) || canArrive(arr, nodes, bx, by, px, py - 1, tx, ty);
    }
}
