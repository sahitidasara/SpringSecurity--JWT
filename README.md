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
