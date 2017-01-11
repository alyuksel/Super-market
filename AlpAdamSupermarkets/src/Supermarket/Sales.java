package Supermarket;

import java.util.ArrayList;

import BDD.Data;
import Factory.NoSuchProductException;
import Factory.ProductFactory;
import Produits.Nutella;
import Produits.ProductType;
import Produits.Produit;
import Rayons.AlimentaryRay;

public class Sales {
	private ArrayList<Produit> sales;
	private double recette;
	private SuperMarket market;
	private Data data;
	
	public Sales(SuperMarket market) {
		this.sales = new ArrayList<>();
		this.recette = 0;
		this.market = market;
		try {
			this.data = new Data();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void productSold(String produit,Integer number){
		ProductType type = ProductType.Beauty;
		double price = 0;
		int numberOfSoldProduct = 0;
		if(market.getProduct().contains(produit)){
			try {
				type = new ProductFactory().createProduct(produit).getProductType();
				price = new ProductFactory().createProduct(produit).getPrice();
			} catch (NoSuchProductException e) {System.err.println(e.getMessage());}
			for(int i=0;i<number;i++){
				sales.add(market.getRays().get(type).getMapProduct().get(produit).get(i));
				market.getRays().get(type).getMapProduct().get(produit).remove(i);
				numberOfSoldProduct ++;
			}
			recette = recette + (numberOfSoldProduct*price);
		}
		data.requete("insert into Ventes(name,type,recette,market) values ('"+produit+"','"+type.toString()+"',"+recette+",'"+market.getName()+"')");
	}
	
	public double getNumberOfSales(){
		return this.recette;
	}
	public static void main(String[] args) {
		Generalist gen = new Generalist("Adam");
		gen.setRayon(new AlimentaryRay());
		ArrayList<Produit> arr = new ArrayList<>();
		arr.add(new Nutella());
		arr.add(new Nutella());
		arr.add(new Nutella());
		arr.add(new Nutella());
		arr.add(new Nutella());
		arr.add(new Nutella());
		gen.addProduct(arr);
		System.out.println(gen.getRays().get(ProductType.Alimentary).getMapProduct().get("nutella"));
		Sales sale = new Sales(gen);
		sale.productSold("nutella", 3);
		System.out.println(sale.sales);
		System.out.println(gen.getRays().get(ProductType.Alimentary).getMapProduct().get("nutella"));
		System.out.println(sale.recette);
		
		
		
		
	}
}
