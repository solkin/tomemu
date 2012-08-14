package com.tomclaw.utils;

import java.io.ByteArrayOutputStream;
import java.util.Vector;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2010
 * http://www.tomclaw.com/
 * @author Игорь
 */
public class TransUtil {
    //Allocation Util
    public final static int BYTE_ARRAY_SIZE_MAX = 10240 - 1;

    public static byte[] createByteArray(int size) {
        return new byte[size];
    }

    //Log util
    public static String toString_Hex0xAndDec(int word) {
        return HexUtil.toHexString0x(word) + " (" + (word & 0xFFFF) + ")";
    }

    static public byte[] explodeToBytes(String text, char serparator, int radix) {
        String[] strings = explode(text, serparator);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        for (int i = 0; i < strings.length; i++) {
            String item = strings[i];
            if (item.charAt(0) == '*') {
                for (int j = 1; j < item.length(); j++) {
                    bytes.write((byte) item.charAt(j));
                }
            } else {
                bytes.write(Integer.parseInt(item, radix));
            }
        }
        return bytes.toByteArray();
    }
    
    static public byte[] explodeToBytes(String text) {
        byte[] bytes = new byte[text.length()/2];

        for(int c=0;c<bytes.length;c++){
            // Logger.outMessage(text.substring(c*2, (c+1)*2));
            bytes[c] = (byte)Integer.parseInt(text.substring(c*2, (c+1)*2), 16);
        }
        return bytes;
    }

    static public String[] explode(String text, char serparator) {
        Vector tmp = new Vector();
        StringBuffer strBuf = new StringBuffer();
        int len = text.length();
        for (int i = 0; i < len; i++) {
            char chr = text.charAt(i);
            if (chr == serparator) {
                tmp.addElement(strBuf.toString());
                strBuf.delete(0, strBuf.length());
            } else {
                strBuf.append(chr);
            }
        }
        tmp.addElement(strBuf.toString());
        String[] result = new String[tmp.size()];
        tmp.copyInto(result);
        return result;
    }

    public static String concatToBytes(byte[] array, String separator, int radix) {
        String result = "";
        for (int c = 0; c < array.length; c++) {
            result += Integer.toString(array[c], radix) + ((c < array.length - 1) ? separator : "");
        }
        return result;
    }
}
