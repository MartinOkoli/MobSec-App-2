package okhttp3;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UByte;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;

/* compiled from: HttpUrl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 J2\u00020\u0001:\u0002IJBa\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b!J\r\u0010\u0011\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\"J\r\u0010\u0012\u001a\u00020\u0003H\u0007¢\u0006\u0002\b#J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007¢\u0006\u0002\b$J\u000f\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b%J\r\u0010\u0016\u001a\u00020\u0003H\u0007¢\u0006\u0002\b&J\u0013\u0010'\u001a\u00020\u00182\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u000f\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b)J\b\u0010*\u001a\u00020\bH\u0016J\r\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0002\b+J\u0006\u0010,\u001a\u00020-J\u0010\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u0003J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b/J\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0007¢\u0006\u0002\b0J\r\u0010\u001a\u001a\u00020\bH\u0007¢\u0006\u0002\b1J\r\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\b2J\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b3J\u0010\u00104\u001a\u0004\u0018\u00010\u00032\u0006\u00105\u001a\u00020\u0003J\u000e\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\bJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eH\u0007¢\u0006\u0002\b8J\u0010\u00109\u001a\u0004\u0018\u00010\u00032\u0006\u00107\u001a\u00020\bJ\u0016\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n2\u0006\u00105\u001a\u00020\u0003J\r\u0010 \u001a\u00020\bH\u0007¢\u0006\u0002\b;J\u0006\u0010<\u001a\u00020\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\u00002\u0006\u0010.\u001a\u00020\u0003J\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b>J\b\u0010?\u001a\u00020\u0003H\u0016J\r\u0010@\u001a\u00020AH\u0007¢\u0006\u0002\bBJ\r\u0010C\u001a\u00020DH\u0007¢\u0006\u0002\b\rJ\b\u0010E\u001a\u0004\u0018\u00010\u0003J\r\u0010B\u001a\u00020AH\u0007¢\u0006\u0002\bFJ\r\u0010\r\u001a\u00020DH\u0007¢\u0006\u0002\bGJ\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\bHR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n8G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0016\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0015\u0010\f\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0010R\u0013\u0010\u0006\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0019R\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014R\u0011\u0010\u001a\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0010R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e8G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0011\u0010 \u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b \u0010\u001bR\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0010R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0010¨\u0006K"}, d2 = {"Lokhttp3/HttpUrl;", "", "scheme", "", "username", "password", "host", "port", "", "pathSegments", "", "queryNamesAndValues", "fragment", ImagesContract.URL, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "encodedFragment", "()Ljava/lang/String;", "encodedPassword", "encodedPath", "encodedPathSegments", "()Ljava/util/List;", "encodedQuery", "encodedUsername", "isHttps", "", "()Z", "pathSize", "()I", SearchIntents.EXTRA_QUERY, "queryParameterNames", "", "()Ljava/util/Set;", "querySize", "-deprecated_encodedFragment", "-deprecated_encodedPassword", "-deprecated_encodedPath", "-deprecated_encodedPathSegments", "-deprecated_encodedQuery", "-deprecated_encodedUsername", "equals", "other", "-deprecated_fragment", "hashCode", "-deprecated_host", "newBuilder", "Lokhttp3/HttpUrl$Builder;", "link", "-deprecated_password", "-deprecated_pathSegments", "-deprecated_pathSize", "-deprecated_port", "-deprecated_query", "queryParameter", "name", "queryParameterName", "index", "-deprecated_queryParameterNames", "queryParameterValue", "queryParameterValues", "-deprecated_querySize", "redact", "resolve", "-deprecated_scheme", "toString", "toUri", "Ljava/net/URI;", "uri", "toUrl", "Ljava/net/URL;", "topPrivateDomain", "-deprecated_uri", "-deprecated_url", "-deprecated_username", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 4, 0})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\okhttp3\HttpUrl.smali */
public final class HttpUrl {
    public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    public static final String FRAGMENT_ENCODE_SET = "";
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
    public static final String QUERY_ENCODE_SET = " \"'<>#";
    public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    private final String fragment;
    private final String host;
    private final boolean isHttps;
    private final String password;
    private final List<String> pathSegments;
    private final int port;
    private final List<String> queryNamesAndValues;
    private final String scheme;
    private final String url;
    private final String username;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    @JvmStatic
    public static final int defaultPort(String str) {
        return INSTANCE.defaultPort(str);
    }

    @JvmStatic
    public static final HttpUrl get(String str) {
        return INSTANCE.get(str);
    }

    @JvmStatic
    public static final HttpUrl get(URI uri) {
        return INSTANCE.get(uri);
    }

    @JvmStatic
    public static final HttpUrl get(URL url) {
        return INSTANCE.get(url);
    }

    @JvmStatic
    public static final HttpUrl parse(String str) {
        return INSTANCE.parse(str);
    }

