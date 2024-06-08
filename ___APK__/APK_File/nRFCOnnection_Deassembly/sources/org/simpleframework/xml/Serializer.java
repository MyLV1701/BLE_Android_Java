package org.simpleframework.xml;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/* loaded from: classes.dex */
public interface Serializer {
    <T> T read(Class<? extends T> cls, File file);

    <T> T read(Class<? extends T> cls, File file, boolean z);

    <T> T read(Class<? extends T> cls, InputStream inputStream);

    <T> T read(Class<? extends T> cls, InputStream inputStream, boolean z);

    <T> T read(Class<? extends T> cls, Reader reader);

    <T> T read(Class<? extends T> cls, Reader reader, boolean z);

    <T> T read(Class<? extends T> cls, String str);

    <T> T read(Class<? extends T> cls, String str, boolean z);

    <T> T read(Class<? extends T> cls, InputNode inputNode);

    <T> T read(Class<? extends T> cls, InputNode inputNode, boolean z);

    <T> T read(T t, File file);

    <T> T read(T t, File file, boolean z);

    <T> T read(T t, InputStream inputStream);

    <T> T read(T t, InputStream inputStream, boolean z);

    <T> T read(T t, Reader reader);

    <T> T read(T t, Reader reader, boolean z);

    <T> T read(T t, String str);

    <T> T read(T t, String str, boolean z);

    <T> T read(T t, InputNode inputNode);

    <T> T read(T t, InputNode inputNode, boolean z);

    boolean validate(Class cls, File file);

    boolean validate(Class cls, File file, boolean z);

    boolean validate(Class cls, InputStream inputStream);

    boolean validate(Class cls, InputStream inputStream, boolean z);

    boolean validate(Class cls, Reader reader);

    boolean validate(Class cls, Reader reader, boolean z);

    boolean validate(Class cls, String str);

    boolean validate(Class cls, String str, boolean z);

    boolean validate(Class cls, InputNode inputNode);

    boolean validate(Class cls, InputNode inputNode, boolean z);

    void write(Object obj, File file);

    void write(Object obj, OutputStream outputStream);

    void write(Object obj, Writer writer);

    void write(Object obj, OutputNode outputNode);
}
