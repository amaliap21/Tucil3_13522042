# Variables
SRC_DIR := src
BIN_DIR := bin
MAIN_SRC := WordLadderCLI/*.java
ALGO_SRC := Algorithms/*.java
GUI_SRC := WordLadderGUI/*.java
ALGO_BIN := Algorithms/*.class
GUI_BIN := WordLadderGUI/*.class
MAIN_BIN := WordLadderCLI/*.class

# Default target
all: compile run

# Compile target
compile:
	@echo "Compiling Java files..."
	javac -d $(BIN_DIR) $(SRC_DIR)/$(GUI_SRC) $(SRC_DIR)/$(MAIN_SRC) $(SRC_DIR)/$(ALGO_SRC)

# Run target
run:
	@echo "Running WordLadderGUI..."
	cd $(BIN_DIR) && java WordLadderGUI.WordLadderGUI

# Clean target (optional)
clean:
	@echo "Cleaning up..."
	rm -rf $(BIN_DIR)/$(ALGO_BIN) $(BIN_DIR)/$(GUI_BIN) $(BIN_DIR)/$(MAIN_BIN)