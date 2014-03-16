package mail2png;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mail2PNG extends JFrame {

  private static final long serialVersionUID = 1L;

  private final static String NAME = "Mail --> PNG";

  public Mail2PNG() {
    this.setTitle(Mail2PNG.NAME);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setIconImage(String2ImageConvertor.getStringAsImage("@", new Font(
        "Trebuchet MS", Font.BOLD, 20)));
    this.setVisible(true);
    this.setResizable(false);

    JPanel panel = new EMailImagePanel();
    this.setContentPane(panel);

    this.pack();
    this.setLocationRelativeTo(null);
  }

  public static void main(String[] args) {
    new Mail2PNG();
  }

}
