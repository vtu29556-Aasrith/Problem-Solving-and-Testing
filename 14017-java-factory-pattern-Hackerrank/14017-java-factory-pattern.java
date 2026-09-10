import java.util.*;
import java.security.*;

interface Food {
    public String getType();
}

class Pizza implements Food {
    public String getType() {
        return "Someone ordered Fast Food!";
    }
}

class Cake implements Food {
    public String getType() {
        return "Someone ordered a Dessert!";
    }
}

class FoodFactory {
    public Food getFood(String order) {
        if (order.equalsIgnoreCase("pizza")) {
            return new Pizza();
        } else if (order.equalsIgnoreCase("cake")) {
            return new Cake();
        }
        return null;
    }
}

public class Solution {
    public static void main(String[] args) {
        DoNotTerminate.forbidExit();

        try {
            Scanner sc = new Scanner(System.in);
            FoodFactory foodFactory = new FoodFactory();

            Food food = foodFactory.getFood(sc.nextLine());

            System.out.println("The factory returned " + food.getClass());
            System.out.println(food.getType());
        } catch (DoNotTerminate.ExitException e) {
            System.out.println("Unsuccessful Termination!!");
        }
    }
}

class DoNotTerminate {
    public static class ExitException extends SecurityException {
        private static final long serialVersionUID = 1L;
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna