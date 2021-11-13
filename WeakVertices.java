import java.util.*;
/*
Store the graph in ArrayList<ArrayList<Integer>> using double nested for loop to run through all the inputs

Run through each graph, for each node set boolean isWeakVertice = true;
Use nested for loop to check each of its connection(adjacent), if its two different neighbors j and k = 1,
set Boolean isWeakVertice=false

Print out node that isWeakVertice=true
 */
//Huang Qing A0221170Y  LAB07
public class WeakVertices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n == -1) {
                break;
            }

            ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<Integer>(n));

                for (int j = 0; j < n; j++) {
                    int input = sc.nextInt();
                    graph.get(i).add(j, input);
                }
            }


            for (int i = 0; i < n; i++) {
                boolean isWeakVertice = true;

                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (i != j && i != k && j != k
                                && graph.get(i).get(j) == 1
                                && graph.get(i).get(k) == 1
                                && graph.get(j).get(k) == 1) {
                            isWeakVertice = false;
                        }
                    }
                }

                if (isWeakVertice) {
                    System.out.println(i + " ");
                }
            }

            System.out.println("\n");

        }

        sc.close();
    }

}
