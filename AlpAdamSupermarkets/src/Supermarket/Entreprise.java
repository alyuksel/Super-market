package Supermarket;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

public class Entreprise extends Observable{
	private static Entreprise isInstanciate = null;
	private String name;
	private Map<String,SuperMarket> supermarkets;
	private SuperMarket currentMarket;
	
	private Entreprise(){
		this.name = "AlpAdamSupermakets";
		this.supermarkets = new HashMap<>();
	}
	public static Entreprise getMySuperMarkets(){
		if (isInstanciate == null)
			isInstanciate = new Entreprise();
		return isInstanciate;
	}
	
	/*Ajout d'un supermarché*/
	public  void addSuperMarket(SuperMarket superMarket){
		 supermarkets.put(superMarket.getName(), superMarket);
	}
	
	/*Ajout d'une collection de Supermarchés*/
	public void addSuperMarkets(Collection<SuperMarket> supermarkets){
		supermarkets.forEach(sm -> this.supermarkets.put(sm.getName(), sm));
	}
	
	/*Suppression d'un supermarché*/
	public void removeSupermarkets(String name){
		this.supermarkets.remove(name);
	}
	
	
	public String getName (){
		return this.name;
	}
	
	public Map<String,SuperMarket> getSupermarkets(){
		return this.supermarkets;
	}
	
	public SuperMarket getCurrentMarket(){
		return currentMarket;
	}
	public void setCurrent(String market) {
		currentMarket = supermarkets.get(market);
		update();
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
}
