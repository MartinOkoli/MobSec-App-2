package androidx.media;

import android.content.Context;
import androidx.media.MediaSessionManager;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\media\MediaSessionManagerImplApi21.smali */
class MediaSessionManagerImplApi21 extends MediaSessionManagerImplBase {
    MediaSessionManagerImplApi21(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // androidx.media.MediaSessionManagerImplBase, androidx.media.MediaSessionManager.MediaSessionManagerImpl
    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl userInfo) {
        return hasMediaControlPermission(userInfo) || super.isTrustedForMediaControl(userInfo);
    }

    private boolean hasMediaControlPermission(MediaSessionManager.RemoteUserInfoImpl userInfo) {
        return getContext().checkPermission("android.permission.MEDIA_CONTENT_CONTROL", userInfo.getPid(), userInfo.getUid()) == 0;
    }
}
