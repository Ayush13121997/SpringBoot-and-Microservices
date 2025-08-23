package in.SpringLearning;

public class Car extends Engine{

	public void drive()
	{
		boolean status = super.start();
		
		if(status)
		{
			System.out.println("Car Journey Started");
		}
		else
		{
			System.out.println("Car Engine Having trouble");
		}
		
	}

}
