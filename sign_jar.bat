jar ufm D:\ConnectionApplet\ConnectionApplet.jar "D:\ZTerm Applet\MANIFEST.MF"
pushd "C:\Program Files\Java\jdk1.8.0_65\bin"
jarsigner -tsa http://timestamp.digicert.com -verbose -keystore NONE -storetype PKCS11 -providerClass sun.security.pkcs11.SunPKCS11 -providerArg ./eToken.cfg "D:\ConnectionApplet\ConnectionApplet.jar" "Advance Multimedia Internet Technology Inc."
popd