package com.tomclaw.utils;

import javax.microedition.lcdui.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2010
 * http://www.tomclaw.com/
 *
 * @author Игорь
 */
public class StringUtils {

    public static String[] wrapText(String text, int width, Font font) {
        Vector strings = new Vector();
        if (font.stringWidth(text) <= width) {
            strings.addElement(text);
            String[] anArray = new String[1];
            strings.copyInto(anArray);
            return anArray;
        }
        int offset = 0;
        int length = 0;
        int prevBrSymLoc = 0;
        String subString;
        for (int c = 1; c < text.length(); c++) {
            subString = text.substring(offset, c);
            length = font.stringWidth(subString);
            if (length >= width || text.charAt(c) == '\n') {
                if (text.charAt(c) == '\n') {
                    prevBrSymLoc = -1;
                } else {
                    prevBrSymLoc = Math.max(subString.lastIndexOf(' '), subString.lastIndexOf('.'));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf(','));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf(';'));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf(':'));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf(')'));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf('('));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf('-'));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf('!'));
                    prevBrSymLoc = Math.max(prevBrSymLoc, subString.lastIndexOf('\n'));
                }
                if (prevBrSymLoc == -1) {
                    strings.addElement(subString);
                    offset += subString.length();
                } else {
                    prevBrSymLoc++;
                    strings.addElement(subString.substring(0, prevBrSymLoc));
                    offset += prevBrSymLoc;
                    c -= subString.length() - prevBrSymLoc;
                }
            } else if (c == text.length() - 1) {
                subString = text.substring(offset);
                strings.addElement(subString);
                break;
            }
        }
        String[] anArray = new String[strings.size()];
        strings.copyInto(anArray);
        return anArray;
    }

    public static String replace(String replex, String regex, String repex) {
        replex = " " + replex + " ";
        for (int c = 0; c < replex.length() - regex.length(); c++) {
            if (replex.substring(c, c + regex.length()).hashCode()
                    == regex.hashCode()) {
                replex = replex.substring(0, c) + repex + replex.substring(c + regex.length(), replex.length());
                c -= regex.length() - repex.length();
            }
        }
        replex = replex.substring(1, replex.length() - 1);
        return replex;
    }

    // Converts an Unicode string into CP1251 byte array
    public static byte[] stringToByteArray1251(String s) {
        byte abyte0[] = s.getBytes();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case 1025:
                    abyte0[i] = -88;
                    break;
                case 1105:
                    abyte0[i] = -72;
                    break;

                /* Ukrainian CP1251 chars section */
                case 1168:
                    abyte0[i] = -91;
                    break;
                case 1028:
                    abyte0[i] = -86;
                    break;
                case 1031:
                    abyte0[i] = -81;
                    break;
                case 1030:
                    abyte0[i] = -78;
                    break;
                case 1110:
                    abyte0[i] = -77;
                    break;
                case 1169:
                    abyte0[i] = -76;
                    break;
                case 1108:
                    abyte0[i] = -70;
                    break;
                case 1111:
                    abyte0[i] = -65;
                    break;
                /* end of section */

                default:
                    char c1 = c;
                    if (c1 >= '\u0410' && c1 <= '\u044F') {
                        abyte0[i] = (byte) ((c1 - 1040) + 192);
                    }
                    break;
            }
        }
        return abyte0;
    }

    // Converts an CP1251 byte array into an Unicode string
    public static String byteArray1251ToString(byte abyte0[], int i, int j) {
        StringBuffer stringbuffer = new StringBuffer(j);
        int l;
        for (int k = 0; k < j; k++) {
            l = abyte0[k + i] & 0xff;
            try {
                switch (l) {
                    case 168:
                        stringbuffer.append('\u0401');
                        break;
                    case 184:
                        stringbuffer.append('\u0451');
                        break;

                    /* Ukrainian CP1251 chars section */
                    case 165:
                        stringbuffer.append('\u0490');
                        break;
                    case 170:
                        stringbuffer.append('\u0404');
                        break;
                    case 175:
                        stringbuffer.append('\u0407');
                        break;
                    case 178:
                        stringbuffer.append('\u0406');
                        break;
                    case 179:
                        stringbuffer.append('\u0456');
                        break;
                    case 180:
                        stringbuffer.append('\u0491');
                        break;
                    case 186:
                        stringbuffer.append('\u0454');
                        break;
                    case 191:
                        stringbuffer.append('\u0457');
                        break;
                    /* end of section */

                    default:
                        if (l >= 192 && l <= 255) {
                            stringbuffer.append((char) ((1040 + l) - 192));
                        } else {
                            stringbuffer.append((char) (l & 0xFF));
                        }
                        break;
                }
            } catch (Exception e) {
                stringbuffer.append('?');
            }
        }

        return stringbuffer.toString();
    }

    // Extracts a string from the buffer (buf) starting at position off, ending at position off+len
    public static String byteArrayToString(byte[] buf, int off, int len,
                                           boolean utf8) {

        // Length check
        if (buf.length < off + len) {
            return (null);
        }

        // Remove \0's at the end
        while ((len > 0) && (buf[off + len - 1] == 0x00)) {
            len--;
        }

        // Read string in UTF-8 format
        if (utf8) {
            try {
                byte[] buf2 = new byte[len + 2];
                aimutil_put16(buf2, 0, len);
                System.arraycopy(buf, off, buf2, 2, len);
                ByteArrayInputStream bais = new ByteArrayInputStream(buf2);
                DataInputStream dis = new DataInputStream(bais);
                return (dis.readUTF());
            } catch (Exception e) {
                // do nothing
            }
        }

        // CP1251 or default character encoding?
        boolean is1251Enc = true;
        if (is1251Enc) {
            return (byteArray1251ToString(buf, off, len));
        } else {
            return (new String(buf, off, len));
        }

    }

    // Converts the specified string (val) to a byte array
    public static byte[] stringToByteArray(String val, boolean utf8) {
        // Write string in UTF-8 format
        if (utf8) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                dos.writeUTF(val);
                byte[] raw = baos.toByteArray();
                byte[] result = new byte[raw.length - 2];
                System.arraycopy(raw, 2, result, 0, raw.length - 2);
                return result;
            } catch (Exception e) {
                // Do nothing
            }
        }

        // CP1251 or default character encoding?
        boolean is1251Enc = true;
        if (is1251Enc) {
            return (stringToByteArray1251(val));
        } else {
            return (val.getBytes());
        }
    }

    private static int aimutil_put16(byte[] buf, int offset, int a) {
        buf[offset] = (byte) ((a >> 8) & 0xff);
        buf[++offset] = (byte) (a & 0xff);
        return 2;
    }
}
