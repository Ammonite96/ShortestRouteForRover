import java.util.Objects;

public class CoordinatePoint {
    int x;
    int y;
    CoordinatePoint parent;

    public CoordinatePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoordinatePoint(int x, int y, CoordinatePoint parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CoordinatePoint getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatePoint that = (CoordinatePoint) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "CoordinatePoint{" +
                "x=" + x +
                ", y=" + y +
                ", parent=" + parent +
                '}';
    }
}
