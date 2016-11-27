package Supermarket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import Employers.Employer;
import Produits.Produit;
import Rayons.Rayon;
import Produits.ProductType;

public abstract class SuperMarket {
	protected boolean isReady;
	
	protected String name;
	protected Map<ProductType,Rayon> rayons;
	protected Set<Employer> employes;
	
	
	public SuperMarket(String name) {
		this.name = name;
		rayons = new HashMap<>();
		employes = new HashSet<>();
	}
	
	
	public void setReady(boolean isReady){
		this.isReady = isReady;
	}

	public boolean isReady() {
		return this.isReady;
	}

	public boolean setRayon(Rayon rayon){
		if(rayons.containsKey(rayon.getProductType())){
			System.err.println("Rayon existant");
			return false;
		}
		
		rayons.put(rayon.getProductType(), rayon);
		return true;
	}
	public void setRayons(ArrayList<Rayon> instal){
		for (Rayon rayon : instal) {
			this.setRayon(rayon);
		}
	}
	public String getName() {
		return this.name;
	}
	
	public Integer getNumberOfRayon(){
		return this.rayons.size();
	}
	public <T extends Produit> void addProduct(Collection<T> arrivals){
		for(T t : arrivals){
			rayons.get(t.getProductType()).addProduct(t);
		}
	}	
	
	@Override
	public String toString() {
		return "Nom du magasin : "+getName()+" , "
				+ "Nombre de rayon : "+getNumberOfRayon()+" , "
				+ "aliments, beauté";
	}
	

	
}
