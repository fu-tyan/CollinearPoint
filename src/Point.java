import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (that.x == this.x) {
            if (that.y == this.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }
        if (that.y == this.y) {
            return +0.00;
        }
        return ((double) (that.y - this.y) / (that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (that.y == this.y) {
            if (that.x < this.x) {
                return 1;
            }
            if (that.x > this.x) {
                return -1;
            }
            return 0;
        }
        else if (that.y < this.y) {
            return 1;
        } else {
            return -1;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class SlopeOrder implements Comparator<Point> {

        public int compare(Point a, Point b) {
            double slopeA = slopeTo(a);
            double slopeB = slopeTo(b);
            if (slopeA == slopeB) {
                return 0;
            } else if (slopeA < slopeB) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */

    }
}

