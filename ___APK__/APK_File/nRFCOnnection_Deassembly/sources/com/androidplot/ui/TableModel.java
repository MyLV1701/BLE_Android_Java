package com.androidplot.ui;

import android.graphics.RectF;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class TableModel {
    private TableOrder order;

    /* loaded from: classes.dex */
    public enum Axis {
        ROW,
        COLUMN
    }

    /* loaded from: classes.dex */
    public enum CellSizingMethod {
        FIXED,
        FILL
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TableModel(TableOrder tableOrder) {
        setOrder(tableOrder);
    }

    public abstract Iterator<RectF> getIterator(RectF rectF, int i);

    public TableOrder getOrder() {
        return this.order;
    }

    public void setOrder(TableOrder tableOrder) {
        this.order = tableOrder;
    }
}
