package javax.microedition.midlet;

/**
 * @author solkin
 */
public abstract class MIDlet {

    private boolean destroyed;

    protected MIDlet() {

    }

    public abstract void startApp() throws MIDletStateChangeException;

    protected abstract void pauseApp();

    protected abstract void destroyApp(boolean unconditional) throws MIDletStateChangeException;

    public final int checkPermission(String permission) {
        return 0;
    }

    public final String getAppProperty(String key) {
        return null;
    }

    public final void notifyDestroyed() {
        destroyed = true;

    }

    public final void notifyPaused() {
    }

    public final boolean platformRequest(String URL) throws Throwable {
        return false;
    }

    public final void resumeRequest() {
        // TODO implement
    }
}
