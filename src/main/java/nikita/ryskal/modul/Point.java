package nikita.ryskal.modul;

import java.util.Objects;

public class Point {
    float x;
    float y;
    int r;
    boolean isHit;

    public Point(float x, float y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        isHit = setIsHit();
    }

    private boolean setIsHit() {
        return checkSquare() || checkCircle() || checkTriangle();
    }

    private boolean checkTriangle() {
        return 0 <= x && x <= (float) r / 2 && (float) -r / 2 <= y && y <= 0 && Math.abs(y) + x <= (float) r / 2;
    }

    private boolean checkCircle() {
        return x * x + y * y <= r * r && -r <= x && x <= 0 && 0 >= y && y >= -r;
    }

    private boolean checkSquare() {
        return -r <= x && x <= 0 && 0 <= y && y <= r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Float.compare(x, point.x) == 0 && Float.compare(y, point.y) == 0 && Float.compare(r, point.r) == 0 && isHit == point.isHit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, isHit);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + ", r=" + r + ", isHit=" + isHit + '}';
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean getHit() {
        return isHit;
    }
}
