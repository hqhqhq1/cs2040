//Huang Qing A0221170Y B36
import java.io.*;

final class Arrays {
    public static LinkStrList[] initialLinkStrList(int len) {
        LinkStrList[] array = new LinkStrList[len];
        for (int i = 0; i < len; i++) {
            array[i] = new LinkStrList();
        }
        return array;
    }
}

class StrNode {
    public String str;
    public int length;
    public StrNode next;
}

class LinkStrList {
    public StrNode head = new StrNode();
    public StrNode tail;

}


public class JoinStrings {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int curr = 0;

        LinkStrList[] strList = Arrays.initialLinkStrList(n);

        for (int i = 0; i < n; i++) {
            strList[i].head.next = null;
            strList[i].head.str = br.readLine();
            strList[i].tail = strList[i].head;
            strList[i].head.length = strList[i].head.str.length();
        }

        for (int i = 0; i < n - 1; i++) {
            String[] input = new String[2];
            int temp1 = 0;
            int temp2 = 0;
            input = br.readLine().split(" ");
            temp1 = Integer.parseInt(input[0]);
            temp2 = Integer.parseInt(input[1]);
            temp1--;
            temp2--;
            curr = temp1;
            append(strList[temp1], strList[temp2]);
        }

        printResult(strList[curr]);

    }

    public static void append(LinkStrList list1, LinkStrList list2) {
        list1.tail.next = list2.head;
        list1.tail = list2.tail;
    }

    public static void printResult(LinkStrList list) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StrNode cur = list.head;
        while (cur != null) {

            pw.printf("%s", cur.str);
            cur = cur.next;
        }
        pw.flush();

    }

}

