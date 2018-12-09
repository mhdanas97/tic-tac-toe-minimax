public class Cell {
    public int x;
    public int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return ("x=" + x + " y=" + y);
    }
}
