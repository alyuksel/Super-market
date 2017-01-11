package Employers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Manager extends Employer{
	
	private Set<Employer> employers;
	
	public Manager(String firstName, String lastName) {
		super(firstName, lastName);
		employers = new HashSet<>();
	}
	
	public boolean addAnEmployee(Employer employe){
		return employers.add(employe);
	}
	
	public boolean addEmployees(Collection <Employer> employers){
		return this.employers.addAll(employers);
	}
	
	public Set<Employer> getEmployers(){
		return this.employers;
	}

	@Override
	public double salary() {
		return 4000.52;
	}
	
}
