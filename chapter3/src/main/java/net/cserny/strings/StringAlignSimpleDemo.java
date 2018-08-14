package net.cserny.strings;

public class StringAlignSimpleDemo {

    public static void main(String[] args) {
        StringAlign formatter = new StringAlign(70, StringAlign.Justify.CENTER);
        System.out.println(formatter.format("- i -"));
        System.out.println(formatter.format(Integer.toString(4)));
    }
}
