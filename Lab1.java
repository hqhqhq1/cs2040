/*
map char 'a' to '2', 'b' to '22' etc
scan for int N,
for N cases:
	case number #:  scan next line, put the string into array of char
	for each char in the array convert to number mapped to it
	store output in a string and append the following char
	(if 2 adjacent char have the same number to key, insert " " to separate them)
 */

import java.util.*;

public class Lab1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Case #" + (1 + i) + ": " + output(sc.nextLine()));
        }
    }

    public static Map<Character, String> alpToNumber = new HashMap<>();

    static {
        alpToNumber.put(' ', "0");
        alpToNumber.put('a', "2");
        alpToNumber.put('b', "22");
        alpToNumber.put('c', "222");
        alpToNumber.put('d', "3");
        alpToNumber.put('e', "33");
        alpToNumber.put('f', "333");
        alpToNumber.put('g', "4");
        alpToNumber.put('h', "44");
        alpToNumber.put('i', "444");
        alpToNumber.put('j', "5");
        alpToNumber.put('k', "55");
        alpToNumber.put('l', "555");
        alpToNumber.put('m', "6");
        alpToNumber.put('n', "66");
        alpToNumber.put('o', "666");
        alpToNumber.put('p', "7");
        alpToNumber.put('q', "77");
        alpToNumber.put('r', "777");
        alpToNumber.put('s', "7777");
        alpToNumber.put('t', "8");
        alpToNumber.put('u', "88");
        alpToNumber.put('v', "888");
        alpToNumber.put('w', "9");
        alpToNumber.put('x', "99");
        alpToNumber.put('y', "999");
        alpToNumber.put('z', "9999");
    }

    public static Map<Character, Integer> keyToPress = new HashMap<>();

    static {
        keyToPress.put('a', 2);
        keyToPress.put('b', 2);
        keyToPress.put('c', 2);
        keyToPress.put('d', 3);
        keyToPress.put('e', 3);
        keyToPress.put('f', 3);
        keyToPress.put('g', 4);
        keyToPress.put('h', 4);
        keyToPress.put('i', 4);
        keyToPress.put('j', 5);
        keyToPress.put('k', 5);
        keyToPress.put('l', 5);
        keyToPress.put('m', 6);
        keyToPress.put('n', 6);
        keyToPress.put('o', 6);
        keyToPress.put('p', 7);
        keyToPress.put('q', 7);
        keyToPress.put('r', 7);
        keyToPress.put('s', 7);
        keyToPress.put('t', 8);
        keyToPress.put('u', 8);
        keyToPress.put('v', 8);
        keyToPress.put('w', 9);
        keyToPress.put('x', 9);
        keyToPress.put('y', 9);
        keyToPress.put('z', 9);
        keyToPress.put(' ', 0);
    }

    public static String output(String next) {
        StringBuilder str = new StringBuilder();
        char[] arrOfChar = next.toCharArray();
        char processedChar = arrOfChar[0];

        str.append(alpToNumber.get(processedChar));

        for (int i = 1; i < arrOfChar.length; i++) {
            char nextChar = arrOfChar[i];
            if (keyToPress.get(processedChar).equals(keyToPress.get(nextChar))) {
                str.append(" ");
            }
            processedChar = nextChar;
            str.append(alpToNumber.get(processedChar));
        }

        return str.toString();

    }
}
