package Supermarket;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	private ArrayList<ArrayList<String>> salesOnDB;
	private double recette;
	private SuperMarket market;
	private Data data;
	public Sales(SuperMarket market) {
		this.sales = new ArrayList<>();
		this.salesOnDB = new ArrayList<>();
		this.recette = 0;
		this.market = market;
		try {
			this.data = new Data();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void productSold(String produit,String number){
		ProductType type = ProductType.Beauty;
		double price = 0;
		int numberOfSoldProduct = 0;
		if(market.getProduct().contains(produit)){
			try {
				type = new ProductFactory().createProduct(produit).getProductType();
				price = new ProductFactory().createProduct(produit).getPrice();
			} catch (NoSuchProductException e) {System.err.println(e.getMessage());}
			for(int i=0;i<Integer.valueOf(number);i++){
				sales.add(market.getRays().get(type).getMapProduct().get(produit).get(i));
				System.out.println("VENDUUUUUUUUU");
				market.getRays().get(type).getMapProduct().get(produit).remove(i);
				System.out.println("VIREEEEEE");
				numberOfSoldProduct ++;
			}
			recette = recette + (numberOfSoldProduct*price);
		}
		data.requete("insert into Ventes(name,type,recette,market,number) values ('"+produit+"','"+type.toString()+"',"+recette+",'"+market.getName()+"',"+number+")");
		data.requete("update Produit set number = number - "+number+" where name = '"+produit+"' and market = '"+market.getName()+"'");
		System.out.println("REQUEEEEEEEEETE");
	}
	
	private ArrayList<ArrayList<String>> getSalesOnDB(){
		ResultSet res = data.select("Select * from Ventes where market = '"+market.getName()+"'");
		try {
			while (res.next()){
				ArrayList<String> row = new ArrayList<>();
				row.add(res.getString("name"));
				row.add(res.getString("type"));
				row.add(""+res.getInt("recette"));
				row.add(""+res.getInt("number"));
				salesOnDB.add(row);
 			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return salesOnDB;
	}
	public double getBenefits(){
		return this.recette;
	}
	
	public ArrayList<Produit> getSales(){
		return this.sales;
	}
	
	public ArrayList<ArrayList<String>> getSalesDB(){
		return this.salesOnDB;
	}
	
	public void setMarket(SuperMarket market){
		this.market = market;
		getSalesOnDB();
	}
	
 	public static void main(String[] args) {
		Generalist gen = new Generalist("Alpi");
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
		sale.productSold("nutella", "3");
		sale.productSold("nutella", "2");
		System.out.println(sale.getSalesDB());
		
		
		
		
	}
}
