package org.simpleframework.xml.core;

import java.util.Iterator;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NamespaceMap;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Composite implements Converter {
    private final Context context;
    private final Criteria criteria;
    private final ObjectFactory factory;
    private final Primitive primitive;
    private final Revision revision;
    private final Type type;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Builder {
        protected final Composite composite;
        protected final Criteria criteria;
        protected final Schema schema;
        protected final Instance value;

        public Builder(Composite composite, Criteria criteria, Schema schema, Instance instance) {
            this.composite = composite;
            this.criteria = criteria;
            this.schema = schema;
            this.value = instance;
        }

        public Object read(InputNode inputNode) {
            Object instance = this.value.getInstance();
            Section section = this.schema.getSection();
            this.value.setInstance(instance);
            this.composite.readVersion(inputNode, instance, this.schema);
            this.composite.readText(inputNode, instance, section);
            this.composite.readAttributes(inputNode, instance, section);
            this.composite.readElements(inputNode, instance, section);
            this.criteria.commit(instance);
            return instance;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Injector extends Builder {
        private Object readInject(InputNode inputNode) {
            Object instantiator = this.schema.getInstantiator().getInstance(this.criteria);
            this.value.setInstance(instantiator);
            this.criteria.commit(instantiator);
            return instantiator;
        }

        @Override // org.simpleframework.xml.core.Composite.Builder
        public Object read(InputNode inputNode) {
            Section section = this.schema.getSection();
            this.composite.readVersion(inputNode, (Object) null, this.schema);
            this.composite.readText(inputNode, null, section);
            this.composite.readAttributes(inputNode, null, section);
            this.composite.readElements(inputNode, null, section);
            return readInject(inputNode);
        }

        private Injector(Composite composite, Criteria criteria, Schema schema, Instance instance) {
            super(composite, criteria, schema, instance);
        }
    }

    public Composite(Context context, Type type) {
        this(context, type, null);
    }

    private boolean isOverridden(OutputNode outputNode, Object obj, Type type) {
        return this.factory.setOverride(type, obj, outputNode);
    }

    private void readAttribute(InputNode inputNode, Object obj, Section section, LabelMap labelMap) {
        String attribute = section.getAttribute(inputNode.getName());
        Label label = labelMap.getLabel(attribute);
        if (label == null) {
            Position position = inputNode.getPosition();
            Class type = this.context.getType(this.type, obj);
            if (labelMap.isStrict(this.context) && this.revision.isEqual()) {
                throw new AttributeException("Attribute '%s' does not have a match in %s at %s", attribute, type, position);
            }
            return;
        }
        readInstance(inputNode, obj, label);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readAttributes(InputNode inputNode, Object obj, Section section) {
        NodeMap<InputNode> attributes = inputNode.getAttributes();
        LabelMap attributes2 = section.getAttributes();
        Iterator<String> it = attributes.iterator();
        while (it.hasNext()) {
            InputNode attribute = inputNode.getAttribute(it.next());
            if (attribute != null) {
                readAttribute(attribute, obj, section, attributes2);
            }
        }
        validate(inputNode, attributes2, obj);
    }

    private void readElement(InputNode inputNode, Object obj, Section section, LabelMap labelMap) {
        String path = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(path);
        if (label == null) {
            label = this.criteria.resolve(path);
        }
        if (label == null) {
            Position position = inputNode.getPosition();
            Class type = this.context.getType(this.type, obj);
            if (labelMap.isStrict(this.context) && this.revision.isEqual()) {
                throw new ElementException("Element '%s' does not have a match in %s at %s", path, type, position);
            }
            inputNode.skip();
            return;
        }
        readUnion(inputNode, obj, labelMap, label);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readElements(InputNode inputNode, Object obj, Section section) {
        LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (next != null) {
            Section section2 = section.getSection(next.getName());
            if (section2 != null) {
                readSection(next, obj, section2);
            } else {
                readElement(next, obj, section, elements);
            }
            next = inputNode.getNext();
        }
        validate(inputNode, elements, obj);
    }

    private Object readInstance(InputNode inputNode, Object obj, Label label) {
        Object readVariable = readVariable(inputNode, obj, label);
        if (readVariable == null) {
            Position position = inputNode.getPosition();
            Class type = this.context.getType(this.type, obj);
            if (label.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Empty value for %s in %s at %s", label, type, position);
            }
        } else if (readVariable != label.getEmpty(this.context)) {
            this.criteria.set(label, readVariable);
        }
        return readVariable;
    }

    private Object readPrimitive(InputNode inputNode, Instance instance) {
        Class type = instance.getType();
        Object read = this.primitive.read(inputNode, type);
        if (type != null) {
            instance.setInstance(read);
        }
        return read;
    }

    private Object readResolve(InputNode inputNode, Object obj, Caller caller) {
        if (obj == null) {
            return obj;
        }
        Position position = inputNode.getPosition();
        Object resolve = caller.resolve(obj);
        Class type = this.type.getType();
        Class<?> cls = resolve.getClass();
        if (type.isAssignableFrom(cls)) {
            return resolve;
        }
        throw new ElementException("Type %s does not match %s at %s", cls, type, position);
    }

    private void readSection(InputNode inputNode, Object obj, Section section) {
        readText(inputNode, obj, section);
        readAttributes(inputNode, obj, section);
        readElements(inputNode, obj, section);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readText(InputNode inputNode, Object obj, Section section) {
        Label text = section.getText();
        if (text != null) {
            readInstance(inputNode, obj, text);
        }
    }

    private void readUnion(InputNode inputNode, Object obj, LabelMap labelMap, Label label) {
        Object readInstance = readInstance(inputNode, obj, label);
        for (String str : label.getPaths()) {
            labelMap.getLabel(str);
        }
        if (label.isInline()) {
            this.criteria.set(label, readInstance);
        }
    }

    private Object readVariable(InputNode inputNode, Object obj, Label label) {
        Object obj2;
        Converter converter = label.getConverter(this.context);
        if (label.isCollection()) {
            Variable variable = this.criteria.get(label);
            Contact contact = label.getContact();
            if (variable != null) {
                return converter.read(inputNode, variable.getValue());
            }
            if (obj != null && (obj2 = contact.get(obj)) != null) {
                return converter.read(inputNode, obj2);
            }
        }
        return converter.read(inputNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readVersion(InputNode inputNode, Object obj, Schema schema) {
        Label version = schema.getVersion();
        Class type = this.type.getType();
        if (version != null) {
            InputNode remove = inputNode.getAttributes().remove(version.getName());
            if (remove != null) {
                readVersion(remove, obj, version);
                return;
            }
            Version version2 = this.context.getVersion(type);
            Double valueOf = Double.valueOf(this.revision.getDefault());
            Double valueOf2 = Double.valueOf(version2.revision());
            this.criteria.set(version, valueOf);
            this.revision.compare(valueOf2, valueOf);
        }
    }

    private void validate(InputNode inputNode, LabelMap labelMap, Object obj) {
        Class type = this.context.getType(this.type, obj);
        Position position = inputNode.getPosition();
        Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (next.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", next, type, position);
            }
            Object empty = next.getEmpty(this.context);
            if (empty != null) {
                this.criteria.set(next, empty);
            }
        }
    }

    private void validateAttribute(InputNode inputNode, Section section, LabelMap labelMap) {
        Position position = inputNode.getPosition();
        String attribute = section.getAttribute(inputNode.getName());
        Label label = labelMap.getLabel(attribute);
        if (label == null) {
            Class type = this.type.getType();
            if (labelMap.isStrict(this.context) && this.revision.isEqual()) {
                throw new AttributeException("Attribute '%s' does not exist for %s at %s", attribute, type, position);
            }
            return;
        }
        validate(inputNode, label);
    }

    private void validateAttributes(InputNode inputNode, Section section) {
        NodeMap<InputNode> attributes = inputNode.getAttributes();
        LabelMap attributes2 = section.getAttributes();
        Iterator<String> it = attributes.iterator();
        while (it.hasNext()) {
            InputNode attribute = inputNode.getAttribute(it.next());
            if (attribute != null) {
                validateAttribute(attribute, section, attributes2);
            }
        }
        validate(inputNode, attributes2);
    }

    private void validateElement(InputNode inputNode, Section section, LabelMap labelMap) {
        String path = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(path);
        if (label == null) {
            label = this.criteria.resolve(path);
        }
        if (label == null) {
            Position position = inputNode.getPosition();
            Class type = this.type.getType();
            if (labelMap.isStrict(this.context) && this.revision.isEqual()) {
                throw new ElementException("Element '%s' does not exist for %s at %s", path, type, position);
            }
            inputNode.skip();
            return;
        }
        validateUnion(inputNode, labelMap, label);
    }

    private void validateElements(InputNode inputNode, Section section) {
        LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (next != null) {
            Section section2 = section.getSection(next.getName());
            if (section2 != null) {
                validateSection(next, section2);
            } else {
                validateElement(next, section, elements);
            }
            next = inputNode.getNext();
        }
        validate(inputNode, elements);
    }

    private void validateSection(InputNode inputNode, Section section) {
        validateAttributes(inputNode, section);
        validateElements(inputNode, section);
    }

    private void validateText(InputNode inputNode, Schema schema) {
        Label text = schema.getText();
        if (text != null) {
            validate(inputNode, text);
        }
    }

    private void validateUnion(InputNode inputNode, LabelMap labelMap, Label label) {
        for (String str : label.getPaths()) {
            labelMap.getLabel(str);
        }
        if (label.isInline()) {
            this.criteria.set(label, null);
        }
        validate(inputNode, label);
    }

    private void writeAttribute(OutputNode outputNode, Object obj, Label label) {
        if (obj != null) {
            label.getDecorator().decorate(outputNode.setAttribute(label.getName(), this.factory.getText(obj)));
        }
    }

    private void writeAttributes(OutputNode outputNode, Object obj, Section section) {
        Iterator<Label> it = section.getAttributes().iterator();
        while (it.hasNext()) {
            Label next = it.next();
            Object obj2 = next.getContact().get(obj);
            Class type = this.context.getType(this.type, obj);
            if (obj2 == null) {
                obj2 = next.getEmpty(this.context);
            }
            if (obj2 == null && next.isRequired()) {
                throw new AttributeException("Value for %s is null in %s", next, type);
            }
            writeAttribute(outputNode, obj2, next);
        }
    }

    private void writeElement(OutputNode outputNode, Object obj, Label label) {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Label label2 = label.getLabel(cls);
            String name = label2.getName();
            Type type = label.getType(cls);
            OutputNode child = outputNode.getChild(name);
            if (!label2.isInline()) {
                writeNamespaces(child, type, label2);
            }
            if (label2.isInline() || !isOverridden(child, obj, type)) {
                Converter converter = label2.getConverter(this.context);
                child.setData(label2.isData());
                writeElement(child, obj, converter);
            }
        }
    }

    private void writeElements(OutputNode outputNode, Object obj, Section section) {
        for (String str : section) {
            Section section2 = section.getSection(str);
            if (section2 != null) {
                writeSection(outputNode.getChild(str), obj, section2);
            } else {
                Label element = section.getElement(section.getPath(str));
                Class type = this.context.getType(this.type, obj);
                if (this.criteria.get(element) != null) {
                    continue;
                } else if (element != null) {
                    writeUnion(outputNode, obj, section, element);
                } else {
                    throw new ElementException("Element '%s' not defined in %s", str, type);
                }
            }
        }
    }

    private void writeNamespaces(OutputNode outputNode, Type type, Label label) {
        label.getDecorator().decorate(outputNode, this.context.getDecorator(type.getType()));
    }

    private Object writeReplace(Object obj) {
        if (obj == null) {
            return obj;
        }
        return this.context.getCaller(obj.getClass()).replace(obj);
    }

    private void writeSection(OutputNode outputNode, Object obj, Section section) {
        NamespaceMap namespaces = outputNode.getNamespaces();
        String prefix = section.getPrefix();
        if (prefix != null) {
            String reference = namespaces.getReference(prefix);
            if (reference != null) {
                outputNode.setReference(reference);
            } else {
                throw new ElementException("Namespace prefix '%s' in %s is not in scope", prefix, this.type);
            }
        }
        writeAttributes(outputNode, obj, section);
        writeElements(outputNode, obj, section);
        writeText(outputNode, obj, section);
    }

    private void writeText(OutputNode outputNode, Object obj, Section section) {
        Label text = section.getText();
        if (text != null) {
            Object obj2 = text.getContact().get(obj);
            Class type = this.context.getType(this.type, obj);
            if (obj2 == null) {
                obj2 = text.getEmpty(this.context);
            }
            if (obj2 == null && text.isRequired()) {
                throw new TextException("Value for %s is null in %s", text, type);
            }
            writeText(outputNode, obj2, text);
        }
    }

    private void writeUnion(OutputNode outputNode, Object obj, Section section, Label label) {
        Object obj2 = label.getContact().get(obj);
        Class type = this.context.getType(this.type, obj);
        if (obj2 == null && label.isRequired()) {
            throw new ElementException("Value for %s is null in %s", label, type);
        }
        Object writeReplace = writeReplace(obj2);
        if (writeReplace != null) {
            writeElement(outputNode, writeReplace, label);
        }
        this.criteria.set(label, writeReplace);
    }

    private void writeVersion(OutputNode outputNode, Object obj, Schema schema) {
        Version revision = schema.getRevision();
        Label version = schema.getVersion();
        if (revision != null) {
            Double valueOf = Double.valueOf(this.revision.getDefault());
            Double valueOf2 = Double.valueOf(revision.revision());
            if (this.revision.compare(valueOf2, valueOf)) {
                if (version.isRequired()) {
                    writeAttribute(outputNode, valueOf2, version);
                    return;
                }
                return;
            }
            writeAttribute(outputNode, valueOf2, version);
        }
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) {
        Instance objectFactory = this.factory.getInstance(inputNode);
        Class type = objectFactory.getType();
        if (objectFactory.isReference()) {
            return objectFactory.getInstance();
        }
        if (this.context.isPrimitive(type)) {
            return readPrimitive(inputNode, objectFactory);
        }
        return read(inputNode, objectFactory, type);
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) {
        Schema schema = this.context.getSchema(obj.getClass());
        Caller caller = schema.getCaller();
        try {
            if (schema.isPrimitive()) {
                this.primitive.write(outputNode, obj);
            } else {
                caller.persist(obj);
                write(outputNode, obj, schema);
            }
        } finally {
            caller.complete(obj);
        }
    }

    public Composite(Context context, Type type, Class cls) {
        this.factory = new ObjectFactory(context, type, cls);
        this.primitive = new Primitive(context, type);
        this.criteria = new Collector();
        this.revision = new Revision();
        this.context = context;
        this.type = type;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) {
        Schema schema = this.context.getSchema(obj.getClass());
        Caller caller = schema.getCaller();
        read(inputNode, obj, schema);
        this.criteria.commit(obj);
        caller.validate(obj);
        caller.commit(obj);
        return readResolve(inputNode, obj, caller);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) {
        Instance objectFactory = this.factory.getInstance(inputNode);
        if (objectFactory.isReference()) {
            return true;
        }
        objectFactory.setInstance(null);
        return validate(inputNode, objectFactory.getType());
    }

    private void write(OutputNode outputNode, Object obj, Schema schema) {
        Section section = schema.getSection();
        writeVersion(outputNode, obj, schema);
        writeSection(outputNode, obj, section);
    }

    private void writeText(OutputNode outputNode, Object obj, Label label) {
        if (obj == null || label.isTextList()) {
            return;
        }
        String text = this.factory.getText(obj);
        outputNode.setData(label.isData());
        outputNode.setValue(text);
    }

    private void readVersion(InputNode inputNode, Object obj, Label label) {
        Object readInstance = readInstance(inputNode, obj, label);
        Class type = this.type.getType();
        if (readInstance != null) {
            Double valueOf = Double.valueOf(this.context.getVersion(type).revision());
            if (readInstance.equals(this.revision)) {
                return;
            }
            this.revision.compare(valueOf, readInstance);
        }
    }

    private boolean validate(InputNode inputNode, Class cls) {
        Schema schema = this.context.getSchema(cls);
        Section section = schema.getSection();
        validateText(inputNode, schema);
        validateSection(inputNode, section);
        return inputNode.isElement();
    }

    private void writeElement(OutputNode outputNode, Object obj, Converter converter) {
        converter.write(outputNode, obj);
    }

    private Object read(InputNode inputNode, Instance instance, Class cls) {
        Schema schema = this.context.getSchema(cls);
        Caller caller = schema.getCaller();
        Object read = read(schema, instance).read(inputNode);
        caller.validate(read);
        caller.commit(read);
        instance.setInstance(read);
        return readResolve(inputNode, read, caller);
    }

    private void validate(InputNode inputNode, Label label) {
        Converter converter = label.getConverter(this.context);
        Position position = inputNode.getPosition();
        Class type = this.type.getType();
        if (converter.validate(inputNode)) {
            this.criteria.set(label, null);
            return;
        }
        throw new PersistenceException("Invalid value for %s in %s at %s", label, type, position);
    }

    private Builder read(Schema schema, Instance instance) {
        if (schema.getInstantiator().isDefault()) {
            return new Builder(this, this.criteria, schema, instance);
        }
        return new Injector(this, this.criteria, schema, instance);
    }

    private void validate(InputNode inputNode, LabelMap labelMap) {
        Position position = inputNode.getPosition();
        Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            Class type = this.type.getType();
            if (next.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", next, type, position);
            }
        }
    }

    private void read(InputNode inputNode, Object obj, Schema schema) {
        Section section = schema.getSection();
        readVersion(inputNode, obj, schema);
        readSection(inputNode, obj, section);
    }
}
