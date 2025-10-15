package infosecadventures.allsafe.challenges;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.ktx.StorageKt;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InsecureProviders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Linfosecadventures/allsafe/challenges/InsecureProviders;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\InsecureProviders.smali */
public final class InsecureProviders extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FirebaseStorage storage = StorageKt.getStorage(Firebase.INSTANCE);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        File filesDir = contextRequireContext.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "requireContext().filesDir");
        File docs = new File(filesDir.getAbsolutePath(), "/docs");
        if (!docs.exists()) {
            docs.mkdir();
        }
        File file = new File(docs, "readme.txt");
        StorageReference readme = storage.getReferenceFromUrl("gs://allsafe-8cef0.appspot.com/readme.txt");
        Intrinsics.checkNotNullExpressionValue(readme, "storage.getReferenceFrom….appspot.com/readme.txt\")");
        readme.getFile(file).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<FileDownloadTask.TaskSnapshot>() { // from class: infosecadventures.allsafe.challenges.InsecureProviders.onCreateView.1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(FileDownloadTask.TaskSnapshot it) {
                SnackUtil snackUtil = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity = InsecureProviders.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                snackUtil.simpleMessage(fragmentActivityRequireActivity, "File successfully downloaded!");
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: infosecadventures.allsafe.challenges.InsecureProviders.onCreateView.2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                SnackUtil snackUtil = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity = InsecureProviders.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                snackUtil.simpleMessage(fragmentActivityRequireActivity, "Sorry, something went wrong!!");
            }
        });
        return inflater.inflate(R.layout.fragment_insecure_providers, container, false);
    }
}
