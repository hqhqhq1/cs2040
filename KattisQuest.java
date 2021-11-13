import java.util.*;

//Huang Qing A0221170Y  LAB07
public class KattisQuest {

    public static class GameQuests implements Comparable<GameQuests> {
        public int energyConsumption;
        public int goldEarn;
        public int uniqueId;
        public static int id = 0;


        public GameQuests(int energyConsumption, int goldEarn, int uniqueId) {
            this.energyConsumption = energyConsumption;
            this.goldEarn = goldEarn;
            this.uniqueId = uniqueId;
        }

        @Override
        public int compareTo(GameQuests another) {
           if (this.energyConsumption == another.energyConsumption) {
               if (this.goldEarn == another.goldEarn) {
                  return this.uniqueId - another.uniqueId;
               } else {
                  return this.goldEarn - another.goldEarn;
               }
           } else {
               return this.energyConsumption - another.energyConsumption;
           }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        TreeSet<GameQuests> data = new TreeSet<>();


        for (int i = 0; i < n; i++) {
            String operationName;
            operationName = sc.next();

            //add instruction, adding new GameQuest with energy consumption E and gold reward G into the pool to TreeSet
            if (operationName.equals("add")) {
                int energy;
                int gold;
                energy = sc.nextInt();
                gold = sc.nextInt();

                data.add(new GameQuests(energy, gold, i));

            //query instruction, print out the total amount of gold Kattis the Cat earned in this session with energy X
            } else {
                long totalGold = 0;
                int energy;
                energy = sc.nextInt();
                GameQuests query;

                //Find the largest energy quest from the current pool of quest which is smaller or equal to X
                while((query = data.floor(new GameQuests(energy, Integer.MAX_VALUE, 0))) != null) {

                    //Clear the quest, removing it from current pool. Reduce energy X by E of the quest,
                    // and add up the gold reward G earned this session.
                    energy = energy - query.energyConsumption;
                    totalGold = totalGold + query.goldEarn;
                    data.remove(query);
                    }

                System.out.println(totalGold);

            }

        }
        sc.close();

    }
}
