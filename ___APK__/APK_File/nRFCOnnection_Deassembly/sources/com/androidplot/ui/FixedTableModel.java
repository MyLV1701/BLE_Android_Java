package com.androidplot.ui;

import android.graphics.RectF;
import java.util.Iterator;

/* loaded from: classes.dex */
public class FixedTableModel extends TableModel {
    private float cellHeight;
    private float cellWidth;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.ui.FixedTableModel$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$ui$TableOrder = new int[TableOrder.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$ui$TableOrder[TableOrder.ROW_MAJOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$ui$TableOrder[TableOrder.COLUMN_MAJOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    private class FixedTableModelIterator implements Iterator<RectF> {
        private int lastElement;
        private RectF lastRect;
        private FixedTableModel model;
        private int numElements;
        private RectF tableRect;

        protected FixedTableModelIterator(FixedTableModel fixedTableModel, RectF rectF, int i) {
            this.model = fixedTableModel;
            this.tableRect = rectF;
            this.numElements = i;
            float f2 = rectF.left;
            this.lastRect = new RectF(f2, rectF.top, fixedTableModel.getCellWidth() + f2, rectF.top + fixedTableModel.getCellHeight());
        }

        private boolean isColumnFinished() {
            return this.lastRect.bottom + this.model.getCellHeight() > this.tableRect.height();
        }

        private boolean isRowFinished() {
            return this.lastRect.right + this.model.getCellWidth() > this.tableRect.width();
        }

        private void moveDown() {
            RectF rectF = this.lastRect;
            rectF.offsetTo(rectF.left, rectF.bottom);
        }

        private void moveDownAndBack() {
            RectF rectF = this.lastRect;
            rectF.offsetTo(this.tableRect.left, rectF.bottom);
        }

        private void moveOver() {
            RectF rectF = this.lastRect;
            rectF.offsetTo(rectF.right, rectF.top);
        }

        private void moveOverAndUp() {
            RectF rectF = this.lastRect;
            rectF.offsetTo(rectF.right, this.tableRect.top);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.lastElement < this.numElements && !(isColumnFinished() && isRowFinished());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public RectF next() {
            RectF rectF;
            try {
                if (this.lastElement == 0) {
                    rectF = this.lastRect;
                } else if (this.lastElement < this.numElements) {
                    int i = AnonymousClass1.$SwitchMap$com$androidplot$ui$TableOrder[this.model.getOrder().ordinal()];
                    if (i != 1) {
                        if (i == 2) {
                            if (isRowFinished()) {
                                moveDownAndBack();
                            } else {
                                moveOver();
                            }
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    } else if (isColumnFinished()) {
                        moveOverAndUp();
                    } else {
                        moveDown();
                    }
                    rectF = this.lastRect;
                } else {
                    throw new IndexOutOfBoundsException();
                }
                return rectF;
            } finally {
                this.lastElement++;
            }
        }
    }

    protected FixedTableModel(float f2, float f3, TableOrder tableOrder) {
        super(tableOrder);
        setCellWidth(f2);
        setCellHeight(f3);
    }

    public float getCellHeight() {
        return this.cellHeight;
    }

    public float getCellWidth() {
        return this.cellWidth;
    }

    @Override // com.androidplot.ui.TableModel
    public Iterator<RectF> getIterator(RectF rectF, int i) {
        return new FixedTableModelIterator(this, rectF, i);
    }

    public void setCellHeight(float f2) {
        this.cellHeight = f2;
    }

    public void setCellWidth(float f2) {
        this.cellWidth = f2;
    }
}
