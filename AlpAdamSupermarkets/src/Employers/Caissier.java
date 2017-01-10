package Employers;

public class Caissier extends Employer {

	public Caissier(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public double salary() {
		return 1300.52;
	}

}
