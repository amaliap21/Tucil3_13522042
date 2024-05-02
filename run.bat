@echo off
REM Change directory to src
cd src

REM Compile Java files and output class files to bin directory, including Gson jar in the classpath
javac -cp ..\gson-2.10.1.jar -d ..\bin *.java

REM Change directory to bin
cd ..\bin

REM Execute the WordLadderGUI class with Gson jar in the classpath
java -cp .;..\gson-2.10.1.jar DictionaryExtract