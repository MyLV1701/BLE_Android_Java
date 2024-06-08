package org.simpleframework.xml.transform;

/* loaded from: classes.dex */
class CharacterTransform implements Transform<Character> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Character read(String str) {
        if (str.length() == 1) {
            return Character.valueOf(str.charAt(0));
        }
        throw new InvalidFormatException("Cannot convert '%s' to a character", str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Character ch) {
        return ch.toString();
    }
}
