package com.tomclaw.utils;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2012
 * http://www.tomclaw.com/
 * @author Игорь
 *
 * This is older-release version of tag util, named HTMLParser,
 * no longer supported title.
 * Old code, but works and I'll rewrite it later
 */
public class TagUtil {

  public static String removeTags(String htmlData) {
    String parsedData = new String();
    /*
     * Primary engine: removing HTML tags
     */
    int offsetStart;
    int offsetStop;
    int oldOffset = 0;
    for ( int c = 0; c < htmlData.length(); c++ ) {
      offsetStart = htmlData.indexOf( "<", oldOffset );
      offsetStop = htmlData.indexOf( ">", offsetStart );
      if ( oldOffset > offsetStart ) {
        break;
      }
      if ( ( ( oldOffset + 1 ) > offsetStart ) ) {
        oldOffset = offsetStop;
        continue;
      }
      parsedData += htmlData.substring( oldOffset + 1, offsetStart );
      oldOffset = offsetStop;
    }
    if ( parsedData.hashCode() == new String().hashCode() ) {
      parsedData = htmlData;
    }
    return parsedData;
  }
}
