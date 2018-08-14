package net.cserny.strings;

import java.util.Stack;
import java.util.StringTokenizer;

public class StringReverseDemo {

    public static void main(String[] args) {
        reverseByChar();
        reverseByWord();
    }

    private static void reverseByWord() {
        String s = "Father Charles Goes Down And Ends Battle";
        Stack<String> strings = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
            strings.push(tokenizer.nextToken());
        }
        System.out.print('"' + s + '"' + " backwards by word is:\n\t\"");
        while (!strings.empty()) {
            System.out.print(strings.pop());
            System.out.print(" ");
        }
        System.out.println('"');
    }

    private static void reverseByChar() {
        String sh = "FCGDAEB";
        System.out.println(sh + " -> " + new StringBuilder(sh).reverse());
    }
}
