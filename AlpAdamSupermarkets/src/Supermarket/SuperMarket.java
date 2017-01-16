package Supermarket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.stream.Collectors;

import Employers.Employer;
import Factory.EmployerFactory;
import Factory.NoSuchEmployerException;
import Produits.ProductType;
import Produits.Produit;
import Rayons.Rayon;

public abstract class SuperMarket extends Observable{
	protected boolean isReady;
	private String type;
	protected String name;
	protected Map<ProductType,Rayon> rayons;
	protected Set<Employer> employes;
	
	
	public SuperMarket(String name,String t) {
		this.name = name;
		type = t;
		rayons = new HashMap<>();
		employes = new HashSet<>();
	}
	
	
	public void setReady(boolean isReady){
		this.isReady = isReady;
	}

	public boolean isReady() {
		return this.isReady;
	}
	
	public String getType(){
		return type;
	}
	
	public boolean setRayon(Rayon rayon){
		if(rayons.containsKey(rayon.getProductType())){
			System.err.println("Rayon existant");
			return false;
		}
		
		rayons.put(rayon.getProductType(), rayon);
		return true;
	}
	public void removeProduct(String label){
		Iterator<Rayon> iter = this.rayons.values().iterator();
		while(iter.hasNext()){
			iter.next().removeProduct(label);
		}
	}
	public void setRayons(ArrayList<Rayon> instal){
		for (Rayon rayon : instal) {
			this.setRayon(rayon);
		}
	}
	public void addEmployers(String firstName, String lastName, String type){
		try {
			employes.add(new EmployerFactory().createEmployer(firstName, lastName, type));
		} catch (NoSuchEmployerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Set<Employer> getEmployer(){
		return employes;	
	}
	
	public boolean addEmployerToManage(){
		Set<Employer> basic = this.employes
							.stream()
							.filter(e -> (!e.isManager()))
							.collect(Collectors.toSet());
		Set<Employer> managers = this.employes
								.stream()
								.filter(e -> e.isManager())
								.collect(Collectors.toSet());
		
		for (Employer m : managers ){
			return m.addEmployees(basic);
		}
		return false;
			
		
	}
	public String getName() {
		return this.name;
	}
	
	public Integer getNumberOfRayon(){
		return this.rayons.size();
	}
	public <T extends Produit> void addProduct(Collection<T> arrivals){
		for(T t : arrivals){
			if(this.rayons.containsKey(t.getProductType()))
				rayons.get(t.getProductType()).addProduct(t);
		}
	}	
	
	@Override
	public String toString() {
		return "Nom du magasin : "+getName()+" , "
				+ "Nombre de rayon : "+getNumberOfRayon()+" , "
				+"Type : "+getType();
	}
	
	public Set<String> getProduct(){
		return this.rayons.values().stream()
			.flatMap(r -> r.getProduit().stream())
			.collect(Collectors.toSet());
	}
	
	public Map<ProductType,Rayon> getRays(){
		return rayons;
	}
	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	
	public Map<String,Integer> toStringInt(){
		Map<String,Integer> map = new HashMap<String,Integer>();
		rayons.values().forEach(r->r.getMapProduct().forEach((k,v)->map.put(k,v.size())));
		return map;
	}
}
