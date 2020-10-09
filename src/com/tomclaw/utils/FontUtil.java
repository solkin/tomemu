package com.tomclaw.utils;

import javax.microedition.lcdui.Font;
import java.util.Hashtable;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2013
 * http://www.tomclaw.com/
 *
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
