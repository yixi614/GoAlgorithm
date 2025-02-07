package SystemDesign;

import java.util.*;

class VectorClock {
    // key is the server id, value is the clock value. For causality record
    private Map<String, Integer> clock = new HashMap<>();

    public void increment(String nodeId) {
        clock.merge(nodeId, 1, Integer::sum);
    }

    public void merge(VectorClock other) {
        other.clock.forEach((k, v) ->
                clock.merge(k, v, Integer::max));
    }

    public boolean isConflict(VectorClock other) {
        boolean hasGreater = false;
        boolean hasLess = false;
        Set<String> unionSet = clock.keySet();
        unionSet.addAll(other.clock.keySet());
        for (String nodeId : unionSet) {
            int t1 = clock.getOrDefault(nodeId, 0);
            int t2 = other.clock.getOrDefault(nodeId, 0);
            if (t1 > t2) hasGreater = true;
            if (t1 < t2) hasLess = true;
        }
        return hasGreater && hasLess;
    }

    public VectorClock clone() {
        VectorClock newClock = new VectorClock();
        newClock.clock.putAll(this.clock);
        return newClock;
    }
}


class Client {
    private VectorClock clientClock = new VectorClock();
    private final String clientId;

    public Client(String clientId) {
        this.clientId = clientId;
    }

    public void write(DistributedKeyValueStore node, String key, String value) {
        clientClock.increment(clientId);
        node.write(key, value, clientClock);
    }

    public DistributedKeyValueStore.VersionedValue read(DistributedKeyValueStore node, String key) {
        DistributedKeyValueStore.VersionedValue value = node.read(key);
        if (value != null) {
            clientClock.merge(value.clock);
        }
        return value;
    }
}

public class DistributedKeyValueStore {
    private final String nodeId;
    // key the key client sent, value is the value client sent but with a vector clock (a hashmap)
    private Map<String, VersionedValue> store = new HashMap<>();

    static class VersionedValue {
        String value;
        VectorClock clock;

        VersionedValue(String value, VectorClock clock) {
            this.value = value;
            this.clock = clock;
        }
    }

    public DistributedKeyValueStore(String nodeId) {
        this.nodeId = nodeId;
    }

    public void write(String key, String value, VectorClock clientClock) {
        VectorClock newClock = clientClock.clone();
        newClock.increment(nodeId);

        VersionedValue existing = store.get(key);
        if (existing != null && existing.clock.isConflict(clientClock)) {
            // Handle conflict with merge strategy
            String mergedValue = mergeValues(existing.value, value);
            VectorClock mergedClock = existing.clock.clone();
            mergedClock.merge(newClock);
            store.put(key, new VersionedValue(mergedValue, mergedClock));
        } else {
            store.put(key, new VersionedValue(value, newClock));
        }
    }

    public VersionedValue read(String key) {
        return store.get(key);
    }

    private String mergeValues(String val1, String val2) {
        return String.join(",", val1, val2);
    }

    public static void main(String[] args) {
        // server example
        DistributedKeyValueStore node1 = new DistributedKeyValueStore("node1");
        DistributedKeyValueStore node2 = new DistributedKeyValueStore("node2");

        VectorClock clock1 = new VectorClock();
        node1.write("key1", "value1", clock1);

        VectorClock clock2 = new VectorClock();
        node2.write("key1", "value2", clock2);

        System.out.println(node1.read("key1").value);
        System.out.println(node2.read("key1").value);

        // clients example
        Client client1 = new Client("client1");
        Client client2 = new Client("client2");

        // Client1 writes to node1
        client1.write(node1, "key1", "value1");

        // Client2 writes to node2 concurrently
        client2.write(node2, "key1", "value2");

        // Simulating replication
        VersionedValue node1Value = node1.read("key1");
        node2.write("key1", node1Value.value, node1Value.clock);

        // Checking final values
        System.out.println("Node1: " + node1.read("key1").value);
        System.out.println("Node2: " + node2.read("key1").value);
    }
}
