package com.tomclaw.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Font;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2010
 * http://www.tomclaw.com/
 * @author Игорь
 */
public class StringUtil {

  public static final String S_QUOTE = "\"";
  public static final String S_EMPTY = "";
  public static final String S_NULL = "null";

  public static String byteArrayDirectToString(byte[] str) {
    String outString = "";
    for ( int c = 0; c < str.length; c++ ) {
      outString += ( char ) str[c];
    }
    return outString;
  }

  public static String ipToString(byte[] ip) {
    if ( ip == null ) {
      return null;
    }
    StringBuffer strIP = new StringBuffer();

    for ( int i = 0; i < 4; i++ ) {
      int tmp = ( int ) ip[i] & 0xFF;
      if ( strIP.length() != 0 ) {
        strIP.append( '.' );
      }
      strIP.append( tmp );
    }

    return strIP.toString();
  }

  /**
   Concatenate vector entries using
   empty string separator.
   */
  public static String concat(java.util.Vector v) {
    return concat( v, S_EMPTY );
  }

  public static String concat(java.util.Vector v, String separator) {
    synchronized ( v ) {
      java.util.Enumeration e = v.elements();
      StringBuffer sb = new StringBuffer();
      if ( e.hasMoreElements() ) {
        sb.append( e.nextElement().toString() );
      }
      while ( e.hasMoreElements() ) {
        sb.append( separator ).append( e.nextElement().toString() );
      }
      return sb.toString();
    }
  }

  public static boolean isEmptyOrNull(String s) {
    return isNullOrEmpty( s );
  }

  public static boolean isNullOrEmpty(String s) {
    return s == null || s.length() == 0;
  }

  public static boolean isNullOrTrimmedEmpty(String s) {
    return s == null || s.trim().length() == 0;
  }

  public static boolean isTrimmedEmptyOrNull(String s) {
    return isNullOrTrimmedEmpty( s );
  }

  public static String mkEmpty(String s) {
    return s == null ? S_EMPTY : s;
  }

  public static String mkEmptyAndTrim(String s) {
    return s == null ? S_EMPTY : s.trim();
  }

  public static String mkNull(String s) {
    return s == null ? null : ( s.length() == 0 ? null : s );
  }

  public static String mkNullAndTrim(String s) {
    if ( s == null ) {
      return null;
    } else {
      String s1 = s.trim();
      return s1.length() == 0 ? null : s1;
    }
  }

  /**
   Concatenate vector entries using
   empty string separator.
   */
  public static boolean startsWith(String s, String prefix) {
    return s.length() >= prefix.length() && s.startsWith( prefix );
  }

  public static String toPrintableString(Object object) {
    if ( object == null ) {
      return "null";
    } else {
      return "\"" + object + "\"";
    }
  }

  // Check is data array utf-8 string
  public static boolean isDataUTF8(byte[] array, int start, int length) {
    if ( length == 0 ) {
      return false;
    }
    if ( array.length < ( start + length ) ) {
      return false;
    }

    int seqLen;
    byte bt;
    for ( int i = start, len = length; len > 0; ) {
      seqLen = 0;
      bt = array[i++];
      len--;

      if ( ( bt & 0xE0 ) == 0xC0 ) {
        seqLen = 1;
      } else if ( ( bt & 0xF0 ) == 0xE0 ) {
        seqLen = 2;
      } else if ( ( bt & 0xF8 ) == 0xF0 ) {
        seqLen = 3;
      } else if ( ( bt & 0xFC ) == 0xF8 ) {
        seqLen = 4;
      } else if ( ( bt & 0xFE ) == 0xFC ) {
        seqLen = 5;
      }

      if ( seqLen == 0 ) {
        if ( ( bt & 0x80 ) == 0x80 ) {
          return false;
        } else {
          continue;
        }
      }

      for ( int j = 0; j < seqLen; j++ ) {
        if ( len == 0 ) {
          return false;
        }
        bt = array[i++];
        if ( ( bt & 0xC0 ) != 0x80 ) {
          return false;
        }
        len--;
      }
      if ( len == 0 ) {
        break;
      }
    }
    return true;
  }

  // Converts an Unicode string into CP1251 byte array
  public static byte[] stringToByteArray1251(String s) {
    byte abyte0[] = s.getBytes();
    char c;
    for ( int i = 0; i < s.length(); i++ ) {
      c = s.charAt( i );
      switch ( c ) {
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
          if ( c1 >= '\u0410' && c1 <= '\u044F' ) {
            abyte0[i] = ( byte ) ( ( c1 - 1040 ) + 192 );
          }
          break;
      }
    }
    return abyte0;
  }

