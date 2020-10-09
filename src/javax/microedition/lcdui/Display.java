/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javax.microedition.lcdui;

import tomemu.MidletPanel;

import javax.microedition.midlet.MIDlet;

/**
 * @author solkin
 */
public class Display {

    private static Displayable displayable;
    public static Display display = new Display();
    // public static Graphics graphics;
    public static MidletPanel midletPanel;

    public static Display getDisplay(MIDlet midlet) {
        return display;
    }

    public Displayable getCurrent() {
        //compiled code
        System.out.println("Compiled Code");
        return displayable;
    }

    public void setCurrent(Displayable nextDisplayable) {
        //compiled code
        System.out.println("Compiled Code");
        displayable = nextDisplayable;
    }

    public static void keyPressed(int keyCode) {
        //compiled code
        System.out.println("Compiled Code");
        displayable.keyPressed(keyCode);
    }

    public static void keyRepeated(int keyCode) {
        //compiled code
        System.out.println("Compiled Code");
        displayable.keyRepeated(keyCode);
    }

    public static void keyReleased(int keyCode) {
        //compiled code
        System.out.println("Compiled Code");
        displayable.keyReleased(keyCode);
    }

    public static void pointerPressed(int x, int y) {
        //compiled code
        System.out.println("Compiled Code");
        displayable.pointerPressed(x, y);
    }

    public static void pointerReleased(int x, int y) {
        //compiled code
        System.out.println("Compiled Code");
        displayable.pointerReleased(x, y);
    }

    public static void pointerDragged(int x, int y) {
        //compiled code
        System.out.println("Compiled Code");
        displayable.pointerDragged(x, y);
    }
}
