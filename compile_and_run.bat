@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

echo Creating directories...
mkdir target\classes 2>nul

echo Compiling Java17Test...
"%JAVA_HOME%\bin\javac.exe" -d target\classes src\main\java\com\api\automation\Java17Test.java

echo Running Java17Test...
"%JAVA_HOME%\bin\java.exe" -cp target\classes com.api.automation.Java17Test

echo Done.
pause 