package no.nordicsemi.android.mcp.ble.parser.gap;

import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class MeshParser {
    private static final byte BEACON_TYPE_SECURE_NETWORK = 1;
    private static final byte BEACON_TYPE_UNPROVISIONED_DEVICE = 0;
    private static final int BEARER_OPCODE_LINK_ACK = 1;
    private static final int BEARER_OPCODE_LINK_CLOSE = 2;
    private static final int BEARER_OPCODE_LINK_OPEN = 0;
    private static final int FORMAT_PROVISIONING_BEARER_CONTROL = 3;
    private static final int FORMAT_TRANSACTION_ACKNOWLEDGMENT = 1;
    private static final int FORMAT_TRANSACTION_CONTINUATION = 2;
    private static final int FORMAT_TRANSACTION_START = 0;
    private static final String MESH_BEACON = "Mesh Beacon";
    private static final String MESH_MESSAGE = "Mesh Message";
    private static final String MESH_PROXY = "Mesh Proxy";
    private static final String PB_ADV = "PB ADV";
    private static final int PROVISIONING_CAPABILITIES = 1;
    private static final int PROVISIONING_COMPLETE = 8;
    private static final int PROVISIONING_CONFIRMATION = 5;
    private static final int PROVISIONING_DATA = 7;
    private static final int PROVISIONING_FAILED = 9;
    private static final int PROVISIONING_INPUT_COMPLETE = 4;
    private static final int PROVISIONING_INVITE = 0;
    private static final int PROVISIONING_PUBLIC_KEY = 3;
    private static final int PROVISIONING_RANDOM = 6;
    private static final int PROVISIONING_START = 2;
    private static final byte PROXY_TYPE_NETWORK_ID = 0;
    private static final byte PROXY_TYPE_NODE_ID = 1;

    private static void addAlgorithms(StringBuilder sb, int i) {
        if ((i & 1) > 0) {
            sb.append("FIPS P-256 Elliptic Curve, ");
        }
        int i2 = 65534 & i;
        if (i2 > 0) {
            sb.append(String.format(Locale.US, "RFU (0x%04X), ", Integer.valueOf(i2)));
        }
        if ((i & 65535) > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("None");
        }
    }

    private static void addInputActionBits2String(StringBuilder sb, int i) {
        if ((i & 1) > 0) {
            sb.append("Push, ");
        }
        if ((i & 2) > 0) {
            sb.append("Twist, ");
        }
        if ((i & 4) > 0) {
            sb.append("Input Numeric, ");
        }
        if ((i & 8) > 0) {
            sb.append("Input Alphanumeric, ");
        }
        int i2 = 65520 & i;
        if (i2 > 0) {
            sb.append(String.format(Locale.US, "RFU (0x%04X), ", Integer.valueOf(i2)));
        }
        if ((i & 65535) > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("None");
        }
    }

    private static void addOobInfo(StringBuilder sb, int i) {
        if ((i & 1) > 0) {
            sb.append("Other, ");
        }
        if ((i & 2) > 0) {
            sb.append("Electronic / URI, ");
        }
        if ((i & 4) > 0) {
            sb.append("2D machine-readable code, ");
        }
        if ((i & 8) > 0) {
            sb.append("Bar code, ");
        }
        if ((i & 16) > 0) {
            sb.append("Near Field Communication (NFC), ");
        }
        if ((i & 32) > 0) {
            sb.append("Number, ");
        }
        if ((i & 64) > 0) {
            sb.append("String, ");
        }
        if ((i & 1920) > 0) {
            sb.append("Reserved for Future Use, ");
        }
        if ((i & DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS) > 0) {
            sb.append("On box, ");
        }
        if ((i & 4096) > 0) {
            sb.append("Inside box, ");
        }
        if ((i & DfuBaseService.ERROR_REMOTE_MASK) > 0) {
            sb.append("On piece of paper, ");
        }
        if ((i & DfuBaseService.ERROR_CONNECTION_MASK) > 0) {
            sb.append("Inside manual, ");
        }
        if ((32768 & i) > 0) {
            sb.append("On device, ");
        }
        if (i > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("Not present");
        }
    }

    private static void addOutputActionBits2String(StringBuilder sb, int i) {
        if ((i & 1) > 0) {
            sb.append("Blink, ");
        }
        if ((i & 2) > 0) {
            sb.append("Beep, ");
        }
        if ((i & 4) > 0) {
            sb.append("Vibrate, ");
        }
        if ((i & 8) > 0) {
            sb.append("Output Numeric, ");
        }
        if ((i & 16) > 0) {
            sb.append("Output Alphanumeric, ");
        }
        int i2 = 65504 & i;
        if (i2 > 0) {
            sb.append(String.format(Locale.US, "RFU (0x%04X), ", Integer.valueOf(i2)));
        }
        if ((i & 65535) > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("None");
        }
    }

    private static void addPublicKeyType(StringBuilder sb, int i) {
        if ((i & 1) > 0) {
            sb.append("Public Key OOB information available, ");
        }
        int i2 = i & GattError.GATT_PROCEDURE_IN_PROGRESS;
        if (i2 > 0) {
            sb.append(String.format(Locale.US, "Prohibited (0x%04X), ", Integer.valueOf(i2)));
        }
        if ((i & 255) > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("None");
        }
    }

    private static void addStaticOOBType(StringBuilder sb, int i) {
        if ((i & 1) > 0) {
            sb.append("Static OOB information available, ");
        }
        int i2 = i & GattError.GATT_PROCEDURE_IN_PROGRESS;
        if (i2 > 0) {
            sb.append(String.format(Locale.US, "Prohibited (0x%04X), ", Integer.valueOf(i2)));
        }
        if ((i & 255) > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("None");
        }
    }

    private static String errorCode2String(byte b2) {
        switch (b2) {
            case 0:
                return "Prohibited";
            case 1:
                return "Invalid PDU";
            case 2:
                return "Invalid Format";
            case 3:
                return "Unexpected PDU";
            case 4:
                return "Confirmation Failed";
            case 5:
                return "Out of Resources";
            case 6:
                return "Decryption Failed";
            case 7:
                return "Unexpected Error";
            case 8:
                return "Cannot Assign Addresses";
            default:
                return "RFU (" + ((int) b2) + ")";
        }
    }

    private static long getUnsignedInt(int i) {
        return i & 4294967295L;
    }

    private static String inputAction2String(byte b2) {
        if (b2 == 0) {
            return "Push";
        }
        if (b2 == 1) {
            return "Twist";
        }
        if (b2 == 2) {
            return "Input Numeric";
        }
        if (b2 == 3) {
            return "Input Alphanumeric";
        }
        return "RFU (" + ((int) b2) + ")";
    }

    private static String outputAction2String(byte b2) {
        if (b2 == 0) {
            return "Blink";
        }
        if (b2 == 1) {
            return "Beep";
        }
        if (b2 == 2) {
            return "Vibrate";
        }
        if (b2 == 3) {
            return "Output Numeric";
        }
        if (b2 == 4) {
            return "Output Alphanumeric";
        }
        return "RFU (" + ((int) b2) + ")";
    }

    public static void parseBeacon(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2, boolean z) {
        int i3;
        byte b2;
        StringBuilder sb = new StringBuilder();
        if (z) {
            i3 = i;
            b2 = 0;
        } else {
            i3 = i + 1;
            b2 = bArr[i];
        }
        if (b2 == 0) {
            sb.append("\nBeacon type: Unprovisioned Device (0x00)");
            UUID bytesToUUID = ParserUtils.bytesToUUID(bArr, i3, 16);
            sb.append("\nUUID: ");
            sb.append(bytesToUUID);
            int intValue = ParserUtils.getIntValue(bArr, 98, i3 + 16);
            sb.append("\nOOB information: ");
            addOobInfo(sb, intValue);
            if (i2 == 23) {
                String bytesToHex = ParserUtils.bytesToHex(bArr, i3 + 18, 4, true);
                sb.append("\nURI Hash: ");
                sb.append(bytesToHex);
            }
        } else if (b2 != 1) {
            sb.append("\nBeacon type: Unknown (");
            sb.append((int) b2);
            sb.append(")");
            if (i2 > 1) {
                sb.append("\nPayload: ");
                sb.append(ParserUtils.bytesToHex(bArr, i3, i2 - 1, true));
            }
        } else {
            sb.append("\nBeacon type: Secure Network (0x01)");
            byte b3 = bArr[i3];
            sb.append("\nKey Refresh flag: ");
            sb.append((b3 & 1) > 0);
            sb.append("\nIV Update flag: ");
            sb.append((b3 & 2) > 0 ? "Normal operation" : "IV Update active");
            if (b3 > 3) {
                sb.append("\nReserved flags: ");
                sb.append(String.format(Locale.US, "0x%02X", Integer.valueOf(b3 & 252)));
            }
            sb.append("\nNetwork ID: ");
            sb.append(ParserUtils.bytesToMeshNetworkID(bArr, i3 + 1));
            int intValue2 = ParserUtils.getIntValue(bArr, 100, i3 + 9);
            sb.append("\nIV Index: ");
            sb.append(getUnsignedInt(intValue2));
            String bytesToHex2 = ParserUtils.bytesToHex(bArr, i3 + 13, 8, true);
            sb.append("\nAuthentication value: ");
            sb.append(bytesToHex2);
        }
        dataUnion.addData(MESH_BEACON, sb.toString());
        if (!z) {
            dataUnion.addData(CompleteLocalNameParser.BYTES, ParserUtils.bytesToHex(bArr, i, i2, true));
        }
        advData.setAppearance(26, false);
    }

    public static void parseMessage(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        byte b2 = bArr[i];
        dataUnion.addData(MESH_MESSAGE, String.format(Locale.US, "\nIVI: %d\nNID: 0x%02X\nEncrypted message: %s", Integer.valueOf((b2 >> 7) & 1), Integer.valueOf(b2 & Byte.MAX_VALUE), ParserUtils.bytesToHex(bArr, i + 1, i2 - 1, true)));
        dataUnion.addData(CompleteLocalNameParser.BYTES, ParserUtils.bytesToHex(bArr, i, i2, true));
        advData.setAppearance(26, false);
    }

    private static String parsePayload(byte[] bArr, int i, int i2) {
        int i3 = bArr[i] & 63;
        int i4 = i + 1;
        StringBuilder sb = new StringBuilder();
        sb.append("- Type: ");
        sb.append(provisioningPduType2String(i3));
        if (i3 != 0) {
            if (i3 == 1) {
                if (i2 >= 2) {
                    sb.append("\n- Number of Elements: ");
                    sb.append((int) bArr[i4]);
                    i4++;
                }
                if (i2 >= 4) {
                    int intValue = ParserUtils.getIntValue(bArr, 98, i4);
                    i4 += 2;
                    sb.append("\n- Algorithms: ");
                    addAlgorithms(sb, intValue);
                }
                if (i2 >= 5) {
                    sb.append("\n- Public Key Type: ");
                    addPublicKeyType(sb, bArr[i4] & FlagsParser.UNKNOWN_FLAGS);
                    i4++;
                }
                if (i2 >= 6) {
                    sb.append("\n- Static OOB Type: ");
                    addStaticOOBType(sb, bArr[i4] & FlagsParser.UNKNOWN_FLAGS);
                    i4++;
                }
                if (i2 >= 7) {
                    sb.append("\n- Output OOB Size: ");
                    if (bArr[i4] == 0) {
                        sb.append("The device does not support output OOB");
                    } else if (bArr[i4] >= 1 && bArr[i4] <= 8) {
                        sb.append((int) bArr[i4]);
                    } else {
                        sb.append("RFU (");
                        sb.append((int) bArr[i4]);
                        sb.append(")");
                    }
                    i4++;
                }
                if (i2 >= 9) {
                    int intValue2 = ParserUtils.getIntValue(bArr, 98, i4);
                    sb.append("\n- Output OOB Action: ");
                    addOutputActionBits2String(sb, intValue2);
                    i4 += 2;
                }
                if (i2 >= 11) {
                    sb.append("\n- Input OOB Size: ");
                    if (bArr[i4] == 0) {
                        sb.append("The device does not support input OOB");
                    } else if (bArr[i4] >= 1 && bArr[i4] <= 8) {
                        sb.append((int) bArr[i4]);
                    } else {
                        sb.append("RFU (");
                        sb.append((int) bArr[i4]);
                        sb.append(")");
                    }
                    i4++;
                }
                if (i2 >= 12) {
                    int intValue3 = ParserUtils.getIntValue(bArr, 98, i4);
                    sb.append("\n- Input OOB Action: ");
                    addInputActionBits2String(sb, intValue3);
                }
            } else if (i3 == 2) {
                if (i2 >= 2) {
                    sb.append("\n- Algorithm: ");
                    if (bArr[i4] == 0) {
                        sb.append("FIPS P-256 Elliptic Curve");
                    } else {
                        sb.append("RFU (");
                        sb.append((int) bArr[i4]);
                        sb.append(")");
                    }
                    i4++;
                }
                if (i2 >= 3) {
                    sb.append("\n- Public Key: ");
                    byte b2 = bArr[i4];
                    if (b2 == 0) {
                        sb.append("No OOB Public Key");
                    } else if (b2 != 1) {
                        sb.append("Prohibited (");
                        sb.append((int) bArr[i4]);
                        sb.append(")");
                    } else {
                        sb.append("OOB Public Key");
                    }
                    i4++;
                }
                if (i2 >= 4) {
                    sb.append("\n- Authentication Method: ");
                    byte b3 = bArr[i4];
                    if (b3 == 0) {
                        sb.append("No OOB");
                    } else if (b3 == 1) {
                        sb.append("Static OOB");
                    } else if (b3 == 2) {
                        sb.append("Output OOB");
                    } else if (b3 != 3) {
                        sb.append("Prohibited (");
                        sb.append((int) bArr[i4]);
                        sb.append(")");
                    } else {
                        sb.append("Input OOB");
                    }
                    int i5 = i4 + 1;
                    if (i2 >= 5) {
                        byte b4 = bArr[i5];
                        if (b3 == 2) {
                            sb.append("\n- Authentication Action: ");
                            sb.append(outputAction2String(b4));
                        } else if (b3 == 3) {
                            sb.append("\n- Authentication Action: ");
                            sb.append(inputAction2String(b4));
                        }
                        i5++;
                    }
                    if (i2 >= 6) {
                        byte b5 = bArr[i5];
                        if (b3 == 2 || b3 == 3) {
                            sb.append("\n- Authentication Size: ");
                            if (b5 == 0) {
                                sb.append("Prohibited (0x00)");
                            } else if (b5 >= 1 && b5 <= 8) {
                                sb.append((int) b5);
                            } else {
                                sb.append("RFU (");
                                sb.append((int) b5);
                                sb.append(")");
                            }
                        }
                    }
                }
            } else if (i3 != 3) {
                if (i3 == 5 || i3 == 6) {
                    if (i2 >= 2) {
                        sb.append("\n- Payload: ");
                        sb.append(ParserUtils.bytesToHex(bArr, i4, i2 - (i4 - i), true));
                    }
                } else if (i3 == 7) {
                    if (i2 >= 2) {
                        sb.append("\n- Encrypted provisioning data: ");
                        sb.append(ParserUtils.bytesToHex(bArr, i4, Math.min(25, i2 - (i4 - i)), true));
                        i4 += 25;
                    }
                    if (i2 >= 26) {
                        sb.append("\n- MIC: ");
                        sb.append(ParserUtils.bytesToHex(bArr, i4, Math.min(8, i2 - (i4 - i)), true));
                    }
                } else if (i3 == 9 && i2 >= 2) {
                    byte b6 = bArr[i4];
                    sb.append("\n- Error: ");
                    sb.append(errorCode2String(b6));
                }
            } else if (i2 >= 2) {
                sb.append("\n- Public key: ");
                sb.append(ParserUtils.bytesToHex(bArr, i4, i2 - (i4 - i), true));
            }
        } else if (i2 >= 2) {
            sb.append("\n- Attention Duration: ");
            sb.append(bArr[i4] == 0 ? "OFF" : String.valueOf((int) bArr[i4]));
        }
        return sb.toString();
    }

    public static void parsePbAdv(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        String bytesToHex = ParserUtils.bytesToHex(bArr, i, 4, true);
        int i3 = i + 4;
        int i4 = bArr[i3] & FlagsParser.UNKNOWN_FLAGS;
        int i5 = i3 + 1;
        StringBuilder sb = new StringBuilder();
        sb.append(i4 & 127);
        sb.append((i4 & 128) > 0 ? " + 0x80 (Provisioner)" : " (Device)");
        String sb2 = sb.toString();
        int i6 = bArr[i5] & 3;
        if (i6 == 0) {
            int i7 = (bArr[i5] & 252) >> 2;
            int i8 = i5 + 1;
            int intValue = ParserUtils.getIntValue(bArr, 98, i8);
            int i9 = i8 + 2;
            byte b2 = bArr[i9];
            int i10 = i9 + 1;
            dataUnion.addData(PB_ADV, String.format(Locale.US, "\nLink ID: %s\nTransaction number: %s\nPDU type: Transaction Start (0b00)\nLast sequence number: %d\nTotal length: %d\nFrame Check Sequence: 0x%02X\nPayload:\n%s", bytesToHex, sb2, Integer.valueOf(i7), Integer.valueOf(intValue), Byte.valueOf(b2), parsePayload(bArr, i10, i2 - (i10 - i))));
        } else if (i6 == 1) {
            dataUnion.addData(PB_ADV, String.format(Locale.US, "\nLink ID: %s\nTransaction number: %s\nPDU type: Transaction Acknowledgment (0b01)", bytesToHex, sb2));
        } else if (i6 == 2) {
            int i11 = (bArr[i5] & 252) >> 2;
            int i12 = i5 + 1;
            dataUnion.addData(PB_ADV, String.format(Locale.US, "\nLink ID: %s\nTransaction number: %s\nPDU type: Transaction Continuation (0b10)\nSequence number: %d\nPayload: %s", bytesToHex, sb2, Integer.valueOf(i11), ParserUtils.bytesToHex(bArr, i12, i2 - (i12 - i), true)));
        } else if (i6 == 3) {
            int i13 = (bArr[i5] & 252) >> 2;
            int i14 = i5 + 1;
            if (i13 == 0) {
                dataUnion.addData(PB_ADV, String.format(Locale.US, "\nLink ID: %s\nTransaction number: %s\nPDU type: Provisioning Bearer Control (0b11)\nBearer opcode: Link Open\nUUID: %s", bytesToHex, sb2, ParserUtils.bytesToUUID(bArr, i14, i2 - (i14 - i))));
            } else if (i13 == 1) {
                dataUnion.addData(PB_ADV, String.format(Locale.US, "\nLink ID: %s\nTransaction number: %s\nPDU type: Provisioning Bearer Control (0b11)\nBearer opcode: Link ACK", bytesToHex, sb2));
            } else if (i13 == 2) {
                dataUnion.addData(PB_ADV, String.format(Locale.US, "\nLink ID: %s\nTransaction number: %s\nPDU type: Provisioning Bearer Control (0b11)\nBearer opcode: Link Close\nReason: %s", bytesToHex, sb2, reason2String(bArr[i14] & FlagsParser.UNKNOWN_FLAGS)));
            }
        }
        dataUnion.addData(CompleteLocalNameParser.BYTES, ParserUtils.bytesToHex(bArr, i, i2, true));
        advData.setAppearance(26, false);
    }

    public static void parseProxy(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        byte b2 = bArr[i];
        int i3 = i + 1;
        if (b2 == 0) {
            sb.append("\nIdentification type: Network ID (0x00)");
            sb.append("\nNetwork ID: ");
            sb.append(ParserUtils.bytesToMeshNetworkID(bArr, i3));
        } else if (b2 != 1) {
            sb.append("\nIdentification type: Unknown (");
            sb.append((int) b2);
            sb.append(")");
            if (i2 > 1) {
                sb.append("\nPayload: ");
                sb.append(ParserUtils.bytesToHex(bArr, i3, i2 - 1, true));
            }
        } else {
            sb.append("\nIdentification type: Node Identity (0x01)");
            sb.append("\nHash: ");
            sb.append(ParserUtils.bytesToHex(bArr, i3, 8, true));
            sb.append("\nRandom: ");
            sb.append(ParserUtils.bytesToHex(bArr, i3 + 8, 8, true));
        }
        dataUnion.addData(MESH_PROXY, sb.toString());
        advData.setAppearance(26, false);
    }

    private static String provisioningPduType2String(int i) {
        switch (i) {
            case 0:
                return "Provisioning Invite (0x00)";
            case 1:
                return "Provisioning Capabilities (0x01)";
            case 2:
                return "Provisioning Start (0x02)";
            case 3:
                return "Provisioning Public Key (0x03)";
            case 4:
                return "Provisioning Input Complete (0x04)";
            case 5:
                return "Provisioning Confirmation (0x05)";
            case 6:
                return "Provisioning Random (0x06)";
            case 7:
                return "Provisioning Data (0x07)";
            case 8:
                return "Provisioning Complete (0x08)";
            case 9:
                return "Provisioning Failed (0x09)";
            default:
                return String.format(Locale.US, "RFU (0x%02X), ", Integer.valueOf(i));
        }
    }

    private static String reason2String(int i) {
        if (i == 0) {
            return "Success";
        }
        if (i == 1) {
            return "Timeout";
        }
        if (i == 2) {
            return "Fail";
        }
        return "Unrecognized (" + i + ")";
    }
}
