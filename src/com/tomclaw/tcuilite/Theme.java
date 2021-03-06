package com.tomclaw.tcuilite;

import com.tomclaw.images.Splitter;
import com.tomclaw.utils.FontUtil;

import javax.microedition.lcdui.Font;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2013
 * http://www.tomclaw.com/
 *
 * @author Solkin
 */
public class Theme {

    /**
     * Constants
     **/
    public static final int BLOCK_BUTTON = 1;
    public static final int BLOCK_CHAT_ITEM = 2;
    public static final int BLOCK_CHECK = 3;
    public static final int BLOCK_DIALOG = 4;
    public static final int BLOCK_FIELD = 5;
    public static final int BLOCK_GAUGE = 6;
    public static final int BLOCK_GRID = 7;
    public static final int BLOCK_GROUP = 8;
    public static final int BLOCK_HEADER = 9;
    public static final int BLOCK_LABEL = 10;
    public static final int BLOCK_LIST = 11;
    public static final int BLOCK_PANE = 12;
    public static final int BLOCK_POPUP = 13;
    public static final int BLOCK_RADIO = 14;
    public static final int BLOCK_SCROLL = 15;
    public static final int BLOCK_SMILE = 16;
    public static final int BLOCK_SOFT = 17;
    public static final int BLOCK_TAB = 18;
    /**
     * Fonts
     **/
    public static Font font = FontUtil.getFont(Font.FACE_PROPORTIONAL,
            Font.STYLE_PLAIN, Font.SIZE_SMALL);
    public static Font titleFont = FontUtil.getFont(Font.FACE_PROPORTIONAL,
            Font.STYLE_BOLD, Font.SIZE_SMALL);
    public static Font italicFont = FontUtil.getFont(Font.FACE_PROPORTIONAL,
            Font.STYLE_ITALIC, Font.SIZE_SMALL);
    /**
     * Sizes
     **/
    public static int upSize = 1;
    public static int scrollWidth = 15;
    /**
     * Theme info
     **/
    public static int version;
    public static String title;
    public static String author;
    public static boolean isThemeChangeActive;
    private static Thread thread;
    private static int[] data_tr;

    private static DataInputStream getInputStream(String resDatFile) {
        DataInputStream dis = new DataInputStream(Runtime.getRuntime()
                .getClass().getResourceAsStream(resDatFile));
        return dis;
    }

    /**
     * Loading theme from resource file
     *
     * @param resDatFile
     * @throws IOException
     */
    public static int[] loadTheme(String resDatFile) {
        try {
            return loadTheme(getInputStream(resDatFile));
        } catch (Throwable ex) {
            return null;
        }
    }

    /**
     * Loading theme from resource file
     *
     * @param resDatFile
     * @throws IOException
     */
    public static String loadTitle(String resDatFile) {
        try {
            return loadTitle(getInputStream(resDatFile));
        } catch (Throwable ex) {
            return null;
        }
    }

    public static String loadTitle(DataInputStream dis) {
        try {
            /** Loading resource file **/
            int header = dis.readChar();
            if (header == 0xaf) {
                version = dis.readChar();
                title = dis.readUTF();
                author = dis.readUTF();
                return title;
            }
        } catch (Throwable ex) {
        }
        return null;
    }

    public static int[] loadTheme(DataInputStream dis) {
        try {
            /** Loading resource file **/
            int header = dis.readChar();
            if (header == 0xaf) {
                version = dis.readChar();
                title = dis.readUTF();
                author = dis.readUTF();
            }
            int size = dis.readInt();
            int[] data = new int[size];
            for (int c = 0; c < data.length; c++) {
                data[c] = dis.readInt();
            }
            return data;
        } catch (Throwable ex) {
        }
        return null;
    }

