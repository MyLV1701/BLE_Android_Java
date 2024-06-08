package no.nordicsemi.android.mcp.definitions;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.l;
import androidx.fragment.app.p;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.CharacteristicContract;
import no.nordicsemi.android.mcp.database.DescriptorContract;
import no.nordicsemi.android.mcp.database.ServiceContract;
import no.nordicsemi.android.mcp.definitions.DefinitionsFragment;

/* loaded from: classes.dex */
public class TabsAdapter extends p {
    private String TITLE_ADOPTED_CHARACTERISTICS;
    private String TITLE_ADOPTED_DESCRIPTORS;
    private String TITLE_ADOPTED_SERVICES;
    private String TITLE_USER_CHARACTERISTICS;
    private String TITLE_USER_DESCRIPTORS;
    private String TITLE_USER_SERVICES;
    private String TITLE_VND_CHARACTERISTICS;
    private String TITLE_VND_DESCRIPTORS;
    private String TITLE_VND_SERVICES;

    public TabsAdapter(Context context, l lVar) {
        super(lVar, 1);
        this.TITLE_USER_SERVICES = context.getString(R.string.tab_user_services);
        this.TITLE_USER_CHARACTERISTICS = context.getString(R.string.tab_user_characteristics);
        this.TITLE_USER_DESCRIPTORS = context.getString(R.string.tab_user_descriptors);
        this.TITLE_ADOPTED_SERVICES = context.getString(R.string.tab_adopted_services);
        this.TITLE_ADOPTED_CHARACTERISTICS = context.getString(R.string.tab_adopted_characteristics);
        this.TITLE_ADOPTED_DESCRIPTORS = context.getString(R.string.tab_adopted_descriptors);
        this.TITLE_VND_SERVICES = context.getString(R.string.tab_vnd_services);
        this.TITLE_VND_CHARACTERISTICS = context.getString(R.string.tab_vnd_characteristics);
        this.TITLE_VND_DESCRIPTORS = context.getString(R.string.tab_vnd_descriptors);
    }

    @Override // androidx.viewpager.widget.a
    public int getCount() {
        return 9;
    }

    @Override // androidx.fragment.app.p
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return DefinitionsFragment.getInstance(ServiceContract.Service.USER_CONTENT_URI, false, DefinitionsFragment.Type.SERVICE, false, i);
            case 1:
                return DefinitionsFragment.getInstance(CharacteristicContract.Characteristic.USER_CONTENT_URI, false, DefinitionsFragment.Type.CHARACTERISTIC, true, i);
            case 2:
                return DefinitionsFragment.getInstance(DescriptorContract.Descriptor.USER_CONTENT_URI, false, DefinitionsFragment.Type.DESCRIPTOR, true, i);
            case 3:
                return DefinitionsFragment.getInstance(ServiceContract.Service.CONTENT_URI, true, DefinitionsFragment.Type.SERVICE, false, i);
            case 4:
                return DefinitionsFragment.getInstance(CharacteristicContract.Characteristic.CONTENT_URI, true, DefinitionsFragment.Type.CHARACTERISTIC, true, i);
            case 5:
                return DefinitionsFragment.getInstance(DescriptorContract.Descriptor.CONTENT_URI, true, DefinitionsFragment.Type.DESCRIPTOR, true, i);
            case 6:
                return DefinitionsFragment.getInstance(ServiceContract.Service.CONTENT_URI, false, DefinitionsFragment.Type.SERVICE, false, i);
            case 7:
                return DefinitionsFragment.getInstance(CharacteristicContract.Characteristic.CONTENT_URI, false, DefinitionsFragment.Type.CHARACTERISTIC, true, i);
            default:
                return DefinitionsFragment.getInstance(DescriptorContract.Descriptor.CONTENT_URI, false, DefinitionsFragment.Type.DESCRIPTOR, true, i);
        }
    }

    @Override // androidx.viewpager.widget.a
    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return this.TITLE_USER_SERVICES;
            case 1:
                return this.TITLE_USER_CHARACTERISTICS;
            case 2:
                return this.TITLE_USER_DESCRIPTORS;
            case 3:
                return this.TITLE_ADOPTED_SERVICES;
            case 4:
                return this.TITLE_ADOPTED_CHARACTERISTICS;
            case 5:
                return this.TITLE_ADOPTED_DESCRIPTORS;
            case 6:
                return this.TITLE_VND_SERVICES;
            case 7:
                return this.TITLE_VND_CHARACTERISTICS;
            case 8:
                return this.TITLE_VND_DESCRIPTORS;
            default:
                return null;
        }
    }
}
