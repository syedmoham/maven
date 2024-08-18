## Generate Project

```
D:\repos\java\maven\cert-verify>mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-simple -DarchetypeVersion=1.4
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] >>> maven-archetype-plugin:3.2.0:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO]
[INFO] <<< maven-archetype-plugin:3.2.0:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO]
[INFO]
[INFO] --- maven-archetype-plugin:3.2.0:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] Archetype repository not defined. Using the one from [org.apache.maven.archetypes:maven-archetype-simple:1.4] found in catalog remote
Define value for property 'groupId': com.syedm
Define value for property 'artifactId': certverify
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' com.syedm: :
Confirm properties configuration:
groupId: com.syedm
artifactId: certverify
version: 1.0-SNAPSHOT
package: com.syedm
 Y: : Y
```

## Downloading root CA Cert

Use openssl to get certificates for cnn.com as follows

```
syedm@MOHD021-AIO MINGW64 /d/repos/java/maven/certverify/certs (main)
$ openssl s_client -showcerts -connect cnn.com:443
CONNECTED(000001B4)
---
Certificate chain
 0 s:CN = cnn.com
   i:C = BE, O = GlobalSign nv-sa, CN = GlobalSign Atlas R3 DV TLS CA 2023 Q3
-----BEGIN CERTIFICATE-----
MIIKqDCCCZCgAwIBAgIQAZ3dCTUFVNcaZ4TM/m6DFTANBgkqhkiG9w0BAQsFADBY
MQswCQYDVQQGEwJCRTEZMBcGA1UEChMQR2xvYmFsU2lnbiBudi1zYTEuMCwGA1UE
AxMlR2xvYmFsU2lnbiBBdGxhcyBSMyBEViBUTFMgQ0EgMjAyMyBRMzAeFw0yMzA5
MTIxOTM4MDVaFw0yNDEwMTMxOTM4MDRaMBIxEDAOBgNVBAMMB2Nubi5jb20wggEi
MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDsZniL9RpV7hDYPJvS4TGa39w5
BLHGsPhi4lV4HVtyIme0/NMMmszIeNoY+aaDSM2dn0gw29GIq1prZSAQK8BgDU6a
otU5mWG8J+xABnn75DQ1BHjXZFl4EfjL4mIhMaVY34O+0wG06owvFDUgxRzYnwlb
y6WEJfTRyv70MF6EIq0zZxW2cMgfyuq8ZEtgYddSr4I/2/xVxACBUDFYNqYbr9AR
qmJKvzglrSYULaBJ84oY3RnBnDCVUkMW3qYT1mIDop+Jz4wLyMyvHq0QA0wY/BhI
ByhJTkdQy7xH2N8O2MohQmaVo6x6w01cqsZyIHND1JSL3lAJiMtU8aMl3+edAgMB
AAGjggeyMIIHrjCCBGcGA1UdEQSCBF4wggRaggdjbm4uY29tgg0qLmFwaS5jbm4u
Y29tggwqLmFwaS5jbm4uaW+CHSouYXBpLmVsZWN0aW9udHJhY2tlci5jbm4uY29t
ghYqLmFwaS5wbGF0Zm9ybS5jbm4uY29tghAqLmFyYWJpYy5jbm4uY29tghQqLmFy
dGVtaXMudHVybmVyLmNvbYIPKi5ibG9ncy5jbm4uY29tghgqLmNsaWVudC5hcHBs
ZXR2LmNubi5jb22CCSouY25uLmNvbYIIKi5jbm4uaW+CDyouY25uYXJhYmljLmNv
bYIOKi5jbm5tb25leS5jb22CESouY25ucG9saXRpY3MuY29tghYqLmNvbmZpZy5v
dXR0dXJuZXIuY29tghEqLmRhdGEuYXBpLmNubi5pb4IRKi5lZGl0aW9uLmNubi5j
b22CFyouZWRpdGlvbi5pLmNkbi5jbm4uY29tghwqLmVkaXRpb24uc3RhZ2UubmV4
dC5jbm4uY29tgh0qLmVkaXRpb24uc3RhZ2UyLm5leHQuY25uLmNvbYIdKi5lZGl0
aW9uLnN0YWdlMy5uZXh0LmNubi5jb22CEyouZWxlY3Rpb25zLmNubi5jb22CGSou
ZWxlY3Rpb250cmFja2VyLmNubi5jb22CDCouZ28uY25uLmNvbYIPKi5pLmNkbi5j
bm4uY29tghYqLm1hcmtldHMubW9uZXkuY25uLmlvgg8qLm1vbmV5LmNubi5jb22C
DioubmV4dC5jbm4uY29tghYqLm9kbS5wbGF0Zm9ybS5jbm4uY29tgg8qLm91dHR1
cm5lci5jb22CEioucGxhdGZvcm0uY25uLmNvbYIfKi5zZWN0aW9uLWNvbnRlbnQu
bW9uZXkuY25uLmNvbYIUKi5zdGFnZS5uZXh0LmNubi5jb22CFSouc3RhZ2UyLm5l
eHQuY25uLmNvbYIVKi5zdGFnZTMubmV4dC5jbm4uY29tghEqLnN0ZWxsYXIuY25u
LmNvbYIUKi50ZXJyYS5uZXh0LmNubi5jb22CECoudHJhdmVsLmNubi5jb22CEyou
d3d3LmkuY2RuLmNubi5jb22CD2FwaS5ldHAuY25uLmNvbYIWY2xpZW50LmFwcGxl
dHYuY25uLmNvbYINY25uYXJhYmljLmNvbYIMY25ubW9uZXkuY29tgg9jbm5wb2xp
dGljcy5jb22CDWRjZmFuZG9tZS5jb22CHGdyYXBocWwudmVydGljYWxzLmFwaS5j
bm4uaW+CFGkuY2RuLnRyYXZlbC5jbm4uY29tghlwcmV2aWV3LmRldi5tb25leS5j
bm4uY29tghhwcmV2aWV3LnFhLm1vbmV5LmNubi5jb22CGXByZXZpZXcucmVmLm1v
bmV5LmNubi5jb22CG3ByZXZpZXcudHJhaW4ubW9uZXkuY25uLmNvbYIacHJldmll
dzIucmVmLm1vbmV5LmNubi5jb22CD3VuZGVyc2NvcmVkLmNvbTAOBgNVHQ8BAf8E
BAMCBaAwHQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMB0GA1UdDgQWBBT9
Fy8eFhWRk9UjmQVNdVD8lZEhFTBXBgNVHSAEUDBOMAgGBmeBDAECATBCBgorBgEE
AaAyCgEDMDQwMgYIKwYBBQUHAgEWJmh0dHBzOi8vd3d3Lmdsb2JhbHNpZ24uY29t
L3JlcG9zaXRvcnkvMAwGA1UdEwEB/wQCMAAwgZ4GCCsGAQUFBwEBBIGRMIGOMEAG
CCsGAQUFBzABhjRodHRwOi8vb2NzcC5nbG9iYWxzaWduLmNvbS9jYS9nc2F0bGFz
cjNkdnRsc2NhMjAyM3EzMEoGCCsGAQUFBzAChj5odHRwOi8vc2VjdXJlLmdsb2Jh
bHNpZ24uY29tL2NhY2VydC9nc2F0bGFzcjNkdnRsc2NhMjAyM3EzLmNydDAfBgNV
HSMEGDAWgBTtoOYBBT40ghqkT1/FvRFBqt/zYTBIBgNVHR8EQTA/MD2gO6A5hjdo
dHRwOi8vY3JsLmdsb2JhbHNpZ24uY29tL2NhL2dzYXRsYXNyM2R2dGxzY2EyMDIz
cTMuY3JsMIIBfgYKKwYBBAHWeQIEAgSCAW4EggFqAWgAdQDuzdBk1dsazsVct520
zROiModGfLzs3sNRSFlGcR+1mwAAAYqK5qvdAAAEAwBGMEQCIE08u4H1qqO/W1OP
YxuxGftmdYvpngZDDBIKPJtwCB1qAiBjpQIgGnsX7H5wVWzxZtpff+gB6a9V+VGx
YY6hTg5eSAB2AD8XS0/XIkdYlB1lHIS+DRLtkDd/H4Vq68G/KIXs+GRuAAABiorm
rCoAAAQDAEcwRQIhAKgfE42oSB7890qz2OJXfydLzubHcsHtPNbO43Z3IsczAiBX
bvuajpVoxMlYmMHhiVS4/qF9Wd1nACXQBy3KaTen8AB3AHb/iD8KtvuVUcJhzPWH
ujS0pM27KdxoQgqf5mdMWjp0AAABiormrGkAAAQDAEgwRgIhAOCBs1ExXErb1s3+
mI53aclpYutFJSWHmbnxbw5lULlEAiEAsrJQzWT2E4w5xcoeC0Zt+nMubTJG2BG7
2KKQnHPiNlswDQYJKoZIhvcNAQELBQADggEBAGMUNah4Pw60DYWQbtlH0jFYdvNM
s+Vsh27OQEYbhE2itGWs0JvvQUDst7Y+jMHPre5NZtdmr1RnmQFoVofTvwxQxtJ4
VOqJfh2X1LTv4VrZI9m6lBLN729CDO/TKeVP9hiflVqe7faAXT8KBEFwPWE5If+z
VqSx3vPmDx+RM7OXYrVzhEmhVVjRq7yANUF+oxW64zK4zsNzYGUAyp1gmInaXKN5
XSRklj10ZrVHcd0XLuAME/9+54Bm7TvRfI46hfCfu6FbQPIX3gg+5j+MZJSdIuQJ
dzXhMVAQYlpu27381/Ts2SuDx6v/cZ8lV8D5o/xTtCpWAnLxM2bxSyVnYbk=
-----END CERTIFICATE-----
 1 s:C = BE, O = GlobalSign nv-sa, CN = GlobalSign Atlas R3 DV TLS CA 2023 Q3
   i:OU = GlobalSign Root CA - R3, O = GlobalSign, CN = GlobalSign
-----BEGIN CERTIFICATE-----
MIIEjzCCA3egAwIBAgIQfofCoyWydLv6vJDWxShlXjANBgkqhkiG9w0BAQsFADBM
MSAwHgYDVQQLExdHbG9iYWxTaWduIFJvb3QgQ0EgLSBSMzETMBEGA1UEChMKR2xv
YmFsU2lnbjETMBEGA1UEAxMKR2xvYmFsU2lnbjAeFw0yMzA0MTkwMzUyMzJaFw0y
NTA0MTkwMDAwMDBaMFgxCzAJBgNVBAYTAkJFMRkwFwYDVQQKExBHbG9iYWxTaWdu
IG52LXNhMS4wLAYDVQQDEyVHbG9iYWxTaWduIEF0bGFzIFIzIERWIFRMUyBDQSAy
MDIzIFEzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoH0SSUPDlZS1
Mc7W61LrUP+4J4g3vOnyefyPHSF4jWAXvjS1jo4ZGE7jvPc3H7w/RlH6bQw9tgis
BXq/mHMwdBA3XEPQyZcoT2Tqf5YZ7Nl2/os9AE2XZ9+l3KFSw9toZJNbKry6vJau
obJtqu1zC67LT+LrP6z66udM5x3xZk8JYWb8asI8gVdUm5o7kkearoN8PEBnIQHI
xw6hsd+VZXwWV82O+XvTFpXLsFE/wYrsA7/PcodHVDzaNXbf3ppyRoZuatX1vAH0
GWDIZnIpqLf7yerhL5+ILWAACnQ77HcV6eS8Dp9pfbqQxTGylh1ynQ/IqOzKQwtA
h+tDbAYcdwIDAQABo4IBXzCCAVswDgYDVR0PAQH/BAQDAgGGMB0GA1UdJQQWMBQG
CCsGAQUFBwMBBggrBgEFBQcDAjASBgNVHRMBAf8ECDAGAQH/AgEAMB0GA1UdDgQW
BBTtoOYBBT40ghqkT1/FvRFBqt/zYTAfBgNVHSMEGDAWgBSP8Et/qC5FJK5NUPpj
move4t0bvDB7BggrBgEFBQcBAQRvMG0wLgYIKwYBBQUHMAGGImh0dHA6Ly9vY3Nw
Mi5nbG9iYWxzaWduLmNvbS9yb290cjMwOwYIKwYBBQUHMAKGL2h0dHA6Ly9zZWN1
cmUuZ2xvYmFsc2lnbi5jb20vY2FjZXJ0L3Jvb3QtcjMuY3J0MDYGA1UdHwQvMC0w
K6ApoCeGJWh0dHA6Ly9jcmwuZ2xvYmFsc2lnbi5jb20vcm9vdC1yMy5jcmwwIQYD
VR0gBBowGDAIBgZngQwBAgEwDAYKKwYBBAGgMgoBAzANBgkqhkiG9w0BAQsFAAOC
AQEAeCQukRcvr4vivbdfLulvgh+u4njzq5VVLa+6jUup8grih9tHMu+qUAo0HY56
2lBYdrF2c6UwTIv7nLPVuxtxFu/+iznc6q26uzaMo/EHIC1P1nspO86nq8fh6Aqq
pn0eENfADthF/JaLbQ27DfkrrV5w/wgz9TH1TfYy3/jF6yTaZk6C97E0iR2HAhBR
dknoZ2Nd+Lb2W1BB9o5umEeMgw4LsVU5HPEmEh6fj/zIAo3CO0EGkc+Fs0zToMQx
cUjA64nXmL0P09OLMnT/ObJhnftZQsRUFhoCdnSIP4p2RwjKbGQXpFeOih8hNTJ3
2LduNzj/aVv/K6qMH95aCmxINw==
-----END CERTIFICATE-----
---
Server certificate
subject=CN = cnn.com

issuer=C = BE, O = GlobalSign nv-sa, CN = GlobalSign Atlas R3 DV TLS CA 2023 Q3

---
No client certificate CA names sent
Peer signing digest: SHA256
Peer signature type: RSA-PSS
Server Temp Key: X25519, 253 bits
---
SSL handshake has read 4452 bytes and written 373 bytes
Verification: OK
---
New, TLSv1.3, Cipher is TLS_AES_128_GCM_SHA256
Server public key is 2048 bit
Secure Renegotiation IS NOT supported
Compression: NONE
Expansion: NONE
No ALPN negotiated
Early data was not sent
Verify return code: 0 (ok)
---
```

