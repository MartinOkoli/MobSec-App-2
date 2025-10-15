package infosecadventures.allsafe.challenges;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\NoteDatabaseHelper.smali */
public class NoteDatabaseHelper extends SQLiteOpenHelper {
    public NoteDatabaseHelper(Context context) {
        super(context, "notes.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) throws SQLException {
        db.execSQL("drop table if exists note");
        db.execSQL("create table note ( id integer primary key autoincrement, user text, note text )");
        db.execSQL("insert into note ( user, note ) values ('admin', 'I can not believe that Jill is still using 123456 as her password...')");
        db.execSQL("insert into note ( user, note ) values ('elliot.alderson', 'A bug is never just a mistake. It represents something bigger. An error of thinking. That makes you who you are.')");
        db.execSQL("insert into note ( user, note ) values ('darlene.alderson', 'That’s the trick about money. Banks care more about it than anything else.')");
        db.execSQL("insert into note ( user, note ) values ('gideon.goddard', 'You’re never sure about anything unless there’s something to be sure about.')");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
