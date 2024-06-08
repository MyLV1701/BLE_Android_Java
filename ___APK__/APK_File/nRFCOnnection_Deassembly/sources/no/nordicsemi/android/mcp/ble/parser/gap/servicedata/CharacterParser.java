package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class CharacterParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        char unsignedByteToChar = unsignedByteToChar(bArr[i]);
        if (validCharacter(unsignedByteToChar)) {
            dataUnion.addData("Character", String.format(Locale.US, "%c", Character.valueOf(unsignedByteToChar)));
        }
    }

    private static char unsignedByteToChar(byte b2) {
        return (char) (b2 & FlagsParser.UNKNOWN_FLAGS);
    }

    private static boolean validCharacter(char c2) {
        return c2 >= ' ' && c2 <= '~';
    }
}
