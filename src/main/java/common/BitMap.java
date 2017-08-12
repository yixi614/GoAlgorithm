package common;

public class BitMap {
  private final int BITSPERWORD = 32;
  private final int SHIFT = 5;
  private final int MASK = 0x1F;
  private int[] a = null;
  public BitMap(int maxNumber) {
    a = new int[1 + maxNumber/BITSPERWORD];
  }

  public void put(int value) {
    a[value>>SHIFT] |= (1<<(value & MASK));
  }

  public void clear(int value) {
    a[value>>SHIFT] &= ~(1<<(value & MASK));
  }

  //if this > 0, we say that this i exists in map
  public boolean contains(int value) {
    return (a[value>>SHIFT] & (1<<(value & MASK))) > 0;
  }

  public static void main(String[] args) {
      BitMap bv = new BitMap(10);
      bv.put(2);
      System.out.println(bv.contains(2));
      bv.put(3);
      System.out.println(bv.contains(3));
  }
}
