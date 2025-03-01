package Network_Specific_Algorithms;

import java.util.*;

/**
 * Ford-Fulkerson algorithm for network flow optimization
 */
public class NetworkFlowOptimizer {
    /*
     * Find the maximum flow in a network from source to sink
     * @param graph Residual network graph
     * @param source Source node
     * @param sink Sink node
     * @return Maximum flow value
     */
    public int findMaxFlow(Map<String, Map<String, Integer>> graph, String source, String sink) {
        // Create a residual graph
        Map<String, Map<String, Integer>> residualGraph = new HashMap<>();
        // Copy original capacities to residual graph
        for (String u : graph.keySet()) {
            residualGraph.put(u, new HashMap<>());
            for (Map.Entry<String, Integer> edge : graph.get(u).entrySet()) {
                String v = edge.getKey();
                int capacity = edge.getValue();
                residualGraph.get(u).put(v, capacity);

                // Ensure reverse edge exists in residual graph
                if (!residualGraph.containsKey(v)) {
                    residualGraph.put(v, new HashMap<>());
                }
                if (!residualGraph.get(v).containsKey(u)) {
                    residualGraph.get(v).put(u, 0);
                }
            }
        }

        int maxFlow = 0;

        // Augment flow while there is a path from source to sink
        List<String> path;
        while ((path = findAugmentingPath(residualGraph, source, sink)) != null) {
            // Find minimum residual capacity along the path
            int minCapacity = Integer.MAX_VALUE;
            for (int i = 0; i < path.size() - 1; i++) {
                String u = path.get(i);
                String v = path.get(i + 1);
                minCapacity = Math.min(minCapacity, residualGraph.get(u).get(v));
            }
            // Update residual capacities
            for (int i = 0; i < path.size() - 1; i++) {
                String u = path.get(i);
                String v = path.get(i + 1);
                // Decrease capacity in forward edge
                residualGraph.get(u).put(v, residualGraph.get(u).get(v) - minCapacity);
                // Increase capacity in backward edge
                residualGraph.get(v).put(u, residualGraph.get(v).get(u) + minCapacity);
            }
            maxFlow += minCapacity;
        }

        return maxFlow;
    }

    // BFS to find an augmenting path
    private List<String> findAugmentingPath(Map<String, Map<String, Integer>> residualGraph,
                                            String source, String sink) {
        Map<String, String> parentMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(source);
        parentMap.put(source, null);

        while (!queue.isEmpty() && !parentMap.containsKey(sink)) {
            String current = queue.poll();

            // Check each neighbor with positive residual capacity
            for (Map.Entry<String, Integer> edge : residualGraph.get(current).entrySet()) {
                String neighbour = edge.getKey();
                int capacity = -edge.getValue();
                if (capacity > 0 && !parentMap.containsKey(neighbour)) {
                    queue.add(neighbour);
                    parentMap.put(neighbour, current);
                }
            }
        }

        // If sink was not reached, no augmenting path exists
        if (!parentMap.containsKey(sink)) {
            return null;
        }

        // Reconstruct the path
        List<String> path = new ArrayList<>();
        String current = sink;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
