package com.androidplot.ui;

import com.androidplot.Series;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class SeriesAndFormatterList<SeriesType extends Series, FormatterType> {
    private LinkedList<SeriesType> seriesList = new LinkedList<>();
    private LinkedList<FormatterType> formatterList = new LinkedList<>();

    public boolean add(SeriesType seriestype, FormatterType formattertype) {
        if (seriestype != null && formattertype != null) {
            if (this.seriesList.contains(seriestype)) {
                return false;
            }
            this.seriesList.add(seriestype);
            this.formatterList.add(formattertype);
            return true;
        }
        throw new IllegalArgumentException("series and formatter must not be null.");
    }

    public boolean contains(SeriesType seriestype) {
        return this.seriesList.contains(seriestype);
    }

    public FormatterType getFormatter(SeriesType seriestype) {
        return this.formatterList.get(this.seriesList.indexOf(seriestype));
    }

    public List<FormatterType> getFormatterList() {
        return this.formatterList;
    }

    public SeriesType getSeries(int i) {
        return this.seriesList.get(i);
    }

    public List<SeriesType> getSeriesList() {
        return this.seriesList;
    }

    public boolean remove(SeriesType seriestype) {
        int indexOf = this.seriesList.indexOf(seriestype);
        if (indexOf < 0) {
            return false;
        }
        this.seriesList.remove(indexOf);
        this.formatterList.remove(indexOf);
        return true;
    }

    public FormatterType setFormatter(SeriesType seriestype, FormatterType formattertype) {
        return this.formatterList.set(this.seriesList.indexOf(seriestype), formattertype);
    }

    public int size() {
        return this.seriesList.size();
    }

    public FormatterType getFormatter(int i) {
        return this.formatterList.get(i);
    }
}
