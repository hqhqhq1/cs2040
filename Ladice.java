//Huang Qing A0221170Y  LAB07

import java.io.*;

public class Ladice {

    //modify UFDS from lecture note
    public static class UFDS {
        public int[] p;
        public int[] rank;
        public int[] size;


        public UFDS (int n){
            p = new int[n];
            rank = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }

        }

        public int findSet (int i) {
            if (p[i] == i) {
                return i;
            } else {
                p[i] = findSet(p[i]);
                return p[i];
            }
        }

        public void unionSet (int i, int j) {
            if (!isSameSet(i,j)) {
               int x = findSet(i);
               int y = findSet(j);
               if (rank[x] > rank [y]) {
                   p[y] = x;
                   size[x] = size[x] + size[y];
               } else {
                   p[x] = y;
                   size[y] = size[y] + size[x];
                   if (rank[x] == rank[y]) {
                       rank[y] = rank[y] + 1;
                   }
               }
            }
        }

        public Boolean isSameSet (int i, int j) {
            return findSet(i) == findSet(j);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String[] input = br.readLine().split(" ");
        int numItems = Integer.parseInt(input[0]);
        int numDrawer = Integer.parseInt(input[1]);

        UFDS ufds = new UFDS(numDrawer);

        for (int i = 0; i < numItems; i++) {
            String[] temp = br.readLine().split(" ");
            int first = Integer.parseInt(temp[0]);
            int second = Integer.parseInt(temp[1]);

            //union two disjoin sets
            ufds.unionSet(--first, --second);
            if (ufds.size[ufds.findSet(first)] > 0) {
                ufds.size[ufds.findSet(first)]--;
                pw.println("LADICA");
            } else {
                pw.println("SMECE");
            }


        }

        pw.close();

    }


}
