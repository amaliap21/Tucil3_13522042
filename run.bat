cd src
javac -d ..\bin WordLadderGUI/*.java WordLadderCLI/*.java Algorithms/*.java
cd ..
cd bin
java WordLadderCLI.WordLadderCLI
@REM java WordLadderGUI.WordLadderGUI