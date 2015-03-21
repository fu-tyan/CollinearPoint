import java.util.Arrays;

public class Fast {

    private static void printAndDraw(Point[] points, double[] slopes, Point mainPoint) {
        int numPoints = 1;
        int indexSlope = 0;
        double tempSlope = slopes[0];
        for (int i = 0; i < slopes.length; i++) {
            if (tempSlope == slopes[i]) {
                numPoints++;
            } else {
                if (numPoints >= 3) {
                    Point[] pointsToPrint = new Point[numPoints + 1];
                    pointsToPrint[0] = mainPoint;
                    for (int k = 1; k <= numPoints; k++) {
                        pointsToPrint[k] = points[indexSlope];
                        indexSlope++;
                    }
                    Arrays.sort(pointsToPrint);
                    if (pointsToPrint[0] == mainPoint) {
                        StdOut.print(pointsToPrint[0] + " -> ");
                        for (int k = 1; k <= numPoints - 1; k++) {
                            StdOut.print(pointsToPrint[k] + " -> ");
                        }
                        StdOut.println(pointsToPrint[numPoints]);
                        pointsToPrint[0].drawTo(pointsToPrint[numPoints]);
                    }
                }
                numPoints = 1;
                indexSlope = i;
                tempSlope = slopes[i];
            }
        }
        if (numPoints >= 3 && indexSlope > 0) {
            Point[] pointsToPrint = new Point[numPoints + 1];
            pointsToPrint[0] = mainPoint;
            for (int k = 1; k <= numPoints; k++) {
                pointsToPrint[k] = points[indexSlope];
                indexSlope++;
            }
            Arrays.sort(pointsToPrint);
            if (pointsToPrint[0] == mainPoint) {
                StdOut.print(pointsToPrint[0] + " -> ");
                for (int k = 1; k <= numPoints - 1; k++) {
                    StdOut.print(pointsToPrint[k] + " -> ");
                }
                StdOut.println(pointsToPrint[numPoints]);
                pointsToPrint[0].drawTo(pointsToPrint[numPoints]);
            }
        }
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

        Arrays.sort(points);

        Point[] tempPoint = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            tempPoint[i] = points[i];
        }

        for (int i = 0; i < numPoints; i++) {
            double[] slopes = new double[numPoints];
            for (int j = 0; j < numPoints; j++) {
                slopes[j] = points[i].slopeTo(points[j]);
            }
            Arrays.sort(tempPoint, points[i].SLOPE_ORDER);
            Arrays.sort(slopes);
            printAndDraw(tempPoint, slopes, points[i]);
        }
    }
}
