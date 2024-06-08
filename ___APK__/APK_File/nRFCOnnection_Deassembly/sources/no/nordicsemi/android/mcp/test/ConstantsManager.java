package no.nordicsemi.android.mcp.test;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ConstantsManager {
    public static final String CONSTANT_REGEX = "\\$\\{([^}]+)\\}";
    private final Pattern CONSTANT_PATTERN = Pattern.compile(CONSTANT_REGEX);
    private final Map<String, String> mGlobalConstants;
    private final Map<String, String> mLocalConstants;

    public ConstantsManager(Map<String, String> map, Map<String, String> map2) {
        this.mGlobalConstants = map;
        this.mLocalConstants = map2;
    }

    public String decode(String str) {
        Map<String, String> map;
        String str2;
        String str3;
        if (str == null) {
            return "";
        }
        Map<String, String> map2 = this.mLocalConstants;
        if ((map2 != null && !map2.isEmpty()) || ((map = this.mGlobalConstants) != null && !map.isEmpty())) {
            Matcher matcher = this.CONSTANT_PATTERN.matcher(str);
            while (matcher.find()) {
                Map<String, String> map3 = this.mLocalConstants;
                if (map3 != null && (str3 = map3.get(matcher.group(1))) != null) {
                    str = str.replaceAll("\\$\\{" + matcher.group(1) + "\\}", str3);
                } else {
                    Map<String, String> map4 = this.mGlobalConstants;
                    if (map4 != null && (str2 = map4.get(matcher.group(1))) != null) {
                        str = str.replaceAll("\\$\\{" + matcher.group(1) + "\\}", str2);
                    }
                }
            }
        }
        return str;
    }

    public boolean matches(String str) {
        return str != null && this.CONSTANT_PATTERN.matcher(str).find();
    }
}