  // Converts an CP1251 byte array into an Unicode string
  public static byte[] string1251ToByteArray(String string) {
    byte[] data = new byte[ string.length() * 2 ];
    int l;
    for ( int k = 0; k < string.length(); k++ ) {
      l = string.charAt( k );
      try {
        switch ( l ) {
          case 168:
            data[k * 2 - 1] = 01;
            data[k * 2] = 04;
            break;
          case 184:
            data[k * 2 - 1] = 51;
            data[k * 2] = 04;
            break;

          /* Ukrainian CP1251 chars section */
          case 165:
            data[k * 2 - 1] = 90;
            data[k * 2] = 04;
            break;
          case 170:
            data[k * 2 - 1] = 04;
            data[k * 2] = 04;
            break;
          case 175:
            data[k * 2 - 1] = 07;
            data[k * 2] = 04;
            break;
          case 178:
            data[k * 2 - 1] = 06;
            data[k * 2] = 04;
            break;
          case 179:
            data[k * 2 - 1] = 56;
            data[k * 2] = 04;
            break;
          case 180:
            data[k * 2 - 1] = 91;
            data[k * 2] = 04;
            break;
          case 186:
            data[k * 2 - 1] = 54;
            data[k * 2] = 04;
            break;
          case 191:
            data[k * 2 - 1] = 57;
            data[k * 2] = 04;
            break;
          /* end of section */
          default:
            if ( l >= 192 && l <= 255 ) {
              DataUtil.put16_reversed( data, k * 2, ( 1040 + 1 - 192 ) );
              // stringbuffer.append((char) ((1040 + l) - 192));
            } else {
              DataUtil.put16_reversed( data, k * 2, l );
            }
            break;
        }
      } catch ( Exception e ) {
        DataUtil.put16_reversed( data, k * 2, '?' );
      }
    }

    return data;
  }

  // Converts an CP1251 byte array into an Unicode string
  public static String byteArray1251ToString(byte abyte0[], int i, int j) {
    StringBuffer stringbuffer = new StringBuffer( j );
    int l;
    for ( int k = 0; k < j; k++ ) {
      l = abyte0[k + i] & 0xff;
      try {
        switch ( l ) {
          case 168:
            stringbuffer.append( '\u0401' );
            break;
          case 184:
            stringbuffer.append( '\u0451' );
            break;

          /* Ukrainian CP1251 chars section */
          case 165:
            stringbuffer.append( '\u0490' );
            break;
          case 170:
            stringbuffer.append( '\u0404' );
            break;
          case 175:
            stringbuffer.append( '\u0407' );
            break;
          case 178:
            stringbuffer.append( '\u0406' );
            break;
          case 179:
            stringbuffer.append( '\u0456' );
            break;
          case 180:
            stringbuffer.append( '\u0491' );
            break;
          case 186:
            stringbuffer.append( '\u0454' );
            break;
          case 191:
            stringbuffer.append( '\u0457' );
            break;
          /* end of section */
          default:
            if ( l >= 192 && l <= 255 ) {
              stringbuffer.append( ( char ) ( ( 1040 + l ) - 192 ) );
            } else {
              stringbuffer.append( ( char ) ( l & 0xFF ) );
            }
            break;
        }
      } catch ( Exception e ) {
        stringbuffer.append( '?' );
      }
    }

    return stringbuffer.toString();
  }

  // Removes all CR occurences
  public static String removeCr(String val) {
    StringBuffer result = new StringBuffer();
    for ( int i = 0; i < val.length(); i++ ) {
      char chr = val.charAt( i );
      if ( ( chr == 0 ) || ( chr == '\r' ) ) {
        continue;
      }
      result.append( chr );
    }
    return result.toString();
  }

  // Extract a UCS-2BE string from the specified buffer (buf) starting at position off, ending at position off+len
  public static String ucs2beByteArrayToString(byte[] buf, int off, int len) {

    // Length check
    if ( ( off + len > buf.length ) || ( len % 2 != 0 ) ) {
      return ( null );
    }

    // Convert
    StringBuffer sb = new StringBuffer();
    for ( int i = off; i < off + len; i += 2 ) {
      sb.append( ( char ) DataUtil.get16( buf, i ) );
    }
    return ( sb.toString() );

  }

