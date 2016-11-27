package Produits;

public abstract class Promotion implements Produit{
	protected ProductType type;
	protected Produit produit;
	
	public Promotion(Produit produit) {
		this.produit = produit;
		this.type = produit.getProductType();
	}
	
	@Override
	public abstract String getLabel();
	
	@Override
	public abstract double getPrice();
	
}
