package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
class InputNodeMap extends LinkedHashMap<String, InputNode> implements NodeMap<InputNode> {
    private final InputNode source;

    /* JADX INFO: Access modifiers changed from: protected */
    public InputNodeMap(InputNode inputNode) {
        this.source = inputNode;
    }

    private void build(EventNode eventNode) {
        for (Attribute attribute : eventNode) {
            InputAttribute inputAttribute = new InputAttribute(this.source, attribute);
            if (!attribute.isReserved()) {
                put((InputNodeMap) inputAttribute.getName(), (String) inputAttribute);
            }
        }
    }

    @Override // org.simpleframework.xml.stream.NodeMap
    public String getName() {
        return this.source.getName();
    }

    @Override // org.simpleframework.xml.stream.NodeMap, java.lang.Iterable
    public Iterator<String> iterator() {
        return keySet().iterator();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode get(String str) {
        return (InputNode) super.get((Object) str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode getNode() {
        return this.source;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode put(String str, String str2) {
        InputAttribute inputAttribute = new InputAttribute(this.source, str, str2);
        if (str != null) {
            put((InputNodeMap) str, (String) inputAttribute);
        }
        return inputAttribute;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.stream.NodeMap
    public InputNode remove(String str) {
        return (InputNode) super.remove((Object) str);
    }

    public InputNodeMap(InputNode inputNode, EventNode eventNode) {
        this.source = inputNode;
        build(eventNode);
    }
}
