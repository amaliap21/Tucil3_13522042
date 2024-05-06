package WordLadderGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.*;
import Algorithms.*;
import java.util.List;

public class WordLadderGUI extends JFrame {
    public static final Color CUSTOM_GREEN = new Color(113, 188, 120);
    public static final Color CUSTOM_RED = new Color(224, 102, 102);

    // Custom colors
    public static final Color CUSTOM_BACKGROUND = new Color(20, 33, 61);
    public static final Color CUSTOM_TEXT_COLOR = new Color(255, 255, 255);
    public static final Color CUSTOM_HIGHLIGHT_COLOR = new Color(252, 163, 17);

    private JTextField startField;
    private JTextField endField;
    private JPanel gridPanel;
    private JComboBox<String> algorithmSelection;
    private JLabel timeExecutionLabel;
    private JLabel nodesVisitedLabel;
    private JLabel pathLengthLabel;

    private UCSWordLadder ucsLadder;
    private GBFSWordLadder gbfsLadder;
    private AStarWordLadder aStarLadder;

    public WordLadderGUI() {
        try {
            ucsLadder = new UCSWordLadder("Dictionary/dictionary.txt");
            gbfsLadder = new GBFSWordLadder("Dictionary/dictionary.txt");
            aStarLadder = new AStarWordLadder("Dictionary/dictionary.txt");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Dictionary file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setTitle("Word Ladder by Amalia Putri (13522042)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.setBackground(CUSTOM_BACKGROUND);
        inputPanel.setForeground(CUSTOM_TEXT_COLOR);

        JLabel startLabel = new JLabel("Start Word:", SwingConstants.LEFT);
        startLabel.setForeground(CUSTOM_TEXT_COLOR);
        startField = new JTextField();
        startField.setBackground(Color.WHITE);

        JLabel endLabel = new JLabel("End Word:", SwingConstants.LEFT);
        endLabel.setForeground(CUSTOM_TEXT_COLOR);
        endField = new JTextField();
        endField.setBackground(Color.WHITE);

        JLabel algoLabel = new JLabel("Algorithm:", SwingConstants.LEFT);
        algoLabel.setForeground(CUSTOM_TEXT_COLOR);
        algorithmSelection = new JComboBox<>(new String[] { "Uniform Cost Search", "Greedy Best First Search", "A*" });

        // font size
        startLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        startField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        endLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        endField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        algoLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        algorithmSelection.setFont(new Font("Montserrat", Font.PLAIN, 20));

        inputPanel.add(startLabel);
        inputPanel.add(startField);
        inputPanel.add(endLabel);
        inputPanel.add(endField);
        inputPanel.add(algoLabel);
        inputPanel.add(algorithmSelection);

        // Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        timeExecutionLabel = new JLabel("Execution Time: N/A", SwingConstants.CENTER);
        nodesVisitedLabel = new JLabel("Nodes Visited: N/A", SwingConstants.CENTER);
        pathLengthLabel = new JLabel("Path Length: N/A", SwingConstants.CENTER);

        // Set text color and font size
        timeExecutionLabel.setForeground(CUSTOM_TEXT_COLOR);
        nodesVisitedLabel.setForeground(CUSTOM_TEXT_COLOR);
        pathLengthLabel.setForeground(CUSTOM_TEXT_COLOR);
        Font labelFont = new Font("Montserrat", Font.PLAIN, 20);
        timeExecutionLabel.setFont(labelFont);
        nodesVisitedLabel.setFont(labelFont);
        pathLengthLabel.setFont(labelFont);

        // Set background color for the info panel and labels
        infoPanel.setBackground(CUSTOM_BACKGROUND);
        timeExecutionLabel.setBackground(CUSTOM_BACKGROUND);
        nodesVisitedLabel.setBackground(CUSTOM_BACKGROUND);
        pathLengthLabel.setBackground(CUSTOM_BACKGROUND);

        // Set an empty border to avoid default margins
        infoPanel.setBorder(BorderFactory.createEmptyBorder());

        // Add labels to the info panel
        infoPanel.add(timeExecutionLabel);
        infoPanel.add(nodesVisitedLabel);
        infoPanel.add(pathLengthLabel);

        // Grid Panel
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 1));
        gridPanel.setBackground(CUSTOM_BACKGROUND);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(CUSTOM_BACKGROUND);

        JButton findButton = new JButton("Find Ladder");
        findButton.setBackground(CUSTOM_GREEN);
        findButton.setOpaque(true);
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findLadder();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(CUSTOM_RED);
        resetButton.setOpaque(true);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        buttonPanel.add(findButton);
        buttonPanel.add(resetButton);

        // Layout Configuration
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.NORTH);
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void findLadder() {
        String start = startField.getText().trim().toLowerCase();
        String end = endField.getText().trim().toLowerCase();
        String algorithm = (String) algorithmSelection.getSelectedItem();

        Map<String, Object> result = null;

        switch (algorithm) {
            case "Uniform Cost Search":
                result = ucsLadder.findLadder(start, end);
                break;
            case "Greedy Best First Search":
                result = gbfsLadder.findLadder(start, end);
                break;
            case "A*":
                result = aStarLadder.findLadder(start, end);
                break;
        }

        gridPanel.removeAll();

        if (result == null || result.isEmpty()) {
            timeExecutionLabel.setText("Execution Time: N/A");
            nodesVisitedLabel.setText("Nodes Visited: N/A");
            pathLengthLabel.setText("Path Length: N/A");
            JLabel noPathLabel = new JLabel("No path found.", SwingConstants.CENTER);
            noPathLabel.setForeground(CUSTOM_TEXT_COLOR);
            gridPanel.add(noPathLabel);
        } else {
            String executionTime = (String) result.get("Execution Time");
            int nodesVisited = (int) result.get("Nodes Visited");
            List<String> path = (List<String>) result.get("Path");

            timeExecutionLabel.setText("Execution Time: " + executionTime);
            nodesVisitedLabel.setText("Nodes Visited: " + nodesVisited);
            pathLengthLabel.setText("Path Length: " + path.size());

            Font customFont = new Font("Montserrat", Font.BOLD, 24);
            int boxSize = 40;
            for (String word : path) {
                JPanel wordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
                wordPanel.setBackground(CUSTOM_BACKGROUND);

                for (int i = 0; i < word.length(); i++) {
                    JLabel letterLabel = new JLabel(String.valueOf(word.charAt(i)), SwingConstants.CENTER);
                    letterLabel.setFont(customFont);
                    letterLabel.setForeground(CUSTOM_TEXT_COLOR);
                    letterLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    letterLabel.setPreferredSize(new Dimension(boxSize, boxSize));
                    letterLabel.setHorizontalAlignment(SwingConstants.CENTER);

                    if (i < end.length() && word.charAt(i) == end.charAt(i)) {
                        letterLabel.setOpaque(true);
                        letterLabel.setBackground(CUSTOM_HIGHLIGHT_COLOR);
                    }

                    wordPanel.add(letterLabel);
                }
                gridPanel.add(wordPanel);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void resetFields() {
        startField.setText("");
        endField.setText("");
        gridPanel.removeAll();
        gridPanel.revalidate();
        gridPanel.repaint();
        timeExecutionLabel.setText("Execution Time: N/A");
        nodesVisitedLabel.setText("Nodes Visited: N/A");
        pathLengthLabel.setText("Path Length: N/A");
        algorithmSelection.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WordLadderGUI::new);
    }
}
