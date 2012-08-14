package com.tomclaw.utils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2010
 * http://www.tomclaw.com/
 * @author Игорь
 */
public class HexUtil {

    public final static char[] HEX_DIGITS_CHARS = "0123456789abcdef".toCharArray();
    public final static byte[] HEX_DIGITS_BYTES = "0123456789abcdef".getBytes();
    public static boolean isLoggerEnabled = true;

    public static void dump_(byte[] data, String linePrefix) {
        if (isLoggerEnabled) {
            dump_(data, linePrefix, data.length);
        }
    }

    public static void dump_(byte[] data, String linePrefix, int lenToPrint) {
        if (isLoggerEnabled) {
            synchronized (System.err) {
                dump_(System.err, data, linePrefix, lenToPrint);
                System.err.flush();
            }
        }
    }

    public static void dump_(OutputStream os, byte[] data, String linePrefix) {
        if (isLoggerEnabled) {
            dump_(os, data, linePrefix, data.length);
        }
    }

    public static void dump_(OutputStream os, byte[] data, String linePrefix,
            int lenToPrint) {
        if (!isLoggerEnabled) {
            return;
        }
        int length = data.length;
        StringBuffer sb = new StringBuffer();
        try {
            sb.append(("\r\n" + linePrefix));
            int printed = 0;
            int printedThisLine = 0;
            int lineStart = 0;
            int actuallyPrinted = 0;
            //int lenToPrint = length;
            if (lenToPrint > data.length) {
                lenToPrint = data.length;
            }
            //int len3 = lenToPrint;
            //if ((len3 & 15) > 0)
            //len3 = (len3 & 0xFFFFFFF0) + 16;
            while (printed < lenToPrint) {
                if (((printedThisLine & 3) == 0) && (printedThisLine > 0)) {
                    sb.append("  ");
                }
                sb.append(((printed >= lenToPrint ? "  " : pad_(Integer.toHexString(data[printed] & 0xff), 2)).toLowerCase() + " "));
                printed++;
                printedThisLine++;
                if (printed < lenToPrint) {
                    actuallyPrinted++;
                }
                if ((printedThisLine >= 16) || (printed == lenToPrint)) {
                    sb.append("  ");
                    dumpChars(sb, data, lineStart, actuallyPrinted);
                    lineStart = printed;
                    printedThisLine = 0;
                    actuallyPrinted = 0;
                    sb.append("\r\n");
                    if (printed < lenToPrint) {
                        sb.append(linePrefix);
                    }
                }
            }
        } catch (Exception e) {
            sb.append("\r\n");
            //Logger.printException(e);
        }
        try {
            LogUtil.outMessage(sb.toString());
            os.write(sb.append("\r\n").toString().getBytes());
        } catch (IOException ex) {
            //Logger.printException(ex);
        }
    }

    private static void dumpChars(StringBuffer sb, byte[] data, int lineStart,
            int maxLen) {
        int printed = lineStart;
        int printedThisLine = 0;
        sb.append("\"");
        while (printed < data.length && printedThisLine <= maxLen) {
            if (((printedThisLine & 7) == 0) && (printedThisLine > 0)) {
                sb.append(" ");
            }
            if (data[printed] >= 32) {
                sb.append((char) data[printed]);
            } else {
                sb.append(".");
            }
            printed++;
            printedThisLine++;
        }
        sb.append("\"");
    }

    /**
     * Insert the method's description here.
     * Creation date: (03.12.99 11:26:13)
     * @return java.lang.String
     * @param str java.lang.String
     * @param resultingStringLength int
     */
    private static String pad_(String str, int resultingStringLength) {
        StringBuffer buf = new StringBuffer();
        while (buf.length() < resultingStringLength - str.length()) {
            buf.append("0");
        }
        return buf.append(str).toString().toLowerCase();
    }

    public static String toHexString(int word) {
        return pad_(Integer.toHexString(word & 0xffff), 4);
    }

    public static String toHexString(long n, long mask,
            int resultingStringLength) {
        return pad_(Long.toString(n & mask), resultingStringLength);
    }

    public static String toHexString0x(int word) {
        return "0x" + pad_(Integer.toHexString(word & 0xffff), 4);
    }

    public static String toHexString0x(long n, long mask,
            int resultingDigitStringLengthWithout0x) {
        return "0x" + toHexString(n, mask, resultingDigitStringLengthWithout0x);
    }

    public static String bytesToString(byte[] data) {
        String string = new String();
        for (int c = 0; c < data.length; c++) {
            string += pad_(Integer.toHexString(data[c] & 0xff), 2);
        }
        return string;
    }

    public static byte[] stringToBytes(String string) {
        byte[] data = new byte[string.length() / 2];
        for (int c = 0; c < string.length(); c += 2) {
            // LogUtil.outMessage(c/2+") " + string.substring(c, c + 2));
            data[c / 2] = (byte) Integer.parseInt(string.substring(c, c + 2), 16);
        }
        return data;
    }
}
