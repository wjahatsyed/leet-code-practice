package Network_Specific_Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simplified route server implementation that handles IPv4 prefixes and AS path selection.
 * This implementation uses a trie-based structure for efficient prefix matching.
 */
public class RouteServer {
    private final TrieNode root;

    public RouteServer() {
        this.root = new TrieNode();
    }

    /**
     * Node class for the prefix trie structure.
     */
    private static class TrieNode {
        private final Map<Character, TrieNode> children;
        private boolean isPreFix;
        private List<List<Integer>> routes;

        TrieNode() {
            this.children = new HashMap<>();
            this.isPreFix = false;
            this.routes = new ArrayList<>();
        }
    }

    /*
     * Represents a route with its prefix and AS path.
     */
    public static class Route {
        private final String prefix;
        private final List<Integer> asPath;

        public Route(String prefix, List<Integer> asPath) {
            this.prefix = prefix;
            this.asPath = new ArrayList<>(asPath);
        }

        @Override
        public String toString() {
            return String.format("Route{prefix='%s', asPath=%s}", prefix, asPath);
        }
    }

    /*
     * Converts an IP address to its binary string representation.
     *
     * @param ip IP address in dotted decimal format
     * @return Binary string representation
     * @throws IllegalArgumentException if IP format is invalid
     */
    private String ipToBinary(String ip) {
        try {
            StringBuilder binary = new StringBuilder();
            String[] parts = ip.split("\\.");

            if (parts.length != 4) {
                throw new IllegalArgumentException("Invalid IP address Format");
            }

            for (String part : parts) {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    throw new IllegalArgumentException("Invalid IP address format");
                }
                binary.append(String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0'));
            }

            return binary.toString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid IP address Format", e);
        }
    }

    /**
     * Adds a route to the route server.
     *
     * @param prefix CIDR notation prefix (e.g., "192.168.0.0/24")
     * @param asPath List of AS numbers representing the path
     * @throws IllegalArgumentException if prefix format is invalid
     */
    public void addRoute(String prefix, List<Integer> asPath) {
        if (prefix == null || asPath == null) {
            throw new IllegalArgumentException("Prefix and AS path cannot be null");
        }

        String[] parts = prefix.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid prefix format");
        }

        String ip = parts[0];
        int maskLen;
        try {
            maskLen = Integer.parseInt(parts[1]);
            if (maskLen < 0 || maskLen > 32) {
                throw new IllegalArgumentException("Mask length must be between 0 and 32");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid mask length", e);
        }

        String binaryIP = ipToBinary(ip);
        TrieNode current = root;

        // Insert prefix into trie
        for (int i = 0; i < maskLen; i++) {
            char bit = binaryIP.charAt(i);
            current.children.putIfAbsent(bit, new TrieNode());
            current = current.children.get(bit);
        }

        current.isPreFix = true;
        current.routes.add(new ArrayList<>(asPath));

        // Sort routes by path length (shorter paths preferred)
        current.routes.sort((a, b) -> Integer.compare(a.size(), b.size()));
    }


    /*
     * Looks up the best route for a given IP address.
     *
     * @param ip IP address to look up
     * @return Best matching AS path or null if no match found
     * @throws IllegalArgumentException if IP format is invalid
     */
    public List<Integer> lookupRoute(String ip) {
        if (ip == null) {
            throw new IllegalArgumentException("IP cannot be null");
        }

        String binaryIp = ipToBinary(ip);
        TrieNode current = root;
        List<Integer> bestMatch = null;

        // Traverse trie to find longest matching prefix
        for (int i = 0; i < 32 && current != null; i++) {
            if (current.isPreFix && !current.routes.isEmpty()) {
                bestMatch = current.routes.getFirst();
            }
            char bit = binaryIp.charAt(i);
            current = current.children.get(bit);
        }
        // Check final node
        if (current != null && current.isPreFix && !current.routes.isEmpty()) {
            bestMatch = current.routes.getFirst();
        }
        return bestMatch != null ? new ArrayList<>(bestMatch) : null;
    }

    public static void main(String[] args) {
        RouteServer rs = new RouteServer();

        // Add test routes
        rs.addRoute("192.168.0.0/24", List.of(65001, 65002, 65003));
        rs.addRoute("192.168.0.0/16", List.of(65001, 65002));
        rs.addRoute("192.168.1.0/24", List.of(65001, 65004));

        // Test cases
        String[][] testCases = {
                {"192.168.0.1", "65001,65002,65003"}, // Should match /24
                {"192.168.1.1", "65001,65004"},       // Should match 192.168.1.0/24
                {"192.168.2.1", "65001,65002"},       // Should match /16
                {"10.0.0.1", null}                    // Should not match anything
        };

        // Run tests
        for (String[] testCase : testCases) {
            String ip = testCase[0];
            String expectedStr = testCase[1];
            List<Integer> result = rs.lookupRoute(ip);

            List<Integer> expected = null;
            if (expectedStr != null) {
                expected = new ArrayList<>();
                for (String s : expectedStr.split(",")) {
                    expected.add(Integer.parseInt(s));
                }
            }

            System.out.println("IP: " + ip);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
            System.out.println("Pass: " + (expected == null ? result == null : expected.equals(result)));
            System.out.println();
        }
    }


}
