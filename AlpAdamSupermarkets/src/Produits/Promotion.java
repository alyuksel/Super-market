package Produits;

public class Promotion implements Produit{
	protected ProductType type;
	protected Produit produit;
	private Integer pourcentage;
	
	
	
	public Promotion(Produit produit, Integer pourcentage){
		this.produit = produit;
		this.type = produit.getProductType();
		this.pourcentage = pourcentage;
	}
	
	@Override
	public double getPrice() {
		return produit.getPrice() - (this.pourcentage*produit.getPrice()/100);
	}

	@Override
	public String getLabel() {
		return produit.getLabel() + "-"+this.pourcentage+"%";
	}
	
	@Override
	public ProductType getProductType() {
		return this.type;
	}
	
	public void affiche() {
		System.out.println(getLabel() + " " +getPrice()+"e au lieu de " + produit.getPrice()+"e");
	}
	
}
