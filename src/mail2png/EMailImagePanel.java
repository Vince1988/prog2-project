package mail2png;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EMailImagePanel extends JPanel implements KeyListener,
    ActionListener {

  private static final long serialVersionUID = 1L;

  private final static int P_WIDTH = 400;
  private final static int P_HEIGHT = 200;
  private final static int P_MARGIN = 10;

  private EMailTextField addressInput;
  private JButton saveButton;

  public EMailImagePanel() {
    this.setLayout(null);
    this.setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
    this.setSize(this.getPreferredSize());

    int inputHeight = (P_HEIGHT / 3) - (4 * P_MARGIN);
    int inputWidth = P_WIDTH - (2 * P_MARGIN);

    // Add input for email address
    this.addressInput = new EMailTextField();
    this.addressInput.addKeyListener(this);
    this.addressInput.setBounds(P_MARGIN, P_MARGIN, inputWidth, inputHeight);
    this.addressInput.setHorizontalAlignment(JTextField.CENTER);
    this.add(this.addressInput);

    // Add save button
    this.saveButton = new JButton("Save PNG");
    this.saveButton.addActionListener(this);
    this.saveButton.setBounds(P_MARGIN, P_HEIGHT - P_MARGIN - inputHeight,
        inputWidth, inputHeight);
    this.add(this.saveButton);
  }

  private void save() {
    if (this.addressInput.isEmpty()) {
      JOptionPane.showMessageDialog(this, "You need to input an email address",
          "Address Missing", JOptionPane.ERROR_MESSAGE);
    }
    else {
      JFileChooser fc = new EMailFileChooser();
      fc.setSelectedFile(new File(this.addressInput.getText() + ".png"));

      switch (fc.showSaveDialog(this)) {
      case JFileChooser.APPROVE_OPTION:
        File file = fc.getSelectedFile();
        this.saveAs(file);
        break;
      default:
        break;
      }
    }
  }

  private void saveAs(File file) {
    if (!file.getName().toLowerCase().endsWith(".png")) {
      file = new File(file + ".png");
    }

    try {
      ImageIO.write(this.addressInput.getImage(), "PNG", file);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    JOptionPane.showMessageDialog(this, "File saved as: " + file.getName(),
        "File saved!", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    BufferedImage mailImage = this.addressInput.getImage();

    g.drawImage(mailImage, EMailImagePanel.P_WIDTH / 2 - mailImage.getWidth()
        / 2, 80, null);
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
    if (key.getKeyCode() == KeyEvent.VK_ENTER) {
      this.save();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(this.saveButton)) {
      this.save();
    }
  }

}
