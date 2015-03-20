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
                    StdOut.print(pointsToPrint[0] + " -> ");
                    for (int k = 1; k <= numPoints - 1; k++) {
                        StdOut.print(pointsToPrint[k] + " -> ");
                    }
                    StdOut.println(pointsToPrint[numPoints]);
                    pointsToPrint[0].drawTo(pointsToPrint[numPoints]);
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
            StdOut.print(pointsToPrint[0] + " -> ");
            for (int k = 1; k <= numPoints - 1; k++) {
                StdOut.print(pointsToPrint[k] + " -> ");
            }
            StdOut.println(pointsToPrint[numPoints]);
            pointsToPrint[0].drawTo(pointsToPrint[numPoints]);
        }
    }

    private static void sortPoints(Point[] points , double[] slopes, Point mainPoint) {
        Point tempPoint;
        double tempSlope;
        for (int i = 0; i < slopes.length; i++) {
            for (int j = i; j > 0 && (slopes[j] < slopes[j - 1]); j--) {
                tempPoint = points[j];
                points[j] = points[j - 1];
                points[j - 1] = tempPoint;

                tempSlope = slopes[j];
                slopes[j] = slopes[j - 1];
                slopes[j - 1] = tempSlope;
            }
        }
        printAndDraw(points, slopes, mainPoint);
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

        for (int i = 0; i < numPoints; i++) {
            double[] slopes = new double[numPoints - i];
            Point[] tempPoints = new Point[numPoints - i];
            int k = i;
            for (int j = 0; j < numPoints - i; j++) {
                slopes[j] = points[i].slopeTo(points[k]);
                tempPoints[j] = points[k];
                k++;
            }
            sortPoints(tempPoints, slopes, points[i]);
        }
    }
}
