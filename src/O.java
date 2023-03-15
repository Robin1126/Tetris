public class O extends Tetromino{
    public O() {
        cells[0] = new Cell(0, 4, jojo.O);
        cells[1] = new Cell(0, 5, jojo.O);
        cells[2] = new Cell(1, 4, jojo.O);
        cells[3] = new Cell(1, 5, jojo.O);

        states = new State[0];
    }
}
