package mail2png;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EMailImagePanel extends JPanel implements KeyListener,
    ActionListener {

  private static final long serialVersionUID = 1L;

  private final static int WIDTH = 400;
  private final static int HEIGTH = 200;

  private final static int MARGIN = 10;

  private String2ImageConvertor imageConvertor;

  private JFileChooser fileChooser;
  private JTextField addressInput;
  private JButton saveButton;

  public EMailImagePanel() {
    this.setLayout(null);
    this.setPreferredSize(new Dimension(EMailImagePanel.WIDTH,
        EMailImagePanel.HEIGTH));

    this.fileChooser = new JFileChooser();
    this.addressInput = new JTextField();
    this.saveButton = new JButton("Save PNG");
    this.imageConvertor = new String2ImageConvertor(this.addressInput);

    this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    this.addressInput.setBounds(MARGIN, MARGIN, WIDTH - MARGIN * 2, 20);
    this.addressInput.setHorizontalAlignment(JTextField.CENTER);
    this.addressInput.addKeyListener(this);

    this.saveButton.setBounds(MARGIN, HEIGTH - MARGIN - 20, WIDTH - MARGIN * 2,
        20);
    this.saveButton.addActionListener(this);

    this.add(this.addressInput);
    this.add(this.saveButton);
  }

  private void save() {
    if (!this.addressInput.getText().isEmpty()) {
      this.fileChooser.setSelectedFile(new File(this.addressInput.getText()
          + ".png"));
      int returnValue = this.fileChooser.showSaveDialog(this);

      switch (returnValue) {
      case JFileChooser.APPROVE_OPTION:
        File file = this.fileChooser.getSelectedFile();
        this.imageConvertor.saveMailImage(file);
        break;
      default:
        break;
      }

    }
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
