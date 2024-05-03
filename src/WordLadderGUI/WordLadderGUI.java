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
    public static final Color customGreen = new Color(113, 188, 120);
    public static final Color customRed = new Color(224, 102, 102);

    private JTextField startField;
    private JTextField endField;
    private JPanel gridPanel;
    private JComboBox<String> algorithmSelection;
    private JLabel timeExecutionLabel;
    private JLabel nodesVisitedLabel;

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

        setTitle("Word Ladder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Start Word:"));
        startField = new JTextField();
        inputPanel.add(startField);

        inputPanel.add(new JLabel("End Word:"));
        endField = new JTextField();
        inputPanel.add(endField);

        inputPanel.add(new JLabel("Algorithm:"));
        algorithmSelection = new JComboBox<>(new String[] { "Uniform Cost Search", "Greedy Best First Search", "A*" });
        inputPanel.add(algorithmSelection);
        add(inputPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        timeExecutionLabel = new JLabel("Execution Time: N/A");
        nodesVisitedLabel = new JLabel("Nodes Visited: N/A");
        infoPanel.add(timeExecutionLabel);
        infoPanel.add(nodesVisitedLabel);
        add(infoPanel, BorderLayout.WEST);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 1));
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton findButton = new JButton("Find Ladder");
        findButton.setBackground(customGreen);
        findButton.setOpaque(true);
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findLadder();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(customRed);
        resetButton.setOpaque(true);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startField.setText("");
                endField.setText("");
                gridPanel.removeAll();
                gridPanel.revalidate();
                gridPanel.repaint();
                timeExecutionLabel.setText("Execution Time: N/A");
                nodesVisitedLabel.setText("Nodes Visited: N/A");
                algorithmSelection.setSelectedIndex(0);
            }
        });

        buttonPanel.add(findButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void findLadder() {
        String start = startField.getText().trim();
        String end = endField.getText().trim();
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
            JLabel noPathLabel = new JLabel("No path found.");
            gridPanel.add(noPathLabel);
        } else {
            String executionTime = (String) result.get("Execution Time");
            int nodesVisited = (int) result.get("Nodes Visited");
            List<String> path = (List<String>) result.get("Path");

            timeExecutionLabel.setText("Execution Time: " + executionTime);
            nodesVisitedLabel.setText("Nodes Visited: " + nodesVisited);

            Font customFont = new Font("Arial", Font.PLAIN, 32);
            for (String word : path) {
                JPanel wordPanel = new JPanel(new GridLayout(1, word.length()));
                wordPanel.setPreferredSize(new Dimension(50 * word.length(), 50));

                for (int i = 0; i < word.length(); i++) {
                    JLabel letterLabel = new JLabel(String.valueOf(word.charAt(i)), SwingConstants.CENTER);
                    letterLabel.setFont(customFont);
                    letterLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    if (i < end.length() && word.charAt(i) == end.charAt(i)) {
                        letterLabel.setOpaque(true);
                        letterLabel.setBackground(customGreen);
                    }
                    wordPanel.add(letterLabel);
                }
                gridPanel.add(wordPanel);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WordLadderGUI::new);
    }
}
