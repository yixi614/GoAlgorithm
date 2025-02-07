package SystemDesign;

import java.nio.ByteBuffer;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConsistentHashing {
    private TreeMap<Long, String> hashRing = new TreeMap<>();
    private int virtualNodes = 100;

    public void addNode(String node) {
        for (int i = 0; i < virtualNodes; i++) {
            long hash = hash(node + i);
            hashRing.put(hash, node);
        }
    }

    public void removeNode(String node) {
        for (int i = 0; i < virtualNodes; i++) {
            long hash = hash(node + i);
            hashRing.remove(hash);
        }
    }

    public String getNode(String key) {
        if (hashRing.isEmpty()) return null;

        long hash = hash(key);
        Long nodeHash = hashRing.ceilingKey(hash);

        if (nodeHash == null) {
            nodeHash = hashRing.firstKey();
        }

        return hashRing.get(nodeHash);
    }

    // why not sha1-256, because md5 is faster
    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(key.getBytes());
            return Math.abs(ByteBuffer.wrap(hashBytes).getLong());
        } catch (NoSuchAlgorithmException e) {
            return key.hashCode();
        }
    }

    public static void main(String[] args) {
        ConsistentHashing ch = new ConsistentHashing();
        ch.addNode("Node1");
        ch.addNode("Node2");
        ch.addNode("Node3");

        System.out.println(ch.getNode("key1")); // Deterministic node selection
    }
}
