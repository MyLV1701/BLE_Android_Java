package no.nordicsemi.android.mcp.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.d;
import androidx.preference.Preference;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class AboutPreference extends Preference {
    public AboutPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public /* synthetic */ void b(androidx.appcompat.app.d dVar, View view) {
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/NordicTweets")));
        dVar.dismiss();
    }

    public /* synthetic */ void c(androidx.appcompat.app.d dVar, View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("linkedin://company/23302"));
        if (getContext().getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("https://touch.www.linkedin.com/?dl=no#company/23302"));
        }
        getContext().startActivity(intent);
        dVar.dismiss();
    }

    public /* synthetic */ void d(androidx.appcompat.app.d dVar, View view) {
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com/user/NordicSemi")));
        dVar.dismiss();
    }

    public /* synthetic */ void e(androidx.appcompat.app.d dVar, View view) {
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://devzone.nordicsemi.com/questions/")));
        dVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onClick() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_about, (ViewGroup) null);
        d.a aVar = new d.a(getContext());
        aVar.b(inflate);
        aVar.c(R.string.ok, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.e
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final androidx.appcompat.app.d a2 = aVar.a();
        inflate.findViewById(R.id.action_facebook).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutPreference.this.a(a2, view);
            }
        });
        inflate.findViewById(R.id.action_twitter).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutPreference.this.b(a2, view);
            }
        });
        inflate.findViewById(R.id.action_linkedin).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutPreference.this.c(a2, view);
            }
        });
        inflate.findViewById(R.id.action_youtube).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutPreference.this.d(a2, view);
            }
        });
        inflate.findViewById(R.id.action_devzone).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.settings.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutPreference.this.e(a2, view);
            }
        });
        try {
            ((TextView) inflate.findViewById(R.id.version)).setText(getContext().getString(R.string.version, getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName));
        } catch (Exception unused) {
        }
        a2.show();
    }

    public AboutPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public /* synthetic */ void a(androidx.appcompat.app.d dVar, View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("fb://page/227282803964174"));
        if (getContext().getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/nordicsemiconductor"));
        }
        getContext().startActivity(intent);
        dVar.dismiss();
    }
}
