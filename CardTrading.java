//Huang Qing A0221170Y B36

import java.util.*;

public class CardTrading {
    public static final class Pair implements Comparable<Pair>{
        public Integer first;
        public Integer second;

        public Pair(Integer firstValue, Integer secondValue) {
            first = firstValue;
            second = secondValue;
        }

        @Override
        public int compareTo(Pair next) {
            if (this.first + this.second > next.first + next.second) {
                return 1;
            } else if (this.first + this.second < next.first + next.second) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public static void main(String[] args) {
        int[] counter = new int[100001];  //1 ≤ K ≤ T ≤100000
        long result = 0;
        ArrayList<Pair> pricePair = new ArrayList<Pair>();

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int K = sc.nextInt();

        //the cards in Anthony’s deck
        for(int i = 0; i < N; i++) {
            int cardIndex = sc.nextInt();
            counter[cardIndex]++;
        }

        // place into pair list (T pairs) of  {buy price, sell price}
        for (int i = 1; i <= T; i++) {
            int buyPrice = sc.nextInt();
            int sellPrice = sc.nextInt();
            //Math.max to check if got a pair of card, then set buy price to 0, since cannot buy anymore
            pricePair.add(new Pair(Math.max(2 - counter[i], 0) * buyPrice, counter[i] * sellPrice));
        }

        // to sort all pairs based on the sum of buy and sell
        Collections.sort(pricePair);

        // for the first K(combo) pair, subtracting buy from profits
        for (int i = 0; i < K; i++) {
            result = result - pricePair.get(i).first;
        }

        // for the remaining T-K pair, add sell price to profits
        for (int j = K; j < pricePair.size(); j++) {
            result = result + pricePair.get(j).second;
        }

        System.out.println(result);

    }
}
