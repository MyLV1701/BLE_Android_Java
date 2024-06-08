package org.simpleframework.xml.core;

import java.util.List;

/* loaded from: classes.dex */
class SignatureCreator implements Creator {
    private final List<Parameter> list;
    private final Signature signature;
    private final Class type;

    public SignatureCreator(Signature signature) {
        this.type = signature.getType();
        this.list = signature.getAll();
        this.signature = signature;
    }

    private double getAdjustment(double d2) {
        double size = this.list.size();
        Double.isNaN(size);
        double d3 = size / 1000.0d;
        if (d2 > 0.0d) {
            double size2 = this.list.size();
            Double.isNaN(size2);
            return d3 + (d2 / size2);
        }
        double size3 = this.list.size();
        Double.isNaN(size3);
        return d2 / size3;
    }

    private double getPercentage(Criteria criteria) {
        double d2 = 0.0d;
        for (Parameter parameter : this.list) {
            if (criteria.get(parameter.getKey()) != null) {
                d2 += 1.0d;
            } else if (parameter.isRequired() || parameter.isPrimitive()) {
                return -1.0d;
            }
        }
        return getAdjustment(d2);
    }

    private Object getVariable(Criteria criteria, int i) {
        Variable remove = criteria.remove(this.list.get(i).getKey());
        if (remove != null) {
            return remove.getValue();
        }
        return null;
    }

    @Override // org.simpleframework.xml.core.Creator
    public Object getInstance() {
        return this.signature.create();
    }

    @Override // org.simpleframework.xml.core.Creator
    public double getScore(Criteria criteria) {
        Signature copy = this.signature.copy();
        for (Object obj : criteria) {
            Parameter parameter = copy.get(obj);
            Variable variable = criteria.get(obj);
            Contact contact = variable.getContact();
            if (parameter != null && !Support.isAssignable(variable.getValue().getClass(), parameter.getType())) {
                return -1.0d;
            }
            if (contact.isReadOnly() && parameter == null) {
                return -1.0d;
            }
        }
        return getPercentage(criteria);
    }

    @Override // org.simpleframework.xml.core.Creator
    public Signature getSignature() {
        return this.signature;
    }

    @Override // org.simpleframework.xml.core.Creator
    public Class getType() {
        return this.type;
    }

    public String toString() {
        return this.signature.toString();
    }

    @Override // org.simpleframework.xml.core.Creator
    public Object getInstance(Criteria criteria) {
        Object[] array = this.list.toArray();
        for (int i = 0; i < this.list.size(); i++) {
            array[i] = getVariable(criteria, i);
        }
        return this.signature.create(array);
    }
}
