@echo off
setlocal enabledelayedexpansion

echo Setting up Java environment...
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

set MAVEN_VERSION=3.9.6
set MAVEN_URL=https://dlcdn.apache.org/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_ZIP=downloads\apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_HOME=downloads\apache-maven-%MAVEN_VERSION%

echo Checking for Maven installation...
if not exist downloads mkdir downloads

if not exist %MAVEN_ZIP% (
    echo Downloading Maven %MAVEN_VERSION%...
    powershell -Command "Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '%MAVEN_ZIP%'"
    
    echo Extracting Maven...
    powershell -Command "Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath downloads -Force"
) else (
    echo Maven download found, using existing file.
)

if not exist %MAVEN_HOME% (
    echo Maven directory not found. Extraction may have failed.
    exit /b 1
)

echo Running tests with Maven...
set MVN=%MAVEN_HOME%\bin\mvn.cmd
set MAVEN_OPTS=-Xmx512m

echo.
echo ==============================================
echo Running TestNG tests for all API endpoints
echo ==============================================
echo.

%MVN% clean test

echo.
echo Tests completed.
pause 