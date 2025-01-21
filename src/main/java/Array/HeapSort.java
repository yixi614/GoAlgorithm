package Array;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        int n = array.length;

        System.out.println("Original array:");
        printArray(array);

        heapSort(array);

        System.out.println("\nSorted array:");
        printArray(array);
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        // Build max heap
        // n/2 is the last non-leaf node. 1 is the root
        for (int i = n / 2; i >= 1; i--) {
            heapify(array, n, i);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap the root (max element) with the last element
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Call max heapify on the reduced heap
            heapify(array, i, 0);
        }
    }

    // Heapify a subtree rooted with node i which is an index in array[]
    // heapify has another name "siftdown"
    static void heapify(int[] array, int n, int i) {
        int largest = i; // Initialize largest as root
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        // If left child is larger than root
        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // If right child is larger than largest so far
        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // If largest is not root
        if (largest != i) {
            // Swap the root and the largest element
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(array, n, largest);
        }
    }

    // A utility function to print an array
    static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
