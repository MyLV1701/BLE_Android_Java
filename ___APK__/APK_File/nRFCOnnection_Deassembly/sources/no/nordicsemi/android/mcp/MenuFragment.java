package no.nordicsemi.android.mcp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.definitions.DefinitionsActivity;
import no.nordicsemi.android.mcp.favorite.ManageFavoritesActivity;
import no.nordicsemi.android.mcp.info.DeviceInfoActivity;
import no.nordicsemi.android.mcp.server.ServerActivity;
import no.nordicsemi.android.mcp.settings.SettingsActivity;
import no.nordicsemi.android.mcp.tips.TipsAndTricksActivity;
import no.nordicsemi.android.mcp.tips.TipsAndTricksCategoriesFragment;

/* loaded from: classes.dex */
public class MenuFragment extends Fragment {
    public static final String SIS_SELECTED_VIEW = "SELECTED_VIEW";
    private MenuListener mListener;
    private int mSelectedViewId;

    /* loaded from: classes.dex */
    public interface MenuListener {
        void onMenuItemClicked(Intent intent);

        void onMenuItemClicked(Fragment fragment);
    }

    private int getShownTipsCount() {
        return PreferenceManager.getDefaultSharedPreferences(requireContext()).getInt(TipsAndTricksCategoriesFragment.PREFS_TIPS_SHOWN_COUNT, 0);
    }

    private void initSelection(View view) {
        View findViewById = view.findViewById(this.mSelectedViewId);
        if (findViewById != null) {
            findViewById.setSelected(true);
            findViewById.setEnabled(false);
        }
    }

    private Intent newActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(getActivity(), cls);
        intent.addFlags(65536);
        return intent;
    }

    private void toggleSelection(View view, int i) {
        View findViewById = view.findViewById(this.mSelectedViewId);
        if (findViewById != null) {
            findViewById.setSelected(false);
            findViewById.setEnabled(true);
        }
        View findViewById2 = view.findViewById(i);
        if (findViewById2 != null) {
            findViewById2.setSelected(true);
            findViewById2.setEnabled(false);
            this.mSelectedViewId = findViewById2.getId();
        }
    }

    public /* synthetic */ void a(View view, View view2) {
        toggleSelection(view, R.id.action_scanner);
        this.mListener.onMenuItemClicked(new TabsFragment());
    }

    public /* synthetic */ void b(View view) {
        this.mListener.onMenuItemClicked(newActivity(ManageFavoritesActivity.class));
    }

    public /* synthetic */ void c(View view) {
        this.mListener.onMenuItemClicked(newActivity(DefinitionsActivity.class));
    }

    public /* synthetic */ void d(View view) {
        this.mListener.onMenuItemClicked(newActivity(DeviceInfoActivity.class));
    }

    public /* synthetic */ void e(View view) {
        this.mListener.onMenuItemClicked(newActivity(SettingsActivity.class));
    }

    public /* synthetic */ void f(View view) {
        String str;
        try {
            str = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
        } catch (Exception unused) {
            str = "";
        }
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:mag@nordicsemi.no"));
        intent.putExtra("android.intent.extra.SUBJECT", "[nRF Connect v" + str + "] Feedback");
        this.mListener.onMenuItemClicked(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (MenuListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement MenuListener");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mSelectedViewId = bundle.getInt(SIS_SELECTED_VIEW);
        } else {
            this.mSelectedViewId = R.id.action_scanner;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final boolean z = false;
        final View inflate = layoutInflater.inflate(R.layout.fragment_menu, viewGroup, false);
        inflate.findViewById(R.id.action_scanner).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.a(inflate, view);
            }
        });
        inflate.findViewById(R.id.action_gatt_server).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.a(view);
            }
        });
        inflate.findViewById(R.id.action_manage_favorites).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.b(view);
            }
        });
        inflate.findViewById(R.id.action_user_data).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.c(view);
            }
        });
        initSelection(inflate);
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType(LogContract.Session.CONTENT_TYPE);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        if (getContext() != null && intent.resolveActivity(getContext().getPackageManager()) != null) {
            z = true;
        }
        ((TextView) inflate.findViewById(R.id.action_open_logger)).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.a(z, intent, view);
            }
        });
        inflate.findViewById(R.id.action_device_information).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.d(view);
            }
        });
        final TextView textView = (TextView) inflate.findViewById(R.id.action_tips_and_tricks);
        textView.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.a(textView, view);
            }
        });
        textView.getCompoundDrawables()[2].setLevel(13 - getShownTipsCount());
        inflate.findViewById(R.id.action_settings).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.e(view);
            }
        });
        inflate.findViewById(R.id.action_feedback).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenuFragment.this.f(view);
            }
        });
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(SIS_SELECTED_VIEW, this.mSelectedViewId);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void reset() {
        toggleSelection(getView(), R.id.action_scanner);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatusBarHeight(int i) {
        View findViewById = getView().findViewById(R.id.logo);
        int paddingBottom = findViewById.getPaddingBottom();
        findViewById.setPadding(0, i + paddingBottom, 0, paddingBottom);
    }

    public /* synthetic */ void a(View view) {
        this.mListener.onMenuItemClicked(newActivity(ServerActivity.class));
    }

    public /* synthetic */ void a(boolean z, Intent intent, View view) {
        if (z) {
            this.mListener.onMenuItemClicked(intent);
            return;
        }
        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=no.nordicsemi.android.log"));
        intent2.setFlags(268435456);
        try {
            this.mListener.onMenuItemClicked(intent2);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getActivity(), R.string.no_application_play, 0).show();
        }
    }

    public /* synthetic */ void a(TextView textView, View view) {
        textView.getCompoundDrawables()[2].setLevel(0);
        this.mListener.onMenuItemClicked(newActivity(TipsAndTricksActivity.class));
    }
}
