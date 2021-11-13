
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class HumanCannonballRun {
    public static class Pair {
        public Double first;
        public Double second;

        public Pair(Double firstValue, Double secondValue) {
            this.first = firstValue;
            this.second = secondValue;
        }

        public double dist(Pair p) {
            return Math.sqrt(Math.pow(this.first - p.first, 2) + Math.pow(this.second - p.second, 2));
        }
    }

    public static final double INF = 999999999;
    public static ArrayList<ArrayList<Double>> grid;




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //the X and Y coordinates is where you’re currently located
        String[] inputLine1 = br.readLine().split(" ");
        double XCoordinate = Double.parseDouble(inputLine1[0]);
        double YCoordinate = Double.parseDouble(inputLine1[1]);
        Pair located = new Pair(XCoordinate, YCoordinate);


        //the real-valued X and Y coordinates of the location you’d like to reach
        String[] inputLine2 = br.readLine().split(" ");
        double xCoordinate = Double.parseDouble(inputLine2[0]);
        double yCoordinate = Double.parseDouble(inputLine2[1]);
        Pair reach = new Pair(xCoordinate, yCoordinate);

        //the number of cannons available
        int n = Integer.parseInt(br.readLine());

        ArrayList<Pair> coord = new ArrayList<>(n + 2);
        for(int i = 0; i < n + 2; i++) {
            coord.add(new Pair(0.0, 0.0));
        }

        coord.set(0, located);
        coord.set(n + 1, reach);


        for (int i = 1; i < n + 1; i++) {
            String[] input = br.readLine().split(" ");
            double x = Double.parseDouble(input[0]);
            double y = Double.parseDouble(input[1]);
            coord.set(i, new Pair(x, y));
        }

        grid = new ArrayList<>(n + 2);
        for (int i = 0; i < n + 2; i++) {
            grid.add(new ArrayList<>(n + 2));
            for (int j = 0; j < n + 2; j++) {
                if (i == j) {
                    grid.get(i).add(j, 0.0);
                } else {
                    grid.get(i).add(j, INF);
                }
            }
        }

        //time taken
        for (int i = 1; i < n + 2; i++) {
            grid.get(0).set(i,
                    (coord.get(0).dist(coord.get(i)) / 5));
        }

        for (int i = 1; i < n + 1; i++) {

            for (int j = 1; j < n + 2; j++) {
                if (i == j) {
                    continue;
                } else {
                    double d = coord.get(i).dist((coord.get(j)));
                    double launchTime = 2.0 + (Math.abs((50 - d) / 5.0));
                    double runTime = d / 5.0;
                    grid.get(i).set(j, Math.min(launchTime, runTime));
                }
            }

        }

        for (int i = 0; i < n + 2; i++) {

            for (int j = 0; j < n + 2; j++) {

                for (int k = 0; k < n + 2; k++) {

                    grid.get(j).set(k,
                            Math.min(grid.get(j).get(k), grid.get(j).get(i) + grid.get(i).get(k)));
                }
            }
        }

        double result = grid.get(0).get(n + 1);
        System.out.printf("%.6f", result);
    }

}
