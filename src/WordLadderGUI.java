import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Map;

public class WordLadderGUI extends JFrame {

    private JTextField startField;
    private JTextField endField;
    private JTextArea resultArea;
    private JComboBox<String> algorithmSelection;

    private UCSWordLadder ucsLadder;
    private GBFSWordLadder gbfsLadder;
    private AStarWordLadder aStarLadder;

    public WordLadderGUI() {
        try {
            ucsLadder = new UCSWordLadder("dictionary.txt");
            gbfsLadder = new GBFSWordLadder("dictionary.txt");
            aStarLadder = new AStarWordLadder("dictionary.txt");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Dictionary file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setTitle("Word Ladder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

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

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JButton findButton = new JButton("Find Ladder");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findLadder();
            }
        });
        add(findButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void findLadder() {
        String start = startField.getText().trim();
        String end = endField.getText().trim();
        String algorithm = (String) algorithmSelection.getSelectedItem();

        Map<String, Object> result = null;

        switch (algorithm) {
            case "UCS":
                result = ucsLadder.findLadder(start, end);
                break;
            case "GBFS":
                result = gbfsLadder.findLadder(start, end);
                break;
            case "A*":
                result = aStarLadder.findLadder(start, end);
                break;
        }

        if (result == null || result.isEmpty()) {
            resultArea.setText("No path found.");
        } else {
            StringBuilder sb = new StringBuilder();
            result.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));
            resultArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WordLadderGUI::new);
    }
}
