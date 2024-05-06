package com.sandwicheese.app;

import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class App extends JFrame implements ActionListener {
    private JTextField startField, goalField;
    private JComboBox<String> methodComboBox;
    private JTable pathTable;
    private JTextArea resultArea;

    public App() {
        setTitle("Word Ladder Solver");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.PLAIN, 24);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel startLabel = new JLabel("Enter starting word:");
        startLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(startLabel, gbc);

        startField = new JTextField(15);
        startField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(startField, gbc);

        JLabel goalLabel = new JLabel("Enter goal word:");
        goalLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(goalLabel, gbc);

        goalField = new JTextField(15);
        goalField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(goalField, gbc);

        JLabel methodLabel = new JLabel("Select method:");
        methodLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(methodLabel, gbc);

        String[] methods = { "UCS", "GBFS", "A*" };
        methodComboBox = new JComboBox<>(methods);
        methodComboBox.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(methodComboBox, gbc);

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        solveButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(solveButton, gbc);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String start = startField.getText().toLowerCase();
        String goal = goalField.getText().toLowerCase();
        String method = methodComboBox.getSelectedItem().toString().toLowerCase();

        if (method.equals("a*")) {
            method = "astar";
        }

        JFrame resultFrame = new JFrame("Result");
        resultFrame.setSize(1200, 800);
        resultFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        resultFrame.setLocationRelativeTo(null);
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if (start.length() != goal.length()) {
            resultArea = new JTextArea("Words must have the same length", 5, 30);
            resultArea.setFont(new Font("Arial", Font.PLAIN, 24));
            resultArea.setLineWrap(true);
            resultArea.setWrapStyleWord(true);
            resultArea.setEditable(false);

            gbc.gridx = 0;
            gbc.gridy = 0;
            resultPanel.add(resultArea, gbc);

            resultFrame.add(resultPanel);
            resultFrame.setVisible(true);
            return;
        }

        String wordListFile = "./wordlist/" + start.length() + ".txt";
        File wordList = new File(wordListFile);

        if (!NodeUtil.isInDictionary(start, wordList) || !NodeUtil.isInDictionary(goal, wordList)) {
            resultArea = new JTextArea("Words must be in the dictionary", 5, 30);
            resultArea.setFont(new Font("Arial", Font.PLAIN, 24));
            resultArea.setLineWrap(true);
            resultArea.setWrapStyleWord(true);
            resultArea.setEditable(false);

            gbc.gridx = 0;
            gbc.gridy = 0;
            resultPanel.add(resultArea, gbc);

            resultFrame.add(resultPanel);
            resultFrame.setVisible(true);
            return;
        }

        long startTime = System.nanoTime();
        Tuple<ArrayList<String>, Integer> pathTuple = null;
        switch (method) {
            case "ucs":
                UCS ucs = new UCS(start, goal);
                pathTuple = ucs.search(wordList);
                break;
            case "gbfs":
                GBFS gbfs = new GBFS(start, goal);
                pathTuple = gbfs.search(wordList);
                break;
            case "astar":
                AStar astar = new AStar(start, goal);
                pathTuple = astar.search(wordList);
                break;
            default:
                resultArea.setText("Invalid method");
                return;
        }
        long endTime = System.nanoTime();
        if (!pathTuple.x.isEmpty()) {
            String[] columnNames = { "Step", "Word" };
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            for (int i = 0; i < pathTuple.x.size(); i++) {
                String step = Integer.toString(i);
                String word = StringUtil.convertToTitleCaseSplitting(pathTuple.x.get(i), " ");
                model.addRow(new String[] { step, word });
            }

            pathTable = new JTable(model);
            pathTable.setFont(new Font("Arial", Font.PLAIN, 24));
            pathTable.setRowHeight(30);
            pathTable.setEnabled(false);
            JScrollPane scrollPane = new JScrollPane(pathTable);
            gbc.gridx = 0;
            gbc.gridy = 0;
            resultPanel.add(scrollPane, gbc);

            resultArea = new JTextArea("Number of nodes visited: " + pathTuple.y + "\nTime taken: "
                    + (endTime - startTime) / 1000000 + " ms", 5, 30);
            resultArea.setFont(new Font("Arial", Font.PLAIN, 24));
            resultArea.setLineWrap(true);
            resultArea.setWrapStyleWord(true);
            resultArea.setEditable(false);

            gbc.gridx = 0;
            gbc.gridy = 1;
            resultPanel.add(resultArea, gbc);

            resultFrame.add(resultPanel);
            resultFrame.setVisible(true);
        } else {
            resultArea = new JTextArea("No path found from " + start + " to " + goal + "\nNumber of nodes visited: "
                    + pathTuple.y + "\nTime taken: "
                    + (endTime - startTime) / 1000000 + " ms", 5, 30);
            resultArea.setFont(new Font("Arial", Font.PLAIN, 24));
            resultArea.setLineWrap(true);
            resultArea.setWrapStyleWord(true);
            resultArea.setEditable(false);

            gbc.gridx = 0;
            gbc.gridy = 0;
            resultPanel.add(resultArea, gbc);

            resultFrame.add(resultPanel);
            resultFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        // Dark Purple
        Color primaryColor = Color.decode("#2C001E");
        // Orange
        Color secondaryColor = Color.decode("#DD4814");
        // White
        Color textWhiteColor = Color.decode("#FFFFFF");
        // Dark Gray
        Color textGrayColor = Color.decode("#333333");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        UIManager.put("Button.background", secondaryColor);
        UIManager.put("Button.foreground", textWhiteColor);
        UIManager.put("Panel.background", primaryColor);
        UIManager.put("Label.foreground", textWhiteColor);
        UIManager.put("TextField.background", textWhiteColor);
        UIManager.put("TextField.foreground", textGrayColor);
        UIManager.put("ComboBox.background", textWhiteColor);
        UIManager.put("ComboBox.foreground", textGrayColor);
        UIManager.put("ComboBox.selectionBackground", secondaryColor);
        UIManager.put("ComboBox.selectionForeground", textWhiteColor);
        UIManager.put("Table.background", textWhiteColor);
        UIManager.put("Table.foreground", textGrayColor);
        UIManager.put("Table.selectionBackground", secondaryColor);
        UIManager.put("Table.selectionForeground", textWhiteColor);
        UIManager.put("Table.gridColor", secondaryColor);
        UIManager.put("TextArea.background", textWhiteColor);
        UIManager.put("TextArea.foreground", textGrayColor);

        new App();
    }
}