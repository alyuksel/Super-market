package Supermarket;

import java.util.ArrayList;
import java.util.Observable;

import BDD.Data;
import Factory.NoSuchProductException;
import Factory.NoSuchRayonException;
import Factory.ProductFactory;
import Factory.RayonFactory;
import Produits.ProductType;
import Produits.Produit;

public class Commande extends Observable{
	private ArrayList<Produit> commande;
	private ProductFactory factory;
	private Data data ;

	
	public Commande() {
		this.commande = new ArrayList<>();
		this.factory = new ProductFactory();
		try {
			this.data = new Data();
		} catch (ClassNotFoundException e) {System.err.println(e.getMessage());}
	}
	
	
	public void setCommande(Object product,int quantite,Object type, SuperMarket market){
			try {
				for(int j = 0; j<quantite;j++)
					this.commande.add(factory.createProduct(product.toString()));
				if(market.getRays().containsKey(ProductType.valueOf(type.toString()))){
					market.addProduct(commande);
				}else{
					market.setRayon(new RayonFactory().createRayon(type.toString()));
					market.addProduct(commande);
				}
				data.requete("insert into Produit(name,type,number,market) values ('"+product.toString()+"','"+
					type+"',"+quantite+",'"+market.getName()+"')");
			} catch (NoSuchProductException | NoSuchRayonException e) {System.err.println(e.getMessage());}
		
	}
	
	public void setRayonForCommande(Object type, Object market){
		data.requete("insert into Rayon (type,market) values ('"+type.toString()+"','"+market.toString()+"')");
	}
	
	public void clearCommande(){
		commande.clear();
	}
	
	public ArrayList<Produit> getCommande(){
		return this.commande;
	}
	


	
	
}
