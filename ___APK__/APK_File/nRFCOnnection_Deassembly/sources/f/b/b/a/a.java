package f.b.b.a;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class a extends Provider implements f.b.a.a.a.a {

    /* renamed from: b, reason: collision with root package name */
    private static String f3460b = "BouncyCastle Security Provider v1.52";

    /* renamed from: c, reason: collision with root package name */
    private static final String[] f3461c;

    /* renamed from: d, reason: collision with root package name */
    private static final String[] f3462d;

    /* renamed from: e, reason: collision with root package name */
    private static final String[] f3463e;

    /* renamed from: f, reason: collision with root package name */
    private static final String[] f3464f;
    private static final String[] g;
    private static final String[] h;
    private static final String[] i;

    /* renamed from: f.b.b.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0095a implements PrivilegedAction {
        C0095a() {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            a.this.a();
            return null;
        }
    }

    static {
        new b();
        new HashMap();
        f3461c = new String[]{"PBEPBKDF2", "PBEPKCS12"};
        f3462d = new String[]{"SipHash"};
        f3463e = new String[]{"AES", "ARC4", "Blowfish", "Camellia", "CAST5", "CAST6", "ChaCha", "DES", "DESede", "GOST28147", "Grainv1", "Grain128", "HC128", "HC256", "IDEA", "Noekeon", "RC2", "RC5", "RC6", "Rijndael", "Salsa20", "SEED", "Serpent", "Shacal2", "Skipjack", "TEA", "Twofish", "Threefish", "VMPC", "VMPCKSA3", "XTEA", "XSalsa20"};
        f3464f = new String[]{"X509", "IES"};
        g = new String[]{"DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145"};
        h = new String[]{"GOST3411", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", "SHA256", "SHA384", "SHA512", "SHA3", "Skein", "SM3", "Tiger", "Whirlpool"};
        i = new String[]{"BC", "PKCS12"};
    }

    public a() {
        super("SC", 1.52d, f3460b);
        AccessController.doPrivileged(new C0095a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        a("org.spongycastle.jcajce.provider.digest.", h);
        a("org.spongycastle.jcajce.provider.symmetric.", f3461c);
        a("org.spongycastle.jcajce.provider.symmetric.", f3462d);
        a("org.spongycastle.jcajce.provider.symmetric.", f3463e);
        a("org.spongycastle.jcajce.provider.asymmetric.", f3464f);
        a("org.spongycastle.jcajce.provider.asymmetric.", g);
        a("org.spongycastle.jcajce.provider.keystore.", i);
        put("X509Store.CERTIFICATE/COLLECTION", "org.spongycastle.jce.provider.X509StoreCertCollection");
        put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "org.spongycastle.jce.provider.X509StoreAttrCertCollection");
        put("X509Store.CRL/COLLECTION", "org.spongycastle.jce.provider.X509StoreCRLCollection");
        put("X509Store.CERTIFICATEPAIR/COLLECTION", "org.spongycastle.jce.provider.X509StoreCertPairCollection");
        put("X509Store.CERTIFICATE/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPCerts");
        put("X509Store.CRL/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPCRLs");
        put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPAttrCerts");
        put("X509Store.CERTIFICATEPAIR/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPCertPairs");
        put("X509StreamParser.CERTIFICATE", "org.spongycastle.jce.provider.X509CertParser");
        put("X509StreamParser.ATTRIBUTECERTIFICATE", "org.spongycastle.jce.provider.X509AttrCertParser");
        put("X509StreamParser.CRL", "org.spongycastle.jce.provider.X509CRLParser");
        put("X509StreamParser.CERTIFICATEPAIR", "org.spongycastle.jce.provider.X509CertPairParser");
        put("Cipher.BROKENPBEWITHMD5ANDDES", "org.spongycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        put("Cipher.BROKENPBEWITHSHA1ANDDES", "org.spongycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "org.spongycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        put("CertPathValidator.RFC3281", "org.spongycastle.jce.provider.PKIXAttrCertPathValidatorSpi");
        put("CertPathBuilder.RFC3281", "org.spongycastle.jce.provider.PKIXAttrCertPathBuilderSpi");
        put("CertPathValidator.RFC3280", "org.spongycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.RFC3280", "org.spongycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertPathValidator.PKIX", "org.spongycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.PKIX", "org.spongycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertStore.Collection", "org.spongycastle.jce.provider.CertStoreCollectionSpi");
        put("CertStore.LDAP", "org.spongycastle.jce.provider.X509LDAPCertStoreSpi");
        put("CertStore.Multi", "org.spongycastle.jce.provider.MultiCertStoreSpi");
        put("Alg.Alias.CertStore.X509LDAP", "LDAP");
    }

    private void a(String str, String[] strArr) {
        for (int i2 = 0; i2 != strArr.length; i2++) {
            Class<?> cls = null;
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                cls = classLoader != null ? classLoader.loadClass(str + strArr[i2] + "$Mappings") : Class.forName(str + strArr[i2] + "$Mappings");
            } catch (ClassNotFoundException unused) {
            }
            if (cls != null) {
                try {
                    ((f.b.a.a.b.a) cls.newInstance()).a(this);
                } catch (Exception e2) {
                    throw new InternalError("cannot create instance of " + str + strArr[i2] + "$Mappings : " + e2);
                }
            }
        }
    }
}
