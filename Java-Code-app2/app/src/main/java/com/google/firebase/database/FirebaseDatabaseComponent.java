package com.google.firebase.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.android.AndroidAuthTokenProvider;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.database.core.DatabaseConfig;
import com.google.firebase.database.core.RepoInfo;
import com.google.firebase.inject.Deferred;
import java.util.HashMap;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\FirebaseDatabaseComponent.smali */
class FirebaseDatabaseComponent {
    private final FirebaseApp app;
    private final AuthTokenProvider authProvider;
    private final Map<RepoInfo, FirebaseDatabase> instances = new HashMap();

    FirebaseDatabaseComponent(FirebaseApp app, Deferred<InternalAuthProvider> authProvider) {
        this.app = app;
        this.authProvider = new AndroidAuthTokenProvider(authProvider);
    }

    synchronized FirebaseDatabase get(RepoInfo repo) {
        FirebaseDatabase database;
        database = this.instances.get(repo);
        if (database == null) {
            DatabaseConfig config = new DatabaseConfig();
            if (!this.app.isDefaultApp()) {
                config.setSessionPersistenceKey(this.app.getName());
            }
            config.setFirebaseApp(this.app);
            config.setAuthTokenProvider(this.authProvider);
            database = new FirebaseDatabase(this.app, repo, config);
            this.instances.put(repo, database);
        }
        return database;
    }
}
