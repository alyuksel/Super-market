package Produits;

public class TV implements Produit{
	private ProductType type;
	
	public TV() {
		this.type = ProductType.IT;
	}
	@Override
	public String getLabel() {
		return "tv";
	}

	@Override
	public double getPrice() {
		return 999.99;
	}

	@Override
	public ProductType getProductType() {
		return type;
	}

}
