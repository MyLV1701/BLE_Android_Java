package no.nordicsemi.android.mcp.database.provider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ProjectionMap extends HashMap<String, String> {
    private static final long serialVersionUID = -4004367756025538190L;
    private String[] mColumns;

    /* loaded from: classes.dex */
    public static class Builder {
        private ProjectionMap mMap = new ProjectionMap();

        public Builder add(String str) {
            this.mMap.putColumn(str, str);
            return this;
        }

        public Builder addAll(String[] strArr) {
            for (String str : strArr) {
                add(str);
            }
            return this;
        }

        public ProjectionMap build() {
            String[] strArr = new String[this.mMap.size()];
            this.mMap.keySet().toArray(strArr);
            Arrays.sort(strArr);
            this.mMap.mColumns = strArr;
            return this.mMap;
        }

        public Builder add(String str, String str2) {
            this.mMap.putColumn(str, str2 + " AS " + str);
            return this;
        }

        public Builder addAll(ProjectionMap projectionMap) {
            for (Map.Entry<String, String> entry : projectionMap.entrySet()) {
                this.mMap.putColumn(entry.getKey(), entry.getValue());
            }
            return this;
        }
    }

    ProjectionMap() {
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putColumn(String str, String str2) {
        super.put((ProjectionMap) str, str2);
    }

    public String[] getColumnNames() {
        return this.mColumns;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends String, ? extends String> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public String put(String str, String str2) {
        throw new UnsupportedOperationException();
    }
}
