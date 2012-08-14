/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 * @author Solkin
 */
public class MidletMain extends MIDlet {

  public void startApp() {
    System.out.println("Canvas");
    CanvasDemo canvasDemo = new CanvasDemo();
    System.out.println("Display");
    Display.getDisplay( this ).setCurrent( canvasDemo );
    
    Display.display.repaint();
  }
  
  public void pauseApp() {
  }
  
  public void destroyApp( boolean unconditional ) {
  }
}
