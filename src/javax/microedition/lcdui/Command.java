package javax.microedition.lcdui;

public class Command {

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

    public Command(String label, int commandType, int priority) {
        //compiled code
        // throw new RuntimeException("Compiled Code");
    }

    public Command(String shortLabel, String longLabel, int commandType, int priority) {
        //compiled code
        // throw new RuntimeException("Compiled Code");
    }

    public String getLabel() {
        //compiled code
        return "";//throw new RuntimeException("Compiled Code");
    }

    public String getLongLabel() {
        //compiled code
        return "";//throw new RuntimeException("Compiled Code");
    }

    public int getCommandType() {
        //compiled code
        return 0;//throw new RuntimeException("Compiled Code");
    }

    public int getPriority() {
        //compiled code
        return 0;//throw new RuntimeException("Compiled Code");
    }

    int getID() {
        //compiled code
        return 0;//throw new RuntimeException("Compiled Code");
    }

    void setInternalID(int num) {
        //compiled code
        //throw new RuntimeException("Compiled Code");
    }

    private void setLabel(String shortLabel, String longLabel) {
        //compiled code
        //throw new RuntimeException("Compiled Code");
    }

    private final void initialize(int commandType, int priority) {
        //compiled code
        //throw new RuntimeException("Compiled Code");
    }
}
