#!/bin/sh

# Change directory to src
cd src || exit

# Compile Java files and output class files to bin directory, including Gson jar in the classpath
javac -cp ../gson-2.10.1.jar -d ../bin *.java || exit

# Change directory to bin
cd ../bin || exit

# Execute the DictionaryExtract class with Gson jar in the classpath
java -cp .:../gson-2.10.1.jar DictionaryExtract