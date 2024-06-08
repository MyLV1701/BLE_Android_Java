package org.simpleframework.xml.stream;

/* loaded from: classes.dex */
public interface InputNode extends Node {
    InputNode getAttribute(String str);

    NodeMap<InputNode> getAttributes();

    InputNode getNext();

    InputNode getNext(String str);

    @Override // org.simpleframework.xml.stream.Node
    InputNode getParent();

    Position getPosition();

    String getPrefix();

    String getReference();

    Object getSource();

    boolean isElement();

    boolean isEmpty();

    boolean isRoot();

    void skip();
}
