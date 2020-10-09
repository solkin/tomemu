package javax.microedition.lcdui;

public class TextField extends Item {

    private class InputMethodClientImpl {

        // private InputMethodHandler imh;
        private int constraints;
        private String inputMode;
        private String[] allowedModes;

        private InputMethodClientImpl() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        /* public void setInputMethodHandler(InputMethodHandler imh) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }*/

        public void setInputMode(String inputMode) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public String getInputMode() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public void setAllowedModes(String[] allowedModes) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public String[] getAllowedModes() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public int getConstraints() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public boolean setConstraints(int constraints) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public void keyEntered(int keyCode) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public void showInputMode(int mode) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public Display getDisplay() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public void setCurrent(Displayable displayable, Display display) {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public boolean isNewWord() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public boolean isNewSentence() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }

        public boolean isNewInputEntry() {
            //compiled code
            throw new RuntimeException("Compiled Code");
        }
    }

    public static final int ANY = 0;
    public static final int EMAILADDR = 1;
    public static final int NUMERIC = 2;
    public static final int PHONENUMBER = 3;
    public static final int URL = 4;
    public static final int DECIMAL = 5;
    public static final int PASSWORD = 65536;
    public static final int UNEDITABLE = 131072;
    public static final int SENSITIVE = 262144;
    public static final int NON_PREDICTIVE = 524288;
    public static final int INITIAL_CAPS_WORD = 1048576;
    public static final int INITIAL_CAPS_SENTENCE = 2097152;
    public static final int CONSTRAINT_MASK = 65535;
    private boolean firstTimeInTraverse;
    private boolean editable;
    private static int BORDER_PAD;
    // DynamicCharacterArray buffer;
    // TextCursor cursor;
    char currentInputChar;
    int oldNumChars;
    boolean multiLine;
    boolean hasBorder;
    boolean usePreferredX;
    String initialInputMode;
    // InputMethodHandler inputHandler;
    InputMethodClientImpl inputClient;
    private boolean ignoreNextKeyEntered;
    private char[] inputChars;
    private static final int FRONT_PART = 0;
    private static final int LAST_PART = 1;
    private static final int LAST_LABEL_PART_INDEX = 1;
    private String[] cachedLabelParts;
    private int[] cachedLabelPartWidths;
    private int[] cachedLabelPartHeights;
    private int[] cachedReqLabelPartW;

    public TextField(String label, String text, int maxSize, int constraints) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public String getString() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setString(String text) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int getChars(char[] data) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setChars(char[] data, int offset, int length) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void insert(String src, int position) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void insert(char[] data, int offset, int length, int position) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private void insert(char ch, int position) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void delete(int offset, int length) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int getMaxSize() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int setMaxSize(int maxSize) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int size() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int getCaretPosition() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setConstraints(int constraints) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public int getConstraints() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setInitialInputMode(String characterSubset) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void commitPendingInteraction() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    boolean equateNLA() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    boolean equateNLB() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    int callMinimumWidth() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    int callPreferredWidth(int h) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    int callMinimumHeight() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    int callPreferredHeight(int w) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void callPaint(Graphics g, int width, int height) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void validateCursor() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void keyEntered(int keyCode) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void callKeyPressed(int keyCode) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void callKeyReleased(int keyCode) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void callKeyRepeated(int keyCode) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void callKeyTyped(char c) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    boolean moveCursor(int dir) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    boolean callTraverse(int dir, int viewportWidth, int viewportHeight, int[] visRect_inout) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void callTraverseOut() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void callHideNotify() {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    void setBorder(boolean state) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private String getLabelPart(int index) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private int getLabelPartWidth(int index) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private int getLabelPartHeight(int index, int w) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private void initializeLabelParts(String label) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    private int paintLabelPart(Graphics g, int index, int width) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }

    public void setLabel(String label) {
        //compiled code
        throw new RuntimeException("Compiled Code");
    }
}
