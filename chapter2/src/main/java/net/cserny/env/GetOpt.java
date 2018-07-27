package net.cserny.env;

import sun.security.util.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetOpt {

    public static final int DONE = 0;
    protected final GetOptDesc[] options;
    protected List<String> fileNameArguments;
    protected int optind = 0;
    protected boolean done = false;
    protected String optarg;

    public String optarg() {
        return optarg;
    }

    public String optArg() {
        return optarg;
    }

    public GetOpt(final GetOptDesc[] opts) {
        this.options = opts.clone();
    }

    public GetOpt(final String patt) {
        if (patt == null) {
            throw new IllegalArgumentException("Pattern may not be null");
        }

        if (patt.charAt(0) == ':') {
            throw new IllegalArgumentException("Pattern incorrect, may not begin with ':'");
        }

        int n = 0;
        for (char ch : patt.toCharArray()) {
            if (ch != ':') {
                ++n;
            }
        }

        if (n == 0) {
            throw new IllegalArgumentException("No option letters found in " + patt);
        }

        options = new GetOptDesc[n];
        for (int i = 0, ix = 0; i < patt.length(); i++) {
            final char c = patt.charAt(i);
            boolean argTakesValue = false;
            if (i < patt.length() - 1 && patt.charAt(i + 1) == ':') {
                argTakesValue = true;
                ++i;
            }

            Debug.println("getopt", "CONSTR: options[" + ix + "] = " + c + ", " + argTakesValue);
            options[ix++] = new GetOptDesc(c, null, argTakesValue);
        }
    }

    public void rewind() {
        fileNameArguments = null;
        done = false;
        optind = 0;
        optarg = null;
    }

    public Map<String, String> parseArguments(String[] argv) {
        Map<String, String> optionsValueMap = new HashMap<>();
        fileNameArguments = new ArrayList<>();
        for (int i = 0; i < argv.length; i++) {
            Debug.println("getopt", "parseArg: i=" + i + ": arg " + argv[i]);
            char c = getopt(argv);
            if (c == DONE) {
                fileNameArguments.add(argv[i]);
            } else {
                optionsValueMap.put(Character.toString(c), optarg);
                if (optarg != null) {
                    i++;
                }
            }
        }
        return optionsValueMap;
    }

    public List<String> getFileNameList() {
        if (fileNameArguments == null) {
            throw new IllegalArgumentException("Illegal call to getFileNameList() before parseOptions()");
        }
        return fileNameArguments;
    }

    public char getopt(String[] argv) {
        Debug.println("getopt", "optind=" + optind + ", argv.length=" + argv.length);
        if (optind >= (argv.length) || !argv[optind].startsWith("-")) {
            done = true;
        }

        if (done) {
            return DONE;
        }

        optarg = null;

        String thisArg = argv[optind];
        if (thisArg.startsWith("-")) {
            for (GetOptDesc option : options) {
                if ((thisArg.length() == 2 && option.getArgLetter() == thisArg.charAt(1))
                        || (option.getArgName() != null && option.getArgName().equals(thisArg.substring(1)))) {
                    if (option.takesArgument()) {
                        if (optind < argv.length - 1) {
                            optarg = argv[++optind];
                        } else {
                            throw new IllegalArgumentException("Option " + option.getArgLetter()
                                    + " needs value but found end of args list");
                        }
                    }
                    ++optind;
                    return option.getArgLetter();
                }
            }
            ++optind;
            return '?';
        } else {
            ++optind;
            done = true;
            return DONE;
        }
    }

    public int getOptInd() {
        return optind;
    }
}
