package Produits;

public class JeanLevis implements Produit{
	private ProductType type;
	
	
	public JeanLevis() {
		this.type = ProductType.Clothes;
	}
	@Override
	public String getLabel() {
		return "jeanlevis";
	}

	@Override
	public double getPrice() {
		return 150.99;
	}

	@Override
	public ProductType getProductType() {
		return this.type;
	}

}
