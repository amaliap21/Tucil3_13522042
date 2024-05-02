# Variables
SRC_DIR = src
BIN_DIR = bin
GSON_JAR = gson-2.10.1.jar
CLASSPATH = $(BIN_DIR):$(GSON_JAR)
JAVA_FILES = $(wildcard $(SRC_DIR)/*.java)
MAIN_CLASS = DictionaryExtract

# Default target to compile and run the Java program
all: $(BIN_DIR)/$(MAIN_CLASS).class
	@echo "Running $(MAIN_CLASS)..."
	java -cp $(CLASSPATH) $(MAIN_CLASS)

# Compile Java files
$(BIN_DIR)/$(MAIN_CLASS).class: $(JAVA_FILES)
	@echo "Compiling Java files..."
	mkdir -p $(BIN_DIR)
	javac -cp $(GSON_JAR) -d $(BIN_DIR) $(JAVA_FILES)

# Clean up the bin directory
clean:
	@echo "Cleaning up..."
	rm -rf $(BIN_DIR)/*.class

# Run the Makefile with the 'clean' target first, then the default 'all' target
rebuild: clean all