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

  public static int hexToNum(String c) {
    String[] letters = {"a", "b", "c", "d", "e", "f"};
    int value;
    
    for (int i = 0; i < letters.length; i++) {
      if (c == letters[i]) {
        value = i + 10;
        return value;
      }
    }
    value = Integer.parseInt(c);
    return value;
  }

  public static String numToHex(int value) {
    String[] letters = {"a", "b", "c", "d", "e", "f"};
    String c;
    
    if (value >= 10) {
      c = letters[value - 10];
    } else {
      c = Integer.toString(value);
    }
    return c;
  }

  public static int power(int base, int exp) {
    int result = 1;
    for (int n = 0; n < Math.abs(exp); n ++) {
      result *= base;
    }

    return result;
  }

  public static String toBaseTen(String fullNumber, int base) {
    String[] splitNumber = fullNumber.split("\\.|$", 2);
    String valueStr = "";
    String numString = splitNumber[0] + splitNumber[1];
    String[] numArray = numString.split("");
    int exp = numString.length() - 1;
    int value = 0;
    
    for (int e = exp; e >= 0; e --) {
      int subValue = power(base, e) * hexToNum(numArray[numArray.length - (e + 1)]);
      value += subValue;
    }

    // NEED TO ADD THE DECIMAL POINT SOMEWHERE HERE
    valueStr = Double.toString(value / power(base, splitNumber[1].length()));
    return valueStr;
  }

  public static String toBaseN(String fullNumber, int base) {
    String[] splitNumber = fullNumber.split("\\.", 2);
    String newNumber = "";
    String numString = splitNumber[1].equals("0") ? splitNumber[0] : splitNumber[0]  + splitNumber[1];
    int num = Integer.parseInt(numString);
    int integerPart = Integer.parseInt(splitNumber[0]);
    int exp = 0;
    int intExp = 0;
    int subTotal = 0;

    while (power(base, exp + 1) <= num) {
      exp ++;
    }

    while (power(base, intExp + 1) <= integerPart) {
      intExp ++;
    }

    for (int e = exp; e >= 0; e --) {
      if (e == exp - intExp - 1) {
        newNumber += ".";
      }

      int positionValue = power(base, e);
      int n = 0;

      while (subTotal + positionValue <= num) {
        subTotal += positionValue;
        n ++;
      }
      newNumber += numToHex(n);
    }

    return newNumber;
  }
}