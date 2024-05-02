# Read the contents of the file
input_file_path = "extracted-word.txt"
output_file_path = "unique_words.txt"

# Initialize an empty set to store unique words
unique_words = set()

# Read and process the file
with open(input_file_path, 'r') as file:
    for line in file:
        word = line.strip()
        unique_words.add(word)

# Write the unique words to the output file
with open(output_file_path, 'w') as file:
    for word in sorted(unique_words):
        file.write(word + '\n')

print(f"Unique words have been written to {output_file_path}")
