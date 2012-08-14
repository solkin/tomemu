package com.tomclaw.utils;

import java.util.Hashtable;
import javax.microedition.lcdui.Font;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2012
 * http://www.tomclaw.com/
 * @author Solkin
 */
public class FontUtil {

    public static Hashtable fonts = new Hashtable();
    public static String fontString;

    public static Font getFont(int face, int style, int size) {
        fontString = style + ":" + size;
        if (fonts.containsKey(fontString)) {
            return (Font) fonts.get(fontString);
        } else {
            Font font = Font.getFont(face, style, size);
            fonts.put(fontString, font);
            return font;
        }
    }
}
