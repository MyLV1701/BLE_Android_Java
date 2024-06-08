package com.androidplot.util;

import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class ZHash<KeyType, ValueType> implements ZIndexable<KeyType> {
    private HashMap<KeyType, ValueType> hash = new HashMap<>();
    private ZLinkedList<KeyType> zlist = new ZLinkedList<>();

    public synchronized void addToBottom(KeyType keytype, ValueType valuetype) {
        if (this.hash.containsKey(keytype)) {
            this.hash.put(keytype, valuetype);
        } else {
            this.hash.put(keytype, valuetype);
            this.zlist.addToBottom(keytype);
        }
    }

    public synchronized void addToTop(KeyType keytype, ValueType valuetype) {
        if (this.hash.containsKey(keytype)) {
            this.hash.put(keytype, valuetype);
        } else {
            this.hash.put(keytype, valuetype);
            this.zlist.addToTop(keytype);
        }
    }

    @Override // com.androidplot.util.ZIndexable
    public List<KeyType> elements() {
        return this.zlist;
    }

    public ValueType get(KeyType keytype) {
        return this.hash.get(keytype);
    }

    public List<KeyType> getKeysAsList() {
        return this.zlist;
    }

    public List<KeyType> keys() {
        return elements();
    }

    @Override // com.androidplot.util.ZIndexable
    public synchronized boolean moveAbove(KeyType keytype, KeyType keytype2) {
        if (keytype != keytype2) {
            if (this.hash.containsKey(keytype2) && this.hash.containsKey(keytype)) {
                return this.zlist.moveAbove(keytype, keytype2);
            }
            return false;
        }
        throw new IllegalArgumentException("Illegal argument to moveAbove(A, B); A cannot be equal to B.");
    }

    @Override // com.androidplot.util.ZIndexable
    public synchronized boolean moveBeneath(KeyType keytype, KeyType keytype2) {
        if (keytype != keytype2) {
            if (this.hash.containsKey(keytype2) && this.hash.containsKey(keytype)) {
                return this.zlist.moveBeneath(keytype, keytype2);
            }
            return false;
        }
        throw new IllegalArgumentException("Illegal argument to moveBeaneath(A, B); A cannot be equal to B.");
    }

    @Override // com.androidplot.util.ZIndexable
    public synchronized boolean moveDown(KeyType keytype) {
        if (!this.hash.containsKey(keytype)) {
            return false;
        }
        return this.zlist.moveDown(keytype);
    }

    @Override // com.androidplot.util.ZIndexable
    public synchronized boolean moveToBottom(KeyType keytype) {
        if (!this.hash.containsKey(keytype)) {
            return false;
        }
        return this.zlist.moveToBottom(keytype);
    }

    @Override // com.androidplot.util.ZIndexable
    public synchronized boolean moveToTop(KeyType keytype) {
        if (!this.hash.containsKey(keytype)) {
            return false;
        }
        return this.zlist.moveToTop(keytype);
    }

    @Override // com.androidplot.util.ZIndexable
    public synchronized boolean moveUp(KeyType keytype) {
        if (!this.hash.containsKey(keytype)) {
            return false;
        }
        return this.zlist.moveUp(keytype);
    }

    public synchronized boolean remove(KeyType keytype) {
        if (!this.hash.containsKey(keytype)) {
            return false;
        }
        this.hash.remove(keytype);
        this.zlist.remove(keytype);
        return true;
    }

    public int size() {
        return this.zlist.size();
    }
}
