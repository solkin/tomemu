package com.tomclaw.utils;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2012
 * http://www.tomclaw.com/
 * @author Solkin
 */
public class DrawUtil {

    public static int smoothIndex = 1;
    public static boolean isHighQuality = false;
    public static boolean isLightGraphics = false;
    /**
     * Primary colors
     */
    private static int pRed__ = 0;
    private static int pGreen = 0;
    private static int pBlue_ = 0;
    /**
     * Secondary colors
     */
    private static int sRed__ = 0;
    private static int sGreen = 0;
    private static int sBlue_ = 0;
    /**
     * Runtime
     */
    public static int[] rgbData;

    public static void fillSharpVerticalGradient(Graphics g, int objX, int objY, int objWidth, int objHeight, int color1, int color2, int color3, int color4, int divPrc) {
        if (isLightGraphics) {
            g.setColor(color2);
            g.fillRect(objX + 1, objY, objWidth - 2, objHeight);
            g.fillRect(objX, objY + 1, objWidth, objHeight - 2);
        } else {
            int objHeightHalf = (objHeight * divPrc) / 100;
            fillVerticalGradient(g, objX, objY, objWidth, objHeightHalf + 1, color1, color2);
            objY += objHeightHalf;
            fillVerticalGradient(g, objX, objY, objWidth, objHeight - objHeightHalf, color3, color4);
        }
    }

    public static void fillVerticalGradient(Graphics g, int objX, int objY, int objWidth, int objHeight, int color1, int color2) {
        if (isLightGraphics) {
            /*g.setColor(color2);
            g.fillRect(objX + 1, objY, objWidth - 2, objHeight);
            g.fillRect(objX, objY + 1, objWidth, objHeight - 2);*/
            g.setColor(color2);
            g.fillRect(objX, objY, objWidth + 1, objHeight);
            g.setColor(color1);
            g.fillRect(objX + 2, objY + 2, objWidth - 4 + 1, objHeight / 2);
        } else {
            /**
             * Primary colors
             */
            pRed__ = (color1 & 0xFF0000) >> 16;
            pGreen = (color1 & 0x00FF00) >> 8;
            pBlue_ = (color1 & 0x0000FF);
            /**
             * Secondary colors
             */
            sRed__ = (color2 & 0xFF0000) >> 16;
            sGreen = (color2 & 0x00FF00) >> 8;
            sBlue_ = (color2 & 0x0000FF);
            /**
             * Drawing gradient
             */
            for (int y = 0; y < objHeight; y++) {
                g.setColor((sRed__ - pRed__) * y / objHeight + pRed__,
                        (sGreen - pGreen) * y / objHeight + pGreen,
                        (sBlue_ - pBlue_) * y / objHeight + pBlue_);
                g.drawLine(objX, objY + y, objX + objWidth, objY + y);
            }
        }
    }

    public static void drawVerticalGradientBorder(Graphics g, int objX, int objY, int objWidth, int objHeight, int color1, int color2) {
        if (isLightGraphics) {
            g.setColor(color2);
            g.drawRect(objX + 1, objY, objWidth - 2, objHeight);
            g.drawRect(objX, objY + 1, objWidth, objHeight - 2);
        } else {
            /**
             * Drawing horizontal lines
             */
            g.setColor(color1);
            g.drawLine(objX, objY, objX + objWidth, objY);
            g.setColor(color2);
            g.drawLine(objX, objY + objHeight, objX + objWidth, objY + objHeight);
            /**
             * Drawing vertical lines
             */
            /**
             * Primary colors
             */
            pRed__ = (color1 & 0xFF0000) >> 16;
            pGreen = (color1 & 0x00FF00) >> 8;
            pBlue_ = (color1 & 0x0000FF);
            /**
             * Secondary colors
             */
            sRed__ = (color2 & 0xFF0000) >> 16;
            sGreen = (color2 & 0x00FF00) >> 8;
            sBlue_ = (color2 & 0x0000FF);
            /**
             * Drawing gradient
             */
            for (int y = 0; y < objHeight; y++) {
                g.setColor((sRed__ - pRed__) * y / objHeight + pRed__,
                        (sGreen - pGreen) * y / objHeight + pGreen,
                        (sBlue_ - pBlue_) * y / objHeight + pBlue_);
                g.drawLine(objX, objY + y, objX, objY + y);
                g.drawLine(objX + objWidth, objY + y, objX + objWidth, objY + y);
            }
        }
    }

