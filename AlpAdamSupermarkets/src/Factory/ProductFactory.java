package Factory;


import Produits.Nivea;
import Produits.Nutella;
import Produits.Prince;
import Produits.ProductType;
import Produits.Produit;
import Produits.YvesRocher;

public class ProductFactory {
	public Produit createProduct(String name) throws NoSuchProductException{
		switch (name) {
		case "nivea":
			return new Nivea();
		case "nutella":
			return new Nutella();
		case "yvesrocher":
			return new YvesRocher();
		case "prince":
			return new Prince();
		default:
			throw new NoSuchProductException();
		}
	}
	
	
	public  boolean isAlimentary(String s){
		Boolean res=false;
		try {
			res = createProduct(s).getProductType().equals(ProductType.Alimentary);
		} catch (NoSuchProductException e) {
			System.err.println(e.getMessage());;
		}
		return res;
	}
}
