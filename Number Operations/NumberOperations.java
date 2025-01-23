import java.util.Scanner;

public class NumberOperations
{
	public static void main(String[]args) {
		Scanner scanner = new Scanner (System.in);
		System.out.print ("Enter First Number: ");
		int N1 = scanner.nextInt();
		System.out.print("Enter Second Number: ");
		int N2 = scanner.nextInt();
		int div = N1 / N2;
		int add = N1 / N2;
		int mul = N1 / N2;
		int min = N1 / N2;
		System.out.println("div: "+ div);
		System.out.println("add: "+ div);
		System.out.println("mul: "+ div);
		System.out.println("min: "+ div);
	}
}