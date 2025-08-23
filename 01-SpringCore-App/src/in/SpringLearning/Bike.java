package in.SpringLearning;

public class Bike{
	
	public void drive()
	{
		
		Engine e = new Engine() ;
		
		boolean status = e.start();
		
		if(status)
		{
			System.out.println("Bike Journey Started .......");
		}
		else
		{
			System.out.println("Bike Engine having trouble ...");
		}
		
		
	}

}
