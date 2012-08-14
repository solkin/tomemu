package javax.microedition.lcdui;

import javax.swing.JButton;

public class Command extends JButton {

  public static final int SCREEN = 1;
  public static final int BACK = 2;
  public static final int CANCEL = 3;
  public static final int OK = 4;
  public static final int HELP = 5;
  public static final int STOP = 6;
  public static final int EXIT = 7;
  public static final int ITEM = 8;
  String shortLabel;
  String longLabel;
  int commandType;
  int priority;
  private int id;

  public Command( String label, int commandType, int priority ) {
    setLabel( label, label );
  }

  public Command( String shortLabel, String longLabel, int commandType, int priority ) {
    setLabel( shortLabel, longLabel );
  }

  @Override
  public String getLabel() {
    return shortLabel;
  }

  public String getLongLabel() {
    return longLabel;
  }

  public int getCommandType() {
    return commandType;
  }

  public int getPriority() {
    return priority;
  }

  int getID() {
    return id;
  }

  void setInternalID( int num ) {
    this.id = num;
  }

  private void setLabel( String shortLabel, String longLabel ) {
    this.shortLabel = shortLabel;
    this.longLabel = longLabel;
    setText( shortLabel );
  }

  private void initialize( int commandType, int priority ) {
    this.commandType = commandType;
    this.priority = priority;
  }
}
