
@REM javac --module-path "C:\Program Files\Java\javafx-sdk-18.0.1\lib" --add-modules javafx.controls -d ./bin --source-path "src" src/Server2.java src/Client2.java src/MultiThread2.java src/Client.java

javac --module-path "C:\Program Files\Java\javafx-sdk-18.0.1\lib" --add-modules javafx.controls -d ./bin --source-path "src" src/HandshakeServer.java src/ClientGUI.java
java -cp bin HandshakeServer


@REM javac -d ./bin --source-path "src" src/Main.java

@REM java --module-path "C:\Program Files\Java\javafx-sdk-18.0.1\lib" --add-modules javafx.controls -classpath "bin" Main
@REM java  -cp "bin" Main


@REM java Server2

@REM java --module-path "C:\Program Files\Java\javafx-sdk-18.0.1\lib" --add-modules javafx.controls Client2