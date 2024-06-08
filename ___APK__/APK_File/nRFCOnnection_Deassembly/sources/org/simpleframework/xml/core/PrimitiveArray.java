package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

/* loaded from: classes.dex */
class PrimitiveArray implements Converter {
    private final Type entry;
    private final ArrayFactory factory;
    private final String parent;
    private final Primitive root;
    private final Type type;

    public PrimitiveArray(Context context, Type type, Type type2, String str) {
        this.factory = new ArrayFactory(context, type);
        this.root = new Primitive(context, type2);
        this.parent = str;
        this.entry = type2;
        this.type = type;
    }

    private boolean isOverridden(OutputNode outputNode, Object obj) {
        return this.factory.setOverride(this.entry, obj, outputNode);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) {
        Instance arrayFactory = this.factory.getInstance(inputNode);
        Object instance = arrayFactory.getInstance();
        return !arrayFactory.isReference() ? read(inputNode, instance) : instance;
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) {
        Instance arrayFactory = this.factory.getInstance(inputNode);
        if (arrayFactory.isReference()) {
            return true;
        }
        arrayFactory.setInstance(null);
        return validate(inputNode, arrayFactory.getType());
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            OutputNode child = outputNode.getChild(this.parent);
            if (child == null) {
                return;
            }
            write(child, obj, i);
        }
    }

    private void write(OutputNode outputNode, Object obj, int i) {
        Object obj2 = Array.get(obj, i);
        if (obj2 == null || isOverridden(outputNode, obj2)) {
            return;
        }
        this.root.write(outputNode, obj2);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) {
        int length = Array.getLength(obj);
        int i = 0;
        while (true) {
            Position position = inputNode.getPosition();
            InputNode next = inputNode.getNext();
            if (next == null) {
                return obj;
            }
            if (i < length) {
                Array.set(obj, i, this.root.read(next));
                i++;
            } else {
                throw new ElementException("Array length missing or incorrect for %s at %s", this.type, position);
            }
        }
    }

    private boolean validate(InputNode inputNode, Class cls) {
        while (true) {
            InputNode next = inputNode.getNext();
            if (next == null) {
                return true;
            }
            this.root.validate(next);
        }
    }
}
