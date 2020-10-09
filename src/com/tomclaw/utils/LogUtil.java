package com.tomclaw.utils;

import java.io.IOException;
import java.io.OutputStream;
//import javax.microedition.io.Connector;
//import javax.microedition.io.SocketConnection;
//import javax.microedition.io.file.FileConnection;

/**
 * Solkin Igor Viktorovich, TomClaw Software, 2003-2013
 * http://www.tomclaw.com/
 *
 * @author Solkin
 */
public final class LogUtil {

    public static boolean isOutToSock = false;
    public static boolean isOutToFile = false;
    public static boolean isOutToCons = true;
    public static String filePath = "/root1/";
    private static OutputStream fileOutputStream = null;
    private static OutputStream sockOutputStream = null;
    private static OutputStream consOutputStream = null;
    public static long startTime = 0;
    public static boolean isShowMessages = true;

    public static void initLogger(boolean isOutToCons, boolean isOutToSock,
                                  String host, int port, boolean isOutToFile, String filePath) {
        LogUtil.isOutToCons = isOutToCons;
        LogUtil.isOutToFile = isOutToFile;
        LogUtil.isOutToSock = isOutToSock;
        LogUtil.filePath = filePath;
        if (isOutToFile) {
            try {
                openFileConnection();
            } catch (Throwable ex) {
            }
        }
        if (isOutToCons) {
            try {
                openConsConnection();
            } catch (Throwable ex) {
            }
        }
        if (isOutToSock) {
            try {
                openSockConnection(host, port);
            } catch (Throwable ex) {
            }
        }
        startTime = System.currentTimeMillis();
        outSystem("Logger started at: " + startTime);
    }

    public static void outMessage(String logMessage) {
        outMessage(logMessage, false);
    }

    public static void outMessage(Class clazz, Throwable ex) {
        outMessage(clazz.getName() + " :: " + ex.toString() + " [ "
                + ex.getMessage() + " ]", true);
    }

    public static void outMessage(Throwable ex) {
        outMessage(ex.toString() + " : " + ex.getMessage(), true);
    }

    public static void outMessage(String logMessage, boolean isError) {
        if (isShowMessages) {
            logMessage = "[" + Runtime.getRuntime().freeMemory() / 1024 + " KiB / "
                    + Runtime.getRuntime().totalMemory() / 1024 + " KiB] ("
                    + (System.currentTimeMillis() - startTime) + " fs-ms.) "
                    + (isError ? "[ERR] " : "") + logMessage;
            outSystem(logMessage + "\n");
        }
    }

    public static void outSystem(String logMessage) {
        if (isOutToFile) {
            write(fileOutputStream, logMessage);
        }
        if (isOutToSock) {
            write(sockOutputStream, logMessage);
        }
        if (isOutToCons) {
            write(consOutputStream, logMessage);
        }
    }

    public static OutputStream[] getOutputStreams() {
        return new OutputStream[]{fileOutputStream, sockOutputStream,
                consOutputStream};
    }

    private static void openFileConnection() throws IOException {
        String fileName = "file://" + filePath + "logutil_"
                + System.currentTimeMillis() + ".log";
//    FileConnection fileConnection =
//            ( FileConnection ) Connector.open( fileName, 3 );
//    if ( !( ( FileConnection ) ( fileConnection ) ).exists() ) {
//      ( fileConnection ).create();
//    }
//    fileOutputStream = fileConnection.openOutputStream();
    }

    private static void openConsConnection() throws IOException {
        consOutputStream = System.out;
    }

    private static void openSockConnection(String host, int port)
            throws IOException {
//    SocketConnection socket = ( SocketConnection ) Connector.open(
//            "socket://" + host + ":" + port, Connector.READ_WRITE );
//    sockOutputStream = socket.openOutputStream();
    }

    private static void write(OutputStream outputStream, String logMessage) {
        if (outputStream != null && logMessage != null) {
            try {
                outputStream.write((logMessage).getBytes());
                outputStream.flush();
            } catch (IOException ex) {
            }
        }
    }
}
