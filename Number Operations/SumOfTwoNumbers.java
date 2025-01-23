import java.util.Scanner;

public class SumOfTwoNumbers
{
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(" ");
		int num1 = scanner.nextInt();
		System.out.print(" ");
		int num2 = scanner.nextInt();
		int sum = num1 + num2;
		System.out.println (sum);
	}
}