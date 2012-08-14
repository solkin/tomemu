/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.Random;
import javax.microedition.lcdui.*;

/**
 *
 * @author Solkin
 */
public class CanvasDemo extends Canvas implements CommandListener {

  private int x1, y1, x2, y2, color;

  public CanvasDemo() {
    System.out.println("Command");
    Command command1 = new Command( "OK", Command.OK, 0 );
    Command command2 = new Command( "Cancel", Command.CANCEL, 1 );
    System.out.println("addCommand");
    addCommand( command1 );
    addCommand( command2 );
    setCommandListener( CanvasDemo.this );
  }

  @Override
  protected void paint( javax.microedition.lcdui.Graphics g ) {
    System.out.println("Paint");
    g.setColor( 0xdddddd );
    g.fillRect( 0, 0, getWidth(), getHeight() );
    g.setColor( color );
    g.drawLine( x1, y1, x2, y2 );
  }

  public void commandAction( Command c, Displayable d ) {
    if ( c.getCommandType() == Command.OK ) {
      Random random = new Random();
      x1 = random.nextInt() % getWidth();
      y1 = random.nextInt() % getHeight();
      x2 = random.nextInt() % getWidth();
      y2 = random.nextInt() % getHeight();
      color = random.nextInt() % 256;
      repaint();
    }
  }
}
