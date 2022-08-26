package se.arctosoft.vault.data;

import android.net.Uri;
import android.provider.DocumentsContract;

public class CursorFile implements Comparable<CursorFile> {
    private final String name;
    private final Uri uri;
    private final long lastModified, size;
    private final String mimeType;
    private final boolean isDirectory;
    private String unencryptedName;

    public CursorFile(String name, Uri uri, long lastModified, String mimeType, long size) {
        this.name = name;
        this.uri = uri;
        this.mimeType = mimeType;
        this.size = size;
        this.isDirectory = DocumentsContract.Document.MIME_TYPE_DIR.equals(mimeType);
        this.lastModified = isDirectory ? System.currentTimeMillis() : lastModified;
    }

    public void setUnencryptedName(String unencryptedName) {
        this.unencryptedName = unencryptedName;
    }

    public long getSize() {
        return size;
    }

    public String getUnencryptedName() {
        return unencryptedName;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getName() {
        return name;
    }

    public Uri getUri() {
        return uri;
    }

    public long getLastModified() {
        return lastModified;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public int compareTo(CursorFile o) {
        return Long.compare(o.lastModified, this.lastModified);
    }
}
