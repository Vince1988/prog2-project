package mail2png;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

public class String2ImageConvertor {

  private final static Font FONT = new Font("Trebuchet MS", Font.BOLD, 24);
  private final static Color FONT_COLOR = Color.BLACK;

  private JTextField input;
  private BufferedImage buffImg;
  private FontMetrics fm;

  public String2ImageConvertor(JTextField input) {
    this.input = input;

    this.buffImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    this.fm = this.buffImg.createGraphics().getFontMetrics(FONT);
  }

  public BufferedImage getMailImage() {
    String mailAddress = this.input.getText();

    int addressWidth = fm.stringWidth(mailAddress);
    int addressHeight = fm.getMaxAscent() + fm.getMaxDescent();
    int addressBaseline = fm.getMaxAscent();

    this.buffImg = new BufferedImage(addressWidth, addressHeight,
        BufferedImage.TYPE_INT_ARGB);
    Graphics g = this.buffImg.createGraphics();

    g.setColor(FONT_COLOR);
    g.setFont(FONT);
    g.drawString(mailAddress, 0, addressBaseline);

    return String2ImageConvertor.makeColorTransparent(this.buffImg, FONT_COLOR);
  }

  public void saveMailImage(File file) {
    try {
      ImageIO.write(this.getMailImage(), "PNG", file);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static BufferedImage image2BufferedImage(Image img) {
    BufferedImage buffImg = new BufferedImage(img.getWidth(null),
        img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics g = buffImg.getGraphics();
    g.drawImage(img, 0, 0, null);
    g.dispose();

    return buffImg;
  }

  private static BufferedImage makeColorTransparent(BufferedImage img,
      Color color) {
    ImageProducer ip = new FilteredImageSource(img.getSource(),
        new EMailImageFilter(color));
    Image transparentImage = Toolkit.getDefaultToolkit().createImage(ip);
    return String2ImageConvertor.image2BufferedImage(transparentImage);
  }

}
