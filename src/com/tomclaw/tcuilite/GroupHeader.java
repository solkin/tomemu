package com.tomclaw.tcuilite;

import java.util.Vector;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2012
 * http://www.tomclaw.com/
 * @author Solkin
 */
public class GroupHeader {

  public String title;
  public boolean isCollapsed = true;
  public Vector childs = null;
  public Thread thread;
  public boolean isGroupVisible = true;
  public boolean isItemsVisible = true;
  /**
   * Location on the screen
   */
  public int row;
  public int column;

  public GroupHeader( String title ) {
    this.title = title;
    childs = null;
  }

  public void addChild( GroupChild groupChild ) {
    if ( childs == null || childs.isEmpty() ) {
      childs = new Vector();
    }
    childs.addElement( groupChild );
  }

  public int getChildsCount() {
    if ( childs == null || childs.isEmpty() ) {
      return 0;
    }
    return childs.size();
  }

  public void actionPerformed() {
    if ( thread != null ) {
      thread.run();
    }
  }
}
