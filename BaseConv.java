import java.util.Scanner;

public final class BaseConv {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    display("Enter base to convert from : ");
    int base = input.nextInt();
    display("Enter a number : ");
    String userNumber = input.next();
    input.close();
    String newValue = toBaseTen(userNumber, base);
    display("Your number in base 10 is : " + newValue);
  }

  public static void display(String text) {
    System.out.println(text);
  }

  public static void display(char chr) {
    System.out.println(chr);
  }
  
  public static void display(int num) {
    System.out.println(num);
  }
  
  public static void display(double num) {
    System.out.println(num);
  }

  public static double power(int n, int exp) {
    double result = 1;
    if (exp > 0) {
      for (int i = 0; i < exp; i ++) {
        result *= n;
      }
    } else if (exp < 0) {
      for (int i = 0; i < -exp; i++) {
        result *= n;
      }
      result = 1 / result;
    }
    return result;
  }

  public static int hexToNum(char c) {
    char[] letters = {'a', 'b', 'c', 'd', 'e', 'f'};
    int value;
    for (int i = 0; i < letters.length; i++) {
      if (c == letters[i]) {
        value = i + 10;
        return value;
      }
    }
    value = (int) c - '0';
    return value;
  }

  public static char numToHex(int value) {
    char[] letters = {'a', 'b', 'c', 'd', 'e', 'f'};
    char c;
    if (value >= 10) {
      c = letters[value - 10];
    } else {
      c = (char) value;
    }
    return c;
  }

  public static String toBaseTen(String fullNumber, int base) {
    String[] splitNumber = fullNumber.split("\\.", 2);
    String num = splitNumber[0];
    char[] numArray = num.toCharArray();
    int numLength = numArray.length;
    double value = 0;
    double subValue;
    for (int i = 0; i < numLength; i++) {
      char n = numArray[numLength - i - 1];
      subValue = power(base, i) * hexToNum(n);
      value += subValue;
    }

    if (splitNumber.length > 1) {
      String dec = splitNumber[1];
      char[] decArray = dec.toCharArray();
      int decLength = decArray.length;
      for (int i = 0; i < decLength; i++) {
        char n = decArray[i];
        subValue = power(base, -(i + 1)) * hexToNum(n);
        value += subValue;
      }
    }
    return Double.toString(value);
  }
}