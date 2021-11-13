import java.io.*;
import java.util.HashSet;
import java.util.Stack;
import java.util.*;

public class Domino {
    public static void DFS(ArrayList<ArrayList<Integer>> grid, boolean[] set, Stack<Integer> stack, int current) {
        set[current] = true;

        for (int i = 0; i < grid.get(current).size(); i++) {
            int next = grid.get(current).get(i);
            if(!set[next]) {

                DFS(grid, set, stack, next);
            }
        }
        stack.push(current);
        return;
    }

    public static void DFS2(ArrayList<ArrayList<Integer>> grid, boolean[] set, int current) {
        set[current] = true;

        for (int i = 0; i < grid.get(current).size(); i++) {
            int next = grid.get(current).get(i);
            if(!set[next]) {
                DFS2(grid, set, next);
            }
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        //number of test cases
        int N = Integer.parseInt(br.readLine());
        while ((N--) != 0) {
            String[] input = br.readLine().split(" ");
            //n  is the number of domino tiles and the second integer m is the number of lines to follow in the test case.
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            ArrayList<ArrayList<Integer>> map1 = new ArrayList<>(n);
            ArrayList<ArrayList<Integer>> map2 = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                map1.add(new ArrayList<>());
                map2.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                String[] str = br.readLine().split(" ");
                // x and y indicating that if domino number x falls, it will cause domino number y to fall as well.
                int x = Integer.parseInt(str[0]) - 1;
                int y = Integer.parseInt(str[1]) - 1;

                map1.get(x).add(y);
                map2.get(y).add(x);
            }

            //first run
            boolean set[] = new boolean[n];
            Stack<Integer> stack = new Stack<>();
            int count = 0;

            for (int i = 0; i < n; i++) {

                if (!set[i]) {
                    count++;
                    DFS(map1, set, stack, i);
                }
            }

            //second run
            set = new boolean[n];

            int total = 0;
            while (!stack.isEmpty()) {
                int current = stack.peek();
                stack.pop();

                if (!set[current]) {
                    total = total + 1;

                    DFS2(map1, set, current);

                }
            }

            pw.println(total);


        }
        pw.close();



    }
}