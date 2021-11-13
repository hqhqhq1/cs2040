//Huang Qing A0221170Y  LAB07
//Sort the 5 courses selected by each frosh from smallest to largest and put them together to form a
// 15-digit number combination
// put the combinations into a hashmap, if same combination appear increase the count.
// use for loop to find the most frequent combination of courses,
// output number of times it appears multiply by the count

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays;

public class Conformity {

    public static void main(String[] args) throws IOException {
        ArrayList<String> keyArr = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
           String[] combinations =  br.readLine().split(" ");
           Arrays.sort(combinations);

           String keys = "";
           for (int j = 0; j < 5; j++) {
               keys = keys + combinations[j];
           }
           keyArr.add(keys);
        }


        HashMap<String, Integer> popularity = new HashMap<>();
        for (int i = 0; i < keyArr.size(); i ++) {
            String key = keyArr.get(i);
            Integer count = popularity.get(key);
            popularity.put(key, (count == null) ? 1 : count + 1);
        }

        int highestCount = 0;
        int samePopularity = 0;


        //array of counts(number) so safe to put into array of integer
        ArrayList<Integer> countArr = new ArrayList<>(popularity.values());

        for (int i = 0; i < countArr.size(); i++) {
            if (countArr.get(i) > highestCount) {
                samePopularity = 0;
                highestCount =  countArr.get(i);
            }

            if (countArr.get(i) == highestCount) {
                samePopularity++;
            }
        }
        System.out.println(highestCount * samePopularity);
    }


}
