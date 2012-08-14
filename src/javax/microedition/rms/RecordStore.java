package javax.microedition.rms;

import java.io.IOException;
import java.util.Vector;

public class RecordStore {

    private class RecordHeaderCache {

        private RecordHeader[] mCache;

        RecordHeaderCache(int size) {
            //compiled code
            System.out.println("Compiled Code");
        }

        RecordHeader get(int rec_id) {
            //compiled code
            System.out.println("Compiled Code");
            return null;
        }

        void insert(RecordHeader rh) {
            //compiled code
            System.out.println("Compiled Code");
        }

        void invalidate(int rec_id) {
            //compiled code
            System.out.println("Compiled Code");
        }
    }

    private class RecordHeader {

        private static final int REC_ID = 0;
        private static final int NEXT_OFFSET = 4;
        private static final int BLOCK_SIZE = 8;
        private static final int DATALEN_OR_NEXTFREE = 12;
        private static final int DATA_OFFSET = 16;
        int offset;
        int id;
        int nextOffset;
        int blockSize;
        int dataLenOrNextFree;

        RecordHeader() {
            //compiled code
            System.out.println("Compiled Code");
        }

        RecordHeader(int _offset) throws IOException {
            //compiled code
            System.out.println("Compiled Code");
        }

        RecordHeader(int _offset, int _id, int next_offset, int size, int len_or_free) {
            //compiled code
            System.out.println("Compiled Code");
        }

        void load(int _offset) throws IOException {
            //compiled code
            System.out.println("Compiled Code");
        }

        void store() throws IOException {
            //compiled code
            System.out.println("Compiled Code");
        }

        int read(byte[] buf, int _offset) throws IOException {
            //compiled code
            System.out.println("Compiled Code");
            return 0;
        }

        void write(byte[] buf, int _offset) throws IOException {
            //compiled code
            System.out.println("Compiled Code");
        }
    }
    public static final int AUTHMODE_PRIVATE = 0;
    public static final int AUTHMODE_ANY = 1;
    private static final int AUTHMODE_ANY_RO = 2;
    private static final byte[] DB_INIT=null;
    private static final int SIGNATURE_LENGTH = 8;
    private static final int DB_RECORD_HEADER_LENGTH = 16;
    private static final int DB_BLOCK_SIZE = 16;
    private static final int DB_COMPACTBUFFER_SIZE = 64;
    private static Vector dbCache=null;
    private static final Object dbCacheLock=null;
    private static RecordStoreFile rootRSF;
    private String recordStoreName;
    private String uniqueIdPath;
    private int opencount;
    private RecordStoreFile dbraf;
    Object rsLock;
    private Vector recordListener;
    private RecordHeaderCache recHeadCache;
    private static int CACHE_SIZE;
    private static byte[] recHeadBuf;
    private int dbNextRecordID;
    private int dbVersion;
    private int dbAuthMode;
    private int dbNumLiveRecords;
    private long dbLastModified;
    private int dbFirstRecordOffset;
    private int dbFirstFreeBlockOffset;
    private int dbDataStart;
    private int dbDataEnd;
    private static byte[] dbState;
    private static final int RS_SIGNATURE = 0;
    private static final int RS_NUM_LIVE = 8;
    private static final int RS_AUTHMODE = 12;
    private static final int RS_VERSION = 16;
    private static final int RS_NEXT_ID = 20;
    private static final int RS_REC_START = 24;
    private static final int RS_FREE_START = 28;
    private static final int RS_LAST_MODIFIED = 32;
    private static final int RS_DATA_START = 40;
    private static final int RS_DATA_END = 44;
    private static final int bytesPer100millis=0;
    private static final int latency=0;
    private static int accessCount;
    private static long lastTime;
    private static int lastRead;

    private RecordStore() {
        //compiled code
        System.out.println("Compiled Code");
    }

    public static void deleteRecordStore(String recordStoreName) throws RecordStoreException, RecordStoreNotFoundException {
        //compiled code
        System.out.println("Compiled Code");
    }

