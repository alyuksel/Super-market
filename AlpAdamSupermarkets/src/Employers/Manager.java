package Employers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Manager extends Employer{
	
	private Set<Employer> employers;
	
	public Manager(String firstName, String lastName) {
		super(firstName, lastName);
		super.isManager = true;
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
	public Integer nubmerOfEmployers(){
		return this.employers.size();
	}
	private long numberOf(Predicate<? super Employer> typeOfEmployers){
		return this.employers
			.stream()
			.filter(typeOfEmployers)
			.count();
	}
	private Set<String> listOfSubalterns(Predicate<? super Employer> typeOfEmployers){
		return this.employers
			.stream()
			.filter(typeOfEmployers)
			.map(e -> e.toString())
			.collect(Collectors.toSet());
	}
	
	private String subalterns(Predicate<? super Employer> typeOfEmployers){
		return numberOf(typeOfEmployers) + " " + listOfSubalterns(typeOfEmployers);
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName+" dirige : \n"
	+ subalterns(p -> p.getClass().equals(Caissier.class)) + " Caissiers et \n" 
	+ subalterns(p -> p.getClass().equals(Manutentionnaire.class))
	+ " Manutentionnaires \n" ; 
	
	}
	
	
}
