public class Lamp{
	
	boolean isOn;
	String name;
	
	void turnOn(){
	isOn = true;
		System.out.println("Light on?" + isOn);
	}
	
	void turnOff() {
	isOn = false;
	System.out.println("Light Off?" + isOn);
	}
}