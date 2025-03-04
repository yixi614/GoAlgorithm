package Graph;

import java.util.*;

public class DijkstraAlgorithm {

    // Graph is represented using an adjacency list
    static class Graph {
        private int vertices;
        private Map<Integer, List<Node>> adjacencyList;

        // Constructor
        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new HashMap<>();

            // Initialize the adjacency list for each vertex
            for (int i = 0; i < vertices; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }

        // Add an edge to the graph
        public void addEdge(int source, int destination, int weight) {
            // Add edge from source to destination
            adjacencyList.get(source).add(new Node(destination, weight));
        }

        // Get adjacency list
        public Map<Integer, List<Node>> getAdjacencyList() {
            return adjacencyList;
        }

        // Get number of vertices
        public int getVertices() {
            return vertices;
        }
    }

    // Node class to store destination vertex and weight of edge
    static class Node {
        private int vertex;
        private int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int getVertex() {
            return vertex;
        }

        public int getWeight() {
            return weight;
        }
    }

    // Class for storing vertex data in the priority queue
    static class VertexDistance implements Comparable<VertexDistance> {
        private int vertex;
        private int distance;

        public VertexDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        // For priority queue to get minimum distance vertex
        @Override
        public int compareTo(VertexDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // Dijkstra's algorithm implementation
    public static int[] findShortestPaths(Graph graph, int source) {
        int vertices = graph.getVertices();

        // Array to store shortest distance from source to each vertex
        int[] distances = new int[vertices];

        // Initialize all distances as infinite (MAX_VALUE)
        Arrays.fill(distances, Integer.MAX_VALUE);

        // Distance of source vertex from itself is 0
        distances[source] = 0;

        // Track if vertex is processed
        boolean[] processed = new boolean[vertices];

        // Priority queue to get minimum distance vertex
        PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>();

        // Add source to priority queue
        priorityQueue.add(new VertexDistance(source, 0));

        // Process vertices
        while (!priorityQueue.isEmpty()) {
            // Remove minimum distance vertex from priority queue
            int currentVertex = priorityQueue.poll().vertex;

            // If already processed, skip
            if (processed[currentVertex]) {
                continue;
            }

            // Mark as processed
            processed[currentVertex] = true;

            // Get all adjacent vertices
            List<Node> adjacentVertices = graph.getAdjacencyList().get(currentVertex);

            // For each adjacent vertex
            for (Node adjacent : adjacentVertices) {
                int adjacentVertex = adjacent.getVertex();
                int edgeWeight = adjacent.getWeight();

                // If not processed and there is a better path
                if (!processed[adjacentVertex] &&
                        distances[currentVertex] != Integer.MAX_VALUE &&
                        distances[currentVertex] + edgeWeight < distances[adjacentVertex]) {
                    // Update distance
                    distances[adjacentVertex] = distances[currentVertex] + edgeWeight;

                    // Add to priority queue
                    priorityQueue.add(new VertexDistance(adjacentVertex, distances[adjacentVertex]));
                }
            }
        }

        return distances;
    }

    // Example usage
    public static void main(String[] args) {
        // Create a graph with 6 vertices (0 to 5)
        Graph graph = new Graph(6);

        // Add edges with weights
        graph.addEdge(0, 1, 4);  // Edge from 0 to 1 with weight 4
        graph.addEdge(0, 2, 2);  // Edge from 0 to 2 with weight 2
        graph.addEdge(1, 2, 5);  // Edge from 1 to 2 with weight 5
        graph.addEdge(1, 3, 10); // Edge from 1 to 3 with weight 10
        graph.addEdge(2, 3, 3);  // Edge from 2 to 3 with weight 3
        graph.addEdge(2, 4, 2);  // Edge from 2 to 4 with weight 2
        graph.addEdge(3, 4, 4);  // Edge from 3 to 4 with weight 4
        graph.addEdge(3, 5, 11); // Edge from 3 to 5 with weight 11
        graph.addEdge(4, 5, 1);  // Edge from 4 to 5 with weight 1

        // Source vertex
        int source = 0;

        // Find shortest paths from source
        int[] shortestDistances = findShortestPaths(graph, source);

        // Print results
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < shortestDistances.length; i++) {
            System.out.println("To vertex " + i + ": " + shortestDistances[i]);
        }
    }
}
