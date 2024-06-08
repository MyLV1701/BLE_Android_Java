package com.androidplot.ui;

import android.graphics.RectF;
import com.androidplot.ui.TableModel;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DynamicTableModel extends TableModel {
    private Float cellHeight;
    private Float cellWidth;
    private TableModel.CellSizingMethod columnSizingMethod;
    private int numColumns;
    private int numRows;
    private TableModel.CellSizingMethod rowSizingMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.ui.DynamicTableModel$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$ui$TableModel$Axis;
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
            $SwitchMap$com$androidplot$ui$TableModel$Axis = new int[TableModel.Axis.values().length];
            try {
                $SwitchMap$com$androidplot$ui$TableModel$Axis[TableModel.Axis.ROW.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$androidplot$ui$TableModel$Axis[TableModel.Axis.COLUMN.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TableModelIterator implements Iterator<RectF> {
        private int calculatedColumns;
        private int calculatedNumElements;
        private int calculatedRows;
        private DynamicTableModel dynamicTableModel;
        private RectF lastElementRect;
        private TableOrder order;
        private RectF tableRect;
        private int totalElements;
        private boolean isOk = true;
        int lastColumn = 0;
        int lastRow = 0;
        int lastElement = 0;

        public TableModelIterator(DynamicTableModel dynamicTableModel, RectF rectF, int i) {
            this.dynamicTableModel = dynamicTableModel;
            this.tableRect = rectF;
            this.totalElements = i;
            this.order = dynamicTableModel.getOrder();
            if (dynamicTableModel.getNumColumns() == 0 && dynamicTableModel.getNumRows() >= 1) {
                this.calculatedRows = dynamicTableModel.getNumRows();
                double d2 = i / this.calculatedRows;
                Double.isNaN(d2);
                this.calculatedColumns = new Float(d2 + 0.5d).intValue();
            } else if (dynamicTableModel.getNumRows() == 0 && dynamicTableModel.getNumColumns() >= 1) {
                this.calculatedColumns = dynamicTableModel.getNumColumns();
                double d3 = i / this.calculatedColumns;
                Double.isNaN(d3);
                this.calculatedRows = new Float(d3 + 0.5d).intValue();
            } else if (dynamicTableModel.getNumColumns() == 0 && dynamicTableModel.getNumRows() == 0) {
                this.calculatedRows = 1;
                this.calculatedColumns = i;
            } else {
                this.calculatedRows = dynamicTableModel.getNumRows();
                this.calculatedColumns = dynamicTableModel.getNumColumns();
            }
            this.calculatedNumElements = this.calculatedRows * this.calculatedColumns;
            this.lastElementRect = dynamicTableModel.getCellRect(rectF, i);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.isOk && this.lastElement < this.calculatedNumElements;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public RectF next() {
            if (hasNext()) {
                int i = this.lastElement;
                if (i == 0) {
                    this.lastElement = i + 1;
                    return this.lastElementRect;
                }
                RectF rectF = new RectF(this.lastElementRect);
                int i2 = AnonymousClass1.$SwitchMap$com$androidplot$ui$TableOrder[this.order.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (this.dynamicTableModel.getNumRows() > 0 && this.lastRow >= this.dynamicTableModel.getNumRows() - 1) {
                            rectF.offsetTo(this.lastElementRect.right, this.tableRect.top);
                            this.lastRow = 0;
                            this.lastColumn++;
                        } else {
                            RectF rectF2 = this.lastElementRect;
                            rectF.offsetTo(rectF2.left, rectF2.bottom);
                            this.lastRow++;
                        }
                    } else {
                        this.isOk = false;
                        throw new IllegalArgumentException();
                    }
                } else if (this.dynamicTableModel.getNumColumns() > 0 && this.lastColumn >= this.dynamicTableModel.getNumColumns() - 1) {
                    rectF.offsetTo(this.tableRect.left, this.lastElementRect.bottom);
                    this.lastColumn = 0;
                    this.lastRow++;
                } else {
                    RectF rectF3 = this.lastElementRect;
                    rectF.offsetTo(rectF3.right, rectF3.top);
                    this.lastColumn++;
                }
                this.lastElement++;
                this.lastElementRect = rectF;
                return rectF;
            }
            this.isOk = false;
            throw new IndexOutOfBoundsException();
        }
    }

    public DynamicTableModel(int i, int i2) {
        this(i, i2, TableOrder.ROW_MAJOR);
    }

    private float calculateCellSize(RectF rectF, TableModel.Axis axis, int i) {
        int i2;
        float height;
        int i3 = AnonymousClass1.$SwitchMap$com$androidplot$ui$TableModel$Axis[axis.ordinal()];
        if (i3 == 1) {
            i2 = this.numRows;
            height = rectF.height();
        } else if (i3 != 2) {
            height = 0.0f;
            i2 = 0;
        } else {
            i2 = this.numColumns;
            height = rectF.width();
        }
        return height / (i2 != 0 ? i2 : i);
    }

    public RectF getCellRect(RectF rectF, int i) {
        RectF rectF2 = new RectF();
        rectF2.left = rectF.left;
        rectF2.top = rectF.top;
        rectF2.bottom = rectF.top + calculateCellSize(rectF, TableModel.Axis.ROW, i);
        rectF2.right = rectF.left + calculateCellSize(rectF, TableModel.Axis.COLUMN, i);
        return rectF2;
    }

    public int getNumColumns() {
        return this.numColumns;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public void setNumColumns(int i) {
        this.numColumns = i;
    }

    public void setNumRows(int i) {
        this.numRows = i;
    }

    public DynamicTableModel(int i, int i2, TableOrder tableOrder) {
        super(tableOrder);
        this.numColumns = i;
        this.numRows = i2;
    }

    @Override // com.androidplot.ui.TableModel
    public TableModelIterator getIterator(RectF rectF, int i) {
        return new TableModelIterator(this, rectF, i);
    }
}
