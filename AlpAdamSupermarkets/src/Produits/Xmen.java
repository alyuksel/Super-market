package Produits;

public class Xmen implements Produit {
	private ProductType type;
	public Xmen() {
		this.type = ProductType.Digital;
	}
	@Override
	public String getLabel() {
		return "xmen";
	}
	@Override
	public double getPrice() {
		return 30.55;
	}
	@Override
	public ProductType getProductType() {
		return this.type;
	}
}
