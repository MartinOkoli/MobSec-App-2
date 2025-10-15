package android.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\customtabs\IPostMessageService.smali */
public interface IPostMessageService extends IInterface {
    void onMessageChannelReady(ICustomTabsCallback callback, Bundle extras) throws RemoteException;

    void onPostMessage(ICustomTabsCallback callback, String message, Bundle extras) throws RemoteException;

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\customtabs\IPostMessageService$Default.smali */
    public static class Default implements IPostMessageService {
        @Override // android.support.customtabs.IPostMessageService
        public void onMessageChannelReady(ICustomTabsCallback callback, Bundle extras) throws RemoteException {
        }

        @Override // android.support.customtabs.IPostMessageService
        public void onPostMessage(ICustomTabsCallback callback, String message, Bundle extras) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\customtabs\IPostMessageService$Stub.smali */
    public static abstract class Stub extends Binder implements IPostMessageService {
        private static final String DESCRIPTOR = "android.support.customtabs.IPostMessageService";
        static final int TRANSACTION_onMessageChannelReady = 2;
        static final int TRANSACTION_onPostMessage = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPostMessageService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IPostMessageService)) {
                return (IPostMessageService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg1;
            Bundle _arg2;
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                ICustomTabsCallback _arg0 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
                if (data.readInt() != 0) {
                    _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                onMessageChannelReady(_arg0, _arg1);
                reply.writeNoException();
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            ICustomTabsCallback _arg02 = ICustomTabsCallback.Stub.asInterface(data.readStrongBinder());
            String _arg12 = data.readString();
            if (data.readInt() != 0) {
                _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
            } else {
                _arg2 = null;
            }
            onPostMessage(_arg02, _arg12, _arg2);
            reply.writeNoException();
            return true;
        }

        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\customtabs\IPostMessageService$Stub$Proxy.smali */
        private static class Proxy implements IPostMessageService {
            public static IPostMessageService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.support.customtabs.IPostMessageService
            public void onMessageChannelReady(ICustomTabsCallback callback, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMessageChannelReady(callback, extras);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.support.customtabs.IPostMessageService
            public void onPostMessage(ICustomTabsCallback callback, String message, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(message);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPostMessage(callback, message, extras);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPostMessageService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IPostMessageService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
