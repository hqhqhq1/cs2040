import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//Huang Qing A0221170Y  LAB07
public class Workstation {

    public static class Researcher implements Comparable<Researcher> {
        public int arrives;
        public int stays;
        public int leaves;

        public Researcher(int arrives, int stays) {
            this.arrives = arrives;
            this.stays = stays;
            this.leaves = arrives + stays;
        }

        @Override
        public int compareTo(Researcher r1) {
            if (this.arrives < r1.arrives) {
                return -1;
            } else if (this.arrives > r1.arrives) {
                return 1;
            } else {
                return 0;
            }
        }
    }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            //n number of researcher
            int n = sc.nextInt();

            //m minutes for unused workstation to be lock itself
            int m = sc.nextInt();
            ArrayList<Researcher> researchers = new ArrayList<>();


            for (int i = 0; i < n; i++) {
                int a = sc.nextInt(); // arriving time
                int s = sc.nextInt(); // stay time
                // create an arraylist of researchers
                researchers.add(i, new Researcher(a, s));
            }
            //sort them by arriving time
            Collections.sort(researchers);


            PriorityQueue<Integer> queue = new PriorityQueue<>();
            int result = 0;

            for (int i = 0; i < researchers.size(); i++) {
                Researcher r = researchers.get(i);
                while (queue.size() != 0 && (queue.peek() < r.arrives)) {
                    queue.poll();
                }
                // during time m, no need to unlock, sub from the result
                if (queue.size() != 0 && (queue.peek() - m <= r.arrives)) {
                    queue.poll();
                    result--;
                }

                //count all researchers with time of computer stay unlock
                queue.add(r.leaves + m);
                result++;

            }
            System.out.println(n - result);
        }



}

