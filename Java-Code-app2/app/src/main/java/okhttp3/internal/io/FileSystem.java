package okhttp3.internal.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import okio.Sink;
import okio.Source;

/* compiled from: FileSystem.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/io/FileSystem;", "", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", "from", "to", "sink", "size", "", "source", "Lokio/Source;", "Companion", "okhttp"}, k = 1, mv = {1, 4, 0})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\okhttp3\internal\io\FileSystem.smali */
public interface FileSystem {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final FileSystem SYSTEM = new FileSystem() { // from class: okhttp3.internal.io.FileSystem$Companion$SystemFileSystem
        @Override // okhttp3.internal.io.FileSystem
        public Source source(File file) throws FileNotFoundException {
            Intrinsics.checkNotNullParameter(file, "file");
            return Okio.source(file);
        }

        @Override // okhttp3.internal.io.FileSystem
        public Sink sink(File file) throws FileNotFoundException {
            Intrinsics.checkNotNullParameter(file, "file");
            try {
                return Okio__JvmOkioKt.sink$default(file, false, 1, null);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return Okio__JvmOkioKt.sink$default(file, false, 1, null);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public Sink appendingSink(File file) throws FileNotFoundException {
            Intrinsics.checkNotNullParameter(file, "file");
            try {
                return Okio.appendingSink(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return Okio.appendingSink(file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void delete(File file) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public boolean exists(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return file.exists();
        }

        @Override // okhttp3.internal.io.FileSystem
        public long size(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return file.length();
        }

        @Override // okhttp3.internal.io.FileSystem
        public void rename(File from, File to) throws IOException {
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            delete(to);
            if (!from.renameTo(to)) {
                throw new IOException("failed to rename " + from + " to " + to);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void deleteContents(File directory) throws IOException {
            Intrinsics.checkNotNullParameter(directory, "directory");
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("not a readable directory: " + directory);
            }
            for (File file : files) {
                Intrinsics.checkNotNullExpressionValue(file, "file");
                if (file.isDirectory()) {
                    deleteContents(file);
                }
                if (!file.delete()) {
                    throw new IOException("failed to delete " + file);
                }
            }
        }

        public String toString() {
            return "FileSystem.SYSTEM";
        }
    };

    Sink appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File directory) throws IOException;

    boolean exists(File file);

    void rename(File from, File to) throws IOException;

    Sink sink(File file) throws FileNotFoundException;

    long size(File file);

    Source source(File file) throws FileNotFoundException;
}
