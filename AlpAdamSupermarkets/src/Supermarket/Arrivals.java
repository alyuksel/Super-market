package Supermarket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import BDD.Data;
import Factory.NoSuchProductException;
import Factory.ProductFactory;
import Produits.Produit;

public class Arrivals {
	private ArrayList<Produit> arrivals;
	private ProductFactory factory;
	private Data data;
	
	public Arrivals() {
		this.factory = new ProductFactory();
		this.arrivals = new ArrayList<>();
		try {
			this.data = new Data();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		ResultSet res = this.data.requete("SELECT * FROM Produit;");
		try {
			while(res.next()){
				for(int i=0; i<res.getInt("number");i++){
					Produit p = factory.createProduct(res.getString("name"));
					this.arrivals.add(p);
				}
			}
		} catch (SQLException | NoSuchProductException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public ArrayList<Produit> getArrivals() {
		return arrivals;
	}
	
	
}