Take last certificate listed and save it to a new file called rootCA.crt using vi editor

## Creation of TrustStore

In windows command prompt run following command to create truststore

```
D:\repos\java\maven\certverify\certs>"c:\program files\java\jdk-11.0.11\bin\keytool" -importcert -file rootCA.crt -alias rootCA -keystore truststore.jks -storepass changeit
Owner: CN=GlobalSign Atlas R3 DV TLS CA 2023 Q3, O=GlobalSign nv-sa, C=BE
Issuer: CN=GlobalSign, O=GlobalSign, OU=GlobalSign Root CA - R3
Serial number: 7e87c2a325b274bbfabc90d6c528655e
Valid from: Tue Apr 18 23:52:32 EDT 2023 until: Fri Apr 18 20:00:00 EDT 2025
Certificate fingerprints:
         SHA1: 55:8A:B0:B7:56:E0:CF:22:F0:2C:29:C6:E7:33:BE:F0:FB:32:41:2E
         SHA256: CA:AB:39:1E:48:2E:E1:36:BE:74:E3:FA:D9:E3:A1:AC:58:9E:BE:06:0B:95:50:85:94:87:62:93:EF:87:E7:CE
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions:

#1: ObjectId: 1.3.6.1.5.5.7.1.1 Criticality=false
AuthorityInfoAccess [
  [
   accessMethod: ocsp
   accessLocation: URIName: http://ocsp2.globalsign.com/rootr3
,
   accessMethod: caIssuers
   accessLocation: URIName: http://secure.globalsign.com/cacert/root-r3.crt
]
]

#2: ObjectId: 2.5.29.35 Criticality=false
AuthorityKeyIdentifier [
KeyIdentifier [
0000: 8F F0 4B 7F A8 2E 45 24   AE 4D 50 FA 63 9A 8B DE  ..K...E$.MP.c...
0010: E2 DD 1B BC                                        ....
]
]

#3: ObjectId: 2.5.29.19 Criticality=true
BasicConstraints:[
  CA:true
  PathLen:0
]

#4: ObjectId: 2.5.29.31 Criticality=false
CRLDistributionPoints [
  [DistributionPoint:
     [URIName: http://crl.globalsign.com/root-r3.crl]
]]

#5: ObjectId: 2.5.29.32 Criticality=false
CertificatePolicies [
  [CertificatePolicyId: [2.23.140.1.2.1]
[]  ]
  [CertificatePolicyId: [1.3.6.1.4.1.4146.10.1.3]
[]  ]
]

#6: ObjectId: 2.5.29.37 Criticality=false
ExtendedKeyUsages [
  serverAuth
  clientAuth
]

#7: ObjectId: 2.5.29.15 Criticality=true
KeyUsage [
  DigitalSignature
  Key_CertSign
  Crl_Sign
]

#8: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: ED A0 E6 01 05 3E 34 82   1A A4 4F 5F C5 BD 11 41  .....>4...O_...A
0010: AA DF F3 61                                        ...a
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore

D:\repos\java\maven\certverify\certs>cd ..
```

