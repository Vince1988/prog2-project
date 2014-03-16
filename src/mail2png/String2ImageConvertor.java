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

public class String2ImageConvertor {

  private final static Font FONT = new Font("Trebuchet MS", Font.BOLD, 24);
  private final static Color FONT_COLOR = Color.BLACK;

  private String2ImageConvertor() {
  }

  public static BufferedImage getStringAsImage(String string) {
    return String2ImageConvertor.getStringAsImage(string, FONT);
  }

  public static BufferedImage getStringAsImage(String string, Font font) {
    BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    FontMetrics fm = img.createGraphics().getFontMetrics(font);

    int stringWidth = fm.stringWidth(string);
    int stringHeight = fm.getMaxAscent() + fm.getMaxDescent();
    int stringBaseline = fm.getMaxAscent();

    img = new BufferedImage(stringWidth, stringHeight,
        BufferedImage.TYPE_INT_ARGB);
    Graphics g = img.createGraphics();

    g.setColor(FONT_COLOR);
    g.setFont(font);
    g.drawString(string, 0, stringBaseline);

    return String2ImageConvertor.makeColorTransparent(img, FONT_COLOR);
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
