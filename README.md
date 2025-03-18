# Tic-Tac-Toe Game

## Description

This is a simple Java-based Tic-Tac-Toe game built using `Swing` for the GUI. Players take turns placing Xs and Os on a 3x3 grid, and the game determines a winner or a tie based on the board state.

## Features

- **Graphical Interface**: The game utilizes `JFrame`, `JLabel`, `JPanel`, and `JButton` for an interactive experience.
- **Win Detection**: Checks for horizontal, vertical, and diagonal wins.
- **Play Again Option**: Allows restarting the game after a win or tie.
- **Scoreboard**: Keeps track of the total wins for Player X, Player O, and ties.
- **Layered Components**: Uses `JLayeredPane` to overlay the "Play Again" button without obstructing the game board.

## Installation & Running

1. Ensure you have Java installed (JDK 8 or higher recommended).
2. Compile the program:
   ```sh
   javac TicTacToe.java
   javac App.java
   ```
3. Run the game:
   ```sh
   java App
   ```

## How to Play

1. The game starts with Player X.
2. Players click on an empty tile to place their marker.
3. The game alternates between Player X and Player O.
4. If a player gets three in a row (horizontal, vertical, or diagonal), they win.
5. If all tiles are filled without a winner, the game is a tie.
6. Click "Play Again" to restart the game.
7. Click "Scoreboard" to view total wins and ties.
8. Click "Back to Game" on the scoreboard to return.

## Code Structure

- ``** Class**: Main game logic and GUI components.
- **Board Panel**: Contains the 3x3 grid.
- **Text Panel**: Displays the current player's turn or the game result.
- **Scoreboard Panel**: Shows the total number of wins and ties.
- **Action Listeners**: Handle button clicks, game state updates, and UI interactions.

## Possible Enhancements

- Add sound effects for clicks and win detection.
- Implement AI for single-player mode.
- Improve animations and visual effects.
- Add a menu screen for game settings.

## Author

Braxton

Enjoy the game!

