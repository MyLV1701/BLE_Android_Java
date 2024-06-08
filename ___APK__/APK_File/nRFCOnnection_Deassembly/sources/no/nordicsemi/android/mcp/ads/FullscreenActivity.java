package no.nordicsemi.android.mcp.ads;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class FullscreenActivity extends Activity {
    public /* synthetic */ void a(View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://devzone.nordicsemi.com/blogs/922/nrf-connect-v10-release-candidate/"));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.no_application, 1).show();
        }
    }

    public /* synthetic */ void b(View view) {
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ad);
        findViewById(R.id.action_learn_more).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ads.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullscreenActivity.this.a(view);
            }
        });
        findViewById(R.id.action_done).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ads.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FullscreenActivity.this.b(view);
            }
        });
    }
}
