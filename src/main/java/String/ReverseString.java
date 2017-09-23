package String;

/**
 * Created by ztang16 on 3/20/2017.
 */
public class ReverseString {
  public static void main(String[] args) {
    String s = new String("originab");
    char[] origin = s.toCharArray();
    System.out.println(origin);
    char temp = ' ';
    int i = 0;
    int j = origin.length - 1;
    while (i < j) {
      temp = origin[j];
      origin[j] = origin[i];
      origin[i] = temp;
      i++;
      j--;
    }
    String newString = new String(origin);
    System.out.println(newString);
  }
}
