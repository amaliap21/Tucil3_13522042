#!/bin/bash
cd src
javac -d ../bin WordLadderGUI/*.java WordLadderCLI/*.java Algorithms/*.java
cd ..
cd bin
# java WordLadderCLI.WordLadderCLI
java WordLadderGUI.WordLadderGUI
