/*
    图案的父类，属性有4个cell，同样也有下降，左移，右移
 */
public class Tetromino {
    protected Cell[] cells = new Cell[4];

    // relative position to No.0 Cell
    class State {

        int row0, col0, row1, col1, row2, col2, row3, col3;

        public State() {
        }

        public State(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
        }
    }

    protected State[] states;
    protected int times = 100;

    public void leftMove() {
        for (Cell cell:cells
             ) {
            cell.left();
        }
    }

    public void rightMove() {
        for (Cell cell:cells
             ) {
            cell.right();
        }
    }

    public void dropMove() {
        for (Cell cell:cells
             ) {
            cell.drop();
        }
    }

    public static Tetromino randomCreate() {
        int type = (int)(Math.random() * 7);
        Tetromino tetromino = null;
        switch (type) {
            case 0:
                tetromino = new I();
                break;
            case 1:
                tetromino = new T();
                break;
            case 2:
                tetromino = new J();
                break;
            case 3:
                tetromino = new L();
                break;
            case 4:
                tetromino = new S();
                break;
            case 5:
                tetromino = new Z();
                break;
            case 6:
                tetromino = new O();
                break;
        }
        return tetromino;
    }

    public void rotationRight() {
        if (states.length == 0) {
            return;
        }
       times++;
        State s = states[times % states.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

}