  // Extracts a UCS-2BE string from the specified buffer (buf)
  public static String ucs2beByteArrayToString(byte[] buf) {
    return ( ucs2beByteArrayToString( buf, 0, buf.length ) );
  }

  public static byte[] stringToUcs2beByteArray(String string) {
    byte[] data = new byte[ string.length() * 2 ];
    for ( int c = 0; c < string.length(); c++ ) {
      DataUtil.put16( data, c * 2, string.charAt( c ) );
    }
    return data;
  }

  // Extracts a string from the buffer (buf) starting at position off, ending at position off+len
  public static String byteArrayToString(byte[] buf, int off, int len,
          boolean utf8) {

    // Length check
    if ( buf.length < off + len ) {
      return ( null );
    }

    // Remove \0's at the end
    while ( ( len > 0 ) && ( buf[off + len - 1] == 0x00 ) ) {
      len--;
    }

    // Read string in UTF-8 format
    if ( utf8 ) {
      try {
        byte[] buf2 = new byte[ len + 2 ];
        DataUtil.put16( buf2, 0, len );
        System.arraycopy( buf, off, buf2, 2, len );
        ByteArrayInputStream bais = new ByteArrayInputStream( buf2 );
        DataInputStream dis = new DataInputStream( bais );
        return ( dis.readUTF() );
      } catch ( Exception e ) {
        // do nothing
      }
    }

    // CP1251 or default character encoding?
    boolean is1251Enc = true;
    if ( is1251Enc ) {
      return ( byteArray1251ToString( buf, off, len ) );
    } else {
      return ( new String( buf, off, len ) );
    }

  }

  // Extracts a string from the buffer (buf) starting at position off, ending at position off+len
  public static String byteArrayToString(byte[] buf, int off, int len) {
    return ( byteArrayToString( buf, off, len, false ) );
  }

  // Converts the specified buffer (buf) to a string
  public static String byteArrayToString(byte[] buf, boolean utf8) {
    return ( byteArrayToString( buf, 0, buf.length, utf8 ) );
  }

  // Converts the specified buffer (buf) to a string
  public static String byteArrayToString(byte[] buf) {
    return ( byteArrayToString( buf, 0, buf.length, false ) );
  }

  // Converts the specific 4 byte max buffer to an unsigned long
  public static long byteArrayToLong(byte[] b) {
    long l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
    l <<= 8;
    if ( b.length > 3 ) {
      l |= b[2] & 0xFF;
      l <<= 8;
      l |= b[3] & 0xFF;
    }
    return l;
  }

  // Converts a byte array to a hex string
  public static String byteArrayToHexString(byte[] buf) {
    StringBuffer hexString = new StringBuffer( buf.length );
    String hex;
    for ( int i = 0; i < buf.length; i++ ) {
      hex = Integer.toHexString( 0x0100 + ( buf[i] & 0x00FF ) ).substring( 1 );
      hexString.append( ( ( hex.length() < 2 ? "0" : "" ) + hex ) );
    }
    return hexString.toString();
  }

  // Converts the specified string (val) to a byte array
  public static byte[] stringToByteArray(String val, boolean utf8) {
    // Write string in UTF-8 format
    if ( utf8 ) {
      try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream( baos );
        dos.writeUTF( val );
        byte[] raw = baos.toByteArray();
        byte[] result = new byte[ raw.length - 2 ];
        System.arraycopy( raw, 2, result, 0, raw.length - 2 );
        return result;
      } catch ( Exception e ) {
        // Do nothing
      }
    }

