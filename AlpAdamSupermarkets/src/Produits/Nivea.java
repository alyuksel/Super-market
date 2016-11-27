package Produits;

public class Nivea implements Produit {
	private ProductType type;
	public Nivea() {
		this.type = ProductType.Beauty;
	}
	@Override
	public String getLabel() {
		return "Nivea";
	}

	@Override
	public double getPrice() {
		return 5.5;
	}

	@Override
	public ProductType getProductType() {
		return this.type;
	}

	

}
