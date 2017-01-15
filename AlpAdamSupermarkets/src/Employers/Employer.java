package Employers;

public abstract class Employer {
	protected String firstName;
	protected String lastName;
	
	public Employer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public abstract double salary();
	
	@Override
	public String toString() {
		return this.firstName +" " + this.lastName;
	}
	
	public String getName(){
		return this.firstName;
	}
	public String getLast(){
		return this.lastName;
	}
}
