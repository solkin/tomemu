package com.tomclaw.utils;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.Vector;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2010
 * http://www.tomclaw.com/
 * @author Игорь
 */
public class DataUtil {

    public static String codeString(String toCode) {
        String encoded = new String();
        String buffer;
        for (int c = 0; c < toCode.length(); c++) {
            buffer = String.valueOf((int) toCode.charAt(c));
            if (buffer.length() == 1) {
                buffer = "0" + buffer;
            }
            if (buffer.length() == 2) {
                buffer = "0" + buffer;
            }
            if (buffer.length() == 3) {
                buffer = "0" + buffer;
            }
            if (buffer.length() == 4) {
                buffer = "0" + buffer;
            }
            encoded += buffer;
        }
        return encoded;
    }

    public static String byteArray2stringConvertXfes(byte[] ba) {
        StringBuffer sb = new StringBuffer(ba.length + 10);
        int chunkStart = 0;
        int chunkLen = 0;
        for (int i = 0; i < ba.length; i++) {
            byte b = ba[i];
            if (b != (byte) 0xFE) {
                chunkLen++;
                continue;
            } else {
                sb.append(byteArray2string(ba, chunkStart, chunkLen));
                i++;
                for (; i < ba.length; i++) {
                    if (ba[i] != 0xFE) {
                        i--;
                        break;
                    }
                }
                chunkStart = i + 1;
                chunkLen = 0;
                if (i >= ba.length) {
                    break;
                }
                sb.append("\r\n");
            }
        }
        if (chunkLen > 0) {
            sb.append(byteArray2string(ba, chunkStart, chunkLen));
        }
        return sb.toString();
    }

    public static String byteArray2string(byte[] ba) {
        return byteArray2string(ba, 0, ba.length);
    }

    public static String byteArray2string(byte[] ba, int ofs, int len) {
        if (len == 0) {
            return "";
        }
        try {
            return new String(ba, ofs, len);
        } catch (Exception e) {
            return new String(ba, ofs, len);
        }
    }

    public static int put8(byte[] buf, int offset, int a) {
        buf[offset] = (byte) (a & 0xff);
        return 1;
    }

    public static int put16(byte[] buf, int offset, int a) {
        buf[offset] = (byte) ((a >> 8) & 0xff);
        buf[++offset] = (byte) (a & 0xff);
        return 2;
    }

    public static int put16_reversed(byte[] buf, int offset, int a) {
        buf[offset] = (byte) (a & 0xff);
        buf[++offset] = (byte) ((a >> 8) & 0xff);
        return 2;
    }

//i += put16(newrx.data, i, 0x01);
    public static int put32(byte[] buf, int offset, long a) {
        buf[offset] = (byte) ((a >> 24) & 0xff);
        buf[++offset] = (byte) ((a >> 16) & 0xff);
        buf[++offset] = (byte) ((a >> 8) & 0xff);
        buf[++offset] = (byte) (a & 0xff);
        return 4;
    }

    public static int put32_reversed(byte[] buf, int offset, long a) {
        buf[offset] = (byte) (a & 0xff);
        buf[++offset] = (byte) ((a >> 8) & 0xff);
        buf[++offset] = (byte) ((a >> 16) & 0xff);
        buf[++offset] = (byte) ((a >> 24) & 0xff);
        return 4;
    }

    public static void putArray_reversed(byte[] buf, int offset, byte[] array) {
        for (int c = offset; c < array.length + offset; c++) {
            buf[c] = array[array.length - 1 - (c - offset)];
        }
    }

    public static void putArray(byte[] buf, int offset, byte[] array) {
        for (int c = offset; c < array.length + offset; c++) {
            buf[c] = array[(c - offset)];
        }
    }

    public static byte[] mmputil_prepareBytesFromLongReversed(long num) {
        byte[] data = new byte[4];
        put32_reversed(data, 0, num);
        return data;
    }

    public static byte[] mmputil_prepareBytesFromLong(long num) {
        byte[] data = new byte[4];
        put32(data, 0, num);
        return data;
    }

    public static byte[] mmputil_prepareBytesWthLength(byte[] string) {
        byte[] data = new byte[4 + string.length];
        put32_reversed(data, 0, string.length);
        if (string.length > 0) {
            putArray(data, 4, string);
        }
        return data;
    }

    public static byte[] mmputil_prepareByteStringWthLength(String string) {
        byte[] data = new byte[4 + string.length()];
        put32_reversed(data, 0, string.length());
        putArray(data, 4, string.getBytes());
        return data;
    }

