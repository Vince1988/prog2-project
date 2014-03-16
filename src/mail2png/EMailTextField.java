package mail2png;

import java.awt.image.BufferedImage;

import javax.swing.JTextField;

public class EMailTextField extends JTextField {

  private static final long serialVersionUID = 1L;

  public BufferedImage getImage() {
    return String2ImageConvertor.getStringAsImage(this.isEmpty() ? " " : this
        .getText());
  }

  public boolean isEmpty() {
    return this.getText().isEmpty();
  }

}
