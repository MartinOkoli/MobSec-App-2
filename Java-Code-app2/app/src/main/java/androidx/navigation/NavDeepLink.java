package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavDeepLink.smali */
public final class NavDeepLink {
    private static final Pattern SCHEME_PATTERN = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");
    private final String mAction;
    private final ArrayList<String> mArguments;
    private boolean mExactDeepLink;
    private boolean mIsParameterizedQuery;
    private final String mMimeType;
    private Pattern mMimeTypePattern;
    private final Map<String, ParamQuery> mParamArgMap;
    private Pattern mPattern;
    private final String mUri;

    NavDeepLink(String uri, String action, String mimeType) {
        this.mArguments = new ArrayList<>();
        this.mParamArgMap = new HashMap();
        this.mPattern = null;
        this.mExactDeepLink = false;
        this.mIsParameterizedQuery = false;
        this.mMimeTypePattern = null;
        this.mUri = uri;
        this.mAction = action;
        this.mMimeType = mimeType;
        if (uri != null) {
            Uri parameterizedUri = Uri.parse(uri);
            int i = 1;
            this.mIsParameterizedQuery = parameterizedUri.getQuery() != null;
            StringBuilder uriRegex = new StringBuilder("^");
            if (!SCHEME_PATTERN.matcher(uri).find()) {
                uriRegex.append("http[s]?://");
            }
            Pattern fillInPattern = Pattern.compile("\\{(.+?)\\}");
            if (!this.mIsParameterizedQuery) {
                this.mExactDeepLink = buildPathRegex(uri, uriRegex, fillInPattern);
            } else {
                Matcher matcher = Pattern.compile("(\\?)").matcher(uri);
                if (matcher.find()) {
                    buildPathRegex(uri.substring(0, matcher.start()), uriRegex, fillInPattern);
                }
                this.mExactDeepLink = false;
                for (String paramName : parameterizedUri.getQueryParameterNames()) {
                    StringBuilder argRegex = new StringBuilder();
                    String queryParam = parameterizedUri.getQueryParameter(paramName);
                    Matcher matcher2 = fillInPattern.matcher(queryParam);
                    int appendPos = 0;
                    ParamQuery param = new ParamQuery();
                    while (matcher2.find()) {
                        param.addArgumentName(matcher2.group(i));
                        argRegex.append(Pattern.quote(queryParam.substring(appendPos, matcher2.start())));
                        argRegex.append("(.+?)?");
                        appendPos = matcher2.end();
                        i = 1;
                    }
                    if (appendPos < queryParam.length()) {
                        argRegex.append(Pattern.quote(queryParam.substring(appendPos)));
                    }
                    param.setParamRegex(argRegex.toString().replace(".*", "\\E.*\\Q"));
                    this.mParamArgMap.put(paramName, param);
                    i = 1;
                }
            }
            String finalRegex = uriRegex.toString().replace(".*", "\\E.*\\Q");
            this.mPattern = Pattern.compile(finalRegex, 2);
        }
        if (mimeType != null) {
            Pattern mimeTypePattern = Pattern.compile("^[\\s\\S]+/[\\s\\S]+$");
            Matcher mimeTypeMatcher = mimeTypePattern.matcher(mimeType);
            if (!mimeTypeMatcher.matches()) {
                throw new IllegalArgumentException("The given mimeType " + mimeType + " does not match to required \"type/subtype\" format");
            }
            MimeType splitMimeType = new MimeType(mimeType);
            String mimeTypeRegex = "^(" + splitMimeType.mType + "|[*]+)/(" + splitMimeType.mSubType + "|[*]+)$";
            String finalRegex2 = mimeTypeRegex.replace("*|[*]", "[\\s\\S]");
            this.mMimeTypePattern = Pattern.compile(finalRegex2);
        }
    }

    NavDeepLink(String uri) {
        this(uri, null, null);
    }

    private boolean buildPathRegex(String uri, StringBuilder uriRegex, Pattern fillInPattern) {
        Matcher matcher = fillInPattern.matcher(uri);
        int appendPos = 0;
        boolean exactDeepLink = !uri.contains(".*");
        while (matcher.find()) {
            String argName = matcher.group(1);
            this.mArguments.add(argName);
            uriRegex.append(Pattern.quote(uri.substring(appendPos, matcher.start())));
            uriRegex.append("(.+?)");
            appendPos = matcher.end();
            exactDeepLink = false;
        }
        if (appendPos < uri.length()) {
            uriRegex.append(Pattern.quote(uri.substring(appendPos)));
        }
        uriRegex.append("($|(\\?(.)*))");
        return exactDeepLink;
    }

    boolean matches(Uri uri) {
        return matches(new NavDeepLinkRequest(uri, null, null));
    }

    boolean matches(NavDeepLinkRequest deepLinkRequest) {
        if (matchUri(deepLinkRequest.getUri()) && matchAction(deepLinkRequest.getAction())) {
            return matchMimeType(deepLinkRequest.getMimeType());
        }
        return false;
    }

    private boolean matchUri(Uri uri) {
        boolean z = uri == null;
        Pattern pattern = this.mPattern;
        if (z == (pattern != null)) {
            return false;
        }
        return uri == null || pattern.matcher(uri.toString()).matches();
    }

    private boolean matchAction(String action) {
        boolean z = action == null;
        String str = this.mAction;
        if (z == (str != null)) {
            return false;
        }
        return action == null || str.equals(action);
    }

