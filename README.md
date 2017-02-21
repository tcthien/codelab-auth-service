# codelab-auth-service
* Generate public/private key:
- Execute:
```
keytool -genkeypair -alias jwt -keyalg RSA -dname "CN=jwt, L=Berlin, S=Berlin, C=DE" -keypass mySecretKey -keystore jwt.jks -storepass mySecretKey
```
- Execute:
```
keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
```