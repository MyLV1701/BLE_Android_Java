package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* loaded from: classes.dex */
final class u {

    /* renamed from: a, reason: collision with root package name */
    private TextView f1094a;

    /* renamed from: b, reason: collision with root package name */
    private TextClassifier f1095b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(TextView textView) {
        a.f.k.h.a(textView);
        this.f1094a = textView;
    }

    public void a(TextClassifier textClassifier) {
        this.f1095b = textClassifier;
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f1095b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f1094a.getContext().getSystemService(TextClassificationManager.class);
        if (textClassificationManager != null) {
            return textClassificationManager.getTextClassifier();
        }
        return TextClassifier.NO_OP;
    }
}
