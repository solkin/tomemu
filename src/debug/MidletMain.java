/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import com.tomclaw.tcuilite.Screen;
import com.tomclaw.tcuilite.Theme;
import javax.microedition.midlet.MIDlet;

/**
 * @author solkin
 */
public class MidletMain extends MIDlet {

  public static Screen screen;
  public static MainFrame mainFrame;

  public void startApp () {
    screen = new Screen ( this );

    try {
      //Theme.applyData ( Theme.loadTheme ( "/res/themes/tcuilite_def1.tth" ) );
    } catch ( Throwable ex ) {
      ex.printStackTrace ();
    }

    screen.show ();

    mainFrame = new MainFrame ();
    mainFrame.s_prevWindow = new SecondFrame ();
    mainFrame.s_prevWindow.s_nextWindow = mainFrame;
    screen.setActiveWindow ( mainFrame );
  }

  public void pauseApp () {
  }

  public void destroyApp ( boolean unconditional ) {
  }
}
