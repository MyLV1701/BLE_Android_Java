package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class InstantiatorBuilder {
    private Detail detail;
    private Instantiator factory;
    private Scanner scanner;
    private List<Creator> options = new ArrayList();
    private Comparer comparer = new Comparer();
    private LabelMap attributes = new LabelMap();
    private LabelMap elements = new LabelMap();
    private LabelMap texts = new LabelMap();

    public InstantiatorBuilder(Scanner scanner, Detail detail) {
        this.scanner = scanner;
        this.detail = detail;
    }

    private boolean contains(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2 == str || str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private Instantiator create(Detail detail) {
        Signature signature = this.scanner.getSignature();
        return new ClassInstantiator(this.options, signature != null ? new SignatureCreator(signature) : null, this.scanner.getParameters(), detail);
    }

    private void populate(Detail detail) {
        Iterator<Signature> it = this.scanner.getSignatures().iterator();
        while (it.hasNext()) {
            populate(it.next());
        }
    }

    private Label resolve(Parameter parameter) {
        if (parameter.isAttribute()) {
            return resolve(parameter, this.attributes);
        }
        if (parameter.isText()) {
            return resolve(parameter, this.texts);
        }
        return resolve(parameter, this.elements);
    }

    private void validate(Detail detail) {
        for (Parameter parameter : this.scanner.getParameters().getAll()) {
            Label resolve = resolve(parameter);
            String path = parameter.getPath();
            if (resolve != null) {
                validateParameter(resolve, parameter);
            } else {
                throw new ConstructorException("Parameter '%s' does not have a match in %s", path, detail);
            }
        }
        validateConstructors();
    }

    private void validateAnnotations(Label label, Parameter parameter) {
        Annotation annotation = label.getAnnotation();
        Annotation annotation2 = parameter.getAnnotation();
        String name = parameter.getName();
        if (this.comparer.equals(annotation, annotation2)) {
            return;
        }
        Class<? extends Annotation> annotationType = annotation.annotationType();
        Class<? extends Annotation> annotationType2 = annotation2.annotationType();
        if (!annotationType.equals(annotationType2)) {
            throw new ConstructorException("Annotation %s does not match %s for '%s' in %s", annotationType2, annotationType, name, parameter);
        }
    }

    private void validateConstructor(Label label, List<Creator> list) {
        Iterator<Creator> it = list.iterator();
        while (it.hasNext()) {
            Signature signature = it.next().getSignature();
            Contact contact = label.getContact();
            Object key = label.getKey();
            if (contact.isReadOnly() && signature.get(key) == null) {
                it.remove();
            }
        }
    }

    private void validateConstructors() {
        List<Creator> creators = this.factory.getCreators();
        if (this.factory.isDefault()) {
            validateConstructors(this.elements);
            validateConstructors(this.attributes);
        }
        if (creators.isEmpty()) {
            return;
        }
        validateConstructors(this.elements, creators);
        validateConstructors(this.attributes, creators);
    }

    private void validateNames(Label label, Parameter parameter) {
        String name;
        String[] names = label.getNames();
        String name2 = parameter.getName();
        if (contains(names, name2) || name2 == (name = label.getName())) {
            return;
        }
        if (name2 != null && name != null) {
            if (!name2.equals(name)) {
                throw new ConstructorException("Annotation does not match %s for '%s' in %s", label, name2, parameter);
            }
            return;
        }
        throw new ConstructorException("Annotation does not match %s for '%s' in %s", label, name2, parameter);
    }

    private void validateParameter(Label label, Parameter parameter) {
        Contact contact = label.getContact();
        String name = parameter.getName();
        if (Support.isAssignable(parameter.getType(), contact.getType())) {
            validateNames(label, parameter);
            validateAnnotations(label, parameter);
            return;
        }
        throw new ConstructorException("Type is not compatible with %s for '%s' in %s", label, name, parameter);
    }

    public Instantiator build() {
        if (this.factory == null) {
            populate(this.detail);
            build(this.detail);
            validate(this.detail);
        }
        return this.factory;
    }

    public void register(Label label) {
        if (label.isAttribute()) {
            register(label, this.attributes);
        } else if (label.isText()) {
            register(label, this.texts);
        } else {
            register(label, this.elements);
        }
    }

    private void populate(Signature signature) {
        Signature signature2 = new Signature(signature);
        Iterator<Parameter> it = signature.iterator();
        while (it.hasNext()) {
            Parameter create = create(it.next());
            if (create != null) {
                signature2.add(create);
            }
        }
        create(signature2);
    }

    private Creator create(Signature signature) {
        SignatureCreator signatureCreator = new SignatureCreator(signature);
        if (signature != null) {
            this.options.add(signatureCreator);
        }
        return signatureCreator;
    }

    private Instantiator build(Detail detail) {
        if (this.factory == null) {
            this.factory = create(detail);
        }
        return this.factory;
    }

    private void register(Label label, LabelMap labelMap) {
        String name = label.getName();
        String path = label.getPath();
        if (labelMap.containsKey(name)) {
            if (!labelMap.get(name).getPath().equals(name)) {
                labelMap.remove(name);
            }
        } else {
            labelMap.put(name, label);
        }
        labelMap.put(path, label);
    }

    private Label resolve(Parameter parameter, LabelMap labelMap) {
        String name = parameter.getName();
        Label label = labelMap.get(parameter.getPath());
        return label == null ? labelMap.get(name) : label;
    }

    private Parameter create(Parameter parameter) {
        Label resolve = resolve(parameter);
        if (resolve != null) {
            return new CacheParameter(parameter, resolve);
        }
        return null;
    }

    private void validateConstructors(LabelMap labelMap) {
        Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (next != null && next.getContact().isReadOnly()) {
                throw new ConstructorException("Default constructor can not accept read only %s in %s", next, this.detail);
            }
        }
    }

    private void validateConstructors(LabelMap labelMap, List<Creator> list) {
        Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (next != null) {
                validateConstructor(next, list);
            }
        }
        if (list.isEmpty()) {
            throw new ConstructorException("No constructor accepts all read only values in %s", this.detail);
        }
    }
}
