package javax.microedition.lcdui;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
// import javax.microedition.lcdui.Displayable.TickerPainter;

public abstract class Displayable extends JPanel {

  private class TickerPainter extends TimerTask {

    private TickerPainter() {
      //compiled code
      System.out.println( "Compiled Code" );
    }

    public final void run() {
      //compiled code
      System.out.println( "Compiled Code" );
    }
  }
  Display currentDisplay;
  Command[] commands;
  int numCommands;
  CommandListener listener;
  static final int X = 0;
  static final int Y = 1;
  static final int WIDTH = 2;
  static final int HEIGHT = 3;
  int[] viewport;
  boolean fullScreenMode;
  boolean sizeChangeOccurred;
  Displayable paintDelegate;
  private static final Font TITLE_FONT = null;
  static final int TITLE_HEIGHT = 0;
  private String title;
  // private Ticker ticker;
  private static final Timer tickerTimer = null;
  private TickerPainter tickerPainter;
  private int tickerHeight;
  private int totalHeight;
  private int vScrollPosition;
  private int vScrollProportion;

  Displayable() {
    commands = new Command[ 0 ];
    this.setBackground( Color.red );
    System.out.println("Displayable()");
  }

  @Override
  public void paint( java.awt.Graphics g ) {
    System.out.println("Paint D");
    Display.graphics.g = g;
    paint( Display.graphics );
  }

  protected abstract void paint( Graphics g );

  public String getTitle() {
    return title;
  }

  public void setTitle( String s ) {
    this.title = s;
  }

  /*public Ticker getTicker() {
   //compiled code
   System.out.println("Compiled Code");
   }

   public void setTicker(Ticker ticker) {
   //compiled code
   System.out.println("Compiled Code");
   }*/
  public boolean isShown() {
    //compiled code
    System.out.println( "Compiled Code" );
    return false;
  }

  public void addCommand( Command cmd ) {
    Command[] command = new Command[ commands.length + 1 ];
    System.arraycopy( commands, 0, command, 0, commands.length );
    command[command.length - 1] = cmd;
    commands = command;
  }

  public void removeCommand( Command cmd ) {
    System.out.println( "Compiled Code" );
  }

  public void setCommandListener( CommandListener l ) {
    this.listener = l;
  }

  void commitPendingInteraction() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void invalidate( Item src ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callInvalidate( Item src ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void itemStateChanged( Item src ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callItemStateChanged( Item src ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  /*void setTickerImpl(Ticker t) {
   //compiled code
   System.out.println("Compiled Code");
   }*/
  void setTitleImpl( String s ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callSizeChanged( int w, int h ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callPaint( Graphics g, Object target ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void paintTicker( Graphics g ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void paintTitle( Graphics g ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void fullScreenMode( boolean onOff ) {
    this.fullScreenMode = onOff;
  }

  private void setupViewport() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  private void translateViewport() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callKeyPressed( int keyCode ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callKeyRepeated( int keyCode ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callKeyReleased( int keyCode ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callKeyTyped( char c ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callPointerPressed( int x, int y ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callPointerDragged( int x, int y ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callPointerReleased( int x, int y ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  final void callRepaint( int x, int y, int width, int height, Object target ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  public void callRepaint() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  final void repaintContents() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void setVerticalScroll( int scrollPosition, int scrollProportion ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  int getVerticalScrollPosition() {
    //compiled code
    System.out.println( "Compiled Code" );
    return 0;
  }

  int getVerticalScrollProportion() {
    //compiled code
    System.out.println( "Compiled Code" );
    return 0;
  }

  void callShowNotify( Display d ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void callHideNotify( Display d ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  Command[] getCommands() {
    return commands;
  }

  int getCommandCount() {
    return commands.length;
  }

  Item getCurrentItem() {
    //compiled code
    System.out.println( "Compiled Code" );
    return null;
  }

  CommandListener getCommandListener() {
    return listener;
  }

  void addCommandImpl( Command cmd ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void removeCommandImpl( Command cmd ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  void updateCommandSet() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  boolean commandInSetImpl( Command command ) {
    //compiled code
    System.out.println( "Compiled Code" );
    return false;
  }

  private void repaintTitle() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  private void startTicker() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  private void stopTicker() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  private void repaintTickerText() {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  private boolean sizeChangeImpl() {
    //compiled code
    System.out.println( "Compiled Code" );
    return false;
  }

  protected void keyPressed( int keyCode ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  protected void keyRepeated( int keyCode ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  protected void keyReleased( int keyCode ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  protected void pointerPressed( int x, int y ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  protected void pointerReleased( int x, int y ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  protected void pointerDragged( int x, int y ) {
    //compiled code
    System.out.println( "Compiled Code" );
  }

  native void grabFullScreen( boolean arg0 );
}
