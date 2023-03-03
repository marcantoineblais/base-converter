import java.util.Scanner;

public final class BaseConv {
  public static void main(String[] args) {
    Scanner input;
    int startBase;
    String userNumber, baseTenValue;

    input = new Scanner(System.in);
    display("Enter base to convert from : ");
    startBase = input.nextInt();
    display("Enter a number : ");
    userNumber = input.next();
    input.close();
    baseTenValue = toBaseTen(userNumber, startBase);
    for (int base = 2; base <= 16; base ++) {
      display("Your number in base " + base + " is : " + toBaseN(baseTenValue, base));
    }
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
    String num = splitNumber[0], dec;
    char[] numArray = num.toCharArray(), decArray;
    int numLength = numArray.length, decLength;
    double value = 0;
    double subValue;
    
    for (int i = 0; i < numLength; i++) {
      char n = numArray[numLength - i - 1];
      subValue = Math.pow(base, i) * hexToNum(n);
      value += subValue;
    }

    if (splitNumber.length > 1) {
      dec = splitNumber[1];
      decArray = dec.toCharArray();
      decLength = decArray.length;
      for (int i = 0; i < decLength; i++) {
        char n = decArray[i];
        subValue = Math.pow(base, -(i + 1)) * hexToNum(n);
        value += subValue;
      }
    }
    return Double.toString(value);
  }

  public static String toBaseN(String fullNumber, int base) {
    String[] splitNumber = fullNumber.split("\\.", 2);
    String newNumber = "";
    int num = Integer.parseInt(splitNumber[0]);
    int exp = 0;
    int subTotal = 0;

    while (Math.pow(base, exp + 1) <= num) {
      exp ++;
    }

    for (int e = exp; e >= 0; e --) {
      int positionValue = (int) Math.pow(base, e);
      int n = 0;

      while (subTotal + positionValue <= num) {
        subTotal += positionValue;
        n ++;
      }
      newNumber += Integer.toString(n);
    }

    if (splitNumber.length > 1) {
      double dec;
      String decStr = "0." + splitNumber[1];
      
      dec = Double.parseDouble(decStr);
      newNumber += ".";
      subTotal = 0;

      for (int e = -1; e >= -16; e --) {
        double positionValue = Math.pow(base, e);
        int n = 0;

        while (subTotal + positionValue <= num) {
          subTotal += positionValue;
          n ++;
        }
        newNumber += n;
        if (subTotal == dec);
          break;
      }
    }

    return newNumber;
  }
}