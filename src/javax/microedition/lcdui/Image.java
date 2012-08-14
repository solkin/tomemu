package javax.microedition.lcdui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Image {

    int imgData;
    private static final int INVALID_TRANSFORM_BITS = -8;
    public java.awt.image.BufferedImage image;

    public Image(int width, int height) {
        //compiled code
        System.out.println("Compiled Code");
        if (width == 0) {
            width = 1;
        }
        if (height == 0) {
            height = 1;
        }
        image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
    }

    public static Image createImage(int width, int height) {
        //compiled code
        System.out.println("Compiled Code");
        return new Image(width, height);
    }

    public static Image createImage(Image source) {
        //compiled code
        System.out.println("Compiled Code");
        Image image = null;
        try {
            image = (Image) source.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    public static Image createImage(String name) throws IOException {
        //compiled code
        System.out.println("Compiled Code");
        Image image = new Image(1, 1);
        try {
            image.image = ImageIO.read(Class.forName("javax.microedition.lcdui.Image").getResourceAsStream(name));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    public static Image createImage(byte[] imageData, int imageOffset, int imageLength) {
        //compiled code
        System.out.println("Compiled Code");
        return null;
    }

    public static Image createImage(Image image, int x, int y, int width, int height, int transform) {
        //compiled code
        System.out.println("Compiled Code");
        return null;
    }

    public Graphics getGraphics() {
        //compiled code
        System.out.println("Compiled Code");
        return new Graphics(image.getGraphics(), 0, 0);
    }

    public int getWidth() {
        //compiled code
        System.out.println("Compiled Code");
        return image.getWidth();
    }

    public int getHeight() {
        //compiled code
        System.out.println("Compiled Code");
        return image.getHeight();
    }

    public boolean isMutable() {
        //compiled code
        System.out.println("Compiled Code");
        return false;
    }

    public static Image createImage(InputStream stream) throws IOException {
        //compiled code
        System.out.println("Compiled Code");
        Image image = new Image(1, 1);
        image.image = ImageIO.read(stream);
        return image;
    }

    public static Image createRGBImage(int[] rgb, int width, int height, boolean processAlpha) {
        //compiled code
        System.out.println("Compiled Code");
        BufferedImage bImage = new java.awt.image.BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        bImage.setRGB(0, 0, width, height, rgb, 0, width);
        Image iImage = new Image(1,1);
        iImage.image = bImage;
        return iImage;
    }

    public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
        rgbData=image.getRGB(x, y, width, height, null, offset, scanlength);
    }
}
