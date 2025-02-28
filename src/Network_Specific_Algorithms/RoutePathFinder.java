package Network_Specific_Algorithms;

import java.util.*;

/**
 * Dijkstra's algorithm for route finding
 */
public class RoutePathFinder {
    //find the shortest path from the source to destination in a network
    public List<String> findShortestPath(NetworkGraph graph, String source, String destination) {
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(
                Comparator.comparingInt(NodeDistance::getDistance));
        // Keep track of distances from source to each node
        Map<String, Integer> distances = new HashMap<>();

        // Keep track of previous node for path reconstruction
        Map<String, String> previousNodes = new HashMap<>();

        // Initialize distances with infinity for all nodes and 0 for source
        for(String node: graph.getAllNode()){
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(source, 0);

        // Add source to the queue
        pq.add(new NodeDistance(source, 0));

        // Process nodes
        while(!pq.isEmpty()){
            NodeDistance current = pq.poll();
            String currentNode = current.node;
            int currentDistance = current.distance;

            // If we've reached the destination, we can stop
            if(currentNode.equals(destination)){
                break;
            }

            // Skip if we've found a better path already
            if(currentDistance > distances.get(currentNode)){
                continue;
            }

            //check All neighbours
            for(NetworkGraph.Edge edge: graph.getNeighbours(currentNode)){
                String neighbour = edge.destination;
                int newDistance = currentDistance + edge.weight;

                // If we found a better path, update the distance and add to queue
                if(newDistance < distances.get(neighbour)){
                    distances.put(neighbour, newDistance);
                    previousNodes.put(neighbour, currentNode);
                    pq.add(new NodeDistance(neighbour, newDistance));
                }
            }
        }
        // Reconstruct the path from destination to source
        List<String> path = new ArrayList<>();
        String current = destination;

        // If the destination is not reachable
        if(!previousNodes.containsKey(destination)){
            return path;
        }

        // Build the path
        while (current != null){
            path.add(current);
            current = previousNodes.get(current);
            if(current !=null && current.equals(source)){
                path.add(current);
                break;
            }
        }
        Collections.reverse(path);
        return path;
    }

    public static class NodeDistance {
        String node;
        int distance;

        public NodeDistance(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }
    }
}
