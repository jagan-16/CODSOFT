import java.util.Random;
import java.util.Scanner;
public class Guessing_game {
   public static void main(String[] args) {
     int tries = 0;
     Random random = new Random();
     int num = random.nextInt(101);
     Scanner scanner = new Scanner(System.in);
     
     while (tries<3) {
      System.out.println("Enter the guessed_number:");
      
      int guess = scanner.nextInt();
      tries++;
       if (guess <1 || guess > 100) {
         System.out.println("Guess the number betweeb 1 to 100");
       }
       else if (num == guess){
         System.out.println("you won the game!");

       }
       else if (num < guess) {
         System.out.println("oops!,too high");
       }
       
       else {
         System.out.println("oops!,too low");
       }
       
     }
     System.out.println("you have lost the game!");
     System.out.println("the num is :"+num);
     scanner.close();
   }
}