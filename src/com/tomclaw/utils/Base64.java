package com.tomclaw.utils;

/**
 *
 * @author Игорь
 */
public class Base64 {

  static private final char[] ALPHABET;
  static private final int[] valueDecoding;
  
  static {
    int i;
    int i1;
    char[] ALPHABET__ = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
    ALPHABET = ALPHABET__;
    valueDecoding = new int[ 128 ];
    i = 0;
    while ( i < valueDecoding.length ) {
      valueDecoding[i] = -1;
      i++;
    }
    i1 = 0;
    while ( i1 < ALPHABET.length ) {
      valueDecoding[ALPHABET[i1]] = i1;
      i1++;
    }
  }

  static public String encode(byte[] data, int offset, int length) {
    int i;
    int encodedLen;
    char[] encoded;
    encodedLen = ( ( length + 2 ) / 3 ) * 4;
    encoded = new char[ encodedLen ];
    i = 0;
    encodedLen = 0;
    while ( encodedLen < encoded.length ) {
      Base64.encodeQuantum( data, offset + i, length - i, encoded, encodedLen );
      i += 3;
      encodedLen += 4;
    }
    return new String( encoded );
  }

  static private void encodeQuantum(byte[] in, int inOffset, int len, char[] out, int outOffset) {
    byte a;
    byte b = 0;
    byte c = 0;
    a = in[inOffset];
    out[outOffset] = ALPHABET[( a >>> 2 ) & 63];
    if ( len > 2 ) {
      b = in[inOffset + 1];
      c = in[inOffset + 2];
      out[outOffset + 1] = ALPHABET[( ( a << 4 ) & 48 ) + ( ( b >>> 4 ) & 15 )];
      out[outOffset + 2] = ALPHABET[( ( b << 2 ) & 60 ) + ( ( c >>> 6 ) & 3 )];
      out[outOffset + 3] = ALPHABET[c & 63];
    } else if ( len > 1 ) {
      b = in[inOffset + 1];
      out[outOffset + 1] = ALPHABET[( ( a << 4 ) & 48 ) + ( ( b >>> 4 ) & 15 )];
      out[outOffset + 2] = ALPHABET[( ( b << 2 ) & 60 ) + ( ( c >>> 6 ) & 3 )];
      out[outOffset + 3] = 61;
    } else {
      out[outOffset + 1] = ALPHABET[( ( a << 4 ) & 48 ) + ( ( b >>> 4 ) & 15 )];
      out[outOffset + 2] = 61;
      out[outOffset + 3] = 61;
    }
  }

  static public byte[] decode(String encoded) throws java.io.IOException {
    return Base64.decode( encoded, 0, encoded.length() );
  }

  static public byte[] decode(String encoded, int offset, int length) throws java.io.IOException {
    int i;
    int decodedLen;
    byte[] decoded;
    if ( ( length % 4 ) != 0 ) {
      throw new java.io.IOException( "Base64 string length is not multiple of 4" );
    }
    decodedLen = ( length / 4 ) * 3;
    if ( encoded.charAt( ( offset + length ) - 1 ) == 61 ) {
      decodedLen--;
      if ( encoded.charAt( ( offset + length ) - 2 ) == 61 ) {
        decodedLen--;
      }
    }
    decoded = new byte[ decodedLen ];
    i = 0;
    decodedLen = 0;
    while ( i < length ) {
      Base64.decodeQuantum( encoded.charAt( offset + i ), encoded.charAt( ( offset + i ) + 1 ), encoded.charAt( ( offset + i ) + 2 ), encoded.charAt( ( offset + i ) + 3 ), decoded, decodedLen );
      i += 4;
      decodedLen += 3;
    }
    return decoded;
  }

  static private void decodeQuantum(char in1, char in2, char in3, char in4, byte[] out, int outOffset) throws java.io.IOException {
    int a;
    int b;
    int c = 0;
    int d = 0;
    int pad = 0;
    a = valueDecoding[in1 & 127];
    b = valueDecoding[in2 & 127];
    if ( in4 == 61 ) {
      pad++;
      if ( in3 == 61 ) {
        pad++;
      } else {
        c = valueDecoding[in3 & 127];
      }
    } else {
      c = valueDecoding[in3 & 127];
      d = valueDecoding[in4 & 127];
    }
    if ( ( ( a < 0 ) || ( ( b < 0 ) || ( c < 0 ) ) ) || ( d < 0 ) ) {
      throw new java.io.IOException( "Invalid character in Base64 string" );
    }
    out[outOffset] = ( byte ) ( ( ( a << 2 ) & 252 ) | ( ( b >>> 4 ) & 3 ) );
    if ( pad < 2 ) {
      out[outOffset + 1] = ( byte ) ( ( ( b << 4 ) & 240 ) | ( ( c >>> 2 ) & 15 ) );
      if ( pad < 1 ) {
        out[outOffset + 2] = ( byte ) ( ( ( c << 6 ) & 192 ) | ( d & 63 ) );
      }
    }
  }
}
