public class Cell {
    public int x;
    public int y;
    public int heuristic;

    public Cell(int x, int y, int heuristic) {
        this.x = x;
        this.y = y;
        this.heuristic = heuristic;
    }

    @Override
    public String toString() {
        return ("x=" + x + " y=" + y);
    }
}
