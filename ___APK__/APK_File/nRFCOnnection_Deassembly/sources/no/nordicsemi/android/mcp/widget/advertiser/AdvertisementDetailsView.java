package no.nordicsemi.android.mcp.widget.advertiser;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.d;
import java.lang.ref.WeakReference;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.advertiser.AdvertiserActionListener;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.widget.ViewAnimator;

/* loaded from: classes.dex */
public class AdvertisementDetailsView extends LinearLayout {
    private WeakReference<AdvertiserActionListener> mActionListener;
    private Button mCloneAction;
    private Button mEditAction;
    private long mId;
    private Button mOpenAction;
    private Uri mUri;

    /* loaded from: classes.dex */
    private static class ChoiceListener implements View.OnClickListener {
        private DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.advertiser.AdvertisementDetailsView.ChoiceListener.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                DataUnion dataUnion = ChoiceListener.this.mDataUnion;
                dataUnion.setSelected(i);
                DataUnion.Data selectedData = dataUnion.getSelectedData();
                SpannableString spannableString = new SpannableString(ChoiceListener.this.mView.getResources().getString(R.string.key_value, selectedData.key, selectedData.value));
                spannableString.setSpan(new ForegroundColorSpan(ChoiceListener.this.mView.getResources().getColor(R.color.variant)), 0, selectedData.key.length() + 1, 0);
                ChoiceListener.this.mView.setText(spannableString);
                ChoiceListener.this.mAnimator.recalculateExpandableViewHeight();
            }
        };
        private final ViewAnimator mAnimator;
        private final DataUnion mDataUnion;
        private final TextView mView;

        ChoiceListener(TextView textView, DataUnion dataUnion, ViewAnimator viewAnimator) {
            this.mView = textView;
            this.mDataUnion = dataUnion;
            this.mAnimator = viewAnimator;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DataUnion dataUnion = this.mDataUnion;
            d.a aVar = new d.a(view.getContext());
            aVar.c(R.string.choice_selector_title);
            aVar.a(dataUnion.getKeys(), dataUnion.getSelectedIndex(), this.clickListener);
            aVar.c();
        }
    }

    public AdvertisementDetailsView(Context context) {
        super(context);
        setOrientation(1);
    }

    private void setupCloneAction() {
        this.mCloneAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.advertiser.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdvertisementDetailsView.this.a(view);
            }
        });
    }

    private void setupEditAction() {
        this.mEditAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.advertiser.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdvertisementDetailsView.this.b(view);
            }
        });
    }

    private void setupOpenAction() {
        this.mOpenAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.advertiser.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdvertisementDetailsView.this.c(view);
            }
        });
    }

    private void setupView() {
        this.mOpenAction = (Button) findViewById(R.id.action_visit);
        this.mCloneAction = (Button) findViewById(R.id.action_clone);
        this.mEditAction = (Button) findViewById(R.id.action_edit);
        setupOpenAction();
        setupCloneAction();
        setupEditAction();
    }

    public /* synthetic */ void a(View view) {
        this.mActionListener.get().onCloneAdvertisement(this.mId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int assign(long j, AdvData advData, ViewAnimator viewAnimator) {
        this.mId = j;
        int i = 0;
        while (true) {
            if (i >= getChildCount() - 1) {
                break;
            }
            TextView textView = (TextView) getChildAt(i);
            if (textView.getVisibility() == 8) {
                break;
            }
            textView.setVisibility(8);
            textView.setText((CharSequence) null);
            textView.setOnClickListener(null);
            textView.setClickable(false);
            i++;
        }
        for (int i2 = 0; i2 < advData.getAllInfo().size() && i2 < getChildCount() - 1; i2++) {
            DataUnion info = advData.getInfo(i2);
            DataUnion.Data selectedData = info.getSelectedData();
            TextView textView2 = (TextView) getChildAt(i2);
            SpannableString spannableString = new SpannableString(getResources().getString(R.string.key_value, selectedData.key, selectedData.value));
            if (info.isMultiple()) {
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.variant)), 0, selectedData.key.length() + 1, 0);
                textView2.setOnClickListener(new ChoiceListener(textView2, info, viewAnimator));
            } else {
                spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, selectedData.key.length() + 1, 0);
                textView2.setOnClickListener(null);
                textView2.setClickable(false);
            }
            textView2.setText(spannableString);
            textView2.setVisibility(0);
            String str = selectedData.uri;
            if (str != null) {
                this.mUri = Uri.parse(str);
            }
        }
        this.mOpenAction.setVisibility(this.mUri != null ? 0 : 8);
        return 0;
    }

    public /* synthetic */ void b(View view) {
        this.mActionListener.get().onStopAdvertisement(this.mId);
        this.mActionListener.get().onEditAdvertisement(this.mId);
    }

    public /* synthetic */ void c(View view) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", this.mUri);
            intent.addFlags(268435456);
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(view.getContext(), R.string.no_uri_application, 0).show();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
    }

    public void setActionListener(WeakReference<AdvertiserActionListener> weakReference) {
        this.mActionListener = weakReference;
    }

    public AdvertisementDetailsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
    }

    public AdvertisementDetailsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
    }
}
