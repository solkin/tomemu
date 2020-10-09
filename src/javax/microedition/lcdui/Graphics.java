package javax.microedition.lcdui;

import utils.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphics {

    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    public static final int BASELINE = 64;
    public static final int SOLID = 0;
    public static final int DOTTED = 1;
    private int transX;
    private int transY;
    private short clipX1;
    private short clipY1;
    private int clipX2;
    private int clipY2;
    private int translatedX1;
    private int translatedY1;
    private int translatedX2;
    private int translatedY2;
    private short[] clip;
    private boolean clipped;
    private int rgbColor;
    private int gray;
    private int pixel;
    private int style;
    private Font currentFont;
    private short maxWidth;
    private short maxHeight;
    Image destination;
    /**
     * J2SE
     **/
    public java.awt.Graphics g;
    Font font;
    BufferedImage bi;

    private native void init();

    public Graphics(java.awt.Graphics g, int w, int h) {
        this.g = g;
    }

    public void translate(int x, int y) {
        g.translate(x, y);
    }

    public int getTranslateX() {
        //compiled code
        Logger.println("Compiled Code");
        return 0;
    }

    public int getTranslateY() {
        //compiled code
        Logger.println("Compiled Code");
        return 0;
    }

    public int getColor() {
        return g.getColor().getRGB();
    }

    public int getRedComponent() {
        return g.getColor().getRed();
    }

    public int getGreenComponent() {
        return g.getColor().getGreen();
    }

    public int getBlueComponent() {
        return g.getColor().getBlue();
    }

    public int getGrayScale() {
        return (int) (g.getColor().getRed() * 0.3
                + +g.getColor().getGreen() * 0.3
                + g.getColor().getBlue() * 0.3);
    }

    public void setColor(int red, int green, int blue) {
        g.setColor(new Color(red, green, blue));
    }

    public void setColor(int RGB) {
        g.setColor(new Color(RGB));
    }

    public void setGrayScale(int value) {
        g.setColor(new Color(value, value, value));
    }

    public Font getFont() {
        return font;
    }

    public void setStrokeStyle(int style) {
        //compiled code
        Logger.println("Compiled Code");
    }

    public int getStrokeStyle() {
        //compiled code
        Logger.println("Compiled Code");
        return 0;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getClipX() {
        return g.getClip().getBounds().x;
    }

    public int getClipY() {
        return g.getClip().getBounds().y;
    }

    public int getClipWidth() {
        return g.getClip().getBounds().width;
    }

    public int getClipHeight() {
        return g.getClip().getBounds().height;
    }

    public void clipRect(int x, int y, int width, int height) {
        g.clipRect(x, y, width, height);
    }

    public void setClip(int x, int y, int width, int height) {
        g.setClip(x, y, width, height);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    public void fillRect(int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawString(String str, int x, int y, int anchor) {
        g.drawString(str, x, y + font.getBaselinePosition());
    }

    public void drawSubstring(String str, int offset, int len, int x, int y, int anchor) {
        g.drawString(str.substring(offset, offset + len), x, y);
    }

    public void drawChar(char character, int x, int y, int anchor) {
    }

    public void drawChars(char[] data, int offset, int length, int x, int y, int anchor) {
    }

    public void drawImage(Image img, int x, int y, int anchor) {
        Logger.println("Painting image " + img.image.getWidth());
        if (anchor == (Graphics.TOP | Graphics.LEFT)) {
            g.drawImage(img.image, x, y, null);
        } else if (anchor == (Graphics.VCENTER | Graphics.HCENTER)) {
            g.drawImage(img.image, x - img.getWidth() / 2, y - img.getHeight() / 2, null);
        } else if (anchor == (Graphics.VCENTER | Graphics.LEFT)) {
            g.drawImage(img.image, x, y - img.getHeight() / 2, null);
        } else {
            g.drawImage(img.image, x, y, null);
        }
    }

    public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor) {
        g.drawImage(src.image, x_dest, y_dest, x_dest + width, y_dest + height,
                x_src, y_src, x_src + width, y_src + height, null);
    }

    public void copyArea(int x_src, int y_src, int width, int height, int x_dest, int y_dest, int anchor) {
        g.copyArea(x_src, y_src, width, height, x_dest, y_dest);
    }

    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
    }

    private void doCopyArea(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
    }

    public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
        bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        bi.setRGB(0, 0, width, height, rgbData, offset, scanlength);
        g.drawImage(bi, x, y, null);
    }

    public int getDisplayColor(int color) {
        return 0;
    }

    static Graphics getGraphics(Image img) {
        return img.getGraphics();
    }

    void reset(int x1, int y1, int x2, int y2) {
        //compiled code
        Logger.println("Compiled Code");

    }

    void reset() {
        //compiled code
        Logger.println("Compiled Code");
    }

    private static int grayVal(int red, int green, int blue) {
        //compiled code
        Logger.println("Compiled Code");
        return 0;
    }

    private int getPixel(int arg0, int arg1, boolean arg2) {
        return 0;
    }
}
