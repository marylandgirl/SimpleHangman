import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
/*
* The SimpleHangman class is for an assignment from Java Bootcamp
*   to practice with ArrayList. This program is a game that randomly
*   selects a word from an ArrayList. Then it repeatedly prompts the 
*   player for guesses. The player has up to 5 wrong guesses and the
*   results are displayed to the screen with each guess. Additionally,
*   an appropriate message is given at the end of the game informing the
*   player if they've won or lost.
*
*   Kim Levin
*   4/27/2020
*/

public class SimpleHangman {
   public static void main(String[] args) {

      //Declare and Initialize variables
      Scanner keyboard = new Scanner(System.in);
      String guess = "";
      int wordNum;
      int wrongGuessCount = 0;
      boolean done = false;
      String chosenWord = "";
      int chosenWordLength = 0;
      char[] obscuredArray = null;
      String lettersGuessed = "";
      String obsWord = "";
      int letterIndex;
  
      //Populate the Word List
      ArrayList<String> wordList = new <String> ArrayList();
      wordList.add("tree");
      wordList.add("rain");
      wordList.add("bear");
      wordList.add("encourage");
      wordList.add("promise");
      wordList.add("soup");
      wordList.add("chess");
      wordList.add("insurance");
      wordList.add("pancakes");
      wordList.add("stream");

      System.out.println("Welcome, let's play hangman!\n");

      //Randomly choose a word from the word list
      //   this word is what the player will try to guess
      Random rand = new Random();
      wordNum = rand.nextInt(wordList.size());
      chosenWord = wordList.get(wordNum);
      obsWord = obscureWord(obscuredArray,lettersGuessed,chosenWord);
      
      //Display starting message
      System.out.print("Here is the word I'm thinking of: ");         
      System.out.println(obsWord);         

      //Repeatedly prompt the user to enter a guess
      //Check if letter or word guessed matches any part of the randomly
      //   selected word.
      do {
         System.out.print("Enter letter or word guess: ");
         guess = keyboard.nextLine();
         if (guess.equalsIgnoreCase(chosenWord)) {
            System.out.printf("You've won! The word was %s\n",chosenWord);
            done = true;
         } else if (chosenWord.contains(guess)){
            if (lettersGuessed == "") {
               lettersGuessed = lettersGuessed.concat(guess);
            } else if (!lettersGuessed.contains(guess)) {
               lettersGuessed = lettersGuessed.concat(",");
               lettersGuessed = lettersGuessed.concat(guess);
            } else {
               System.out.printf("%s was guessed before\n",guess);
            }
            //The obscureWord method returns a string composed of
            //   underscores where letters haven't been guessed
            //   and letters that have been correctly guessed
            //  (for example: r_ _ _ _  is what the player sees if they've
            //   correctly guessed "r" for the randomly chosen word of rain)
            obsWord = obscureWord(obscuredArray,lettersGuessed,chosenWord);
            System.out.printf("Your guess so far: %s\n\n",obsWord);

            if (obsWord.equals(chosenWord)) {
               System.out.printf("You've won! The word was %s\n",chosenWord);
               done = true;
            }
         } else {
            wrongGuessCount++;
            System.out.printf("You have guessed incorrectly ");
            System.out.printf("%d/6\n",wrongGuessCount);
            System.out.printf("Your guess so far: %s\n\n",obsWord);
         }

         if (wrongGuessCount >= 6) {
            done = true;
            System.out.printf("Sorry, you have no more guesses left ");
            System.out.printf("The word was %s\n",chosenWord);
         }

      }while (!done);
      System.out.println("\nThank you for playing!");
   }

   //Accepts 3 arguments - After processing, char[] arrayObs will hold the
   // correctly guessed letters at the same location as the word being guessed.
   // And it will contain underscores at the other locations.
   // The string being returned is a string representation of arrayObs
   //   which is an array of characters
   static String obscureWord(char[] arrayObs,String guesd, String chosen ) {
      arrayObs = new char[chosen.length()];
      Arrays.fill(arrayObs,'_');
      String retStr = "";

      for ( int i = 0; i < guesd.length(); i++) {
         for ( int j = 0; j < chosen.length(); j++) {
            if (guesd.charAt(i) == chosen.charAt(j)){
              arrayObs[j] = guesd.charAt(i);             
            }
         }
      }

      for (int i = 0; i < arrayObs.length; i++) {
         retStr  += Character.toString(arrayObs[i]);
      }

      return retStr;
   }
}
