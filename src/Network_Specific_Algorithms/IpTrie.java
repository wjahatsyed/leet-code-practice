package Network_Specific_Algorithms;

/**
 * Trie for IP/CIDR prefix matching
 */
public class IpTrie {
    private TrieNode root;

    public IpTrie() {
        this.root = new TrieNode();

    }

    /**
     * Insert an IP prefix (CIDR) into the trie
     *
     * @param cidr      IP prefix in CIDR notation (e.g., "192.168.1.0/24")
     * @param routeInfo Information associated with this route
     */
    public void insert(String cidr, String routeInfo) {
        String[] parts = cidr.split("/");
        String ip = parts[0];
        int preFixLength = Integer.parseInt(parts[1]);

        // Convert IP to binary
        String[] octets = ip.split("\\.");
        StringBuilder binaryIP = new StringBuilder();
        for (String octet : octets) {
            String binary = Integer.toBinaryString(Integer.parseInt(octet));
            // Pad to 8 bits
            while (binary.length() < 8) {
                binary = "0" + binary;
            }
            binaryIP.append(binary);
        }

        TrieNode current = root;

        // Insert only up to the prefix length
        for(int i=0; i<preFixLength; i++){
            int bit = binaryIP.charAt(i) - '0';
            if(current.children[bit] == null){
                current.children[bit] = new TrieNode();
            }
            current.isEndOfPrefix = true;
            current.routeInfo = routeInfo;
        }
    }

    /**
     * Find the longest matching prefix for a given IP
     * @param ip IP address to match
     * @return Route information or null if no match
     */
    public String findLongestMatchingPrefix(String ip){
        String[] octets = ip.split("\\.");
        StringBuilder binaryIp = new StringBuilder();

        for(String octet: octets){
            String binary = Integer.toBinaryString(Integer.parseInt(octet));
            //pad to 8
            while(binary.length() < 8){
                binary = "0" + binary;
            }
            binaryIp.append(ip);
        }
        TrieNode current = root;
        String lastMatchedRoute = null;

        //Traverse the Trie bit by bit
        for(int i=0; i<binaryIp.length(); i++){
            int bit = binaryIp.charAt(i) - '0';
            if(current.children[bit] == null){
                break;
            }
            current = current.children[bit];
            if(current.isEndOfPrefix){
                lastMatchedRoute = current.routeInfo;
            }
        }
        return lastMatchedRoute;
    }

    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfPrefix;
        String routeInfo;

        public TrieNode() {
            children = new TrieNode[2]; //Boolean Trie 0 and 1
            isEndOfPrefix = false;
            routeInfo = null;
        }
    }
}
