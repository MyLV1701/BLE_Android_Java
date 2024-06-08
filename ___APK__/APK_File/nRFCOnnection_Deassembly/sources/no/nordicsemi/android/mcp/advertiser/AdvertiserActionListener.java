package no.nordicsemi.android.mcp.advertiser;

/* loaded from: classes.dex */
public interface AdvertiserActionListener {
    void onCloneAdvertisement(long j);

    void onEditAdvertisement(long j);

    void onRemoveAdvertisement(long j, int i);

    void onStartAdvertisement(long j);

    void onStartAdvertisement(long j, int i, int i2);

    void onStopAdvertisement(long j);
}
