package no.nordicsemi.android.mcp.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/* loaded from: classes.dex */
public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private View edit;
    private RadioButton radio;

    public CheckableLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        RadioButton radioButton = this.radio;
        return radioButton != null && radioButton.isChecked();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.radio = (RadioButton) findViewById(R.id.checkbox);
        if (this.radio != null) {
            this.edit = findViewById(no.nordicsemi.android.mcp.R.id.edit);
            return;
        }
        throw new UnsupportedOperationException("CheckableListView must have a child with id android.R.id.checkbox");
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        RadioButton radioButton = this.radio;
        if (radioButton != null) {
            radioButton.setChecked(z);
        }
        View view = this.edit;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        RadioButton radioButton = this.radio;
        if (radioButton != null) {
            radioButton.toggle();
        }
        View view = this.edit;
        if (view != null) {
            view.setVisibility(view.getVisibility() == 8 ? 0 : 8);
        }
    }
}
