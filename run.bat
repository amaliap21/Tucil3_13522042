cd src
javac -d ..\bin WordLadderGUI/*.java WordLadderCLI/*.java Algorithms/*.java
cd ..
cd bin
@REM java WordLadderCLI.WordLadderCLI
java WordLadderGUI.WordLadderGUI