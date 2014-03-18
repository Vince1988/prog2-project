package creditcard;

public class LuhnAlgorithm {

  private LuhnAlgorithm() {

  }

  private static int calcChecksumm(long number) {
    int checksumm = 0;
    int digits = 0;

    while (number > 0) {
      digits++;

      int digit = (int) (number % 10);
      number /= 10;

      // Double every second digit, from the rightmost
      // & sum all the individual digits
      if (digits % 2 == 0) {
        digit *= 2;
        digit = (digit % 10) + (digit / 10);
      }

      checksumm += digit;
    }

    return checksumm % 10;
  }

  public static boolean isValid(long number) {
    return LuhnAlgorithm.calcChecksumm(number) == 0;
  }

  public static int calcCheckDigit(long number) {
    int checkDigit = 10 - LuhnAlgorithm.calcChecksumm(number * 10);
    return checkDigit % 10;
  }
}
