//Huang Qing A0221170Y B36

import java.util.*;

public class CoconutSplat {
    public static class Pair {
        public int playerIndex;
        public int handState;

        public Pair(int firstValue, int secondValue) {
            this.playerIndex = firstValue;
            this.handState = secondValue;
        }

        public boolean isFold() {
            return this.handState == 2;
        }

        public boolean isFist() {
            return this.handState == 1;
        }

        public void update() {
            this.handState = this.handState - 1;
        }

        public int getPlayer() {
            return this.playerIndex;
        }

        public String toString() {
            return this.playerIndex + "," + this.handState;
        }

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numOfSyllables = sc.nextInt();
        int numOfPlayers = sc.nextInt();

        ArrayList<Pair> players = new ArrayList<>(numOfPlayers);

        //create list of players with initial state
        for (int i = 1; i <= numOfPlayers; i++) {
            players.add(new Pair(i, 2));
        }


        int count = 0;

        while (players.size() > 1) {
            count = (count + numOfSyllables -1) % players.size();

            // hands are still folded, split into two fists
            if (players.get(count).isFold()) {
                players.get(count).update();
                players.add(count, new Pair(players.get(count).playerIndex, players.get(count).handState));

            //a fist is touched last, the hand is turned palm down
            } else if (players.get(count).isFist()) {
                players.get(count).update();
                count++;
            } else {
                //hand that is already turned palm down is touched, will not be counted in the following rounds
                players.remove(count);
            }
        }

        //output the winner
        System.out.println(players.get(0).getPlayer());
    }
}
