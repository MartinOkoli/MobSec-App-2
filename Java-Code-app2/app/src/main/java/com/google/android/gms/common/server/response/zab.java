package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\server\response\zab.smali */
final class zab implements FastParser.zaa<Long> {
    zab() {
    }

    public final /* synthetic */ Object zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return Long.valueOf(fastParser.zae(bufferedReader));
    }
}
