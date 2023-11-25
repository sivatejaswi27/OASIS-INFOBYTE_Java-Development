import java.util.Random;
import java.util.Scanner;

public class Task2_NumberGuessingGame {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random();
        int minRangeValue = 1;
        int maxRangeValue = 100;
        int maxAttempts = 10;
        int playerScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You will earn 100 points for each correct guess, and 1 point will be deducted for each incorrect attempt.");

        while (true) {
            int targetNumber = randomGenerator.nextInt(maxRangeValue - minRangeValue + 1) + minRangeValue;
            int attempts = 0;

            System.out.println("I've picked a number between " + minRangeValue + " and " + maxRangeValue + ". Try to guess it!");

            while (true) {
                System.out.print("Enter your guess: ");
                int userGuess = inputScanner.nextInt();
                attempts++;

                if (userGuess < minRangeValue || userGuess > maxRangeValue) {
                    System.out.println("Please enter a number within the specified range.");
                }
                else if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You've guessed the correct number in " + attempts + " attempts.");
                    playerScore += calculateScore(attempts);
                    break;
                }
                else if (attempts >= maxAttempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
                    break;
                }
                else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                }
                else {
                    System.out.println("Too high! Try again.");
                }
            }

            System.out.println("Your current score: " + playerScore);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainChoice = inputScanner.next().toLowerCase();

            if (!playAgainChoice.equals("yes")) {
                System.out.println("Thanks for playing! Your final score is: " + playerScore);
                break;
            }
        }

        inputScanner.close();
    }

    private static int calculateScore(int attempts) {
        return 100 - attempts;
    }
}
