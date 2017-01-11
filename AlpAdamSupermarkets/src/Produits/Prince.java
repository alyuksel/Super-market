package Produits;

public class Prince implements Produit {

	@Override
	public String getLabel() {
		return "prince";
	}

	@Override
	public double getPrice() {
		return 3;
	}

	@Override
	public ProductType getProductType() {
		return ProductType.Alimentary;
	}

}
