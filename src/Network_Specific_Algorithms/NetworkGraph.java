package Network_Specific_Algorithms;

import java.util.*;

public class NetworkGraph {
    private Map<String, List<Edge>> adjacencyList;

    public NetworkGraph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(String node) {
        if (!adjacencyList.containsKey(node)) {
            adjacencyList.put(node, new ArrayList<>());
        }
    }

    public void addEdge(String source, String destination, int weight) {
        addNode(source);
        addNode(destination);
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    public List<Edge> getNeighbours(String node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }

    public Set<String> getAllNode() {
        return adjacencyList.keySet();
    }

    static class Edge {
        String destination;
        int weight;

        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}
