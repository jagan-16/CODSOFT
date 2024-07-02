import java.util.Scanner;

public class grade_calculator {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the no of subjects:");
        int n = scanner.nextInt();
        int sum = 0;
        int marks [] = new int[n];
        for(int i = 0 ;i<n ; i++){
            System.out.println("Enter the subject " + (i+1) + " mark out of 100 :" );
            marks[i] = scanner.nextInt();
        }
        
        for(int mark : marks){
             sum+=mark;
            
        }
        double average = (double)sum / n;
        System.out.println("Total mark is : "+sum);
        System.out.println(" Average Percentage is : " + average);
        if (average >90 ){
            System.out.println("The grade is O");
        }
        else if (average >= 80 && average < 90 ){
            System.out.println("The grade is A+");
        }
        else if (average >= 70 && average < 80){
            System.out.println("The grade is A");
        }
        else if (average >=60 && average <70) {
            System.out.println("The grade is B+");

        }
        else if (average >=  50 && average < 60){
            System.out.println("The grade is B");
        }
        else if (average >= 40 && average < 50) {
            System.out.println("The grade is C");

        }
        else {
            System.out.println("The grade is F");
        }
        scanner.close();
    }
}