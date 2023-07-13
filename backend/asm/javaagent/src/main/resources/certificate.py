# pip install cryptography
from cryptography.hazmat.primitives.asymmetric import rsa, padding
from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.backends import default_backend
from cryptography import x509
from cryptography.x509.oid import NameOID
from datetime import datetime, timedelta
import base64
import hashlib

# ROOT_CERTIFICATES
# https://github.com/JetBrains/marketplace-makemecoffee-plugin/blob/master/src/main/java/com/company/license/CheckLicense.java
rootCrtStr = """
-----BEGIN CERTIFICATE-----
MIIFOzCCAyOgAwIBAgIJANJssYOyg3nhMA0GCSqGSIb3DQEBCwUAMBgxFjAUBgNV
BAMMDUpldFByb2ZpbGUgQ0EwHhcNMTUxMDAyMTEwMDU2WhcNNDUxMDI0MTEwMDU2
WjAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBMIICIjANBgkqhkiG9w0BAQEFAAOC
Ag8AMIICCgKCAgEA0tQuEA8784NabB1+T2XBhpB+2P1qjewHiSajAV8dfIeWJOYG
y+ShXiuedj8rL8VCdU+yH7Ux/6IvTcT3nwM/E/3rjJIgLnbZNerFm15Eez+XpWBl
m5fDBJhEGhPc89Y31GpTzW0vCLmhJ44XwvYPntWxYISUrqeR3zoUQrCEp1C6mXNX
EpqIGIVbJ6JVa/YI+pwbfuP51o0ZtF2rzvgfPzKtkpYQ7m7KgA8g8ktRXyNrz8bo
iwg7RRPeqs4uL/RK8d2KLpgLqcAB9WDpcEQzPWegbDrFO1F3z4UVNH6hrMfOLGVA
xoiQhNFhZj6RumBXlPS0rmCOCkUkWrDr3l6Z3spUVgoeea+QdX682j6t7JnakaOw
jzwY777SrZoi9mFFpLVhfb4haq4IWyKSHR3/0BlWXgcgI6w6LXm+V+ZgLVDON52F
LcxnfftaBJz2yclEwBohq38rYEpb+28+JBvHJYqcZRaldHYLjjmb8XXvf2MyFeXr
SopYkdzCvzmiEJAewrEbPUaTllogUQmnv7Rv9sZ9jfdJ/cEn8e7GSGjHIbnjV2ZM
Q9vTpWjvsT/cqatbxzdBo/iEg5i9yohOC9aBfpIHPXFw+fEj7VLvktxZY6qThYXR
Rus1WErPgxDzVpNp+4gXovAYOxsZak5oTV74ynv1aQ93HSndGkKUE/qA/JECAwEA
AaOBhzCBhDAdBgNVHQ4EFgQUo562SGdCEjZBvW3gubSgUouX8bMwSAYDVR0jBEEw
P4AUo562SGdCEjZBvW3gubSgUouX8bOhHKQaMBgxFjAUBgNVBAMMDUpldFByb2Zp
bGUgQ0GCCQDSbLGDsoN54TAMBgNVHRMEBTADAQH/MAsGA1UdDwQEAwIBBjANBgkq
hkiG9w0BAQsFAAOCAgEAjrPAZ4xC7sNiSSqh69s3KJD3Ti4etaxcrSnD7r9rJYpK
BMviCKZRKFbLv+iaF5JK5QWuWdlgA37ol7mLeoF7aIA9b60Ag2OpgRICRG79QY7o
uLviF/yRMqm6yno7NYkGLd61e5Huu+BfT459MWG9RVkG/DY0sGfkyTHJS5xrjBV6
hjLG0lf3orwqOlqSNRmhvn9sMzwAP3ILLM5VJC5jNF1zAk0jrqKz64vuA8PLJZlL
S9TZJIYwdesCGfnN2AETvzf3qxLcGTF038zKOHUMnjZuFW1ba/12fDK5GJ4i5y+n
fDWVZVUDYOPUixEZ1cwzmf9Tx3hR8tRjMWQmHixcNC8XEkVfztID5XeHtDeQ+uPk
X+jTDXbRb+77BP6n41briXhm57AwUI3TqqJFvoiFyx5JvVWG3ZqlVaeU/U9e0gxn
8qyR+ZA3BGbtUSDDs8LDnE67URzK+L+q0F2BC758lSPNB2qsJeQ63bYyzf0du3wB
/gb2+xJijAvscU3KgNpkxfGklvJD/oDUIqZQAnNcHe7QEf8iG2WqaMJIyXZlW3me
0rn+cgvxHPt6N4EBh5GgNZR4l0eaFEV+fxVsydOQYo1RIyFMXtafFBqQl6DDxujl
FeU3FZ+Bcp12t7dlM4E0/sS1XdL47CfGVj4Bp+/VbF862HmkAbd7shs7sDQkHbU=
-----END CERTIFICATE-----
"""
rootCrt = x509.load_pem_x509_certificate(
    rootCrtStr.encode(), default_backend())
key_identifier = rootCrt.extensions[1].value.key_identifier
authority_cert_issuer = rootCrt.extensions[1].value.authority_cert_issuer
authority_cert_serial_number = rootCrt.extensions[1].value.authority_cert_serial_number

private_key = rsa.generate_private_key(65537, 4096, default_backend())
public_key = private_key.public_key()

