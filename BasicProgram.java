import java.util.Scanner;

public class BasicProgram
{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a value with decimal point: ");
    double d = input.nextDouble();
    System.out.print("You have entered " + d + ". Thank you!");
  }
}
