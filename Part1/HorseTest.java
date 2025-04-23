public class HorseTest {
    public static void main(String[] args)
    {
        // Create new Horse object
        Horse horse1 = new Horse('♘', "QueenMary Horse", 0.8);

        // Test getName method
        System.out.println("Expected Horse Name: QueenMary Horse");
        System.out.println("Actual Horse Name: " + horse1.getName());

        // Test getSymbol method
        System.out.println("Expected Horse Symbol: ♘");
        System.out.println("Actual Horse Symbol: " + horse1.getSymbol());

        // Test setSymbol method
        System.out.println("Expected Horse Symbol after setting new symbol: &");
        horse1.setSymbol('&');
        System.out.println("Actual Horse Symbol after setting new symbol: " + horse1.getSymbol());

        // Test getConfidence method
        System.out.println("Expected Horse Confidence: 0.8");
        System.out.println("Actual Horse Confidence: " + horse1.getConfidence());

        // Test getDistanceTravelled method
        System.out.println("Expected Distance Travelled: 0");
        System.out.println("Actual Distance Travelled: " + horse1.getDistanceTravelled());

        // Test moveForward method
        System.out.println("Expected Distance Travelled after moving forward: 2");
        horse1.moveForward();
        horse1.moveForward();
        System.out.println("Actual Distance Travelled after moving forward: " + horse1.getDistanceTravelled());

        // Test goBackToStart method
        System.out.println("Expected Distance Travelled after going back to start: 0");
        horse1.goBackToStart();
        System.out.println("Actual Distance Travelled after going back to start: " + horse1.getDistanceTravelled());

        // Test hasFallen method
        System.out.println("Expected Horse Fallen: false");
        System.out.println("Actual Horse Fallen: " + horse1.hasFallen());

        // Test fall method
        System.out.println("Expected Horse Fallen after falling: true");
        horse1.fall();
        System.out.println("Actual Horse Fallen after falling: " + horse1.hasFallen());

        // Test setConfidence method
        System.out.println("Expected Horse Confidence after setting new confidence: 0.5");
        horse1.setConfidence(0.5);
        System.out.println("Actual Horse Confidence after setting new confidence: " + horse1.getConfidence());

        // Test setConfidence method with invalid high value
        try {
            horse1.setConfidence(1.5); // Invalid value
            System.out.println("No exception thrown (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught as expected: " + e.getMessage());
        }

        // Test setConfidence method with invalid low value
        try {
            horse1.setConfidence(-1); // Invalid value
            System.out.println("No exception thrown (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught as expected: " + e.getMessage());
        }
    }
}
