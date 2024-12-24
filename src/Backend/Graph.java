package Backend;
import java.util.*;
import java.util.PriorityQueue;
public class Graph {
    private int vertices;
    private List<List<Node>> adjacencyList;

    // Node class for PriorityQueue and adjacency list
    static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    // Constructor
    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Add edge method
    public void addEdge(int source, int destination, int forwardWeight, int backwardWeight) {
        // Add edge from source to destination with `forwardWeight`
        adjacencyList.get(source).add(new Node(destination, forwardWeight));
        // Add edge from destination to source with `backwardWeight`
        adjacencyList.get(destination).add(new Node(source, backwardWeight));
    }

    // Dijkstra's algorithm
    public int[] dijkstra(int start) {
        //Compare according to node weight
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node neighbor : adjacencyList.get(current.vertex)) {
                int newDist = distances[current.vertex] + neighbor.weight;

                if (newDist < distances[neighbor.vertex]) {
                    distances[neighbor.vertex] = newDist;
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }

        return distances;
    }
}