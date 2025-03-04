package SystemDesign;

public class SimpleHashMap<K, V> {

    // Default initial capacity
    private static final int DEFAULT_CAPACITY = 16;

    // Default load factor
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // Node class represents a key-value entry in the hash map
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Array of buckets (linked lists)
    private Node<K, V>[] buckets;

    // Number of key-value pairs in the map
    private int size;

    // Current capacity of the buckets array
    private int capacity;

    // Load factor determines when to resize
    private final float loadFactor;

    // Constructor with default settings
    public SimpleHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    // Constructor with custom capacity and default load factor
    public SimpleHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    // Constructor with custom capacity and load factor
    @SuppressWarnings("unchecked")
    public SimpleHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + initialCapacity);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be positive: " + loadFactor);
        }

        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.buckets = (Node<K, V>[]) new Node[initialCapacity];
        this.size = 0;
    }

    // Put a key-value pair into the map
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Null keys are not supported");
        }

        // Check if we need to resize the buckets array
        if (size >= capacity * loadFactor) {
            resize();
        }

        // Get the bucket index based on the hash code
        int bucketIndex = getBucketIndex(key);

        // Check if the key already exists
        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (key.equals(current.key)) {
                // Key exists, update value and return the old value
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }

        // Key doesn't exist, add a new node at the beginning of the linked list
        buckets[bucketIndex] = new Node<>(key, value, buckets[bucketIndex]);
        size++;
        return null;
    }

    // Get a value by key
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Null keys are not supported");
        }

        // Get the bucket index
        int bucketIndex = getBucketIndex(key);

        // Search for the key in the linked list
        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }
            current = current.next;
        }

        // Key not found
        return null;
    }

    // Remove a key-value pair
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Null keys are not supported");
        }

        // Get the bucket index
        int bucketIndex = getBucketIndex(key);

        // Check if the bucket is empty
        if (buckets[bucketIndex] == null) {
            return null;
        }

        // Check if the key is at the head of the list
        if (key.equals(buckets[bucketIndex].key)) {
            V oldValue = buckets[bucketIndex].value;
            buckets[bucketIndex] = buckets[bucketIndex].next;
            size--;
            return oldValue;
        }

        // Search for the key in the rest of the list
        Node<K, V> prev = buckets[bucketIndex];
        Node<K, V> current = prev.next;

        while (current != null) {
            if (key.equals(current.key)) {
                // Key found, remove the node
                prev.next = current.next;
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        // Key not found
        return null;
    }

    // Check if the map contains a key
    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException("Null keys are not supported");
        }

        // Get the bucket index
        int bucketIndex = getBucketIndex(key);

        // Search for the key in the linked list
        Node<K, V> current = buckets[bucketIndex];
        while (current != null) {
            if (key.equals(current.key)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    // Get the number of entries in the map
    public int size() {
        return size;
    }

    // Check if the map is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get bucket index for a key
    private int getBucketIndex(K key) {
        // We use Math.abs to handle negative hash codes
        return Math.abs(key.hashCode()) % capacity;
    }

    // Resize the buckets array when load factor is exceeded
    @SuppressWarnings("unchecked")
    private void resize() {
        // Double the capacity
        int newCapacity = capacity * 2;
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[newCapacity];

        // Rehash all existing entries
        for (int i = 0; i < capacity; i++) {
            Node<K, V> current = buckets[i];
            while (current != null) {
                // Save the next node before we modify the current one
                Node<K, V> next = current.next;

                // Calculate new bucket index based on new capacity
                int newBucketIndex = Math.abs(current.key.hashCode()) % newCapacity;

                // Insert at beginning of the new bucket
                current.next = newBuckets[newBucketIndex];
                newBuckets[newBucketIndex] = current;

                // Move to the next node in the original list
                current = next;
            }
        }

        // Update buckets and capacity
        buckets = newBuckets;
        capacity = newCapacity;
    }

    // Clear all entries
    public void clear() {
        // Set all buckets to null
        for (int i = 0; i < capacity; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    // Example usage
    public static void main(String[] args) {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();

        // Add some key-value pairs
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Get values
        System.out.println("Value for 'one': " + map.get("one"));
        System.out.println("Value for 'two': " + map.get("two"));
        System.out.println("Value for 'three': " + map.get("three"));
        System.out.println("Value for 'four': " + map.get("four"));

        // Update a value
        map.put("one", 11);
        System.out.println("Updated value for 'one': " + map.get("one"));

        // Remove a key
        System.out.println("Removing 'two': " + map.remove("two"));
        System.out.println("Value for 'two' after removal: " + map.get("two"));

        // Check size
        System.out.println("Size of map: " + map.size());

        // Check if key exists
        System.out.println("Contains 'one': " + map.containsKey("one"));
        System.out.println("Contains 'two': " + map.containsKey("two"));
    }
}
