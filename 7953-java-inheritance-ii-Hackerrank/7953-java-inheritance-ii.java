import java.io.*;
import java.util.*;

// Write your code here
class Arithmetic {
    int add(int a, int b) {
        return a + b;
    }
}

class Adder extends Arithmetic {
}

public class Solution {

    public static void main(String[] args) {
        // Create a new Adder object
        Adder a = new Adder();
        
        // Print the name of the superclass
        System.out.println("My superclass is: " + a.getClass().getSuperclass().getName());    
        
        // Print the result of calling the add method
        System.out.print(a.add(10,32) + " " + a.add(10,3) + " " + a.add(10,10) + "\n");
     }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna