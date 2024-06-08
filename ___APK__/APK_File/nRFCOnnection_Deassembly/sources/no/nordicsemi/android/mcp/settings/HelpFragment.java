package no.nordicsemi.android.mcp.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
public class HelpFragment extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        WebView webView = new WebView(getActivity());
        webView.setBackgroundColor(-1);
        webView.setClickable(true);
        webView.setWebViewClient(new WebViewClient() { // from class: no.nordicsemi.android.mcp.settings.HelpFragment.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setFlags(268435456);
                HelpFragment.this.startActivity(intent);
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(false);
        settings.setCacheMode(1);
        webView.loadUrl("file:///android_asset/html/qaa.html");
        return webView;
    }
}
