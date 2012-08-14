/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomclaw.utils;

/**
 *
 * @author solkin
 */
public class TimeUtil {

  private final static String error_str = "[incorrect data]";
  final public static int TIME_SECOND = 0;
  final public static int TIME_MINUTE = 1;
  final public static int TIME_HOUR = 2;
  final public static int TIME_DAY = 3;
  final public static int TIME_MON = 4;
  final public static int TIME_YEAR = 5;
  final private static byte[] dayCounts = new byte[]{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  public static int gmtOffset = 4;
  public static boolean summerTime = false;

  public static long getCurrentTime() {
    return System.currentTimeMillis() / 1000;
  }

  public static long getCurrentTimeGMT() {
    return getCurrentTime() + getGmtOffset();
  }

  public static long getMentionedTimeGMT(long time) {
    return time + getGmtOffset();
  }

  public static int getGmtOffset() {
    return ( gmtOffset + ( summerTime ? 1 : 0 ) ) * 3600;
  }

  /* Generates seconds count from 1st Jan 1970 till mentioned date */
  public static long createTimeLong(int year, int mon, int day, int hour,
          int min, int sec) {
    int day_count, i, febCount;

    day_count = ( year - 1970 ) * 365 + day;
    day_count += ( year - 1968 ) / 4;
    if ( year >= 2000 ) {
      day_count--;
    }

    if ( ( year % 4 == 0 ) && ( year != 2000 ) ) {
      day_count--;
      febCount = 29;
    } else {
      febCount = 28;
    }

    for ( i = 0; i < mon - 1; i++ ) {
      day_count += ( i == 1 ) ? febCount : dayCounts[i];
    }

    return day_count * 24L * 3600L + hour * 3600L + min * 60L + sec;
  }

  // Creates array of calendar values form value of seconds since 1st jan 1970 (GMT)
  public static int[] createDateArray(long value) {
    int total_days, last_days, i;
    int sec, min, hour, day, mon, year;

    sec = ( int ) ( value % 60 );

    min = ( int ) ( ( value / 60 ) % 60 ); // min
    value -= 60 * min;

    hour = ( int ) ( ( value / 3600 ) % 24 ); // hour
    value -= 3600 * hour;

    total_days = ( int ) ( value / ( 3600 * 24 ) );

    year = 1970;
    for ( ;; ) {
      last_days = total_days - ( ( year % 4 == 0 ) && ( year != 2000 ) ? 366 : 365 );
      if ( last_days <= 0 ) {
        break;
      }
      total_days = last_days;
      year++;
    } // year

    int febrDays = ( ( year % 4 == 0 ) && ( year != 2000 ) ) ? 29 : 28;

    mon = 1;
    for ( i = 0; i < 12; i++ ) {
      last_days = total_days - ( ( i == 1 ) ? febrDays : dayCounts[i] );
      if ( last_days <= 0 ) {
        break;
      }
      mon++;
      total_days = last_days;
    } // mon

    day = total_days; // day

    return new int[]{ sec, min, hour, day, mon, year };
  }

  /** Creates string for time delay **/
  public static String delayToString(long seconds) {
    StringBuffer buf = new StringBuffer();
    int days = ( int ) ( seconds / 86400 );
    seconds %= 86400;
    int hours = ( int ) ( seconds / 3600 );
    seconds %= 3600;
    //TODO: locale "days", "hours", "minutes" 
    int minutes = ( int ) ( seconds / 60 );

    if ( days != 0 ) {
      buf.append( days ).append( ' ' ).append(
              ( "days" ) ).append( ' ' );
    }
    if ( hours != 0 ) {
      buf.append( hours ).append( ' ' ).append(
              ( "hours" ) ).append( ' ' );
    }
    if ( minutes != 0 ) {
      buf.append( minutes ).append( ' ' ).append(
              ( "minutes" ) );
    }

    return buf.toString();
  }

  private static String makeTwo(int number) {
    if ( number < 10 ) {
      return ( "0" + String.valueOf( number ) );
    } else {
      return ( String.valueOf( number ) );
    }
  }

  /* Show date string */
  public static String getDateString(long date, boolean time) {
    if ( date == 0 ) {
      return error_str;
    }

    int[] loclaDate = createDateArray( date );

    StringBuffer sb = new StringBuffer();

    sb.append( makeTwo( loclaDate[TIME_DAY] ) ).append( '.' ).append(
            makeTwo( loclaDate[TIME_MON] ) ).append( '.' ).append(
            loclaDate[TIME_YEAR] );
    if ( time ) {
      sb.append( ' ' ).append( makeTwo( loclaDate[TIME_HOUR] ) ).append(
              ':' ).append( makeTwo( loclaDate[TIME_MINUTE] ) );
    }


    return sb.toString();
  }

  /* Show time string */
  public static String getTimeString(long date, boolean seconds) {
    if ( date == 0 ) {
      return error_str;
    }

    int[] loclaDate = createDateArray( date );

    StringBuffer sb = new StringBuffer();

    sb.append( makeTwo( loclaDate[TIME_HOUR] ) ).append( ':' ).append(
            makeTwo( loclaDate[TIME_MINUTE] ) );

    if ( seconds ) {
      sb.append( ':' ).append( makeTwo( loclaDate[TIME_SECOND] ) );
    }

    return sb.toString();
  }

  public static String getUtcTimeString(long date) {
    if ( date == 0 ) {
      return error_str;
    }

    int[] loclaDate = createDateArray( date );

    StringBuffer sb = new StringBuffer();

    sb.append( makeTwo( loclaDate[TIME_YEAR] ) ).append( '-' ).append(
            makeTwo( loclaDate[TIME_MON] ) ).append( '-' ).append( makeTwo( loclaDate[TIME_DAY] ) ).append( 'T' ).append( makeTwo( loclaDate[TIME_HOUR] ) ).append( ':' ).append( makeTwo( loclaDate[TIME_MINUTE] ) ).append( ':' ).append( makeTwo( loclaDate[TIME_SECOND] ) ).append( 'Z' );

    return sb.toString();
  }
}
