//Huang Qing A0221170Y B36
import java.io.*;

public class Teque {
    static int firstHeadIndex;
    static int firstTailIndex;
    static int secondHeadIndex;
    static int secondTailIndex;
    static int[] firstArray;
    static int[] secondArray;
    static int queue1;
    static int queue2;
    static int totalSize;

    public Teque(int n) {
        firstHeadIndex = n;
        firstTailIndex = n;
        secondHeadIndex = n;
        secondTailIndex = n;

        firstArray = new int[n * 2 + 1];
        secondArray = new int[n * 2 + 1];
    }

    // this method to balance the 2 array
    public static void shift() {
        //more element in queue1, shift last element of queue1 to first element of queue2
        if (queue1 - queue2 == 2) {
            if (queue2 > 0) {
                secondHeadIndex--;
            }
            secondArray[secondHeadIndex] = firstArray[firstTailIndex];
            firstArray[firstTailIndex] = 0;
            queue1--;
            queue2++;
            firstTailIndex--;

            //more element in queue2, shift first element of queue2 to last element of queue1
        } else if(queue2 - queue1 == 2) {
            if (queue1 > 0) {
                firstTailIndex++;
            }
            firstArray[firstTailIndex] = secondArray[secondHeadIndex];
            secondArray[secondHeadIndex] = 0;
            queue1++;
            queue2--;
            secondHeadIndex++;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine());
        Teque result = new Teque(n);

        while((n--) != 0) {
            String[] input = br.readLine().split(" ");
            String command = input[0];
            int value = Integer.parseInt(input[1]);

            //add element infront
            if (command.equals("push_front")) {
                if (queue1 > 0) {
                    firstHeadIndex--;
                }
                firstArray[firstHeadIndex] = value;
                queue1++;
                totalSize++;
                shift();

            //add element behind
            } else if (command.equals("push_back")) {
                if (queue2 > 0) {
                    secondTailIndex++;
                }
                secondArray[secondTailIndex] = value;
                queue2++;
                totalSize++;
                shift();

            //add element in middle
            } else if (command.equals("push_middle")) {
                // total element is odd number, median is the first element of second array
                if (totalSize % 2 == 1) {
            // first array has more element, add to head of second array
                    if (queue1 > queue2) {
                        if (queue2 > 0) {
                            secondHeadIndex--;
                        }
                        queue2++;
                        secondArray[secondHeadIndex] = value;
                        totalSize++;
            // second array has more element, shift first element to last element of first array, then add to the head
                    } else {
                        if (queue1 > 0) {
                            firstTailIndex++;
                        }
                        firstArray[firstTailIndex] = secondArray[secondHeadIndex];
                        queue1++;
                        secondArray[secondHeadIndex] = value;
                        totalSize++;
                    }

            // else total element is even number, add to tail of first array
                } else {
                    if (queue1 > 0) {
                        firstTailIndex++;
                    }
                    firstArray[firstTailIndex] = value;
                    totalSize++;
                    queue1++;
                }

            //get command, print out index of the element
            } else {
                if (value < queue1) {
                    pw.println(firstArray[firstHeadIndex + value]);
                } else {
                    pw.println(secondArray[value - queue1 + secondHeadIndex]);
                }
            }

            }
        pw.flush();

    }

}