    // CP1251 or default character encoding?
    boolean is1251Enc = true;
    if ( is1251Enc ) {
      return ( stringToByteArray1251( val ) );
    } else {
      return ( val.getBytes() );
    }
  }

  // Converts the specified string (val) to a byte array
  public static byte[] stringToByteArray(String val) {
    return ( stringToByteArray( val, false ) );
  }

  public static boolean isFill(String textData) {
    boolean isFill = false;
    for ( int c = 0; c < textData.length(); c++ ) {
      if ( textData.charAt( c ) != ' ' ) {
        isFill = true;
        break;
      }
    }
    return isFill;
  }

  public static String toXmlWellFormed(String string) {
    LogUtil.outMessage( "To XML well formed" );
    String[] symbols = new String[]{ "&", "<", ">", "'", "\"" };
    String[] replace = new String[]{ "&amp;", "&lt;", "&gt;", "&apos;", "&quot;" };
    int location = 0;
    for ( int c = 0; c < symbols.length; c++ ) {
      location = string.indexOf( symbols[c], location );
      if ( location >= 0 ) {
        string = string.substring( 0, location ).concat( replace[c] ).concat( string.substring( location + 1 ) );
        location += replace[c].length();
        c--;
        continue;
      }
      location = 0;
    }
    LogUtil.outMessage( "Formed" );
    return string;
  }

  public static String toStringFromXmlWellFormed(String string) {
    String[] symbols = new String[]{ "&amp;", "&lt;", "&gt;", "&apos;", "&quot;" };
    String[] replace = new String[]{ "&", "<", ">", "'", "\"" };
    int location;
    for ( int c = 0; c < symbols.length; c++ ) {
      location = string.indexOf( symbols[c], 0 );
      if ( location >= 0 ) {
        string = string.substring( 0, location ).concat( replace[c] ).concat( string.substring( location + symbols[c].length() ) );
        c--;
      }
    }
    return string;
  }

  public static String generateString(int length) {
    String resultString = "";
    String symbols = "abdefhiknrstyzABDEFGHKNQRSTYZ23456789";
    Random random = new Random();
    int rand;
    for ( int c = 0; c < length; c++ ) {
      rand = Math.abs( random.nextInt() ) & 0x000f;
      if ( rand < 0 ) {
        c--;
        continue;
      }
      resultString += symbols.charAt( rand );
    }
    return resultString;
  }

  public static String getWin1251(byte[] encodedString) {
    StringBuffer decodedString = new StringBuffer();
    int ch;
    for ( int c = 0; c < encodedString.length; c += 2 ) {
      //ch = DataUtil.get8(encodedString, c);
      ch = DataUtil.get16_reversed( encodedString, c );
      decodedString.append( ( char ) ch );

    }
    return decodedString.toString();
  }

  public static int determEncoding(byte[] data) {
    int encIndex = 0x02;
    for ( int c = 0; c < data.length; c++ ) {
      if ( ( c + 1 ) % 2 == 0 ) {
        if ( data[c] >= 0 && data[c] <= 10 ) {
          // LogUtil.outMessage("data[c]="+data[c]);
          encIndex = 0x01; // ucs2
        } else {
          encIndex = 0x02; // win1251
          break;
        }
      }
    }
    LogUtil.outMessage( "Det. enc.: " + encIndex );
    return encIndex;
  }

  public static String[] wrapText(String text, int width, Font font) {
    return wrapText( text, width, font, false );
  }

  public static String[] wrapText(String text, int width, Font font, boolean isHidden) {
    int length = text.length();
    if ( isHidden ) {
      text = "";
      for ( int c = 0; c < length; c++ ) {
        text += "*";
      }
    }
    Vector strings = new Vector();
    if ( font.stringWidth( text ) <= width ) {
      strings.addElement( text );
      String[] anArray = new String[ 1 ];
      strings.copyInto( anArray );
      return anArray;
    }
    int offset = 0;
    int prevBrSymLoc;
    String subString;
    for ( int c = 1; c < text.length(); c++ ) {
      subString = text.substring( offset, c );
      length = font.stringWidth( subString );
      if ( length >= width || text.charAt( c ) == '\n' ) {
        if ( text.charAt( c ) == '\n' ) {
          prevBrSymLoc = -1;
        } else {
          prevBrSymLoc = Math.max( subString.lastIndexOf( ' ' ), subString.lastIndexOf( '.' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( ',' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( ';' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( ':' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( ')' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( '(' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( '-' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( '!' ) );
          prevBrSymLoc = Math.max( prevBrSymLoc, subString.lastIndexOf( '\n' ) );
        }
        if ( prevBrSymLoc == -1 ) {
          strings.addElement( subString );
          offset += subString.length();
        } else {
          prevBrSymLoc++;
          strings.addElement( subString.substring( 0, prevBrSymLoc ) );
          offset += prevBrSymLoc;
          c -= subString.length() - prevBrSymLoc;
        }
      } else if ( c == text.length() - 1 ) {
        subString = text.substring( offset );
        strings.addElement( subString );
        break;
      }
    }
    String[] anArray = new String[ strings.size() ];
    strings.copyInto( anArray );
    return anArray;
  }

  public static String replace(String where, String what, String with) {
    int index = -with.length();
    while ( ( index = where.indexOf( what, index + with.length() ) ) != -1 ) {
      where = where.substring( 0, index ) + with + where.substring( index + what.length() );
    }
    return where;
  }
}
