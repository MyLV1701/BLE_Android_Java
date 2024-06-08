package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Iterator;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/* loaded from: classes.dex */
class TextList implements Repeater {
    private final CollectionFactory factory;
    private final Primitive primitive;
    private final Type type = new ClassType(String.class);

    public TextList(Context context, Type type, Label label) {
        this.factory = new CollectionFactory(context, type);
        this.primitive = new Primitive(context, this.type);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) {
        Instance collectionFactory = this.factory.getInstance(inputNode);
        Object instance = collectionFactory.getInstance();
        if (collectionFactory.isReference()) {
            return collectionFactory.getInstance();
        }
        return read(inputNode, instance);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) {
        return true;
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) {
        OutputNode parent = outputNode.getParent();
        Iterator it = ((Collection) obj).iterator();
        while (it.hasNext()) {
            this.primitive.write(parent, it.next());
        }
    }

    @Override // org.simpleframework.xml.core.Repeater, org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) {
        Collection collection = (Collection) obj;
        Object read = this.primitive.read(inputNode);
        if (read != null) {
            collection.add(read);
        }
        return obj;
    }
}
