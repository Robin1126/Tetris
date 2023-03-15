public class T extends Tetromino{
    public T() {
        cells[0] = new Cell(0, 4, jojo.T);
        cells[1] = new Cell(0, 3, jojo.T);
        cells[2] = new Cell(0, 5, jojo.T);
        cells[3] = new Cell(1, 4, jojo.T);

        // total 4 states
        states = new State[4];
        states[0] = new State(0,0,0,-1,0,1,1,0);
        states[1] = new State(0,0,-1,0,1,0,0,-1);
        states[2] = new State(0,0,0,1,0,-1,-1,0);
        states[3] = new State(0,0,1,0,-1,0,0,1);
    }
}
