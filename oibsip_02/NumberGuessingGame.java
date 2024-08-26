import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            
            int rounds = 3;
            int maxAttempts = 5;
            int totalScore = 0;
            
            for (int round = 1; round <= rounds; round++) {
                int numberToGuess = random.nextInt(100) + 1;
                int attempts = 0;
                boolean guessed = false;
                
                System.out.println("Round " + round + ":");
                System.out.println("I have selected a number between 1 and 100. Try to guess it!");
                
                while (attempts < maxAttempts) {
                    System.out.print("Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    attempts++;
                    
                    if (userGuess == numberToGuess) {
                        System.out.println("Congratulations! You've guessed the number!");
                        guessed = true;
                        break;
                    } else if (userGuess < numberToGuess) {
                        System.out.println("The number is higher than your guess.");
                    } else {
                        System.out.println("The number is lower than your guess.");
                    }
                }
                
                if (!guessed) {
                    System.out.println("Sorry! You've used all your attempts. The number was " + numberToGuess);
                }
                
                int roundScore = guessed ? (maxAttempts - attempts + 1) * 10 : 0;
                totalScore += roundScore;
                System.out.println("Round " + round + " Score: " + roundScore);
                System.out.println("Total Score after Round " + round + ": " + totalScore);
                System.out.println();
            }
            
            System.out.println("Game Over! Your total score is: " + totalScore);
 }
}
}