## Verify truststore created and password within
D:\repos\java\maven\certverify\certs>"c:\program files\java\jdk-11.0.11\bin\keytool" -list -keystore truststore.jks
Enter keystore password:

Keystore type: PKCS12
Keystore provider: SUN

Your keystore contains 1 entry

rootca, May 29, 2024, trustedCertEntry,
Certificate fingerprint (SHA-256): CA:AB:39:1E:48:2E:E1:36:BE:74:E3:FA:D9:E3:A1:AC:58:9E:BE:06:0B:95:50:85:94:87:62:93:EF:87:E7:CE


## Save cnn.com cert

From output of openssl take first cert and save it as cnncert.crt

# Get CRL Distribution point

$ openssl x509 -in rootCA.crt -noout -text | grep -A 4 'CRL Distribution Points'
            X509v3 CRL Distribution Points:

                Full Name:
                  URI:http://crl.globalsign.com/root-r3.crl


# Download CRL File 

syedm@MOHD021-AIO MINGW64 /d/repos/java/maven/certverify/certs (main)
$ curl -o crlfile.crl http://crl.globalsign.com/root-r3.crl
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  2157  100  2157    0     0  13397      0 --:--:-- --:--:-- --:--:-- 13481


# Download chase cert and issuing certs using openssl as earlier
files are as follows

chase-entrust-l1m.crt
chasecert.crt
entrustg2-entrustg2.crt


# Import crt files to truststore

D:\repos\java\maven\certverify\certs>"c:\program files\java\jdk-11.0.11\bin\keytool" -importcert -file chase-entrust-l1m.crt -alias entrust-l1m -keystore truststore.jks -storepass changeit

D:\repos\java\maven\certverify\certs>"c:\program files\java\jdk-11.0.11\bin\keytool" -importcert -file entrustg2-entrustg2.crt -alias entrustg2 -keystore truststore.jks -storepass changeit



