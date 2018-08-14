package net.cserny.strings;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class StringAlign extends Format {

    private Justify justify;
    private int maxChars;

    public enum Justify {
        LEFT, CENTER, RIGHT;
    }

    public StringAlign(int maxChars, Justify justify) {
        switch (justify) {
            case LEFT:
            case CENTER:
            case RIGHT:
                this.justify = justify;
                break;
            default:
                throw new IllegalArgumentException("invalid justification arg");
        }

        if (maxChars < 0) {
            throw new IllegalArgumentException("maxChars must be positive");
        }

        this.maxChars = maxChars;
    }

    public String format(String s) {
        return format(s, new StringBuffer(), null).toString();
    }

    @Override
    public StringBuffer format(Object input, StringBuffer where, FieldPosition ignore) {
        String s = input.toString();
        String wanted = s.substring(0, Math.min(s.length(), maxChars));
        switch (justify) {
            case RIGHT:
                pad(where, maxChars - wanted.length());
                where.append(wanted);
                break;
            case CENTER:
                int toAdd = maxChars - wanted.length();
                pad(where, toAdd / 2);
                where.append(wanted);
                pad(where, toAdd - toAdd / 2);
                break;
            case LEFT:
                where.append(wanted);
                pad(where, maxChars - wanted.length());
                break;
        }
        return where;
    }

    protected final void pad(StringBuffer to, int howMany) {
        for (int i = 0; i < howMany; i++) {
            to.append(" ");
        }
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }
}
