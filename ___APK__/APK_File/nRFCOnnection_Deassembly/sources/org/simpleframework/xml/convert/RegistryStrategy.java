package org.simpleframework.xml.convert;

import java.util.Map;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

/* loaded from: classes.dex */
public class RegistryStrategy implements Strategy {
    private final Registry registry;
    private final Strategy strategy;

    public RegistryStrategy(Registry registry) {
        this(registry, new TreeStrategy());
    }

    private boolean isReference(Value value) {
        return value != null && value.isReference();
    }

    private Converter lookup(Type type, Value value) {
        Class type2 = type.getType();
        if (value != null) {
            type2 = value.getType();
        }
        return this.registry.lookup(type2);
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public Value read(Type type, NodeMap<InputNode> nodeMap, Map map) {
        Value read = this.strategy.read(type, nodeMap, map);
        return isReference(read) ? read : read(type, nodeMap, read);
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap, Map map) {
        boolean write = this.strategy.write(type, obj, nodeMap, map);
        return !write ? write(type, obj, nodeMap) : write;
    }

    public RegistryStrategy(Registry registry, Strategy strategy) {
        this.registry = registry;
        this.strategy = strategy;
    }

    private boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap) {
        Converter lookup = lookup(type, obj);
        OutputNode node = nodeMap.getNode();
        if (lookup == null) {
            return false;
        }
        lookup.write(node, obj);
        return true;
    }

    private Converter lookup(Type type, Object obj) {
        Class<?> type2 = type.getType();
        if (obj != null) {
            type2 = obj.getClass();
        }
        return this.registry.lookup(type2);
    }

    private Value read(Type type, NodeMap<InputNode> nodeMap, Value value) {
        Converter lookup = lookup(type, value);
        InputNode node = nodeMap.getNode();
        if (lookup == null) {
            return value;
        }
        Object read = lookup.read(node);
        Class type2 = type.getType();
        if (value != null) {
            value.setValue(read);
        }
        return new Reference(value, read, type2);
    }
}
