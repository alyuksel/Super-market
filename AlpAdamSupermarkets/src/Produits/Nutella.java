package Produits;

public class Nutella implements Produit{
	private ProductType type;
	
	public Nutella() {
		this.type = ProductType.Alimentary;
	}
	@Override
	public String getLabel() {
		return "Nutella";
	}

	@Override
	public double getPrice() {
		return 2.60;
	}

	public ProductType getProductType(){
		return this.type;
	}
	
	
	

}