    private static int readBlock(int offset, int[] theme) {
        int type = theme[offset++];
        int size = theme[offset++];
        int defo = offset;
        switch (type) {
            case BLOCK_BUTTON: {
                /** Button **/
                Button.unactForeColor = theme[offset++];
                Button.unactForeShadowColor = theme[offset++];
                Button.unactOnlTopBorder = theme[offset++];
                Button.unactBotBorder = theme[offset++];
                Button.unactInTopBorder = theme[offset++];
                Button.unactInOnlBotBorder = theme[offset++];
                Button.unactGradFrom = theme[offset++];
                Button.unactGradTo = theme[offset++];
                Button.actForeColor = theme[offset++];
                Button.actForeShadowColor = theme[offset++];
                Button.actOnlTopBorder = theme[offset++];
                Button.actBotBorder = theme[offset++];
                Button.actInOnlBotBorder = theme[offset++];
                Button.actGradFrom = theme[offset++];
                Button.actGradMidd = theme[offset++];
                Button.actGradAftr = theme[offset++];
                Button.actGradFinl = theme[offset++];
                Button.actOuterLight = theme[offset++];
                Button.actInnerLight = theme[offset++];
                Button.prsdGradFrom = theme[offset++];
                Button.prsdGradFinl = theme[offset++];
                break;
            }
            case BLOCK_CHAT_ITEM: {
                /** Chat item **/
                ChatItem.foreColor = theme[offset++];
                ChatItem.titleColor = theme[offset++];
                ChatItem.borderColor = theme[offset++];
                ChatItem.actOuterLight = theme[offset++];
                ChatItem.actInnerLight = theme[offset++];
                break;
            }
            case BLOCK_CHECK: {
                /** Check **/
                Check.foreColor = theme[offset++];
                Check.actOuterLight = theme[offset++];
                Check.actInnerLight = theme[offset++];
                break;
            }
            case BLOCK_DIALOG: {
                /** Dialog **/
                Dialog.titleColor = theme[offset++];
                Dialog.textColor = theme[offset++];
                Dialog.alphaBackColor = theme[offset++];
                Dialog.plainBackColor = theme[offset++];
                Dialog.hrLineColor = theme[offset++];
                Dialog.hrLineShadow = theme[offset++];
                Dialog.shadowColor = theme[offset++];
                break;
            }
            case BLOCK_FIELD: {
                /** Field **/
                Field.foreColor = theme[offset++];
                Field.backColor = theme[offset++];
                Field.borderColor = theme[offset++];
                Field.actOuterLight = theme[offset++];
                Field.actInnerLight = theme[offset++];
                break;
            }
            case BLOCK_GAUGE: {
                /** Gauge **/
                Gauge.backColorGradFrom = theme[offset++];
                Gauge.backColorGradFinl = theme[offset++];
                Gauge.unactForeColor = theme[offset++];
                Gauge.unactForeShadowColor = theme[offset++];
                Gauge.unactOnlTopBorder = theme[offset++];
                Gauge.unactBotBorder = theme[offset++];
                Gauge.unactInTopBorder = theme[offset++];
                Gauge.unactInOnlBotBorder = theme[offset++];
                Gauge.unactGradFrom = theme[offset++];
                Gauge.unactGradTo = theme[offset++];
                Gauge.actForeColor = theme[offset++];
                Gauge.actForeShadowColor = theme[offset++];
                Gauge.actOnlTopBorder = theme[offset++];
                Gauge.actBotBorder = theme[offset++];
                Gauge.actInOnlBotBorder = theme[offset++];
                Gauge.actGradFrom = theme[offset++];
                Gauge.actGradMidd = theme[offset++];
                Gauge.actGradAftr = theme[offset++];
                Gauge.actGradFinl = theme[offset++];
                Gauge.actOuterLight = theme[offset++];
                Gauge.actInnerLight = theme[offset++];
                break;
            }
            case BLOCK_SCROLL: {
                /** Scroll **/
                Scroll.scrollBack = theme[offset++];
                Scroll.scrollGradFrom = theme[offset++];
                Scroll.scrollGradTo = theme[offset++];
                Scroll.scrollBorder = theme[offset++];
                Scroll.scrollFix = theme[offset++];
                Scroll.scrollFixShadow = theme[offset++];
                break;
            }
            case BLOCK_GRID: {
                /** Grid **/
                Grid.backColor = theme[offset++];
                Grid.hrLine = theme[offset++];
                break;
            }
            case BLOCK_GROUP: {
                /** Group **/
                Group.foreColor = theme[offset++];
                Group.foreSelColor = theme[offset++];
                Group.backColor = theme[offset++];
                Group.hrLine = theme[offset++];
                Group.selectedGradFrom = theme[offset++];
                Group.selectedGradTo = theme[offset++];
                Group.selectedUpOutline = theme[offset++];
                Group.selectedBottomOutline = theme[offset++];
                break;
            }
            case BLOCK_HEADER: {
                /** Header **/
                Header.headerGradFrom = theme[offset++];
                Header.headerGradTo = theme[offset++];
                Header.headerLine1 = theme[offset++];
                Header.headerLine2 = theme[offset++];
                Header.fontColor = theme[offset++];
                Header.fontShadow = theme[offset++];
                break;
            }
            case BLOCK_LABEL: {
                /** Label **/
                Label.foreColor = theme[offset++];
                Label.borderColor = theme[offset++];
                Label.focusedBackColor = theme[offset++];
                Label.actOuterLight = theme[offset++];
                Label.actInnerLight = theme[offset++];
                Label.headerForeColor = theme[offset++];
                Label.headerGradFrom = theme[offset++];
                Label.headerGradTo = theme[offset++];
                Label.headerHr = theme[offset++];
                break;
            }
            case BLOCK_LIST: {
                /** List **/
                List.foreColor = theme[offset++];
                List.foreSelColor = theme[offset++];
                List.backColor = theme[offset++];
                List.hrLine = theme[offset++];
                List.selectedGradFrom = theme[offset++];
                List.selectedGradTo = theme[offset++];
                List.selectedUpOutline = theme[offset++];
                List.selectedBottomOutline = theme[offset++];
                break;
            }
            case BLOCK_PANE: {
                /** Pane **/
                Pane.backColor = theme[offset++];
                break;
            }
            case BLOCK_POPUP: {
                /** Pop-up **/
                Popup.foreColor = theme[offset++];
                Popup.foreSelColor = theme[offset++];
                Popup.backGradFrom = theme[offset++];
                Popup.backGradTo = theme[offset++];
                Popup.selectedGradFrom = theme[offset++];
                Popup.selectedGradTo = theme[offset++];
                Popup.selectedUpOutline = theme[offset++];
                Popup.selectedBottomOutline = theme[offset++];
                Popup.shadowBorder = theme[offset++];
                Popup.alphaBackColor = theme[offset++];
                Popup.shadowColor = theme[offset++];
                break;
            }
            case BLOCK_RADIO: {
                /** Radio **/
                Radio.foreColor = theme[offset++];
                Radio.actOuterLight = theme[offset++];
                Radio.actInnerLight = theme[offset++];
                break;
            }
            case BLOCK_SMILE: {
                /** Smile **/
                Smile.actOuterLight = theme[offset++];
                Smile.actInnerLight = theme[offset++];
                break;
            }
            case BLOCK_SOFT: {
                /** Soft **/
                Soft.softLine1 = theme[offset++];
                Soft.softLine2 = theme[offset++];
                Soft.softGradFrom = theme[offset++];
                Soft.softGradTo = theme[offset++];
                Soft.fontColor = theme[offset++];
                Soft.fontShadow = theme[offset++];
                break;
            }
            case BLOCK_TAB: {
                /** Tab **/
                Tab.backGradFrom = theme[offset++];
                Tab.backGradTo = theme[offset++];
                Tab.selectedForeColor = theme[offset++];
                Tab.selectedGradFrom = theme[offset++];
                Tab.selectedGradTo = theme[offset++];
                Tab.selectedUpOutline = theme[offset++];
                Tab.selectedBottomOutline = theme[offset++];
                Tab.unSelectedForeColor = theme[offset++];
                Tab.unSelectedGradFrom = theme[offset++];
                Tab.unSelectedGradTo = theme[offset++];
                Tab.unSelectedUpOutline = theme[offset++];
                Tab.unSelectedBottomOutline = theme[offset++];
                break;
            }
        }
        return defo + size;
    }

