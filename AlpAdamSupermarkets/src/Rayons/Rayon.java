package Rayons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import Produits.ProductType;
import Produits.Produit;
import Produits.Promotion;
import Tools.GenericClass;

public abstract class Rayon {
	private ProductType type;
	private HashMap <String, ArrayList<Produit>> mapProduct ;
	
	protected Rayon(ProductType type){
		this.type = type;
		this.mapProduct = new HashMap<>();
	}
	
	public ArrayList<String> getProduit(){
		return (ArrayList<String>) mapProduct
				.values()
				.stream()
				.flatMap(list -> list.stream())
				.map(prod -> prod.getLabel())
				.collect(Collectors.toList());
	}
	
	public Integer numberOfProductWithPrice(double price){
		return (int) mapProduct.values().stream()
				.flatMap(list->list.stream())
				.filter(prod->prod.getPrice()>=price)
				.count();
	}
	public Integer numberOfProduct(){
		return (int) mapProduct
				.values()
				.stream()
				.flatMap(list -> list.stream())
				.count();
	}
	public Integer numberOfPromotions(){
		return (int) mapProduct
				.values()
				.stream()
				.flatMap(list -> list.stream())
				.filter(prod -> prod.getClass().getSuperclass().equals(Promotion.class))
				.count();
	}
	
	
	public <T extends Produit> void addProduct(T produit){
		GenericClass.addProduct(mapProduct, produit);
    	}
	
	public HashMap <String, ArrayList<Produit>> getMapProduct() {
		return mapProduct;
	}
	public ProductType getProductType(){
		return this.type;
	}
	public ArrayList<Double> getPrices() {
		return (ArrayList<Double>) mapProduct
				.values()
				.stream()
				.flatMap(list -> list.stream())
				.map(prod -> prod.getPrice())
				.collect(Collectors.toList());
	}
	
}
