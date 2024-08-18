    package com.syedm;

    import java.io.ByteArrayInputStream;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.net.URL;
    import java.security.KeyStore;
    import java.security.cert.*;
    import java.util.*;
    import java.security.cert.CertificateFactory;
    import java.security.cert.X509Certificate;
    import javax.net.ssl.TrustManagerFactory;
    import javax.net.ssl.X509TrustManager;

    import org.bouncycastle.asn1.ASN1InputStream;
    import org.bouncycastle.asn1.ASN1Primitive;
    import org.bouncycastle.asn1.DERIA5String;
    import org.bouncycastle.asn1.DEROctetString;
    import org.bouncycastle.asn1.x509.CRLDistPoint;
    import org.bouncycastle.asn1.x509.DistributionPoint;
    import org.bouncycastle.asn1.x509.DistributionPointName;
    import org.bouncycastle.asn1.x509.GeneralName;
    import org.bouncycastle.asn1.x509.GeneralNames;
    import org.bouncycastle.asn1.x509.Extension;


    public class CertificateVerifier {

        private static final String CRL_PATH = "certs/crlfile.crl";

        public static void main(String[] args) {
            try {
                String trustStorePath = System.getProperty("javax.net.ssl.trustStore");
                String trustStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");

                System.setProperty("javax.net.ssl.trustStore", "certs/truststore.jks");
                System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
                // Get the default TrustManager
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmf.init((KeyStore) null); // Use default trust store

                trustStorePath = System.getProperty("javax.net.ssl.trustStore");
                trustStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");
                System.out.println("trust store file : "  + trustStorePath + ", trust store pass : " + trustStorePassword);

                // Get the default X509TrustManager
                X509Certificate certificate = (X509Certificate) loadCertificate("certs/cnncert.crt");
                // X509Certificate certificate = (X509Certificate) loadCertificate("certs/chasecert.crt");
                boolean isVerified = verifyCertificate(tmf.getTrustManagers(), certificate);

                if (isVerified) {
                    System.out.println("Certificate is verified against truststore.");

                    System.out.println("------------- PROPERTIES --------------");
                    System.out.println("Subject             : " + certificate.getSubjectX500Principal());
                    System.out.println("Issuer              : " + certificate.getIssuerX500Principal());
                    System.out.println("Serial Number       : " + certificate.getSerialNumber());

                    boolean isRevoked = checkRevocation(certificate);
                    if (isRevoked) {
                        System.out.println("Certificate is revoked.");
                    } else {
                        System.out.println("Certificate is not revoked.");
                    }
                } else {
                    System.out.println("Certificate verification failed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static boolean verifyCertificate(javax.net.ssl.TrustManager[] trustManagers, X509Certificate targetCertificate) {
            boolean rc = false;
            for (javax.net.ssl.TrustManager trustManager : trustManagers) {
                if (trustManager instanceof javax.net.ssl.X509TrustManager) {
                    X509TrustManager x509Tm = (X509TrustManager) Arrays.stream(trustManagers)
                            .filter(tm -> tm instanceof X509TrustManager)
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("No X509TrustManager found"));

                    for (X509Certificate trustedCert : x509Tm.getAcceptedIssuers()) {
                        try {
                            targetCertificate.verify(trustedCert.getPublicKey());
                            System.out.println("Issuing CA Root Cert found:");
                            System.out.println("Issuer : " + trustedCert.getIssuerX500Principal() + ", Subject : " + trustedCert.getSubjectX500Principal());
                            rc = true;
                        } catch (Exception e) {
                            System.out.println("CA not issuer:");
                            System.out.println("Issuer : " + trustedCert.getIssuerX500Principal() + ", Subject : " + trustedCert.getSubjectX500Principal());
                        }
                    }
                }
            }
            return rc;
        }

        private static Certificate loadCertificate(String certPath) throws Exception {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            FileInputStream fis = new FileInputStream(certPath);
            Certificate certificate = cf.generateCertificate(fis);
            fis.close();
            return certificate;
        }

        public static boolean isCertificateRevoked(X509Certificate cert, X509CRL crl)  {
            X509CRLEntry revokedEntry = crl.getRevokedCertificate(cert.getSerialNumber());
            return revokedEntry != null;
        }

        private static boolean checkRevocation(X509Certificate cert) throws CertificateException, CRLException, IOException, Exception {
            boolean rc = false;
            List<String> crlUrlList = getCRLDistributionPoints(cert);
            for (String crlUrlString : crlUrlList) {
                URL crlUrl = new URL(crlUrlString);

                try(InputStream inStream = crlUrl.openStream())  {
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    X509CRL crl = (X509CRL) cf.generateCRL(inStream);
                    if (isCertificateRevoked(cert, crl))  {
                        rc = true;
                    }
                }
            }
            return rc;
        }

        private static List<CRL> loadCRLsFromFile() throws CertificateException, CRLException, IOException {
            List<CRL> crls = new ArrayList<>();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            FileInputStream fis = new FileInputStream(CRL_PATH);
            crls.add(cf.generateCRL(fis));
            fis.close();
            return crls;
        }

        public static List<String> getCRLDistributionPoints(X509Certificate cert) throws Exception {
            List<String> crlUrls = new ArrayList<>();

            try {
                byte[] crlDistributionPointsExt = cert.getExtensionValue(Extension.cRLDistributionPoints.getId());
                if (crlDistributionPointsExt == null) {
                    return null;
                }

                ASN1InputStream asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(crlDistributionPointsExt));
                CRLDistPoint crlDistPoint = CRLDistPoint.getInstance(asn1InputStream.readObject());
                asn1InputStream.close();

                for (DistributionPoint dp : crlDistPoint.getDistributionPoints()) {
                    DistributionPointName dpn = dp.getDistributionPoint();
                    if (dpn != null) {
                        if (dpn.getType() == DistributionPointName.FULL_NAME) {
                            GeneralNames gns = (GeneralNames) dpn.getName();
                            for (GeneralName gn : gns.getNames()) {
                                if (gn.getTagNo() == GeneralName.uniformResourceIdentifier) {
                                    String url = gn.getName().toString();
                                    crlUrls.add(url);
                                }
                            }
                        }
                    }
                }
            }
            catch(Exception ex)   {
                System.out.println("Exception in getCRLDistributionPoints" + ex);
            }
            return crlUrls;
        }

        /*

        public static List<String> getCRLDistributionPoints(X509Certificate cert) throws Exception {
            byte[] crlDistributionPointsExt = cert.getExtensionValue(Extension.cRLDistributionPoints.getId());
            if (crlDistributionPointsExt == null) {
                return null;
            }

            ASN1InputStream asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(crlDistributionPointsExt));

            ASN1Primitive derObjCrlDP = asn1InputStream.readObject();
            DEROctetString dosCrlDP = (DEROctetString) derObjCrlDP;

            if (dosCrlDP == null)  {
                System.out.println("dosCrlDP is null");
                return null;
            }

            asn1InputStream.close();

            byte[] crldpExtOctets = dosCrlDP.getOctets();
            ASN1InputStream oAsnInStream2 = new ASN1InputStream(new ByteArrayInputStream(crldpExtOctets));
            ASN1Primitive derObj2 = oAsnInStream2.readObject();
            CRLDistPoint distPoint = CRLDistPoint.getInstance(derObj2);

            oAsnInStream2.close();

            List<String> crlUrls = new ArrayList<String>();
            for (DistributionPoint dp : distPoint.getDistributionPoints())
            {
                DistributionPointName dpn = dp.getDistributionPoint();
                // Look for URIs in fullName
                if (dpn != null)
                {
                    if (dpn.getType() == DistributionPointName.FULL_NAME)
                    {
                        GeneralName[] genNames = GeneralNames.getInstance(dpn.getName()).getNames();
                        // Look for an URI
                        for (int j = 0; j < genNames.length; j++)
                        {
                            if (genNames[j].getTagNo() == GeneralName.uniformResourceIdentifier)
                            {
                                String url = DERIA5String.getInstance(genNames[j].getName()).getString();
                                crlUrls.add(url);
                            }
                        }
                    }
                }
            }

            for (String url : crlUrls)
                System.out.println(url);

            return crlUrls;
        }
         */
    }

