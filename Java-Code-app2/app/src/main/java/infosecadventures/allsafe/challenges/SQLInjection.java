package infosecadventures.allsafe.challenges;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import infosecadventures.allsafe.R;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: SQLInjection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Linfosecadventures/allsafe/challenges/SQLInjection;", "Landroidx/fragment/app/Fragment;", "()V", "md5", "", "input", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "populateDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\SQLInjection.smali */
public final class SQLInjection extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SQLException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_sql_injection, container, false);
        Intrinsics.checkNotNullExpressionValue(view, "inflater.inflate(R.layou…ection, container, false)");
        final SQLiteDatabase db = populateDatabase();
        View viewFindViewById = view.findViewById(R.id.username);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.username)");
        final TextInputEditText username = (TextInputEditText) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.password);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.password)");
        final TextInputEditText password = (TextInputEditText) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.login);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.login)");
        Button login = (Button) viewFindViewById3;
        login.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.SQLInjection.onCreateView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Cursor cursor = db.rawQuery("select * from user where username = '" + String.valueOf(username.getText()) + "' and password = '" + SQLInjection.this.md5(String.valueOf(password.getText())) + "'", null);
                Intrinsics.checkNotNullExpressionValue(cursor, "db.rawQuery(\"select * fr….toString()) + \"'\", null)");
                StringBuilder data = new StringBuilder();
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
                        String user = cursor.getString(1);
                        String pass = cursor.getString(2);
                        data.append("User: " + user + " \nPass: " + pass + '\n');
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(SQLInjection.this.getContext(), data, 1).show();
            }
        });
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        Charset charset = Charsets.UTF_8;
        if (input == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = input.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        String string = new BigInteger(1, md.digest(bytes)).toString(16);
        Intrinsics.checkNotNullExpressionValue(string, "BigInteger(1, md.digest(…yteArray())).toString(16)");
        return StringsKt.padStart(string, 32, '0');
    }

    private final SQLiteDatabase populateDatabase() throws SQLException {
        SQLiteDatabase db = requireActivity().openOrCreateDatabase("allsafe", 0, null);
        db.execSQL("drop table if exists user");
        db.execSQL("create table user ( id integer primary key autoincrement, username text, password text )");
        db.execSQL("insert into user ( username, password ) values ('admin', '21232f297a57a5a743894a0e4a801fc3')");
        db.execSQL("insert into user ( username, password ) values ('elliot.alderson', '3484cef7f6ff172c2cd278d3b51f3e66')");
        db.execSQL("insert into user ( username, password ) values ('angela.moss', '0af58729667eace3883a992ef2b8ce29')");
        db.execSQL("insert into user ( username, password ) values ('gideon.goddard', '65dc3431f8c5e3f0e249c5b1c6e3534d')");
        db.execSQL("insert into user ( username, password ) values ('tyrell.wellick', '6d2e1c6dd505a108cc7e19a46aa30a8a')");
        db.execSQL("insert into user ( username, password ) values ('darlene.alderson', 'd510b80eb22f8eb684f1a19681eb7bcf')");
        Intrinsics.checkNotNullExpressionValue(db, "db");
        return db;
    }
}
