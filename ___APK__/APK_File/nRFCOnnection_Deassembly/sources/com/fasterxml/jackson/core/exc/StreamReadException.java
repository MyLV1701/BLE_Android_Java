package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.RequestPayload;

/* loaded from: classes.dex */
public abstract class StreamReadException extends JsonProcessingException {
    protected transient JsonParser _processor;
    protected RequestPayload _requestPayload;

    public StreamReadException(JsonParser jsonParser, String str) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation());
        this._processor = jsonParser;
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException, java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (this._requestPayload == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append("\nRequest payload : ");
        this._requestPayload.toString();
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException
    public JsonParser getProcessor() {
        return this._processor;
    }

    public StreamReadException(JsonParser jsonParser, String str, Throwable th) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation(), th);
        this._processor = jsonParser;
    }

    public StreamReadException(JsonParser jsonParser, String str, JsonLocation jsonLocation) {
        super(str, jsonLocation, null);
        this._processor = jsonParser;
    }
}
