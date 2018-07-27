package net.cserny.env;

public class GetOptDesc {

    private char argLetter;
    private String argName;
    private boolean takesArgument;

    public GetOptDesc(char type, String desc, boolean enabled) {
        this.argLetter = type;
        this.argName = desc;
        this.takesArgument = enabled;
    }

    public char getArgLetter() {
        return argLetter;
    }

    public String getArgName() {
        return argName;
    }

    public boolean takesArgument() {
        return takesArgument;
    }
}
