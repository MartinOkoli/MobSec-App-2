package infosecadventures.allsafe.challenges;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\DataProvider.smali */
public class DataProvider extends ContentProvider {
    private static final UriMatcher uriMatcher = new UriMatcher(-1);
    NoteDatabaseHelper noteDatabaseHelper;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.noteDatabaseHelper = new NoteDatabaseHelper(getContext());
        uriMatcher.addURI("infosecadventures.allsafe.dataprovider", "note", 123);
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables("note");
        return queryBuilder.query(this.noteDatabaseHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        return ContentUris.withAppendedId(uri, this.noteDatabaseHelper.getWritableDatabase().insert("note", null, values));
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return this.noteDatabaseHelper.getWritableDatabase().delete("note", selection, selectionArgs);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return this.noteDatabaseHelper.getWritableDatabase().update("note", values, selection, selectionArgs);
    }
}