    private boolean matchMimeType(String mimeType) {
        if ((mimeType == null) == (this.mMimeType != null)) {
            return false;
        }
        return mimeType == null || this.mMimeTypePattern.matcher(mimeType).matches();
    }

    boolean isExactDeepLink() {
        return this.mExactDeepLink;
    }

    public String getUriPattern() {
        return this.mUri;
    }

    public String getAction() {
        return this.mAction;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    int getMimeTypeMatchRating(String mimeType) {
        if (this.mMimeType == null || !this.mMimeTypePattern.matcher(mimeType).matches()) {
            return -1;
        }
        return new MimeType(this.mMimeType).compareTo(new MimeType(mimeType));
    }

    Bundle getMatchingArguments(Uri deepLink, Map<String, NavArgument> arguments) {
        Bundle bundle;
        Map<String, NavArgument> map = arguments;
        Matcher matcher = this.mPattern.matcher(deepLink.toString());
        Bundle bundle2 = null;
        if (!matcher.matches()) {
            return null;
        }
        Bundle bundle3 = new Bundle();
        int size = this.mArguments.size();
        for (int index = 0; index < size; index++) {
            String argumentName = this.mArguments.get(index);
            String value = Uri.decode(matcher.group(index + 1));
            NavArgument argument = map.get(argumentName);
            if (parseArgument(bundle3, argumentName, value, argument)) {
                return null;
            }
        }
        if (this.mIsParameterizedQuery) {
            for (String paramName : this.mParamArgMap.keySet()) {
                Matcher argMatcher = null;
                ParamQuery storedParam = this.mParamArgMap.get(paramName);
                String inputParams = deepLink.getQueryParameter(paramName);
                if (inputParams != null) {
                    argMatcher = Pattern.compile(storedParam.getParamRegex()).matcher(inputParams);
                    if (!argMatcher.matches()) {
                        return bundle2;
                    }
                }
                int index2 = 0;
                while (index2 < storedParam.size()) {
                    String value2 = null;
                    if (argMatcher != null) {
                        value2 = Uri.decode(argMatcher.group(index2 + 1));
                    }
                    String argName = storedParam.getArgumentName(index2);
                    NavArgument argument2 = map.get(argName);
                    if (value2 == null) {
                        bundle = bundle2;
                    } else if (value2.replaceAll("[{}]", "").equals(argName)) {
                        bundle = null;
                    } else if (!parseArgument(bundle3, argName, value2, argument2)) {
                        bundle = null;
                    } else {
                        return null;
                    }
                    index2++;
                    bundle2 = bundle;
                    map = arguments;
                }
                map = arguments;
            }
        }
        return bundle3;
    }

    private boolean parseArgument(Bundle bundle, String name, String value, NavArgument argument) {
        if (argument != null) {
            NavType<?> type = argument.getType();
            try {
                type.parseAndPut(bundle, name, value);
                return false;
            } catch (IllegalArgumentException e) {
                return true;
            }
        }
        bundle.putString(name, value);
        return false;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavDeepLink$ParamQuery.smali */
    private static class ParamQuery {
        private ArrayList<String> mArguments = new ArrayList<>();
        private String mParamRegex;

        ParamQuery() {
        }

        void setParamRegex(String paramRegex) {
            this.mParamRegex = paramRegex;
        }

        String getParamRegex() {
            return this.mParamRegex;
        }

        void addArgumentName(String name) {
            this.mArguments.add(name);
        }

        String getArgumentName(int index) {
            return this.mArguments.get(index);
        }

        public int size() {
            return this.mArguments.size();
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavDeepLink$MimeType.smali */
    private static class MimeType implements Comparable<MimeType> {
        String mSubType;
        String mType;

        MimeType(String mimeType) {
            String[] typeAndSubType = mimeType.split("/", -1);
            this.mType = typeAndSubType[0];
            this.mSubType = typeAndSubType[1];
        }

        @Override // java.lang.Comparable
        public int compareTo(MimeType o) {
            int result = 0;
            if (this.mType.equals(o.mType)) {
                result = 0 + 2;
            }
            if (this.mSubType.equals(o.mSubType)) {
                return result + 1;
            }
            return result;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavDeepLink$Builder.smali */
    public static final class Builder {
        private String mAction;
        private String mMimeType;
        private String mUriPattern;

        Builder() {
        }

        public static Builder fromUriPattern(String uriPattern) {
            Builder builder = new Builder();
            builder.setUriPattern(uriPattern);
            return builder;
        }

        public static Builder fromAction(String action) {
            if (action.isEmpty()) {
                throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.");
            }
            Builder builder = new Builder();
            builder.setAction(action);
            return builder;
        }

        public static Builder fromMimeType(String mimeType) {
            Builder builder = new Builder();
            builder.setMimeType(mimeType);
            return builder;
        }

        public Builder setUriPattern(String uriPattern) {
            this.mUriPattern = uriPattern;
            return this;
        }

        public Builder setAction(String action) {
            if (action.isEmpty()) {
                throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.");
            }
            this.mAction = action;
            return this;
        }

        public Builder setMimeType(String mimeType) {
            this.mMimeType = mimeType;
            return this;
        }

        public NavDeepLink build() {
            return new NavDeepLink(this.mUriPattern, this.mAction, this.mMimeType);
        }
    }
}
