package codeSoftIntern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGameGUI extends JFrame {
    private int sys;
    private int limit;
    private int score;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton guessButton;
    private JButton quitButton;
    private JButton yesButton;
    private JButton noButton;
    private JPanel playAgainPanel;

    public NumberGameGUI() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        limit = 10;
        score = 100;

        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        quitButton = new JButton("Quit");
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int player = Integer.parseInt(inputField.getText());
                    limit--;

                    if (sys == player) {
                        outputArea.append("Congratulations, you won the game!\n");
                        outputArea.append("Your Score: " + limit * 10 + "\n");
                        guessButton.setEnabled(false);
                        showPlayAgainButtons();
                    } else if (sys < player) {
                        outputArea.append("Too High\n");
                    } else if (sys > player) {
                        outputArea.append("Too Low\n");
                    }

                    if (limit == 0) {
                        outputArea.append("You've run out of guesses. You lost the game.\n");
                        guessButton.setEnabled(false);
                        showPlayAgainButtons();
                    }

                    outputArea.append("Your Guessing Limits Now: " + limit + "\n");
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid input. Please enter a number.\n");
                }

                inputField.setText("");
                inputField.requestFocus();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the game window
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Your Guess: "));
        topPanel.add(inputField);
        topPanel.add(guessButton);
        topPanel.add(quitButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        playAgainPanel = new JPanel();
        playAgainPanel.add(new JLabel("Do you want to play again?  "));
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the game window
            }
        });

        playAgainPanel.add(yesButton);
        playAgainPanel.add(noButton);
        playAgainPanel.setVisible(false);
        add(playAgainPanel, BorderLayout.SOUTH);
    }

    private void randomNoGenerate() {
        double random = Math.random();
        double n = 1 + (random * 100);
        sys = (int) n;
    }

    private void newGame() {
        randomNoGenerate();
        limit = 10;
        score = 100;
        outputArea.setText("");
        guessButton.setEnabled(true);
        inputField.setText("");
        playAgainPanel.setVisible(false);
    }

    private void showPlayAgainButtons() {
        playAgainPanel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGameGUI game = new NumberGameGUI();
            game.setVisible(true);
            game.randomNoGenerate();
        });
    }
}
