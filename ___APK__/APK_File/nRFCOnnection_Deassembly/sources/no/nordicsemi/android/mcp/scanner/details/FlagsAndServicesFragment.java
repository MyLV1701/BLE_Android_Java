package no.nordicsemi.android.mcp.scanner.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class FlagsAndServicesFragment extends Fragment {
    private static final byte BR_EDR_NOT_SUPPORTED = 4;
    private static final String DEVICE_INDEX = "device_index";
    private static final int FLAGS = 1;
    private static final byte LE_GENERAL_DISCOVERABLE_MODE = 2;
    private static final byte LE_LIMITED_DISCOVERABLE_MODE = 1;
    private static final long LSB = -9223371485494954757L;
    private static final long MSB = 4096;
    private static final int SERVICES_COMPLETE_LIST_128_BIT = 7;
    private static final int SERVICES_COMPLETE_LIST_16_BIT = 3;
    private static final int SERVICES_COMPLETE_LIST_32_BIT = 5;
    private static final int SERVICES_MORE_AVAILABLE_128_BIT = 6;
    private static final int SERVICES_MORE_AVAILABLE_16_BIT = 2;
    private static final int SERVICES_MORE_AVAILABLE_32_BIT = 4;
    private static final int SERVICES_SOLICITATION_128_BIT = 21;
    private static final int SERVICES_SOLICITATION_16_BIT = 20;
    private static final int SERVICES_SOLICITATION_32_BIT = 31;
    private static final int SERVICE_DATA_128_BIT = 33;
    private static final int SERVICE_DATA_16_BIT = 22;
    private static final int SERVICE_DATA_32_BIT = 32;
    private static final byte SIMULTANEOUS_LE_AND_BR_EDR_CAPABLE_CONTROLLER = 8;
    private static final byte SIMULTANEOUS_LE_AND_BR_EDR_CAPABLE_HOST = 16;
    private int[] mColors;
    private DatabaseHelper mDatabaseHelper;
    private Device mDevice;

    /* loaded from: classes.dex */
    private class PacketsAdapter extends RecyclerView.g<ViewHolder> {
        private final List<UUID> mServiceData128Bit;
        private final List<UUID> mServiceData16Bit;
        private final List<UUID> mServiceData32Bit;
        private final List<UUID> mServicesComplete128Bit;
        private final List<UUID> mServicesComplete16Bit;
        private final List<UUID> mServicesComplete32Bit;
        private final List<UUID> mServicesIncomplete128Bit;
        private final List<UUID> mServicesIncomplete16Bit;
        private final List<UUID> mServicesIncomplete32Bit;
        private final List<UUID> mServicesSolicitation128Bit;
        private final List<UUID> mServicesSolicitation16Bit;
        private final List<UUID> mServicesSolicitation32Bit;

        /* loaded from: classes.dex */
        public class ViewHolder extends RecyclerView.d0 {
            private TextView mBrErdNotSupportedFlagView;
            private TextView mFlagView;
            private View mFlagsDetailsView;
            private TextView mGeneralDiscoverableFlagView;
            private ImageView mIconView;
            private TextView mLeAndErErdCapableControllerFlagView;
            private TextView mLeAndErErdCapableHostFlagView;
            private TextView mLimitedDiscoverableFlagView;
            private TextView mParsingErrors;
            private TextView mParsingErrorsTitle;
            private View mServiceData128BitTitleView;
            private TextView mServiceData128BitView;
            private View mServiceData16BitTitleView;
            private TextView mServiceData16BitView;
            private View mServiceData32BitTitleView;
            private TextView mServiceData32BitView;
            private View mServicesComplete128BitTitleView;
            private TextView mServicesComplete128BitView;
            private View mServicesComplete16BitTitleView;
            private TextView mServicesComplete16BitView;
            private View mServicesComplete32BitTitleView;
            private TextView mServicesComplete32BitView;
            private View mServicesIncomplete128BitTitleView;
            private TextView mServicesIncomplete128BitView;
            private View mServicesIncomplete16BitTitleView;
            private TextView mServicesIncomplete16BitView;
            private View mServicesIncomplete32BitTitleView;
            private TextView mServicesIncomplete32BitView;
            private View mServicesSolicitation128BitTitleView;
            private TextView mServicesSolicitation128BitView;
            private View mServicesSolicitation16BitTitleView;
            private TextView mServicesSolicitation16BitView;
            private View mServicesSolicitation32BitTitleView;
            private TextView mServicesSolicitation32BitView;

            public ViewHolder(View view) {
                super(view);
                this.mIconView = (ImageView) view.findViewById(R.id.bar);
                this.mFlagView = (TextView) view.findViewById(R.id.flags);
                this.mFlagsDetailsView = view.findViewById(R.id.flags_details);
                this.mLimitedDiscoverableFlagView = (TextView) view.findViewById(R.id.flag_limited_discoverable);
                this.mGeneralDiscoverableFlagView = (TextView) view.findViewById(R.id.flag_general_discoverable);
                this.mBrErdNotSupportedFlagView = (TextView) view.findViewById(R.id.flag_br_erd_not_supported);
                this.mLeAndErErdCapableControllerFlagView = (TextView) view.findViewById(R.id.flag_le_and_br_erd_controller);
                this.mLeAndErErdCapableHostFlagView = (TextView) view.findViewById(R.id.flag_le_and_br_erd_host);
                this.mServicesIncomplete16BitTitleView = view.findViewById(R.id.services_incomplete_16_bit_title);
                this.mServicesIncomplete32BitTitleView = view.findViewById(R.id.services_incomplete_32_bit_title);
                this.mServicesIncomplete128BitTitleView = view.findViewById(R.id.services_incomplete_128_bit_title);
                this.mServicesComplete16BitTitleView = view.findViewById(R.id.services_complete_16_bit_title);
                this.mServicesComplete32BitTitleView = view.findViewById(R.id.services_complete_32_bit_title);
                this.mServicesComplete128BitTitleView = view.findViewById(R.id.services_complete_128_bit_title);
                this.mServicesSolicitation16BitTitleView = view.findViewById(R.id.services_solicitation_16_bit_title);
                this.mServicesSolicitation32BitTitleView = view.findViewById(R.id.services_solicitation_32_bit_title);
                this.mServicesSolicitation128BitTitleView = view.findViewById(R.id.services_solicitation_128_bit_title);
                this.mServicesIncomplete16BitView = (TextView) view.findViewById(R.id.services_incomplete_16_bit);
                this.mServicesIncomplete32BitView = (TextView) view.findViewById(R.id.services_incomplete_32_bit);
                this.mServicesIncomplete128BitView = (TextView) view.findViewById(R.id.services_incomplete_128_bit);
                this.mServicesComplete16BitView = (TextView) view.findViewById(R.id.services_complete_16_bit);
                this.mServicesComplete32BitView = (TextView) view.findViewById(R.id.services_complete_32_bit);
                this.mServicesComplete128BitView = (TextView) view.findViewById(R.id.services_complete_128_bit);
                this.mServicesSolicitation16BitView = (TextView) view.findViewById(R.id.services_solicitation_16_bit);
                this.mServicesSolicitation32BitView = (TextView) view.findViewById(R.id.services_solicitation_32_bit);
                this.mServicesSolicitation128BitView = (TextView) view.findViewById(R.id.services_solicitation_128_bit);
                this.mServiceData16BitTitleView = view.findViewById(R.id.service_data_16_bit_title);
                this.mServiceData32BitTitleView = view.findViewById(R.id.service_data_32_bit_title);
                this.mServiceData128BitTitleView = view.findViewById(R.id.service_data_128_bit_title);
                this.mServiceData16BitView = (TextView) view.findViewById(R.id.service_data_16_bit);
                this.mServiceData32BitView = (TextView) view.findViewById(R.id.service_data_32_bit);
                this.mServiceData128BitView = (TextView) view.findViewById(R.id.service_data_128_bit);
                this.mParsingErrorsTitle = (TextView) view.findViewById(R.id.parsing_errors_title);
                this.mParsingErrors = (TextView) view.findViewById(R.id.parsing_errors);
            }
        }

        private PacketsAdapter() {
            this.mServicesIncomplete16Bit = new ArrayList(4);
            this.mServicesIncomplete32Bit = new ArrayList(0);
            this.mServicesIncomplete128Bit = new ArrayList(1);
            this.mServicesComplete16Bit = new ArrayList(4);
            this.mServicesComplete32Bit = new ArrayList(0);
            this.mServicesComplete128Bit = new ArrayList(1);
            this.mServicesSolicitation16Bit = new ArrayList(4);
            this.mServicesSolicitation32Bit = new ArrayList(0);
            this.mServicesSolicitation128Bit = new ArrayList(1);
            this.mServiceData16Bit = new ArrayList(4);
            this.mServiceData32Bit = new ArrayList(0);
            this.mServiceData128Bit = new ArrayList(1);
        }

        private void add128BitServices(List<UUID> list, byte[] bArr, int i, int i2) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr, i, i2);
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            for (int i3 = i; i3 < bArr.length && i3 < i + i2; i3 += 16) {
                list.add(new UUID(wrap.getLong(), wrap.getLong()));
            }
        }

        private void add16BitServices(List<UUID> list, byte[] bArr, int i, int i2) {
            ByteBuffer.wrap(bArr, i, i2).order(ByteOrder.LITTLE_ENDIAN);
            for (int i3 = i; i3 < bArr.length && i3 < i + i2; i3 += 2) {
                list.add(generateBluetoothBaseUuid(r0.getShort() & 65535));
            }
        }

        private void add32BitServices(List<UUID> list, byte[] bArr, int i, int i2) {
            ByteBuffer.wrap(bArr, i, i2).order(ByteOrder.LITTLE_ENDIAN);
            for (int i3 = i; i3 < bArr.length && i3 < i + i2; i3 += 4) {
                list.add(generateBluetoothBaseUuid(r0.getInt()));
            }
        }

        private void clearServicesList() {
            this.mServicesIncomplete16Bit.clear();
            this.mServicesIncomplete32Bit.clear();
            this.mServicesIncomplete128Bit.clear();
            this.mServicesComplete16Bit.clear();
            this.mServicesComplete32Bit.clear();
            this.mServicesComplete128Bit.clear();
            this.mServicesSolicitation16Bit.clear();
            this.mServicesSolicitation32Bit.clear();
            this.mServicesSolicitation128Bit.clear();
            this.mServiceData16Bit.clear();
            this.mServiceData32Bit.clear();
            this.mServiceData128Bit.clear();
        }

        private UUID generateBluetoothBaseUuid(long j) {
            return new UUID((j << 32) + 4096, -9223371485494954757L);
        }

        private String getMemberName(UUID uuid) {
            if (uuid.getLeastSignificantBits() != -9223371485494954757L || (uuid.getMostSignificantBits() & 4294967295L) != 4096) {
                return null;
            }
            switch ((int) ((uuid.getMostSignificantBits() & (-4294967296L)) >>> 32)) {
                case 64900:
                    return "Tile, Inc.";
                case 64901:
                    return "Husqvarna AB";
                case 64902:
                    return "Abbott";
                case 64903:
                    return "Google LLC";
                case 64904:
                case 64905:
                    return "Urbanminded LTD";
                case 64906:
                    return "Signify Netherlands B.V.";
                case 64907:
                    return "Jigowatts Inc.";
                case 64908:
                    return "Google LLC";
                case 64909:
                    return "quip NYC Inc.";
                case 64910:
                    return "Motorola Solutions";
                case 64911:
                    return "Matrix ComSec Pvt. Ltd.";
                case 64912:
                    return "Guangzhou SuperSound Information Technology Co.,Ltd";
                case 64913:
                    return "Groove X, Inc.";
                case 64914:
                    return "Qualcomm Technologies International, Ltd. (QTIL)";
                case 64915:
                    return "Bayerische Motoren Werke AG";
                case 64916:
                    return "Hewlett Packard Enterprise";
                case 64917:
                    return "Rigado";
                case 64918:
                    return "Google LLC";
                case 64919:
                    return "June Life, Inc.";
                case 64920:
                    return "Disney Worldwide Services, Inc.";
                case 64921:
                    return "ABB Oy";
                case 64922:
                case 64923:
                case 64924:
                    return "Huawei Technologies Co., Ltd.";
                case 64925:
                    return "Gastec Corporation";
                case 64926:
                    return "The Coca-Cola Company";
                case 64927:
                    return "VitalTech Affiliates LLC";
                case 64928:
                    return "Secugen Corporation";
                case 64929:
                case 64930:
                    return "Groove X, Inc";
                case 64931:
                case 64932:
                    return "Inseego Corp.";
                case 64933:
                    return "Neurostim OAB, Inc.";
                case 64934:
                case 64935:
                    return "WWZN Information Technology Company Limited";
                case 64936:
                    return "PSA Peugeot Citroën";
                case 64937:
                    return "Rhombus Systems, Inc.";
                case 64938:
                case 64939:
                    return "Xiaomi Inc.";
                case 64940:
                    return "Tentacle Sync GmbH";
                case 64941:
                case 64942:
                    return "Houwa System Design, k.k.";
                case 64943:
                    return "Wiliot LTD";
                case 64944:
                case 64945:
                    return "Proxy Technologies, Inc.";
                case 64946:
                    return "Portable Multimedia Ltd ";
                case 64947:
                    return "Audiodo AB";
                case 64948:
                    return "HP Inc";
                case 64949:
                    return "ECSG";
                case 64950:
                    return "GWA Hygiene GmbH";
                case 64951:
                case 64952:
                    return "LivaNova USA Inc.";
                case 64953:
                case 64954:
                    return "Comcast Cable Corporation";
                case 64955:
                    return "Profoto";
                case 64956:
                    return "Emerson";
                case 64957:
                    return "Clover Network, Inc.";
                case 64958:
                case 64959:
                    return "California Things Inc. ";
                case 64960:
                case 64961:
                    return "Hunter Douglas";
                case 64962:
                case 64963:
                    return "Baidu Online Network Technology (Beijing) Co., Ltd";
                case 64964:
                    return "Simavita (Aust) Pty Ltd";
                case 64965:
                    return "Automatic Labs";
                case 64966:
                case 64967:
                    return "Eli Lilly and Company";
                case 64968:
                    return "Hach Danaher";
                case 64969:
                    return "Busch-Jaeger Elektro GmbH";
                case 64970:
                    return "Fortin Electronic Systems";
                case 64971:
                    return "Meggitt SA";
                case 64972:
                    return "Shoof Technologies";
                case 64973:
                    return "Qingping Technology (Beijing) Co., Ltd.";
                case 64974:
                    return "SENNHEISER electronic GmbH & Co. KG";
                case 64975:
                    return "Nalu Medical, Inc";
                case 64976:
                case 64977:
                    return "Huawei Technologies Co., Ltd ";
                case 64978:
                    return "Bose Corporation";
                case 64979:
                    return "FUBA Automotive Electronics GmbH";
                case 64980:
                    return "LX Solutions Pty Limited";
                case 64981:
                    return "Brompton Bicycle Ltd";
                case 64982:
                    return "Ministry of Supply";
                case 64983:
                    return "Emerson";
                case 64984:
                case 64985:
                    return "Jiangsu Teranovo Tech Co., Ltd.";
                case 64986:
                    return "MHCS";
                case 64987:
                    return "Samsung Electronics Co., Ltd. ";
                case 64988:
                    return "4iiii Innovations Inc.";
                case 64989:
                    return "Arch Systems Inc";
                case 64990:
                    return "Noodle Technology Inc.";
                case 64991:
                    return "Harman International";
                case 64992:
                    return "John Deere";
                case 64993:
                    return "Fortin Electronic Systems ";
                case 64994:
                    return "Google Inc.";
                case 64995:
                    return "Abbott Diabetes Care";
                case 64996:
                    return "JUUL Labs, Inc.";
                case 64997:
                    return "SMK Corporation ";
                case 64998:
                    return "Intelletto Technologies Inc";
                case 64999:
                    return "SECOM Co., LTD";
                case 65000:
                    return "Robert Bosch GmbH";
                case 65001:
                    return "Spacesaver Corporation";
                case 65002:
                    return "SeeScan, Inc";
                case 65003:
                    return "Syntronix Corporation";
                case 65004:
                    return "Mannkind Corporation";
                case 65005:
                    return "Pole Star";
                case 65006:
                    return "Huawei Technologies Co., Ltd.";
                case 65007:
                    return "ART AND PROGRAM, INC.";
                case 65008:
                    return "Google Inc.";
                case 65009:
                    return "LAMPLIGHT Co.,Ltd";
                case 65010:
                    return "AMICCOM Electronics Corporation";
                case 65011:
                    return "Amersports";
                case 65012:
                    return "O. E. M. Controls, Inc.";
                case 65013:
                    return "Milwaukee Electric Tools";
                case 65014:
                    return "AIAIAI ApS";
                case 65015:
                    return "HP Inc.";
                case 65016:
                    return "Onvocal";
                case 65017:
                    return "INIA";
                case 65018:
                case 65019:
                    return "Tandem Diabetes Care";
                case 65020:
                    return "Optrel AG";
                case 65021:
                    return "RecursiveSoft Inc.";
                case 65022:
                    return "ADHERIUM(NZ) LIMITED";
                case 65023:
                    return "OSRAM GmbH";
                case 65024:
                    return "Amazon Fulfillment Services Inc.";
                case 65025:
                    return "Duracell U.S. Operations Inc.";
                case 65026:
                    return "Robert Bosch GmbH";
                case 65027:
                    return "Amazon Fulfillment Services, Inc";
                case 65028:
                    return "OpenPath Security Inc";
                case 65029:
                    return "CORE Transport Technologies NZ Limited";
                case 65030:
                    return "Qualcomm Technologies, Inc.";
                case 65031:
                    return "Sonos Inc.";
                case 65032:
                    return "Microsoft";
                case 65033:
                    return "Pillsy, Inc.";
                case 65034:
                case 65035:
                    return "ruwido austria gmbh";
                case 65036:
                case 65037:
                    return "Procter & Gamble";
                case 65038:
                    return "Setec Pty Ltd";
                case 65039:
                    return "Philips Lighting B.V.";
                case 65040:
                    return "Lapis Semiconductor Co., Ltd.";
                case 65041:
                    return "GMC-I Messtechnik GmbH";
                case 65042:
                    return "M-Way Solutions GmbH";
                case 65043:
                    return "Apple Inc.";
                case 65044:
                    return "Flextronics International USA Inc.";
                case 65045:
                    return "Amazon Fulfillment Services, Inc.";
                case 65046:
                    return "Footmarks, Inc.";
                case 65047:
                    return "Telit Wireless Solutions GmbH";
                case 65048:
                    return "Runtime, Inc.";
                case 65049:
                    return "Google Inc.";
                case 65050:
                case 65051:
                    return "Tyto Life LLC";
                case 65052:
                    return "NetMedia, Inc.";
                case 65053:
                    return "Illuminati Instrument Corporation";
                case 65054:
                    return "Smart Innovations Co., Ltd";
                case 65055:
                    return "Garmin International, Inc.";
                case 65056:
                    return "Emerson";
                case 65057:
                    return "Bose Corporation";
                case 65058:
                case 65059:
                    return "Zoll Medical Corporation";
                case 65060:
                    return "August Home Inc";
                case 65061:
                    return "Apple, Inc. ";
                case 65062:
                case 65063:
                    return "Google Inc.";
                case 65064:
                    return "Ayla Networks";
                case 65065:
                    return "Gibson Innovations";
                case 65066:
                    return "DaisyWorks, Inc.";
                case 65067:
                    return "ITT Industries";
                case 65068:
                    return "Google Inc.";
                case 65069:
                    return "SMART INNOVATION Co.,Ltd";
                case 65070:
                    return "ERi,Inc.";
                case 65071:
                    return "CRESCO Wireless, Inc";
                case 65072:
                case 65073:
                    return "Volkswagen AG";
                case 65074:
                    return "Pro-Mark, Inc.";
                case 65075:
                    return "CHIPOLO d.o.o.";
                case 65076:
                    return "SmallLoop LLC";
                case 65077:
                case 65078:
                    return "HUAWEI Technologies Co., Ltd";
                case 65079:
                case 65080:
                    return "Spaceek LTD";
                case 65081:
                case 65082:
                    return "TTS Tooltechnic Systems AG & Co. KG";
                case 65083:
                    return "Dolby Laboratories";
                case 65084:
                    return "Alibaba";
                case 65085:
                    return "BD Medical";
                case 65086:
                    return "BD Medical";
                case 65087:
                    return "Friday Labs Limited";
                case 65088:
                case 65089:
                    return "Inugo Systems Limited";
                case 65090:
                    return "Nets A/S ";
                case 65091:
                    return "Andreas Stihl AG & Co. KG";
                case 65092:
                    return "SK Telecom ";
                case 65093:
                    return "Snapchat Inc";
                case 65094:
                    return "B&O Play A/S ";
                case 65095:
                case 65096:
                    return "General Motors";
                case 65097:
                    return "SenionLab AB";
                case 65098:
                    return "OMRON HEALTHCARE Co., Ltd.";
                case 65099:
                    return "Philips Lighting B.V.";
                case 65100:
                    return "Volkswagen AG";
                case 65101:
                    return "Casambi Technologies Oy";
                case 65102:
                    return "NTT docomo";
                case 65103:
                    return "Molekule, Inc.";
                case 65104:
                    return "Google Inc.";
                case 65105:
                    return "SRAM";
                case 65106:
                    return "SetPoint Medical";
                case 65107:
                    return "3M";
                case 65108:
                    return "Motiv, Inc.";
                case 65109:
                case 65110:
                    return "Google Inc.";
                case 65111:
                    return "Dotted Labs";
                case 65112:
                case 65113:
                    return "Nordic Semiconductor ASA";
                case 65114:
                    return "Chronologics Corporation";
                case 65115:
                    return "GT-tronics HK Ltd";
                case 65116:
                    return "million hunters GmbH";
                case 65117:
                    return "Grundfos A/S";
                case 65118:
                    return "Plastc Corporation";
                case 65119:
                    return "Eyefi, Inc.";
                case 65120:
                    return "Lierda Science & Technology Group Co., Ltd.";
                case 65121:
                    return "Logitech International SA";
                case 65122:
                    return "Indagem Tech LLC";
                case 65123:
                    return "Connected Yard, Inc.";
                case 65124:
                    return "Siemens AG";
                case 65125:
                    return "CHIPOLO d.o.o.";
                case 65126:
                    return "Intel Corporation";
                case 65127:
                    return "Lab Sensor Solutions";
                case 65128:
                case 65129:
                    return "Qualcomm Life Inc";
                case 65130:
                    return "Kontakt Micro-Location Sp. z o.o.";
                case 65131:
                case 65132:
                    return "TASER International, Inc.";
                case 65133:
                case 65134:
                    return "The University of Tokyo";
                case 65135:
                    return "LINE Corporation";
                case 65136:
                    return "Beijing Jingdong Century Trading Co., Ltd.";
                case 65137:
                    return "Plume Design Inc";
                case 65138:
                case 65139:
                    return "St. Jude Medical, Inc.";
                case 65140:
                    return "unwire";
                case 65141:
                case 65142:
                    return "TangoMe";
                case 65143:
                case 65144:
                    return "Hewlett-Packard Company";
                case 65145:
                    return "Zebra Technologies";
                case 65146:
                    return "Bragi GmbH";
                case 65147:
                    return "Orion Labs, Inc.";
                case 65148:
                    return "Stollmann E+V GmbH\u200b";
                case 65149:
                    return "\u200bAterica Health Inc.";
                case 65150:
                    return "\u200bAwear Solutions Ltd";
                case 65151:
                case 65152:
                    return "\u200bDoppler Lab";
                case 65153:
                    return "\u200bMedtronic Inc.";
                case 65154:
                    return "\u200bMedtronic Inc.";
                case 65155:
                    return "Blue Bite\u200b";
                case 65156:
                case 65157:
                    return "\u200b\u200bRF Digital Corp";
                case 65158:
                    return "\u200bHUAWEI Technologies Co., Ltd.";
                case 65159:
                    return "\u200bQingdao Yeelink Information Technology Co., Ltd.";
                case 65160:
                    return "\u200bSALTO SYSTEMS S.L.\u200b";
                case 65161:
                    return "B&O Play A/S\u200b";
                case 65162:
                case 65163:
                    return "\u200bApple, Inc.";
                case 65164:
                    return "\u200bTRON Forum";
                case 65165:
                    return "\u200bInteraxon Inc.";
                case 65166:
                    return "\u200bARM Ltd";
                case 65167:
                    return "CSR";
                case 65168:
                    return "JUMA";
                case 65169:
                    return "Shanghai Imilab Technology Co.,Ltd";
                case 65170:
                    return "Jarden Safety & Security";
                case 65171:
                case 65172:
                    return "OttoQ Inc.";
                case 65173:
                    return "Xiaomi Inc.";
                case 65174:
                case 65175:
                    return "Tesla Motor Inc.";
                case 65176:
                case 65177:
                    return "Currant, Inc.";
                case 65178:
                    return "Estimote";
                case 65179:
                    return "Samsara Networks, Inc";
                case 65180:
                    return "GSI Laboratories, Inc.";
                case 65181:
                    return "Mobiquity Networks Inc";
                case 65182:
                    return "Dialog Semiconductor B.V.";
                case 65183:
                case 65184:
                    return "Google Inc.";
                case 65185:
                case 65186:
                    return "Intrepid Control Systems, Inc.";
                case 65187:
                    return "ITT Industries";
                case 65188:
                    return "Paxton Access Ltd";
                case 65189:
                case 65190:
                    return "GoPro, Inc.";
                case 65191:
                    return "UTC Fire and Security";
                case 65192:
                case 65193:
                    return "Savant Systems LLC";
                case 65194:
                    return "Eddystone";
                case 65195:
                case 65196:
                case 65197:
                case 65198:
                    return "Nokia Corporation";
                case 65199:
                case 65200:
                    return "Nest Labs Inc.";
                case 65201:
                    return "Electronics Tomorrow Limited";
                case 65202:
                    return "Microsoft Corporation";
                case 65203:
                    return "Taobao";
                case 65204:
                case 65205:
                    return "WiSilica Inc.";
                case 65206:
                    return "Vencer Co, Ltd";
                case 65207:
                case 65208:
                    return "Facebook, Inc.";
                case 65209:
                    return "LG Electronics";
                case 65210:
                    return "Tencent Holdings Limited";
                case 65211:
                    return "adafruit industries";
                case 65212:
                    return "Dexcom, Inc.";
                case 65213:
                    return "Clover Network, Inc.";
                case 65214:
                    return "Bose Corporation";
                case 65215:
                    return "Nod, Inc.";
                case 65216:
                case 65217:
                    return "KDDI Corporation";
                case 65218:
                    return "Blue Spark Technologies, Inc.";
                case 65219:
                    return "360fly, Inc. ";
                case 65220:
                    return "PLUS Location Systems";
                case 65221:
                    return "Realtek Semiconductor Corp.";
                case 65222:
                    return "Kocomojo, LLC";
                case 65223:
                case 65224:
                case 65225:
                case 65226:
                case 65227:
                case 65228:
                case 65229:
                case 65230:
                case 65231:
                case 65232:
                case 65233:
                case 65234:
                case 65235:
                case 65236:
                    return "Apple, Inc.";
                case 65237:
                    return "Plantronics Inc.";
                case 65238:
                case 65239:
                    return "Broadcom Corporation";
                case 65240:
                    return "Google Inc.";
                case 65241:
                    return "Pebble Technology Corporation";
                case 65242:
                    return "ISSC Technologies Corporation";
                case 65243:
                    return "Perka, Inc.";
                case 65244:
                case 65245:
                    return "Jawbone";
                case 65246:
                    return "Coin, Inc.";
                case 65247:
                    return "Design SHIFT";
                case 65248:
                case 65249:
                    return "Anhui Huami Information Technology Co.";
                case 65250:
                case 65251:
                    return "Anki, Inc.";
                case 65252:
                case 65253:
                    return "Nordic Semiconductor ASA";
                case 65254:
                    return "Seed Labs, Inc.";
                case 65255:
                    return "Tencent Holdings Limited";
                case 65256:
                case 65257:
                    return "Quintic Corp.";
                case 65258:
                case 65259:
                    return "Swirl Networks, Inc.";
                case 65260:
                case 65261:
                    return "Tile, Inc.";
                case 65262:
                    return "Polar Electro Oy";
                case 65263:
                    return "Polar Electro Oy";
                case 65264:
                    return "Intel";
                case 65265:
                case 65266:
                    return "CSR";
                case 65267:
                case 65268:
                    return "Google";
                case 65269:
                    return "Dialog Semiconductor GmbH";
                case 65270:
                    return "Wicentric, Inc.";
                case 65271:
                case 65272:
                    return "Aplix Corporation";
                case 65273:
                case 65274:
                    return "PayPal, Inc.";
                case 65275:
                    return "Stollmann E+V GmbH";
                case 65276:
                case 65277:
                    return "Gimbal, Inc.";
                case 65278:
                    return "GN ReSound A/S";
                case 65279:
                    return "GN Netcom";
                default:
                    return null;
            }
        }

        private String servicesToString(List<UUID> list) {
            StringBuilder sb = new StringBuilder();
            for (UUID uuid : list) {
                sb.append(uuid);
                String serviceName = FlagsAndServicesFragment.this.mDatabaseHelper.getServiceName(uuid);
                if (serviceName != null) {
                    sb.append(" (");
                    sb.append(serviceName);
                    sb.append(")");
                } else {
                    String memberName = getMemberName(uuid);
                    if (memberName != null) {
                        sb.append(" (");
                        sb.append(memberName);
                        sb.append(")");
                    }
                }
                sb.append(",\n");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return FlagsAndServicesFragment.this.mDevice.getPacketsHistory().size();
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:11:0x003f. Please report as an issue. */
        /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0042. Please report as an issue. */
        /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0045. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:144:0x01ff  */
        /* JADX WARN: Removed duplicated region for block: B:167:0x0289  */
        /* JADX WARN: Removed duplicated region for block: B:170:0x02ac  */
        /* JADX WARN: Removed duplicated region for block: B:173:0x02cf  */
        /* JADX WARN: Removed duplicated region for block: B:176:0x02f2  */
        /* JADX WARN: Removed duplicated region for block: B:179:0x0315  */
        /* JADX WARN: Removed duplicated region for block: B:182:0x0338  */
        /* JADX WARN: Removed duplicated region for block: B:185:0x035b  */
        /* JADX WARN: Removed duplicated region for block: B:188:0x037e  */
        /* JADX WARN: Removed duplicated region for block: B:191:0x03a1  */
        /* JADX WARN: Removed duplicated region for block: B:194:0x03c4  */
        /* JADX WARN: Removed duplicated region for block: B:197:0x03e7  */
        /* JADX WARN: Removed duplicated region for block: B:200:0x040a  */
        /* JADX WARN: Removed duplicated region for block: B:202:0x0427  */
        @Override // androidx.recyclerview.widget.RecyclerView.g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onBindViewHolder(no.nordicsemi.android.mcp.scanner.details.FlagsAndServicesFragment.PacketsAdapter.ViewHolder r14, int r15) {
            /*
                Method dump skipped, instructions count: 1134
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.scanner.details.FlagsAndServicesFragment.PacketsAdapter.onBindViewHolder(no.nordicsemi.android.mcp.scanner.details.FlagsAndServicesFragment$PacketsAdapter$ViewHolder, int):void");
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_details_data_item, viewGroup, false));
        }
    }

    public static FlagsAndServicesFragment getInstance(Device device) {
        FlagsAndServicesFragment flagsAndServicesFragment = new FlagsAndServicesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("device_index", device.getDeviceIndex());
        flagsAndServicesFragment.setArguments(bundle);
        return flagsAndServicesFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDevice = BluetoothLeScannerCompat.getDevices(getArguments().getInt("device_index"));
        this.mColors = getResources().getIntArray(R.array.hover_colors);
        this.mDatabaseHelper = new DatabaseHelper(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.device_details_data, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new g(view.getContext(), 1));
        recyclerView.setAdapter(new PacketsAdapter());
    }
}
