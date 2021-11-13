/* Huang Qing  B07
scan for int N
while N != 0
put N number of name in a String array using for loop
using Array.sort to sort this array by comparing substring(0,2)
then print out this sorted array
insert a new line
scan for next int if available.
 */


import java.util.*;

public class SortOfSorting {

    static class NameComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return (str1.substring(0, 2).compareTo(str2.substring(0, 2)));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        while (n != 0) {


            String nameArr[] = new String[n];

            for (int i = 0; i < n; i++) {
                nameArr[i] = sc.nextLine();
            }

           // Arrays.sort(nameArr, new NameComparator());

            for(int j = 0; j < nameArr.length; j++) {
                System.out.println(nameArr[j] + "\n");
            }
            System.out.println("\n");

            n = Integer.parseInt(sc.nextLine());
            if (n == 0) {
                break;
            }

        }

        sc.close();
    }
}




