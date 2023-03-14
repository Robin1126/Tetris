import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
    俄罗斯方块的主类，用于初始化游戏界面等
 */
public class Tetris extends JPanel {
    private Tetromino currentOne = Tetromino.randomCreate();
    private Tetromino nextOne = Tetromino.randomCreate();
    private Cell[][] background = new Cell[18][9];
    private static final int CELL_SIZE = 48;

    //score
    int[] score_pool = {0, 1, 2, 5, 10};
    private int totalScore = 0;
    private int totalLine = 0;

    // game status
    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int GAMEOVER = 2;
    public static int gameState;
    // string[] for show_state
    String[] show_state = {"Press P[Pause]","Press C[Continue]","Press R[Restart]"};
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage O;
    public static BufferedImage S;
    public static BufferedImage T;
    public static BufferedImage Z;
    public static BufferedImage Wall;
    // 静态代码块，类加载时自动载入，先载入上面的静态变量
    static {
        try {
            // 导入图片
            I = ImageIO.read(new File("images/I.png"));
            J = ImageIO.read(new File("images/J.png"));
            L = ImageIO.read(new File("images/L.png"));
            O = ImageIO.read(new File("images/O.png"));
            S = ImageIO.read(new File("images/S.png"));
            T = ImageIO.read(new File("images/T.png"));
            Z = ImageIO.read(new File("images/Z.png"));
            Wall = ImageIO.read(new File("images/background.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method paint
    @Override
    public void paint(Graphics g) {
        g.drawImage(Wall, 0, 0, null);
        g.translate(22,15);
        paintBackground(g);
        // paint different parts
        paintCurrent(g);
        paintNextOne(g);
        paintScore(g);
        paintGameState(g);
    }

    private void paintGameState(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        if (gameState == PLAYING) {
            g.drawString(show_state[gameState], 480, 660);
        }else if (gameState == PAUSE) {
            g.drawString(show_state[gameState], 480, 660);
        } else if (gameState == GAMEOVER) {
            g.drawString(show_state[gameState], 480, 660);
            g.setColor(Color.red);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,60));
            g.drawString("GAME OVER!",30, 450);
        }
    }


    private void paintScore(Graphics g) {
        g.setFont(new Font(Font.DIALOG, Font.BOLD,30));
        g.drawString("SCORES: " + totalScore, 480, 250);
        g.drawString("LINES: " + totalLine, 480, 440);
    }


    private void paintNextOne(Graphics g) {
        Cell[] cells = nextOne.cells;
        for (Cell cell: cells
             ) {
            int x = cell.getCol() * CELL_SIZE + 380;
            int y = cell.getRow() * CELL_SIZE + 20;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    private void paintCurrent(Graphics g) {
        Cell[] cells = currentOne.cells;
        for (Cell cell:cells
             ) {
            // position x, y
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }


    private void paintBackground(Graphics g) {
        for (int i = 0; i < background.length; i++) {
            for (int j = 0; j < background[i].length; j++) {
                // location x, y
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                Cell cell = background[i][j];
                if (cell == null) {
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                }else {
                    g.drawImage(cell.getImage(), x, y, null);
                }
            }
        }
    }

    // outOfBound for currentOne
    public boolean outOfBound() {
        Cell[] cells = currentOne.cells;
        for (Cell cell:cells
             ) {
            int col = cell.getCol();
            int row = cell.getRow();
            if (col < 0 || col > background.length - 1 || row < 0 || row > background[0].length - 1) {
                return true;
            }
        }
        return false;
    }

    // duplication Cell
    public boolean dup() {
        Cell[] cells = currentOne.cells;
        for (Cell cell: cells
             ) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (background[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    // moveLeft
    public void moveLeft() {
        currentOne.leftMove();
        if (outOfBound() || dup()){
            currentOne.rightMove();
        }
    }
    public void moveRight() {
        currentOne.rightMove();
        if (outOfBound() || dup()) {
            currentOne.leftMove();
        }
    }

    // Full?
    public boolean isFull(int row) {
        Cell[] cells = background[row];
        for (Cell cell:cells
             ) {
            if(cell == null) {
                return false;
            }
        }
        return true;
    }
    // clear lines and count scores
    public void clear() {
        int countLine = 0;
        Cell[] cells = currentOne.cells;
        for (Cell cell:cells
             ) {
            int row = cell.getRow();
            if (isFull(row)) {
                countLine++;
                for (int i = row; i > 0  ; i--) {
                    System.arraycopy(background[i-1], 0, background[i],0, background[0].length);
                }
                background[0] = new Cell[9]; // No.0
            }
        }
        totalLine += countLine;
        totalScore += score_pool[countLine];
    }

    // Gameover?
    public boolean isGameOver() {
        Cell[] cells = currentOne.cells;
        for (Cell cell:cells
             ) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (background[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        // backgroundPicture
        Tetris back = new Tetris();
        frame.add(back); // insert back into frame


        frame.setVisible(true);
        frame.setSize(810, 940);
        // center
        frame.setLocationRelativeTo(null);
        // exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
