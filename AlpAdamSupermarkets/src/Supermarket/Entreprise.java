package Supermarket;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Entreprise {
	private static Entreprise isInstanciate = null;
	private String name;
	private Set<SuperMarket> supermarkets;
	
	private Entreprise(){
		this.name = "AlpAdamSupermakets";
		this.supermarkets = new HashSet<>();
	}
	public static Entreprise getMySuperMarkets(){
		if (isInstanciate == null)
			isInstanciate = new Entreprise();
		return isInstanciate;
	}
	
	/*Ajout d'un supermarché*/
	public boolean addSuperMarket(SuperMarket superMarket){
		return supermarkets.add(superMarket);
	}
	
	/*Ajout d'une collection de Supermarchés*/
	public void addSuperMarkets(Collection<SuperMarket> supermarkets){
		supermarkets.forEach(sm -> this.supermarkets.add(sm));
	}
	
	/*Suppression d'un supermarché*/
	public void removeSupermarkets(String name){
		this.supermarkets.forEach(sm-> {if(sm.getName().equals(name)) this.supermarkets.remove(sm);});
	}
	
	
	public String getName (){
		return this.name;
	}
	
	public Set<SuperMarket> getSupermarkets(){
		return this.supermarkets;
	}
	
}
