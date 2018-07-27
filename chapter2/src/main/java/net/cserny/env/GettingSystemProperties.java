package net.cserny.env;

import java.util.Map;

public class GettingSystemProperties {

    public static void main(String[] args) {
        // you can add properties like so -D"pencil_color=Deep Sea Green"
        System.out.println(System.getProperty("pencil_color"));

        System.out.println();
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
