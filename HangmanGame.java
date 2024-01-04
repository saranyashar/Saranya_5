import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = {"java", "python", "javascript", "html", "css", "ruby", "android"};
    private static final int MAX_TRIES = 6;

    private String selectedWord;
    private char[] guessedLetters;
    private int incorrectGuesses;

    public HangmanGame() {
        selectedWord = getRandomWord();
        guessedLetters = new char[selectedWord.length()];
        incorrectGuesses = 0;
        initializeGuessedLetters();
    }

    private String getRandomWord() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }

    private void initializeGuessedLetters() {
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    private void displayGuessedWord() {
        for (char letter : guessedLetters) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }

    private void displayHangman() {
        if (incorrectGuesses > 0) System.out.println(" 0");
        if (incorrectGuesses > 1) System.out.println("/|\\");
        if (incorrectGuesses > 2) System.out.println(" |");
        if (incorrectGuesses > 3) System.out.println("/ \\");
    }

    private boolean isGameOver() {
        return incorrectGuesses >= MAX_TRIES || !new String(guessedLetters).contains("_");
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");

        while (!isGameOver()) {
            System.out.println("\nIncorrect guesses left: " + (MAX_TRIES - incorrectGuesses));
            displayGuessedWord();
            displayHangman();

            System.out.print("Enter a letter: ");
            char guess = scanner.next().charAt(0);

            if (selectedWord.contains(String.valueOf(guess))) {
                for (int i = 0; i < selectedWord.length(); i++) {
                    if (selectedWord.charAt(i) == guess) {
                        guessedLetters[i] = guess;
                    }
                }
            } else {
                incorrectGuesses++;
            }
        }

        if (new String(guessedLetters).equals(selectedWord)) {
            System.out.println("Congratulations! You guessed the word: " + selectedWord);
        } else {
            System.out.println("Sorry, you ran out of guesses. The word was: " + selectedWord);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        HangmanGame hangmanGame = new HangmanGame();
        hangmanGame.play();
    }
}
