package Factory;


import Produits.CocaCola;
import Produits.JeanLevis;
import Produits.MrPropre;
import Produits.Nivea;
import Produits.Nutella;
import Produits.Prince;
import Produits.ProductType;
import Produits.Produit;
import Produits.TV;
import Produits.Xmen;
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
		case "cocacola":
			return new CocaCola();
		case "xmen":
			return new Xmen();
		case"jeanlevis":
			return new JeanLevis();
		case "tv":
			return new TV();
		case "mrpropre":
			return new MrPropre();
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
