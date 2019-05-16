package dataStructure;

import Algorithm.myGraph.Topology;
import org.junit.Test;

public class TopologyTest {
    @Test
    public void testKnhn() {
        Topology.Graph instance = new Topology.Graph(6);
        instance.addEdge(0, 1);
        instance.addEdge(2, 3);
        instance.addEdge(4, 5);
        instance.addEdge(1, 2);
        instance.addEdge(3, 4);
        // instance.addEdge(5, 0);
        instance.topSortbyKahn();
        // instance.topoSortByKahn();
    }
    @Test
    public void testDFS() {
        Topology.Graph instance = new Topology.Graph(10);
        instance.addEdge(6, 7);
        instance.addEdge(5, 8);
        instance.addEdge(7, 8);
        instance.addEdge(8, 9);
        instance.addEdge(4, 5);
        instance.addEdge(4, 6);
        instance.addEdge(1, 4);
        instance.addEdge(1, 0);
        instance.addEdge(2, 4);
        instance.addEdge(3, 4);
        // instance.addEdge(5, 0);
        instance.topoSortByDFS();
        // instance.topoSortByKahn();
    }
//    @Test
//    public void testKnhn2() {
//        Graph instance = new Graph(10);
//        instance.addEdge(6, 7);
//        instance.addEdge(5, 8);
//        instance.addEdge(7, 8);
//        instance.addEdge(8, 9);
//        instance.addEdge(4, 5);
//        instance.addEdge(4, 6);
//        instance.addEdge(1, 4);
//        instance.addEdge(2, 4);
//        instance.addEdge(3, 4);
//
//        instance.kahn();
//        // instance.topoSortByKahn();
//    }
}
