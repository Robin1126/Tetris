public class L extends Tetromino{
    public L() {
        cells[0] = new Cell(0, 4, jojo.L);
        cells[1] = new Cell(0, 3, jojo.L);
        cells[2] = new Cell(0, 5, jojo.L);
        cells[3] = new Cell(1, 3, jojo.L);

        states = new State[4];
        states[0] = new State(0,0,0,-1,0,1,1,0);
        states[1] = new State(0,0,-1,0,1,0,0,-1);
        states[2] = new State(0,0,0,1,0,-1,-1,0);
        states[3] = new State(0,0,1,0,-1,0,0,1);
    }
}
