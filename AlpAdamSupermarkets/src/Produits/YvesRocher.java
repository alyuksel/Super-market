package Produits;

public class YvesRocher implements Produit {
	private ProductType type;
	public YvesRocher() {
		this.type = ProductType.Beauty;
	}
	@Override
	public String getLabel() {
		return "Yves Rocher";
	}

	@Override
	public double getPrice() {
		return 10.5;
	}

	@Override
	public ProductType getProductType() {
		return this.type;
	}

}
