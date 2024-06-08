package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators$PropertyGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    private boolean _isSetterlessType(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    protected void _validateSubType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        SubTypeValidator.instance().validateSubType(deserializationContext, javaType, beanDescription);
    }

    protected void addBackReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        List<BeanPropertyDefinition> findBackReferences = beanDescription.findBackReferences();
        if (findBackReferences != null) {
            for (BeanPropertyDefinition beanPropertyDefinition : findBackReferences) {
                beanDeserializerBuilder.addBackReferenceProperty(beanPropertyDefinition.findReferenceName(), constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getPrimaryType()));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty[]] */
    /* JADX WARN: Type inference failed for: r17v0, types: [com.fasterxml.jackson.databind.DeserializationContext] */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder] */
    protected void addBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        Set<String> emptySet;
        SettableBeanProperty settableBeanProperty;
        CreatorProperty creatorProperty;
        CreatorProperty[] fromObjectArguments = beanDescription.getType().isAbstract() ^ true ? beanDeserializerBuilder.getValueInstantiator().getFromObjectArguments(deserializationContext.getConfig()) : null;
        boolean z = fromObjectArguments != null;
        JsonIgnoreProperties.Value defaultPropertyIgnorals = deserializationContext.getConfig().getDefaultPropertyIgnorals(beanDescription.getBeanClass(), beanDescription.getClassInfo());
        if (defaultPropertyIgnorals != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(defaultPropertyIgnorals.getIgnoreUnknown());
            emptySet = defaultPropertyIgnorals.findIgnoredForDeserialization();
            Iterator<String> it = emptySet.iterator();
            while (it.hasNext()) {
                beanDeserializerBuilder.addIgnorable(it.next());
            }
        } else {
            emptySet = Collections.emptySet();
        }
        Set<String> set = emptySet;
        AnnotatedMember findAnySetterAccessor = beanDescription.findAnySetterAccessor();
        if (findAnySetterAccessor != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationContext, beanDescription, findAnySetterAccessor));
        } else {
            Set<String> ignoredPropertyNames = beanDescription.getIgnoredPropertyNames();
            if (ignoredPropertyNames != null) {
                Iterator<String> it2 = ignoredPropertyNames.iterator();
                while (it2.hasNext()) {
                    beanDeserializerBuilder.addIgnorable(it2.next());
                }
            }
        }
        boolean z2 = deserializationContext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationContext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS);
        List<BeanPropertyDefinition> filterBeanProps = filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, beanDescription.findProperties(), set);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it3 = this._factoryConfig.deserializerModifiers().iterator();
            while (it3.hasNext()) {
                filterBeanProps = it3.next().updateProperties(deserializationContext.getConfig(), beanDescription, filterBeanProps);
            }
        }
        for (BeanPropertyDefinition beanPropertyDefinition : filterBeanProps) {
            if (beanPropertyDefinition.hasSetter()) {
                settableBeanProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getSetter().getParameterType(0));
            } else if (beanPropertyDefinition.hasField()) {
                settableBeanProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getField().getType());
            } else {
                AnnotatedMethod getter = beanPropertyDefinition.getGetter();
                if (getter != null) {
                    if (z2 && _isSetterlessType(getter.getRawType())) {
                        if (!beanDeserializerBuilder.hasIgnorable(beanPropertyDefinition.getName())) {
                            settableBeanProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                        }
                    } else if (!beanPropertyDefinition.hasConstructorParameter() && beanPropertyDefinition.getMetadata().getMergeInfo() != null) {
                        settableBeanProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                    }
                }
                settableBeanProperty = null;
            }
            if (z && beanPropertyDefinition.hasConstructorParameter()) {
                String name = beanPropertyDefinition.getName();
                if (fromObjectArguments != null) {
                    for (CreatorProperty creatorProperty2 : fromObjectArguments) {
                        if (name.equals(creatorProperty2.getName()) && (creatorProperty2 instanceof CreatorProperty)) {
                            creatorProperty = creatorProperty2;
                            break;
                        }
                    }
                }
                creatorProperty = null;
                if (creatorProperty == null) {
                    ArrayList arrayList = new ArrayList();
                    for (CreatorProperty creatorProperty3 : fromObjectArguments) {
                        arrayList.add(creatorProperty3.getName());
                    }
                    deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "Could not find creator property with name '%s' (known Creator properties: %s)", name, arrayList);
                    throw null;
                }
                if (settableBeanProperty != null) {
                    creatorProperty.setFallbackSetter(settableBeanProperty);
                }
                Class<?>[] findViews = beanPropertyDefinition.findViews();
                if (findViews == null) {
                    findViews = beanDescription.findDefaultViews();
                }
                creatorProperty.setViews(findViews);
                beanDeserializerBuilder.addCreatorProperty(creatorProperty);
            } else if (settableBeanProperty != null) {
                Class<?>[] findViews2 = beanPropertyDefinition.findViews();
                if (findViews2 == null) {
                    findViews2 = beanDescription.findDefaultViews();
                }
                settableBeanProperty.setViews(findViews2);
                beanDeserializerBuilder.addProperty(settableBeanProperty);
            }
        }
    }

    protected void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        Map<Object, AnnotatedMember> findInjectables = beanDescription.findInjectables();
        if (findInjectables != null) {
            for (Map.Entry<Object, AnnotatedMember> entry : findInjectables.entrySet()) {
                AnnotatedMember value = entry.getValue();
                beanDeserializerBuilder.addInjectable(PropertyName.construct(value.getName()), value.getType(), beanDescription.getClassAnnotations(), value, entry.getKey());
            }
        }
    }

    protected void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        SettableBeanProperty settableBeanProperty;
        ObjectIdGenerator<?> objectIdGeneratorInstance;
        JavaType javaType;
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo == null) {
            return;
        }
        Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        ObjectIdResolver objectIdResolverInstance = deserializationContext.objectIdResolverInstance(beanDescription.getClassInfo(), objectIdInfo);
        if (generatorType == ObjectIdGenerators$PropertyGenerator.class) {
            PropertyName propertyName = objectIdInfo.getPropertyName();
            settableBeanProperty = beanDeserializerBuilder.findProperty(propertyName);
            if (settableBeanProperty != null) {
                javaType = settableBeanProperty.getType();
                objectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
            } else {
                throw new IllegalArgumentException("Invalid Object Id definition for " + beanDescription.getBeanClass().getName() + ": cannot find property with name '" + propertyName + "'");
            }
        } else {
            JavaType javaType2 = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType((Class<?>) generatorType), ObjectIdGenerator.class)[0];
            settableBeanProperty = null;
            objectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
            javaType = javaType2;
        }
        beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(javaType, objectIdInfo.getPropertyName(), objectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty, objectIdResolverInstance));
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        JsonDeserializer<?> build;
        try {
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            DeserializationConfig config = deserializationContext.getConfig();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    constructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
                }
            }
            if (javaType.isAbstract() && !findValueInstantiator.canInstantiate()) {
                build = constructBeanDeserializerBuilder.buildAbstract();
            } else {
                build = constructBeanDeserializerBuilder.build();
            }
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
                while (it2.hasNext()) {
                    build = it2.next().modifyDeserializer(config, beanDescription, build);
                }
            }
            return build;
        } catch (IllegalArgumentException e2) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e2), beanDescription, (BeanPropertyDefinition) null);
        } catch (NoClassDefFoundError e3) {
            return new ErrorThrowingDeserializer(e3);
        }
    }

    protected JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        try {
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            DeserializationConfig config = deserializationContext.getConfig();
            BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            JsonPOJOBuilder.Value findPOJOBuilderConfig = beanDescription.findPOJOBuilderConfig();
            String str = findPOJOBuilderConfig == null ? "build" : findPOJOBuilderConfig.buildMethodName;
            AnnotatedMethod findMethod = beanDescription.findMethod(str, null);
            if (findMethod != null && config.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(findMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            constructBeanDeserializerBuilder.setPOJOBuilder(findMethod, findPOJOBuilderConfig);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    constructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
                }
            }
            JsonDeserializer<?> buildBuilderBased = constructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
                while (it2.hasNext()) {
                    buildBuilderBased = it2.next().modifyDeserializer(config, beanDescription, buildBuilderBased);
                }
            }
            return buildBuilderBased;
        } catch (IllegalArgumentException e2) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e2), beanDescription, (BeanPropertyDefinition) null);
        } catch (NoClassDefFoundError e3) {
            return new ErrorThrowingDeserializer(e3);
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        SettableBeanProperty constructSettableProperty;
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext, beanDescription));
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        AnnotatedMethod findMethod = beanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (findMethod != null && (constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), findMethod, new PropertyName("cause")), findMethod.getParameterType(0))) != null) {
            constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("suppressed");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                constructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> build = constructBeanDeserializerBuilder.build();
        if (build instanceof BeanDeserializer) {
            build = new ThrowableDeserializer((BeanDeserializer) build);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
            while (it2.hasNext()) {
                build = it2.next().modifyDeserializer(config, beanDescription, build);
            }
        }
        return build;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0089  */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.fasterxml.jackson.databind.deser.SettableAnyProperty constructAnySetter(com.fasterxml.jackson.databind.DeserializationContext r9, com.fasterxml.jackson.databind.BeanDescription r10, com.fasterxml.jackson.databind.introspect.AnnotatedMember r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L2a
            r10 = r11
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r10 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethod) r10
            com.fasterxml.jackson.databind.JavaType r0 = r10.getParameterType(r2)
            com.fasterxml.jackson.databind.JavaType r10 = r10.getParameterType(r1)
            com.fasterxml.jackson.databind.JavaType r10 = r8.resolveMemberAndTypeAnnotations(r9, r11, r10)
            com.fasterxml.jackson.databind.BeanProperty$Std r7 = new com.fasterxml.jackson.databind.BeanProperty$Std
            java.lang.String r1 = r11.getName()
            com.fasterxml.jackson.databind.PropertyName r2 = com.fasterxml.jackson.databind.PropertyName.construct(r1)
            r4 = 0
            com.fasterxml.jackson.databind.PropertyMetadata r6 = com.fasterxml.jackson.databind.PropertyMetadata.STD_OPTIONAL
            r1 = r7
            r3 = r10
            r5 = r11
            r1.<init>(r2, r3, r4, r5, r6)
            r10 = r0
            goto L55
        L2a:
            boolean r0 = r11 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedField
            if (r0 == 0) goto L99
            r10 = r11
            com.fasterxml.jackson.databind.introspect.AnnotatedField r10 = (com.fasterxml.jackson.databind.introspect.AnnotatedField) r10
            com.fasterxml.jackson.databind.JavaType r10 = r10.getType()
            com.fasterxml.jackson.databind.JavaType r2 = r8.resolveMemberAndTypeAnnotations(r9, r11, r10)
            com.fasterxml.jackson.databind.JavaType r10 = r2.getKeyType()
            com.fasterxml.jackson.databind.JavaType r6 = r2.getContentType()
            com.fasterxml.jackson.databind.BeanProperty$Std r7 = new com.fasterxml.jackson.databind.BeanProperty$Std
            java.lang.String r0 = r11.getName()
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.construct(r0)
            r3 = 0
            com.fasterxml.jackson.databind.PropertyMetadata r5 = com.fasterxml.jackson.databind.PropertyMetadata.STD_OPTIONAL
            r0 = r7
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            r3 = r6
            r1 = r7
        L55:
            com.fasterxml.jackson.databind.KeyDeserializer r0 = r8.findKeyDeserializerFromAnnotation(r9, r11)
            if (r0 != 0) goto L61
            java.lang.Object r0 = r10.getValueHandler()
            com.fasterxml.jackson.databind.KeyDeserializer r0 = (com.fasterxml.jackson.databind.KeyDeserializer) r0
        L61:
            if (r0 != 0) goto L69
            com.fasterxml.jackson.databind.KeyDeserializer r10 = r9.findKeyDeserializer(r10, r1)
        L67:
            r4 = r10
            goto L75
        L69:
            boolean r10 = r0 instanceof com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer
            if (r10 == 0) goto L74
            com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer r0 = (com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer) r0
            com.fasterxml.jackson.databind.KeyDeserializer r10 = r0.createContextual(r9, r1)
            goto L67
        L74:
            r4 = r0
        L75:
            com.fasterxml.jackson.databind.JsonDeserializer r10 = r8.findContentDeserializerFromAnnotation(r9, r11)
            if (r10 != 0) goto L81
            java.lang.Object r10 = r3.getValueHandler()
            com.fasterxml.jackson.databind.JsonDeserializer r10 = (com.fasterxml.jackson.databind.JsonDeserializer) r10
        L81:
            if (r10 == 0) goto L89
            com.fasterxml.jackson.databind.JsonDeserializer r9 = r9.handlePrimaryContextualization(r10, r1, r3)
            r5 = r9
            goto L8a
        L89:
            r5 = r10
        L8a:
            java.lang.Object r9 = r3.getTypeHandler()
            r6 = r9
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r6 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r6
            com.fasterxml.jackson.databind.deser.SettableAnyProperty r9 = new com.fasterxml.jackson.databind.deser.SettableAnyProperty
            r0 = r9
            r2 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r9
        L99:
            com.fasterxml.jackson.databind.JavaType r10 = r10.getType()
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.Class r11 = r11.getClass()
            r0[r2] = r11
            java.lang.String r11 = "Unrecognized mutator type for any setter: %s"
            java.lang.String r11 = java.lang.String.format(r11, r0)
            r9.reportBadDefinition(r10, r11)
            r9 = 0
            goto Lb1
        Lb0:
            throw r9
        Lb1:
            goto Lb0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.constructAnySetter(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.introspect.AnnotatedMember):com.fasterxml.jackson.databind.deser.SettableAnyProperty");
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext);
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, JavaType javaType) {
        SettableBeanProperty fieldProperty;
        AnnotatedMember nonConstructorMutator = beanPropertyDefinition.getNonConstructorMutator();
        if (nonConstructorMutator != null) {
            JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, nonConstructorMutator, javaType);
            TypeDeserializer typeDeserializer = (TypeDeserializer) resolveMemberAndTypeAnnotations.getTypeHandler();
            if (nonConstructorMutator instanceof AnnotatedMethod) {
                fieldProperty = new MethodProperty(beanPropertyDefinition, resolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedMethod) nonConstructorMutator);
            } else {
                fieldProperty = new FieldProperty(beanPropertyDefinition, resolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedField) nonConstructorMutator);
            }
            JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, nonConstructorMutator);
            if (findDeserializerFromAnnotation == null) {
                findDeserializerFromAnnotation = (JsonDeserializer) resolveMemberAndTypeAnnotations.getValueHandler();
            }
            if (findDeserializerFromAnnotation != null) {
                fieldProperty = fieldProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(findDeserializerFromAnnotation, fieldProperty, resolveMemberAndTypeAnnotations));
            }
            AnnotationIntrospector.ReferenceProperty findReferenceType = beanPropertyDefinition.findReferenceType();
            if (findReferenceType != null && findReferenceType.isManagedReference()) {
                fieldProperty.setManagedReferenceName(findReferenceType.getName());
            }
            ObjectIdInfo findObjectIdInfo = beanPropertyDefinition.findObjectIdInfo();
            if (findObjectIdInfo != null) {
                fieldProperty.setObjectIdInfo(findObjectIdInfo);
            }
            return fieldProperty;
        }
        deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "No non-constructor mutator available", new Object[0]);
        throw null;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) {
        AnnotatedMethod getter = beanPropertyDefinition.getGetter();
        JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, getter, getter.getType());
        SetterlessProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, resolveMemberAndTypeAnnotations, (TypeDeserializer) resolveMemberAndTypeAnnotations.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, getter);
        if (findDeserializerFromAnnotation == null) {
            findDeserializerFromAnnotation = (JsonDeserializer) resolveMemberAndTypeAnnotations.getValueHandler();
        }
        return findDeserializerFromAnnotation != null ? setterlessProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(findDeserializerFromAnnotation, setterlessProperty, resolveMemberAndTypeAnnotations)) : setterlessProperty;
    }

    @Override // com.fasterxml.jackson.databind.deser.DeserializerFactory
    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        JavaType materializeAbstractType;
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer<?> _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, config, beanDescription);
        if (_findCustomBeanDeserializer != null) {
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    _findCustomBeanDeserializer = it.next().modifyDeserializer(deserializationContext.getConfig(), beanDescription, _findCustomBeanDeserializer);
                }
            }
            return _findCustomBeanDeserializer;
        }
        if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isAbstract() && !javaType.isPrimitive() && !javaType.isEnumType() && (materializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription)) != null) {
            return buildBeanDeserializer(deserializationContext, materializeAbstractType, config.introspect(materializeAbstractType));
        }
        JsonDeserializer<?> findStdDeserializer = findStdDeserializer(deserializationContext, javaType, beanDescription);
        if (findStdDeserializer != null) {
            return findStdDeserializer;
        }
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        _validateSubType(deserializationContext, javaType, beanDescription);
        return buildBeanDeserializer(deserializationContext, javaType, beanDescription);
    }

    @Override // com.fasterxml.jackson.databind.deser.DeserializerFactory
    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) {
        return buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.getConfig().introspectForBuilder(deserializationContext.constructType(cls)));
    }

    protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) {
        Class<?> rawPrimaryType;
        ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : list) {
            String name = beanPropertyDefinition.getName();
            if (!set.contains(name)) {
                if (!beanPropertyDefinition.hasConstructorParameter() && (rawPrimaryType = beanPropertyDefinition.getRawPrimaryType()) != null && isIgnorableType(deserializationContext.getConfig(), beanPropertyDefinition, rawPrimaryType, hashMap)) {
                    beanDeserializerBuilder.addIgnorable(name);
                } else {
                    arrayList.add(beanPropertyDefinition);
                }
            }
        }
        return arrayList;
    }

    protected JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        JsonDeserializer<?> findDefaultDeserializer = findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (findDefaultDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                findDefaultDeserializer = it.next().modifyDeserializer(deserializationContext.getConfig(), beanDescription, findDefaultDeserializer);
            }
        }
        return findDefaultDeserializer;
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanPropertyDefinition beanPropertyDefinition, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool;
        Boolean bool2 = map.get(cls);
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        if (cls != String.class && !cls.isPrimitive()) {
            bool = deserializationConfig.getConfigOverride(cls).getIsIgnoredType();
            if (bool == null) {
                bool = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations(cls).getClassInfo());
                if (bool == null) {
                    bool = Boolean.FALSE;
                }
            }
        } else {
            bool = Boolean.FALSE;
        }
        map.put(cls, bool);
        return bool.booleanValue();
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        String canBeABeanType = ClassUtil.canBeABeanType(cls);
        if (canBeABeanType == null) {
            if (!ClassUtil.isProxyType(cls)) {
                String isLocalType = ClassUtil.isLocalType(cls, true);
                if (isLocalType == null) {
                    return true;
                }
                throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + isLocalType + ") as a Bean");
            }
            throw new IllegalArgumentException("Cannot deserialize Proxy class " + cls.getName() + " as a Bean");
        }
        throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + canBeABeanType + ") as a Bean");
    }

    protected JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) {
        Iterator<AbstractTypeResolver> it = this._factoryConfig.abstractTypeResolvers().iterator();
        while (it.hasNext()) {
            JavaType resolveAbstractType = it.next().resolveAbstractType(deserializationContext.getConfig(), beanDescription);
            if (resolveAbstractType != null) {
                return resolveAbstractType;
            }
        }
        return null;
    }
}
