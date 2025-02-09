package Common;


/**
 * Think of Roaring Bitmap as a smart container system that efficiently stores and manages large sets of numbers
 * by using different storage strategies based on the data patterns.
 * It splits numbers into "chunks" of 2¹⁶ (65,536) values
 * First 16 bits: Used as "chunk" number
 * Last 16 bits: Used as position within chunk
 * 1. Array for sparse data:
 * If few numbers in chunk: Store as sorted array
 * Example: [1,2,3] for first few integers
 *
 * 2. Bitmap for dense data:
 * If many numbers in chunk: Use traditional bitmap
 * Example: lots of numbers between 65,536-131,071
 *
 * 3. Run containers for sequences:
 * If consecutive numbers: Store as ranges
 * Example: Store 1-1000 as [1,1000] instead of 1000 values
 * */

class Bitmap {
    private long[] bits;

    public Bitmap(int size) {
        bits = new long[(size + 63) / 64];
    }
    // pos % 64 is equivalent to pos & 63 (or pos & 0x3F).
    public void set(int pos) {
        bits[pos / 64] |= 1L << (pos % 64);
    }

    public boolean get(int pos) {
        return (bits[pos / 64] & (1L << (pos % 64))) != 0;
    }

    public void clear(int pos) {
        bits[pos / 64] &= ~(1L << (pos % 64));
    }

  public static void main(String[] args) {
      Bitmap bv = new Bitmap(10);
      bv.set(2);
      System.out.println(bv.get(2));
      bv.set(3);
      System.out.println(bv.get(3));
  }
}
