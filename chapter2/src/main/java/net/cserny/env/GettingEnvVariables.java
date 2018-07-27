package net.cserny.env;

import java.util.Map;

public class GettingEnvVariables {

    public static void main(String[] args) {
        System.out.println("System.getenv('PATH') = " + System.getenv("PATH"));

        System.out.println("\nAll entries:");
        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
