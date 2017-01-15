package Factory;

import Employers.Caissier;
import Employers.Employer;
import Employers.Manager;
import Employers.Manutentionnaire;

public class EmployerFactory {
	public Employer createEmployer(String firstName, String lastName, String type) throws NoSuchEmployerException{
		switch (type){
			case "caissier" : return new Caissier(firstName, lastName);
			case "manu" : return new Manutentionnaire(firstName, lastName);
			case "Manager" : return new Manager(firstName, lastName);
			default : throw new NoSuchEmployerException();
		}
	}
	
	
}
