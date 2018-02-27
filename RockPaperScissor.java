import java.util.Scanner;
import java.util.Random;
/**
 * A program for playing Rock Paper Scissors with Computer.
 * @author Priyanka Deoskar
 * @version 1.0
 * @since 2018-02-11
 */
public class RockPaperScissor {
	/**
	 * Specifies 3 moves that players can make. 
	 */
	private enum Move {
        ROCK, PAPER, SCISSOR;
        /**
         * Compares this move with another move to determine a tie, a win, or
         * a loss.
         * 
         * @param otherMove
         *            move to compare to
         * @return 1 if this move beats the other move, -1 if this move loses to
         *         the other move, 0 if both moves tie
         */
        public int compareMoves(Move otherMove) {
            // Tie
            if (this == otherMove)
                return 0;
            //Compare if both moves are different
            switch (this) {
            case ROCK:
                return (otherMove == SCISSOR ? 1 : -1);
            case PAPER:
                return (otherMove == ROCK ? 1 : -1);
            case SCISSOR:
                return (otherMove == PAPER ? 1 : -1);
            }
            // Should never reach here
            return 0;
        }
    }
	/**
	 * Captures player's move and allows player to play again.
	 */
	private class User {
        /**
         * Scanner instance.
         */
		private Scanner inputScanner;
		/**
		 * class constructor to initialize Scanner object.
		 */
        public User() {
            inputScanner = new Scanner(System.in);
        }
        /**
         * Captures player input on valid keystrokes otherwise prompts again.
         * @return Valid moves Rock, Paper or Scissor 
         */
        public Move getMove() {
        	//Prompt the user to input one of the 3 options.
        	System.out.print("Rock, paper, or scissors? ");

            // Get the user input
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            if (firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S') {
                // User has entered a valid input.
                switch (firstLetter) {
                case 'R':
                    return Move.ROCK;
                case 'P':
                    return Move.PAPER;
                case 'S':
                    return Move.SCISSOR;
                }
            }
            // User has not entered a valid input then prompt again.
            return getMove();
        }
        /**
         * Asks the user if she wants to play again.Compares the response's first letter
         * to characters 'Y' or 'N' for yes or no respectively.
         * @return true if user wants to play again, false if not.
         */
        public boolean playAgain() {
        	System.out.println("Do you want to play again?\n(Enter Yes/No)");
        	String response = inputScanner.nextLine();
        	response = response.toUpperCase();
        	return response.charAt(0) == 'Y';
        }
    }
	/**
	 * Captures Computer's move.
	 */
	private class Computer {
		/**
		 * Generates random move for Computer player.
		 * @return ROCK if O, PAPER if 1, SCISSOR if 2.
		 */
		public Move getMove() {
		    Move[] moves = Move.values();
		    Random random = new Random();
		    int index = random.nextInt(moves.length);
		    return moves[index];
	    }
	}
	/**
	 * Reference variable for User class.
	 */
	private User user;
	/**
	 * Reference variable for Computer class.
	 */
	private Computer computer;
	/**
	 * Constructor
	 * 
	 * Creates objects of User and Computer class.
	 */
	public RockPaperScissor() {
		user = new User();
		computer = new Computer();
	}
	/**
	 * Initiates the game by getting the moves from both players and
	 * determining the winner.
	 */
	public void startGame() {
		System.out.println("Get ready for the game of Rock, Paper, Scissors!");
		Move userMove = user.getMove();
		Move computerMove = computer.getMove();
		System.out.println("\nYou played: " + userMove + ".");
		System.out.println("Computer played: " + computerMove + ".\n");
		int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
        case 0: // Tie
            System.out.println("Tie!");
            break;
        case 1: // User wins
            System.out.println(userMove + " beats " + computerMove + ". You won!");
            break;
        case -1: // Computer wins
            System.out.println(computerMove + " beats " + userMove + ". You lost.");
            break;
        }
     // Ask the user to play again
        if (user.playAgain()) {
            System.out.println();
            startGame();
        } else {
        	System.out.println("Game Over.");
        }
	}
	/**
	 * Main Method
	 * 
	 * Starts the game.
	 * @param args Array of Strings
	 */
	public static void main(String[] args) {
		RockPaperScissor game = new RockPaperScissor();
		game.startGame();
	}
}