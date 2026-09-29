# Projeto de Segurança da Informação

Projeto simples para demonstrar uma página web servida por HTTPS usando Java e um certificado local.

## Arquivos

- `ServidorHTTPS.java`: código do servidor.
- `index.html`: página exibida no navegador.
- `Link Video.txt`: link para o vídeo do projeto.

## Como executar

É necessário ter o JDK instalado. No PowerShell, dentro da pasta do projeto:

1. Confira o Java:
   ```powershell
   java -version
   ```
2. Gere um certificado local com o `keytool`:
   ```powershell
   keytool -genkeypair -alias meuservidor -keyalg RSA -keysize 2048 -validity 365 -keystore keystore.jks -dname "CN=localhost, O=MinhaFaculdade, C=BR" -storepass senha123 -keypass senha123
   ```
3. Compile e execute:
   ```powershell
   javac ServidorHTTPS.java
   java ServidorHTTPS
   ```
4. Abra no navegador o endereço HTTPS local exibido pelo servidor.

> O navegador pode alertar que o certificado não é confiável: ele foi gerado localmente para testes. A senha do exemplo é apenas para uso local; não use esse certificado ou essa senha em produção.
