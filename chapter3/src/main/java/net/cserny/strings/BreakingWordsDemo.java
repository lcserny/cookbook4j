package net.cserny.strings;

import java.util.StringTokenizer;

public class BreakingWordsDemo {

    public final static int MAXFIELDS = 5;
    public static final String DELIM = "|";

    public static void main(String[] args) {
        printResults("A|B|C|D", process("A|B|C|D"));
        printResults("A||C|D", process("A||C|D"));
        printResults("A|||D|E", process("A|||D|E"));
    }

    private static String[] process(String line) {
        String[] results = new String[MAXFIELDS];
        StringTokenizer tokenizer = new StringTokenizer(line, DELIM, true);
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            String s = tokenizer.nextToken();
            if (s.equals(DELIM)) {
                if (i++ > MAXFIELDS) {
                    throw new IllegalArgumentException("Input line " + line + " has too many fields");
                }
                continue;
            }
            results[i] = s;
        }
        return results;
    }

    private static void printResults(String input, String[] outputs) {
        System.out.println("Input: " + input);
        for (String output : outputs) {
            System.out.println("Output: " + output);
        }
    }

    private static void simpleTests() {
        StringTokenizer st1 = new StringTokenizer("Hello World of Java");
        while (st1.hasMoreTokens()) {
            System.out.println("Token: " + st1.nextToken());
        }

        System.out.println();

        StringTokenizer st2 = new StringTokenizer("Hello, World|of|Java", ", |");
        while (st2.hasMoreElements()) {
            System.out.println("Token: " + st2.nextElement());
        }

        System.out.println();

        StringTokenizer st3 = new StringTokenizer("Hello, World|of|Java", ", |", true);
        while (st3.hasMoreElements()) {
            System.out.println("Token: " + st3.nextElement());
        }
    }
}
