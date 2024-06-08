package org.simpleframework.xml.core;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface Section extends Iterable<String> {
    String getAttribute(String str);

    LabelMap getAttributes();

    Label getElement(String str);

    LabelMap getElements();

    String getName();

    String getPath(String str);

    String getPrefix();

    Section getSection(String str);

    Label getText();

    boolean isSection(String str);
}
