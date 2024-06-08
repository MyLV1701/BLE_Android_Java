package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;

/* loaded from: classes.dex */
final class InternalNodeMapper {
    private static final JsonMapper JSON_MAPPER = new JsonMapper();
    private static final ObjectWriter STD_WRITER = JSON_MAPPER.writer();

    static {
        JSON_MAPPER.writer().withDefaultPrettyPrinter();
        JSON_MAPPER.readerFor(JsonNode.class);
    }

    public static String nodeToString(JsonNode jsonNode) {
        try {
            return STD_WRITER.writeValueAsString(jsonNode);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }
}
