import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {
		
		Lamp led = new Lamp();
		Lamp halogen = new Lamp();
		Lamp Flourescent = new Lamp();
		Lamp Incandescent = new Lamp();
		
		led.name = args[0];
		halogen.name = args[2];
		Flourescent.name = args[0];
		Incandescent.name = args[2];
		
		led.isOn = Boolean.parseBoolean(args[3]);
		Flourescent.isOn = Boolean.parseBoolean(args[3]);
		Flourescent.turnOn();
		System.out.println("Flourenscent turned on by" + Flourescent.name);
		led.turnOn();
		System.out.println("Led turned on by" + led.name);
		halogen.isOn = Boolean.parseBoolean (args[1]);
		Incandescent.isOn = Boolean.parseBoolean(args[1]);
		Incandescent.turnOff();
		System.out.println("Incandescent turned off by" + Incandescent.name);
		halogen.turnOff();
		System.out.println("Halogen turned off by" + halogen.name);
	}
		
}
