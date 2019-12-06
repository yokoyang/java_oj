package Algorithm.dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.*;

public class MoveBox2 {
    public static void main(String[] args) {
        MoveBox2 moveBox = new MoveBox2();
        System.out.println(moveBox.minPushBox(new char[][]{{'#', '#', '#', '#', '#', '#'}, {'#', 'T', '#', '#', '#', '#'}, {'#', '.', '.', 'B', '.', '#'}, {'#', '.', '#', '#', '.', '#'}, {'#', '.', '.', '.', 'S', '#'}, {'#', '#', '#', '#', '#', '#'}}));
    }

    private class Sugarcane{
        int box;
        int person;
        public Sugarcane(int box, int person){
            this.box = box;
            this.person = person;
        }
        public String toString(){
            return this.box + " " + this.person;
        }
    }

    Set<Integer> set = new HashSet<>();
    int[] dir = {0, 1, 0, -1, 0};
    int m, n;
    public int minPushBox(char[][] grid) {
        int res = -1;
        m = grid.length; n = grid[0].length;
        int d = 0, p = 0;
        Queue<Sugarcane> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 'B'){
                    d = i * n + j;
                }
                else if(grid[i][j] == 'S'){
                    p = i * n + j;
                }
            }
        }
        Sugarcane sugarcane = new Sugarcane(d, p);
        queue.offer(sugarcane);
        visited.add(sugarcane.toString());

        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for(int i = 0; i < size; i++){
                Sugarcane sugar = queue.poll();
                int bp = sugar.box, pp = sugar.person;
                int x = bp / n, y = bp % n;
                if(grid[x][y] == 'T')
                    return res;

                grid[x][y] = '#';
                for(int j = 0; j < 4; j++){
                    int bx = x + dir[j], by = y + dir[j + 1];
                    int px = x - dir[j], py = y - dir[j + 1];
                    Sugarcane nextSugar = new Sugarcane(bx * n + by, px * n + py);
                    String nV = nextSugar.toString();
                    set = new HashSet<>();
                    if(isVaild(bx, by, grid) && !visited.contains(nV) && dfs(pp / n, pp % n, grid, px, py)){
                        queue.offer(nextSugar);
                        visited.add(nV);
                    }
                }
                grid[x][y] = '.';
            }
        }
        return -1;
    }


    public boolean isVaild(int x, int y, char[][] grid){
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#';
    }

    public boolean dfs(int x, int y, char[][] grid, int dx, int dy){
        if(x == dx && y == dy)
            return true;
        for(int i = 0; i < 4; i++){
            int nx = x + dir[i], ny = y + dir[i + 1];
            if(isVaild(nx, ny, grid) && set.add(nx * n + ny))
                if(dfs(nx, ny, grid, dx, dy))
                    return true;
        }
        return false;
    }
}
