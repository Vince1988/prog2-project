package creditcard;

public class UnknownCompanyException extends Exception {

  private static final long serialVersionUID = 1L;

  public UnknownCompanyException(String message) {
    super(message);
  }

}