    public static RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary) throws RecordStoreException, RecordStoreFullException, RecordStoreNotFoundException {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    public static RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary, int authmode, boolean writable) throws RecordStoreException, RecordStoreFullException, RecordStoreNotFoundException {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    public static RecordStore openRecordStore(String recordStoreName, String vendorName, String suiteName) throws RecordStoreException, RecordStoreNotFoundException {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    public void setMode(int authmode, boolean writable) throws RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    public void closeRecordStore() throws RecordStoreNotOpenException, RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    public static String[] listRecordStores() {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    public String getName() throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    public int getVersion() throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public int getNumRecords() throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public int getSize() throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public int getSizeAvailable() throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public long getLastModified() throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public void addRecordListener(RecordListener listener) {
        //compiled code
        System.out.println("Compiled Code");
    }

    public void removeRecordListener(RecordListener listener) {
        //compiled code
        System.out.println("Compiled Code");
    }

    public int getNextRecordID() throws RecordStoreNotOpenException, RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public int addRecord(byte[] data, int offset, int numBytes) throws RecordStoreNotOpenException, RecordStoreException, RecordStoreFullException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public void deleteRecord(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    public int getRecordSize(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public int getRecord(int recordId, byte[] buffer, int offset) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    public byte[] getRecord(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    public void setRecord(int recordId, byte[] newData, int offset, int numBytes) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException, RecordStoreFullException {
        //compiled code
        System.out.println("Compiled Code");
    }

    /*public RecordEnumeration enumerateRecords(RecordFilter filter, RecordComparator comparator, boolean keepUpdated) throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
    }*/

    private RecordHeader findRecord(int recordId, boolean addToCache) throws InvalidRecordIDException, IOException {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    private int getAllocSize(int numBytes) {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    private RecordHeader allocateNewRecordStorage(int id, int dataSize) throws RecordStoreException, RecordStoreFullException {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    private void splitRecord(RecordHeader recHead, int allocSize) throws RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    private void freeRecord(RecordHeader rh) throws RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    private void removeFreeBlock(RecordHeader blockToFree) throws RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    private void storeDBState() throws RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    boolean isOpen() {
        //compiled code
        System.out.println("Compiled Code");
        return false;
    }

    private void checkOpen() throws RecordStoreNotOpenException {
        //compiled code
        System.out.println("Compiled Code");
    }

    private void notifyRecordChangedListeners(int recordId) {
        //compiled code
        System.out.println("Compiled Code");
    }

    private void notifyRecordAddedListeners(int recordId) {
        //compiled code
        System.out.println("Compiled Code");
    }

    private void notifyRecordDeletedListeners(int recordId) {
        //compiled code
        System.out.println("Compiled Code");
    }

    static int getInt(byte[] data, int offset) {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    static long getLong(byte[] data, int offset) {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    static int putInt(int i, byte[] data, int offset) {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    static int putLong(long l, byte[] data, int offset) {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    int[] getRecordIDs() {
        //compiled code
        System.out.println("Compiled Code");
            return null;
    }

    private void compactRecords() throws RecordStoreNotOpenException, RecordStoreException {
        //compiled code
        System.out.println("Compiled Code");
    }

    static synchronized int getBytesQuota(int request) {
        //compiled code
        System.out.println("Compiled Code");
            return 0;
    }

    static synchronized void addLatency() {
        //compiled code
        System.out.println("Compiled Code");
    }

    private RecordStore(String uidPath, String recordStoreName, boolean create) throws RecordStoreException, RecordStoreNotFoundException {
        //compiled code
        System.out.println("Compiled Code");
    }

    private boolean checkOwner() {
        //compiled code
        System.out.println("Compiled Code");
            return false;
    }

    private boolean checkWritable() {
        //compiled code
        System.out.println("Compiled Code");
            return false;
    }

    private static native int getSlowingFactor();
}
