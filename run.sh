#!/bin/bash
cd src
javac -d ../bin WordLadderGUI/*.java Main/*.java Algorithms/*.java
cd ..
cd bin
# java Main.Main
java WordLadderGUI.WordLadderGUI
