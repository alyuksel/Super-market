package Employers;

public class Manager extends Employer{
	
	
	public Manager(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public double salary() {
		return 4000.52;
	}
	
}
