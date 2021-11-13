import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

//Huang Qing A0221170Y  LAB07
public class MillionaireMadness {

    public static class CoinsStack implements Comparable<CoinsStack> {
        public int row;
        public int column;
        public int cost;

        public CoinsStack(int row, int column, int cost) {
            this.row = row;
            this.column = column;
            this.cost = cost;
        }

        @Override
        public int compareTo(CoinsStack other) {
            return this.cost - other.cost;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String[] input = br.readLine().split(" ");
        //length M, width N of the vault
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        //create the vault layout
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        boolean[] bool = new boolean[M * N];

        for (int i = 0; i < M; i++) {
            grid.add(new ArrayList<Integer>(M));
            String[] strArr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                grid.get(i).add(j, Integer.parseInt(strArr[j]));
            }
        }

        PriorityQueue<CoinsStack> queue = new PriorityQueue<>();
        queue.add(new CoinsStack(0,0,0));
        int result = 0;

        while(!queue.isEmpty()) {
            //go to next
            CoinsStack cur = queue.peek();
            queue.poll();

            //check whether visited

            int location = cur.row * N + cur.column;
            if(bool[location]) {
                continue;
            }
            if(cur.row == M - 1 && cur.column == N - 1) {
                result = cur.cost;
            }
            bool[location] = true;

            // add neighbours to the queue
            if (cur.row > 0 && !bool[(cur.row - 1) * N + cur.column]) {
                queue.add(new CoinsStack(cur.row - 1, cur.column,
                        Math.max(cur.cost, grid.get(cur.row - 1).get(cur.column) - grid.get(cur.row).get(cur.column))));
            }

            if (cur.row < M - 1 && !bool[(cur.row + 1) * N + cur.column]) {
                queue.add(new CoinsStack(cur.row + 1, cur.column,
                        Math.max(cur.cost, (grid.get(cur.row + 1).get(cur.column) - grid.get(cur.row).get(cur.column)))));
            }

            if (cur.column < N - 1 && !bool[cur.row * N + cur.column + 1]) {
                queue.add(new CoinsStack(cur.row, cur.column + 1,
                        Math.max(cur.cost, (grid.get(cur.row).get(cur.column + 1) - grid.get(cur.row).get(cur.column)))));
            }

            if (cur.column > 0 && !bool[cur.row * N + cur.column - 1]) {
                queue.add(new CoinsStack(cur.row, cur.column - 1,
                        Math.max(cur.cost, (grid.get(cur.row).get(cur.column - 1) - grid.get(cur.row).get(cur.column)))));
            }


        }

        //output result of the total cost add up
        pw.println(result);
        pw.close();

    }



}
