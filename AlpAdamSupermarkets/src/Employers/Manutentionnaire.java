package Employers;

import java.util.Collection;

public class Manutentionnaire extends Employer {

	public Manutentionnaire(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public double salary() {
		return 1200.85;
	}

	@Override
	public boolean addEmployees(Collection<Employer> employers) {
		return false;
	}
	
}