    public static void applyData(int[] theme) {
        int offset = 0;
        int count = theme[offset++];
        for (int c = 0; c < count; c++) {
            offset = readBlock(offset, theme);
        }
    }

    public static boolean startThemeChange(final String theme_fr, final String theme_to) {
        try {
            int[] data_fr = Theme.loadTheme(theme_fr);
            int[] data_to = Theme.loadTheme(theme_to);
            return Theme.startThemeChange(data_fr, data_to);
        } catch (Throwable ex) {
            return false;
        }
    }

    public static boolean startThemeChange(final int[] data_fr, final int[] data_to) {
        if (isThemeChangeActive) {
            isThemeChangeActive = false;
            try {
                thread.join();
            } catch (InterruptedException ex) {
                return false;
            }
        }
        if (data_tr != null) {
            /** Copy temporary array as from **/
            System.arraycopy(data_tr, 0, data_fr, 0, data_tr.length);
        } else {
            data_tr = new int[data_to.length];
        }
        thread = new Thread() {
            public void run() {
                isThemeChangeActive = true;
                int currPoint, destPoint;
                int a, r, g, b;
                for (int c = 0; c <= 100; c++) {
                    if (!isThemeChangeActive) {
                        /** Action was stopped **/
                        return;
                    }
                    for (int i = 0; i < data_fr.length; i++) {
                        /** Obtain colors **/
                        currPoint = data_fr[i];
                        destPoint = data_to[i];
                        /** Calculating new color **/
                        a = (((currPoint & 0xff000000) * (100 - c)
                                + (destPoint & 0xff000000) * c) / 100) & 0xff000000;
                        b = (((currPoint & 0xff0000) * (100 - c)
                                + (destPoint & 0xff0000) * c) / 100) & 0xff0000;
                        g = (((currPoint & 0xff00) * (100 - c)
                                + (destPoint & 0xff00) * c) / 100) & 0xff00;
                        r = (((currPoint & 0xff) * (100 - c)
                                + (destPoint & 0xff) * c) / 100) & 0xff;
                        /** Applying color to temporary array **/
                        data_tr[i] = a | r | g | b;
                    }
                    /** Applying theme to system **/
                    Theme.applyData(data_tr);
                    /** Repainting **/
                    Screen.screen.repaint();
                    try {
                        sleep(25);
                    } catch (InterruptedException ex) {
                    }
                }
                isThemeChangeActive = false;
                data_tr = null;
            }
        };
        thread.start();
        return true;
    }

    /**
     * Setup upSize according for images, loaded by splitter
     */
    public static void checkForUpSize() {
        upSize = 1;
        if (font.getHeight() + 2 < Splitter.imageMaxSize) {
            upSize = (Splitter.imageMaxSize - font.getHeight()) / 2 + 1;
        }
        if (Screen.screen.isPointerEvents) {
            if (upSize < Screen.screen.getHeight() / 70) {
                upSize = Screen.screen.getHeight() / 70;
            }
        }
    }
}