    public static void fillSharpHorizontalGradient(Graphics g, int objX, int objY, int objWidth, int objHeight, int color1, int color2, int color3, int color4) {
        if (isLightGraphics) {
            g.setColor(color2);
            g.fillRect(objX + 1, objY, objWidth - 2, objHeight);
            g.fillRect(objX, objY + 1, objWidth, objHeight - 2);
        } else {
            int objWidthHalf = objWidth / 2;
            fillHorizontalGradient(g, objX, objY, objWidthHalf, objHeight, color1, color2);
            objX += objWidthHalf;
            fillHorizontalGradient(g, objX, objY, objWidthHalf, objHeight, color3, color4);
        }
    }

    public static void fillHorizontalGradient(Graphics g, int objX, int objY, int objWidth, int objHeight, int color1, int color2) {
        if (isLightGraphics) {
            g.setColor(color2);
            g.drawRect(objX + 1, objY, objWidth - 2, objHeight);
            g.drawRect(objX, objY + 1, objWidth, objHeight - 2);
        } else {
            /**
             * Primary colors
             */
            pRed__ = (color1 & 0xFF0000) >> 16;
            pGreen = (color1 & 0x00FF00) >> 8;
            pBlue_ = (color1 & 0x0000FF);
            /**
             * Secondary colors
             */
            sRed__ = (color2 & 0xFF0000) >> 16;
            sGreen = (color2 & 0x00FF00) >> 8;
            sBlue_ = (color2 & 0x0000FF);
            /**
             * Drawing gradient
             */
            for (int x = 0; x < objWidth; x++) {
                g.setColor((sRed__ - pRed__) * x / objWidth + pRed__,
                        (sGreen - pGreen) * x / objWidth + pGreen,
                        (sBlue_ - pBlue_) * x / objWidth + pBlue_);
                g.drawLine(objX + x, objY, objX + x, objY + objHeight);
            }
        }
    }

    public static void drawHorizontalGradientBorder(Graphics g, int objX, int objY, int objWidth, int objHeight, int color1, int color2) {
        if (isLightGraphics) {
            g.setColor(color2);
            g.drawRect(objX + 1, objY, objWidth - 2, objHeight);
            g.drawRect(objX, objY + 1, objWidth, objHeight - 2);
        } else {
            /**
             * Drawing horizontal lines
             */
            g.setColor(color1);
            g.drawLine(objX, objY, objX, objY + objHeight);
            g.setColor(color2);
            g.drawLine(objX + objWidth, objY, objX + objWidth, objY + objHeight);
            /**
             * Drawing vertical lines
             */
            /**
             * Primary colors
             */
            pRed__ = (color1 & 0xFF0000) >> 16;
            pGreen = (color1 & 0x00FF00) >> 8;
            pBlue_ = (color1 & 0x0000FF);
            /**
             * Secondary colors
             */
            sRed__ = (color2 & 0xFF0000) >> 16;
            sGreen = (color2 & 0x00FF00) >> 8;
            sBlue_ = (color2 & 0x0000FF);
            /**
             * Drawing gradient
             */
            for (int x = 0; x < objWidth; x++) {
                g.setColor((sRed__ - pRed__) * x / objWidth + pRed__,
                        (sGreen - pGreen) * x / objWidth + pGreen,
                        (sBlue_ - pBlue_) * x / objWidth + pBlue_);
                g.drawLine(objX + x, objY, objX + x, objY);
                g.drawLine(objX + x, objY + objHeight, objX + x, objY + objHeight);
            }
        }
    }

