package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ktx.DatabaseKt;
import com.google.firebase.ktx.Firebase;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FirebaseDatabase.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Linfosecadventures/allsafe/challenges/FirebaseDatabase;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\FirebaseDatabase.smali */
public final class FirebaseDatabase extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_firebase_database, container, false);
        Intrinsics.checkNotNullExpressionValue(view, "inflater.inflate(R.layou…tabase, container, false)");
        View viewFindViewById = view.findViewById(R.id.execute);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.execute)");
        Button execute = (Button) viewFindViewById;
        execute.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.FirebaseDatabase.onCreateView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) throws DatabaseException {
                com.google.firebase.database.FirebaseDatabase database = DatabaseKt.getDatabase(Firebase.INSTANCE);
                DatabaseReference reference = database.getReference().child("secret");
                Intrinsics.checkNotNullExpressionValue(reference, "database.reference.child(\"secret\")");
                reference.addValueEventListener(new ValueEventListener() { // from class: infosecadventures.allsafe.challenges.FirebaseDatabase.onCreateView.1.1
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Intrinsics.checkNotNullParameter(dataSnapshot, "dataSnapshot");
                        SnackUtil snackUtil = SnackUtil.INSTANCE;
                        FragmentActivity fragmentActivityRequireActivity = FirebaseDatabase.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                        FragmentActivity fragmentActivity = fragmentActivityRequireActivity;
                        Object value = dataSnapshot.getValue();
                        if (value == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        snackUtil.simpleMessage(fragmentActivity, (String) value);
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                        Intrinsics.checkNotNullParameter(databaseError, "databaseError");
                        SnackUtil snackUtil = SnackUtil.INSTANCE;
                        FragmentActivity fragmentActivityRequireActivity = FirebaseDatabase.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                        snackUtil.simpleMessage(fragmentActivityRequireActivity, "Sorry, database error!");
                    }
                });
            }
        });
        return view;
    }
}
