package Factory;

import Produits.MoinDixPourCent;
import Produits.Nivea;
import Produits.Nutella;
import Produits.Produit;
import Produits.YvesRocher;

public class ProductFactory {
	public Produit createProduct(String name) throws NoSuchProductException{
		switch (name) {
		case "nivea":
			return new Nivea();
		case "nutella":
			return new Nutella();
		case "yves rocher":
			return new YvesRocher();
		default:
			throw new NoSuchProductException();
		}
	}
	
	public Produit createPromotionnalProduct(Produit produit, String name) throws NoSuchPromotionException{
		switch (name) {
		case "10%":
			return new MoinDixPourCent(produit);
		default:
			throw new NoSuchPromotionException();
		}
	}
}
