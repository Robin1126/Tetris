public class Z extends Tetromino{
    public Z() {
        cells[0] = new Cell(1, 4, jojo.Z);
        cells[1] = new Cell(0, 3, jojo.Z);
        cells[2] = new Cell(0, 4, jojo.Z);
        cells[3] = new Cell(1, 5, jojo.Z);

        states = new State[2];
        states[0] = new State(0,0,-1,-1,-1,0,0,1);
        states[1] = new State(0,0,-1,1,0,1,1,0);
    }
}
