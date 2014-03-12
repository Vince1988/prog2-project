package mail2png;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class EMailImagePanel extends JPanel implements KeyListener {

  private static final long serialVersionUID = 1L;

  private final static int WIDTH = 400;
  private final static int HEIGTH = 200;

  private JTextField addressInput;
  private String2ImageConvertor imageConvertor;

  public EMailImagePanel() {
    this.setLayout(null);
    this.setPreferredSize(new Dimension(EMailImagePanel.WIDTH,
        EMailImagePanel.HEIGTH));

    this.addressInput = new JTextField();
    this.imageConvertor = new String2ImageConvertor(this.addressInput);

    this.addressInput.setBounds(10, 10, EMailImagePanel.WIDTH - 20, 20);
    this.addressInput.setHorizontalAlignment(JTextField.CENTER);
    this.addressInput.addKeyListener(this);

    this.add(this.addressInput);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!this.addressInput.getText().isEmpty()) {
      BufferedImage mailImage = this.imageConvertor.getMailImage();

      g.drawImage(mailImage, EMailImagePanel.WIDTH / 2 - mailImage.getWidth()
          / 2, 80, null);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    this.repaint();
  }

  @Override
  public void keyReleased(KeyEvent key) {
    if (key.getKeyCode() == KeyEvent.VK_ENTER
        && !this.addressInput.getText().isEmpty()) {
      System.out.println("ENTER");
      this.imageConvertor.saveMailImage();
    }
  }

}
