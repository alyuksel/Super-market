package Employers;

public abstract class Employer {
	protected String firstName;
	protected String lastName;
	
	public Employer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public abstract double salary();
	
}
