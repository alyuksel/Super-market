package Produits;

public class MoinDixPourCent extends Promotion {
	
	public MoinDixPourCent(Produit produit) {
		super(produit);
		
	}
	
	@Override
	public double getPrice() {
		return produit.getPrice() - (10*produit.getPrice()/100);
	}

	@Override
	public String getLabel() {
		return produit.getLabel() + " -10 %";
	}
	
	
	public void affiche() {
		System.out.println(getLabel() + " " +getPrice()+"e au lieu de " + produit.getPrice()+"e");
	}


	@Override
	public ProductType getProductType() {
		return this.type;
	}
	

}
