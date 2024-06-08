package com.androidplot.util;

import java.util.List;

/* loaded from: classes.dex */
public class ListOrganizer<ElementType> implements ZIndexable<ElementType> {
    private List<ElementType> list;

    public ListOrganizer(List<ElementType> list) {
        this.list = list;
    }

    public void addToBottom(ElementType elementtype) {
        this.list.add(0, elementtype);
    }

    public void addToTop(ElementType elementtype) {
        List<ElementType> list = this.list;
        list.add(list.size(), elementtype);
    }

    @Override // com.androidplot.util.ZIndexable
    public List<ElementType> elements() {
        return this.list;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveAbove(ElementType elementtype, ElementType elementtype2) {
        if (elementtype != elementtype2) {
            this.list.remove(elementtype);
            this.list.add(this.list.indexOf(elementtype2) + 1, elementtype);
            return true;
        }
        throw new IllegalArgumentException("Illegal argument to moveAbove(A, B); A cannot be equal to B.");
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveBeneath(ElementType elementtype, ElementType elementtype2) {
        if (elementtype != elementtype2) {
            this.list.remove(elementtype);
            this.list.add(this.list.indexOf(elementtype2), elementtype);
            return true;
        }
        throw new IllegalArgumentException("Illegal argument to moveBeaneath(A, B); A cannot be equal to B.");
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveDown(ElementType elementtype) {
        int indexOf = this.list.indexOf(elementtype);
        if (indexOf == -1) {
            return false;
        }
        if (indexOf <= 0) {
            return true;
        }
        return moveBeneath(elementtype, this.list.get(indexOf - 1));
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveToBottom(ElementType elementtype) {
        this.list.remove(elementtype);
        this.list.add(0, elementtype);
        return true;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveToTop(ElementType elementtype) {
        if (!this.list.remove(elementtype)) {
            return false;
        }
        List<ElementType> list = this.list;
        list.add(list.size(), elementtype);
        return true;
    }

    @Override // com.androidplot.util.ZIndexable
    public boolean moveUp(ElementType elementtype) {
        int indexOf = this.list.indexOf(elementtype);
        if (indexOf == -1) {
            return false;
        }
        if (indexOf >= this.list.size() - 1) {
            return true;
        }
        return moveAbove(elementtype, this.list.get(indexOf + 1));
    }
}
