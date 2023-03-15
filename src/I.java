public class I extends Tetromino{
    public I() {
        cells[0] = new Cell(0, 4, jojo.I);
        cells[1] = new Cell(0, 3, jojo.I);
        cells[2] = new Cell(0, 5, jojo.I);
        cells[3] = new Cell(0, 6, jojo.I);

        // total 2 states
        states = new State[2];
        // init Class State
        states[0] = new State(0,0,0,-1,0,1,0,2);
        states[1] = new State(0,0,-1,0,1,0,2,0);

    }
}
