package in.SpringLearning;

public class Car {

	public Engine eng;

	public Car() {
		System.out.println("Car Constructor :: Executed");

	}

	// Constructor Injection
	public Car(Engine eng) {

		System.out.println("Car Constructor :: Executed");

		this.eng = eng;
	}

	public void drive() {
		boolean start = eng.start();

		if (start) {
			System.out.println("Engine Started ");

			System.out.println("Journey Started ");
		} else {
			System.out.println("Engine having trouble");
		}

	}

}
