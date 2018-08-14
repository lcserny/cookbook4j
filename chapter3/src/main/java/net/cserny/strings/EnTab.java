package net.cserny.strings;

import java.io.*;

public class EnTab {

    protected Tabs tabs;

    public int getTabSpacing() {
        return tabs.getTabSpace();
    }

    public static void main(String[] args) throws IOException {
        EnTab et = new EnTab(8);
        if (args.length == 0) {
            et.entab(
                    new BufferedReader(new InputStreamReader(System.in)),
                    System.out
            );
        } else {
            for (String fileName : args) {
                et.entab(
                        new BufferedReader(new FileReader(fileName)),
                        System.out
                );
            }
        }
    }

    public EnTab(int n) {
        tabs = new Tabs(n);
    }

    public EnTab() {
        tabs = new Tabs();
    }

    public void entab(BufferedReader in, PrintWriter out) throws IOException {
        String line;
        while ((line = in.readLine()) != null) {
            out.println(entabLine(line));
        }
    }

    public void entab(BufferedReader in, PrintStream out) throws IOException {
        entab(in, new PrintWriter(out));
    }

    public String entabLine(String line) {
        int N = line.length(), outCol = 0;
        StringBuilder sb = new StringBuilder();
        char ch;
        int consumedSpaces = 0;

        for (int inCol = 0; inCol < N; inCol++) {
            ch = line.charAt(inCol);
            if (ch == ' ') {
                System.out.println("space: Got space at " + inCol);
                if (!tabs.isTabStop(inCol)) {
                    consumedSpaces++;
                } else {
                    System.out.println("tab: Got a Tab Stop " + inCol);
                    sb.append("\t");
                    outCol += consumedSpaces;
                    consumedSpaces = 0;
                }
                continue;
            }

            while (inCol - 1 > outCol) {
                System.out.println("pad: Padding space at " + inCol);
                sb.append(" ");
                outCol++;
            }

            sb.append(ch);
            outCol++;
        }

        for (int i = 0; i < consumedSpaces; i++) {
            System.out.println("trail: Padding space at end # " + i);
            sb.append(" ");
        }

        return sb.toString();
    }
}
