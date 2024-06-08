package no.nordicsemi.android.mcp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import java.util.UUID;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.database.init.AdoptedCharacteristics;
import no.nordicsemi.android.mcp.database.init.AdoptedDescriptors;
import no.nordicsemi.android.mcp.database.init.AdoptedServices;
import no.nordicsemi.android.mcp.database.init.ThirdPartyCharacteristics;
import no.nordicsemi.android.mcp.database.init.ThirdPartyServices;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SplashscreenActivity extends Activity {
    private TextView mStatus;

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.app.Activity
    @SuppressLint({"StaticFieldLeak"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splashscreen);
        if (bundle != null) {
            return;
        }
        this.mStatus = (TextView) findViewById(R.id.status);
        new AsyncTask<Void, Integer, Void>() { // from class: no.nordicsemi.android.mcp.SplashscreenActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                DatabaseHelper databaseHelper = new DatabaseHelper(SplashscreenActivity.this);
                int supportedServicesCount = databaseHelper.getSupportedServicesCount();
                Integer valueOf = Integer.valueOf(R.string.splashscreen_msg_3rd_party_characteristics);
                Integer valueOf2 = Integer.valueOf(R.string.splashscreen_msg_3rd_party_services);
                Integer valueOf3 = Integer.valueOf(R.string.splashscreen_msg_adopted_characteristics);
                Integer valueOf4 = Integer.valueOf(R.string.splashscreen_msg_adopted_services);
                if (supportedServicesCount == 0) {
                    publishProgress(valueOf4);
                    AdoptedServices.initialize(databaseHelper);
                    AdoptedServices.initializeDBVersion_1_11(databaseHelper);
                    AdoptedServices.initializeDBVersion_2_2(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initialize(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_1_11(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_2_2(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_3_2_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(Integer.valueOf(R.string.splashscreen_msg_adopted_desctiptors));
                    AdoptedDescriptors.initialize(databaseHelper);
                    AdoptedDescriptors.initializeDBVersion_2_2(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initialize(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_2_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initialize(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_2_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(DatabaseUtils.generateBluetoothBaseUuid(6172L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_1_11(databaseHelper);
                    AdoptedServices.initializeDBVersion_2_2(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_1_11(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_2_2(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_3_2_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_2_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_2_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(7944349750023943059L, -2258021889238840674L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_2_2(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_2_2(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_3_2_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_2_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_2_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isCharacteristicDefined(new UUID(23313385713630L, 1523193452336828707L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_2_2(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_2_2(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_3_2_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_2_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(DatabaseUtils.generateBluetoothBaseUuid(6177L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_2_2(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_2_2(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_3_2_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(-8628896877831879602L, -4233388132819706284L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_3_2_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_3_2(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isCharacteristicDefined(DatabaseUtils.generateBluetoothBaseUuid(10900L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_3_2_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(23240371269598L, 1523193452336828707L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_0(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(6178)) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_0_4(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(-6644932604928504865L, -8486575981020192107L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_1(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(8720644747813605017L, -6624993934312668976L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(65113)) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_SecDFU(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(-8196551313441075360L, -6937650605005804976L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_8(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isCharacteristicDefined(new UUID(-8157989228746813600L, -6937650605005804976L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_12_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_12(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(6183)) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_13_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(-1631139436351895798L, -6889669494057227864L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_14_4(databaseHelper);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isCharacteristicDefined(10813)) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_17_0(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                if (!databaseHelper.isServiceDefined(new UUID(-8263018873684013869L, -8751749364119983484L))) {
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf2);
                    ThirdPartyServices.initializeDBVersion_4_20_0(databaseHelper);
                    publishProgress(valueOf);
                    ThirdPartyCharacteristics.initializeDBVersion_4_20_0(databaseHelper);
                    return null;
                }
                try {
                    if (!databaseHelper.isServiceDefined(6185)) {
                        publishProgress(valueOf4);
                        AdoptedServices.initializeDBVersion_4_20_1(databaseHelper);
                        AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                        publishProgress(valueOf3);
                        AdoptedCharacteristics.initializeDBVersion_4_20_1(databaseHelper);
                        AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                        synchronized (this) {
                            wait(300L);
                        }
                        return null;
                    }
                    if (databaseHelper.isServiceDefined(6203)) {
                        return null;
                    }
                    publishProgress(valueOf4);
                    AdoptedServices.initializeDBVersion_4_23_0(databaseHelper);
                    publishProgress(valueOf3);
                    AdoptedCharacteristics.initializeDBVersion_4_23_0(databaseHelper);
                    synchronized (this) {
                        wait(300L);
                    }
                    return null;
                } catch (InterruptedException unused) {
                    return null;
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r3) {
                Intent intent = new Intent(SplashscreenActivity.this, (Class<?>) MainActivity.class);
                intent.addFlags(65536);
                SplashscreenActivity.this.startActivity(intent);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onProgressUpdate(Integer... numArr) {
                SplashscreenActivity.this.mStatus.setText(numArr[0].intValue());
            }
        }.execute(new Void[0]);
    }
}
