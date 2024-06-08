package no.nordicsemi.android.mcp.ble.parser.gap;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.mcp.ble.model.DataUnion;

/* loaded from: classes.dex */
public class UriParser {
    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        String str;
        int i3 = i + 1;
        int i4 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
        if (i4 == 194 || i4 == 195) {
            int i5 = i3 + 1;
            int i6 = bArr[i3] & FlagsParser.UNKNOWN_FLAGS;
            i2--;
            i3 = i5;
            i4 = i6;
        }
        try {
            str = scheme2String(i4) + new String(bArr, i3, i2 - 1, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            str = scheme2String(i4) + new String(bArr, i3, i2 - 1);
        }
        dataUnion.addData("URI", str, str);
    }

    private static String scheme2String(int i) {
        switch (i) {
            case 1:
                return "";
            case 2:
                return "aaa:";
            case 3:
                return "aaas:";
            case 4:
                return "about:";
            case 5:
                return "acap:";
            case 6:
                return "acct:";
            case 7:
                return "cap:";
            case 8:
                return "cid:";
            case 9:
                return "coap:";
            case 10:
                return "coaps:";
            case 11:
                return "crid:";
            case 12:
                return "data:";
            case 13:
                return "dav:";
            case 14:
                return "dict:";
            case 15:
                return "dns:";
            case 16:
                return "file:";
            case 17:
                return "ftp:";
            case 18:
                return "geo:";
            case 19:
                return "go:";
            case 20:
                return "gopher:";
            case 21:
                return "h323:";
            case 22:
                return "http:";
            case 23:
                return "https:";
            case 24:
                return "iax:";
            case 25:
                return "icap:";
            case 26:
                return "im:";
            case 27:
                return "imap:";
            case 28:
                return "info:";
            case 29:
                return "ipp:";
            case 30:
                return "ipps:";
            case 31:
                return "iris:";
            case 32:
                return "iris.beep:";
            case 33:
                return "iris.xpc:";
            case 34:
                return "iris.xpcs:";
            case 35:
                return "iris.lwz:";
            case 36:
                return "jabber:";
            case 37:
                return "ldap:";
            case 38:
                return "mailto:";
            case 39:
                return "mid:";
            case 40:
                return "msrp:";
            case 41:
                return "msrps:";
            case 42:
                return "mtqp:";
            case 43:
                return "mupdate:";
            case 44:
                return "news:";
            case 45:
                return "nfs:";
            case 46:
                return "ni:";
            case 47:
                return "nih:";
            case 48:
                return "nntp:";
            case 49:
                return "opaquelocktoken:";
            case 50:
                return "pop:";
            case 51:
                return "pres:";
            case 52:
                return "reload:";
            case 53:
                return "rtsp:";
            case 54:
                return "rtsps:";
            case 55:
                return "rtspu:";
            case 56:
                return "service:";
            case 57:
                return "session:";
            case 58:
                return "shttp:";
            case 59:
                return "sieve:";
            case 60:
                return "sip:";
            case 61:
                return "sips:";
            case 62:
                return "sms:";
            case 63:
                return "snmp:";
            case 64:
                return "soap.beep:";
            case 65:
                return "soap.beeps:";
            case 66:
                return "stun:";
            case 67:
                return "stuns:";
            case 68:
                return "tag:";
            case 69:
                return "tel:";
            case 70:
                return "telnet:";
            case 71:
                return "tftp:";
            case 72:
                return "thismessage:";
            case 73:
                return "tn3270:";
            case 74:
                return "tip:";
            case 75:
                return "turn:";
            case 76:
                return "turns:";
            case 77:
                return "tv:";
            case 78:
                return "urn:";
            case 79:
                return "vemmi:";
            case 80:
                return "ws:";
            case 81:
                return "wss:";
            case 82:
                return "xcon:";
            case 83:
                return "xcon-userid:";
            case 84:
                return "xmlrpc.beep:";
            case 85:
                return "xmlrpc.beeps:";
            case 86:
                return "xmpp:";
            case 87:
                return "z39.50r:";
            case 88:
                return "z39.50s:";
            case 89:
                return "acr:";
            case 90:
                return "adiumxtra:";
            case 91:
                return "afp:";
            case 92:
                return "afs:";
            case 93:
                return "aim:";
            case 94:
                return "apt:";
            case 95:
                return "attachment:";
            case 96:
                return "aw:";
            case 97:
                return "barion:";
            case 98:
                return "beshare:";
            case 99:
                return "bitcoin:";
            case 100:
                return "bolo:";
            case 101:
                return "callto:";
            case 102:
                return "chrome:";
            case 103:
                return "chrome-extension:";
            case 104:
                return "com-eventbrite-attendee:";
            case 105:
                return "content:";
            case 106:
                return "cvs:";
            case 107:
                return "dlna-playsingle:";
            case 108:
                return "dlna-playcontainer:";
            case 109:
                return "dtn:";
            case 110:
                return "dvb:";
            case 111:
                return "ed2k:";
            case 112:
                return "facetime:";
            case 113:
                return "feed:";
            case 114:
                return "feedready:";
            case 115:
                return "finger:";
            case 116:
                return "fish:";
            case 117:
                return "gg:";
            case 118:
                return "git:";
            case 119:
                return "gizmoproject:";
            case 120:
                return "gtalk:";
            case 121:
                return "ham:";
            case 122:
                return "hcp:";
            case 123:
                return "icon:";
            case 124:
                return "ipn:";
            case 125:
                return "irc:";
            case 126:
                return "irc6:";
            case 127:
                return "ircs:";
            case 128:
                return "itms:";
            case GattError.GATT_INTERNAL_ERROR /* 129 */:
                return "jar:";
            case GattError.GATT_WRONG_STATE /* 130 */:
                return "jms:";
            case GattError.GATT_DB_FULL /* 131 */:
                return "keyparc:";
            case GattError.GATT_BUSY /* 132 */:
                return "lastfm:";
            case GattError.GATT_ERROR /* 133 */:
                return "ldaps:";
            case GattError.GATT_CMD_STARTED /* 134 */:
                return "magnet:";
            case GattError.GATT_ILLEGAL_PARAMETER /* 135 */:
                return "maps:";
            case GattError.GATT_PENDING /* 136 */:
                return "market:";
            case GattError.GATT_AUTH_FAIL /* 137 */:
                return "message:";
            case GattError.GATT_MORE /* 138 */:
                return "mms:";
            case GattError.GATT_INVALID_CFG /* 139 */:
                return "ms-help:";
            case GattError.GATT_SERVICE_STARTED /* 140 */:
                return "ms-settings-power:";
            case GattError.GATT_ENCRYPTED_NO_MITM /* 141 */:
                return "msnim:";
            case GattError.GATT_NOT_ENCRYPTED /* 142 */:
                return "mumble:";
            case GattError.GATT_CONGESTED /* 143 */:
                return "mvn:";
            case 144:
                return "notes:";
            case 145:
                return "oid:";
            case 146:
                return "palm:";
            case 147:
                return "paparazzi:";
            case 148:
                return "pkcs11:";
            case 149:
                return "platform:";
            case 150:
                return "proxy:";
            case 151:
                return "psyc:";
            case 152:
                return "query:";
            case 153:
                return "res:";
            case 154:
                return "resource:";
            case 155:
                return "rmi:";
            case 156:
                return "rsync:";
            case 157:
                return "rtmfp:";
            case 158:
                return "rtmp:";
            case 159:
                return "secondlife:";
            case 160:
                return "sftp:";
            case 161:
                return "sgn:";
            case 162:
                return "skype:";
            case 163:
                return "smb:";
            case 164:
                return "smtp:";
            case 165:
                return "soldat:";
            case 166:
                return "spotify:";
            case 167:
                return "ssh:";
            case 168:
                return "steam:";
            case 169:
                return "submit:";
            case 170:
                return "svn:";
            case 171:
                return "teamspeak:";
            case 172:
                return "teliaeid:";
            case 173:
                return "things:";
            case 174:
                return "udp:";
            case 175:
                return "unreal:";
            case 176:
                return "ut2004:";
            case 177:
                return "ventrilo:";
            case 178:
                return "view-source:";
            case 179:
                return "webcal:";
            case 180:
                return "wtai:";
            case 181:
                return "wyciwyg:";
            case 182:
                return "xfire:";
            case 183:
                return "xri:";
            case 184:
                return "ymsgr:";
            case 185:
                return "example:";
            case 186:
                return "ms-settings-cloudstorage:";
            default:
                return String.format(Locale.US, "[unknown scheme (%04X)]:", Integer.valueOf(i));
        }
    }
}
