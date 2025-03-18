import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; //50px for text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;
    int numWinsX = 0;
    int numWinsO = 0;
    int numTies = 0;

    JButton playAgain = new JButton();
    JButton scoreBoard = new JButton();
    JLayeredPane layeredPane = new JLayeredPane();

    JFrame scoreBoardDisplay = new JFrame("Scoreboard");
    JLabel playerXScore = new JLabel();
    JLabel playerOScore = new JLabel();
    JLabel playerTies = new JLabel();
    JPanel scoresPanel = new JPanel();
    JButton backButton = new JButton();


    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        layeredPane.setPreferredSize(new Dimension(boardWidth, boardHeight));
        frame.add(layeredPane, BorderLayout.CENTER);

        playAgain.setText("Play Again");
        playAgain.setFont(new Font("Arial", Font.BOLD, 30));
        playAgain.setBounds(boardWidth / 4, boardHeight / 3, boardWidth / 2, boardHeight / 6);
        playAgain.setVisible(false); //Hidden until game ends
        layeredPane.add(playAgain, JLayeredPane.POPUP_LAYER); //Overlay layer

        scoreBoard.setText("Scoreboard");
        scoreBoard.setFont(new Font("Arial", Font.BOLD, 30));
        scoreBoard.setSize(600,50);
        bottomPanel.setBackground(Color.darkGray);
        bottomPanel.add(scoreBoard);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        boardPanel.setBounds(0, 0, boardWidth, boardHeight);
        layeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER); //Background layer
        frame.add(layeredPane);
        frame.pack();

        //Creating scoreboard frame
        scoreBoardDisplay.setVisible(false);
        scoreBoardDisplay.setSize(boardWidth,boardHeight);
        scoreBoardDisplay.setLocationRelativeTo(null);
        scoreBoardDisplay.setResizable(false);
        scoreBoardDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scoreBoardDisplay.setLayout(new BorderLayout());

        playerXScore.setBackground(Color.darkGray);
        playerXScore.setForeground(Color.white);
        playerXScore.setFont(new Font("Arial", Font.BOLD, 50));
        playerXScore.setHorizontalAlignment(JLabel.CENTER);
        playerXScore.setOpaque(true);

        playerOScore.setBackground(Color.darkGray);
        playerOScore.setForeground(Color.white);
        playerOScore.setFont(new Font("Arial", Font.BOLD, 50));
        playerOScore.setHorizontalAlignment(JLabel.CENTER);
        playerOScore.setOpaque(true);

        playerTies.setBackground(Color.darkGray);
        playerTies.setForeground(Color.white);
        playerTies.setFont(new Font("Arial", Font.BOLD, 50));
        playerTies.setHorizontalAlignment(JLabel.CENTER);
        playerTies.setOpaque(true);

        backButton.setText("Back to Game");
        backButton.setFont(new Font("Arial", Font.BOLD, 50));

        scoresPanel.setLayout(new GridLayout(4,1));
        scoresPanel.add(playerXScore);
        scoresPanel.add(playerOScore);
        scoresPanel.add(playerTies);
        scoresPanel.add(backButton, BorderLayout.SOUTH);
        scoreBoardDisplay.add(scoresPanel, BorderLayout.CENTER);


        for (int r = 0; r < 3; r++){ //loops through each tile and adds a button there
            for (int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;

                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                            
                        }
                
                    }
                });
            }
        }

        playAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                turns = 0;
                for (int r = 0; r < 3; r++){
                    for (int c = 0; c < 3; c++){
                        board[r][c].setText("");
                        board[r][c].setBackground(Color.darkGray);
                        board[r][c].setForeground(Color.white);
                    }
                }
                playAgain.setVisible(false);
                currentPlayer = playerX;
                textLabel.setText(currentPlayer + "'s turn.");
                gameOver = false;
            }
        });

        scoreBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                scoreBoardDisplay.setVisible(true);
                
                playerXScore.setText("X Wins: " + numWinsX);
                playerOScore.setText("O Wins: " + numWinsO);
                playerTies.setText("Ties: " + numTies);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                scoreBoardDisplay.setVisible(false);
            }
        });



    }

    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++){
            if (board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {

                    for (int i = 0; i < 3; i++){
                        setWinner(board[r][i]);
                    }
                    increaseWins();
                    gameOver = true;
                    playAgain.setVisible(true);
                    return;
                }
        }

        //vertical
        for (int c = 0; c < 3; c++){
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {

                    for (int i = 0; i < 3; i++) {
                        setWinner(board[i][c]);
                    }
                    increaseWins();
                    gameOver = true;
                    playAgain.setVisible(true);
                    return;
                }
        }

        //diagonal
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {

            for (int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            increaseWins();
            gameOver = true;
            playAgain.setVisible(true);
            return;
        }

        //anti-diagonal
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {

            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            increaseWins();
            gameOver = true;
            playAgain.setVisible(true);
            return;
        }

        if (turns == 9){
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            numTies++;
            gameOver = true;
            playAgain.setVisible(true);
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }

    void increaseWins(){
        if (currentPlayer == playerX) {
            numWinsX++;
        }
        else {
            numWinsO++;
        }
    }
}