    public static Image smoothImage(Image sourceImage, int imageX, int imageY, int imageWidth, int imageHeight) {
        imageWidth -= imageX;
        imageHeight -= imageY;
        int[] rgbData = new int[imageWidth * imageHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, imageX, imageY, imageWidth, imageHeight);
        int currY;
        int prevY;
        int nextY;
        int r;
        int g;
        int b;
        int currPoint;
        int prevYPoint;
        int prevXPoint;
        int nextYPoint;
        int nextXPoint;
        for (int y = 1; y < imageHeight; y++) {
            prevY = (y - 1) * imageWidth;
            currY = y * imageWidth;
            if (y + 1 < imageHeight) {
                nextY = (y + 1) * imageWidth;
            } else {
                nextY = currY;
            }
            for (int x = 1; x < imageWidth - 1; x++) {
                currPoint = rgbData[currY + x];
                prevYPoint = rgbData[prevY + x];
                prevXPoint = rgbData[currY + x - 1];
                nextYPoint = rgbData[nextY + x];
                nextXPoint = rgbData[currY + x + 1];
                b = ((currPoint & 0xff0000) + (prevYPoint & 0xff0000) + (prevXPoint & 0xff0000) + (nextYPoint & 0xff0000) + (nextXPoint & 0xff0000)) / 5 & 0xff0000;
                g = ((currPoint & 0xff00) + (prevYPoint & 0xff00) + (prevXPoint & 0xff00) + (nextYPoint & 0xff00) + (nextXPoint & 0xff00)) / 5 & 0xff00;
                r = ((currPoint & 0xff) + (prevYPoint & 0xff) + (prevXPoint & 0xff) + (nextYPoint & 0xff) + (nextXPoint & 0xff)) / 5 & 0xff;
                rgbData[currY + x] = r | g | b;
            }
        }
        return Image.createRGBImage(rgbData, imageWidth, imageHeight, false);
    }

