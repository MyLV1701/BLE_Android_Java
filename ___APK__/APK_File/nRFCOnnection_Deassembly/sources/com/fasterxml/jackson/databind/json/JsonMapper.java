package com.fasterxml.jackson.databind.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/* loaded from: classes.dex */
public class JsonMapper extends ObjectMapper {
    public JsonMapper() {
        this(new JsonFactory());
    }

    public JsonMapper(JsonFactory jsonFactory) {
        super(jsonFactory);
    }
}
