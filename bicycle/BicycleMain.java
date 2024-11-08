public class BicycleMain
{
	public static void main(String[] args) {
		Bicycle sportsBicycle = new Bicycle();
		int Gears = sportsBicycle.gear;
		System.out.println("Number of Gears: " + Gears);
		sportsBicycle.braking();
	}
}
