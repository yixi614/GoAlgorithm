package SystemDesign;


/**
 * The Bloom filter is designed to be a space-efficient probabilistic data structure. It's meant to be used when:
 *
 * You need to store a relatively small subset of all possible values
 * You can tolerate some false positives
 * You want to save space compared to storing the actual values
 *
 * Bloom filter has a fixed space.
 * you cannot store all double values and string values in a single machine
 * */
public class BloomFilter<T> {
    private byte[] bitArray;
    private int size;
    private int numHashFunctions;

    public BloomFilter(int size, int numHashFunctions) {
        this.size = size;
        this.numHashFunctions = numHashFunctions;
        // Initialize bit array (1 byte = 8 bits)
        this.bitArray = new byte[size / 8 + 1];
    }

    private int[] getHashValues(T item) {
        int[] hashValues = new int[numHashFunctions];
        // Using string representation for hash calculation
        String str = item.toString();

        for (int i = 0; i < numHashFunctions; i++) {
            // Custom hash function implementation using djb2 with different seeds
            hashValues[i] = djb2Hash(str, i) % size;
        }
        return hashValues;
    }

    private int djb2Hash(String str, int seed) {
        int hash = 5381 + seed;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return Math.abs(hash);
    }

    // Set a bit in the array
    private void setBit(int index) {
        int byteIndex = index / 8;
        int bitIndex = index % 8;
        bitArray[byteIndex] |= (1 << bitIndex);
    }

    // Test if a bit is set
    private boolean getBit(int index) {
        int byteIndex = index / 8;
        int bitIndex = index % 8;
        return (bitArray[byteIndex] & (1 << bitIndex)) != 0;
    }

    public void add(T item) {
        int[] hashValues = getHashValues(item);
        for (int hash : hashValues) {
            setBit(hash);
        }
    }

    public boolean mightContain(T item) {
        int[] hashValues = getHashValues(item);
        for (int hash : hashValues) {
            if (!getBit(hash)) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < bitArray.length; i++) {
            bitArray[i] = 0;
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Create a Bloom filter with size 1000 and 3 hash functions
        BloomFilter<String> filter = new BloomFilter<>(1000, 3);

        // Add some elements
        filter.add("apple");
        filter.add("banana");
        filter.add("orange");

        // Test membership
        System.out.println("Contains 'apple'? " + filter.mightContain("apple"));     // true
        System.out.println("Contains 'banana'? " + filter.mightContain("banana"));   // true
        System.out.println("Contains 'grape'? " + filter.mightContain("grape"));     // probably false

        // Performance test
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            filter.add("item" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to add 10000 items: " + (endTime - startTime) + "ms");
    }
}
