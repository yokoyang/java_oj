package dataStructure;

import Algorithm.shortestPath.Graph;
import org.junit.Test;

public class MinGraphDisTest {
    @Test
    public void testDijkstra() {
        Graph instance = new Graph(15);
        instance.addEdge(1, 5, 5);
        instance.addEdge(2, 5, 10);
        instance.addEdge(3, 7, 7);
        instance.addEdge(3, 6, 6);
        instance.addEdge(4, 6, 8);
        instance.addEdge(4, 7, 6);
        instance.addEdge(5, 8, 3);
        instance.addEdge(6, 8, 2);
        instance.addEdge(6, 9, 3);
        instance.addEdge(7, 9, 2);
        instance.addEdge(8, 10, 1);
        instance.addEdge(8, 11, 1);
        instance.addEdge(9, 11, 2);
        instance.addEdge(9, 12, 1);
        instance.addEdge(10, 13, 3);
        instance.addEdge(11, 13, 2);
        instance.addEdge(11, 14, 2);
        instance.addEdge(11, 14, 1);
        instance.addEdge(12, 14, 2);
        instance.dijkstra(3, 14);
    }
}