    public static Image opaqueImage(Image sourceImage, Image __destImage, int prcSrcToDest) {
        if (prcSrcToDest > 100) {
            prcSrcToDest = 100;
        } else if (prcSrcToDest < 0) {
            prcSrcToDest = 0;
        }
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int[] rgbData = new int[imageWidth * imageHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int[] rgbDest = new int[imageWidth * imageHeight];
        __destImage.getRGB(rgbDest, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int currY;
        int r;
        int g;
        int b;
        int currPoint;
        int destPoint;
        int prcDestToSrc = 100 - prcSrcToDest;
        for (int y = 0; y < imageHeight; y++) {
            currY = y * imageWidth;
            for (int x = 0; x < imageWidth; x++) {
                currPoint = rgbData[currY + x];
                destPoint = rgbDest[currY + x];

                b = ((currPoint & 0xff0000) * prcSrcToDest + (destPoint & 0xff0000) * prcDestToSrc) / 100 & 0xff0000;
                g = ((currPoint & 0xff00) * prcSrcToDest + (destPoint & 0xff00) * prcDestToSrc) / 100 & 0xff00;
                r = ((currPoint & 0xff) * prcSrcToDest + (destPoint & 0xff) * prcDestToSrc) / 100 & 0xff;

                rgbData[currY + x] = r | g | b;
            }
        }
        return Image.createRGBImage(rgbData, imageWidth, imageHeight, false);
    }

    public static Image resizeImageWithAlphaProportional(Image sourceImage, int prcSize, int prcAlpha) {
        if (prcSize > 100) {
            prcSize = 100;
        } else if (prcSize <= 0) {
            return Image.createImage(1, 1);
        }
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int destWidth = imageWidth * prcSize / 100;
        int destHeight = imageHeight * prcSize / 100;

        int[] rgbData = new int[imageWidth * imageHeight];
        int[] rgbDest = new int[destWidth * destHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int currY;
        int xx = 0;
        int yy = 0;
        int currPoint;
        int a, r, g, b;
        for (int y = 0; y < destHeight; y++) {
            currY = y * destWidth;
            xx = 0;
            for (int x = 0; x < destWidth; x++) {
                try {
                    // rgbDest[currY + x] = rgbData[(yy * 100 + xx) / 100];
                    currPoint = rgbData[(yy * 100 + xx) / 100];
                    a = currPoint & 0xff000000;
                    r = currPoint & 0xff;
                    g = currPoint & 0xff00;
                    b = currPoint & 0xff0000;
                    if (a == 0xff000000) {
                        a = 256;
                    } else {
                        a >>= 24;
                    }
                    a -= (255 * prcAlpha) / 100;
                    if (a >= 256) {
                        a = 0xff000000;
                    } else {
                        a <<= 24;
                    }
                    rgbDest[currY + x] = a | r | g | b;
                } catch (Throwable ex) {
                    // // System.out.println(xx + ", " + yy);
                }
                xx += 10000 / prcSize;
            }
            yy = (y * imageHeight / destHeight) * imageWidth;
        }
        return Image.createRGBImage(rgbDest, destWidth, destHeight, true);
    }

    public static Image resizeImageProportional(Image sourceImage, int prcSize) {
        if (prcSize > 100) {
            prcSize = 100;
        } else if (prcSize <= 0) {
            return Image.createImage(1, 1);
        }
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int destWidth = imageWidth * prcSize / 100;
        int destHeight = imageHeight * prcSize / 100;

        int[] rgbData = new int[imageWidth * imageHeight];
        int[] rgbDest = new int[destWidth * destHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int currY;
        int xx = 0;
        int yy = 0;
        for (int y = 0; y < destHeight; y++) {
            currY = y * destWidth;
            xx = 0;
            for (int x = 0; x < destWidth; x++) {
                try {
                    rgbDest[currY + x] = rgbData[(yy * 100 + xx) / 100];//(y * imageWidth + x) * 100 / prcSize
                } catch (Throwable ex) {
                    // // System.out.println(xx + ", " + yy);
                }
                xx += 10000 / prcSize;
            }
            yy = (y * imageHeight / destHeight) * imageWidth;
        }
        return Image.createRGBImage(rgbDest, destWidth, destHeight, false);
    }

    public static Image resizeImageSmoothProportional(Image sourceImage, int prcSize) {
        if (prcSize > 100) {
            prcSize = 100;
        } else if (prcSize <= 0) {
            return Image.createImage(1, 1);
        }
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int destWidth = imageWidth * prcSize / 100;
        int destHeight = imageHeight * prcSize / 100;

        int[] rgbData = new int[imageWidth * imageHeight];
        int[] rgbDest = new int[destWidth * destHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);

        int[] lines = new int[destWidth * imageHeight];
        int[] columns = new int[destWidth * destHeight];

        ///Быстрый алгоритм
        if (destWidth < imageWidth) {
            for (int k = 0; k < imageHeight; k++) { // trough all lines
                int i = k * imageWidth; // index in old pix
                int j = k * destWidth; // index in new pix
                int part = destWidth;
                int addon = 0, r = 0, g = 0, b = 0, a = 0;
                for (int m = 0; m < destWidth; m++) { ///OPTI ijm!!! need???
                    int total = imageWidth;
                    int R = 0, G = 0, B = 0, A = 0;
                    if (addon != 0) {
                        R = r * addon;
                        G = g * addon;
                        B = b * addon;
                        A = a * addon;
                        total -= addon;
                    }
                    while (0 < total) {
                        a = (rgbData[i] >> 24) & 0xff;
                        r = (rgbData[i] >> 16) & 0xff;
                        g = (rgbData[i] >> 8) & 0xff;
                        b = rgbData[i++] & 0xff;
                        if (total > part) {
                            R += r * part;
                            G += g * part;
                            B += b * part;
                            A += a * part;
                        } else {
                            R += r * total;
                            G += g * total;
                            B += b * total;
                            A += a * total;
                            addon = part - total;
///set new pixel
                            lines[j++] = ((R / imageWidth) << 16) | ((G / imageWidth) << 8)
                                    | (B / imageWidth) | ((A / imageWidth) << 24); // A??
                        }
                        total -= part;
                    }
                }
            }
        } else { /// destWidth > imageWidth
            int part = imageWidth;
            for (int k = 0; k < imageHeight; k++) { // trough all lines
                int i = k * imageWidth; // index in old pix
                int j = k * destWidth; // index in new pix
                int total = 0;
                int r = 0, g = 0, b = 0, a = 0;
                for (int m = 0; m < destWidth; m++) {
                    int R = 0, G = 0, B = 0, A = 0;
                    if (total >= part) {
                        R = r * part;
                        G = g * part;
                        B = b * part;
                        A = a * part;
                        total -= part;
                    } else {
                        if (0 != total) {
                            R = r * total;
                            G = g * total;
                            B = b * total;
                            A = a * total;
                        }
                        a = (rgbData[i] >> 24) & 0xff;
                        r = (rgbData[i] >> 16) & 0xff;
                        g = (rgbData[i] >> 8) & 0xff;
                        b = rgbData[i++] & 0xff;
                        int addon = part - total;
                        R += r * addon;
                        G += g * addon;
                        B += b * addon;
                        A += a * addon;
                        total = destWidth - addon;
                    }
///set new pixel
                    lines[j++] = ((R / imageWidth) << 16) | ((G / imageWidth) << 8)
                            | (B / imageWidth) | ((A / imageWidth) << 24); // A??
                }
            }
        }
/// проходим по столбцам
        if (destHeight < imageHeight) {
            for (int k = 0; k < destWidth; k++) { // trough columns
                int i = k; // index in lines pix
                int j = k; // index in new pix
                int part = destHeight;
                int addon = 0, r = 0, g = 0, b = 0, a = 0;
                for (int m = 0; m < destHeight; m++) {
                    int total = imageHeight;
                    int R = 0, G = 0, B = 0, A = 0;
                    if (addon != 0) {
                        R = r * addon;
                        G = g * addon;
                        B = b * addon;
                        A = a;//*addon;
                        total -= addon;
                    }
                    while (0 < total) {
//            a = (lines[i] >> 24) & 0xff;// may no rotate
                        a = lines[i] & 0xff000000;
                        r = (lines[i] >> 16) & 0xff;
                        g = (lines[i] >> 8) & 0xff;
                        b = lines[i] & 0xff;
                        i += destWidth;
                        if (total > part) {
                            R += r * part;
                            G += g * part;
                            B += b * part;
                            A += a;//*part;
                        } else {
                            R += r * total;
                            G += g * total;
                            B += b * total;
                            A += a;//*total;
                            addon = part - total;
///set new pixel
                            if (0 != A) {
                                columns[j] = ((R / imageHeight) << 16) | ((G / imageHeight) << 8)
                                        | (B / imageHeight) | 0xff000000; // A??
                            } else {
                                columns[j] = 0;//((R/imageHeight)<<16)|((G/imageHeight)<<8)|(B/imageHeight); // A??
                            }
                            j += destWidth;
                        }
                        total -= part;
                    }
                }
            }
        } else {
            int part = imageHeight;
            for (int k = 0; k < destWidth; k++) { // trough all lines
                int i = k; // index in old pix
                int j = k; // index in new pix
                int total = 0;
                int r = 0, g = 0, b = 0, a = 0;
                for (int m = 0; m < destHeight; m++) {
                    int R = 0, G = 0, B = 0, A = 0;
                    if (total >= part) {
                        R = r * part;
                        G = g * part;
                        B = b * part;
                        A = a;//*part;
                        total -= part;
                    } else {
                        if (0 != total) {
                            R = r * total;
                            G = g * total;
                            B = b * total;
                            A = a;//*total;
                        }
//            a = (lines[i] >> 24) & 0xff;// may no rotate
                        a = lines[i] & 0xff000000;
                        r = (lines[i] >> 16) & 0xff;
                        g = (lines[i] >> 8) & 0xff;
                        b = lines[i] & 0xff;
                        i += destWidth;
                        int addon = part - total;
                        R += r * addon;
                        G += g * addon;
                        B += b * addon;
                        A += a;//*addon;
                        total = destHeight - addon;
                    }
///set new pixel
                    if (0 != A) {
                        columns[j] = ((R / imageHeight) << 16) | ((G / imageHeight) << 8)
                                | (B / imageHeight) | 0xff000000; // A??
                    } else {
                        columns[j] = 0;//((R/imageHeight)<<16)|((G/imageHeight)<<8)|(B/imageHeight);
                    }
                    j += destWidth;
                }
            }
        }
        return Image.createRGBImage(columns, destWidth, destHeight, true);
    }

    public static Image blendColorAndImage(Image sourceImage, int blendColor) {
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int[] rgbData = new int[imageWidth * imageHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int a;
        int r;
        int g;
        int b;
        pRed__ = (blendColor & 0x0000ff);
        pGreen = (blendColor & 0x00ff00) >> 8;
        pBlue_ = (blendColor & 0xff0000) >> 16;
        //System.out.println("[" + pRed__ + ", " + pGreen + ", " + pBlue_ + "]");
        int currPoint;
        for (int y = 0; y < imageHeight * imageWidth; y++) {
            currPoint = rgbData[y];
            r = currPoint & 0x0000ff;
            g = (currPoint & 0x00ff00) >> 8;
            b = (currPoint & 0xff0000) >> 16;

            r = ((r + pRed__) / 2);
            g = ((g + pGreen) / 2);
            b = ((b + pBlue_) / 2);
            // System.out.println("["+r+", " + g+", " + b+"]");

            rgbData[y] = r | g << 8 | b << 16;
        }
        return Image.createRGBImage(rgbData, imageWidth, imageHeight, false);
    }

    public static Image grayScaleImage(Image sourceImage) {
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int[] rgbData = new int[imageWidth * imageHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int r;
        int g;
        int b;
        int currPoint;
        int grayColor;
        for (int y = 0; y < imageHeight * imageWidth; y++) {
            currPoint = rgbData[y];

            r = currPoint & 0x0000ff;
            g = (currPoint & 0x00ff00) >> 8;
            b = (currPoint & 0xff0000) >> 16;

            grayColor = (r * 76 / 255 + g * 149 / 255 + b * 29 / 255);

            rgbData[y] = grayColor | grayColor << 8 | grayColor << 16;
        }
        return Image.createRGBImage(rgbData, imageWidth, imageHeight, false);
    }

    public static Image setAlphaToImage(Image sourceImage, int prcAlpha) {
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int[] rgbData = new int[imageWidth * imageHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int a;
        int r;
        int g;
        int b;
        int currPoint;
        for (int y = 0; y < imageHeight * imageWidth; y++) {
            currPoint = rgbData[y];
            a = currPoint & 0xff000000;
            r = currPoint & 0xff;
            g = currPoint & 0xff00;
            b = currPoint & 0xff0000;
            if (a == 0xff000000) {
                a = 256;
            } else {
                a >>= 24;
            }
            a -= (255 * prcAlpha) / 100;
            if (a >= 256) {
                a = 0xff000000;
            } else {
                a <<= 24;
            }
            rgbData[y] = a | r | g | b;
        }
        return Image.createRGBImage(rgbData, imageWidth, imageHeight, true);
    }

    public static Image colorizeImage(Image sourceImage, int prcAlpha) {
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        int[] rgbData = new int[imageWidth * imageHeight];
        sourceImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
        int currY;
        int a;
        int r;
        int g;
        int b;
        int currPoint;
        for (int y = 0; y < imageHeight; y++) {
            currY = y * imageWidth;
            for (int x = 0; x < imageWidth; x++) {
                currPoint = rgbData[currY + x];
                a = currPoint & 0xff000000;
                r = currPoint & 0xff;
                g = currPoint & 0xff00;
                b = currPoint & 0xff0000;

                if (a == 0xff000000) {
                    a = 256;
                } else {
                    a >>= 24;
                }
                b >>= 16;
                g >>= 8;

                a -= (255 * prcAlpha) / 100;
                if (a < 0) {
                    a = 0;
                }
                if (a >= 256) {
                    a = 256;
                }

                if (a >= 256) {
                    a = 0xff000000;
                } else {
                    a <<= 24;
                }
                b <<= 16;
                g <<= 8;

                rgbData[currY + x] = a | r | g | b;
            }
        }
        return Image.createRGBImage(rgbData, imageWidth, imageHeight, true);
    }

    public static void drawLine(Graphics gr, int x, int y, int length, boolean isHorizontal, int color) {
        length++;
        if (length > rgbData.length) {
            length = rgbData.length;
        }
        for (int c = 0; c < length; c++) {
            rgbData[c] = color;
        }
        gr.drawRGB(rgbData, 0, (isHorizontal ? length : 1), x, y, (isHorizontal ? length : 1), (isHorizontal ? 1 : length), true);
    }

    public static void fillRect(Graphics gr, int x, int y, int width, int height, int color) {
        rgbData = new int[width * height];
        for (int c = 0; c < rgbData.length; c++) {
            rgbData[c] = color;
        }
        gr.drawRGB(rgbData, 0, width, x, y, width, height, true);
    }
}