    public HttpUrl(String scheme, String username, String password, String host, int port, List<String> pathSegments, List<String> list, String fragment, String url) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(url, "url");
        this.scheme = scheme;
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.pathSegments = pathSegments;
        this.queryNamesAndValues = list;
        this.fragment = fragment;
        this.url = url;
        this.isHttps = Intrinsics.areEqual(scheme, "https");
    }

    public final String scheme() {
        return this.scheme;
    }

    public final String username() {
        return this.username;
    }

    public final String password() {
        return this.password;
    }

    public final String host() {
        return this.host;
    }

    public final int port() {
        return this.port;
    }

    public final List<String> pathSegments() {
        return this.pathSegments;
    }

    public final String fragment() {
        return this.fragment;
    }

    /* renamed from: isHttps, reason: from getter */
    public final boolean getIsHttps() {
        return this.isHttps;
    }

    public final URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public final URI uri() {
        String uri = newBuilder().reencodeForUri$okhttp().toString();
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            try {
                String stripped = new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").replace(uri, "");
                URI uriCreate = URI.create(stripped);
                Intrinsics.checkNotNullExpressionValue(uriCreate, "try {\n        val stripp…e) // Unexpected!\n      }");
                return uriCreate;
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
    }

    public final String encodedUsername() {
        if (this.username.length() == 0) {
            return "";
        }
        int usernameStart = this.scheme.length() + 3;
        String str = this.url;
        int usernameEnd = Util.delimiterOffset(str, ":@", usernameStart, str.length());
        String str2 = this.url;
        if (str2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str2.substring(usernameStart, usernameEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String encodedPassword() {
        if (this.password.length() == 0) {
            return "";
        }
        int passwordStart = StringsKt.indexOf$default((CharSequence) this.url, ':', this.scheme.length() + 3, false, 4, (Object) null) + 1;
        int passwordEnd = StringsKt.indexOf$default((CharSequence) this.url, '@', 0, false, 6, (Object) null);
        String str = this.url;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(passwordStart, passwordEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final int pathSize() {
        return this.pathSegments.size();
    }

    public final String encodedPath() {
        int pathStart = StringsKt.indexOf$default((CharSequence) this.url, '/', this.scheme.length() + 3, false, 4, (Object) null);
        String str = this.url;
        int pathEnd = Util.delimiterOffset(str, "?#", pathStart, str.length());
        String str2 = this.url;
        if (str2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str2.substring(pathStart, pathEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final List<String> encodedPathSegments() {
        int pathStart = StringsKt.indexOf$default((CharSequence) this.url, '/', this.scheme.length() + 3, false, 4, (Object) null);
        String str = this.url;
        int pathEnd = Util.delimiterOffset(str, "?#", pathStart, str.length());
        List result = new ArrayList();
        int i = pathStart;
        while (i < pathEnd) {
            int i2 = i + 1;
            int segmentEnd = Util.delimiterOffset(this.url, '/', i2, pathEnd);
            String str2 = this.url;
            if (str2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = str2.substring(i2, segmentEnd);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            result.add(strSubstring);
            i = segmentEnd;
        }
        return result;
    }

    public final String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int queryStart = StringsKt.indexOf$default((CharSequence) this.url, '?', 0, false, 6, (Object) null) + 1;
        String str = this.url;
        int queryEnd = Util.delimiterOffset(str, '#', queryStart, str.length());
        String str2 = this.url;
        if (str2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str2.substring(queryStart, queryEnd);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        INSTANCE.toQueryString$okhttp(this.queryNamesAndValues, result);
        return result.toString();
    }

    public final int querySize() {
        List<String> list = this.queryNamesAndValues;
        if (list != null) {
            return list.size() / 2;
        }
        return 0;
    }

    public final String queryParameter(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<String> list = this.queryNamesAndValues;
        if (list == null) {
            return null;
        }
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, list.size()), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step < 0 ? i >= last : i <= last) {
            while (!Intrinsics.areEqual(name, this.queryNamesAndValues.get(i))) {
                if (i != last) {
                    i += step;
                }
            }
            return this.queryNamesAndValues.get(i + 1);
        }
        return null;
    }

    public final Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return SetsKt.emptySet();
        }
        LinkedHashSet result = new LinkedHashSet();
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step < 0 ? i >= last : i <= last) {
            while (true) {
                String str = this.queryNamesAndValues.get(i);
                Intrinsics.checkNotNull(str);
                result.add(str);
                if (i == last) {
                    break;
                }
                i += step;
            }
        }
        Set<String> setUnmodifiableSet = Collections.unmodifiableSet(result);
        Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "Collections.unmodifiableSet(result)");
        return setUnmodifiableSet;
    }

    public final List<String> queryParameterValues(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.queryNamesAndValues == null) {
            return CollectionsKt.emptyList();
        }
        List result = new ArrayList();
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step < 0 ? i >= last : i <= last) {
            while (true) {
                if (Intrinsics.areEqual(name, this.queryNamesAndValues.get(i))) {
                    result.add(this.queryNamesAndValues.get(i + 1));
                }
                if (i == last) {
                    break;
                }
                i += step;
            }
        }
        List<String> listUnmodifiableList = Collections.unmodifiableList(result);
        Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiableList(result)");
        return listUnmodifiableList;
    }

    public final String queryParameterName(int index) {
        List<String> list = this.queryNamesAndValues;
        if (list == null) {
            throw new IndexOutOfBoundsException();
        }
        String str = list.get(index * 2);
        Intrinsics.checkNotNull(str);
        return str;
    }

    public final String queryParameterValue(int index) {
        List<String> list = this.queryNamesAndValues;
        if (list == null) {
            throw new IndexOutOfBoundsException();
        }
        return list.get((index * 2) + 1);
    }

    public final String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        int fragmentStart = StringsKt.indexOf$default((CharSequence) this.url, '#', 0, false, 6, (Object) null) + 1;
        String str = this.url;
        if (str == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = str.substring(fragmentStart);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    public final String redact() {
        Builder builderNewBuilder = newBuilder("/...");
        Intrinsics.checkNotNull(builderNewBuilder);
        return builderNewBuilder.username("").password("").build().getUrl();
    }

    public final HttpUrl resolve(String link) {
        Intrinsics.checkNotNullParameter(link, "link");
        Builder builderNewBuilder = newBuilder(link);
        if (builderNewBuilder != null) {
            return builderNewBuilder.build();
        }
        return null;
    }

    public final Builder newBuilder() {
        Builder result = new Builder();
        result.setScheme$okhttp(this.scheme);
        result.setEncodedUsername$okhttp(encodedUsername());
        result.setEncodedPassword$okhttp(encodedPassword());
        result.setHost$okhttp(this.host);
        result.setPort$okhttp(this.port != INSTANCE.defaultPort(this.scheme) ? this.port : -1);
        result.getEncodedPathSegments$okhttp().clear();
        result.getEncodedPathSegments$okhttp().addAll(encodedPathSegments());
        result.encodedQuery(encodedQuery());
        result.setEncodedFragment$okhttp(encodedFragment());
        return result;
    }

    public final Builder newBuilder(String link) {
        Intrinsics.checkNotNullParameter(link, "link");
        try {
            return new Builder().parse$okhttp(this, link);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean equals(Object other) {
        return (other instanceof HttpUrl) && Intrinsics.areEqual(((HttpUrl) other).url, this.url);
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    /* renamed from: toString, reason: from getter */
    public String getUrl() {
        return this.url;
    }

    public final String topPrivateDomain() {
        if (Util.canParseAsIpAddress(this.host)) {
            return null;
        }
        return PublicSuffixDatabase.INSTANCE.get().getEffectiveTldPlusOne(this.host);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to toUrl()", replaceWith = @ReplaceWith(expression = "toUrl()", imports = {}))
    /* renamed from: -deprecated_url, reason: not valid java name */
    public final URL m1418deprecated_url() {
        return url();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to toUri()", replaceWith = @ReplaceWith(expression = "toUri()", imports = {}))
    /* renamed from: -deprecated_uri, reason: not valid java name */
    public final URI m1417deprecated_uri() {
        return uri();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "scheme", imports = {}))
    /* renamed from: -deprecated_scheme, reason: not valid java name and from getter */
    public final String getScheme() {
        return this.scheme;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedUsername", imports = {}))
    /* renamed from: -deprecated_encodedUsername, reason: not valid java name */
    public final String m1406deprecated_encodedUsername() {
        return encodedUsername();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "username", imports = {}))
    /* renamed from: -deprecated_username, reason: not valid java name and from getter */
    public final String getUsername() {
        return this.username;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPassword", imports = {}))
    /* renamed from: -deprecated_encodedPassword, reason: not valid java name */
    public final String m1402deprecated_encodedPassword() {
        return encodedPassword();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "password", imports = {}))
    /* renamed from: -deprecated_password, reason: not valid java name and from getter */
    public final String getPassword() {
        return this.password;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "host", imports = {}))
    /* renamed from: -deprecated_host, reason: not valid java name and from getter */
    public final String getHost() {
        return this.host;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "port", imports = {}))
    /* renamed from: -deprecated_port, reason: not valid java name and from getter */
    public final int getPort() {
        return this.port;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSize", imports = {}))
    /* renamed from: -deprecated_pathSize, reason: not valid java name */
    public final int m1411deprecated_pathSize() {
        return pathSize();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPath", imports = {}))
    /* renamed from: -deprecated_encodedPath, reason: not valid java name */
    public final String m1403deprecated_encodedPath() {
        return encodedPath();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPathSegments", imports = {}))
    /* renamed from: -deprecated_encodedPathSegments, reason: not valid java name */
    public final List<String> m1404deprecated_encodedPathSegments() {
        return encodedPathSegments();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSegments", imports = {}))
    /* renamed from: -deprecated_pathSegments, reason: not valid java name */
    public final List<String> m1410deprecated_pathSegments() {
        return this.pathSegments;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedQuery", imports = {}))
    /* renamed from: -deprecated_encodedQuery, reason: not valid java name */
    public final String m1405deprecated_encodedQuery() {
        return encodedQuery();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = SearchIntents.EXTRA_QUERY, imports = {}))
    /* renamed from: -deprecated_query, reason: not valid java name */
    public final String m1413deprecated_query() {
        return query();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "querySize", imports = {}))
    /* renamed from: -deprecated_querySize, reason: not valid java name */
    public final int m1415deprecated_querySize() {
        return querySize();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "queryParameterNames", imports = {}))
    /* renamed from: -deprecated_queryParameterNames, reason: not valid java name */
    public final Set<String> m1414deprecated_queryParameterNames() {
        return queryParameterNames();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedFragment", imports = {}))
    /* renamed from: -deprecated_encodedFragment, reason: not valid java name */
    public final String m1401deprecated_encodedFragment() {
        return encodedFragment();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "fragment", imports = {}))
    /* renamed from: -deprecated_fragment, reason: not valid java name and from getter */
    public final String getFragment() {
        return this.fragment;
    }

    /* compiled from: HttpUrl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0017\u0018\u0000 V2\u00020\u0001:\u0001VB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0004J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004J\u0018\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0004J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u0004J\u0018\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00042\b\u00101\u001a\u0004\u0018\u00010\u0004J\u0006\u00102\u001a\u000203J\b\u00104\u001a\u00020\u001bH\u0002J\u0010\u0010\u0003\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u00105\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u0004J\u0010\u00106\u001a\u00020\u00002\b\u00106\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u00107\u001a\u00020\u00002\b\u00107\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0004J\u0010\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u0004H\u0002J\u0010\u0010:\u001a\u00020.2\u0006\u00109\u001a\u00020\u0004H\u0002J\u001f\u0010;\u001a\u00020\u00002\b\u0010<\u001a\u0004\u0018\u0001032\u0006\u00109\u001a\u00020\u0004H\u0000¢\u0006\u0002\b=J\u000e\u0010>\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u0004J\b\u0010?\u001a\u00020@H\u0002J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ0\u0010A\u001a\u00020@2\u0006\u00109\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020.2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010E\u001a\u00020\u00002\b\u0010E\u001a\u0004\u0018\u00010\u0004J\r\u0010F\u001a\u00020\u0000H\u0000¢\u0006\u0002\bGJ\u0010\u0010H\u001a\u00020@2\u0006\u0010I\u001a\u00020\u0004H\u0002J\u000e\u0010J\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0004J\u000e\u0010K\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0004J\u000e\u0010L\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u001bJ \u0010N\u001a\u00020@2\u0006\u00109\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001bH\u0002J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0004J\u0016\u0010P\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u0004J\u0018\u0010Q\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u0016\u0010R\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u0004J\u0018\u0010S\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00042\b\u00101\u001a\u0004\u0018\u00010\u0004J\b\u0010T\u001a\u00020\u0004H\u0016J\u000e\u0010U\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000f\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\u001a\u0010\u001a\u001a\u00020\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\b¨\u0006W"}, d2 = {"Lokhttp3/HttpUrl$Builder;", "", "()V", "encodedFragment", "", "getEncodedFragment$okhttp", "()Ljava/lang/String;", "setEncodedFragment$okhttp", "(Ljava/lang/String;)V", "encodedPassword", "getEncodedPassword$okhttp", "setEncodedPassword$okhttp", "encodedPathSegments", "", "getEncodedPathSegments$okhttp", "()Ljava/util/List;", "encodedQueryNamesAndValues", "getEncodedQueryNamesAndValues$okhttp", "setEncodedQueryNamesAndValues$okhttp", "(Ljava/util/List;)V", "encodedUsername", "getEncodedUsername$okhttp", "setEncodedUsername$okhttp", "host", "getHost$okhttp", "setHost$okhttp", "port", "", "getPort$okhttp", "()I", "setPort$okhttp", "(I)V", "scheme", "getScheme$okhttp", "setScheme$okhttp", "addEncodedPathSegment", "encodedPathSegment", "addEncodedPathSegments", "addEncodedQueryParameter", "encodedName", "encodedValue", "addPathSegment", "pathSegment", "addPathSegments", "pathSegments", "alreadyEncoded", "", "addQueryParameter", "name", "value", "build", "Lokhttp3/HttpUrl;", "effectivePort", "encodedPath", "encodedQuery", "fragment", "isDot", "input", "isDotDot", "parse", "base", "parse$okhttp", "password", "pop", "", "push", "pos", "limit", "addTrailingSlash", SearchIntents.EXTRA_QUERY, "reencodeForUri", "reencodeForUri$okhttp", "removeAllCanonicalQueryParameters", "canonicalName", "removeAllEncodedQueryParameters", "removeAllQueryParameters", "removePathSegment", "index", "resolvePath", "startPos", "setEncodedPathSegment", "setEncodedQueryParameter", "setPathSegment", "setQueryParameter", "toString", "username", "Companion", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\okhttp3\HttpUrl$Builder.smali */
    public static final class Builder {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final String INVALID_HOST = "Invalid URL host";
        private String encodedFragment;
        private final List<String> encodedPathSegments;
        private List<String> encodedQueryNamesAndValues;
        private String host;
        private String scheme;
        private String encodedUsername = "";
        private String encodedPassword = "";
        private int port = -1;

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this.encodedPathSegments = arrayList;
            arrayList.add("");
        }

        /* renamed from: getScheme$okhttp, reason: from getter */
        public final String getScheme() {
            return this.scheme;
        }

        public final void setScheme$okhttp(String str) {
            this.scheme = str;
        }

        /* renamed from: getEncodedUsername$okhttp, reason: from getter */
        public final String getEncodedUsername() {
            return this.encodedUsername;
        }

        public final void setEncodedUsername$okhttp(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.encodedUsername = str;
        }

        /* renamed from: getEncodedPassword$okhttp, reason: from getter */
        public final String getEncodedPassword() {
            return this.encodedPassword;
        }

        public final void setEncodedPassword$okhttp(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.encodedPassword = str;
        }

        /* renamed from: getHost$okhttp, reason: from getter */
        public final String getHost() {
            return this.host;
        }

        public final void setHost$okhttp(String str) {
            this.host = str;
        }

        /* renamed from: getPort$okhttp, reason: from getter */
        public final int getPort() {
            return this.port;
        }

        public final void setPort$okhttp(int i) {
            this.port = i;
        }

        public final List<String> getEncodedPathSegments$okhttp() {
            return this.encodedPathSegments;
        }

        public final List<String> getEncodedQueryNamesAndValues$okhttp() {
            return this.encodedQueryNamesAndValues;
        }

        public final void setEncodedQueryNamesAndValues$okhttp(List<String> list) {
            this.encodedQueryNamesAndValues = list;
        }

        /* renamed from: getEncodedFragment$okhttp, reason: from getter */
        public final String getEncodedFragment() {
            return this.encodedFragment;
        }

        public final void setEncodedFragment$okhttp(String str) {
            this.encodedFragment = str;
        }

        public final Builder scheme(String scheme) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            Builder $this$apply = this;
            if (StringsKt.equals(scheme, "http", true)) {
                $this$apply.scheme = "http";
            } else if (StringsKt.equals(scheme, "https", true)) {
                $this$apply.scheme = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + scheme);
            }
            return this;
        }

        public final Builder username(String username) {
            Intrinsics.checkNotNullParameter(username, "username");
            Builder $this$apply = this;
            $this$apply.encodedUsername = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, username, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }

        public final Builder encodedUsername(String encodedUsername) {
            Intrinsics.checkNotNullParameter(encodedUsername, "encodedUsername");
            Builder $this$apply = this;
            $this$apply.encodedUsername = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedUsername, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null);
            return this;
        }

        public final Builder password(String password) {
            Intrinsics.checkNotNullParameter(password, "password");
            Builder $this$apply = this;
            $this$apply.encodedPassword = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, password, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }

        public final Builder encodedPassword(String encodedPassword) {
            Intrinsics.checkNotNullParameter(encodedPassword, "encodedPassword");
            Builder $this$apply = this;
            $this$apply.encodedPassword = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedPassword, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null);
            return this;
        }

        public final Builder host(String host) {
            Intrinsics.checkNotNullParameter(host, "host");
            Builder $this$apply = this;
            String encoded = HostnamesKt.toCanonicalHost(Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, host, 0, 0, false, 7, null));
            if (encoded == null) {
                throw new IllegalArgumentException("unexpected host: " + host);
            }
            $this$apply.host = encoded;
            return this;
        }

        public final Builder port(int port) {
            Builder $this$apply = this;
            if (!(1 <= port && 65535 >= port)) {
                throw new IllegalArgumentException(("unexpected port: " + port).toString());
            }
            $this$apply.port = port;
            return this;
        }

        private final int effectivePort() {
            int i = this.port;
            if (i != -1) {
                return i;
            }
            Companion companion = HttpUrl.INSTANCE;
            String str = this.scheme;
            Intrinsics.checkNotNull(str);
            return companion.defaultPort(str);
        }

        public final Builder addPathSegment(String pathSegment) {
            Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
            Builder $this$apply = this;
            $this$apply.push(pathSegment, 0, pathSegment.length(), false, false);
            return this;
        }

        public final Builder addPathSegments(String pathSegments) {
            Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
            return addPathSegments(pathSegments, false);
        }

        public final Builder addEncodedPathSegment(String encodedPathSegment) {
            Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            Builder $this$apply = this;
            $this$apply.push(encodedPathSegment, 0, encodedPathSegment.length(), false, true);
            return this;
        }

        public final Builder addEncodedPathSegments(String encodedPathSegments) {
            Intrinsics.checkNotNullParameter(encodedPathSegments, "encodedPathSegments");
            return addPathSegments(encodedPathSegments, true);
        }

        private final Builder addPathSegments(String pathSegments, boolean alreadyEncoded) {
            Builder $this$apply = this;
            int offset = 0;
            do {
                int segmentEnd = Util.delimiterOffset(pathSegments, "/\\", offset, pathSegments.length());
                boolean addTrailingSlash = segmentEnd < pathSegments.length();
                $this$apply.push(pathSegments, offset, segmentEnd, addTrailingSlash, alreadyEncoded);
                offset = segmentEnd + 1;
            } while (offset <= pathSegments.length());
            return this;
        }

        public final Builder setPathSegment(int index, String pathSegment) {
            Intrinsics.checkNotNullParameter(pathSegment, "pathSegment");
            Builder $this$apply = this;
            String canonicalPathSegment = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, pathSegment, 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET, false, false, false, false, null, 251, null);
            if (!(($this$apply.isDot(canonicalPathSegment) || $this$apply.isDotDot(canonicalPathSegment)) ? false : true)) {
                throw new IllegalArgumentException(("unexpected path segment: " + pathSegment).toString());
            }
            $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
            return this;
        }

        public final Builder setEncodedPathSegment(int index, String encodedPathSegment) {
            Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            Builder $this$apply = this;
            String canonicalPathSegment = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedPathSegment, 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET, true, false, false, false, null, 243, null);
            $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
            if (($this$apply.isDot(canonicalPathSegment) || $this$apply.isDotDot(canonicalPathSegment)) ? false : true) {
                return this;
            }
            throw new IllegalArgumentException(("unexpected path segment: " + encodedPathSegment).toString());
        }

        public final Builder removePathSegment(int index) {
            Builder $this$apply = this;
            $this$apply.encodedPathSegments.remove(index);
            if ($this$apply.encodedPathSegments.isEmpty()) {
                $this$apply.encodedPathSegments.add("");
            }
            return this;
        }

        public final Builder encodedPath(String encodedPath) {
            Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
            Builder $this$apply = this;
            if (!StringsKt.startsWith$default(encodedPath, "/", false, 2, (Object) null)) {
                throw new IllegalArgumentException(("unexpected encodedPath: " + encodedPath).toString());
            }
            $this$apply.resolvePath(encodedPath, 0, encodedPath.length());
            return this;
        }

        public final Builder query(String query) {
            String strCanonicalize$okhttp$default;
            Builder $this$apply = this;
            $this$apply.encodedQueryNamesAndValues = (query == null || (strCanonicalize$okhttp$default = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, query, 0, 0, HttpUrl.QUERY_ENCODE_SET, false, false, true, false, null, 219, null)) == null) ? null : HttpUrl.INSTANCE.toQueryNamesAndValues$okhttp(strCanonicalize$okhttp$default);
            return this;
        }

        public final Builder encodedQuery(String encodedQuery) {
            String strCanonicalize$okhttp$default;
            Builder $this$apply = this;
            $this$apply.encodedQueryNamesAndValues = (encodedQuery == null || (strCanonicalize$okhttp$default = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedQuery, 0, 0, HttpUrl.QUERY_ENCODE_SET, true, false, true, false, null, 211, null)) == null) ? null : HttpUrl.INSTANCE.toQueryNamesAndValues$okhttp(strCanonicalize$okhttp$default);
            return this;
        }

        public final Builder addQueryParameter(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                $this$apply.encodedQueryNamesAndValues = new ArrayList();
            }
            List<String> list = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            list.add(Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, name, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null));
            List<String> list2 = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list2);
            list2.add(value != null ? Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, value, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null) : null);
            return this;
        }

        public final Builder addEncodedQueryParameter(String encodedName, String encodedValue) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                $this$apply.encodedQueryNamesAndValues = new ArrayList();
            }
            List<String> list = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            list.add(Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedName, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            List<String> list2 = $this$apply.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list2);
            list2.add(encodedValue != null ? Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedValue, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null) : null);
            return this;
        }

        public final Builder setQueryParameter(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            $this$apply.removeAllQueryParameters(name);
            $this$apply.addQueryParameter(name, value);
            return this;
        }

        public final Builder setEncodedQueryParameter(String encodedName, String encodedValue) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder $this$apply = this;
            $this$apply.removeAllEncodedQueryParameters(encodedName);
            $this$apply.addEncodedQueryParameter(encodedName, encodedValue);
            return this;
        }

        public final Builder removeAllQueryParameters(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                return $this$apply;
            }
            String nameToRemove = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, name, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null);
            $this$apply.removeAllCanonicalQueryParameters(nameToRemove);
            return this;
        }

        public final Builder removeAllEncodedQueryParameters(String encodedName) {
            Intrinsics.checkNotNullParameter(encodedName, "encodedName");
            Builder $this$apply = this;
            if ($this$apply.encodedQueryNamesAndValues == null) {
                return $this$apply;
            }
            $this$apply.removeAllCanonicalQueryParameters(Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedName, 0, 0, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            return this;
        }

        private final void removeAllCanonicalQueryParameters(String canonicalName) {
            List<String> list = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list);
            IntProgression intProgressionStep = RangesKt.step(RangesKt.downTo(list.size() - 2, 0), 2);
            int i = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (i > last) {
                    return;
                }
            } else if (i < last) {
                return;
            }
            while (true) {
                List<String> list2 = this.encodedQueryNamesAndValues;
                Intrinsics.checkNotNull(list2);
                if (Intrinsics.areEqual(canonicalName, list2.get(i))) {
                    List<String> list3 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list3);
                    list3.remove(i + 1);
                    List<String> list4 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list4);
                    list4.remove(i);
                    List<String> list5 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list5);
                    if (list5.isEmpty()) {
                        this.encodedQueryNamesAndValues = (List) null;
                        return;
                    }
                }
                if (i == last) {
                    return;
                } else {
                    i += step;
                }
            }
        }

        public final Builder fragment(String fragment) {
            Builder $this$apply = this;
            $this$apply.encodedFragment = fragment != null ? Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, fragment, 0, 0, "", false, false, false, true, null, 187, null) : null;
            return this;
        }

        public final Builder encodedFragment(String encodedFragment) {
            Builder $this$apply = this;
            $this$apply.encodedFragment = encodedFragment != null ? Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, encodedFragment, 0, 0, "", true, false, false, true, null, 179, null) : null;
            return this;
        }

        public final Builder reencodeForUri$okhttp() {
            Builder $this$apply = this;
            String str = $this$apply.host;
            $this$apply.host = str != null ? new Regex("[\"<>^`{|}]").replace(str, "") : null;
            int size = $this$apply.encodedPathSegments.size();
            for (int i = 0; i < size; i++) {
                $this$apply.encodedPathSegments.set(i, Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, $this$apply.encodedPathSegments.get(i), 0, 0, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, true, true, false, false, null, 227, null));
            }
            List encodedQueryNamesAndValues = $this$apply.encodedQueryNamesAndValues;
            if (encodedQueryNamesAndValues != null) {
                int size2 = encodedQueryNamesAndValues.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str2 = encodedQueryNamesAndValues.get(i2);
                    encodedQueryNamesAndValues.set(i2, str2 != null ? Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, str2, 0, 0, HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, false, null, 195, null) : null);
                }
            }
            String str3 = $this$apply.encodedFragment;
            $this$apply.encodedFragment = str3 != null ? Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, str3, 0, 0, HttpUrl.FRAGMENT_ENCODE_SET_URI, true, true, false, true, null, 163, null) : null;
            return this;
        }

        public final HttpUrl build() {
            ArrayList arrayList;
            String str = this.scheme;
            if (str != null) {
                String strPercentDecode$okhttp$default = Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, this.encodedUsername, 0, 0, false, 7, null);
                String strPercentDecode$okhttp$default2 = Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, this.encodedPassword, 0, 0, false, 7, null);
                String str2 = this.host;
                if (str2 == null) {
                    throw new IllegalStateException("host == null");
                }
                int iEffectivePort = effectivePort();
                Iterable $this$map$iv = this.encodedPathSegments;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    destination$iv$iv.add(Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, (String) item$iv$iv, 0, 0, false, 7, null));
                }
                ArrayList arrayList2 = (List) destination$iv$iv;
                Iterable iterable = this.encodedQueryNamesAndValues;
                if (iterable != null) {
                    Iterable $this$map$iv2 = iterable;
                    Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                    for (Object item$iv$iv2 : $this$map$iv2) {
                        String it = (String) item$iv$iv2;
                        destination$iv$iv2.add(it != null ? Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, it, 0, 0, true, 3, null) : null);
                    }
                    arrayList = (List) destination$iv$iv2;
                } else {
                    arrayList = null;
                }
                String str3 = this.encodedFragment;
                return new HttpUrl(str, strPercentDecode$okhttp$default, strPercentDecode$okhttp$default2, str2, iEffectivePort, arrayList2, arrayList, str3 != null ? Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, str3, 0, 0, false, 7, null) : null, toString());
            }
            throw new IllegalStateException("scheme == null");
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00a1  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                r8 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r1 = r0
                r2 = 0
                java.lang.String r3 = r8.scheme
                if (r3 == 0) goto L14
                r1.append(r3)
                java.lang.String r3 = "://"
                r1.append(r3)
                goto L19
            L14:
                java.lang.String r3 = "//"
                r1.append(r3)
            L19:
                java.lang.String r3 = r8.encodedUsername
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                int r3 = r3.length()
                r4 = 1
                r5 = 0
                if (r3 <= 0) goto L28
                r3 = r4
                goto L29
            L28:
                r3 = r5
            L29:
                r6 = 58
                if (r3 != 0) goto L3c
                java.lang.String r3 = r8.encodedPassword
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                int r3 = r3.length()
                if (r3 <= 0) goto L39
                r3 = r4
                goto L3a
            L39:
                r3 = r5
            L3a:
                if (r3 == 0) goto L5c
            L3c:
                java.lang.String r3 = r8.encodedUsername
                r1.append(r3)
                java.lang.String r3 = r8.encodedPassword
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                int r3 = r3.length()
                if (r3 <= 0) goto L4c
                goto L4d
            L4c:
                r4 = r5
            L4d:
                if (r4 == 0) goto L57
                r1.append(r6)
                java.lang.String r3 = r8.encodedPassword
                r1.append(r3)
            L57:
                r3 = 64
                r1.append(r3)
            L5c:
                java.lang.String r3 = r8.host
                if (r3 == 0) goto L82
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r4 = 2
                r7 = 0
                boolean r3 = kotlin.text.StringsKt.contains$default(r3, r6, r5, r4, r7)
                if (r3 == 0) goto L7d
                r3 = 91
                r1.append(r3)
                java.lang.String r3 = r8.host
                r1.append(r3)
                r3 = 93
                r1.append(r3)
                goto L82
            L7d:
                java.lang.String r3 = r8.host
                r1.append(r3)
            L82:
                int r3 = r8.port
                r4 = -1
                if (r3 != r4) goto L8c
                java.lang.String r3 = r8.scheme
                if (r3 == 0) goto La7
            L8c:
                int r3 = r8.effectivePort()
                java.lang.String r4 = r8.scheme
                if (r4 == 0) goto La1
                okhttp3.HttpUrl$Companion r4 = okhttp3.HttpUrl.INSTANCE
                java.lang.String r5 = r8.scheme
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                int r4 = r4.defaultPort(r5)
                if (r3 == r4) goto La7
            La1:
                r1.append(r6)
                r1.append(r3)
            La7:
                okhttp3.HttpUrl$Companion r3 = okhttp3.HttpUrl.INSTANCE
                java.util.List<java.lang.String> r4 = r8.encodedPathSegments
                r3.toPathString$okhttp(r4, r1)
                java.util.List<java.lang.String> r3 = r8.encodedQueryNamesAndValues
                if (r3 == 0) goto Lc1
                r3 = 63
                r1.append(r3)
                okhttp3.HttpUrl$Companion r3 = okhttp3.HttpUrl.INSTANCE
                java.util.List<java.lang.String> r4 = r8.encodedQueryNamesAndValues
                kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                r3.toQueryString$okhttp(r4, r1)
            Lc1:
                java.lang.String r3 = r8.encodedFragment
                if (r3 == 0) goto Lcf
                r3 = 35
                r1.append(r3)
                java.lang.String r3 = r8.encodedFragment
                r1.append(r3)
            Lcf:
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "StringBuilder().apply(builderAction).toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.toString():java.lang.String");
        }

        public final Builder parse$okhttp(HttpUrl base, String input) {
            int componentDelimiterOffset;
            int iCharAt;
            String str;
            int slashCount;
            boolean z;
            String str2;
            int schemeDelimiterOffset;
            int componentDelimiterOffset2;
            String str3;
            int pos;
            Intrinsics.checkNotNullParameter(input, "input");
            int pos2 = Util.indexOfFirstNonAsciiWhitespace$default(input, 0, 0, 3, null);
            int limit = Util.indexOfLastNonAsciiWhitespace$default(input, pos2, 0, 2, null);
            Companion companion = INSTANCE;
            int schemeDelimiterOffset2 = companion.schemeDelimiterOffset(input, pos2, limit);
            String str4 = "(this as java.lang.Strin…ing(startIndex, endIndex)";
            int i = -1;
            boolean z2 = true;
            if (schemeDelimiterOffset2 != -1) {
                if (StringsKt.startsWith(input, "https:", pos2, true)) {
                    this.scheme = "https";
                    pos2 += "https:".length();
                } else if (StringsKt.startsWith(input, "http:", pos2, true)) {
                    this.scheme = "http";
                    pos2 += "http:".length();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Expected URL scheme 'http' or 'https' but was '");
                    String strSubstring = input.substring(0, schemeDelimiterOffset2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    sb.append(strSubstring);
                    sb.append("'");
                    throw new IllegalArgumentException(sb.toString());
                }
            } else if (base != null) {
                this.scheme = base.scheme();
            } else {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
            }
            int slashCount2 = companion.slashCount(input, pos2, limit);
            int i2 = 63;
            int i3 = 35;
            if (slashCount2 >= 2 || base == null || (!Intrinsics.areEqual(base.scheme(), this.scheme))) {
                boolean hasUsername = false;
                boolean hasPassword = false;
                int pos3 = pos2 + slashCount2;
                while (true) {
                    componentDelimiterOffset = Util.delimiterOffset(input, "@/\\?#", pos3, limit);
                    if (componentDelimiterOffset != limit) {
                        iCharAt = input.charAt(componentDelimiterOffset);
                    } else {
                        iCharAt = i;
                    }
                    int c = iCharAt;
                    if (c == i || c == i3 || c == 47 || c == 92 || c == i2) {
                        break;
                    }
                    if (c != 64) {
                        slashCount = slashCount2;
                        z = z2;
                        str2 = str4;
                        schemeDelimiterOffset = schemeDelimiterOffset2;
                    } else {
                        if (!hasPassword) {
                            int passwordColonOffset = Util.delimiterOffset(input, ':', pos3, componentDelimiterOffset);
                            slashCount = slashCount2;
                            z = z2;
                            str2 = str4;
                            schemeDelimiterOffset = schemeDelimiterOffset2;
                            String canonicalUsername = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, input, pos3, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                            if (hasUsername) {
                                str3 = this.encodedUsername + "%40" + canonicalUsername;
                            } else {
                                str3 = canonicalUsername;
                            }
                            this.encodedUsername = str3;
                            componentDelimiterOffset2 = componentDelimiterOffset;
                            if (passwordColonOffset != componentDelimiterOffset2) {
                                hasPassword = true;
                                this.encodedPassword = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, input, passwordColonOffset + 1, componentDelimiterOffset2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                            }
                            hasUsername = true;
                        } else {
                            slashCount = slashCount2;
                            z = z2;
                            str2 = str4;
                            schemeDelimiterOffset = schemeDelimiterOffset2;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(this.encodedPassword);
                            sb2.append("%40");
                            componentDelimiterOffset2 = componentDelimiterOffset;
                            sb2.append(Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, input, pos3, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null));
                            this.encodedPassword = sb2.toString();
                        }
                        pos3 = componentDelimiterOffset2 + 1;
                    }
                    slashCount2 = slashCount;
                    z2 = z;
                    schemeDelimiterOffset2 = schemeDelimiterOffset;
                    str4 = str2;
                    i3 = 35;
                    i2 = 63;
                    i = -1;
                }
                int pos4 = pos3;
                boolean z3 = z2;
                String str5 = str4;
                Companion companion2 = INSTANCE;
                int portColonOffset = companion2.portColonOffset(input, pos4, componentDelimiterOffset);
                if (portColonOffset + 1 < componentDelimiterOffset) {
                    this.host = HostnamesKt.toCanonicalHost(Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, input, pos4, portColonOffset, false, 4, null));
                    int port = companion2.parsePort(input, portColonOffset + 1, componentDelimiterOffset);
                    this.port = port;
                    if (!(port != -1 ? z3 : false)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Invalid URL port: \"");
                        String strSubstring2 = input.substring(portColonOffset + 1, componentDelimiterOffset);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, str5);
                        sb3.append(strSubstring2);
                        sb3.append(Typography.quote);
                        throw new IllegalArgumentException(sb3.toString().toString());
                    }
                    str = str5;
                } else {
                    str = str5;
                    this.host = HostnamesKt.toCanonicalHost(Companion.percentDecode$okhttp$default(HttpUrl.INSTANCE, input, pos4, portColonOffset, false, 4, null));
                    Companion companion3 = HttpUrl.INSTANCE;
                    String str6 = this.scheme;
                    Intrinsics.checkNotNull(str6);
                    this.port = companion3.defaultPort(str6);
                }
                if (!(this.host != null ? z3 : false)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Invalid URL host: \"");
                    String strSubstring3 = input.substring(pos4, portColonOffset);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, str);
                    sb4.append(strSubstring3);
                    sb4.append(Typography.quote);
                    throw new IllegalArgumentException(sb4.toString().toString());
                }
                pos2 = componentDelimiterOffset;
            } else {
                this.encodedUsername = base.encodedUsername();
                this.encodedPassword = base.encodedPassword();
                this.host = base.host();
                this.port = base.port();
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(base.encodedPathSegments());
                if (pos2 == limit || input.charAt(pos2) == '#') {
                    encodedQuery(base.encodedQuery());
                }
            }
            int pathDelimiterOffset = Util.delimiterOffset(input, "?#", pos2, limit);
            resolvePath(input, pos2, pathDelimiterOffset);
            if (pathDelimiterOffset < limit && input.charAt(pathDelimiterOffset) == '?') {
                int queryDelimiterOffset = Util.delimiterOffset(input, '#', pathDelimiterOffset, limit);
                this.encodedQueryNamesAndValues = HttpUrl.INSTANCE.toQueryNamesAndValues$okhttp(Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, input, pathDelimiterOffset + 1, queryDelimiterOffset, HttpUrl.QUERY_ENCODE_SET, true, false, true, false, null, 208, null));
                pos = queryDelimiterOffset;
            } else {
                pos = pathDelimiterOffset;
            }
            if (pos < limit && input.charAt(pos) == '#') {
                this.encodedFragment = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, input, pos + 1, limit, "", true, false, false, true, null, 176, null);
            }
            return this;
        }

        private final void resolvePath(String input, int startPos, int limit) {
            int pos = startPos;
            if (pos == limit) {
                return;
            }
            char c = input.charAt(pos);
            if (c == '/' || c == '\\') {
                this.encodedPathSegments.clear();
                this.encodedPathSegments.add("");
                pos++;
            } else {
                List<String> list = this.encodedPathSegments;
                list.set(list.size() - 1, "");
            }
            int i = pos;
            while (i < limit) {
                int pathSegmentDelimiterOffset = Util.delimiterOffset(input, "/\\", i, limit);
                boolean segmentHasTrailingSlash = pathSegmentDelimiterOffset < limit;
                push(input, i, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
                i = pathSegmentDelimiterOffset;
                if (segmentHasTrailingSlash) {
                    i++;
                }
            }
        }

        private final void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
            String segment = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, input, pos, limit, HttpUrl.PATH_SEGMENT_ENCODE_SET, alreadyEncoded, false, false, false, null, 240, null);
            if (isDot(segment)) {
                return;
            }
            if (isDotDot(segment)) {
                pop();
                return;
            }
            List<String> list = this.encodedPathSegments;
            if (list.get(list.size() - 1).length() == 0) {
                List<String> list2 = this.encodedPathSegments;
                list2.set(list2.size() - 1, segment);
            } else {
                this.encodedPathSegments.add(segment);
            }
            if (addTrailingSlash) {
                this.encodedPathSegments.add("");
            }
        }

        private final boolean isDot(String input) {
            return Intrinsics.areEqual(input, ".") || StringsKt.equals(input, "%2e", true);
        }

        private final boolean isDotDot(String input) {
            return Intrinsics.areEqual(input, "..") || StringsKt.equals(input, "%2e.", true) || StringsKt.equals(input, ".%2e", true) || StringsKt.equals(input, "%2e%2e", true);
        }

        private final void pop() {
            List<String> list = this.encodedPathSegments;
            String removed = list.remove(list.size() - 1);
            if ((removed.length() == 0) && (!this.encodedPathSegments.isEmpty())) {
                List<String> list2 = this.encodedPathSegments;
                list2.set(list2.size() - 1, "");
            } else {
                this.encodedPathSegments.add("");
            }
        }

        /* compiled from: HttpUrl.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u001c\u0010\f\u001a\u00020\u0006*\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/HttpUrl$Builder$Companion;", "", "()V", "INVALID_HOST", "", "parsePort", "", "input", "pos", "limit", "portColonOffset", "schemeDelimiterOffset", "slashCount", "okhttp"}, k = 1, mv = {1, 4, 0})
        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\okhttp3\HttpUrl$Builder$Companion.smali */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int schemeDelimiterOffset(String input, int pos, int limit) {
                if (limit - pos < 2) {
                    return -1;
                }
                char c0 = input.charAt(pos);
                if ((Intrinsics.compare((int) c0, 97) < 0 || Intrinsics.compare((int) c0, 122) > 0) && (Intrinsics.compare((int) c0, 65) < 0 || Intrinsics.compare((int) c0, 90) > 0)) {
                    return -1;
                }
                for (int i = pos + 1; i < limit; i++) {
                    char cCharAt = input.charAt(i);
                    if (('a' > cCharAt || 'z' < cCharAt) && (('A' > cCharAt || 'Z' < cCharAt) && (('0' > cCharAt || '9' < cCharAt) && cCharAt != '+' && cCharAt != '-' && cCharAt != '.'))) {
                        if (cCharAt == ':') {
                            return i;
                        }
                        return -1;
                    }
                }
                return -1;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int slashCount(String $this$slashCount, int pos, int limit) {
                int slashCount = 0;
                for (int i = pos; i < limit; i++) {
                    char c = $this$slashCount.charAt(i);
                    if (c != '\\' && c != '/') {
                        break;
                    }
                    slashCount++;
                }
                return slashCount;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int portColonOffset(String input, int pos, int limit) {
                int i = pos;
                while (i < limit) {
                    char cCharAt = input.charAt(i);
                    if (cCharAt == ':') {
                        return i;
                    }
                    if (cCharAt == '[') {
                        do {
                            i++;
                            if (i < limit) {
                            }
                        } while (input.charAt(i) != ']');
                    }
                    i++;
                }
                return limit;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int parsePort(String input, int pos, int limit) {
                try {
                    String portString = Companion.canonicalize$okhttp$default(HttpUrl.INSTANCE, input, pos, limit, "", false, false, false, false, null, 248, null);
                    int i = Integer.parseInt(portString);
                    if (1 <= i && 65535 >= i) {
                        return i;
                    }
                    return -1;
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }
    }

    /* compiled from: HttpUrl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\b\u0018J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0007¢\u0006\u0002\b\u0018J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0004H\u0007¢\u0006\u0002\b\u0018J\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u0004H\u0007¢\u0006\u0002\b\u001cJa\u0010\u001d\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0000¢\u0006\u0002\b(J\u001c\u0010)\u001a\u00020\"*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0002J/\u0010*\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\"H\u0000¢\u0006\u0002\b+J\u0011\u0010,\u001a\u00020\u0015*\u00020\u0004H\u0007¢\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u0017H\u0007¢\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u001aH\u0007¢\u0006\u0002\b\u0014J\u0013\u0010-\u001a\u0004\u0018\u00010\u0015*\u00020\u0004H\u0007¢\u0006\u0002\b\u001bJ#\u0010.\u001a\u00020/*\b\u0012\u0004\u0012\u00020\u0004002\n\u00101\u001a\u000602j\u0002`3H\u0000¢\u0006\u0002\b4J\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000406*\u00020\u0004H\u0000¢\u0006\u0002\b7J%\u00108\u001a\u00020/*\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004002\n\u00101\u001a\u000602j\u0002`3H\u0000¢\u0006\u0002\b9JV\u0010:\u001a\u00020/*\u00020;2\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\"2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J,\u0010=\u001a\u00020/*\u00020;2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lokhttp3/HttpUrl$Companion;", "", "()V", "FORM_ENCODE_SET", "", "FRAGMENT_ENCODE_SET", "FRAGMENT_ENCODE_SET_URI", "HEX_DIGITS", "", "PASSWORD_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET_URI", "QUERY_COMPONENT_ENCODE_SET", "QUERY_COMPONENT_ENCODE_SET_URI", "QUERY_COMPONENT_REENCODE_SET", "QUERY_ENCODE_SET", "USERNAME_ENCODE_SET", "defaultPort", "", "scheme", "get", "Lokhttp3/HttpUrl;", "uri", "Ljava/net/URI;", "-deprecated_get", ImagesContract.URL, "Ljava/net/URL;", "parse", "-deprecated_parse", "canonicalize", "pos", "limit", "encodeSet", "alreadyEncoded", "", "strict", "plusIsSpace", "unicodeAllowed", "charset", "Ljava/nio/charset/Charset;", "canonicalize$okhttp", "isPercentEncoded", "percentDecode", "percentDecode$okhttp", "toHttpUrl", "toHttpUrlOrNull", "toPathString", "", "", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toPathString$okhttp", "toQueryNamesAndValues", "", "toQueryNamesAndValues$okhttp", "toQueryString", "toQueryString$okhttp", "writeCanonicalized", "Lokio/Buffer;", "input", "writePercentDecoded", "encoded", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\okhttp3\HttpUrl$Companion.smali */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        public final int defaultPort(String scheme) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            int iHashCode = scheme.hashCode();
            if (iHashCode == 3213448) {
                if (scheme.equals("http")) {
                    return 80;
                }
            } else if (iHashCode == 99617003 && scheme.equals("https")) {
                return 443;
            }
            return -1;
        }

        public final void toPathString$okhttp(List<String> toPathString, StringBuilder out) {
            Intrinsics.checkNotNullParameter(toPathString, "$this$toPathString");
            Intrinsics.checkNotNullParameter(out, "out");
            int size = toPathString.size();
            for (int i = 0; i < size; i++) {
                out.append('/');
                out.append(toPathString.get(i));
            }
        }

        public final void toQueryString$okhttp(List<String> toQueryString, StringBuilder out) {
            Intrinsics.checkNotNullParameter(toQueryString, "$this$toQueryString");
            Intrinsics.checkNotNullParameter(out, "out");
            IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, toQueryString.size()), 2);
            int i = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (i > last) {
                    return;
                }
            } else if (i < last) {
                return;
            }
            while (true) {
                String name = toQueryString.get(i);
                String value = toQueryString.get(i + 1);
                if (i > 0) {
                    out.append(Typography.amp);
                }
                out.append(name);
                if (value != null) {
                    out.append('=');
                    out.append(value);
                }
                if (i == last) {
                    return;
                } else {
                    i += step;
                }
            }
        }

        public final List<String> toQueryNamesAndValues$okhttp(String toQueryNamesAndValues) {
            Intrinsics.checkNotNullParameter(toQueryNamesAndValues, "$this$toQueryNamesAndValues");
            List result = new ArrayList();
            int pos = 0;
            while (pos <= toQueryNamesAndValues.length()) {
                int ampersandOffset = StringsKt.indexOf$default((CharSequence) toQueryNamesAndValues, Typography.amp, pos, false, 4, (Object) null);
                if (ampersandOffset == -1) {
                    ampersandOffset = toQueryNamesAndValues.length();
                }
                int ampersandOffset2 = ampersandOffset;
                int equalsOffset = StringsKt.indexOf$default((CharSequence) toQueryNamesAndValues, '=', pos, false, 4, (Object) null);
                if (equalsOffset == -1 || equalsOffset > ampersandOffset2) {
                    String strSubstring = toQueryNamesAndValues.substring(pos, ampersandOffset2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    result.add(strSubstring);
                    result.add(null);
                } else {
                    String strSubstring2 = toQueryNamesAndValues.substring(pos, equalsOffset);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    result.add(strSubstring2);
                    String strSubstring3 = toQueryNamesAndValues.substring(equalsOffset + 1, ampersandOffset2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    result.add(strSubstring3);
                }
                pos = ampersandOffset2 + 1;
            }
            return result;
        }

        @JvmStatic
        public final HttpUrl get(String toHttpUrl) {
            Intrinsics.checkNotNullParameter(toHttpUrl, "$this$toHttpUrl");
            return new Builder().parse$okhttp(null, toHttpUrl).build();
        }

        @JvmStatic
        public final HttpUrl parse(String toHttpUrlOrNull) {
            Intrinsics.checkNotNullParameter(toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            try {
                return get(toHttpUrlOrNull);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        @JvmStatic
        public final HttpUrl get(URL toHttpUrlOrNull) {
            Intrinsics.checkNotNullParameter(toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            String string = toHttpUrlOrNull.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString()");
            return parse(string);
        }

        @JvmStatic
        public final HttpUrl get(URI toHttpUrlOrNull) {
            Intrinsics.checkNotNullParameter(toHttpUrlOrNull, "$this$toHttpUrlOrNull");
            String string = toHttpUrlOrNull.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString()");
            return parse(string);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrl()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrl"}))
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m1420deprecated_get(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return get(url);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        /* renamed from: -deprecated_parse, reason: not valid java name */
        public final HttpUrl m1423deprecated_parse(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return parse(url);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m1422deprecated_get(URL url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return get(url);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "uri.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        /* renamed from: -deprecated_get, reason: not valid java name */
        public final HttpUrl m1421deprecated_get(URI uri) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            return get(uri);
        }

        public static /* synthetic */ String percentDecode$okhttp$default(Companion companion, String str, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = str.length();
            }
            if ((i3 & 4) != 0) {
                z = false;
            }
            return companion.percentDecode$okhttp(str, i, i2, z);
        }

        public final String percentDecode$okhttp(String percentDecode, int pos, int limit, boolean plusIsSpace) {
            Intrinsics.checkNotNullParameter(percentDecode, "$this$percentDecode");
            for (int i = pos; i < limit; i++) {
                char c = percentDecode.charAt(i);
                if (c == '%' || (c == '+' && plusIsSpace)) {
                    Buffer out = new Buffer();
                    out.writeUtf8(percentDecode, pos, i);
                    writePercentDecoded(out, percentDecode, i, limit, plusIsSpace);
                    return out.readUtf8();
                }
            }
            String strSubstring = percentDecode.substring(pos, limit);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }

        private final void writePercentDecoded(Buffer $this$writePercentDecoded, String encoded, int pos, int limit, boolean plusIsSpace) {
            int i = pos;
            while (i < limit) {
                if (encoded == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                int codePoint = encoded.codePointAt(i);
                if (codePoint == 37 && i + 2 < limit) {
                    int d1 = Util.parseHexDigit(encoded.charAt(i + 1));
                    int d2 = Util.parseHexDigit(encoded.charAt(i + 2));
                    if (d1 != -1 && d2 != -1) {
                        $this$writePercentDecoded.writeByte((d1 << 4) + d2);
                        i = i + 2 + Character.charCount(codePoint);
                    } else {
                        $this$writePercentDecoded.writeUtf8CodePoint(codePoint);
                        i += Character.charCount(codePoint);
                    }
                } else if (codePoint == 43 && plusIsSpace) {
                    $this$writePercentDecoded.writeByte(32);
                    i++;
                } else {
                    $this$writePercentDecoded.writeUtf8CodePoint(codePoint);
                    i += Character.charCount(codePoint);
                }
            }
        }

        private final boolean isPercentEncoded(String $this$isPercentEncoded, int pos, int limit) {
            return pos + 2 < limit && $this$isPercentEncoded.charAt(pos) == '%' && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 1)) != -1 && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 2)) != -1;
        }

        public static /* synthetic */ String canonicalize$okhttp$default(Companion companion, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset, int i3, Object obj) {
            return companion.canonicalize$okhttp(str, (i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? str.length() : i2, str2, (i3 & 8) != 0 ? false : z, (i3 & 16) != 0 ? false : z2, (i3 & 32) != 0 ? false : z3, (i3 & 64) != 0 ? false : z4, (i3 & 128) != 0 ? (Charset) null : charset);
        }

        public final String canonicalize$okhttp(String canonicalize, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, Charset charset) {
            Intrinsics.checkNotNullParameter(canonicalize, "$this$canonicalize");
            Intrinsics.checkNotNullParameter(encodeSet, "encodeSet");
            int i = pos;
            while (i < limit) {
                int codePoint = canonicalize.codePointAt(i);
                if (codePoint < 32 || codePoint == 127 || ((codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default((CharSequence) encodeSet, (char) codePoint, false, 2, (Object) null) || ((codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded(canonicalize, i, limit)))) || (codePoint == 43 && plusIsSpace)))) {
                    Buffer out = new Buffer();
                    out.writeUtf8(canonicalize, pos, i);
                    writeCanonicalized(out, canonicalize, i, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, unicodeAllowed, charset);
                    return out.readUtf8();
                }
                i += Character.charCount(codePoint);
            }
            String strSubstring = canonicalize.substring(pos, limit);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }

        private final void writeCanonicalized(Buffer $this$writeCanonicalized, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, Charset charset) {
            Buffer encodedCharBuffer = (Buffer) null;
            int i = pos;
            while (i < limit) {
                if (input == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                int codePoint = input.codePointAt(i);
                if (!alreadyEncoded || (codePoint != 9 && codePoint != 10 && codePoint != 12 && codePoint != 13)) {
                    if (codePoint == 43 && plusIsSpace) {
                        $this$writeCanonicalized.writeUtf8(alreadyEncoded ? "+" : "%2B");
                    } else if (codePoint < 32 || codePoint == 127 || ((codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default((CharSequence) encodeSet, (char) codePoint, false, 2, (Object) null) || (codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded(input, i, limit)))))) {
                        if (encodedCharBuffer == null) {
                            encodedCharBuffer = new Buffer();
                        }
                        if (charset == null || Intrinsics.areEqual(charset, StandardCharsets.UTF_8)) {
                            encodedCharBuffer.writeUtf8CodePoint(codePoint);
                        } else {
                            encodedCharBuffer.writeString(input, i, Character.charCount(codePoint) + i, charset);
                        }
                        while (!encodedCharBuffer.exhausted()) {
                            int b = encodedCharBuffer.readByte() & UByte.MAX_VALUE;
                            $this$writeCanonicalized.writeByte(37);
                            $this$writeCanonicalized.writeByte((int) HttpUrl.HEX_DIGITS[(b >> 4) & 15]);
                            $this$writeCanonicalized.writeByte((int) HttpUrl.HEX_DIGITS[b & 15]);
                        }
                    } else {
                        $this$writeCanonicalized.writeUtf8CodePoint(codePoint);
                    }
                }
                i += Character.charCount(codePoint);
            }
        }
    }
}
