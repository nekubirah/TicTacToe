import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {
    private final int BOARD_SIZE = 3;
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private boolean player1Turn = true;
    private boolean gameOver = false;
    private JLabel gameStatus = new JLabel("Spieler X ist am Zug");
    private JButton resetButton = new JButton("Neustarten");

    public TicTacToeGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(Color.GRAY);
                gamePanel.add(buttons[i][j]);
            }
        }

        add(gameStatus, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);
        resetButton.addActionListener(e -> reset());

        setVisible(true);
    }

    private void reset() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.GRAY);
            }
        }
        player1Turn = true;
        gameOver = false;
        gameStatus.setText("Spieler X ist am Zug");
        gameStatus.setForeground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().length() > 0) {
            return;
        }

        if (player1Turn) {
            clickedButton.setText("X");
            clickedButton.setBackground(Color.GREEN);
            gameStatus.setText("Spieler O ist am Zug");
        } else {
            clickedButton.setText("O");
            clickedButton.setBackground(Color.BLUE);
            clickedButton.setForeground(Color.PINK);
            gameStatus.setText("Spieler X ist am Zug");
        }

        if (checkWin()) {
            gameOver = true;
            if (player1Turn) {
                gameStatus.setText("Spieler X hat gewonnen!");
                gameStatus.setForeground(Color.GREEN);

            } else {
                gameStatus.setText("Spieler O hat gewonnen!");
                gameStatus.setForeground(Color.BLUE);
            }
            return;
        }

        if (checkDraw()) {
            gameOver = true;
            gameStatus.setText("Unentschieden!");
            return;
        }

        player1Turn = !player1Turn;
    }

    private boolean checkWin() {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
              return true;
            }
            }
            // Check diagonals
            if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
            }
            if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
            }
            return false;
            }
            private boolean checkDraw() {
              for (int i = 0; i < BOARD_SIZE; i++) {
                  for (int j = 0; j < BOARD_SIZE; j++) {
                      if (buttons[i][j].getText().equals("")) {
                          return false;
                      }
                  }
              }
              return true;
          }
          
          public static void main(String[] args) {
              new TicTacToeGame();
          }}