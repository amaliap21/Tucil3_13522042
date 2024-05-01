#!/bin/sh

# Navigate to the src directory
cd src || exit

# Compile Java files and put the output in the bin directory
javac -d ../bin *.java || exit

# Navigate to the bin directory
cd ../bin || exit

# Run the Java program
java Main
