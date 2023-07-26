//TETRIS

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TetrisGame extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private static final int TILE_SIZE = 30;
    private static final int BOARD_WIDTH = WIDTH * TILE_SIZE;
    private static final int BOARD_HEIGHT = HEIGHT * TILE_SIZE;

    private Timer timer;
    private boolean[][] board;
    private int[][] currentPiece;
    private int currentX;
    private int currentY;

    public TetrisGame() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        board = new boolean[WIDTH][HEIGHT];
        currentPiece = getRandomTetromino();
        currentX = WIDTH / 2;
        currentY = 0;

        timer = new Timer(500, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        moveDown();
        repaint();
    }

    private void moveDown() {
        if (canMove(currentPiece, currentX, currentY + 1)) {
            currentY++;
        } else {
            placePiece();
            clearLines();
            newPiece();
            if (!canMove(currentPiece, currentX, currentY)) {
                gameOver();
            }
        }
    }

    private void placePiece() {
        for (int y = 0; y < currentPiece.length; y++) {
            for (int x = 0; x < currentPiece[y].length; x++) {
                if (currentPiece[y][x] != 0) {
                    board[currentX + x][currentY + y] = true;
                }
            }
        }
    }

    private void clearLines() {
        for (int y = HEIGHT - 1; y >= 0; y--) {
            boolean lineFull = true;
            for (int x = 0; x < WIDTH; x++) {
                if (!board[x][y]) {
                    lineFull = false;
                    break;
                }
            }
            if (lineFull) {
                for (int i = y; i > 0; i--) {
                    for (int x = 0; x < WIDTH; x++) {
                        board[x][i] = board[x][i - 1];
                    }
                }
                y++; // Check the line again since it has been moved down
            }
        }
    }

    private void newPiece() {
        currentPiece = getRandomTetromino();
        currentX = WIDTH / 2;
        currentY = 0;
    }

    private boolean canMove(int[][] piece, int newX, int newY) {
        for (int y = 0; y < piece.length; y++) {
            for (int x = 0; x < piece[y].length; x++) {
                if (piece[y][x] != 0) {
                    int xCoord = newX + x;
                    int yCoord = newY + y;
                    if (xCoord < 0 || xCoord >= WIDTH || yCoord >= HEIGHT) {
                        return false;
                    }
                    if (yCoord >= 0 && board[xCoord][yCoord]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over", "Tetris", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the board
        g.setColor(Color.GRAY);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (board[x][y]) {
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Draw the current piece
        g.setColor(Color.RED);
        for (int y = 0; y < currentPiece.length; y++) {
            for (int x = 0; x < currentPiece[y].length; x++) {
                if (currentPiece[y][x] != 0) {
                    g.fillRect((currentX + x) * TILE_SIZE, (currentY + y) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                if (canMove(currentPiece, currentX - 1, currentY)) {
                    currentX--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (canMove(currentPiece, currentX + 1, currentY)) {
                    currentX++;
                }
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_UP:
                rotatePiece();
                break;
        }
        repaint();
    }

    private void rotatePiece() {
        int[][] rotatedPiece = new int[currentPiece[0].length][currentPiece.length];
        for (int y = 0; y < currentPiece.length; y++) {
            for (int x = 0; x < currentPiece[y].length; x++) {
                rotatedPiece[x][currentPiece.length - 1 - y] = currentPiece[y][x];
            }
        }
        if (canMove(rotatedPiece, currentX, currentY)) {
            currentPiece = rotatedPiece;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    private static int[][] getRandomTetromino() {
        Random random = new Random();
        int shape = random.nextInt(7); // 7 different tetrominos (shapes)
        switch (shape) {
            case 0: return new int[][]{{1, 1, 1, 1}}; // I
            case 1: return new int[][]{{1, 1, 1}, {0, 1, 0}}; // T
            case 2: return new int[][]{{1, 1, 1}, {1, 0, 0}}; // L
            case 3: return new int[][]{{1, 1, 1}, {0, 0, 1}}; // J
            case 4: return new int[][]{{1, 1, 0}, {0, 1, 1}}; // S
            case 5: return new int[][]{{0, 1, 1}, {1, 1, 0}}; // Z
            case 6: return new int[][]{{1, 1}, {1, 1}}; // O
        }
        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        TetrisGame tetris = new TetrisGame();
        frame.add(tetris);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}