package no.nordicsemi.android.mcp.util;

import org.simpleframework.xml.core.PersistenceException;

/* loaded from: classes.dex */
public final class PhyHelper {
    public static final String LE_1M = "LE_1M";
    public static final String LE_2M = "LE_2M";
    public static final String LE_CODED = "LE_CODED";

    /* loaded from: classes.dex */
    public static class Phy {
        public int rx;
        public int tx;

        public Phy(int i, int i2) {
            this.tx = i;
            this.rx = i2;
        }

        public String toString() {
            if (this.tx != 0 && this.rx != 0) {
                return "TX: " + PhyHelper.phyMaskToString(this.tx) + ", RX: " + PhyHelper.phyMaskToString(this.rx);
            }
            if (this.tx != 0) {
                return "TX: " + PhyHelper.phyMaskToString(this.tx);
            }
            return "RX: " + PhyHelper.phyMaskToString(this.rx);
        }
    }

    public static int phyMaskToInt(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        int i = 0;
        for (String str2 : str.split("\\|")) {
            String trim = str2.trim();
            if (LE_1M.equals(trim)) {
                i |= 1;
            } else if (LE_2M.equals(trim)) {
                i |= 2;
            } else {
                if (!LE_CODED.equals(trim)) {
                    throw new PersistenceException("Invalid PHY constant: " + trim + ". Only " + LE_1M + ", " + LE_2M + " or " + LE_CODED + " are allowed.", new Object[0]);
                }
                i |= 4;
            }
        }
        return i;
    }

    public static String phyMaskToString(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 1) != 0) {
            sb.append(LE_1M);
            sb.append(" | ");
        }
        if ((i & 2) != 0) {
            sb.append(LE_2M);
            sb.append(" | ");
        }
        if ((i & 4) != 0) {
            sb.append(LE_CODED);
            sb.append(" | ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 3);
        }
        return sb.toString();
    }
}
