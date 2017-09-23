package Common;


/**
 * A stack with min method returns the minimum element
 * O(1) for pop, push and min
 */
public class MinStack {
  public class minStackElement {
    int val = -1;
    int min = -1;
  }

  public MinStack(int size) {
    this.capacity = size + 1;
    this.elements = new minStackElement[capacity];
    for (int i = 0; i < elements.length; i++) {
      elements[i] = new minStackElement();
    }
  }

  private int capacity = 10;
  private minStackElement[] elements = null;
  private int top = 0;

  public boolean empty() {
    return top == 0;
  }

  public int getTop() {
    return top;
  }

  public int pop(){
    if (empty()) {
      return 65535;
    }
    return elements[--top].val;
  }


  public void copyOldElements(minStackElement[] oldV, minStackElement[] newV) {
    for (int i = 0; i < oldV.length; i++) {
      newV[i].min = oldV[i].min;
      newV[i].val = oldV[i].val;
    }
  }

  public void push(int a) {
    if (top == capacity) {
      minStackElement[] newElements = new minStackElement[capacity*2];
      for (int i = 0; i < capacity *2; i++) {
        newElements[i] = new minStackElement();
      }
      copyOldElements(elements, newElements);
      elements = newElements;
      capacity = capacity *2;
    }
    elements[top].val = a;
    if (top == 0) {
      elements[top].min = a;
      top++;
      return;
    }
    if (a <= elements[top-1].val) {
      elements[top].min = a;
    } else {
      elements[top].min = elements[top-1].min;
    }
    top++;
  }
  public int min() {
    if (empty()) {
      return -1;
    }
    return elements[top-1].min;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = top - 1; i >= 0; i--) {
      s.append(elements[i].val + "-current min:" + elements[i].min + "\n");
    }
    return s.toString();
  }

  public static void main(String[] args) {
    MinStack stack = new MinStack(10);
    int[] a = {10,5,15,3,4,9,20,30,1,11};
    for (int i = 0; i < a.length; i++) {
      stack.push(a[i]);
      System.out.println("Push:" + a[i]);
      System.out.println("Top is :" + stack.getTop());
      System.out.println("Min is :" + stack.min());
      System.out.println(stack);
    }
    System.out.println(stack);
    for (int i = 9; i >= 0; i--) {
      System.out.println("Pop:" + stack.pop());
      System.out.println("Top is :" + stack.getTop());
      System.out.println("Min is :" + stack.min());
      System.out.println(stack);
    }
  }
}
