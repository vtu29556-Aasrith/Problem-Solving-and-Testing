import java.io.*;
import java.util.*;

class Singleton {
    private static Singleton instance;
    public String str;

    private Singleton() {
    }

    public static Singleton getSingleInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        Singleton singleton = Singleton.getSingleInstance();
        singleton.str = input;
        
        System.out.println("Hello I am a singleton! Let me say " + singleton.str + " to you");
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna