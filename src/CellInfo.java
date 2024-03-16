public class CellInfo {
    private Cell cell;
    private int state;
    private double result;

    public CellInfo(Cell cell, int state, double result) {
        this.cell = cell;
        this.state = state;
        this.result = result;
    }

    public Cell getCell() {
        return cell;
    }

    public int getState() {
        return state;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "CellInfo{" +
                "cell=" + cell +
                ", state=" + state +
                ", result=" + String.format("%.2f", result) +
                '}';
    }
}
