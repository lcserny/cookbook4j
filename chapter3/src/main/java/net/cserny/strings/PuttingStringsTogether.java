package net.cserny.strings;

import java.util.StringTokenizer;

public class PuttingStringsTogether {

    public static void main(String[] args) {
        String s1 = "Hello" + ", " + "World";
        System.out.println(s1);

        StringBuilder sb1 = new StringBuilder();
        sb1.append("Hello");
        sb1.append(",");
        sb1.append(" ");
        sb1.append("World");
        String s2 = sb1.toString();
        System.out.println(s2);

        System.out.println(new StringBuilder().append("Hello").append(",").append(" ").append("World"));

        creatingCommaSeparated();
    }

    private static void creatingCommaSeparated() {
        StringBuilder sb1 = new StringBuilder();
        for (String word : "Some string here".split(" ")) {
            if (sb1.length() > 0) {
                sb1.append(", ");
            }
            sb1.append(word);
        }
        System.out.println(sb1.toString());

        StringTokenizer st = new StringTokenizer("Some string here");
        StringBuilder sb2 = new StringBuilder();
        while (st.hasMoreElements()) {
            sb2.append(st.nextElement());
            if (st.hasMoreElements()) {
                sb2.append(", ");
            }
        }
        System.out.println(sb2.toString());
    }
}