    public static long reverseLong(long num) {
        long revNum = (((long) (num & 0xff)) << 24) & 0xFF000000;
        revNum |= (((long) ((num >> 8) & 0xff)) << 16) & 0x00FF0000;
        revNum |= (((long) (byte) ((num >> 16) & 0xff)) << 8) & 0x0000FF00;
        revNum |= (((long) (byte) ((num >> 24) & 0xff))) & 0x000000FF;
        return revNum;
    }

    public static int aim_puttlv_str_(byte buf[], final int offset,
            int tlv_type,
            byte[] stringAsByteArray) {
        int delta = 0;
        delta += put16(buf, offset + delta, tlv_type);
        delta += put16(buf, offset + delta, stringAsByteArray.length);
        System.arraycopy(stringAsByteArray, 0, buf, offset + delta,
                stringAsByteArray.length);
        delta += stringAsByteArray.length;
        return delta;
    }

    public static byte[] string2byteArray(String s) {
        return s.getBytes();
    }

    public static int get8int(byte[] buf, int offset) {
        return ((buf[offset])) & 0xff;
    }
    
    public static byte get8(byte[] buf, int offset) {
        return (byte)(((buf[offset])) & 0xff);
    }

    /*public static int get8(byte[] buf, int offset) {
        return ((int) (buf[offset])) & 0xff;
    }*/

    public static int get16(byte[] buf, int offset) {
        int val;
        val = (buf[offset] << 8) & 0xff00;
        val |= (buf[++offset]) & 0xff;
        return val;
    }

    public static int get16_reversed(byte[] buf, int offset) {
        int val;
        val = (buf[offset + 1] << 8) & 0xff00;
        val |= (buf[offset]) & 0xff;
        return val;
    }

    public static long get32(byte[] buf, int off, boolean bigEndian) {
        long val;
        if (bigEndian) {
            val = (((long) buf[off]) << 24) & 0xFF000000;
            val |= (((long) buf[++off]) << 16) & 0x00FF0000;
            val |= (((long) buf[++off]) << 8) & 0x0000FF00;
            val |= (((long) buf[++off])) & 0x000000FF;
        } else // Little endian
        {
            val = (((long) buf[off])) & 0x000000FF;
            val |= (((long) buf[++off]) << 8) & 0x0000FF00;
            val |= (((long) buf[++off]) << 16) & 0x00FF0000;
            val |= (((long) buf[++off]) << 24) & 0xFF000000;
        }
        return (val);
    }

    public static long get32_reversed(byte[] buf, int off, boolean bigEndian) {
        long val;
        //Logger.outMessage(new String(buf).substring(off, off + 4));
        byte[] data = new byte[4];
        data[0] = buf[off + 3];
        data[1] = buf[off + 2];
        data[2] = buf[off + 1];
        data[3] = buf[off];
        val = get32(data, 0, bigEndian);
        return (val);
    }

    public static byte[] getByteArray(byte[] buf, int offset) {
        return getByteArray(buf, offset + 1, get8int(buf, offset));
    }

    public static byte[] getByteArray(byte[] buf, int offset, int length) {
        byte[] b = TransUtil.createByteArray(length);
        System.arraycopy(buf, offset, b, 0, b.length);
        return b;
    }

    public static String getString(byte[] buf, int offset) {
        byte[] b = getByteArray(buf, offset);
        return byteArray2string(b);
    }

    public static byte[] getTlv(byte[] buf, int off) {
        if (off + 4 > buf.length) {
            return (null); // Length check (#1)

        }
        int length = get16(buf, off + 2);
        if (off + 4 + length > buf.length) {
            return (null); // Length check (#2)

        }
        byte[] value = new byte[length];
        System.arraycopy(buf, off + 4, value, 0, length);
        return (value);
    }

    public static String getMetaString(int offset, byte data[]) {
        String metaString;
        int length = get16_reversed(data, offset);
        metaString = new String(data).substring(offset + 2, offset + 2 + length - 1);
        metaString += " ";
        offset += length;
        return metaString;
    }

    public static void nextBytes(byte[] unkBytes) {
        Random random = new Random();
        for (int c = 0; c < unkBytes.length; c++) {
            unkBytes[c] = (byte) random.nextInt();
        }
    }

    static public byte[] byteStringToBytes(String text, char separator, int radix) {
        String[] strings = explode(text, separator);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        String item;
        for (int i = 0; i < strings.length; i++) {
            item = strings[i];
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

    static public String[] explode(String text, char separator) {
        Vector tmp = new Vector();
        StringBuffer strBuf = new StringBuffer();
        int len = text.length();
        char chr;
        for (int i = 0; i < len; i++) {
            chr = text.charAt(i);
            if (chr == separator) {
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
}
