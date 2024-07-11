package payroll;

public class CalculationClass {
	//declare class variables
	private double dblRate,dblHours,dblPay;

	private static int numberProcessed = 0;

	//create class constructor
	//it's a method that runs first and sets
	//everything up
	//Constructor has the same name as the class

	CalculationClass(double theRate, double theHours)
	{
	dblRate = theRate;
	dblHours = theHours;

	}
	private void CalculatePay()
	{
	dblPay = dblRate * dblHours;
	numberProcessed++;
	}
	public double getPay()
	{
	CalculatePay();
	return dblPay;
	}
	public int getEmployeeCount()
	{
	return numberProcessed;
	}
	}