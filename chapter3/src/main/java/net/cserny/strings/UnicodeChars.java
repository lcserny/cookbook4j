package net.cserny.strings;

public class UnicodeChars {

    public static void main(String[] args) {
        StringBuilder buffer = new StringBuilder();
        for (char c = 'a'; c < 'd'; c++) {
            buffer.append(c);
        }
        buffer.append("\u00a5");
        buffer.append("\u01FC");
        buffer.append("\u0391");
        buffer.append("\u0349");
        for (int i = 0; i < buffer.length(); i++) {
            System.out.printf("Character #%d (%04x) is %c%n", i,
                    (int) buffer.charAt(i), buffer.charAt(i));
        }
        System.out.println("Accumulated characters are " + buffer);
    }
}
