package Supermarket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


import BDD.Data;
import Factory.MarketsFactory;
import Factory.NoSuchMarketException;

public class Entreprise extends Observable{
	private static Entreprise isInstanciate = null;
	private Data data;
	private String name;
	private Map<String,SuperMarket> supermarkets;
	private MarketsFactory marketFactory;
	private SuperMarket currentMarket;
	
	private Entreprise(){
		this.name = "AlpAdamSupermakets";
		this.marketFactory = new MarketsFactory();
		this.supermarkets = new HashMap<>();
		try {
			data = new Data();
			ResultSet res = data.select("select * from Market");
			while(res.next()){
				supermarkets.put(res.getString("name"),this.marketFactory.createMarket(res.getString("name"),res.getString("type")));
			}
					
		} catch (ClassNotFoundException | SQLException | NoSuchMarketException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static Entreprise getMySuperMarkets(){
		if (isInstanciate == null)
			isInstanciate = new Entreprise();
		return isInstanciate;
	}
	
	/*Ajout d'un supermarché*/
	public void addSuperMarket(SuperMarket superMarket){
		 supermarkets.put(superMarket.getName(), superMarket);
	}
	
	public boolean addSuperMarketOnDB(String name,String type){
		return data.requete("insert into Market(name,type) values('"+name+"'"+",'"+type+"'"+")");
	}
	
	/*Ajout d'une collection de Supermarchés*/
	public void addSuperMarkets(Collection<SuperMarket> supermarkets){
		supermarkets.forEach(sm -> this.supermarkets.put(sm.getName(), sm));
	}
	
	/*Suppression d'un supermarché*/
	public void removeSupermarkets(String name){
		this.supermarkets.remove(name);
		data.requete("delete from Market where name='"+name+"'");
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
		if(currentMarket!=null)currentMarket.deleteObservers();
		currentMarket = supermarkets.get(market);
		update();
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
}
