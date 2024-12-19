Configuration plays vital role when working on Spring Security

Add the dependencies in pom.xml

\<dependency>\
			\<groupId>org.springframework.boot\</groupId>\
			\<artifactId>spring-boot-starter-security\</artifactId>\
		\</dependency>
  
  \<dependency>\
    \<groupId>io.jsonwebtoken\</groupId>\
   \<artifactId>jjwt-api\</artifactId>\
    \<version>0.11.5\</version>\
\</dependency>

\<dependency>\
    \<groupId>io.jsonwebtoken\</groupId>\
    \<artifactId>jjwt-impl\</artifactId>\
    \<version>0.11.5\</version>\
    \<scope>runtime\</scope>\
\</dependency>

\<dependency>\
    \<groupId>io.jsonwebtoken\</groupId>\
    \<artifactId>jjwt-jackson\</artifactId>
    \<version>0.11.5\</version>\
    \<scope>runtime\</scope>\
\</dependency>

Secure the applications only by accessing https requests. Generate a SSL certificate for this project like\
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12  -validity 3650

In the application.properties file add below,\
server.ssl.key-store = C://Users/sahit/.ssh/keystore.p12\
server.ssl.key-store-password = carrental //Given during certificate generation\
server.ssl.keyStoreType = PKCS12\
server.ssl.keyAlias = tomcat
