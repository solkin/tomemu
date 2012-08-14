/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.microedition.lcdui;

import javax.microedition.midlet.MIDlet;
import javax.swing.JPanel;

/**
 *
 * @author solkin
 */
public class Display extends JPanel {

  private static Displayable displayable;
  public static Display display;
  public static javax.microedition.lcdui.Graphics graphics;

  public Display() {
    display = Display.this;
    graphics = new javax.microedition.lcdui.Graphics();
  }

  public static Display getDisplay( MIDlet midlet ) {
    return display;
  }

  public Displayable getCurrent() {
    return displayable;
  }

  public void setCurrent( Displayable nextDisplayable ) {
    displayable = nextDisplayable;
    display.removeAll();
    display.add( displayable );
    System.out.println( "setCurrent" );
  }

  @Override
  public void paint( java.awt.Graphics g ) {
    displayable.paint( g );
  }

  public void keyPressed( int keyCode ) {
    displayable.keyPressed( keyCode );
  }

  public void keyRepeated( int keyCode ) {
    displayable.keyRepeated( keyCode );
  }

  public void keyReleased( int keyCode ) {
    displayable.keyReleased( keyCode );
  }

  public void pointerPressed( int x, int y ) {
    displayable.pointerPressed( x, y );
  }

  public void pointerReleased( int x, int y ) {
    displayable.pointerReleased( x, y );
  }

  public void pointerDragged( int x, int y ) {
    displayable.pointerDragged( x, y );
  }
}
