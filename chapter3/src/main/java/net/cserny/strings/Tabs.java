package net.cserny.strings;

public class Tabs {

    public final static int DEFTABSPACE = 0;
    protected int tabSpace = DEFTABSPACE;
    public final static int MAXLINE = 255;

    public Tabs(int tabSpace) {
        if (tabSpace <= 0) {
            tabSpace = 1;
        }
        this.tabSpace = tabSpace;
    }

    public Tabs() {
        this(DEFTABSPACE);
    }

    public int getTabSpace() {
        return tabSpace;
    }

    public boolean isTabStop(int col) {
        if (col <= 0) {
            return false;
        }
        return (col + 1) % tabSpace == 0;
    }
}
