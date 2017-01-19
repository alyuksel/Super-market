package Produits;

public class CocaCola implements Produit {
	private ProductType type;
	
	public CocaCola() {
		this.type = ProductType.Alimentary;
	}
	@Override
	public String getLabel() {
		return "cocacola";
	}

	@Override
	public double getPrice() {
		return 1.90;
	}

	@Override
	public ProductType getProductType() {
		return this.type;
	}

}
