package creditcard;

import java.util.ArrayList;

public enum Company {

  VISA("Visa", "^4[0-9]{12}(?:[0-9]{3})?$"), MASTERCARD("MasterCard",
      "^5[1-5][0-9]{14}$"), MAESTRO("Maestro", "^50(?:18|20|38)\\d*$");

  private String regexPattern;
  private String prettyString;

  private Company(String prettyString, String regexPattern) {
    this.regexPattern = regexPattern;
    this.prettyString = prettyString;
  }

  @Override
  public String toString() {
    return this.prettyString;
  }

  public static Company checkCompany(long cardNumber)
      throws UnknownCompanyException, AmbiguousCompanyException {
    String number = String.valueOf(cardNumber);
    ArrayList<Company> matches = new ArrayList<>();

    for (Company c : Company.values()) {
      if (number.matches(c.regexPattern)) {
        matches.add(c);
      }
    }

    Company company;

    if (matches.size() == 1) {
      company = matches.get(0);
    }
    else if (matches.size() == 0) {
      throw new UnknownCompanyException(
          "The given creditcard number doesn't match any known company");
    }
    else {
      throw new AmbiguousCompanyException(
          "The given creditcard number matches multiple company definitions");
    }

    return company;
  }
}
