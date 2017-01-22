package Supermarket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import BDD.Data;
import Employers.Employer;
import Factory.MarketsFactory;
import Factory.NoSuchMarketException;
import Factory.NoSuchProductException;
import Factory.NoSuchRayonException;
import Factory.ProductFactory;
import Factory.RayonFactory;
import Produits.ProductType;
import Produits.Produit;
import Produits.Promotion;

public class Entreprise extends Observable{
	private static Entreprise isInstanciate = null;
	private Data data;
	private String name;
	private Map<String,SuperMarket> supermarkets;
	private MarketsFactory marketFactory;
	private ProductFactory productFactory;
	private RayonFactory rayonFactory;
	private SuperMarket currentMarket;
	
	private Entreprise(){
		this.name = "AlpAdamSupermakets";
		this.marketFactory = new MarketsFactory();
		this.rayonFactory = new RayonFactory();
		this.productFactory = new ProductFactory();
		this.supermarkets = new HashMap<>();
		try {
			data = new Data();
			ResultSet res = data.select("select * from Market");
			while(res.next()){
				this.supermarkets.put(res.getString("name"),this.marketFactory.createMarket(res.getString("name"),res.getString("type")));
			}
			ResultSet resray = data.select("select * from Rayon");
			while(resray.next()){
				this.supermarkets.get(resray.getString("market")).setRayon(rayonFactory.createRayon(resray.getString("type")));
			}
			ResultSet resprod = data.select("select * from Produit");
			while(resprod.next()){
				for(int i = 0; i<resprod.getInt("number");i++){
					Produit p = productFactory.createProduct(resprod.getString("name"));
					if(resprod.getInt("promo")>0){
						p = new Promotion(p,resprod.getInt("promo"));
					}
					
					this.supermarkets.get(resprod.getString("market")).getRays()
									.get(ProductType.valueOf(resprod.getString("type")))
									.addProduct(p);
				}
			}
			ResultSet employers = data.select("select * from Employer");
			while(employers.next()){
				this.addEmployerInSupermarkets(employers.getString("prenom"),employers.getString("nom"),employers.getString("type"),employers.getString("market"));
				
			}
			
		} catch (ClassNotFoundException | SQLException | NoSuchMarketException | NoSuchProductException | NoSuchRayonException e) {
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
		data.requete("delete from Produit where market='"+name+"'");
		data.requete("delete from Rayon where market='"+name+"'");
		data.requete("delete from Ventes where market='"+name+"'");
		data.requete("delete from Employer where market ='"+name+"'");
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
	public Set<Employer> employers(){
		return getCurrentMarket().getEmployer();
	}
	public boolean isAlreadyAManager(){
		for(Employer e : this.getCurrentMarket().getEmployer()){
			if(e.isManager())
				return true;
		}
		return false;
	}
	public void addEmployer(String firstName, String lastName, String type){
		this.getCurrentMarket().addEmployers(firstName, lastName, type);
		this.getCurrentMarket().addEmployerToManage();
		this.data.requete("insert into Employer(nom,prenom,type,market) values ('"+firstName+"','"+lastName+"','"+type+"','"+this.getCurrentMarket().getName()+"')");
		this.update();
	}
	public boolean addEmployerToManage(){
		return this.getCurrentMarket().addEmployerToManage();
	}

	public void addEmployerInSupermarkets(String firstName, String lastName, String type,String market){
		this.supermarkets.values().forEach(s -> {if(s.getName().equals(market)){s.addEmployers(firstName, lastName, type);s.addEmployerToManage();}});
	}
	public void setProductOnPromo(String label,int pourcentage){
		Set<Promotion> promo = new HashSet<>();
		this.getCurrentMarket().rayons.values()
							   .stream()
							   .map(p->p.getMapProduct())
							   .flatMap(e->e.values().stream())
							   .flatMap(l->l.stream())
							   .filter(p->p.getLabel().equals(label))
							   .forEach(p-> promo.add(new Promotion(p,pourcentage)));
		
		this.getCurrentMarket().addProduct(promo);
		this.getCurrentMarket().removeProduct(label);
		this.data.requete("update Produit set promo = "+pourcentage+" where name = '"+label+"'");
		this.getCurrentMarket().update();
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	

	
}
