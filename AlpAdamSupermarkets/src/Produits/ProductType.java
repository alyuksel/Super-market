package Produits;

import java.util.stream.Stream;

public enum ProductType{
	Alimentary,
	Beauty,
	Vegetables,
	Meat,
	ColdFood,
	IceCream,
	VideoGames,
	Movies,
	Promotion,
	Cleaning;
	
	public static String[] getStrings() {
	    return Stream.of(ProductType.values()).map(ProductType::toString).toArray(String[]::new);
	}
}
