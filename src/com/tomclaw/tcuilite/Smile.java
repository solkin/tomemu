package com.tomclaw.tcuilite;

import com.tomclaw.tcuilite.smiles.SmileLink;
import com.tomclaw.tcuilite.smiles.Smiles;
import javax.microedition.lcdui.Graphics;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2012
 * http://www.tomclaw.com/
 * @author Solkin
 */
public class Smile extends PaneObject {

  public SmileLink smileLink = null;
  public int x = 0;
  public int y = 0;
  public int width = 0;
  public int height = 0;
  public boolean isUpSize = true;
  public boolean isCenteredHorizontally = true;
  public boolean isCenteredVertically = true;
  /**
   * Colors
   */
  public static int actOuterLight = 0xBDC7FF;
  public static int actInnerLight = 0x8C9AFF;

  public Smile( SmileLink smileLink ) {
    this.smileLink = smileLink;
  }

  public void repaint( Graphics g ) {
    if ( isFocusable && isFocused ) {
      g.setColor( actOuterLight );
      g.drawRect( x, y, width, height );
      g.setColor( actInnerLight );
      g.drawRect( x + 1, y + 1, width - 2, height - 2 );
    }
    smileLink.updateLocation( x + ( isCenteredHorizontally ? ( width / 2 - smileLink.getWidth() / 2 ) : 0 ),
        y + ( isCenteredVertically ? ( height / 2 - smileLink.getHeight() / 2 ) : ( isUpSize ? Theme.upSize : 0 ) ) );
    if ( g != null ) {
      try {
        g.drawImage(
            Smiles.smiles[smileLink.smileIndex].getFrameARGB( smileLink.frameIndex ),
            smileLink.x, smileLink.y, Graphics.TOP | Graphics.LEFT );
      } catch ( Throwable ex1 ) {
      }
      smileLink.analyzeFrame();
    }
  }

  public void setLocation( int x, int y ) {
    this.x = x;
    this.y = y;
  }

  public void setSize( int width, int height ) {
    this.width = width;
    this.height = height;
    getHeight();
  }

  public void keyPressed( int keyCode ) {
  }

  public void keyReleased( int keyCode ) {
  }

  public void keyRepeated( int keyCode ) {
  }

  public void pointerPressed( int x, int y ) {
  }

  public void pointerReleased( int x, int y ) {
  }

  public void pointerDragged( int x, int y ) {
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    if ( height == 0 ) {
      height = smileLink.getHeight() + ( isUpSize ? Theme.upSize * 2 : 0 );
    }
    return height;
  }

  public void setTouchOrientation( boolean touchOrientation ) {
  }

  public String getStringValue() {
    return String.valueOf( smileLink.smileIndex );
  }

  public void actionPerformed() {
  }
}