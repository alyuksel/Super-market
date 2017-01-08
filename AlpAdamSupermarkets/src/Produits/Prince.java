package Produits;

public class Prince implements Produit{

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "prince";
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public ProductType getProductType() {
		// TODO Auto-generated method stub
		return ProductType.Alimentary;
	}

}
