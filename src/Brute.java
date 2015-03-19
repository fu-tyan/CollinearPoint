public class Brute {

    private static void printAndDraw(Point[] points) {
        points[0].drawTo(points[3]);
        StdOut.println(points[0] + " -> " + points[1] + " -> " + points[2] + " -> " + points[3]);
    }

    private static void sortPoints(Point a, Point b, Point c, Point d) {
        Point temp;
        Point[] points = new Point[4];
        points[0] = a;
        points[1] = b;
        points[2] = c;
        points[3] = d;
        for (int i = 0; i < 4; i++) {
            for (int j = i; j > 0 && (points[j].compareTo(points[j - 1]) < 0); j--) {
                temp = points[j];
                points[j] = points[j - 1];
                points[j - 1] = temp;
            }
        }
        printAndDraw(points);
    }


    public static void main(String[] args) {

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        In fileName = new In(args[0]);
        int numPoints = fileName.readInt();
        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(fileName.readInt(), fileName.readInt());
            points[i].draw();
        }

        for (int i = 0; i < numPoints - 3; i++) {
            for (int j = i + 1; j < numPoints - 2; j++) {
                for (int k = j + 1; k < numPoints - 1; k++) {
                    for (int n = k + 1; n < numPoints; n++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[n])) {
                            sortPoints(points[i], points[j], points[k], points[n]);
                        }
                    }
                }
            }
        }
    }
}
