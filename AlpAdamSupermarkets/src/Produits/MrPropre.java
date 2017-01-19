package Produits;

public class MrPropre implements Produit{
	private ProductType type;
	public MrPropre() {
		this.type = ProductType.Cleaning;
	}

	@Override
	public String getLabel() {
		return "mrpropre";
	}

	@Override
	public double getPrice() {
		return 5.25;
	}

	@Override
	public ProductType getProductType() {
		return this.type;
	}
}
