package no.nordicsemi.android.mcp.settings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.appcompat.app.d;
import androidx.fragment.app.Fragment;
import com.google.android.material.switchmaterial.SwitchMaterial;
import java.util.Calendar;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.tutorial.TutorialActivity;

/* loaded from: classes.dex */
public class SettingsCategoriesFragment extends Fragment {
    static final String CATEGORY_ABOUT = "about";
    static final String CATEGORY_CONNECTIVITY = "connectivity";
    static final String CATEGORY_DFU = "dfu";
    static final String CATEGORY_EXPORT = "export";
    static final String CATEGORY_LOGGER = "logger";
    static final String CATEGORY_SCANNER = "scanner";
    private OnCategoryClickedListener mListener;

    /* loaded from: classes.dex */
    public interface OnCategoryClickedListener {
        void onCategoryClicked(String str);

        void onHelpClicked();
    }

    /* loaded from: classes.dex */
    private class OpenSettingsFragmentListener implements View.OnClickListener {
        private final String category;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingsCategoriesFragment.this.mListener.onCategoryClicked(this.category);
        }

        private OpenSettingsFragmentListener(String str) {
            this.category = str;
        }
    }

    public static boolean isChristmas() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(2) == 11 && calendar.get(5) >= 10 && calendar.get(5) <= 26;
    }

    private void setTheme() {
        final androidx.fragment.app.d activity = getActivity();
        if (activity != null) {
            int i = androidx.preference.j.a(activity).getInt(SettingsFragment.SETTINGS_THEME, -1) + 1;
            d.a aVar = new d.a(activity);
            aVar.c(R.string.settings_theme);
            aVar.a(R.array.settings_theme_entries, i, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.h
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    SettingsCategoriesFragment.a(activity, dialogInterface, i2);
                }
            });
            aVar.a(android.R.string.cancel, (DialogInterface.OnClickListener) null);
            aVar.c();
        }
    }

    public /* synthetic */ void a(View view) {
        setTheme();
    }

    public /* synthetic */ void b(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) TutorialActivity.class));
    }

    public /* synthetic */ void c(View view) {
        Intent intent = new Intent(getActivity(), (Class<?>) TutorialActivity.class);
        intent.putExtra(TutorialActivity.EXTRA_LAST_VERSION, 1);
        startActivity(intent);
    }

    public /* synthetic */ void d(View view) {
        this.mListener.onHelpClicked();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnCategoryClickedListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement CategoriesListener");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_settings, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle bundle) {
        view.findViewById(R.id.category_scanner).setOnClickListener(new OpenSettingsFragmentListener(CATEGORY_SCANNER));
        view.findViewById(R.id.category_connectivity).setOnClickListener(new OpenSettingsFragmentListener(CATEGORY_CONNECTIVITY));
        view.findViewById(R.id.category_dfu).setOnClickListener(new OpenSettingsFragmentListener("dfu"));
        view.findViewById(R.id.category_logger).setOnClickListener(new OpenSettingsFragmentListener(CATEGORY_LOGGER));
        view.findViewById(R.id.category_export).setOnClickListener(new OpenSettingsFragmentListener(CATEGORY_EXPORT));
        view.findViewById(R.id.category_theme).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsCategoriesFragment.this.a(view2);
            }
        });
        SwitchMaterial switchMaterial = (SwitchMaterial) view.findViewById(R.id.santa_mode);
        switchMaterial.setChecked(androidx.preference.j.a(view.getContext()).getBoolean(SettingsFragment.SETTINGS_SANTA, true));
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.settings.i
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                androidx.preference.j.a(view.getContext()).edit().putBoolean(SettingsFragment.SETTINGS_SANTA, z).apply();
            }
        });
        if (isChristmas()) {
            switchMaterial.setVisibility(0);
        }
        view.findViewById(R.id.category_tutorial).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsCategoriesFragment.this.b(view2);
            }
        });
        view.findViewById(R.id.category_tutorial_update).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsCategoriesFragment.this.c(view2);
            }
        });
        view.findViewById(R.id.category_help).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SettingsCategoriesFragment.this.d(view2);
            }
        });
        view.findViewById(R.id.category_about).setOnClickListener(new OpenSettingsFragmentListener(CATEGORY_ABOUT));
        Intent intent = requireActivity().getIntent();
        if (intent.hasExtra(SettingsActivity.EXTRA_THEME)) {
            intent.removeExtra(SettingsActivity.EXTRA_THEME);
            setTheme();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(Activity activity, DialogInterface dialogInterface, int i) {
        int i2 = i - 1;
        androidx.appcompat.app.g.e(i2);
        androidx.preference.j.a(activity).edit().putInt(SettingsFragment.SETTINGS_THEME, i2).apply();
        activity.recreate();
    }
}
