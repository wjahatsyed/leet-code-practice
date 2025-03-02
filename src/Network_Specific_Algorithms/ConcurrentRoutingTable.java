package Network_Specific_Algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-safe network routing table implementation
 */
public class ConcurrentRoutingTable {
    private final Map<String, Integer> routeMap;
    private final ReentrantReadWriteLock lock;

    public ConcurrentRoutingTable(){
        routeMap = new ConcurrentHashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    /**
     * Add or update a route
     * @param destination Destination network
     * @param metric Route metric value
     */
    public void addRoute(String destination, int metric){
        lock.writeLock().lock();
        try{
            routeMap.put(destination, metric);
        }finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Remove a route
     * @param destination Destination network to remove
     * @return True if route was removed, false if not found
     */
    public boolean removeRoute(String destination) {
        lock.writeLock().lock();
        try{
            if(routeMap.containsKey(destination)){
                routeMap.remove(destination);
                return true;
            }
            return false;
        }finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Get metric for a specific route
     * @param destination Destination network
     * @return Metric value or -1 if route not found
     */
    public int getRouteMetric(String destination) {
        lock.readLock().lock();
        try {
            return routeMap.getOrDefault(destination, -1);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Get all routes
     * @return Map of all routes
     */
    public Map<String, Integer> getAllRoutes() {
        lock.readLock().lock();
        try {
            return new HashMap<>(routeMap);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Get the best route based on lowest metric
     * @return The destination with the lowest metric or null if no routes exist
     */
    public String getBestRoute() {
        lock.readLock().lock();
        try {
            if (routeMap.isEmpty()) {
                return null;
            }

            String bestDest = null;
            int bestMetric = Integer.MAX_VALUE;

            for (Map.Entry<String, Integer> entry : routeMap.entrySet()) {
                if (entry.getValue() < bestMetric) {
                    bestMetric = entry.getValue();
                    bestDest = entry.getKey();
                }
            }

            return bestDest;
        } finally {
            lock.readLock().unlock();
        }
    }
}