builder = x509.CertificateBuilder()
builder = builder.subject_name(
    x509.Name([x509.NameAttribute(NameOID.COMMON_NAME, 'JetBrains'),]))
builder = builder.issuer_name(
    x509.Name([x509.NameAttribute(NameOID.COMMON_NAME, 'JetProfile CA'),]))
builder = builder.not_valid_before(datetime.today())
builder = builder.not_valid_after(datetime.today() + timedelta(18250))
builder = builder.serial_number(x509.random_serial_number())
builder = builder.public_key(public_key)
builder = builder.add_extension(x509.BasicConstraints(False, None), False)
builder = builder.add_extension(
    x509.SubjectKeyIdentifier.from_public_key(public_key), False)
builder = builder.add_extension(x509.AuthorityKeyIdentifier(
    key_identifier, authority_cert_issuer, authority_cert_serial_number), False)
builder = builder.add_extension(x509.ExtendedKeyUsage(
    [x509.oid.ExtendedKeyUsageOID.SERVER_AUTH]), False)
builder = builder.add_extension(x509.KeyUsage(
    True, True, False, False, False, False, False, False, False), False)
certificate = builder.sign(private_key, hashes.SHA256(), default_backend())

# 密钥
private_bytes = private_key.private_bytes(
    serialization.Encoding.PEM, serialization.PrivateFormat.TraditionalOpenSSL, serialization.NoEncryption())
# 证书
public_bytes = certificate.public_bytes(serialization.Encoding.PEM)

licenseId = "ZLHYWLF"
licenseeName = "zlhywlf"

# https://data.services.jetbrains.com/products
# salesCode
# AC	  -->  	https://www.jetbrains.com/objc
# CL	  -->  	https://www.jetbrains.com/clion
# CWME	  -->  	https://www.jetbrains.com/code-with-me/on-prem
# DC	  -->  	https://www.jetbrains.com/dotcover
# DB	  -->  	https://www.jetbrains.com/datagrip
# DM	  -->  	https://www.jetbrains.com/dotmemory
# DP	  -->  	https://www.jetbrains.com/profiler
# DS	  -->  	https://www.jetbrains.com/dataspell
# FL	  -->  	https://www.jetbrains.com/fleet
# GO	  -->  	https://www.jetbrains.com/go
# II	  -->  	https://www.jetbrains.com/idea
# PC	  -->  	https://www.jetbrains.com/pycharm
# PS	  -->  	https://www.jetbrains.com/phpstorm
# QA	  -->  	https://www.jetbrains.com/aqua
# RC	  -->  	https://www.jetbrains.com/resharper-cpp
# RD	  -->  	https://www.jetbrains.com/rider
# RDCPPP  -->  	https://www.jetbrains.com/lp/rider-unreal
# RM	  -->  	https://www.jetbrains.com/ruby
# RS0	  -->  	https://www.jetbrains.com/resharper
# RSU	  -->  	https://www.jetbrains.com/dotnet
# TC	  -->  	https://www.jetbrains.com/teamcity
# TCC0	  -->  	https://www.jetbrains.com/teamcity
# US	  -->  	https://www.jetbrains.com/upsource
# WS	  -->  	https://www.jetbrains.com/webstorm
# YTD	  -->  	https://www.jetbrains.com/youtrack
license = ("""
{
	"gracePeriodDays": 7,
	"metadata": "0120230606PSAN000005",
	"autoProlongated": false,
	"assigneeName": "",
	"licenseRestriction": "",
	"isAutoProlongated": false,
	"assigneeEmail": "",
	"licenseId": "%s",
	"checkConcurrentUse": false,
	"licenseeName": "%s",
	"hash": "15021354/0:-1251114717",
	"products": [{
		"code": "CL",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}, {
		"code": "DB",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}, {
		"code": "GO",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}, {
		"code": "II",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}, {
		"code": "PC",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}, {
		"code": "PS",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}, {
		"code": "WS",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}, {
		"code": "DS",
		"paidUpTo": "2066-06-06",
		"fallbackDate": "2066-06-06",
		"extended": false
	}]
}
""" % (licenseId, licenseeName)).encode()

crt = x509.load_pem_x509_certificate(public_bytes, default_backend())
private_key = serialization.load_pem_private_key(
    private_bytes, None, default_backend())
print(public_bytes.decode())
crtBase64 = ''.join(public_bytes.decode().split('\n')[1:-2])
licenseBase64 = base64.b64encode(license)
sign = private_key.sign(license, padding.PKCS1v15(), hashes.SHA1())
signBase64 = base64.b64encode(sign)
crt.public_key().verify(
    sign,
    license,
    padding=padding.PKCS1v15(),
    algorithm=hashes.SHA1(),
)
code = f"{licenseId}-{licenseBase64.decode()}-{signBase64.decode()}-{crtBase64}"
s = int.from_bytes(crt.signature, byteorder='big', signed=False)
sha = hashlib.sha256()
sha.update(crt.tbs_certificate_bytes)


def pad(b): return f"0001{'ff' * (512 - len(b) // 2 - 3)}00{b}"


r = int.from_bytes(bytes.fromhex(pad("3031300D060960864801650304020105000420" + sha.digest().hex())),
                   byteorder='big', signed=False)
pk = rootCrt.public_key().public_numbers()


print("==================================================")
print("==================================================")
print("==================================================")

print("Activation Code:", code, sep="\n")
print("Key:", f"{s},{pk.e},{pk.n}", sep="\n")
print("Value:", r, sep="\n")
