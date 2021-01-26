
About 
=======

A minimal spring boot project with TLS/SSL connectivity


Overview
==========

Using the server.ssl.key-store (and related properties) one can configure the embedded container to only accept HTTPS connection. 
Before that you will need to have a certificate to secure your application with. Generally one can get a certificate from a certification authority like VeriSign or Let’s Encrypt. In this example the certificate is self-signed

```
keytool -genkey -keyalg RSA -alias sb-ssl-1 -keystore sb-ssl-1.pfx -storepass password -validity 3600 -keysize 4096 -storetype pkcs12
``` 

To configure the JVM's keystore the following minimal config is needed:
```
server.ssl.key-store=classpath:sb-ssl-1.pfx 
server.ssl.key-store-type=pkcs12 
server.ssl.key-store-password=password 
server.ssl.key-password=password 
server.ssl.key-alias=sb-ssl-1
```

Also this example shows how to re-direct requests http -> https

