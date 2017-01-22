package Employers;

import java.util.Collection;

public abstract class Employer {
	protected String firstName;
	protected String lastName;
	protected boolean isManager;
	
	public Employer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.isManager=false;
	}
	
	public abstract double salary();
	public abstract boolean addEmployees(Collection <Employer> employers);
	
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
	public boolean isManager(){
		return this.isManager;
	}
}
