import java.util.*;
//Huang Qing A0221170Y  LAB07

public class Island {
    // used to check for neighbours
    static final ArrayList<Integer> rowBound = new ArrayList<Integer>(List.of(-1, 0, 1, 0));
    static final ArrayList<Integer> columnBound = new ArrayList<Integer>(List.of(0, 1, 0, -1));

    public static void check(ArrayList<ArrayList<Boolean>> find, ArrayList<ArrayList<Character>> map,
                             ArrayList<ArrayList<Integer>> counting, int i, int j, int count) {
        for(int k = 0; k < 4; k++) {
            int nRow = i + rowBound.get(k);
            int nColumn = j + columnBound.get(k);
            if (nRow >= 0 && nColumn >= 0 && nRow < map.size() && nColumn < map.get(0).size()
                    && !find.get(nRow).get(nColumn) && (map.get(nRow).get(nColumn) == 'L' || map.get(nRow).get(nColumn) == 'C')) {
                find.get(nRow).set(nColumn, true);
                counting.get(nRow).set(nColumn, count);
                map.get(nRow).set(nColumn, 'L');
                check(find, map, counting, nRow, nColumn, count);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        //creating the map
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        //used to store counting for island
        ArrayList<ArrayList<Integer>> counting = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> find = new ArrayList<>();

        //fill in the map
        for (int i = 0; i < r; i++) {
            map.add(new ArrayList<Character>(r));
            counting.add(new ArrayList<>(r));
            find.add(new ArrayList<>(r));

            String str = sc.next();

            for (int j =0; j < c; j++) {
                char ch;
                ch = str.charAt(j);
                map.get(i).add(j, ch);
                counting.get(i).add(j, 0);
                find.get(i).add(j, false);
            }
        }
        int count = 0;
        for (int i = 0; i < r; i ++) {
            for (int j =0; j < c; j++) {
                if (!find.get(i).get(j) && map.get(i).get(j) == 'L') {
                    find.get(i).set(j, true);
                    counting.get(i).set(j, count);
                    map.get(i).set(j,'L');
                    check(find, map, counting, i, j, count++);
                }
            }

        }

        System.out.println(count);

    }
}
