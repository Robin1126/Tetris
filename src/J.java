public class J extends Tetromino{
    public J() {
        cells[0] = new Cell(0, 4, jojo.J);
        cells[1] = new Cell(0, 3, jojo.J);
        cells[2] = new Cell(0, 5, jojo.J);
        cells[3] = new Cell(1, 5, jojo.J);

        states = new State[4];
        states[0] = new State(0,0,0,-1,0,1,1,1);
        states[1] = new State(0,0,-1,0,1,0,1,-1);
        states[2] = new State(0,0,0,1,0,-1,-1,-1);
        states[3] = new State(0,0,1,0,-1,0,-1,1);
    }
}
