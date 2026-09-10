import java.io.IOException;
import java.lang.reflect.Method;

class Printer {

    // Generic method printArray
    public <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.println(element);
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        Printer myPrinter = new Printer();
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = { "Hello", "World" };

        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);

        int count = 0;

        for (Method method : Printer.class.getDeclaredMethods()) {
            if (method.getName().equals("printArray")) count++;
        }

        if (count > 1) {
            System.out.println("Method overloading is not allowed!");
        }
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna