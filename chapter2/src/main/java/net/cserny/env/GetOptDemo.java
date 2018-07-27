package net.cserny.env;

import java.util.Map;

public class GetOptDemo {

    public static void main(String[] args) {
        boolean numericOption = false;
        boolean errors = false;
        String outputFileName = null;
        GetOptDesc[] options = {
                new GetOptDesc('n', "numeric", false),
                new GetOptDesc('o', "output-file", true)
        };
        GetOpt parser = new GetOpt(options);

        Map<String, String> optionsFound = parser.parseArguments(args);
        for (String key : optionsFound.keySet()) {
            char c = key.charAt(0);
            switch (c) {
                case 'n':
                    numericOption = true;
                    break;
                case 'o':
                    outputFileName = (String) optionsFound.get(key);
                    break;
                case '?':
                    errors = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected option character: " + c);
            }
        }

        if (errors) {
            System.err.println("Usage: GetOptDemo [-n][-o file][file...]");
        }

        System.out.print("Options: ");
        System.out.print("Numeric: " + numericOption + " ");
        System.out.print("Output: " + outputFileName + "; ");
        System.out.print("Input files: ");
        for (String fileName : parser.getFileNameList()) {
            System.out.print(fileName);
            System.out.print(" ");
        }
        System.out.println();
    }
}
