package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> implements Serializable {
    protected final ContextAttributes _attributes;
    protected final ConfigOverrides _configOverrides;
    protected final SimpleMixInResolver _mixIns;
    protected final PropertyName _rootName;
    protected final RootNameLookup _rootNames;
    protected final SubtypeResolver _subtypeResolver;
    protected final Class<?> _view;
    protected static final ConfigOverride EMPTY_OVERRIDE = ConfigOverride.empty();
    private static final int DEFAULT_MAPPER_FEATURES = MapperConfig.collectFeatureDefaults(MapperFeature.class);
    private static final int AUTO_DETECT_MASK = (((MapperFeature.AUTO_DETECT_FIELDS.getMask() | MapperFeature.AUTO_DETECT_GETTERS.getMask()) | MapperFeature.AUTO_DETECT_IS_GETTERS.getMask()) | MapperFeature.AUTO_DETECT_SETTERS.getMask()) | MapperFeature.AUTO_DETECT_CREATORS.getMask();

    /* JADX INFO: Access modifiers changed from: protected */
    public MapperConfigBase(BaseSettings baseSettings, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides) {
        super(baseSettings, DEFAULT_MAPPER_FEATURES);
        this._mixIns = simpleMixInResolver;
        this._subtypeResolver = subtypeResolver;
        this._rootNames = rootNameLookup;
        this._rootName = null;
        this._view = null;
        this._attributes = ContextAttributes.getEmpty();
        this._configOverrides = configOverrides;
    }

    protected abstract T _withMapperFeatures(int i);

    @Override // com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver
    public final Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixIns.findMixInClassFor(cls);
    }

    public PropertyName findRootName(JavaType javaType) {
        PropertyName propertyName = this._rootName;
        return propertyName != null ? propertyName : this._rootNames.findRootName(javaType, this);
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final ContextAttributes getAttributes() {
        return this._attributes;
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final ConfigOverride getConfigOverride(Class<?> cls) {
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        return findOverride == null ? EMPTY_OVERRIDE : findOverride;
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final JsonInclude.Value getDefaultInclusion(Class<?> cls, Class<?> cls2) {
        JsonInclude.Value includeAsProperty = getConfigOverride(cls2).getIncludeAsProperty();
        JsonInclude.Value defaultPropertyInclusion = getDefaultPropertyInclusion(cls);
        return defaultPropertyInclusion == null ? includeAsProperty : defaultPropertyInclusion.withOverrides(includeAsProperty);
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public Boolean getDefaultMergeable() {
        return this._configOverrides.getDefaultMergeable();
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return this._configOverrides.findFormatDefaults(cls);
    }

    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls) {
        JsonIgnoreProperties.Value ignorals;
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        if (findOverride == null || (ignorals = findOverride.getIgnorals()) == null) {
            return null;
        }
        return ignorals;
    }

    public final JsonInclude.Value getDefaultPropertyInclusion() {
        return this._configOverrides.getDefaultInclusion();
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final JsonSetter.Value getDefaultSetterInfo() {
        return this._configOverrides.getDefaultSetterInfo();
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.fasterxml.jackson.databind.introspect.VisibilityChecker, com.fasterxml.jackson.databind.introspect.VisibilityChecker<?>] */
    public final VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker<?> defaultVisibility = this._configOverrides.getDefaultVisibility();
        int i = this._mapperFeatures;
        int i2 = AUTO_DETECT_MASK;
        if ((i & i2) == i2) {
            return defaultVisibility;
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            defaultVisibility = defaultVisibility.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
            defaultVisibility = defaultVisibility.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
            defaultVisibility = defaultVisibility.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
            defaultVisibility = defaultVisibility.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return !isEnabled(MapperFeature.AUTO_DETECT_CREATORS) ? defaultVisibility.withCreatorVisibility(JsonAutoDetect.Visibility.NONE) : defaultVisibility;
    }

    public final PropertyName getFullRootName() {
        return this._rootName;
    }

    public final SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public final T with(MapperFeature... mapperFeatureArr) {
        int i = this._mapperFeatures;
        for (MapperFeature mapperFeature : mapperFeatureArr) {
            i |= mapperFeature.getMask();
        }
        return i == this._mapperFeatures ? this : _withMapperFeatures(i);
    }

    public final T without(MapperFeature... mapperFeatureArr) {
        int i = this._mapperFeatures;
        for (MapperFeature mapperFeature : mapperFeatureArr) {
            i &= mapperFeature.getMask() ^ (-1);
        }
        return i == this._mapperFeatures ? this : _withMapperFeatures(i);
    }

    public Boolean getDefaultMergeable(Class<?> cls) {
        Boolean mergeable;
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        return (findOverride == null || (mergeable = findOverride.getMergeable()) == null) ? this._configOverrides.getDefaultMergeable() : mergeable;
    }

    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final JsonInclude.Value getDefaultPropertyInclusion(Class<?> cls) {
        JsonInclude.Value include = getConfigOverride(cls).getInclude();
        JsonInclude.Value defaultPropertyInclusion = getDefaultPropertyInclusion();
        return defaultPropertyInclusion == null ? include : defaultPropertyInclusion.withOverrides(include);
    }

    public PropertyName findRootName(Class<?> cls) {
        PropertyName propertyName = this._rootName;
        return propertyName != null ? propertyName : this._rootNames.findRootName(cls, this);
    }

    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls, AnnotatedClass annotatedClass) {
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        return JsonIgnoreProperties.Value.merge(annotationIntrospector == null ? null : annotationIntrospector.findPropertyIgnorals(annotatedClass), getDefaultPropertyIgnorals(cls));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, int i) {
        super(mapperConfigBase, i);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.fasterxml.jackson.databind.introspect.VisibilityChecker, com.fasterxml.jackson.databind.introspect.VisibilityChecker<?>] */
    @Override // com.fasterxml.jackson.databind.cfg.MapperConfig
    public final VisibilityChecker<?> getDefaultVisibilityChecker(Class<?> cls, AnnotatedClass annotatedClass) {
        VisibilityChecker<?> defaultVisibilityChecker = getDefaultVisibilityChecker();
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        if (annotationIntrospector != null) {
            defaultVisibilityChecker = annotationIntrospector.findAutoDetectVisibility(annotatedClass, defaultVisibilityChecker);
        }
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        return findOverride != null ? defaultVisibilityChecker.withOverrides(findOverride.getVisibility()) : defaultVisibilityChecker;
    }
}
