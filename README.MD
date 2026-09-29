Passo 1
java -version

Passo 2

& "C:\Program Files\Java\JDK-21\bin\keytool" -genkeypair -alias meuservidor -keyalg RSA -keysize 2048 -validity 365 -keystore keystore.jks -dname "CN=localhost, O=MinhaFaculdade, C=BR" -storepass senha123 -keypass senha123

Passo 3
& "C:\Program Files\Java\JDK-21\bin\keytool" -list -v -keystore keystore.jks -storepass senha123

Passo 4
javac ServidorHTTPS.java

Passo 5
java ServidorHTTPS

Passo 6
Navegador